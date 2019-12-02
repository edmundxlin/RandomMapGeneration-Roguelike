package culminating;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy { //#class

	AnchorPane root;
	public Rectangle [] enemyArr = new Rectangle[4]; //#array
	private int speed; //#variables

	public Enemy(AnchorPane root){  //creates array of enemies
		this.root = root;
		for(int i =0; i<enemyArr.length; i++){
			enemyArr[i] = new Rectangle();
			enemyArr[i].setFill(Color.BLUE);
			enemyArr[i].setWidth(5);
			enemyArr[i].setHeight(5);
		}
	}
	public Node spawnEnemy(double x, double y, int i){ //spawns enemy - returns the enemy and sets its layout
		enemyArr[i].setLayoutX(x);
		enemyArr[i].setLayoutY(y);
		return enemyArr[i];
	}

	public void moveEnemies(int x, int speedX, int speedY){ //to move enemies - not functional
		enemyArr[x].setTranslateX(speedX+enemyArr[x].getTranslateX());
		enemyArr[x].setTranslateY(speedY+enemyArr[x].getTranslateY());
	}
}
