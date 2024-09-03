package project;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
@WebServlet("/InsertRegisterDetails")
public class InsertRegisterDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Connection con = DBConnect.connect();
            PreparedStatement st = con.prepareStatement("INSERT INTO insertregisterdetailsnew (Name,Email,Password, PhoneNumber,Gender,Language) VALUES (?, ?, ?, ?, ?, ?)");
            
            st.setString(1, request.getParameter("Name"));
            st.setString(2, request.getParameter("Email"));
            st.setString(3, request.getParameter("Password"));
            st.setString(4, request.getParameter("PhoneNumber"));
            st.setString(5, request.getParameter("Gender"));
            st.setString(6, request.getParameter("Language"));

            st.executeUpdate();
            st.close();
            con.close();
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><b>Successfully Inserted</b></body></html>");
        } catch (Exception e) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Error: " + e);
        }
    }
}
