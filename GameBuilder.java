package culminating;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameBuilder{ //currently just applies generated map onto the scene #class
	public double x = Resolution.x/50*25; //#variables
	public double y = Resolution.y/50*25;
	Stage stage;
    AnchorPane root;

    public GameBuilder(Stage stage, AnchorPane root) //constructer to get root
	{
		this.stage = stage;
		this.root = root;


	}

	public ArrayList<Rectangle> createMap(AnchorPane root){ //creates the randomly generated map
		int enemyNum = 0;
		ArrayList<Rectangle> nodes = new ArrayList<Rectangle>(); //#array
		Enemy enemy = new Enemy(root);
		Group mapTiles = new Group();
		MapGenerator mapgen = new MapGenerator(); //#object
		boolean[][] mapGrid = mapgen.generate();// #methodinstance
		double[][] enemyGrid = mapgen.enemyMap(mapGrid); //#methodinstance

		for(int i = 0; i<mapGrid.length; i++) //#loop
		{
			for(int j = 0; j<mapGrid[i].length; j++)
			{
				if(mapGrid[i][j]) //#condition
				{
					Rectangle r = new Rectangle(); //the GameBuilder sets rectangles where the map isn't - to set boundaries
					r.setFill(Color.GREY);
					r.setX(Resolution.x/50*j);
					r.setY(Resolution.y/50*i);
					r.setWidth(Resolution.x/50);
					r.setHeight(Resolution.y/50);

					mapTiles.getChildren().add(r);
					nodes.add(r);

				}

			}

		}
		for(int i = 0; i< enemyGrid.length;i++) //#loop
		{
			for(int j = 0; j<enemyGrid[i].length; j++)
			{
				if (enemyGrid[i][j] == 1)
				{
					if(enemyNum <= 3)
					{
						if(Math.random() > 0.90) //#condition - randomly sets enemies
						{
						root.getChildren().add(enemy.spawnEnemy(Resolution.x/50*j, Resolution.x/50*i, enemyNum)); //adds enemies
						enemyNum++;
						}
					}
				}
			}
		}
        Line line1 = new Line(850, 850, 130, 850); //lines for gui
        Line line2 = new Line(130, 850, 130, 130);
        Line line3 = new Line(850, 130, 130, 130);
        Line line4 = new Line(850, 850, 850, 130);
        root.getChildren().add(mapTiles); //adds the map
        root.getChildren().add(line1);
        root.getChildren().add(line2);
        root.getChildren().add(line3);
        root.getChildren().add(line4);

		return nodes; //returns the rectangles for collision detection
	}
}





