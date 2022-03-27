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
 * This class defines the tests to be performed on the attacks of the monsters
 * when they are next to the player.
 */
public class DungeonTestMonsterAttack {

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
	public void monsterAttackUp() {
		Player p = objs.initPlayer(5, 5);
		double playerHP = p.getHealth();
		int py = p.getY();
		
		objs.initMonster(5, 6, ma, p); 
		Monsters m = (Monsters) map.getEntity(5, 6);
		
		mp = new MonsterManager(m, p, map);
		mp.Hit(MonsterManager.UP);
		// Il faut un deuxième hit car il y a un cooldown de 1 attaque
		mp.Hit(MonsterManager.UP);
		
		boolean hasTakeDamage = false;
		boolean hasMove = false;
		
		if(playerHP > p.getHealth()) {
			hasTakeDamage = true;
		}
		if(py > p.getY()) {
			hasMove = true;
		}
		
		assertTrue(hasTakeDamage && hasMove);
	}
	
	@Test
	public void monsterAttackDown() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		double playerHP = p.getHealth();
		int py = p.getY();
		
		objs.initMonster(5, 4, ma, p); 
		Monsters m = (Monsters) map.getEntity(5, 4);
		
		mp = new MonsterManager(m, p, map);
		mp.Hit(MonsterManager.DOWN);
		// Il faut un deuxième hit car il y a un cooldown de 1 attaque
		mp.Hit(MonsterManager.DOWN);
		
		boolean hasTakeDamage = false;
		boolean hasMove = false;
		
		if(playerHP > p.getHealth()) {
			hasTakeDamage = true;
		}
		if(py < p.getY()) {
			hasMove = true;
		}
		
		assertTrue(hasTakeDamage && hasMove);
	}
	
	@Test
	public void monsterAttackLeft() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		double playerHP = p.getHealth();
		int px = p.getX();
		
		objs.initMonster(6, 5, ma, p); 
		Monsters m = (Monsters) map.getEntity(6, 5);
		
		mp = new MonsterManager(m, p, map);
		mp.Hit(MonsterManager.LEFT);
		// Il faut un deuxième hit car il y a un cooldown de 1 attaque
		mp.Hit(MonsterManager.LEFT);
		
		boolean hasTakeDamage = false;
		boolean hasMove = false;
		
		if(playerHP > p.getHealth()) {
			hasTakeDamage = true;
		}
		if(px > p.getX()) {
			hasMove = true;
		}
		
		assertTrue(hasTakeDamage && hasMove);
	}
	
	@Test
	public void monsterAttackRight() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		double playerHP = p.getHealth();
		int px = p.getX();
		
		objs.initMonster(4, 5, ma, p); 
		Monsters m = (Monsters) map.getEntity(4, 5);
		
		mp = new MonsterManager(m, p, map);
		mp.Hit(MonsterManager.RIGHT);
		// Il faut un deuxième hit car il y a un cooldown de 1 attaque
		mp.Hit(MonsterManager.RIGHT);
		
		boolean hasTakeDamage = false;
		boolean hasMove = false;
		
		if(playerHP > p.getHealth()) {
			hasTakeDamage = true;
		}
		if(px < p.getX()) {
			hasMove = true;
		}
		
		assertTrue(hasTakeDamage && hasMove);
	}
	
	//------------------------------------------------------------------------------
	
	@Test
	public void monsterAttackUpWall() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		double playerHP = p.getHealth();
		int py = p.getY();
		
		Walls w = new Walls(5, 4);
		int wx = w.getX();
		int wy = w.getY();
		map.changeObject(wx, wy, w);
		
		objs.initMonster(5, 6, ma, p); 
		Monsters m = (Monsters) map.getEntity(5, 6);
		
		mp = new MonsterManager(m, p, map);
		mp.Hit(MonsterManager.UP);
		// Il faut un deuxième hit car il y a un cooldown de 1 attaque
		mp.Hit(MonsterManager.UP);
		
		boolean hasTakeDamage = false;
		boolean hasNotMove = false;
		
		if(playerHP > p.getHealth()) {
			hasTakeDamage = true;
		}
		if(py == p.getY()) {
			hasNotMove = true;
		}
		
		assertTrue(hasTakeDamage && hasNotMove);
	}
	
	@Test
	public void monsterAttackDownWall() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		double playerHP = p.getHealth();
		int py = p.getY();
		
		Walls w = new Walls(5, 6);
		int wx = w.getX();
		int wy = w.getY();
		map.changeObject(wx, wy, w);
		
		objs.initMonster(5, 4, ma, p); 
		Monsters m = (Monsters) map.getEntity(5, 4);
		
		mp = new MonsterManager(m, p, map);
		mp.Hit(MonsterManager.DOWN);
		// Il faut un deuxième hit car il y a un cooldown de 1 attaque
		mp.Hit(MonsterManager.DOWN);
		
		boolean hasTakeDamage = false;
		boolean hasNotMove = false;
		
		if(playerHP > p.getHealth()) {
			hasTakeDamage = true;
		}
		if(py == p.getY()) {
			hasNotMove = true;
		}
		
		assertTrue(hasTakeDamage && hasNotMove);
	}
	
	@Test
	public void monsterAttackLeftWall() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		double playerHP = p.getHealth();
		int px = p.getX();
		
		Walls w = new Walls(4, 5);
		int wx = w.getX();
		int wy = w.getY();
		map.changeObject(wx, wy, w);
		
		objs.initMonster(6, 5, ma, p); 
		Monsters m = (Monsters) map.getEntity(6, 5);
		
		mp = new MonsterManager(m, p, map);
		mp.Hit(MonsterManager.LEFT);
		// Il faut un deuxième hit car il y a un cooldown de 1 attaque
		mp.Hit(MonsterManager.LEFT);
		
		boolean hasTakeDamage = false;
		boolean hasNotMove = false;
		
		if(playerHP > p.getHealth()) {
			hasTakeDamage = true;
		}
		if(px == p.getX()) {
			hasNotMove = true;
		}
		
		assertTrue(hasTakeDamage && hasNotMove);
	}
	
	@Test
	public void monsterAttackRightWall() {
		map.resetMap();
		ma.resetMonsterTAB();
		
		Player p = objs.initPlayer(5, 5);
		double playerHP = p.getHealth();
		int px = p.getX();
		
		Walls w = new Walls(6, 5);
		int wx = w.getX();
		int wy = w.getY();
		map.changeObject(wx, wy, w);
		
		objs.initMonster(4, 5, ma, p); 
		Monsters m = (Monsters) map.getEntity(4, 5);
		
		mp = new MonsterManager(m, p, map);
		mp.Hit(MonsterManager.RIGHT);
		// Il faut un deuxième hit car il y a un cooldown de 1 attaque
		mp.Hit(MonsterManager.RIGHT);
		
		boolean hasTakeDamage = false;
		boolean hasNotMove = false;
		
		if(playerHP > p.getHealth()) {
			hasTakeDamage = true;
		}
		if(px == p.getX()) {
			hasNotMove = true;
		}
		
		assertTrue(hasTakeDamage && hasNotMove);
	}
}
