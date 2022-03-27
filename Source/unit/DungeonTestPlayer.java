package unit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Datas.Player;
import Datas.Walls;
import process.InitObjects;
import process.MapProcessing;

/**
 * This class defines the tests to be performed on the player's movements.
 */
public class DungeonTestPlayer {

	private MapProcessing map;
	private InitObjects objs;

	@Before
	public void prepareDungeon() {
		map = new MapProcessing();
		objs = new InitObjects(map);
	}
	
	@Test
	public void movePlayerMoveUp() {
		Player p = objs.initPlayer(10, 10);
		
		int x = p.getX();
		int y = p.getY();
		boolean hasMove = false;
		boolean hasMoveOnMap = false;
		
		map.moveEntity(MapProcessing.MOVE_UP, p);
		
		if(p.getX() == x && p.getY() == y-1) {
			hasMove = true;
		}
		
		if(map.getEntity(x, y) == null && map.getEntity(x, y-1) == p) {
			hasMoveOnMap = true;
		}
		
		assertTrue(hasMove && hasMoveOnMap);
	}
	
	@Test
	public void movePlayerMoveDown() {
		map.resetMap();
		
		Player p = objs.initPlayer(10, 10);
		
		int x = p.getX();
		int y = p.getY();
		boolean hasMove = false;
		boolean hasMoveOnMap = false;
		
		map.moveEntity(MapProcessing.MOVE_DOWN, p);
		
		if(p.getX() == x && p.getY() == y+1) {
			hasMove = true;
		}
		
		if(map.getEntity(x, y) == null && map.getEntity(x, y+1) == p) {
			hasMoveOnMap = true;
		}
		
		assertTrue(hasMove && hasMoveOnMap);
	}
	
	@Test
	public void movePlayerMoveLeft() {
		map.resetMap();
		
		Player p = objs.initPlayer(10, 10);
		
		int x = p.getX();
		int y = p.getY();
		boolean hasMove = false;
		boolean hasMoveOnMap = false;
		
		map.moveEntity(MapProcessing.MOVE_LEFT, p);
		
		if(p.getX() == x-1 && p.getY() == y) {
			hasMove = true;
		}
		
		if(map.getEntity(x, y) == null && map.getEntity(x-1, y) == p) {
			hasMoveOnMap = true;
		}
		
		assertTrue(hasMove && hasMoveOnMap);
	}
	
	@Test
	public void movePlayerMoveRight() {
		map.resetMap();
		
		Player p = objs.initPlayer(10, 10);
		
		int x = p.getX();
		int y = p.getY();
		boolean hasMove = false;
		boolean hasMoveOnMap = false;
		
		map.moveEntity(MapProcessing.MOVE_RIGHT, p);
		
		if(p.getX() == x+1 && p.getY() == y) {
			hasMove = true;
		}
		
		if(map.getEntity(x, y) == null && map.getEntity(x+1, y) == p) {
			hasMoveOnMap = true;
		}
		
		assertTrue(hasMove && hasMoveOnMap);
	}
	
	@Test
	public void movePlayerMoveUpWithWall() {
		map.resetMap();
		
		Walls w = new Walls(5, 0);
		
		int wx = w.getX();
		int wy = w.getY();
		
		map.changeObject(wx, wy, w);
		
		Player p = objs.initPlayer(5, 1);
		
		int x = p.getX();
		int y = p.getY();
		
		boolean hasNotMove = false;
		boolean hasNotMoveOnMap = false;
		
		map.moveEntity(MapProcessing.MOVE_UP, p);
		
		if(p.getX() == x && p.getY() == y) {
			hasNotMove = true;
		}
		
		if(map.getEntity(x, y) == p) {
			hasNotMoveOnMap = true;
		}
		
		assertTrue(hasNotMove && hasNotMoveOnMap);
	}
	
	@Test
	public void movePlayerMoveDownWithWall() {
		map.resetMap();
		
		Walls w = new Walls(5, map.getGridHeight()-1);
		
		int wx = w.getX();
		int wy = w.getY();
		
		map.changeObject(wx, wy, w);
		
		Player p = objs.initPlayer(5, map.getGridHeight()-2);
		
		int x = p.getX();
		int y = p.getY();
		
		boolean hasNotMove = false;
		boolean hasNotMoveOnMap = false;
		
		map.moveEntity(MapProcessing.MOVE_DOWN, p);
		
		if(p.getX() == x && p.getY() == y) {
			hasNotMove = true;
		}
		
		if(map.getEntity(x, y) == p) {
			hasNotMoveOnMap = true;
		}
		
		assertTrue(hasNotMove && hasNotMoveOnMap);
	}
	
	@Test
	public void movePlayerMoveLeftWithWall() {
		map.resetMap();
		
		Walls w = new Walls(0, 5);
		
		int wx = w.getX();
		int wy = w.getY();
		
		map.changeObject(wx, wy, w);
		
		Player p = objs.initPlayer(1, 5);
		
		int x = p.getX();
		int y = p.getY();
		
		boolean hasNotMove = false;
		boolean hasNotMoveOnMap = false;
		
		map.moveEntity(MapProcessing.MOVE_LEFT, p);
		
		if(p.getX() == x && p.getY() == y) {
			hasNotMove = true;
		}
		
		if(map.getEntity(x, y) == p) {
			hasNotMoveOnMap = true;
		}
		
		assertTrue(hasNotMove && hasNotMoveOnMap);
	}
	
	@Test
	public void movePlayerMoveRightWithWall() {
		map.resetMap();
		
		Walls w = new Walls(map.getGridWidth()-1, 5);
		
		int wx = w.getX();
		int wy = w.getY();
		
		map.changeObject(wx, wy, w);
		
		Player p = objs.initPlayer(map.getGridWidth()-2, 5);
		
		int x = p.getX();
		int y = p.getY();
		
		boolean hasNotMove = false;
		boolean hasNotMoveOnMap = false;
		
		map.moveEntity(MapProcessing.MOVE_RIGHT, p);
		
		if(p.getX() == x && p.getY() == y) {
			hasNotMove = true;
		}
		
		if(map.getEntity(x, y) == p) {
			hasNotMoveOnMap = true;
		}
		
		assertTrue(hasNotMove && hasNotMoveOnMap);
	}
	
}
