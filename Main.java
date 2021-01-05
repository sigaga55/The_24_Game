package application;
	
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Interface when the game is started. 
			Text text = new Text(150, 220, "24 Game");
			text.setFont(Font.font("Zapfino", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 48));
			text.setFill(Color.WHITE);

			Rectangle rec1 = new Rectangle(220, 360, 160, 40);  // First button, "HOW TO PLAY"
			rec1.setArcWidth(30); 
			rec1.setArcHeight(30); 
			rec1.setFill(Color.WHITE);
			Text ins1 = new Text(236, 387, "HOW TO PLAY");
			ins1.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 18));
			ins1.setFill(Color.BLACK);

			Rectangle rec2 = new Rectangle(220, 410, 160, 40);  // Second button, "START"
			rec2.setArcWidth(30); 
			rec2.setArcHeight(30); 
			rec2.setFill(Color.WHITE);
			Text ins2 = new Text(270, 437, "START");
			ins2.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 18));
			ins2.setFill(Color.BLACK);

			Group rootOpen = new Group(text, rec1, rec2, ins1, ins2);
			Scene scene = new Scene(rootOpen, 600, 600, Color.LIGHTBLUE);
			
			// Set the background image
			// Image source: https://st2.depositphotos.com/1205137/7289/v/600/depositphotos_72890943-stock-video-colorful-flying-numbers.jpg
			Image bg = null;
			try {
				bg = new Image(new FileInputStream("/Users/chensijia/Desktop/The24Game/src/application/startBG.jpg"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			scene.setFill(new ImagePattern(bg));
			
			primaryStage.setTitle("24Game");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Click on the first button to see a new window showing the instructions
			EventHandler<MouseEvent> click1 = new EventHandler<MouseEvent>() { 

				public void handle(MouseEvent e) { 

					Text howToPlay = new Text(300, 200, ""
							+ "     24 Game is a game where ......");

					howToPlay.setFont(Font.font("American Typewritter", FontWeight.LIGHT, FontPosture.REGULAR, 18));
					howToPlay.setFill(Color.MINTCREAM);

					StackPane instructions = new StackPane();
					instructions.getChildren().add(howToPlay);

					Scene sceneI = new Scene(instructions, 600, 300);
					Image bg = null;
					try {
						// Image source: https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnJQW5AqIqatk0Y_OvvJgsCSncQFeGGDIAkQ&usqp=CAU
						bg = new Image(new FileInputStream("/Users/chensijia/Desktop/The24Game/src/application/insBG.jpg"));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sceneI.setFill(new ImagePattern(bg));

					// New window (Stage)
					Stage newWindow = new Stage();
					newWindow.setTitle("Instructions");
					newWindow.setScene(sceneI);
					newWindow.initModality(Modality.WINDOW_MODAL);
					newWindow.initOwner(primaryStage);

					newWindow.show();
				} 
			};  
			ins1.addEventFilter(MouseEvent.MOUSE_CLICKED, click1); 
			
			// Click on the second button to start a new game
			EventHandler<MouseEvent> click2 = new EventHandler<MouseEvent>() { 
				@Override 
				public void handle(MouseEvent e) { 
					
					GamePlay newGame = new GamePlay();
					newGame.set();

				} 
			};  
			ins2.addEventFilter(MouseEvent.MOUSE_CLICKED, click2); 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
