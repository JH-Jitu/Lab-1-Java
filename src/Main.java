import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

enum EmployeeRecordsType {
    Officer, Staff
}

class EmployeeRecords {
    private final int id;
    private final String name;
    private final LocalDate dateOfBirth;
    private final String email;
    private final LocalDate joiningDate;
    private final EmployeeRecordsType employeeType;

    public EmployeeRecords(int id, String name, LocalDate dateOfBirth, String email, LocalDate joiningDate, EmployeeRecordsType employeeType) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.joiningDate = joiningDate;
        this.employeeType = employeeType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public EmployeeRecordsType getEmployeeRecordsType() {
        return employeeType;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nName: " + name +
                "\nDate of Birth: " + dateOfBirth +
                "\nEmail: " + email +
                "\nJoining Date: " + joiningDate +
                "\nEmployee Type: " + employeeType;
    }
}

class CalculateLeavings {
    public static int calculateLeave(EmployeeRecords employee, int totalLeaveDays) {
        LocalDate yearEnd = LocalDate.of(employee.getJoiningDate().getYear(), 12, 31);
        long daysWorked = ChronoUnit.DAYS.between(employee.getJoiningDate(), yearEnd) + 1;
        double divisor = yearEnd.isLeapYear() ? 366.0 : 365.0;
        return (int) Math.ceil((daysWorked * totalLeaveDays) / divisor);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<EmployeeRecords> employees = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            System.out.println("Enter employee information for employee " + (i + 1));
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Date of Birth (yyyy-MM-dd): ");
            LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Joining Date (yyyy-MM-dd): ");
            LocalDate joiningDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Employee Type (Officer or Staff): ");
            EmployeeRecordsType employeeType = EmployeeRecordsType.valueOf(scanner.nextLine());

            EmployeeRecords employee = new EmployeeRecords(id, name, dateOfBirth, email, joiningDate, employeeType);
            employees.add(employee);
        }

        System.out.println("\nEmployee Details and Leave Information:");
        for (EmployeeRecords employee : employees) {
            int vacationLeave = CalculateLeavings.calculateLeave(employee, (employee.getEmployeeRecordsType() == EmployeeRecordsType.Officer) ? 15 : 10);
            int sickLeave = CalculateLeavings.calculateLeave(employee, (employee.getEmployeeRecordsType() == EmployeeRecordsType.Officer) ? 10 : 7);

            System.out.println(employee);
            System.out.println("Vacation Leave: " + vacationLeave);
            System.out.println("Sick Leave: " + sickLeave);
            System.out.println();
        }
    }
}
