package com.ruppyrup.episode10.smelly;


import com.ruppyrup.episode10.MockReportPrinter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SmellyExpenseReportTest {
  private SmellyExpenseReporter reporter;
  private MockReportPrinter printer;

  @BeforeEach
  public void setUp() {
    reporter = new SmellyExpenseReporter();
    printer = new MockReportPrinter();
  }

  @Test
  public void printEmpty() {
    reporter.printReport(printer);

    Assertions.assertThat(printer.getText()).isEqualTo( "Expenses 9/12/2002\n" +
                                                            "\n" +
                                                            "Meal expenses $0.00\n" +
                                                            "Total $0.00");
  }

  @Test
  public void printOneDinner() {
    reporter.addExpense(new Expense(Expense.Type.DINNER, 1678));
    reporter.printReport(printer);

    Assertions.assertThat(printer.getText()).isEqualTo( "Expenses 9/12/2002\n" +
                                                            " \tDinner\t$16.78\n" +
                                                            "\n" +
                                                            "Meal expenses $16.78\n" +
                                                            "Total $16.78");
  }

  // how easy is it to add the new lunch type?
  @Test
  public void threeMeals() throws Exception {
    reporter.addExpense(new Expense(Expense.Type.DINNER, 1000));
    reporter.addExpense(new Expense(Expense.Type.BREAKFAST, 500));
//    reporter.addExpense(new Expense(Expense.Type.LUNCH), 7);
    reporter.printReport(printer);

    Assertions.assertThat(printer.getText()).isEqualTo( "Expenses 9/12/2002\n" +
                                                            " \tDinner\t$10.00\n" +
                                                            " \tBreakfast\t$5.00\n" +
//                                                            " \tLunch\t$7.00\n" +

                                                            "\n" +
                                                            "Meal expenses $15.00\n" +
                                                            "Total $15.00");
  }

  @Test
  public void twoMealsAndCarRental() throws Exception {
    reporter.addExpense(new Expense(Expense.Type.DINNER, 1000));
    reporter.addExpense(new Expense(Expense.Type.BREAKFAST, 500));
    reporter.addExpense(new Expense(Expense.Type.CAR_RENTAL, 50000));
    reporter.printReport(printer);

    Assertions.assertThat(printer.getText()).isEqualTo( "Expenses 9/12/2002\n" +
        " \tDinner\t$10.00\n" +
            " \tBreakfast\t$5.00\n" +
            " \tCar Rental\t$500.00\n" +
            "\n" +
            "Meal expenses $15.00\n" +
            "Total $515.00");

  }

  @Test
  public void overages() throws Exception {
    reporter.addExpense(new Expense(Expense.Type.BREAKFAST, 1000));
    reporter.addExpense(new Expense(Expense.Type.BREAKFAST, 1001));
    reporter.addExpense(new Expense(Expense.Type.DINNER, 5000));
    reporter.addExpense(new Expense(Expense.Type.DINNER, 5001));
//    report.addExpense(new LunchExpense(3000));
//    report.addExpense(new LunchExpense(3001));
    reporter.printReport(printer);

    Assertions.assertThat(printer.getText()).isEqualTo( "Expenses 9/12/2002\n" +
                                                            " \tBreakfast\t$10.00\n" +
                                                            "X\tBreakfast\t$10.01\n" +
                                                            " \tDinner\t$50.00\n" +
                                                            "X\tDinner\t$50.01\n" +
//                                                            " \tLunch\t$30.00\n" +
//                                                            "X\tLunch\t$30.01\n" +
                                                            "\n" +
                                                            "Meal expenses $120.02\n" +
                                                            "Total $120.02");
  }

}
