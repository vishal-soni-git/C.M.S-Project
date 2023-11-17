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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;

public class AboutCollege extends Application
{
	public void start(Stage stage) throws Exception
	{
		Text t = new Text("About AKTU.");
		Text t1= new Text("Dr. A.P.J. Abdul Kalam Technical University");
		Text t2 = new Text(" Uttar Pradesh Technical University (UPTU) was established by the Government of Uttar Pradesh on 8th May 2000\n"+
							"vide Act No. 1248(2)XVII-V-I-I-19-2000 Uttar Pradesh Adhiniyam Sankhya 23 of 2000. Under the University Act,\n"+
							"'Technical Education' includes programmes of education, research and training in Engineering, Technology, Archi\n"+
							"tecture, Town Planning, Pharmacy, Applied Arts & Crafts and such other programmes and areas that the central Gove\n"+
							"rnment may in consultation with All India Council for Technical Education (AICTE) by notification in Gazette\n"+
							"declare. The University is affiliating in nature and its jurisdiction spans the entire state of U.P. in affiliating\n"+
							" B.Tech., M.B.A., M.C.A., B.Arch., B. Pharma., B.H.M.C.T., M.Tech. and Ph.D. programmes in 785 colleges/institutions\n"+
							"imparting graduate, postgraduate and doctoral level training in all government and private institutions located all\n"+
							" over U.P. in engineering, technology, architecture, pharmacy, hotel management and catering technology as well as\n"+
							"M.B.A. and M.C.A. programmes.");
		t.setX(50);
		t.setY(50);
		t.setFont(Font.font("Vardana" , FontWeight.BOLD, FontPosture.REGULAR, 20));

		t1.setX(50);
		t1.setY(50);
		t1.setTextAlignment(TextAlignment.CENTER);
		t1.setFont(Font.font("Vardana" , FontWeight.BOLD, FontPosture.ITALIC, 20));

		t2.setX(50);
		t2.setY(50);
		t2.setTextAlignment(TextAlignment.JUSTIFY);
		t2.setFont(Font.font("Vardana" ,null, FontPosture.REGULAR, 14));

		VBox vb = new VBox();
		vb.getChildren().addAll(t,t1,t2);
		vb.setSpacing(25);
		//vb.setPadding(new Insets(5,5,5,5));

		//FileInputStream stream = new FileInputStream("E:\\anandi.jpg");
		FileInputStream stream = new FileInputStream("C:\\Users\\user\\OneDrive\\Desktop\\Projects sql\\using css project\\Khandari-logo.png");
		Image image = new Image(stream);
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setX(10);
		imageView.setY(10);
		imageView.setFitWidth(75);
		imageView.setPreserveRatio(true);

		Text t11 = new Text("Smt. Anandiben Patel\nHon'ble Chancellor & H.E Governor of U.P.");
		t11.setX(50);
		t11.setY(50);
		t11.setFont(Font.font("Vardana" , FontWeight.BOLD, FontPosture.REGULAR, 20));

		HBox hb1 = new HBox();
		hb1.getChildren().addAll(imageView,t11);
		hb1.setSpacing(150);
		hb1.setPadding(new Insets(10,10,10,10));
		hb1.setPrefSize(50,100);

        FileInputStream stream1 = new FileInputStream("C:\\Users\\user\\OneDrive\\Desktop\\Projects sql\\using css project\\Khandari-logo.png");
		//FileInputStream stream1 = new FileInputStream("E:\\cm.jpg");
		Image image1 = new Image(stream1);
		ImageView imageView1 = new ImageView();
		imageView1.setImage(image1);
		imageView1.setX(10);
		imageView1.setY(10);
		imageView1.setFitWidth(75);
		imageView1.setPreserveRatio(true);

		Text t12 = new Text("Shri. Adityanath Yogi\nHon'ble Chief Minister Government of U.P.(liar)");
		t12.setX(50);
		t12.setY(50);
		t12.setFont(Font.font("Vardana" , FontWeight.BOLD, FontPosture.REGULAR, 20));

		HBox hb2 = new HBox();
		hb2.getChildren().addAll(imageView1,t12);
		hb2.setSpacing(150);
		hb2.setPadding(new Insets(10,10,10,10));
		hb2.setPrefSize(50,100);

		Button b = new Button("OK.");
		b.setPadding(new Insets(5,20,5,20));
		GridPane gp = new GridPane();
		gp.addRow(0,b);	gp.setAlignment(Pos.CENTER);

		VBox vb2 = new VBox();
		vb2.getChildren().addAll(vb,hb1,hb2,gp);
		vb2.setSpacing(25);
		//vb2.setPadding(new Insets(5,5,5,5));
		
		Scene sc = new  Scene(vb2,750,600);
		stage.setScene(sc);
		stage.show();

		b.setOnAction(e->{
			stage.close();
		});
		
		
	}
	public static void main(String [] args)
	{
		launch(args);
	}
}