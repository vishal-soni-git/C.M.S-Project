import java.util.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.text.FontPosture;  
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;  
import java.io.*;
import java.lang.*;
import java.sql.*;
import java.text.SimpleDateFormat;

  public class StudentForm extends Application {

	  MysqlCon mc=new MysqlCon();
	  Connection con=null;


	    TextField  tFirst=new TextField();//name
		TextField  tMiddle=new TextField();//middle name
		TextField  tLast=new TextField();//last name
		TextField  tEmail=new TextField();//email
		TextField  tPhone=new TextField();//phone
		ChoiceBox<String> cbCourse;//course
		DatePicker datePicker=new DatePicker();	//dob
		RadioButton male,female,other;//gender
		ToggleGroup tg;
		TextArea  tAddress1=new TextArea();//address
		TextArea  tAddress2=new TextArea();//address
		TextField  city=new TextField();
		TextField  state=new TextField();
		TextField  pin=new TextField();
		TextField  country=new TextField();

		
//clear all fields logic
	public void clearAllText()
	  {
		                    tFirst.setText("");
					        tMiddle.setText("");
							tLast.setText("");
						    tEmail.setText("");
							tPhone.setText("");
							cbCourse.setValue(null);
							tg.selectToggle(null);
					        tAddress1.setText("");
							tAddress1.setText("");                 			
					        city.setText("");
					        state.setText("");
							pin.setText("");
							country.setText("");
						  
	  }
					 				 
    public void start(Stage stage) throws Exception 
		{
        stage.setTitle("Student Form");
  
         Text text = new Text("Student Form for Apply"); 
       text.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,25));  
      //Setting the position of the text 
       text.setX(100); 
       text.setY(90);  
	   text.setUnderline(true);
       text.setFill(Color.BLUE); 
       text.setStrokeWidth(2); 


        Font f=new Font("Times new Roman",20);
		Label l1=new Label("Name"); l1.setFont(f);
		Label l2=new Label("Email"); l2.setFont(f);
		Label l3=new Label("Mobile No."); l3.setFont(f);
		Label l4=new Label("Course"); l4.setFont(f);	
		Label l5=new Label("D.O.B"); l5.setFont(f);
		Label l6=new Label("Gender");l6.setFont(f);
		Label l7=new Label("Address"); l7.setFont(f);
		

		 cbCourse=new ChoiceBox<String>();
			cbCourse.getItems().addAll("MCA(INTEGRATED)","MCA","MBA(INTEGRATED)","MBA");
				male = new RadioButton("Male\t");
		        female = new RadioButton("Female\t");
		        other = new RadioButton("Other\t");
	        	 tg = new ToggleGroup();
		        male.setToggleGroup(tg);
		        female.setToggleGroup(tg);
		        other.setToggleGroup(tg);
		        HBox hbox = new HBox();
		        hbox.getChildren().addAll(male,female,other);
    

		Button submit=new Button("Submit");
		submit.setPrefSize(100, 20);
		submit.setStyle("-fx-background-color:linear-gradient(White,skyblue );");

	
		  tFirst.setPrefColumnCount(10);
         tMiddle.setPrefColumnCount(10);
		 tLast.setPrefColumnCount(10);
		 tEmail.setPrefColumnCount(10);
		  tPhone.setPrefColumnCount(10);
		   tAddress1.setPrefHeight(40);  //sets height of the TextArea  
          tAddress1.setPrefWidth(100); //sets width of the TextArea 
		   tAddress2.setPrefHeight(40);  //sets height of the TextArea  
          tAddress2.setPrefWidth(100); //sets width of the TextArea 
		  pin.setPrefColumnCount(10);
		  city.setPrefColumnCount(10);
		  state.setPrefColumnCount(10);
		  country.setPrefColumnCount(10);
   

         //setting default Text on TextFields
		  tFirst.setPromptText("First Name");
		 tMiddle.setPromptText("Middle Name (Optional*)");
		 tLast.setPromptText("Last Name (Optional*)");
		 tEmail.setPromptText("Enter Email");
		 tPhone.setPromptText("Enter Mobile NO.");
		  cbCourse.setValue("Select Course.");
	      datePicker.setPromptText("Select D.O.B.");
		  //gender
		 tAddress1.setPromptText("Lane 1");
		  tAddress2.setPromptText("Lane 2");
		 pin.setPromptText("Area Pin Code");
		 city.setPromptText("City");
		 state.setPromptText("State");
		 country.setPromptText("Country");

        HBox hb1=new HBox();
        HBox hb2=new HBox();

		 VBox vb1=new VBox();
		 VBox vb2=new VBox();
		
       GridPane gp1=new GridPane();
	   gp1.addRow(0,l1);
	   gp1.addRow(1,tFirst,tMiddle,tLast);
	   gp1.addRow(2,l2,l3,l4);
	   gp1.addRow(3,tEmail,tPhone,cbCourse);
	   gp1.addRow(4,l5,l6);
	   gp1.addRow(5,datePicker,hbox);
	   gp1.addRow(6,l7);
	   gp1.addRow(7,tAddress1,tAddress2,pin);
	   gp1.addRow(8,city,state,country);


	    gp1.setHgap(30);
		 gp1.setVgap(15);
		

	   hb1.getChildren().addAll(text);
	   hb1.setPadding(new Insets(10,20,20,20));
	   hb2.getChildren().addAll(gp1);
	   hb2.setAlignment(Pos.CENTER);
	   vb2.getChildren().add(submit);vb2.setStyle("-fx-background-color:Beige;");
	   vb2.setAlignment(Pos.CENTER);
	    vb2.setPadding(new Insets(40,20,20,20));
 
         vb1.getChildren().addAll(hb1,hb2,vb2);vb1.setStyle("-fx-background-color:Beige;");
        Scene sc=new Scene(vb1,620,550);
        stage.setScene(sc);
        stage.show();  

//Submi Data With Submit button		
		
		submit.setOnAction(e->{
		try
		{
			         con=mc.getMyCon();

					 if ((tFirst.getText().equals("")) || (tEmail.getText().equals("")) || (tPhone.getText().equals("")) ||
					     (cbCourse.getValue().equals("")) || (datePicker.getValue() == null)||(tAddress1.getText().equals(""))||
						 (tAddress2.getText().equals(""))||(city.getText().equals(""))||(state.getText().equals(""))||
						 (pin.getText().equals(""))||(country.getText().equals(""))) 
						 {
				       Alert al1 = new Alert(AlertType.INFORMATION);
				    al1.setTitle("Alert");
				    al1.setHeaderText("Warning:");
				    al1.setContentText("it is must to fill all column");
				    al1.showAndWait();
			           }
			      else{
			      String chkId = "select * from studentform where Email=?";
					PreparedStatement ps = con.prepareStatement(chkId);
					ps.setString(1, tEmail.getText());
					ResultSet rs1 = ps.executeQuery();

					if (rs1.next())
					{
						Alert alert2 = new Alert(AlertType.INFORMATION);
						alert2.setTitle("Alert");
						alert2.setHeaderText("Warning:");
						alert2.setContentText("Your Data already submit with this email :-"+tEmail);
						alert2.showAndWait();
					} // if end
			        else
			       {
					String  insQue= "insert into studentform values(?,?,?,?,?,?,?)";
					PreparedStatement ps1 = con.prepareStatement(insQue);

					String name=tFirst.getText()+" "+tMiddle.getText()+""+tLast.getText();
					ps1.setString(1,name);		
					ps1.setString(2,tEmail.getText());
					try
					{
						ps1.setLong(3,Long.parseLong(tPhone.getText()));
					}
					catch (NumberFormatException nfe)
					{
						nfe.printStackTrace();
						Alert alertNfe = new Alert(AlertType.INFORMATION);
						alertNfe.setTitle("Alert");
						alertNfe.setHeaderText("Warning:");
						alertNfe.setContentText("please Enter Only 'Number like 0 to 9' in Mobile Number Feild");
						alertNfe.showAndWait();
					}
					ps1.setString(4,cbCourse.getValue());
					ps1.setString(5,String.valueOf(datePicker.getValue()));

					if (male.isSelected()) {
							ps1.setString(6, "Male");}
							
						else if (female.isSelected()) {
							ps1.setString(6, "Female");}

							else if (other.isSelected()) {
							ps1.setString(6, "Other");} 

						else {
							Alert al3 = new Alert(AlertType.INFORMATION);
							al3.setTitle("Alert");
							al3.setHeaderText("Warning:");
							al3.setContentText("it is must to fill all column");
							al3.showAndWait();
						}

					String fullAddress=tAddress1.getText()+", "+tAddress2.getText()+", "+city.getText()+", "+state.getText()+", "+
					country.getText()+"- "+pin.getText();
					ps1.setString(7,fullAddress);
					
					int rs=ps1.executeUpdate();
					Alert al1 = new Alert(AlertType.INFORMATION);
					if(rs!=0)
			          {		          
				          al1.setTitle("Alert");
				          al1.setHeaderText("Success:");
				         al1.setContentText("your data is inserted");
						 al1.showAndWait();
						 clearAllText();
			           }
					   else
			         {
						   al1.setTitle("Alert");
				          al1.setHeaderText("Fail:");
				         al1.setContentText("your data is not inserted");
						 al1.showAndWait();	
			          }
		          }//second else
	    }//first else
				     
		}
		catch (Exception sql)
		{
			sql.printStackTrace();
		}
      });

   } //close Start method

     public static void main(String[] args) 
		 {
        launch(args);
    }
}//close class 
