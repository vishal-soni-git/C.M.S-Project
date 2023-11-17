import java.util.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.image.Image;  
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;  
import javafx.event.EventHandler;  
import javafx.scene.input.KeyEvent;
import java.io.*;
import java.lang.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;  
import javafx.embed.swing.SwingFXUtils;

  public class StudentPage extends Application {

	  MysqlCon mc=new MysqlCon();
	  Connection con=null;

	    File file=null;//for photo
		ImageView selectView=null;//for selected picture
		ImageView selectView1;//for default picture

	    TextField  t1=new TextField();//id
		TextField  t2=new TextField();//rollNo
		TextField  t3=new TextField();//name
		TextField  t4=new TextField();//Fname
		ChoiceBox<String> cb;//course
		TextField  t6=new TextField();//session
		TextField  t7=new TextField();//adhar
		TextField  t8=new TextField();//phone
		DatePicker t9=new DatePicker();	//dob
		TextArea  t10=new TextArea();//address	
		TextField  t11=new TextField();//email
		Label photoLabel=new Label();//photo

		
		 TableView<Student_Bean> table = new TableView();
		Integer index;
		//table.setEditable(true);
		TableColumn<Student_Bean,String> c1= new TableColumn<>("ID");
        TableColumn<Student_Bean,String> c2 = new TableColumn<>("Roll No.");
        TableColumn<Student_Bean,String> c3 = new TableColumn<>("Name");
		TableColumn<Student_Bean,String> c4 = new TableColumn<>("Father Name");
		TableColumn<Student_Bean,String> c5 = new TableColumn<>("Course");
		TableColumn<Student_Bean,String> c6 = new TableColumn<>("Session");
		TableColumn<Student_Bean,String> c7 = new TableColumn<>("Adhar");
		TableColumn<Student_Bean,String> c8 = new TableColumn<>("Phone");
		TableColumn<Student_Bean,String> c9 = new TableColumn<>("D.O.B");
		TableColumn<Student_Bean,String> c10 = new TableColumn<>("Address");
		TableColumn<Student_Bean,String> c11 = new TableColumn<>("Email");
		TableColumn<Student_Bean,ImageView> c12 = new TableColumn<>("Photo");

		Student_Bean student =null;
		ObservableList<Student_Bean> studentData=FXCollections.observableArrayList();
       
	   //Adding Data in table from database 
				  public void getAllStudentData()
	                {
					     try {
                                   con=mc.getMyCon();
                                   Statement st = con.createStatement();
                                   String recordQuery = ("select * from studentdata");
                                    ResultSet rs = st.executeQuery(recordQuery);
									          
                                    while (rs.next()) 
										{
										 InputStream is = rs.getBinaryStream("sphoto");
												 Image image =new Image(is);
												  ImageView view = new ImageView(image);
				                                  view.setImage(image);
												  view.setFitHeight(40);
				                                  view.setFitWidth(30);   
                                                 
                                               studentData.add(new Student_Bean(rs.getString("sid"),rs.getLong("srollno"),rs.getString("sname"),
												 rs.getString("sfathername"),rs.getString("scourse"),rs.getString("ssession"),rs.getLong("sadhar"),
												 rs.getLong("sphone"),rs.getString("sdob"),rs.getString("saddress"),rs.getString("semail"),view));

											  table.setItems(studentData);
                                         }     
										  c1.setCellValueFactory(new PropertyValueFactory<>("id"));
		                                  c2.setCellValueFactory(new PropertyValueFactory<>("rollNo"));
		                                  c3.setCellValueFactory(new PropertyValueFactory<>("name"));
		                                  c4.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
		                                  c5.setCellValueFactory(new PropertyValueFactory<>("course"));
		                                  c6.setCellValueFactory(new PropertyValueFactory<>("session"));
										  c7.setCellValueFactory(new PropertyValueFactory<>("adhar"));
										  c8.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
										  c9.setCellValueFactory(new PropertyValueFactory<>("dob"));
										  c10.setCellValueFactory(new PropertyValueFactory<>("address"));
										  c11.setCellValueFactory(new PropertyValueFactory<>("email"));
										  c12.setCellValueFactory(new PropertyValueFactory<>("photo"));							   
                              }
                          catch (Exception ex) 
			              {
                             ex.printStackTrace();
                          }
	                 }
 //getting data on all Feilds
					   public void getItem()
	                   {
						   try
                            {
							   con=mc.getMyCon();
			                index=table.getSelectionModel().getSelectedIndex();
							if(index<=-1)
							 {
								return;
							 }        
							 String s1=String.valueOf(c1.getCellData(index));
							 String s2=String.valueOf(c2.getCellData(index));

							 String query="select * from studentdata where sid=? and srollno=?";
							 PreparedStatement pst=con.prepareStatement(query);
							 pst.setString(1,s1);
							 pst.setString(2,s2);
			                 ResultSet rs1=pst.executeQuery();
							 while(rs1.next())
								{
							t1.setText(rs1.getString("sid"));
					        t2.setText(String.valueOf(rs1.getLong("srollno")));
							t3.setText(rs1.getString("sname"));
						    t4.setText( rs1.getString("sfathername"));
							cb.setValue(rs1.getString("scourse"));
							t6.setText(rs1.getString("ssession"));
					        t7.setText(String.valueOf(rs1.getLong("sadhar")));
							t8.setText(String.valueOf(rs1.getLong("sphone")));
							
							 LocalDate date=LocalDate.parse(rs1.getString("sdob"));                  
                             t9.setValue(date);//DatePicker Constuctor holds only Local Date Value 				
					        t10.setText(rs1.getString("saddress"));
					        t11.setText(rs1.getString("semail"));
                            
                                   InputStream is = rs1.getBinaryStream("sphoto");
                                  	Image image1 = new Image(is);
							       
				              selectView = new ImageView(image1);
				              selectView.setImage(image1);
				               selectView.setFitHeight(80);
				               selectView.setFitWidth(90);     
							    photoLabel.setGraphic(selectView);			
                            }
							}
                            catch (Exception eu)
                            {
								eu.printStackTrace();
                            }
		              }
//clear all fields logic
	public void clearAllText()
	  {
		                    t1.setText("");
					        t2.setText("");
							t3.setText("");
						    t4.setText("");
							cb.setValue(null);
							t6.setText("");
					        t7.setText("");
							t8.setText("");                 
                             t9.setValue(null);				
					        t10.setText("");
					        t11.setText("");       
						  photoLabel.setGraphic(selectView1);
	  }

					 				 
    public void start(Stage stage) throws Exception 
		{
		
		//tableview work 
        table.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
		getAllStudentData();
		//table.setEditable(true);

		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() 
			{ 
            public void handle(MouseEvent e) 
				{   
                     getItem();
                } 
             }; 
        table.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

        stage.setTitle("Student Page");
  
         Text text = new Text("Student Information"); 
       text.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,25));  
      //Setting the position of the text 
       text.setX(100); 
       text.setY(90);  
	   text.setUnderline(true);
       text.setFill(Color.BLUE); 
       text.setStrokeWidth(2); 


        Font f=new Font("Times new Roman",20);
        Label l1=new Label("ID"); l1.setFont(f);
        Label l2=new Label("RollNo."); l2.setFont(f);
		Label l3=new Label("Name"); l3.setFont(f);
        Label l4=new Label("Father Name"); l4.setFont(f);
		Label l5=new Label("Course"); l5.setFont(f);
		Label l6=new Label("Session"); l6.setFont(f);
		Label l7=new Label("Adhar"); l7.setFont(f);
		Label l8=new Label("Mobile"); l8.setFont(f);
		Label l9=new Label("D.O.B"); l9.setFont(f);
		Label l10=new Label("Address"); l10.setFont(f);
		Label l11=new Label("Email"); l11.setFont(f);

		 cb=new ChoiceBox<String>();
			cb.getItems().addAll("MCA(INTEGRATED)","MCA","MBA(INTEGRATED)","MBA");
        
        //For default Image 
		Image selectImage1 = new Image(new FileInputStream(new File("Default_Image.png")));
				 selectView1 = new ImageView();
				selectView1.setImage(selectImage1);
				selectView1.setFitHeight(80);
				selectView1.setFitWidth(90); 

		//set photo on GUI
        photoLabel.setMinWidth(90);
       photoLabel.setMinHeight(80);
	   photoLabel.setGraphic(selectView1);

		Button clear=new Button("New/Clear");
        Button insert=new Button("Save/Insert");
		Button update=new Button("Update/Edit");
        Button delete=new Button("Delete/Remove");
		Button selectPhoto=new Button("Select Photo");

		Label searchLabel=new Label("Search Student by Name :          "); searchLabel.setFont(f);
		TextField  searchName=new TextField();
		HBox hb3= new  HBox(); hb3.setAlignment(Pos.CENTER);
		hb3.getChildren().addAll(searchLabel,searchName);hb3.setPadding(new Insets(20,5,10,5));

		  t1.setPrefColumnCount(10);
         t2.setPrefColumnCount(10);
		 t3.setPrefColumnCount(10);
		 t4.setPrefColumnCount(10);
		  t6.setPrefColumnCount(10);
         t7.setPrefColumnCount(10);
		 t8.setPrefColumnCount(10);
		 t10.setPrefHeight(70);  //sets height of the TextArea  
          t10.setPrefWidth(100); //sets width of the TextArea 
		 t11.setPrefColumnCount(10);

         //setting default Text on TextFields
		  t1.setPromptText("Enter  Id");
		 t2.setPromptText("Enter RollNo.");
		 t3.setPromptText("Enter Student Name.");
		 t4.setPromptText("Enter Father Name.");
		 cb.setValue("Select Course.");
		 t6.setPromptText("Enter Session .");
		  t7.setPromptText("Enter Adhar NO. .");
		 t8.setPromptText("Enter Phone NO..");
		 t9.setPromptText("Select D.O.B.");
		 t10.setPromptText("Enter Address.");
		 t11.setPromptText("Enter Email");

        HBox hb1=new HBox();
        HBox hb2=new HBox();

		 VBox vb1=new VBox();   
		VBox vb2=new VBox();
		
       GridPane gp1=new GridPane();
	   GridPane gp2=new GridPane();
	   gp1.addRow(0,l1,t1,l2,t2);
	   gp1.addRow(1,l3,t3);
	   gp1.addRow(2,l4,t4);
	   gp1.addRow(3,l5,cb,l6,t6);
	   gp1.addRow(4,l7,t7,l8,t8);
	   gp1.addRow(5,l9,t9,l11,t11);
	   gp1.addRow(6,l10,t10);

       gp2.addRow(0,selectPhoto);
	   gp2.addRow(1,photoLabel);
	   gp2.addRow(2,clear);
	   gp2.addRow(3,insert);
	   gp2.addRow(4,update);
	   gp2.addRow(5,delete);

	    gp1.setHgap(20);
		 gp1.setVgap(15);
		 gp2.setHgap(10);
		 gp2.setVgap(20);
		 gp2.setPadding(new Insets(10,20,20,80));

	   hb1.getChildren().addAll(text);
	   hb1.setPadding(new Insets(10,20,20,20));
	   hb2.getChildren().addAll(gp1,gp2);
	   hb2.setAlignment(Pos.CENTER);


        vb2.getChildren().addAll(table);
		vb2.setPadding(new Insets(20,20,20,20));
         vb1.getChildren().addAll(hb1,hb2,hb3,vb2);vb1.setStyle("-fx-background-color:Beige;");
        Scene sc=new Scene(vb1,800,650);
        stage.setScene(sc);
        stage.show();  

		// select photo button
		selectPhoto.setOnAction(e -> {
			 try {
				    FileChooser fc=new FileChooser();
					 file = fc.showOpenDialog(null);
					Image selectImage = new Image(new FileInputStream(file));
				 selectView = new ImageView();
				selectView.setImage(selectImage);
				selectView.setFitHeight(80);
				selectView.setFitWidth(90);     
				photoLabel.setGraphic(selectView);		

			} catch (Exception fio) 
				{
				fio.printStackTrace();	
			    }
		});   

//clear all fields using clear button
		clear.setOnAction(e -> {
			              clearAllText();			   
		});   

//Insert Data With insert button
		insert.setOnAction(e->{
		try
		{
			         con=mc.getMyCon();

					 if ((t1.getText().equals("")) || (t2.getText().equals("")) || (t3.getText().equals("")) ||
					(t4.getText().equals("")) || (cb.getValue().equals("")) || (t9.getValue() == null)||
						(t6.getText().equals(""))||(t7.getText().equals(""))||(t8.getText().equals(""))||
						 (t10.getText().equals(""))||(t11.getText().equals(""))) {
				       Alert al1 = new Alert(AlertType.INFORMATION);
				    al1.setTitle("Alert");
				    al1.setHeaderText("Warning:");
				    al1.setContentText("it is must to fill all column");
				    al1.showAndWait();
			     }
			      else{
			      String chkId = "select * from studentdata where sid=? and srollno=?";
					PreparedStatement ps = con.prepareStatement(chkId);
					ps.setString(1, t1.getText());
					ps.setString(2, t2.getText());
					ResultSet rs1 = ps.executeQuery();

					if (rs1.next())

					{// rs.getString(2).equals(t2.getText())
						Alert alert2 = new Alert(AlertType.INFORMATION);
						alert2.setTitle("Alert");
						alert2.setHeaderText("Warning:");
						alert2.setContentText("please choose another Teacher id this Id is already in use(;-;)");
						alert2.showAndWait();
						System.out.println("please choose another Teacher id");

					} // if end
			        else
			       {
					String  insQue= "insert into studentdata values(?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps1 = con.prepareStatement(insQue);
					ps1.setString(1, t1.getText());
					try
					{
							ps1.setLong(2,Long.parseLong(t2.getText()));
					}
					catch (NumberFormatException nfe)
					{
						nfe.printStackTrace();
						Alert alertNfe = new Alert(AlertType.INFORMATION);
						alertNfe.setTitle("Alert");
						alertNfe.setHeaderText("Warning:");
						alertNfe.setContentText("please Enter Only 'Number like 0 to 9' in Roll Number Field");
						alertNfe.showAndWait();
					}		
					ps1.setString(3,t3.getText());
					ps1.setString(4,t4.getText());
					ps1.setString(5,cb.getValue());
					ps1.setString(6,t6.getText());
					try
					{
						ps1.setLong(7, Long.parseLong(t7.getText()));
					}
					catch (NumberFormatException nfe)
					{
						nfe.printStackTrace();
						Alert alertNfe = new Alert(AlertType.INFORMATION);
						alertNfe.setTitle("Alert");
						alertNfe.setHeaderText("Warning:");
						alertNfe.setContentText("please Enter Only 'Number like 0 to 9' in Adhar Card Field");
						alertNfe.showAndWait();
					}

					try
					{
						ps1.setLong(8,Long.parseLong(t8.getText()));
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
					
					ps1.setString(9,String.valueOf(t9.getValue()));
					ps1.setString(10,t10.getText());
					ps1.setString(11,t11.getText());
					if (file == null) {
							Alert al4 = new Alert(AlertType.INFORMATION);
							al4.setTitle("Alert");
							al4.setHeaderText("Message:");
							al4.setContentText("Please select the photo!!");
							al4.showAndWait();
						} else {
							FileInputStream img = new FileInputStream(file);
							ps1.setBlob(12, img);
						}
					int rs=ps1.executeUpdate();
					Alert al1 = new Alert(AlertType.INFORMATION);
					if(rs!=0)
			          {		          
				          al1.setTitle("Alert");
				          al1.setHeaderText("Success:");
				         al1.setContentText("your data is inserted");
						 al1.showAndWait();
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
				      table.getItems().clear();
					  getAllStudentData();
		}
		catch (Exception sql)
		{
			sql.printStackTrace();
		}
      });

//For Update data with Update Button
update.setOnAction(e->{
		try
		{
			        con=mc.getMyCon();
					String  upQue= " update studentdata set sname=?,sfathername=?,scourse=?,ssession=?,sadhar=?"+
					",sphone=?,sdob=?,saddress=?,semail=?,sphoto=? where sid=? and srollno=?";

					PreparedStatement ps1 = con.prepareStatement(upQue);
					ps1.setString(1,t3.getText());
					ps1.setString(2,t4.getText());
					ps1.setString(3,cb.getValue());//t5
					ps1.setString(4,t6.getText());
					try
					{
						ps1.setLong(5,Long.parseLong(t7.getText()));
					}
					catch (NumberFormatException nfe)
					{
						nfe.printStackTrace();
						Alert alertNfe = new Alert(AlertType.INFORMATION);
						alertNfe.setTitle("Alert");
						alertNfe.setHeaderText("Warning:");
						alertNfe.setContentText("please Enter Only 'Number like 0 to 9' in Adhar Card Field");
						alertNfe.showAndWait();
					}
					try
					{
						ps1.setLong(6,Long.parseLong(t8.getText()));
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
					ps1.setString(7,String.valueOf(t9.getValue()));
					ps1.setString(8,t10.getText());
					ps1.setString(9,t11.getText());
					if (selectView == null) {
							Alert al4 = new Alert(AlertType.INFORMATION);
							al4.setTitle("Alert");
							al4.setHeaderText("Message:");
							al4.setContentText("Please select the photo!!");
							al4.showAndWait();
						} else {
							FileInputStream img = new FileInputStream(file);
							ps1.setBlob(10, img);
						}
						ps1.setString(11, t1.getText());
				     	ps1.setLong(12,Long.parseLong(t2.getText()));
					int rs=ps1.executeUpdate();
					Alert al2 = new Alert(AlertType.INFORMATION);
					if(rs!=0)
			          {		          
				          al2.setTitle("Alert");
				          al2.setHeaderText("Success:");
				         al2.setContentText("your data is Updated");
						 al2.showAndWait();
			           }
					   else
			         {
						   al2.setTitle("Alert");
				          al2.setHeaderText("Fail:");
				         al2.setContentText("your data is not Updated");
						 al2.showAndWait();
			          }
				      table.getItems().clear();
					  getAllStudentData();
		}
		catch (Exception sql)
		{
			sql.printStackTrace();
		}
      });

//For Delete Row Or Data with delete button
	  delete.setOnAction(e->{
		try
		{
			        con=mc.getMyCon();
					String  delQue= " delete from studentdata where sid=? and srollno=?";
					PreparedStatement ps2 = con.prepareStatement(delQue);
					ps2.setString(1,t1.getText());
					ps2.setString(2,t2.getText());
					int rs=ps2.executeUpdate();
					Alert al3 = new Alert(AlertType.INFORMATION);
					if(rs!=0)
			          {		          
				          al3.setTitle("Alert");
				          al3.setHeaderText("Success:");
				         al3.setContentText("your data is deleted");
						 al3.showAndWait();
			           }
					   else
			         {
						   al3.setTitle("Alert");
				          al3.setHeaderText("Fail:");
				         al3.setContentText("your data is not deleted");
						 al3.showAndWait();				
			          }
					  clearAllText();
					  table.getItems().clear();
					  getAllStudentData();
		}
		catch (Exception sql)
		{
			sql.printStackTrace();
		}
      });

	  // For Searching 
		searchName.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event)
				{
				     ObservableList<Student_Bean> searchData=FXCollections.observableArrayList();
                     String val=searchName.getText().toString();
       
					     try {
                                   con=mc.getMyCon();
                                   Statement st = con.createStatement();
                                   String query ="select * from studentdata where sname like '%"+val+"%'";
                                    ResultSet rs = st.executeQuery(query);
                                    while (rs.next()) 
										{
                                            InputStream is = rs.getBinaryStream("sphoto");
												 Image image =new Image(is);
												  ImageView view = new ImageView(image);
				                                  view.setImage(image);
												  view.setFitHeight(40);
				                                  view.setFitWidth(30);                        
                                               searchData.add(new Student_Bean(rs.getString("sid"),rs.getLong("srollno"),rs.getString("sname"),
												 rs.getString("sfathername"),rs.getString("scourse"),rs.getString("ssession"),rs.getLong("sadhar"),
												 rs.getLong("sphone"),rs.getString("sdob"),rs.getString("saddress"),rs.getString("semail"),view));

											     table.setItems(searchData);								 
                                         }     
										  c1.setCellValueFactory(new PropertyValueFactory<>("id"));
		                                  c2.setCellValueFactory(new PropertyValueFactory<>("rollNo"));
		                                  c3.setCellValueFactory(new PropertyValueFactory<>("name"));
		                                  c4.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
		                                  c5.setCellValueFactory(new PropertyValueFactory<>("course"));
		                                  c6.setCellValueFactory(new PropertyValueFactory<>("session"));
										  c7.setCellValueFactory(new PropertyValueFactory<>("adhar"));
										  c8.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
										  c9.setCellValueFactory(new PropertyValueFactory<>("dob"));
										  c10.setCellValueFactory(new PropertyValueFactory<>("address"));
										  c11.setCellValueFactory(new PropertyValueFactory<>("email"));
										  c12.setCellValueFactory(new PropertyValueFactory<>("photo"));								   
                              }
                          catch (Exception ex) 
			              {
                             ex.printStackTrace();
                          }
                }
        });
		
   } //close Start method

     public static void main(String[] args) 
		 {
        launch(args);
    }
}//close class 
