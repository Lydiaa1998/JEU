package process;

import java.util.ArrayList;
import java.util.Collections;

import Datas.Entities;
import Datas.Map;
import Datas.Monsters;
import Datas.Player;

public class MapProcessing {

	public final static int MOVE_UP = 0;
	public final static int MOVE_DOWN = 1;
	public final static int MOVE_LEFT = 2;
	public final static int MOVE_RIGHT = 3;
	
	private Map map;
	
	/*
	 * Contains Monsters that have been killed by the player. Useful to play sounds when they die.
	 */
	ArrayList<Monsters> killedMonsters = new ArrayList<>();
	ArrayList<Player> killedPlayers = new ArrayList<>();
	
	public MapProcessing() {
		this.map = new Map();
	}

	/*
	 * Makes an entity move to a chosen direction if conditions are OK (walls, monsters)
	 */
	public void moveEntity(int dir, Entities e) {
		int posx = e.getX();
		int posy = e.getY();
		
		if(dir == MOVE_UP) {
			if(getEntity(posx, posy-1) == null && e.getY() > 0) {
				e.setY(posy - 1);			
				changeObject(posx, posy, null);
				posy--;
				changeObject(posx, posy, e);
			}
		}
		
		else if(dir == MOVE_DOWN) {
			if(getEntity(posx, posy+1) == null && e.getY() < getGridHeight()-1) {
				e.setY(posy + 1);			
				changeObject(posx, posy, null);
				posy++;
				changeObject(posx, posy, e);
			}
		}
		
		else if(dir == MOVE_LEFT) {
			if(getEntity(posx-1, posy) == null && e.getX() > 0) {
				e.setX(posx - 1);			
				changeObject(posx, posy, null);
				posx--;
				changeObject(posx, posy, e);
			}
		}
		
		else if(dir == MOVE_RIGHT) {
			if(getEntity(posx+1, posy) == null && e.getX() < getGridWidth()-1) {
				e.setX(posx + 1);			
				changeObject(posx, posy, null);
				posx++;
				changeObject(posx, posy, e);
			}
		}
	}
	
	
	public Map getMap() {
		return map;
	}

	/**
	 * Used the reset the map.
	 */
	public void resetMap() {
		Entities[][] ObjTab = map.getObjTab();
		
		for(int i = 0 ; i < getGridWidth() ; i++) {
			for(int j = 0 ; j < getGridHeight() ; j++) {
				ObjTab[i][j] = null;
			}
		}
		
		setObjTab(ObjTab);
	}

	public String[][] getCellsToCoords() {
		return map.getCellsToCoords();
	}

	public Entities[][] getObjTab() {
		return map.getObjTab();
	}
	
	/**
	 * Change an entity to a coordinate on the map.
	 * 
	 * @param x. Coordinate in abscissa.
	 * @param y. Coordinate in ordinate.
	 * @param e. The entity to put on the map.
	 */
	public void changeObject(int x, int y, Entities e) {
		map.changeObject(x, y, e);
	}
	
	/**
	 * Removes the entity at X and Y coordinates from the map. 
	 * If the entity is a Monster, it's added to the list of killed monsters.
	 *  If the entity is the Player, he's added to the list of killed players and 
	 *  if we get three players killed, the game is stopped and the list reseted.
	 * @param x. Coordinate in abscissa.
	 * @param y. Coordinate in ordinate.
	 */
	public void supressEntity(int x, int y) {
		if(map.getObjTab()[x][y] instanceof Monsters) {
			Monsters monster = (Monsters) map.getObjTab()[x][y];
			
			addKilledMonster(monster);
			changeObject(x, y, null);
		}
		if(map.getObjTab()[x][y] instanceof Player) {
			Player player = (Player) map.getObjTab()[x][y];
			
			addKilledPlayers(player);
			int numberplayer = Collections.frequency(killedPlayers, "player");
			
			if (numberplayer==3) {
				changeObject(x, y, null);
				killedPlayers.clear();
			}
		}
	
	}
	
	public Entities getEntity(int x, int y) {
		return map.getEntity(x, y);
	}

	public void setObjTab(Entities[][] objTab) {
		map.setObjTab(objTab);
	}

	public int getGridHeight() {
		return map.getGridHeight();
	}

	public int getGridWidth() {
		return map.getGridWidth();
	}

	public int getWidth() {
		return map.getWidth();
	}

	public int getHeight() {
		return map.getHeight();
	}
	
	public ArrayList<Monsters> getKilledMonsters() {
		return killedMonsters;
	}
	
	public void addKilledMonster(Monsters monster) {
		killedMonsters.add(monster);
	}
	
	public void removeKilledMonster(Monsters monster) {
		killedMonsters.remove(monster);
	}
	
	public void addKilledPlayers(Player player) {
		killedPlayers.add(player);
	}
	
	public void removeKilledPlayers (Player player) {
		killedPlayers.remove(player);
	}
	
}
