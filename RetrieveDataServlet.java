package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RetrieveData")
public class RetrieveDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Replace the databaseURL, username, and password with your database connection details
            String databaseURL = "jdbc:mysql://localhost:3306/books";
            String username = "root";
            String password = "";

            // Create a connection to the database
            Connection connection = DriverManager.getConnection(databaseURL, username, password);

            // SQL query to retrieve data with count
            String sql = "SELECT AccessionNumber, TitleId, TitleName, AllowLend, AuthorName, AuthorId, SupplierName, BillNo, COUNT(TitleId) AS BookCount FROM booksdata GROUP BY TitleId";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Display the retrieved data in a styled table with count column
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Book Details</title>");
            out.println("<style>");
            out.println("body { font-family: 'Arial', sans-serif; background-color: #f4f4f4; }");
            out.println("table { width: 80%; border-collapse: collapse; margin: 20px auto; }");
            out.println("th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println("th { background-color: #4CAF50; color: white; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table border=2.0>");
            out.println("<tr><th>Accession Number</th><th>Title ID</th><th>Title Name</th><th>Allow Lend</th><th>Author Name</th><th>Author ID</th><th>Supplier Name</th><th>Bill Number</th><th>Book Count</th></tr>");

            while (resultSet.next()) {
                String accessionNumber = resultSet.getString("AccessionNumber");
                String titleId = resultSet.getString("TitleId");
                String titleName = resultSet.getString("TitleName");
                String allowLend = resultSet.getString("AllowLend");
                String authorName = resultSet.getString("AuthorName");
                String authorId = resultSet.getString("AuthorId");
                String supplierName = resultSet.getString("SupplierName");
                String billNo = resultSet.getString("BillNo");
                int bookCount = resultSet.getInt("BookCount");

                out.println("<tr>");
                out.println("<td>" + accessionNumber + "</td>");
                out.println("<td>" + titleId + "</td>");
                out.println("<td>" + titleName + "</td>");
                out.println("<td>" + allowLend + "</td>");
                out.println("<td>" + authorName + "</td>");
                out.println("<td>" + authorId + "</td>");
                out.println("<td>" + supplierName + "</td>");
                out.println("<td>" + billNo + "</td>");
                out.println("<td>" + bookCount + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            out.println("</body>");
            out.println("</html>");

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error:" + e.getMessage());
        }
    }
}
