import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import java.text.*;

public class Register extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	Image image,selectImage;
	ImageView iv,selectView;
	File file = null;
	Label labelImage = new Label();
	String otpNum=null;
	boolean otpValid;

	public void start(Stage stage) throws Exception {

		stage.setTitle("Register");

		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(5);
		dropShadow.setOffsetY(5);
		Reflection r = new Reflection();
		r.setFraction(0.7f);

		Text text = new Text("Register New User");
		text.setFont(Font.font("TIMES_NEW_ROMAN", FontWeight.BOLD, FontPosture.REGULAR, 25));
		// Setting the position of the text
		text.setX(90);
		text.setY(90);
		text.setUnderline(true);
		text.setFill(Color.WHITE);
		text.setStrokeWidth(2);
		text.setEffect(dropShadow);
		// logo image
		final String lp = "Khandari-logo.png";
		File logoPath = new File(lp);
		image = new Image(new FileInputStream(logoPath));
		iv = new ImageView();
		iv.setImage(image);
		iv.setFitHeight(40);
		iv.setFitWidth(60);

		Font f = new Font("Times new Roman", 20);

		Label l1 = new Label("Full Name               ");	
		Label l2 = new Label("Email-Id                  ");
		Label l3 = new Label("Password                 ");
		Label l4 = new Label("Confirm Password   ");	
		Label l5 = new Label("Mobile No.              ");
		Label l6 = new Label("Gender                     ");
		Label l7 = new Label("Date Of birth           ");
		l1.setFont(f);
		l2.setFont(f);
		l3.setFont(f);
		l4.setFont(f);
		l5.setFont(f);
		l6.setFont(f);
		l7.setFont(f);

		TextField t1 = new TextField();// name
		TextField t2 = new TextField();// email
		PasswordField t3 = new PasswordField();// passsword
		PasswordField t4 = new PasswordField();// confirm password
		TextField t5 = new TextField();// mobile no.
		RadioButton r1 = new RadioButton("Male  ");
		RadioButton r2 = new RadioButton("Female  ");
		ToggleGroup tg1 = new ToggleGroup();
		r1.setToggleGroup(tg1);
		r2.setToggleGroup(tg1);

		DatePicker dp = new DatePicker();

		FileChooser fc = new FileChooser();//for choosing file

		t1.setPrefColumnCount(15);
		t2.setPrefColumnCount(15);
		t3.setPrefColumnCount(15);
		t4.setPrefColumnCount(15);
		t5.setPrefColumnCount(15);

		Button selectPhoto = new Button("Select Photo");
		Button register = new Button("Register");
		Button reset = new Button("Reset");
		Button login = new Button("Click here Back to Login");
		Button otp =new Button("Verify");
		selectPhoto.setEffect(dropShadow);
		register.setEffect(dropShadow);
		reset.setEffect(dropShadow);
		login.setEffect(dropShadow);
		otp.setEffect(dropShadow);
		otp.setPrefSize(70, 18);
		register.setPrefSize(100, 20);
		reset.setPrefSize(100, 20);
		login.setPrefSize(200, 20);

		

		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);

		gridPane.addRow(1, l1, t1);
		gridPane.addRow(2, l2, t2,otp);
		gridPane.addRow(3, l3, t3);
		gridPane.addRow(4, l4, t4);
		gridPane.addRow(5, l5, t5);
		gridPane.addRow(6, l6, r1, r2);
		gridPane.addRow(7, l7, dp);
		gridPane.addRow(8, selectPhoto);

		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(15);
		gridPane.setVgap(10);


      //panel for otp buttom
		GridPane otpPanel = new GridPane();
		otpPanel.setAlignment(Pos.CENTER);

		Label otpL1=new Label("Email        :");
		Label otpL2=new Label();
		Label otpL3=new Label("Enter OTP :");
		TextField otpText=new TextField();
		Button confirmBtn=new Button("Confirm");

		otpPanel.addRow(1,otpL1,otpL2);
		otpPanel.addRow(2,otpL3,otpText);
		otpPanel.addRow(3,confirmBtn);

		HBox hb1 = new HBox();
		hb1.setAlignment(Pos.CENTER);

		HBox hb2 = new HBox();
		hb2.setAlignment(Pos.CENTER);

		HBox hb3 = new HBox();// for text and logo
		hb1.setSpacing(40);
		hb3.setSpacing(200);

	

		hb1.setPadding(new Insets(30, 5, 5, 5));
		hb2.setPadding(new Insets(30, 5, 5, 5));

		hb1.getChildren().addAll(register, reset);
		hb2.getChildren().add(login);
		hb3.getChildren().addAll(text, iv);
		hb2.setEffect(r);

		VBox vb = new VBox();
		VBox vb1 = new VBox();
		VBox vb2 = new VBox();

		vb1.setPadding(new Insets(10, 20, 15, 20));
		vb.setPadding(new Insets(10, 5, 20, 5));

		vb.getChildren().addAll(gridPane, hb1, hb2);
		vb1.getChildren().addAll(hb3);
		vb2.getChildren().addAll(vb1, vb);

		// Add ID's to Nodes
		register.setId("register");
		reset.setId("reset");
		login.setId("login");
		selectPhoto.setId("selectPhoto");
		text.setId("text");
		gridPane.setId("gridPane");
		vb2.setId("vbox");
		vb1.setId("vbox1");


      //for otp panel
		Stage stage1=new Stage();

		//otp verification button
		otp.setOnAction(e -> {

			otpL2.setText(t2.getText());

			
			GenerateOtp o=new GenerateOtp();
		    otpNum= new String(o.otpGenerator(6));
			System.out.println(otpNum);

			String message = "Your OTP for C.M.S is : "+otpNum;
           String subject = "OTP Validation";
           String to =t2.getText();
           String from = "cinestar569@gmail.com";
		    Mail.sendEmail(message, subject, to, from);

			Scene sc1 = new Scene(otpPanel, 300, 400);
	    	stage1.setScene(sc1);
	    	stage1.show();    			
		});   

		//otp confirm button
         
		 confirmBtn.setOnAction(e -> {
            if(otpNum.equals(otpText.getText()))
			 {
				otpValid=true;
				otp.setText("Varified");
				stage1.close();
			 }
			 else
			 {
				otpValid=false;
                 Label mm=new Label("Please enter valid otp");
				 otpPanel.addRow(4,mm);
			 }
		});   
		


		// select photo button
		selectPhoto.setOnAction(e -> {
			 try {
					file = fc.showOpenDialog(null);
					selectImage = new Image(new FileInputStream(file));
				selectView = new ImageView();
				selectView.setImage(selectImage);
				selectView.setFitHeight(70);
				selectView.setFitWidth(50);     
				labelImage.setGraphic(selectView);		

			} catch (Exception fio) 
				{
				fio.printStackTrace();	
			    }
		});     	
				gridPane.addRow(8, labelImage);

       
		// register button working
		register.setOnAction(e -> {

			if ((t1.getText().equals("")) || (t2.getText().equals("")) || (t3.getText().equals("")) ||
					(t4.getText().equals("")) || (t5.getText().equals("")) || (dp.getValue() == null)) {
				Alert al1 = new Alert(AlertType.INFORMATION);
				al1.setTitle("Alert");
				al1.setHeaderText("Warning:");
				al1.setContentText("it is must to fill all column");

				al1.showAndWait();
			} else {
				/*
				 * when condition is done mean all filled are fill then
				 * statement would be execute
				 */

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/collegedb", "root",
							"2580");

					String chkEmail = "select * from logindata where email=?";
					PreparedStatement ps = con.prepareStatement(chkEmail);
					ps.setString(1, t2.getText());
					ResultSet rs = ps.executeQuery();

					if (rs.next())

					{// rs.getString(2).equals(t2.getText())
						Alert alert2 = new Alert(AlertType.INFORMATION);
						alert2.setTitle("Alert");
						alert2.setHeaderText("Warning:");
						alert2.setContentText("please choose another email id this email is already in use(;-;)");
						alert2.showAndWait();
						System.out.println("please choose another email id");

					} // if end
					else {


						String query = "insert into collegedb.logindata values(?,?,?,?,?,?,?)";
						PreparedStatement ps1 = con.prepareStatement(query);

						ps1.setString(1, t1.getText());
						ps1.setString(2, t2.getText());

						if (t3.getText().equals(t4.getText())) {
							ps1.setString(3, t4.getText());
						} // if end
						else {
							Alert alert1 = new Alert(AlertType.WARNING);
							alert1.setTitle("Alert");
							alert1.setHeaderText("Results:");
							alert1.setContentText(
									"Check the paasword,your password and confirm password both are not matched!!");
							alert1.showAndWait();

							System.out.println("Check the paasword both are not same");
						} // else end
						// mobile data
						ps1.setString(4, t5.getText());
						// gender data
						if (r1.isSelected()) {
							ps1.setString(5, "Male");
						} else if (r1.isSelected()) {
							ps1.setString(5, "Female");
						} else {
							Alert al3 = new Alert(AlertType.INFORMATION);
							al3.setTitle("Alert");
							al3.setHeaderText("Warning:");
							al3.setContentText("it is must to fill all column");
							al3.showAndWait();
						}

						// taking date
						String sd = String.valueOf(dp.getValue());
						ps1.setString(6, sd);

						// getting image

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

						// execute all 7 statement
						int result=0;
						if(otpValid==true)//otp verification
						{
						 result = ps1.executeUpdate();
						}
						else
						{
							Alert otpAlert = new Alert(AlertType.INFORMATION);
							otpAlert.setTitle("Message");
							otpAlert.setHeaderText("Message:");
							otpAlert.setContentText("Please Verify email first");
							otpAlert.showAndWait();
							System.out.println("Please Verify email first");
						}

						if (result == 0) {
							Alert al5 = new Alert(AlertType.INFORMATION);
							al5.setTitle("Message");
							al5.setHeaderText("Message:");
							al5.setContentText("Your data is not send due to some problem");
							al5.showAndWait();
							System.out.println("no record inserted");
						} else {
							Alert al2 = new Alert(AlertType.INFORMATION);
							al2.setTitle("Message");
							al2.setHeaderText("Message:");
							al2.setContentText("Your data is submitted");
							al2.showAndWait();
							System.out.println(result + " record inserted");
						} // result else end
						ps1.close();
						ps.close();
						rs.close();
						con.close();

					} // execute all statement else block end

				} // try block of all statement is end
				catch (Exception ex) {
					System.out.println(ex);
				}
			} // main else block is end
		});
 
 //reset button working
		reset.setOnAction(e -> {
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			tg1.selectToggle(null);
			dp.setValue(null);
			labelImage.setGraphic(null);
		});

//login button working
		login.setOnAction(e -> {
			try {
				Login log = new Login();
				log.start(new Stage());
				// Platform.exit();
				stage.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		Scene sc = new Scene(vb2, 550, 580);
		sc.getStylesheets().add(getClass().getClassLoader().getResource("Register.css").toExternalForm());
		stage.setScene(sc);
		stage.show();
	} // start method end
}// class end
