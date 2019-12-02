package culminating;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Level { //#class - for writing on to txt file level number, and change level number in game

	public int levelNum = 0; //#variables
	public int score;
	public boolean win;
	public File pastScore = new File("Scores.txt");


	public void contGame(boolean winLose){ //if lose, it writes score and level onto txt file
		if(winLose){ //#condition
		levelNum++;
		}
		else{
			try { //#error
				PrintStream scoreWrite = new PrintStream(pastScore);
				scoreWrite.println("Level: " + levelNum + "\n"
						+ "Score: " + score);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

	}

	public int getLevel(){ //gets the levelNum to set onto scene in MainFX
		return levelNum;
	}

}
