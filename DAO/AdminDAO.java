package DAO;
import java.sql.*;
public class AdminDAO {
	public boolean login(String user,String pwd) {
		String str = "SELECT * FROM admin WHERE username = ? AND password = ? ";
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(str)){
			ps.setString(1, user);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		}catch(Exception e ) { e.printStackTrace(); }
		return false;
	}
	public void adminDisplay() {
		String str = "SELECT username FROM admin";
		try(Connection conn = DBUtil.getConnection();
				Statement st = conn.createStatement() ){
			ResultSet rs = st.executeQuery(str);
			while(rs.next()) {
				System.out.println(rs.getString("username"));
			}
		}catch(Exception e) { e.printStackTrace(); }
	}
	public void teacherApplicationDisplay() {
		String str = "SELECT teacher_id,username,status FROM teacher";
		try(Connection conn = DBUtil.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(str)){
			System.out.println("ID\tName\tStatus");
			System.out.println("------------------------------------");
			while(rs.next()) {
				int id = rs.getInt("teacher_id");
				String name =  rs.getString("username");
				String status = rs.getString("status");
				System.out.println(id+"\t"+name+"\t"+status);
			}
		}catch(Exception e) {e.printStackTrace(); }
	}
	public void newApplicantByAdmin(String username,String password) {
		String str = "INSERT INTO teacher (username,password) VALUES (?,?)";
		String s = "UPDATE teacher SET status='APPROVED' WHERE username=?";
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(str);
				PreparedStatement upst = conn.prepareStatement(s)){
			st.setString(1,username);
			st.setString(2, password);
			st.executeUpdate();
			upst.setString(1,username);
			upst.executeUpdate();
		}catch(Exception e ) {e.printStackTrace(); }
	}
	public void removingTheTeacher(int id) {
		String str = "DELETE FROM teacher WHERE teacher_id= ?";
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement pt = conn.prepareStatement(str)){
			pt.setInt(1, id);
			pt.executeUpdate();
		}catch(Exception e ) { e.printStackTrace(); }
	}
	public void approveTeacher(int teacher_id) {
		String str = "UPDATE teacher SET status='APPROVED' WHERE teacher_id=?";
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(str)){
			ps.setInt(1, teacher_id);
			ps.executeUpdate();
		}catch(Exception e) { e.printStackTrace(); }
	}
	public void rejectTeacher(int teacher_id) {
		String str = "UPDATE teacher SET status='REJECTED' WHERE teacher_id=?";
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(str)){
			ps.setInt(1, teacher_id);
			ps.executeUpdate();
		}catch(Exception e) { e.printStackTrace(); }
	}
	
}
