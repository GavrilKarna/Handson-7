package Task3;

import java.time.LocalDate;
import java.time.Period;

// Implementasikan class Employee dengan constructor chaining
class Employee {
    // Instance variables
    // employeeId, firstName, lastName, department, position, salary, hireDate
    // Instance variables
    String employeeId;
    String firstName;
    String lastName;
    String department;
    String position;
    double salary;
    LocalDate hireDate;

    // Constructor chaining examples
    // Buat multiple constructors yang saling memanggil dengan this()
    public Employee() {
        this("N/A", "Unknown", "Employee");
    }

    public Employee(String employeeId, String firstName, String lastName) {
        this(employeeId, firstName, lastName, "General", "Staff", 0.0, LocalDate.now());
    }

    public Employee(String employeeId, String firstName, String lastName, String department, String position, double salary, LocalDate hireDate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    // Methods
    // getFullName()
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    // calculateYearsOfService()
    public int calculateYearsOfService() {
        return Period.between(this.hireDate, LocalDate.now()).getYears();
    }

    // getEmployeeInfo()
    public void getEmployeeInfo() {
        System.out.println("Employee: " + getFullName() + " | Dept: " + department + " | Pos: " + position + " | Gaji: " + salary);
    }

    // giveRaise(double percentage)
    public void giveRaise(double percentage) {
        this.salary += this.salary * (percentage / 100);
        System.out.println("Kenaikan gaji " + percentage + "% diberikan. Gaji baru: " + salary);
    }
}
