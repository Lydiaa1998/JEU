package unit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Datas.Monsters;
import Datas.MonstersArray;
import Datas.Player;
import Datas.Walls;
import process.InitObjects;
import process.MapProcessing;
import process.MonsterManager;

/**
 * This class defines the tests to be performed on the movement of the monsters
 * when they are next to the player.
 */
public class DungeonTestMonster {

	private MonstersArray ma;
	private MapProcessing map;
	private InitObjects objs;
	private MonsterManager mp;

	@Before
	public void prepareDungeon() {
		ma = new MonstersArray();
		map = new MapProcessing();
		objs = new InitObjects(map);
	}
	
	@Test
	public void moveMonsterMoveUp() {
		Player p = objs.initPlayer(5, 5);
		
		objs.initMonster(5, 8, ma, p); 
		Monsters m = (Monsters) map.getEntity(5, 8);
		int mx = m.getX();
		int my = m.getY();
		
		mp = new MonsterManager(m, p, map);
		mp.Move();
		
		boolean hasMove = false;
		boolean hasMoveOnMap = false;
		
		if(m.getX() == mx && m.getY() == my-1) {
			hasMove = true;
		}
		
		if(map.getEntity(mx, my) == null && map.getEntity(mx, my-1) == m) {
			hasMoveOnMap = true;
		}
		
		assertTrue(hasMove && hasMoveOnMap);
	}
	
	@Test
	public void moveMonsterMoveDown() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		objs.initMonster(5, 2, ma, p);
		Monsters m = (Monsters) map.getEntity(5, 2);
		int mx = m.getX();
		int my = m.getY();

		mp = new MonsterManager(m, p, map);
		mp.Move();
		
		boolean hasMove = false;
		boolean hasMoveOnMap = false;
		
		if(m.getX() == mx && m.getY() == my+1) {
			hasMove = true;
		}
		
		if(map.getEntity(mx, my) == null && map.getEntity(mx, my+1) == m) {
			hasMoveOnMap = true;
		}
		
		assertTrue(hasMove && hasMoveOnMap);
	}
	
	@Test
	public void moveMonsterMoveLeft() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		objs.initMonster(8, 5, ma, p);
		Monsters m = (Monsters) map.getEntity(8, 5);
		int mx = m.getX();
		int my = m.getY();

		mp = new MonsterManager(m, p, map);
		mp.Move();
		
		boolean hasMove = false;
		boolean hasMoveOnMap = false;
		
		if(m.getX() == mx-1 && m.getY() == my) {
			hasMove = true;
		}
		
		if(map.getEntity(mx, my) == null && map.getEntity(mx-1, my) == m) {
			hasMoveOnMap = true;
		}
		
		assertTrue(hasMove && hasMoveOnMap);
	}
	
	@Test
	public void moveMonsterMoveRight() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		objs.initMonster(2, 5, ma, p);
		Monsters m = (Monsters) map.getEntity(2, 5);
		int mx = m.getX();
		int my = m.getY();

		mp = new MonsterManager(m, p, map);
		mp.Move();
		
		boolean hasMove = false;
		boolean hasMoveOnMap = false;
		
		if(m.getX() == mx+1 && m.getY() == my) {
			hasMove = true;
		}
		
		if(map.getEntity(mx, my) == null && map.getEntity(mx+1, my) == m) {
			hasMoveOnMap = true;
		}
		
		assertTrue(hasMove && hasMoveOnMap);
	}
	
	@Test
	public void moveMonsterMoveUpWithWall() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		Walls w = new Walls(5, 6);
		
		int wx = w.getX();
		int wy = w.getY();
		
		map.changeObject(wx, wy, w);
		
		objs.initMonster(5, 7, ma, p);
		Monsters m = (Monsters) map.getEntity(5, 7);
		int mx = m.getX();
		int my = m.getY();

		mp = new MonsterManager(m, p, map);
		mp.Move();
		
		boolean hasNotMove = false;
		boolean hasNotMoveOnMap = false;
		
		if(m.getX() == mx && m.getY() == my) {
			hasNotMove = true;
		}
		
		if(map.getEntity(mx, my) == m) {
			hasNotMoveOnMap = true;
		}
		
		assertTrue(hasNotMove && hasNotMoveOnMap);
	}
	
	@Test
	public void moveMonsterMoveDownWithWall() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		Walls w = new Walls(5, 4);
		
		int wx = w.getX();
		int wy = w.getY();
		
		map.changeObject(wx, wy, w);
		
		objs.initMonster(5, 3, ma, p);
		Monsters m = (Monsters) map.getEntity(5, 3);
		int mx = m.getX();
		int my = m.getY();

		mp = new MonsterManager(m, p, map);
		mp.Move();
		
		boolean hasNotMove = false;
		boolean hasNotMoveOnMap = false;
		
		if(m.getX() == mx && m.getY() == my) {
			hasNotMove = true;
		}
		
		if(map.getEntity(mx, my) == m) {
			hasNotMoveOnMap = true;
		}
		
		assertTrue(hasNotMove && hasNotMoveOnMap);
	}
	
	@Test
	public void moveMonsterMoveLeftWithWall() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		Walls w = new Walls(6, 5);
		
		int wx = w.getX();
		int wy = w.getY();
		
		map.changeObject(wx, wy, w);
		
		objs.initMonster(7, 5, ma, p);
		Monsters m = (Monsters) map.getEntity(7, 5);
		int mx = m.getX();
		int my = m.getY();

		mp = new MonsterManager(m, p, map);
		mp.Move();
		
		boolean hasNotMove = false;
		boolean hasNotMoveOnMap = false;
		
		if(m.getX() == mx && m.getY() == my) {
			hasNotMove = true;
		}
		
		if(map.getEntity(mx, my) == m) {
			hasNotMoveOnMap = true;
		}
		
		assertTrue(hasNotMove && hasNotMoveOnMap);
	}
	
	@Test
	public void moveMonsterMoveRightWithWall() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		Walls w = new Walls(4, 5);
		
		int wx = w.getX();
		int wy = w.getY();
		
		map.changeObject(wx, wy, w);
		
		objs.initMonster(3, 5, ma, p);
		Monsters m = (Monsters) map.getEntity(3, 5);
		int mx = m.getX();
		int my = m.getY();

		mp = new MonsterManager(m, p, map);
		mp.Move();
		
		boolean hasNotMove = false;
		boolean hasNotMoveOnMap = false;
		
		if(m.getX() == mx && m.getY() == my) {
			hasNotMove = true;
		}
		
		if(map.getEntity(mx, my) == m) {
			hasNotMoveOnMap = true;
		}
		
		assertTrue(hasNotMove && hasNotMoveOnMap);
	}
}
