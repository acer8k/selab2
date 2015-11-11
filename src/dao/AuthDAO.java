package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class AuthDAO {
	static Connection cn;

	public static User getUserById(int userId){		
		String username = "";
		String password = "";
		String firstName = "";
		String lastName = "";
		String role = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/lab20", "root", "4580");
			String q = "SELECT username, firstName, lastName, role FROM user JOIN user_profile ON user.userId=user_profile.userId WHERE user.userId="+userId;

			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);
			while(rs.next()){
				username = rs.getString("username");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				role = rs.getString("role");
			}
			rs.close();
			st.close();
		}catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();

		User u = new User();
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setUsername(username);
		u.setRole(role);

		return u;		
	}

	public static int checkUserPass(String username, String password, String role){
		int userId = -1;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/lab20", "root", "4580");

			String q="SELECT userId FROM user WHERE BINARY username='"+username+"' AND BINARY password='"+password+"' AND role='"+role+"'";

			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);
			while(rs.next()){
				userId=Integer.parseInt(rs.getString("userId"));
			}
			rs.close();
			st.close();			
		}catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		if(userId!=-1)
			return userId;
		return -1;
	}

	public static boolean checkUserNameAvailable(String username){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/lab20", "root", "4580");
			String q="SELECT username FROM user";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);
			while(rs.next()){
				if(rs.getString("username").equals(username)){
					return false;
				}
			}
			rs.close();
			st.close();
		}catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		return true;
	}

	public static int enterNewUser(String username,String password, String role){
		int userId=-1;
		int ID = 0;
		//int ID=-1;
		try{
			if(checkUserNameAvailable(username)==true){
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://localhost/lab20", "root", "4580");

				String q0 = "Select userId from user ORDER BY userId DESC LIMIT 1";
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery(q0);

				if(rs.next()){
					//rs.last(); // Get ID of last User
					ID = rs.getInt("userId");
					ID++;
				}
				else
					ID=1; // Empty Table, so start with ID 1

				rs.close();
				st.close();

				String q1="insert into user values(?,?,?,?)";
				//String q1="INSERT into user(userId,username,password,role)" + "values(?,?,?,?)";
				//String q1="INSERT INTO user VALUES (" + userId + ",'"	+ username + "','" + password + "','" + role + "')";
				/*
				 * String sql = "INSERT INTO user VALUES (" + userId + ",'"	+ firstName + "','" + middleName + "','" + lastName + "')";

				INSERT into user values(				
				 */

				PreparedStatement ps = cn.prepareStatement(q1);
				ps.setInt(1,ID);
				ps.setString(2,username);
				ps.setString(3,password);
				ps.setString(4,role); 
				ps.executeUpdate();
				ps.close();

				String q2 = "SELECT userId FROM user WHERE username='"+username+"'";
				st = cn.createStatement();
				rs = st.executeQuery(q2);
				while(rs.next()){
					userId = Integer.parseInt(rs.getString("userId"));
			    }
				rs.close();
			    st.close();
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		if(userId!=-1)
			return userId;
		return -1;		
	}

	public static boolean enterUsername(int userId, String firstname, String lastname){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/lab20", "root", "4580");



			//String q1="INSERT INTO user_profile values(?,?,?)";
			//String q1="INSERT INTO user_profile values('','"+firstname+"','"+lastname+"')";
			String q1 = "INSERT into user_profile (userId, firstname, lastname)" + " values (?, ?, ?)";

			PreparedStatement ps = cn.prepareStatement(q1);
			ps.setInt(1, userId);
			ps.setString(2, firstname);
			ps.setString(3, lastname);
			ps.executeUpdate();
			ps.close();
			return true;
		}catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		return false;
	}

	public static void DB_close(){
		try{
			if(cn!=null)
				cn.close();
		}catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
