import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
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
import javafx.stage.FileChooser;
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
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;

  public class TeacherPage extends Application {

	
	  TextField  t1=new TextField();//for id
		TextField  t2=new TextField();//for name
		TextField  t3=new TextField();// for salary
		DatePicker t4=new DatePicker();//for dob
		TextArea  t5=new TextArea();//for address
		TextField  t6=new TextField();//for subject

		Label photoLabel =new Label();
		ImageView selectView=null;
		ImageView selectView1;//for default picture
		File file=null;


        //getting connection from mysql class
	    MysqlCon mc=new MysqlCon();
	    Connection con=null;
	    TableView<Teacher_Bean> table = new TableView();
		Integer index;
		
		TableColumn<Teacher_Bean,String> c1= new TableColumn<>("Teacher ID");
        TableColumn<Teacher_Bean,String> c2 = new TableColumn<>("Teacher Name");
        TableColumn<Teacher_Bean,Integer> c3 = new TableColumn<>("Teacher Salary");
		TableColumn<Teacher_Bean,String> c4 = new TableColumn<>("Date of Birth");
		TableColumn<Teacher_Bean,String> c5 = new TableColumn<>("Address");
		TableColumn<Teacher_Bean,String> c6 = new TableColumn<>("Subject");
		TableColumn<Teacher_Bean,ImageView> c7 = new TableColumn<>("Photo");

		Teacher_Bean teacher=null;
		ObservableList<Teacher_Bean> teacherData=FXCollections.observableArrayList();
       
				  public void getAllTeacherData()
	                {
					     try {
                                   con=mc.getMyCon();
                                   Statement st = con.createStatement();
                                   String recordQuery = ("select * from teacherdata");
                                     ResultSet rs = st.executeQuery(recordQuery);
                                    while (rs.next()) 
										{
										         InputStream is = rs.getBinaryStream("tphoto");
												 Image image =new Image(is);
												  ImageView imageView = new ImageView(image);
				                                  imageView.setImage(image);
												  imageView.setFitHeight(30);
				                                  imageView.setFitWidth(20);   
                                             teacherData.add(new Teacher_Bean(rs.getInt("tid"),rs.getString("tname"),rs.getInt("tsalary"),rs.getString("tdob"),rs.getString("tadd"),rs.getString("tsub"),imageView));
											  table.setItems(teacherData);
											 
                                         }     
										  c1.setCellValueFactory(new PropertyValueFactory<>("id"));
		                                  c2.setCellValueFactory(new PropertyValueFactory<>("name"));
		                                  c3.setCellValueFactory(new PropertyValueFactory<>("salary"));
		                                  c4.setCellValueFactory(new PropertyValueFactory<>("dob"));
		                                  c5.setCellValueFactory(new PropertyValueFactory<>("address"));
		                                  c6.setCellValueFactory(new PropertyValueFactory<>("subject"));
										  c7.setCellValueFactory(new PropertyValueFactory<>("photo"));
										   
                              }
                          catch (Exception ex) 
			              {
                             ex.printStackTrace();
                          }
	                 }
     
	    public void getItem()
	      {
			                 con=mc.getMyCon();
			                index=table.getSelectionModel().getSelectedIndex();
							if(index<=-1)
							 {
								return;
							 }                     
							t1.setText(String.valueOf(c1.getCellData(index)));
					        t2.setText(c2.getCellData(index).toString());
					        t3.setText(String.valueOf(c3.getCellData(index)));  
							
							 LocalDate date=LocalDate.parse(c4.getCellData(index));                  
                             t4.setValue(date);//DatePicker Constuctor holds only Local Date Value 				
					        t5.setText(c5.getCellData(index).toString());
					        t6.setText(c6.getCellData(index).toString());	
							try
							{
								String query="select * from teacherdata where tid=?";
							 PreparedStatement pst=con.prepareStatement(query);
							 pst.setString(1,String.valueOf(c1.getCellData(index)));

			                 ResultSet rs1=pst.executeQuery();
							 while(rs1.next())
								{
								InputStream is = rs1.getBinaryStream("tphoto");
                                  	Image image1 = new Image(is);
							       
				              selectView = new ImageView(image1);
				              selectView.setImage(image1);
				               selectView.setFitHeight(80);
				               selectView.setFitWidth(90);     
							    photoLabel.setGraphic(selectView);		
								}
							}
							catch (Exception ee)
							{
								ee.printStackTrace();
							}
							
		  }
					 				 
    public void start(Stage stage) throws Exception 
		{
		//setting value in textfeilds which select by table
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() 
			{ 
            public void handle(MouseEvent e) 
				{   
                     getItem();
                } 
             }; 
   table.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
  
       Text text = new Text("Teacher Information"); 
       text.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,25));  
      //Setting the position of the text 
       text.setX(100); 
       text.setY(90);  
	   text.setUnderline(true);
       text.setFill(Color.BLUE); 
       text.setStrokeWidth(2); 
         
        Font f=new Font("Times new Roman",20);
        Label l1=new Label("Teacher ID"); l1.setFont(f);
        Label l2=new Label("Teacher Name"); l2.setFont(f);
		Label l3=new Label("Teacher Salary"); l3.setFont(f);
        Label l4=new Label("Date of Birth"); l4.setFont(f);
		Label l5=new Label("Address"); l5.setFont(f);
		Label l6=new Label("Subject"); l6.setFont(f);
       

         t1.setPrefColumnCount(15);
         t2.setPrefColumnCount(15);
		 t3.setPrefColumnCount(15);
		  t5.setPrefHeight(60);  //sets height of the TextArea  
          t5.setPrefWidth(100); //sets width of the TextArea 
		 t6.setPrefColumnCount(15);
         
		 t1.setPromptText("Enter Teacher Id No.");
		 t2.setPromptText("Enter Teacher Name.");
		 t3.setPromptText("Enter Teacher Salary.");
		 t4.setPromptText("Select D.O.B.");
		 t5.setPromptText("Enter Teacher Address.");
		 t6.setPromptText("Enter Teacher Subject .");
         
   
		Button clear=new Button("New/Clear");
        Button insert=new Button("Save/Insert");
		Button update=new Button("Update/Edit");
        Button delete=new Button("Delete/Remove");
		Button selectPhoto=new Button("Select Photo");

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
	   
	   GridPane gridPane=new GridPane();
	   gridPane.addRow(1,l1,t1,clear);
	   gridPane.addRow(2,l2,t2,insert);
	   gridPane.addRow(3,l3,t3,update);
	   gridPane.addRow(4,l4,t4,delete);
	    gridPane.addRow(5,l5,t5);
		 gridPane.addRow(6,l6,t6);
		 gridPane.setPadding(new Insets(10,20,20,40));
		 gridPane.setHgap(50);
		 gridPane.setVgap(15);

		 GridPane gridPane1=new GridPane();
       gridPane1.addRow(1,selectPhoto);
	   gridPane1.addRow(2,photoLabel);
	   gridPane1.addRow(3,clear);
	   gridPane1.addRow(4,insert);
	   gridPane1.addRow(5,update);
	   gridPane1.addRow(6,delete);
	   gridPane1.setAlignment(Pos.CENTER);
	   gridPane1.setHgap(50);
	   gridPane1.setVgap(15);
	   gridPane1.setPadding(new Insets(10,20,20,60));

	   HBox hb1=new HBox();
	   hb1.getChildren().addAll(gridPane,gridPane1);


		Label l7=new Label("Search Teacher by Name :          "); l7.setFont(f);
		TextField  searchName=new TextField();
		HBox hb7= new  HBox(); hb7.setAlignment(Pos.CENTER);
		hb7.getChildren().addAll(l7,searchName);hb7.setPadding(new Insets(30,5,10,5));
	
	   //tableview work 
        table.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7);
		//table.setEditable(true);
        getAllTeacherData();

        VBox vb=new VBox();vb.setAlignment(Pos.CENTER);
		VBox vb1=new VBox();
		VBox vb2=new VBox();
		VBox vb3=new VBox();

		vb1.setPadding(new Insets(20,30,30,20));
		vb3.setPadding(new Insets(20,20,20,20));
		vb2.setStyle("-fx-background-color:BEIGE;");
	   vb.getChildren().addAll(hb1,hb7);
		vb1.getChildren().addAll(text);
		vb3.getChildren().addAll(table);
		vb2.getChildren().addAll(vb1,vb,vb3);
    
        Scene sc=new Scene(vb2,650,650);
        stage.setScene(sc);
		stage.setTitle("Teacher Page");
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

		//Insert button working
      insert.setOnAction(e->{
		try
		{
			         con=mc.getMyCon();

					 if ((t1.getText().equals("")) || (t2.getText().equals("")) || (t3.getText().equals("")) ||
					(t6.getText().equals("")) || (t5.getText().equals("")) || (t4.getValue() == null)) {
				       Alert al1 = new Alert(AlertType.INFORMATION);
				    al1.setTitle("Alert");
				    al1.setHeaderText("Warning:");
				    al1.setContentText("it is must to fill all column");
				    al1.showAndWait();
			     }
			      else{
			      String chkId = "select * from teacherdata where tid=?";
					PreparedStatement ps = con.prepareStatement(chkId);
					ps.setString(1, t1.getText());
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
					String  insQue= "insert into teacherdata values(?,?,?,?,?,?,?)";
					PreparedStatement ps1 = con.prepareStatement(insQue);
					ps1.setInt(1, Integer.parseInt(t1.getText()));
					ps1.setString(2,t2.getText());
					ps1.setInt(3, Integer.parseInt(t3.getText()));
					ps1.setString(4,String.valueOf(t4.getValue()));
					ps1.setString(5,t5.getText());
					ps1.setString(6,t6.getText());
					if (file == null) {
							Alert al4 = new Alert(AlertType.INFORMATION);
							al4.setTitle("Alert");
							al4.setHeaderText("Message:");
							al4.setContentText("Please select the photo!!");
							al4.showAndWait();
						} else {
							FileInputStream img = new FileInputStream(file);
							ps1.setBlob(7, img);
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
					  table.getItems().clear();
					  getAllTeacherData();
		}//second else
				  }//first else
		}
		catch (Exception sql)
		{
			sql.printStackTrace();
		}
      });

	   clear.setOnAction(e->{
	
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setValue(null);
					t5.setText("");
					t6.setText("");		
					photoLabel.setGraphic(selectView1);
      });

	  update.setOnAction(e->{
		try
		{
			        con=mc.getMyCon();
					String  upQue= " update teacherdata set tname=?,tsalary=?,tdob=?,tadd=?,tsub=?,tphoto=? where tid=?";
					PreparedStatement ps1 = con.prepareStatement(upQue);
					ps1.setString(1, t2.getText());
					ps1.setInt(2,Integer.parseInt(t3.getText()));
					ps1.setString(3, String.valueOf(t4.getValue()));
					ps1.setString(4,t5.getText());
					ps1.setString(5,t6.getText());
					if (file == null) {
							Alert al4 = new Alert(AlertType.INFORMATION);
							al4.setTitle("Alert");
							al4.setHeaderText("Message:");
							al4.setContentText("Please select the photo!!");
							al4.showAndWait();
						} else {
							FileInputStream img = new FileInputStream(file);
							ps1.setBlob(6, img);
						}
					ps1.setInt(7,Integer.parseInt(t1.getText()));		
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
					  getAllTeacherData();
		}
		catch (Exception sql)
		{
			sql.printStackTrace();
		}
      });

	  delete.setOnAction(e->{
		try
		{
			        con=mc.getMyCon();
					String  delQue= " delete from teacherdata where tid=?";
					PreparedStatement ps2 = con.prepareStatement(delQue);
					ps2.setInt(1,Integer.parseInt(t1.getText()));
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
					  table.getItems().clear();
					  getAllTeacherData();
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
				     ObservableList<Teacher_Bean> searchData=FXCollections.observableArrayList();
                     String val=searchName.getText().toString();
       
					     try {
                                   con=mc.getMyCon();
                                   Statement st = con.createStatement();
                                   String query ="select * from teacherdata where tname like '%"+val+"%'";
                                    ResultSet rs = st.executeQuery(query);
                                    while (rs.next()) 
										{
										     InputStream is = rs.getBinaryStream("tphoto");
												 Image image =new Image(is);
												  ImageView view = new ImageView(image);
				                                  view.setImage(image);
												  view.setFitHeight(30);
				                                  view.setFitWidth(20);  
                                             searchData.add(new Teacher_Bean(rs.getInt("tid"),rs.getString("tname"),rs.getInt("tsalary"),rs.getString("tdob"),rs.getString("tadd"),rs.getString("tsub"),view));
											  table.setItems(searchData);									 
                                         }     
										  c1.setCellValueFactory(new PropertyValueFactory<>("id"));
		                                  c2.setCellValueFactory(new PropertyValueFactory<>("name"));
		                                  c3.setCellValueFactory(new PropertyValueFactory<>("salary"));
		                                  c4.setCellValueFactory(new PropertyValueFactory<>("dob"));
		                                  c5.setCellValueFactory(new PropertyValueFactory<>("address"));
		                                  c6.setCellValueFactory(new PropertyValueFactory<>("subject"));
										  c7.setCellValueFactory(new PropertyValueFactory<>("photo"));
                              }
                          catch (Exception ex) 
			              {
                             ex.printStackTrace();
                          }
                }
        });
		
   } //close Start method

     public static void main(String[] args) {
        launch(args);
    }
}//close class