import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox.*;
import javafx.scene.text.*;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.scene.paint.*;
import java.io.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.effect.DropShadow;

public class CollegePage extends Application 
{
	Button teacherBt,studentBt,syllabusBt,CourseBt,collegeBt;
	public void start(Stage stage) throws Exception
	{
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(4);
		dropShadow.setOffsetY(4);

		Text t = new Text("Raja Balwant Singh Management\nTechnical Campus Khandari, Agra");
		t.setX(50);
		t.setY(50);
		t.setFont(Font.font("Vardana" , FontWeight.BOLD, FontPosture.ITALIC, 25));
		t.setFill(Color.SKYBLUE);
		t.setStrokeWidth(1);
		t.setStroke(Color.BLUE);
		t.setEffect(dropShadow);
		t.setUnderline(true);
		
		String logo="C:\\Users\\user\\OneDrive\\Desktop\\Projects sql\\using css project\\Khandari-logo.png";
		FileInputStream stream = new FileInputStream(logo);
		Image image = new Image(stream);
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setX(10);
		imageView.setY(10);
		imageView.setFitWidth(70);
		imageView.setPreserveRatio(true);

		
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(t,imageView);
		hb1.setSpacing(175);
		hb1.setPadding(new Insets(20,10,20,10));
		hb1.setPrefSize(50,100);
		//hb1.setBackground(new Background(new BackgroundFill(Color.SKYBLUE,null,null)));


		
		teacherBt=new Button("Teacher Data");
		studentBt=new Button("Student Data");
		syllabusBt=new Button("Syllabus");
		CourseBt=new Button("Courses & Fees");
		collegeBt=new Button("About College");

		teacherBt.setPrefSize(600,30);
		studentBt.setPrefSize(600,30);
		syllabusBt.setPrefSize(600,30);
		collegeBt.setPrefSize(600,30);
		CourseBt.setPrefSize(600,30);

		Font font = Font.font("Vardana" , FontWeight.BOLD , 20);
		teacherBt.setFont(font);
		studentBt.setFont(font);
		syllabusBt.setFont(font);
		CourseBt.setFont(font);
		collegeBt.setFont(font);

         // setting layout
		GridPane gp = new GridPane();
		gp.addRow(0,teacherBt);
		gp.addRow(1,studentBt);
		gp.addRow(2,syllabusBt);
		gp.addRow(4,collegeBt);
		gp.addRow(3,CourseBt);

	//	gp.setMinSize(400,200);
		//gp.setHgap(10);
		gp.setVgap(40);
		gp.setAlignment(Pos.CENTER);


		VBox vb = new VBox();
		vb.getChildren().addAll(hb1,gp);
		vb.setSpacing(25);
		vb.setPadding(new Insets(20,10,20,10));

			//setting Node
			vb.setId("vbox");
            hb1.setId("hbox");
			teacherBt.setId("TeacherData");
			studentBt.setId("StudentData");
			syllabusBt.setId("Syllabus");
			collegeBt.setId("AboutCollege");
			CourseBt.setId("CourseFee");

			teacherBt.setOnAction(e->{
				try
				{
					TeacherPage tp=new TeacherPage();
				    tp.start(new Stage());				
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			});

			studentBt.setOnAction(e->{
				try
				{
					StudentPage sp=new StudentPage();
				    sp.start(new Stage());				
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			});

			syllabusBt.setOnAction(e->{
				try
				{
					SyllabusWork sw=new SyllabusWork();
				    sw.start(new Stage());				
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			});
            
			CourseBt.setOnAction(e->{
				try
				{
					Course_FEE cf=new Course_FEE();
				    cf.start(new Stage());				
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			});

			collegeBt.setOnAction(e->{
				try
				{
					AboutCollege ac=new AboutCollege();
				    ac.start(new Stage());				
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			});

		
		Scene sc = new  Scene(vb,700,600);
		sc.getStylesheets().add(getClass().getClassLoader().getResource("CollegePage.css").toExternalForm());
		stage.setScene(sc);
		stage.setTitle("College Page");
		stage.show();
		
	}
   public static void main(String[] args)
   {
	launch(args);
    }
}