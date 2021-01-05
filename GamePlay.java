package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GamePlay {

	Group root = new Group();
	protected Stage primaryStage;

	private static int[][] board = new int[2][2];
	private Boolean isUpdated = false;
	private Boolean gets24 = false;
	private Boolean fails = false;
	Text num1, num2, num3, num4;
	ArrayList<Integer> clicks = new ArrayList<Integer>();
	ArrayList<Integer> loc = new ArrayList<Integer>();

	public void set() {
		int[][] numberBank = {
				{6, 6, 6, 6},
				{3, 8, 1, 1},
				{2, 3, 9, 1}
				{5, 5, 5, 1}
				{2, 8, 8, 8}
				{2, 8, 9, 9}

		};
		
		// Choose a set of 4 numbers from the numberBank above
		Random rand = new Random();
	    int int_random = rand.nextInt(numberBank.length);
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				board[i][j] = numberBank[int_random][i*2+j];
			}
		}

		Scene sceneP = new Scene(root, 800, 600, Color.LIGHTBLUE);

		Stage newWindow = new Stage();
		newWindow.setScene(sceneP);
		newWindow.initModality(Modality.WINDOW_MODAL);
		newWindow.initOwner(primaryStage);
		newWindow.show();
		
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				Rectangle rec = new Rectangle(200+200*j, 100+100*i, 180, 80);  
				rec.setArcWidth(30); 
				rec.setArcHeight(30); 
				rec.setFill(Color.WHITE);
				rec.setStyle("-fx-stroke: black; -fx-stroke-width: 3;");
				root.getChildren().add(rec);
			}
		}
		
		num1 = new Text(275+200*0, 155+100*0, ""+board[0][0]);
		num1.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 40));
		num1.setFill(Color.BLACK);
		root.getChildren().add(num1);
		
		num2 = new Text(275+200*1, 155+100*0, ""+board[0][1]);
		num2.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 40));
		num2.setFill(Color.BLACK);
		root.getChildren().add(num2);
		
		num3 = new Text(275+200*0, 155+100*1, ""+board[1][0]);
		num3.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 40));
		num3.setFill(Color.BLACK);
		root.getChildren().add(num3);
		
		num4 = new Text(275+200*1, 155+100*1, ""+board[1][1]);
		num4.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 40));
		num4.setFill(Color.BLACK);
		root.getChildren().add(num4);
		
		Rectangle recA = new Rectangle(100, 330, 90, 90); // Addition sign 
		recA.setArcWidth(10); 
		recA.setArcHeight(10); 
		Image signA = null;
		try {
			signA = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/The24Game/src/application/add.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recA.setFill(new ImagePattern(signA));
		recA.setStyle("-fx-stroke: black; -fx-stroke-width: 3;");
		
		Rectangle recS = new Rectangle(260, 330, 90, 90); // Subtraction sign
		recS.setArcWidth(10); 
		recS.setArcHeight(10); 
		Image signS = null;
		try {
			signS = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/The24Game/src/application/minus.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recS.setFill(new ImagePattern(signS));
		recS.setStyle("-fx-stroke: black; -fx-stroke-width: 3;");
		
		Rectangle recM = new Rectangle(420, 330, 90, 90); // Multiplication sign
		recM.setArcWidth(10); 
		recM.setArcHeight(10); 
		Image signM = null;
		try {
			signM = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/The24Game/src/application/multiply.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recM.setFill(new ImagePattern(signM));
		recM.setStyle("-fx-stroke: black; -fx-stroke-width: 3;");
		
		Rectangle recD = new Rectangle(580, 330, 90, 90); // Division sign
		recD.setArcWidth(10); 
		recD.setArcHeight(10); 
		Image signD = null;
		try {
			signD = new Image(new FileInputStream("/Users/mingyuliu/eclipse-workspace/The24Game/src/application/divide.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recD.setFill(new ImagePattern(signD));
		recD.setStyle("-fx-stroke: black; -fx-stroke-width: 3;");
		
		root.getChildren().add(recA);
		root.getChildren().add(recS);
		root.getChildren().add(recM);
		root.getChildren().add(recD);
		
		AnimationTimer timer = new AnimationTimer() {
			public void handle(long arg0) {
				
				if(!isUpdated) {

					// Update the numbers
					root.getChildren().remove(num1);
					if (board[0][0]!=2000) {
						num1 = new Text(275+200*0, 155+100*0, ""+board[0][0]);
						num1.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 40));
						root.getChildren().add(num1);
					}
					
					root.getChildren().remove(num2);
					if (board[0][1]!=2000) {
						num2 = new Text(275+200*1, 155+100*0, ""+board[0][1]);
						num2.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 40));
						root.getChildren().add(num2);
					}
					
					root.getChildren().remove(num3);
					if (board[1][0]!=2000) {
						num3 = new Text(275+200*0, 155+100*1, ""+board[1][0]);
						num3.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 40));
						root.getChildren().add(num3);
					}
					
					root.getChildren().remove(num4);
					if (board[1][1]!=2000) {
						num4 = new Text(275+200*1, 155+100*1, ""+board[1][1]);
						num4.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 40));
						root.getChildren().add(num4);
					}
					
					if (board[0][0] == 24 || board[0][1] == 24 || board[1][0] == 24 || board[1][1] == 24)
						gets24 = true;
					else if (board[0][0]+board[0][1]+board[1][0]+board[1][1]>=5000)
						fails = true;
				}
				
				// When the player wins the game:
				if(gets24) {

					this.stop();
					Stage newOver = new Stage();
					newOver.initModality(Modality.APPLICATION_MODAL);
					newOver.initOwner(primaryStage);
					Text congrats = new Text("   Congrats! You've won the game!");
					congrats.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 16));
					congrats.setFill(Color.BLACK);
					congrats.setLayoutY(42);

					Button button = new Button("OK!");
					button.setLayoutX(130);
					button.setLayoutY(72);

					EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() { 
						@Override 
						public void handle(MouseEvent e) { 
							newOver.close();
							newWindow.close();
						}
					};
					button.addEventFilter(MouseEvent.MOUSE_CLICKED, click); 

					Pane dialogBox = new Pane();
					dialogBox.getChildren().addAll(button, congrats); 
					dialogBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

					Scene dialogScene = new Scene(dialogBox, 300, 120, Color.WHITE);
					newOver.setScene(dialogScene);
					newOver.show();
				}
				
				// When the player fails:
				if(fails) {

					this.stop();
					Stage newOver = new Stage();
					newOver.initModality(Modality.APPLICATION_MODAL);
					newOver.initOwner(primaryStage);
					Text congrats = new Text("   Sorry but you can't get 24 in this way!");
					congrats.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 14));
					congrats.setFill(Color.BLACK);
					congrats.setLayoutY(42);

					Button button = new Button("OK!");
					button.setLayoutX(130);
					button.setLayoutY(72);

					EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() { 
						@Override 
						public void handle(MouseEvent e) { 
							newOver.close();
							newWindow.close();
						}
					};
					button.addEventFilter(MouseEvent.MOUSE_CLICKED, click); 

					Pane dialogBox = new Pane();
					dialogBox.getChildren().addAll(button, congrats); 
					dialogBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

					Scene dialogScene = new Scene(dialogBox, 300, 120, Color.WHITE);
					newOver.setScene(dialogScene);
					newOver.show();
				}
			}
		};
		timer.start();
		
		// When the mouse clicks on the numbers/operation signs, the location of the
		// click will be collected and the display would be updated accordingly. 
		sceneP.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				double xPos = me.getX();
				double yPos = me.getY();
				
				if (xPos>=200 && xPos<=380 && yPos>=100 && yPos<=180) {
					clicks.add(board[0][0]);
					loc.add(0);
				}
				else if (xPos>=400 && xPos<=580 && yPos>=100 && yPos<=180) {
					clicks.add(board[0][1]);
					loc.add(1);
				}
				else if (xPos>=200 && xPos<=380 && yPos>=200 && yPos<=280) {
					clicks.add(board[1][0]);
					loc.add(2);
				}
				else if (xPos>=400 && xPos<=580 && yPos>=200 && yPos<=280) {
					clicks.add(board[1][1]);
					loc.add(3);
				}
				else if (xPos>=100 && xPos<=190 && yPos>=330 && yPos<=420)
					clicks.add(1001); // Add
				else if (xPos>=260 && xPos<=350 && yPos>=330 && yPos<=420)
					clicks.add(1002); // Subtract
				else if (xPos>=420 && xPos<=510 && yPos>=330 && yPos<=420)
					clicks.add(1003); // Multiply
				else if (xPos>=580 && xPos<=670 && yPos>=330 && yPos<=420)
					clicks.add(1004); // Divide
				
				if (clicks.size()==3 && clicks.get(0)<=1000 && clicks.get(1)>1000 && clicks.get(2)<=1000) {
					if (clicks.get(1)==1001)
						board[loc.get(0)/2][loc.get(0)%2] += board[loc.get(1)/2][loc.get(1)%2];
					else if (clicks.get(1)==1002)
						board[loc.get(0)/2][loc.get(0)%2] -= board[loc.get(1)/2][loc.get(1)%2];
					else if (clicks.get(1)==1003)
						board[loc.get(0)/2][loc.get(0)%2] *= board[loc.get(1)/2][loc.get(1)%2];
					else
						board[loc.get(0)/2][loc.get(0)%2] /= board[loc.get(1)/2][loc.get(1)%2];
					board[loc.get(1)/2][loc.get(1)%2] = 2000;
					clicks.clear();
					loc.clear();
					isUpdated = false;
				} else if (clicks.size()>=2 && clicks.get(0)<=1000 && clicks.get(1)<=1000) {
					clicks.remove(0);
					loc.remove(0);
				} else if (clicks.size()>=2 && clicks.get(0)>1000 && clicks.get(1)>1000) {
					clicks.remove(0);
				} 

			}
		});
	}
	
	
}
