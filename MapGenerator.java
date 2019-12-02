package culminating;

//generates the map procedurally
public class MapGenerator{			//#class

    public boolean[][] generate(){ //this method generates the base of the map
    	boolean[][] mapGrid = new boolean[50][50]; //#array

 		int x = 25;
 		int y = 25;
 		for(int i = 0; i<mapGrid.length;i++)
 		{
 			for(int j = 0; j<mapGrid[i].length;j++)
 			{
 				mapGrid[i][j] = true;
 			}
 		}
 		mapGrid[25][25] = false;

		while( x < 39 && y < 39 && x >= 10 && y >= 10){ //walk algorithm derived from https://introcs.cs.princeton.edu/java/14array/SelfAvoidingWalk.java.html
 	            double r = Math.random(); //#random - random chance for mapGrid[x][y] to be false - where the map is
 	            if (r < 0.25)  //#condition //#random
 	            {
 	            	x--;
 	            }
 	            else if (r < 0.50 && r >0.25) ///#condition //#random
 	            {
 	            	x++;
 	            }
 	            else if (r < 0.75 && r>0.50) //#condition //#random
 	            {
 	            	y--;
 	            }
 	            else if (r < 1&&r>0.75) //#condition //#random
 	            {
 	            	y++;
 	            }
 	            mapGrid[x][y] = false;
 			}
 			return mapGrid;
 	}

 	public double[][] enemyMap(boolean[][] mapGrid){ //this method generates where the enemies will spawn - they are just dots of colour currently
 		generate(); //#methodclass
 		double[][] enemyGrid = new double[50][50]; //#array
 		for(int i = 0; i<enemyGrid.length;i++)
 		{
 			for(int j = 0; j<enemyGrid[i].length;j++)
 			{
 				if(!mapGrid[i][j]) //#condition
 				{

 					enemyGrid[i][j] = 1;
 				}
 			}
 		}
 		return enemyGrid;
 	}
}