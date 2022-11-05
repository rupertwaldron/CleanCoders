package com.ruppyrup.episode10;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExpenseReportTest {
  private ExpenseReport report;
  private ExpenseReporter reporter;
  private MockReportPrinter printer;

  @BeforeEach
  public void setUp() {
    report = new ExpenseReport();
    reporter = new ExpenseReporter(report);
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
    report.addExpense(new DinnerExpense(1678));
    reporter.printReport(printer);

    Assertions.assertThat(printer.getText()).isEqualTo( "Expenses 9/12/2002\n" +
                                                            " \tDinner\t$16.78\n" +
                                                            "\n" +
                                                            "Meal expenses $16.78\n" +
                                                            "Total $16.78");
  }

  @Test
  public void threeMeals() throws Exception {
    report.addExpense(new DinnerExpense(1000));
    report.addExpense(new BreakfastExpense(500));
    report.addExpense(new LunchExpense(700));
    reporter.printReport(printer);

    Assertions.assertThat(printer.getText()).isEqualTo( "Expenses 9/12/2002\n" +
                                                            " \tDinner\t$10.00\n" +
                                                            " \tBreakfast\t$5.00\n" +
                                                            " \tLunch\t$7.00\n" +

                                                            "\n" +
                                                            "Meal expenses $22.00\n" +
                                                            "Total $22.00");
  }

  @Test
  public void twoMealsAndCarRental() throws Exception {
    report.addExpense(new DinnerExpense(1000));
    report.addExpense(new BreakfastExpense(500));
    report.addExpense(new CarRentalExpense(50000));
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
    report.addExpense(new BreakfastExpense(1000));
    report.addExpense(new BreakfastExpense(1001));
    report.addExpense(new DinnerExpense(5000));
    report.addExpense(new DinnerExpense(5001));
    report.addExpense(new LunchExpense(3000));
    report.addExpense(new LunchExpense(3001));
    reporter.printReport(printer);

    Assertions.assertThat(printer.getText()).isEqualTo( "Expenses 9/12/2002\n" +
                                                            " \tBreakfast\t$10.00\n" +
                                                            "X\tBreakfast\t$10.01\n" +
                                                            " \tDinner\t$50.00\n" +
                                                            "X\tDinner\t$50.01\n" +
                                                            " \tLunch\t$30.00\n" +
                                                            "X\tLunch\t$30.01\n" +
                                                            "\n" +
                                                            "Meal expenses $180.03\n" +
                                                            "Total $180.03");
  }

}
