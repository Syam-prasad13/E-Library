package project;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

@WebServlet("/InsertLibrarainDetails")
public class InsertLibrarainDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Connection con = DBConnect.connect();
            PreparedStatement st = con.prepareStatement("insert into addlibrarain values(?,?,?,?)");
            st.setString(1, request.getParameter("Firstname"));
            st.setString(2, request.getParameter("Lastname"));
            st.setString(3, request.getParameter("Email"));
            st.setString(4, request.getParameter("Password"));
            st.executeUpdate();
            st.close();
            con.close();
            
            // Set response content type
            response.setContentType("text/html");
            
            // Display the styled alert message using JavaScript
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<script type='text/javascript'>");
            out.println("alert('Record Successfully Inserted!');");
            out.println("</script>");
            out.println("</body></html>");
        } catch (Exception e) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("error" + e);
        }
    }
}
