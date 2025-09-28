package main;
import java.util.Scanner;
public class MainRunning {
	public static void main(String[] args) {
		DataManipulating dm = new DataManipulating();
		Scanner sc = new Scanner(System.in);
		int role = 0;
		boolean flag = true;
		while(flag==true) {
			System.out.println("Enter your role : \n1.Admin\n2.Teacher\n3.Student");
			if(sc.hasNextInt()) {
				role= sc.nextInt();
				switch(role) {
				case 1:
					System.out.print("Enter your Username :");
					String user = sc.next();
					System.out.print("Enter your Password :");
					String pwd = sc.next();
					boolean adminRole = true;
					
					if(dm.adminLogin(user, pwd)) {
						System.out.println("Hey Admin !!");
						System.out.println("What do you want to do now ??");
						while(adminRole) {
							System.out.println("1. See all the admins\n"
									+ "2. See the applicants\n3.Exit the Role");
							int opt = sc.nextInt();
							if(opt == 1) {
								dm.adminDisplay();
							}else if(opt == 2) {
								dm.TeacherApplicationDisplay();
								System.out.println("What to change ?\n1.Add Teacher\n2.Remove a teacher\n3.Update application status");
								int ch = sc.nextInt();
								switch(ch) {
								case 1: 
									System.out.print("Enter the Teacher username :");
									String t_user = sc.next();
									System.out.print("Enter the Teacher password :");
									String t_pwd = sc.next();
									dm.newApplicantByAdmin(t_user, t_pwd);
									System.out.println("Teacher data inserted Successfuly");
									break;
								case 2:
									System.out.print("Enter the ID of Teacher to be removed : ");
									int id = sc.nextInt();
									dm.deleteTheTeacher(id);
									System.out.println("Application removed Successfully !!");
									break;
								case 3:
									System.out.println("Enter \n1.Approving the application.\n2.Removing the application.");
									int st1 = sc.nextInt();
									if(st1 == 1) {
										System.out.print("Enter Teacher ID :");
										int id1 = sc.nextInt();
										dm.approveTeacher(id1);
										System.out.println("Approval Successful");
									}else if(st1 == 2) {
										System.out.println("Enter teacher ID :");
										int id1 = sc.nextInt();
										dm.approveTeacher(id1);
										System.out.println("Rejection successful");
									}
									break;
								}
							}else if(opt == 3) {
								adminRole = false;
							}else {
								System.out.println("Enter a valid choice");
							}
						}
					}
					break;
					
				case 2 :
					boolean teacherRole = true;
					System.out.println("1.New applicant\n2.Existing one\n3.Exit");
					int app = sc.nextInt();
					if(app == 1) {
						System.out.print("Create your Username :");
						String t_user = sc.next();
						System.out.print("Create your Password :");
						String t_pwd = sc.next();
						dm.newApplicantByTeacher(t_user, t_pwd);
						System.out.println("Application submitted Sucessfully");
						teacherRole = false;
					}else if(app == 2) {
						System.out.print("Enter your Username :");
						String t_user = sc.next();
						System.out.print("Enter your Password :");
						String t_pwd = sc.next();
						while(teacherRole) {
							if(dm.teacherLogin(t_user, t_pwd)) {
								System.out.println("Hey "+t_user+" Teacher Welcome !!");
								if("PENDING".equals(dm.getStatus(t_user))) {
									System.out.println("Your application is PENDING ");
									teacherRole= false;
								}else if("REJECTED".equals(dm.getStatus(t_user))) {
									System.out.println("Sorry your application is Rejected ");
									teacherRole = false;
								}else {
									System.out.println("What do you want to do now ??");
									System.out.println("1. To add Student\n2. To Check the Attendance\n3.Exit");
									int t_opt = sc.nextInt();
									int id = dm.getId(t_user);
									if(t_opt == 1) {
										System.out.print("Enter the Name of Student :");
										String s_name = sc.next();
										System.out.print("Enter the password for Student :");
										String s_pwd = sc.next();
										dm.createStudent(id, s_name, s_pwd);
										System.out.println("Student Data added Successfully !!");
									}else if (t_opt == 2) {
										System.out.println("-----The Attendace Report-----");
										dm.printAttendanceSummary(id);
									}else if(t_opt == 3){
										teacherRole = false;
									}
								}
							}else {
								System.out.println("Incorrect Credentials");
								System.out.print("Enter correct Username :");
								t_user = sc.next();
								System.out.print("Enter correct Password :");
								t_pwd = sc.next();
							}
						}
					}
					break;
				case 3:
					boolean studentRole = true;
					System.out.print("Enter the username :");
					String name = sc.next();
					System.out.print("Enter the password :");
					String pwd1 = sc.next();
					while(studentRole) {
						if(dm.studentLogin(name, pwd1)) {
							System.out.println("Hey Student !!");
							System.out.println("1. Mark Attendance\n2. Check Attendance\n3.Exit");
							int studentOpt = sc.nextInt();
							if(studentOpt == 1) {
								System.out.println("Your attendance has marked successfully !!");
								dm.markAttendance(name);
							}else if(studentOpt == 2) {
								System.out.println("Number of classes presented were : "+ dm.returnPresentDays(name));
							}else if(studentOpt == 3) {
								studentRole = false;
							}else {
								System.out.println("Enter a valid Option");
							}
						}else {
							System.out.println("Invalid Credentials");
							System.out.print("Enter correct username :");
							name = sc.next();
							System.out.print("Enter correct password :");
							pwd1 = sc.next();
						}
					}
				}
					
			}else {
				System.out.println("Enter a valid role");
			}
		}
		sc.close();
	}
}