package com.ruppyrup.episode10.smelly;

import com.ruppyrup.episode10.ExpenseReport;
import com.ruppyrup.episode10.ReportPrinter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class SmellyExpenseReporter {

  private static final DateTimeFormatter FORMATTERS = DateTimeFormatter.ofPattern("d/MM/uuuu");
  List<Expense> expenses = new ArrayList<>();

  public void addExpense(Expense expense) {
    expenses.add(expense);
  }


  public void printReport(ReportPrinter printer) {
    int total = 0;
    int mealExpenses = 0;

    // prints the header - could extract and call this function printHeader()
    printer.print("Expenses " + getDate() + "\n");

    // could extract out into a method called print expenses
    for (Expense expense : expenses) {
      // if expense is a meal add to the meal expenses
      if (expense.type == Expense.Type.BREAKFAST || expense.type == Expense.Type.DINNER)
        mealExpenses += expense.amount;

      // label the expense type
      String name = switch (expense.type) {
        case DINNER -> "Dinner";
        case BREAKFAST -> "Breakfast";
        case CAR_RENTAL -> "Car Rental";
      };

      // add the overage indicator
      printer.print(String.format("%s\t%s\t$%.02f\n",
          (expense.type == Expense.Type.DINNER && expense.amount > 5000)
          || (expense.type == Expense.Type.BREAKFAST && expense.amount > 1000) ? "X" : " ", name, expense.amount / 100.0));

      total += expense.amount;
    }

    // should be extracted out to printTotals - can extract a method to convert Pennies to Dollars
    printer.print(String.format("\nMeal expenses $%.02f", mealExpenses / 100.0));
    printer.print(String.format("\nTotal $%.02f", total / 100.0));
  }

  private String getDate() {
    return "9/12/2002";
  }
}
