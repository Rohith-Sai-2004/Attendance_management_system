package main;
import DAO.AdminDAO;
import DAO.TeacherDAO;
import DAO.StudentDAO;
public class DataManipulating {
	private AdminDAO admin;
	private TeacherDAO teacher;
	private StudentDAO student;
	
	public DataManipulating() {
		admin = new AdminDAO();
		teacher = new TeacherDAO();
		student = new StudentDAO();
	}
	
	public boolean adminLogin(String username, String password) {
        return admin.login(username, password);
    }
    public void approveTeacher(int teacherId) {
        admin.approveTeacher(teacherId);
    }
    public void rejectTeacher(int teacherId) {
    	admin.rejectTeacher(teacherId);
    }
    public void adminDisplay() {
    	admin.adminDisplay();
    }
    public void TeacherApplicationDisplay() {
    	admin.teacherApplicationDisplay();
    }
    public void newApplicantByAdmin(String username,String password) {
    	admin.newApplicantByAdmin(username, password);
    }
    public void deleteTheTeacher(int id) {
    	admin.removingTheTeacher(id);
    }
    
    public boolean teacherLogin(String username, String password) {
        return teacher.login(username, password);
    }
    public void createStudent(int teacherId, String username, String password) {
        teacher.createStudent(teacherId, username, password);
    }
    public void printAttendanceSummary(int teacherId) {
        teacher.printAttendanceSummary(teacherId);
    }
    public int getId(String t_user) {
    	return teacher.id(t_user);
    }
    public void newApplicantByTeacher(String username,String pwd) {
    	teacher.newApplicantByTeacher(username, pwd);
    }
    public String getStatus(String username) {
    	return teacher.status(username);
    }
    
    public boolean studentLogin(String username, String password) {
        return student.login(username, password);
    }
    public void markAttendance(String username) {
        int studentId = student.getStudentId(username);
        if (studentId != -1) {
            student.markAttendance(studentId);
        } else {
            System.out.println("Student not found: " + username);
        }
    }
    public int returnPresentDays(String username) {
    	int student_id = student.getStudentId(username);
    	if (student_id != -1) {
            return student.numberOfPresentDays(student_id);
        } else {
            System.out.println("Student not found: " + username);
        }
		return 0;
    }
}
