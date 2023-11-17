import java.sql.*;  
import java.io.*;

public class sendPdf
	{ 
            public static void main(String[] args)
		        {  
					    try
                          {
					        Class.forName("com.mysql.jdbc.Driver");  
                            Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/collegedb","root","2580");  
                             String  upQue= "insert into syllabus values(?,?,?)";
					         PreparedStatement ps1 = con.prepareStatement(upQue);
							 ps1.setInt(1,105);
							 ps1.setString(2,"MCA(INTEGRATED)V");
							 FileInputStream pdf = new FileInputStream(new File("C:\\Users\\user\\OneDrive\\Desktop\\Projects sql\\Syllabus\\5thYear.pdf"));
							ps1.setBlob(3, pdf);
							ps1.executeUpdate();

                         }
                   catch (Exception e)
                        {
					        System.out.println(e);
                       }                   
                }  
 }  