package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import static dao.AuthDAO.*;

/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String check = request.getParameter("check"); 
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String role = request.getParameter("role");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		
		String url="";
		String msg="";
		
		int userId=0;
		boolean available=false;
		boolean status;
		
		int flag=0;
		try{
			
		if(username.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Username missing!!";
			request.setAttribute("msg", msg);
			flag=0;
		}
		else{
			if(request.getParameter("check")!= null){
				available = checkUserNameAvailable(username);
				if(available){
					url = "/signup.jsp";
					msg = msg + "\nUsername Available!";
					request.setAttribute("msg", msg);
					flag=1;
				}
				else{
					url = "/signup.jsp";
					msg = msg + "\n Username NOT available, please choose another!\n";
					request.setAttribute("msg", msg);
					flag=0;
				}
			}
		}

		if(firstname.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Firstname missing!!!";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(lastname.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Lastname missing!!!!";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(password.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Please fill-in Password";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(cpassword.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Please type Confirm password again";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(! password.equals(cpassword)){
			url = "/signup.jsp";
			msg = msg + "\nPassword does not match!";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(role == null){
			url = "/signup.jsp";
			msg = msg + "\n You must select a role!!!!";
			request.setAttribute("msg", msg);
			flag=0;		
			
		}
		else if(username.length()!=0 && firstname.length()!=0 && lastname.length()!=0 && role != null
				&& password.length()!=0 && cpassword.length()!=0 && password.equals(cpassword))
			flag = 1;
		if(flag ==1){
			userId = enterNewUser(username, password, role);
			
			if(userId >0){
				status = enterUsername(userId, firstname, lastname);
				if(status == true){
					msg = "Your account is created successfully!";
					request.setAttribute("msg", msg);
					url = "/login.jsp";
				}
				else{
					msg = "UserName Insert Failed, ERROR";
					request.setAttribute("msg", msg);
					url = "/signup.jsp";
				}
			}
			else{
				msg = "Cannot create account, try again!";
				request.setAttribute("msg", msg);
				url = "/signup.jsp";
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		DB_close();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}

















