package unit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Datas.Monsters;
import Datas.MonstersArray;
import Datas.Player;
import Datas.Walls;
import Exceptions.DeathException;
import process.InitObjects;
import process.MapProcessing;
import process.MotorPlayer;

/**
 * This class defines the tests to be performed on the player's attacks.
 */
public class DungeonTestPlayerAttack {
	
	private MonstersArray ma;
	private MapProcessing map;
	private InitObjects objs;
	private MotorPlayer mp;

	@Before
	public void prepareDungeon() {
		ma = new MonstersArray();
		map = new MapProcessing();
		objs = new InitObjects(map);
		mp = new MotorPlayer(map, ma, objs);
	}
	
	@Test
	public void playerAttackUp() {
		Player p = objs.initPlayer(5, 5);
		
		objs.initMonster(5, 4, ma, p); 
		Monsters m = (Monsters) map.getEntity(5, 4);
		double mHp = m.getHp();
		int my = m.getY();
		
		try {
			mp.attack(MotorPlayer.UP);
		} catch (DeathException e) {
			// TODO Auto-generated catch block
		}
		
		boolean hasTakeDamage = false;
		boolean hasMove = false;
		
		if(mHp > m.getHp()) {
			hasTakeDamage = true;
		}
		if(my > m.getY()) {
			hasMove = true;
		}
		
		assertTrue(hasTakeDamage && hasMove);
	}
	
	@Test
	public void playerAttackDown() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		objs.initMonster(5, 6, ma, p); 
		Monsters m = (Monsters) map.getEntity(5, 6);
		double mHp = m.getHp();
		int my = m.getY();
		
		try {
			mp.attack(MotorPlayer.DOWN);
		} catch (DeathException e) {
			// TODO Auto-generated catch block
		}
		
		boolean hasTakeDamage = false;
		boolean hasMove = false;
		
		if(mHp > m.getHp()) {
			hasTakeDamage = true;
		}
		if(my < m.getY()) {
			hasMove = true;
		}
		
		assertTrue(hasTakeDamage && hasMove);
	}
	
	@Test
	public void playerAttackLeft() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		objs.initMonster(4, 5, ma, p); 
		Monsters m = (Monsters) map.getEntity(4, 5);
		double mHp = m.getHp();
		int mx = m.getX();
		
		try {
			mp.attack(MotorPlayer.LEFT);
		} catch (DeathException e) {
			// TODO Auto-generated catch block
		}
		
		boolean hasTakeDamage = false;
		boolean hasMove = false;
		
		if(mHp > m.getHp()) {
			hasTakeDamage = true;
		}
		if(mx > m.getX()) {
			hasMove = true;
		}
		
		assertTrue(hasTakeDamage && hasMove);
	}
	
	@Test
	public void playerAttackRight() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		objs.initMonster(6, 5, ma, p); 
		Monsters m = (Monsters) map.getEntity(6, 5);
		double mHp = m.getHp();
		int mx = m.getX();
		
		try {
			mp.attack(MotorPlayer.RIGHT);
		} catch (DeathException e) {
			// TODO Auto-generated catch block
		}
		
		boolean hasTakeDamage = false;
		boolean hasMove = false;
		
		if(mHp > m.getHp()) {
			hasTakeDamage = true;
		}
		if(mx < m.getX()) {
			hasMove = true;
		}
		
		assertTrue(hasTakeDamage && hasMove);
	}
	
	//------------------------------------------------------------------------------
	
	@Test
	public void playerAttackUpWall() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		Walls w = new Walls(5, 3);
		int wx = w.getX();
		int wy = w.getY();
		map.changeObject(wx, wy, w);
		
		objs.initMonster(5, 4, ma, p); 
		Monsters m = (Monsters) map.getEntity(5, 4);
		double mHp = m.getHp();
		int my = m.getY();
		
		try {
			mp.attack(MotorPlayer.UP);
		} catch (DeathException e) {
			// TODO Auto-generated catch block
		}
		
		boolean hasTakeDamage = false;
		boolean hasNotMove = false;
		
		if(mHp > m.getHp()) {
			hasTakeDamage = true;
		}
		if(my == m.getY()) {
			hasNotMove = true;
		}
		
		assertTrue(hasTakeDamage && hasNotMove);
	}
	
	@Test
	public void playerAttackDownWall() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		Walls w = new Walls(5, 7);
		int wx = w.getX();
		int wy = w.getY();
		map.changeObject(wx, wy, w);
		
		objs.initMonster(5, 6, ma, p); 
		Monsters m = (Monsters) map.getEntity(5, 6);
		double mHp = m.getHp();
		int my = m.getY();
		
		try {
			mp.attack(MotorPlayer.DOWN);
		} catch (DeathException e) {
			// TODO Auto-generated catch block
		}
		
		boolean hasTakeDamage = false;
		boolean hasNotMove = false;
		
		if(mHp > m.getHp()) {
			hasTakeDamage = true;
		}
		if(my == m.getY()) {
			hasNotMove = true;
		}
		
		assertTrue(hasTakeDamage && hasNotMove);
	}
	
	@Test
	public void playerAttackLeftWall() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		Walls w = new Walls(3, 5);
		int wx = w.getX();
		int wy = w.getY();
		map.changeObject(wx, wy, w);
		
		objs.initMonster(4, 5, ma, p); 
		Monsters m = (Monsters) map.getEntity(4, 5);
		double mHp = m.getHp();
		int mx = m.getX();
		
		try {
			mp.attack(MotorPlayer.LEFT);
		} catch (DeathException e) {
			// TODO Auto-generated catch block
		}
		
		boolean hasTakeDamage = false;
		boolean hasNotMove = false;
		
		if(mHp > m.getHp()) {
			hasTakeDamage = true;
		}
		if(mx == m.getX()) {
			hasNotMove = true;
		}
		
		assertTrue(hasTakeDamage && hasNotMove);
	}
	
	@Test
	public void playerAttackRightWall() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		
		Walls w = new Walls(7, 5);
		int wx = w.getX();
		int wy = w.getY();
		map.changeObject(wx, wy, w);
		
		objs.initMonster(6, 5, ma, p); 
		Monsters m = (Monsters) map.getEntity(6, 5);
		double mHp = m.getHp();
		int mx = m.getX();
		
		try {
			mp.attack(MotorPlayer.RIGHT);
		} catch (DeathException e) {
			// TODO Auto-generated catch block
		}
		
		boolean hasTakeDamage = false;
		boolean hasNotMove = false;
		
		if(mHp > m.getHp()) {
			hasTakeDamage = true;
		}
		if(mx == m.getX()) {
			hasNotMove = true;
		}
		
		assertTrue(hasTakeDamage && hasNotMove);
	}
}
