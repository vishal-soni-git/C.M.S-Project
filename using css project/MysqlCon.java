import java.sql.*;  

public class MysqlCon
	{  
			private Connection con;
            public Connection getMyCon()
		        {  
				if(con==null)
					{
					    try
                          {
					        Class.forName("com.mysql.jdbc.Driver");  
                             con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/collegedb","root","2580");  
                         }
                   catch (Exception e)
                        {
					        System.out.println(e);
                       }  
					   return con;
						
					}
					else
					{
						return con;
					}
                   
                }  
 }  