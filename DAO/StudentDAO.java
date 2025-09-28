package DAO;
import java.sql.*;
public class StudentDAO {
    public boolean login(String username, String password) {
        String sql = "SELECT student_id FROM student WHERE username=? AND password=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void markAttendance(int studentId) {
        String sql = "INSERT INTO attendance (student_id) VALUES (?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.executeUpdate();
            System.out.println("Attendance marked for Student ID: " + studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getStudentId(String username) {
        String sql = "SELECT student_id FROM student WHERE username=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("student_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public int numberOfPresentDays(int id) {
    	String sql = "SELECT count(student_id) as presents from attendance where student_id=? ";
    	try(Connection conn = DBUtil.getConnection();
    			PreparedStatement ps = conn.prepareStatement(sql)){
    		ps.setInt(1, id);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			return rs.getInt("presents");
    		}
    	}catch(Exception e) { e.printStackTrace(); }
    	return 0;
    }
}
