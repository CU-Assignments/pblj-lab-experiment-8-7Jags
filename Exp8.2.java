Steps to Create a JDBC-Integrated Servlet for Employee Management
1. Set Up Database
Create a MySQL database (EmployeeDB).
Create an employees table with columns (id, name, department, salary).
Insert sample employee data.

2. Set Up Your Java Project
Add MySQL JDBC Driver (via Maven or manually).
Configure Apache Tomcat in your IDE.

3. Create Database Connection Class
Write a utility class (DBConnection.java) to establish a connection with the MySQL database.

4. Develop the Servlet (EmployeeServlet.java)
- Handle GET requests to fetch all employees or search by Employee ID.
- Use JDBC to query data and display it in HTML format.
- Implement a search form for filtering employees by ID.

5. Deploy and Run
Deploy the servlet on Tomcat.
Access it via http://localhost:8080/YourAppName/EmployeeServlet.
(The page displays employee records and allows searching by ID.)

Note : Enhancements (Optional)
Improve UI with CSS & Bootstrap.



  <!DOCTYPE html>
<html>
<head><title>Search Employee</title></head>
<body>
<form action="EmployeeServlet" method="post">
Enter Employee ID: <input type="text" name="empId">
<input type="submit" value="Search">
</form>
</body>
</html>
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class EmployeeServlet extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
String empId = request.getParameter("empId");
response.setContentType("text/html");
PrintWriter out = response.getWriter();
try {
Class.forName("com.mysql.jdbc.Driver");
Connection con =
DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "password");
String query = "SELECT * FROM employees WHERE emp_id=?";
PreparedStatement ps = con.prepareStatement(query);
  ps.setString(1, empId);
ResultSet rs = ps.executeQuery();
if (rs.next()) {
out.println("<h2>Employee Details</h2>");
out.println("ID: " + rs.getInt(1) + "<br>");
out.println("Name: " + rs.getString(2) + "<br>");
out.println("Department: " + rs.getString(3));
} else {
out.println("No employee found with ID " + empId);
}
con.close();
} catch (Exception e) {
out.println("Error: " + e.getMessage());
}
}
}

