package com.ruppyrup.episode2;

public class Prose {

    private Employee employee = new Employee();

    public void testProse() {

        if (employee.isLate())
            employee.reprimand();
    }



    private static class Employee {

        private boolean isLate() {
            return true;
        }

        private void reprimand() {
            System.out.println("Employee is reprimanded");
        }
    }
}
