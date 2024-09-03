package project;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
private static final long serialVersionUID = 1L;
public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
try {
Connection con = DBConnect.connect();
PreparedStatement st = con.prepareStatement("insert into addbook values(?,?,?,?,?)");
st.setString(1, request.getParameter("BookId"));
st.setString(2, request.getParameter("BookName"));
st.setString(3, request.getParameter("BookAuthor"));
st.setString(4, request.getParameter("BookVolume"));
st.setString(5, request.getParameter("BookCost"));
st.executeUpdate();
st.close();
con.close();
response.setContentType("text/html");
PrintWriter out = response.getWriter();
out.println("<html><body><b>Successfully Inserted</b></body></html>");
}
catch(Exception e) {
	response.setContentType("text/html");
    PrintWriter out = response.getWriter();
	out.println("error"+e);
	
	
}
}
}