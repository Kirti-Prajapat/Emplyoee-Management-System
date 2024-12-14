import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Employee_Data employeeDA = new Employee_Data();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 ->{
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
    private static void addEmployee() {
        try {
            Employee emp = new Employee();
            System.out.print("Enter Name: ");
            emp.setName(scanner.nextLine());
            System.out.print("Enter Designation: ");
            emp.setDesignation(scanner.nextLine());
            System.out.print("Enter Department: ");
            emp.setDepartment(scanner.nextLine());
            System.out.print("Enter Salary: ");
            emp.setSalary(scanner.nextDouble());
            scanner.nextLine();
            System.out.print("Enter Email: ");
            emp.setEmail(scanner.nextLine());
            System.out.print("Enter Phone: ");
            emp.setPhone(scanner.nextLine());

            employeeDA.addEmployee(emp);
            System.out.println("Employee added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
        }
    }

    private static void viewEmployees() {
        try {
            List<Employee> employees = employeeDA.getAllEmployees();
            System.out.println("\nEmployee List:");
            for (Employee emp : employees) {
                System.out.println(emp.getId() + ". " + emp.getName() + " - " + emp.getDesignation() + " - " + emp.getDepartment() + " - " + emp.getSalary());
            }
        } catch (SQLException e) {
            System.err.println("Error fetching employees: " + e.getMessage());
        }

    }
    private static void updateEmployee() {
        try {
            Employee emp = new Employee();
            System.out.print("Enter Employee ID to update: ");
            emp.setId(scanner.nextInt());
            scanner.nextLine(); // Clear the newline
            System.out.print("Enter Name: ");
            emp.setName(scanner.nextLine());
            System.out.print("Enter Designation: ");
            emp.setDesignation(scanner.nextLine());
            System.out.print("Enter Department: ");
            emp.setDepartment(scanner.nextLine());
            System.out.print("Enter Salary: ");
            emp.setSalary(scanner.nextDouble());
            scanner.nextLine();
            System.out.print("Enter Email: ");
            emp.setEmail(scanner.nextLine());
            System.out.print("Enter Phone: ");
            emp.setPhone(scanner.nextLine());

            employeeDA.updateEmployee(emp);
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
        }
    }

    private static void deleteEmployee() {
        try {
            System.out.print("Enter Employee ID to delete: ");
            int id = scanner.nextInt();
            employeeDA.deleteEmployee(id);
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
    }

}
