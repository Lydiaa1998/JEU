package unit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Datas.Door;
import Datas.MonstersArray;
import Datas.Parameters;
import Datas.Player;
import process.InitObjects;
import process.MapProcessing;

/**
 * This class defines the tests to be performed on the door.
 */
public class DungeonTestDoor {

	private MonstersArray ma;
	private MapProcessing map;
	private InitObjects objs;

	@Before
	public void prepareDungeon() {
		ma = new MonstersArray();
		map = new MapProcessing();
		objs = new InitObjects(map);
	}
	
	@Test
	public void checkIfDoorIsOpenWhenMonsters() {
		Player p = objs.initPlayer(10, 10);
		objs.initMonster(5, 8, ma, p); 
		objs.initMonster(6, 8, ma, p); 
		objs.initMonster(7, 8, ma, p);
		objs.initDoor(0, 5);
		
		Door door = null;
		int cpt = 0;
		
		for(int i = 0 ; i < Parameters.GRID_WIDTH ; i++) {
			for(int j = 0 ; j < Parameters.GRID_HEIGHT ; j++) {
				if(map.getEntity(i, j) != null) {
					if(map.getEntity(i, j).getType() == "door") {
						door = (Door) map.getEntity(i, j);
					}
					
					else if(map.getEntity(i, j).getType() == "monster") {
						cpt++;
					}
				}
			}
		}
		
		if(cpt == 0) {
			door.setDoorOpen(true);
		}
		
		if(door == null) {
			assertTrue(false);
		}
		else {
			assertTrue(cpt > 1 && !door.isOpen());
		}
	}
	
	@Test
	public void checkIfDoorIsOpenWhenNotMonsters() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		objs.initPlayer(10, 10);
		objs.initDoor(0, 5);
		
		Door door = null;
		int cpt = 0;
		
		for(int i = 0 ; i < Parameters.GRID_WIDTH ; i++) {
			for(int j = 0 ; j < Parameters.GRID_HEIGHT ; j++) {
				if(map.getEntity(i, j) != null) {
					if(map.getEntity(i, j).getType() == "door") {
						door = (Door) map.getEntity(i, j);
					}
					
					else if(map.getEntity(i, j).getType() == "monster") {
						cpt++;
					}
				}
			}
		}
		
		if(cpt == 0) {
			door.setDoorOpen(true);
		}
		
		if(door == null) {
			assertTrue(false);
		}
		else {
			assertTrue(cpt == 0 && door.isOpen());
		}
	}
	
}
