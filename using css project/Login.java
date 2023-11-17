import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Pos;
import java.sql.*;
  
//Doveloper Vishal Sir
 public class Login extends Application {
    public void start(Stage primaryStage) {
        
         primaryStage.setTitle("Login Window");
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,50,50,50));
         
        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(20,20,20,30));
		hb.setAlignment(Pos.CENTER);
         
        //Adding GridPane
        GridPane gridPane = new GridPane();gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(5);
        gridPane.setVgap(15);
         
       //Implementing Nodes for GridPane
        Label lblUserName = new Label("Username");
        final TextField txtUserName = new TextField();
        Label lblPassword = new Label("Password");
        final PasswordField pf = new PasswordField();
        Button btnLogin = new Button("Log in");
        final Label lblMessage = new Label();
		Button btnSignUp = new Button("Sign up");
         
        //Adding Nodes to GridPane layout
        gridPane.add(lblUserName, 0, 0);
        gridPane.add(txtUserName, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(pf, 1, 1);
        gridPane.add(btnLogin, 1, 2);
        gridPane.add(btnSignUp,2,2);
		gridPane.add(lblMessage, 1, 3);
         
                 
        //Reflection for gridPane
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        gridPane.setEffect(r);
         
        //DropShadow effect 
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
         
        //Adding text and DropShadow effect to it
        Text text = new Text("Login Page");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);
         
        //Adding text to HBox
        hb.getChildren().add(text);
                           
        //Add ID's to Nodes
        bp.setId("bp");
        gridPane.setId("root");
        btnLogin.setId("btnLogin");
		btnSignUp.setId("btnSignUp");
        text.setId("text");
                 
        //Action for btnLogin
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
         public void handle(ActionEvent event) {

			  String email=txtUserName.getText();
			 String password=pf.getText();
			 if(email.equals(""))
			     {
				      Alert al1 = new Alert(AlertType.INFORMATION);
		              al1.setTitle("Message");
					  al1.setHeaderText("Message:");
                	  al1.setContentText("Please Enter The Email ID !!");
					  al1.showAndWait();
			      }
				  else if(password.equals(""))
			{     
					  Alert al2 = new Alert(AlertType.INFORMATION);
		              al2.setTitle("Message");
					  al2.setHeaderText("Message:");
                	  al2.setContentText("Please Enter The Password !!");
					  al2.showAndWait();			
			}
			else
			{
				   try
			           {
						      Class.forName("com.mysql.jdbc.Driver");  
                              Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/collegedb","root","2580");  
       
	                         String chkValidation="select * from logindata where email=? and password=?";
                             PreparedStatement ps=con.prepareStatement(chkValidation);
		                     ps.setString(1,email);
							 ps.setString(2,password);
		                     ResultSet result=ps.executeQuery();

							 	 if(result.next())	 
				                       {
										   Alert al3 = new Alert(AlertType.INFORMATION);
		                                   al3.setTitle("Message");
					                       al3.setHeaderText("Message:");
                	                       al3.setContentText("Your Information is Correct !!");
					                       al3.showAndWait();
										   lblMessage.setText("Congratulations!");
                                           lblMessage.setTextFill(Color.GREEN);

										 //  teacher.Project3 p3=new teacher.Project3();
										    CollegePage tp=new CollegePage();
			                              tp.start(new Stage());
										  primaryStage.close();		
				                        }//if end
									else
									   {
                                            Alert al4 = new Alert(AlertType.INFORMATION);
		                                   al4.setTitle("Message");
					                       al4.setHeaderText("Message:");
                	                       al4.setContentText("Your Information is Incorrect !!\nplease!! Enter Correct Details !\nor Register First");
					                       al4.showAndWait();
										    lblMessage.setText("Incorrect user or password.");
                                            lblMessage.setTextFill(Color.RED);
									   }
			            }
			        catch (Exception ex)
			            {
				          ex.printStackTrace();
			            }				
			}			
         }
         });

		 	btnSignUp.setOnAction(e->{
			try
			{			
				Register reg=new Register();
			    reg.start(new Stage());
			    primaryStage.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}		
		});
        
        //Add HBox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setCenter(gridPane);  
         
        //Adding BorderPane to the scene and loading CSS
     Scene scene = new Scene(bp,400,400);
     scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
     primaryStage.setScene(scene);
     primaryStage.show();
    }//start method end

    public static void main(String[] args) 
		{
        launch(args);
        }
      
}