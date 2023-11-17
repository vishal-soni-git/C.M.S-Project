// Java program to create a split pane, set
// its orientation and add labels to it
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.geometry.Pos;
import javafx.scene.paint.*;
import javafx.scene.*;
import java.io.*;
import javafx.scene.image.*;

public class Course_FEE extends Application {

	// launch the application
	public void start(Stage stage)
	{
			// set title for the stage
			stage.setTitle("Courses and Fees");
	
			Label l1=new Label("MCA 2 YEAR PROGRAMMING COURSE \n FEES\n Institude Fee\t\t\t\t:Rs. 74,900/- per annum."+
				"\n Examination Fee\t\t\t:Rs. 7,865/- per annum.\n Other Misc. fees\t\t\t:Rs. 1,500/- per annum\n"+
					"Placement Assistance Fee\t:Rs. 1,000/-(one time for final year student only)");
			Label l2=new Label("MCA 5 YEAR PROGRAMMING COURSE \n FEES\n Institude Fee\t\t\t\t:Rs. 40,000/- per annum."+
				"\n Examination Fee\t\t\t:Rs. 7,865/- per annum.\n Other Misc. fees\t\t\t:Rs. 1,500/- per annum\n"+
					"Placement Assistance Fee\t:Rs. 1,000/-(one time for final year student only)");
			Label l3=new Label("MBA 2 YEAR PROGRAMMING COURSE \n FEES\n Institude Fee\t\t\t\t:Rs. 48,000/- per annum."+
				"\n Examination Fee\t\t\t:Rs. 7,865/- per annum.\n Other Misc. fees\t\t\t:Rs. 1,500/- per annum\n"+
					"Placement Assistance Fee\t:Rs. 1,000/-(one time for final year student only)");
			Label l4=new Label("MBA 5 YEAR PROGRAMMING COURSE \n FEES\n Institude Fee\t\t\t\t:Rs. 40,000/- per annum."+
				"\n Examination Fee\t\t\t:Rs. 7,865/- per annum.\n Other Misc. fees\t\t\t:Rs. 1,500/- per annum\n"+
					"Placement Assistance Fee\t:Rs. 1,000/-(one time for final year student only)");
			TitledPane tl1=new TitledPane("MCA",l1);
			TitledPane tl2=new TitledPane("MCA INTEGRATED	",l2);
			TitledPane tl3=new TitledPane("MBA",l3);
			TitledPane tl4=new TitledPane("MBA INTEGRATED",l4);
			tl1.setPadding(new Insets(10,70,10,70));
			tl2.setPadding(new Insets(10,70,10,70));
			tl3.setPadding(new Insets(10,70,10,70));
			tl4.setPadding(new Insets(10,70,10,70));

			tl1.setStyle("-fx-backgroung-color:YELLOW");

			//createing button for appliying student
			Button apply_Bt=new Button("Apply");
			apply_Bt.setPrefSize(100, 20);
            apply_Bt.setAlignment(Pos.CENTER);


			
			//creating main layout
			VBox vb=new VBox();
			vb.getChildren().addAll(tl1,tl2,tl3,tl4,apply_Bt);
			vb.setAlignment(Pos.CENTER);

			// create a scene
			Scene scene = new Scene(vb, 650, 650);
			vb.setStyle("-fx-background-color:BEIGE;");

			// set the scene
			stage.setScene(scene);
			stage.show();

			apply_Bt.setOnAction(e->{
			try
			{
					StudentForm sf=new StudentForm();
			sf.start(new Stage());
				stage.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		
		});

	}

	// Main Method
	public static void main(String args[])
	{
		// launch the application
		launch(args);
	}
}
