package com.yehhh.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		  String uname=request.getParameter("name");
		  String uemail=request.getParameter("email");
		  String upwd=request.getParameter("pass");
		  String repwd=request.getParameter("re_pass");
		  String umobi=request.getParameter("contact");
		 RequestDispatcher dispatcher =null;
		 Connection con = null;
		 if(uname==null || uemail==(""))
		   {
			    request.setAttribute("status", "invaildUname");
			   dispatcher = request.getRequestDispatcher("registration.jsp");
			   dispatcher.forward(request, response);
		   }
		 
		 if(uemail==null || uemail==(""))
		   {
			    request.setAttribute("status", "invaildEmail");
			   dispatcher = request.getRequestDispatcher("registration.jsp");
			   dispatcher.forward(request, response);
		   }
		   if(upwd==null || upwd==(""))
		   {
			   request.setAttribute("status", "invaildUpwd");
			   dispatcher = request.getRequestDispatcher("registration.jsp");
			   dispatcher.forward(request, response);  
		   }
		   else if(!upwd.equals(repwd))
		   {
			   request.setAttribute("status", "invaildConfirmPass");
			   dispatcher = request.getRequestDispatcher("registration.jsp");
			   dispatcher.forward(request, response);  
		   }
		   if(umobi==null || upwd==(""))
		   {
			   request.setAttribute("status", "invaildUmobi");
			   dispatcher = request.getRequestDispatcher("registration.jsp");
			   dispatcher.forward(request, response);  
		   }
		   else if(umobi.length()>10)
		   {
			   request.setAttribute("status", "invaildMobileLength");
			   dispatcher = request.getRequestDispatcher("registration.jsp");
			   dispatcher.forward(request, response);  
		   }
		 try
		 {
			 
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con=DriverManager.
			 getConnection("jdbc:mysql://localhost:3306/youtube?useSSL=false", "root", "root");

			 PreparedStatement pst = con.prepareStatement("insert into users(uname,upwd,uemail,umobi)values(?,?,?,?)");
			 pst.setString(1, uname);
			 pst.setString(2, upwd);
			 pst.setString(3, uemail);
			 pst.setString(4, umobi);
			
			  int rowCount =pst.executeUpdate();
			  dispatcher=request.getRequestDispatcher("registration.jsp");
			  
			  if(rowCount > 0)
			  {
				  request.setAttribute("status", "sucess");
			  }
			  else
			  {
				  request.setAttribute("status","failed");
			  }
			  dispatcher.forward(request, response);
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 finally
		 {
			 try {
			    con.close()	; 
			 }
			 
			 catch(Exception  ex)
			 {
				 ex.printStackTrace();
;			 }
		 }
	}

}
