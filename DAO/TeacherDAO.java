package DAO;
import java.sql.*;
public class TeacherDAO {
	public boolean login(String user,String pwd) {
		String str = "SELECT * FROM teacher  WHERE username = ? AND password = ?";
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(str)){
			ps.setString(1,user);
			ps.setString(2,pwd);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		}catch(Exception e) { e.printStackTrace(); } 
		return false;
	}
	public void newApplicantByTeacher(String username,String password) {
		String str = "INSERT INTO teacher (username,password) VALUES (?,?)";
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(str)){
			st.setString(1,username);
			st.setString(2, password);
			st.executeUpdate();
		}catch(Exception e ) {e.printStackTrace(); }
	}
	public void createStudent(int teacherId, String username, String password) {
		String sql = "INSERT INTO student (teacher_id, username, password) VALUES (?,?,?)";
	    try (Connection conn = DBUtil.getConnection();
	    		PreparedStatement ps = conn.prepareStatement(sql)) {
	    	ps.setInt(1, teacherId);
	    	ps.setString(2, username);
	    	ps.setString(3, password);
	    	ps.executeUpdate();
	    } catch (Exception e) { e.printStackTrace(); }
	}
	public void printAttendanceSummary(int teacherId) {
		String sql = "SELECT s.student_id, s.username, COUNT(a.student_id) AS total_presents " +
				"FROM student s " +
				"LEFT JOIN attendance a ON s.student_id = a.student_id " +
	            "WHERE s.teacher_id = ? " +
				"GROUP BY s.student_id, s.username";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, teacherId);
	        ResultSet rs = ps.executeQuery();
	        System.out.println("Attendance Summary for Teacher ID: " + teacherId);
	        System.out.println("-------------------------------------------");
	        System.out.printf("%-5s %-15s %-10s%n", "ID", "Student", "Total_Presents");
	        System.out.println("-------------------------------------------");
	        while (rs.next()) {
	        	int id = rs.getInt("student_id");
	            String name = rs.getString("username");
	            int presents = rs.getInt("total_presents");
	            System.out.printf("%-5d %-15s %-10d%n", id, name, presents);
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	public int id(String t_id) {
		String st = "SELECT teacher_id FROM teacher WHERE username = ?";
		int id = 0;
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(st)){
			stmt.setString(1,t_id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				id = rs.getInt("teacher_id");
			}
		}catch(Exception e ) { e.printStackTrace(); }
		return id;
	}
	public String status(String username) {
		String str = "SELECT status FROM teacher WHERE username= ?";
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(str)){
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getString("status");
			}
		}catch(Exception e) { e.printStackTrace(); }
		return "PENDING";
	}
}
