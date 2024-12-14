import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Employee_Data{
    public void addEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO employees (name, designation, department, salary, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getDesignation());
            stmt.setString(3, employee.getDepartment());
            stmt.setDouble(4, employee.getSalary());
            stmt.setString(5, employee.getEmail());
            stmt.setString(6, employee.getPhone());
            stmt.executeUpdate();
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (Connection con = DBConnection.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setDesignation(rs.getString("designation"));
                emp.setDepartment(rs.getString("department"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                employees.add(emp);
            }
        }
        return employees;
    }


    public void updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE employees SET name = ?, designation = ?, department = ?, salary = ?, email = ?, phone = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getDesignation());
            stmt.setString(3, employee.getDepartment());
            stmt.setDouble(4, employee.getSalary());
            stmt.setString(5, employee.getEmail());
            stmt.setString(6, employee.getPhone());
            stmt.setInt(7, employee.getId());
            stmt.executeUpdate();
            System.out.println("Employee updated successfully!");
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("No employee found with ID: " + id);
            }
        }
    }


}

