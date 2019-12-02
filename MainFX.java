package culminating;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
public class MainFX extends Application { //launches game, sets scenes etc.



//GLOBALVARIABLES................... //#variables
public double charPosX;
public double charPosY;
public ArrayList<Rectangle> nodes = new ArrayList<Rectangle>(); //array
public File pastScores = new File("Scores");
public int bulletDir = 0;
public boolean bulletShoot;
//GLOBALVARIABLES...................

public static void main(String[] args) {  //#main

	launch(args);
}

@Override
public void start(Stage primaryStage) { //sets the scene to main menu


	AnchorPane root = new AnchorPane();
	AnchorPane scoreRoot = new AnchorPane();
    AnchorPane root1 = new AnchorPane();
	Scene scene = new Scene(root, Resolution.x, Resolution.y);
	Scene mainGame = new Scene(root1, Resolution.x, Resolution.y);
	final Canvas canvas = new Canvas(Resolution.x,Resolution.y);
	Button button = new Button();
	Button button1 = new Button();
	Button button2 = new Button();
	Rectangle mainChar = new Rectangle();
	Text levelText = new Text();








    button1.setText("Score");  //button to display scores
    button1.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
        	Scanner scoreReader;


			try { 												//#error
				scoreReader = new Scanner(pastScores);
            	for(int i = 1; scoreReader.hasNext();i++){
            	Text scores = new Text();
            	Text scores2 = new Text();
            	scores.setText(scoreReader.next());
            	scores2.setText(scoreReader.next());
            	scores.setX(50);
            	scores.setY(50*i);
            	scores2.setX(100);
            	scores2.setY(50*i);
            	scoreRoot.getChildren().add(scores);
            	scoreRoot.getChildren().add(scores2);
            	}


			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}


			 Scene scoreScene = new Scene(scoreRoot, Resolution.x, Resolution.y);
            primaryStage.setScene(scoreScene);
        }
    });


    button2.setText("Exit"); //button to exit game

    button2.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
        	Platform.exit();
        }
    });



    button.setLayoutX(61);
    button.setLayoutY(246);
    button1.setLayoutX(61);
    button1.setLayoutY(410);
    button2.setLayoutX(61);
    button2.setLayoutY(599);
    root.getChildren().add(button);
    root.getChildren().add(button1);
    root.getChildren().add(button2);



    primaryStage.setTitle("Roguelike");
    primaryStage.setScene(scene);
	scene.getStylesheets().add(getClass().getResource("MainMenu.css").toExternalForm());
    primaryStage.show();


    //new scene begins here
	mainChar.setX(Resolution.x/51*26); //main player set onto game scene
	mainChar.setY(Resolution.y/51*26);
	mainChar.setWidth(5);
	mainChar.setHeight(5);
	mainChar.setFill(Color.PURPLE);
	root1.getChildren().add(mainChar);



    GameBuilder gameBuild = new GameBuilder(primaryStage, root1); //#object - calls GameBuilder for use later
	Level level = new Level(); //#object
    nodes = gameBuild.createMap(root1); //calls map rectangles for collision

    EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() { //handles player movement on key press
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.UP) //#condition
            	{

            	bulletDir = 1;
            	charPosX = mainChar.getLayoutX();
            	charPosY = mainChar.getLayoutY()+4;
            	mainChar.setTranslateY(-3+mainChar.getTranslateY());

            	}
            else if(event.getCode() == KeyCode.DOWN)
            {
            	bulletDir = 2;
            	charPosX = mainChar.getLayoutX();
            	charPosY = mainChar.getLayoutY()-4;
            	mainChar.setTranslateY(3 + mainChar.getTranslateY());
            }
            else if(event.getCode() == KeyCode.RIGHT) {
            	bulletDir = 3;
            	charPosX = mainChar.getLayoutX()-4;
            	charPosY = mainChar.getLayoutY();
            	mainChar.setTranslateX(3 + mainChar.getTranslateX());
            }
            else if(event.getCode() == KeyCode.LEFT)
            {
            	bulletDir = 4;
            	charPosX = mainChar.getLayoutX()+4;
            	charPosY = mainChar.getLayoutY();
            	mainChar.setTranslateX(-3 +  mainChar.getTranslateX());
            }
            else if(event.getCode() == KeyCode.SPACE){
            	bulletShoot = true;
            }

        }
    };

    canvas.setFocusTraversable(true);
    canvas.setOnKeyPressed(keyListener); //implements keyListener to canvas


    button.setText("New Game");
    button.setOnAction(new EventHandler<ActionEvent>() {


        @Override
        public void handle(ActionEvent event) {

        	level.contGame(true);

        	levelText.setText("Level: " + level.getLevel());
        	levelText.setX(100);
        	levelText.setY(100);

        	root1.getChildren().add(levelText);

        	root1.getChildren().add(canvas);

            primaryStage.setTitle("Roguelike");
            primaryStage.setScene(mainGame);

            primaryStage.show();

            AnimationTimer timer = new AnimationTimer(){

    			@Override
    			public void handle(long now) { //projectile does not work
    				checkBounds(mainChar);
    				//DOES NOT WORK..................
    				int bulletTravel = 100;
    				int bulletPos = 0;
    				int travelSpeed = 10;
    				Rectangle bullet = new Rectangle();
    				if(bulletDir == 1 && bulletShoot){

						bullet.setLayoutX(charPosX);
						bullet.setLayoutY(charPosY);
						bullet.setFill(Color.BLUE);
						root1.getChildren().add(bullet);
    					while(bulletTravel > bulletPos){
    						bullet.setTranslateY(-travelSpeed + bullet.getTranslateY());
    						bulletPos+=10;
    					}
    				}
    				else if(bulletDir == 2 && bulletShoot){

						bullet.setLayoutX(charPosX);
						bullet.setLayoutY(charPosY);
						bullet.setFill(Color.BLUE);
						root1.getChildren().add(bullet);
    					while(bulletTravel > bulletPos){
    						bullet.setTranslateY(travelSpeed + bullet.getTranslateY());
    						bulletPos+=10;
    					}

    				}
    				else if(bulletDir == 3 && bulletShoot){

						bullet.setLayoutX(charPosX);
						bullet.setLayoutY(charPosY);
						bullet.setFill(Color.BLUE);
						root1.getChildren().add(bullet);
    					while(bulletTravel > bulletPos){
    						bullet.setTranslateX(travelSpeed + bullet.getTranslateX());
    						bulletPos+=10;
    					}
    				}
    				else if(bulletDir == 4 && bulletShoot){

						bullet.setLayoutX(charPosX);
						bullet.setLayoutY(charPosY);
						bullet.setFill(Color.BLUE);
						root1.getChildren().add(bullet);
    					while(bulletTravel > bulletPos){
    						bullet.setTranslateX(-travelSpeed + bullet.getTranslateX());
    						bulletPos+=10;
    					}
    				}
    				//DOES NOT WORK..................

    			}

            };
            timer.start(); //starts Animation timer - does nothing current
        }

      });

	}

//checkBounds checks collision with player and map walls
  private void checkBounds(Rectangle block) { //from https://stackoverflow.com/questions/15013913/checking-collision-of-shapes-with-javafx
	    boolean collisionDetected = false;
	    for (Rectangle static_bloc : nodes) {
	      if (static_bloc != block) {
	        static_bloc.setFill(Color.GREY);

	        Shape intersect = Shape.intersect(block, static_bloc);
	        if (intersect.getBoundsInLocal().getWidth() != -1) {//#condition
	          collisionDetected = true;
	        }
	      }
	    }

	    if (collisionDetected) {

	    	block.setLayoutX(charPosX);
	    	block.setLayoutY(charPosY);
	    }
	  }
}
