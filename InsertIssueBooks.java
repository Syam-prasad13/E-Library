package project;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
@WebServlet("/InsertIssueBooks")
public class InsertIssueBooks extends HttpServlet {
private static final long serialVersionUID = 1L;
public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
try {
Connection con = DBConnect.connect();
PreparedStatement st = con.prepareStatement("insert into issuebooks values(?,?,?,?,?)");
st.setString(1, request.getParameter("BookId"));
st.setString(2, request.getParameter("StudentName"));
st.setString(3, request.getParameter("StdRegNumber"));
st.setString(4, request.getParameter("IssuedDate"));
st.setString(5, request.getParameter("IssuedTime"));
st.executeUpdate();
st.close();
con.close();
response.setContentType("text/html");
PrintWriter out = response.getWriter();
out.println("<html><body><b>Successfully Inserted</b></body></html>");
}
catch(Exception e) {
	response.setContentType("text/html");
	PrintWriter out =response.getWriter();
	out.println("error"+e);
	
	
}
}
}
