package process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import Datas.Entities;
import Datas.MonstersArray;
import Datas.Parameters;
import Datas.Player;

/**
 * This class allows to initialize a game or to end it.
 */
public class GameManagement {
	
	private InitObjects objs;
	private MonstersArray ma;
	private Motor motor;
	private boolean playerinit = false;
	private Player p;

	public GameManagement(InitObjects objs, MonstersArray ma, Motor motor) {
		this.objs = objs;
		this.ma = ma;
		this.motor = motor;
	}
	
	/**
	 * Used to initialize the game and create the save file.
	 */
	public void initGame() {
		File file = new File("save.ser");
		if(!file.exists()) {
			if(!playerinit) {
				p = objs.initPlayer(Parameters.GRID_WIDTH/2 - 1,Parameters.GRID_HEIGHT-2);
				playerinit = true;
			}

			objs.initDoor(Parameters.GRID_WIDTH/2 - 1, 0);
			objs.initWalls();
			objs.initMonster(ma,p);
			objs.initChest();
		}
		else {
			Entities[][] objstab = null;
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("save.ser"));
				objstab = (Entities[][]) in.readObject();
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Entities[][] currObjtab = motor.getMapProcessing().getObjTab();
			Player p = null;
			/*
			 * Initialise le joueur en premier
			 */
			for(int i = 0; i<Parameters.GRID_WIDTH; i++) {
				for (int j = 0; j<Parameters.GRID_HEIGHT; j++) {
					if(objstab[i][j] != null && objstab[i][j].getType().equals("player")) {
						p = (Player) objstab[i][j];
						currObjtab[i][j] = p;
						motor.getMapProcessing().setObjTab(currObjtab);
					}

				}

			}
			for(int i = 0; i<Parameters.GRID_WIDTH; i++) {
				for (int j = 0; j<Parameters.GRID_HEIGHT; j++) {
					if(objstab[i][j] != null) {

						if(objstab[i][j].getType().equals("monster")) {
							objs.initMonster(i, j, ma, p);
						}
						else if (objstab[i][j].getType().equals("player")) {

						}
						else if(objstab[i][j].getType().equals("wall")){
							objs.initWall(i, j);
						}
						else if(objstab[i][j].getType().equals("door")) {
							objs.initDoor(i, j);
						}
					}
				}
			}
			motor.getMapProcessing().setObjTab(currObjtab);
		}
	}
	
	/**
	 * Used to reset the current game.
	 */
	public void resetGame() {
		motor.getMapProcessing().resetMap();
		motor.getMa().resetMonsterTAB();
		objs.resetPlayerPos(p);
		p.resetPlayer();

	}

}
