package process;



import java.util.Random;



import Datas.Entities;
import Datas.Monsters;
import Datas.Player;

/*
 * MonsterProcessing V.2 with multi-threading implementation
 */
public class MonsterManager extends Thread{
	
	private Monsters m;
	private Player p;
	private MapProcessing map;
	private Entities[][] objtab;
	private int hitcounter = 2;
	
	public static final int NONE = -1;
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	private boolean run = true;

	public MonsterManager(Monsters m, Player p, MapProcessing map) {
		this.m = m;
		this.map = map;
		this.p = p;
		objtab = map.getObjTab();
	}
	
	public void run() {
		while(m.getHp() > 0 && run == true) {
			Move();
			try {
				Random r = new Random();
				
				Thread.sleep(r.nextInt(400)+800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Used to make a monster move depending on the player's position.
	 */
	public void Move() {
		int playerX = p.getX();
		int playerY = p.getY();
		/*
		 * If the player is very close to the monster, then makes the monster move to the player
		 */
		if((Math.abs(m.getX() - playerX) < 7 && Math.abs(m.getY() - playerY) < 7)  && (Math.abs(m.getX() - playerX) > 1  || Math.abs(m.getY() - playerY) > 1)  ) {


			if(m.getX() > playerX ) {
				if(objtab[m.getX()-1][m.getY()] != null) {
					if(!objtab[m.getX()-1][m.getY()].getType().equals("monster") && !objtab[m.getX()-1][m.getY()].getType().equals("wall")) {
						objtab[m.getX()][m.getY()] = null;
						m.setX(m.getX()-1);
						objtab[m.getX()][m.getY()] = m;
					}
				}
				else{
					objtab[m.getX()][m.getY()] = null;
					m.setX(m.getX()-1);
					objtab[m.getX()][m.getY()] = m;
				}
				
			}
			if(m.getX() < playerX) {
				if(objtab[m.getX()+1][m.getY()] != null) {
					if(!objtab[m.getX()+1][m.getY()].getType().equals("monster") && !objtab[m.getX()+1][m.getY()].getType().equals("wall")) {
						objtab[m.getX()][m.getY()] = null;
						m.setX(m.getX()+1);
						objtab[m.getX()][m.getY()] = m;
					}

				}
				else{
					objtab[m.getX()][m.getY()] = null;
					m.setX(m.getX()+1);
					objtab[m.getX()][m.getY()] = m;
				}
			}
			if(m.getY() > playerY) {
				if(objtab[m.getX()][m.getY()-1] != null) {
					if(!objtab[m.getX()][m.getY()-1].getType().equals("monster") && !objtab[m.getX()][m.getY()-1].getType().equals("wall")) {
						objtab[m.getX()][m.getY()] = null;
						m.setY(m.getY()-1);
						objtab[m.getX()][m.getY()] = m;
					}
					
				}
				else{
					objtab[m.getX()][m.getY()] = null;
					m.setY(m.getY()-1);
					objtab[m.getX()][m.getY()] = m;
				}
			}
			if(m.getY() < playerY) {
				if(objtab[m.getX()][m.getY()+1] != null) {
					if(!objtab[m.getX()][m.getY()+1].getType().equals("monster") && !objtab[m.getX()][m.getY()+1].getType().equals("wall")) {
						objtab[m.getX()][m.getY()] = null;
						m.setY(m.getY()+1);
						objtab[m.getX()][m.getY()] = m;
					}
				}
				else{
					objtab[m.getX()][m.getY()] = null;
					m.setY(m.getY()+1);
					objtab[m.getX()][m.getY()] = m;
				}
			}	
			map.setObjTab(objtab);
			
		}
		
		int isHit = NONE;
		
		if(m.getX() - playerX == 1 && (m.getY() == playerY)) { // Si le monstre est à gauche ?
			isHit = LEFT;
		}
		if(m.getX() - playerX == -1 && (m.getY() == playerY)) { // Si le monstre est à droite ?
			isHit = RIGHT;
		}
		if(m.getY() - playerY == 1 && (m.getX() == playerX)) { // Si le monstre est en haut ?
			isHit = UP;
		}
		if(m.getY() - playerY == -1 && (m.getX() == playerX)) { // Si le monstre est en bas ?
			isHit = DOWN;
		}
		if(isHit != NONE) {
			Hit(isHit);
		}
	}
	
	/**
	 * Makes the monster attack in a direction.
	 * 
	 * @param direction. The direction of the attack.
	 */
	public void Hit(int direction) {//le monstre tape dans la direction demandée
		hitcounter--;
		
		if(hitcounter == 0) {
			hitcounter = 2;
			
			switch (direction) {
				case UP:
					if(!(objtab[m.getX()][m.getY()-1] == null)) {
						if(objtab[m.getX()][m.getY()-1].getType().equals("player")) {
							Player p = (Player) objtab[m.getX()][m.getY()-1];
							p.setHealth(p.getHealth()-m.getDmg());
							p.setHasBeenAttacked(true);
							
							if(!(objtab[m.getX()][m.getY()-2] == null)) {
								if(!objtab[m.getX()][m.getY()-2].getType().equals("wall") && !objtab[m.getX()][m.getY()-2].getType().equals("monster")) {
									objtab[m.getX()][m.getY()-1] = null;
									objtab[m.getX()][m.getY()-2] = p;
									p.setY(p.getY()-1);
								}
								
								else {
									p.setHealth(p.getHealth()-m.getDmg());
								}
							}
							
							else {
								objtab[m.getX()][m.getY()-1] = null;
								objtab[m.getX()][m.getY()-2] = p;
								p.setY(p.getY()-1);
							}
							
							if(p.getHealth() <= 0) {
								map.supressEntity(p.getX(), p.getY());
								p.setDead(true);
							}
						}
					}
					
					break;
				case DOWN:
					if(!(objtab[m.getX()][m.getY()+1] == null)) {
						if(objtab[m.getX()][m.getY()+1].getType().equals("player")) {
							Player p = (Player) objtab[m.getX()][m.getY()+1];
							p.setHealth(p.getHealth()-m.getDmg());
							p.setHasBeenAttacked(true);
							
							if(!(objtab[m.getX()][m.getY()+2] == null)) {
								if(!objtab[m.getX()][m.getY()+2].getType().equals("wall") && !objtab[m.getX()][m.getY()+2].getType().equals("monster")) {
									objtab[m.getX()][m.getY()+1] = null;
									objtab[m.getX()][m.getY()+2] = p;
									p.setY(p.getY()+1);
								}
								
								else {
									p.setHealth(p.getHealth()-m.getDmg());
								}
							}
							
							else {
								objtab[m.getX()][m.getY()+1] = null;
								objtab[m.getX()][m.getY()+2] = p;
								p.setY(p.getY()+1);
							}
							
							if(p.getHealth() <= 0) {
								map.supressEntity(p.getX(), p.getY());
								p.setDead(true);
							}							
						}
					}
					
					break;
				case LEFT:
					if(!(objtab[m.getX()-1][m.getY()] == null)) {
						if(objtab[m.getX()-1][m.getY()].getType().equals("player")) {
							Player p = (Player) objtab[m.getX()-1][m.getY()];
							p.setHealth(p.getHealth()-m.getDmg());
							p.setHasBeenAttacked(true);
							
							if(!(objtab[m.getX()-2][m.getY()] == null)) {
								if(!objtab[m.getX()-2][m.getY()].getType().equals("wall") && !objtab[m.getX()-2][m.getY()].getType().equals("monster")) {
									objtab[m.getX()-1][m.getY()] = null;
									objtab[m.getX()-2][m.getY()] = p;
									p.setX(p.getX()-1);
								}
								
								else {
									p.setHealth(p.getHealth()-m.getDmg());
								}
							}
							
							else {
								objtab[m.getX()-1][m.getY()] = null;
								objtab[m.getX()-2][m.getY()] = p;
								p.setX(p.getX()-1);
							}
							
							if(p.getHealth() <= 0) {
								map.supressEntity(p.getX(), p.getY());
								p.setDead(true);
							}
						}
					}
			
					break;
				case RIGHT:
					if(!(objtab[m.getX()+1][m.getY()] == null)) {
						if(objtab[m.getX()+1][m.getY()].getType().equals("player")) {
							Player p = (Player) objtab[m.getX()+1][m.getY()];
							p.setHealth(p.getHealth()-m.getDmg());
							p.setHasBeenAttacked(true);
							
							if(!(objtab[m.getX()+2][m.getY()] == null)) {
								if(!objtab[m.getX()+2][m.getY()].getType().equals("wall") && !objtab[m.getX()+2][m.getY()].getType().equals("monster")) {
									objtab[m.getX()+1][m.getY()] = null;
									objtab[m.getX()+2][m.getY()] = p;
									p.setX(p.getX()+1);
								}
								
								else {
									p.setHealth(p.getHealth()-m.getDmg());
								}
							}
							
							else {
								objtab[m.getX()+1][m.getY()] = null;
								objtab[m.getX()+2][m.getY()] = p;
								p.setX(p.getX()+1);
							}
							
							if(p.getHealth() <= 0) {
								map.supressEntity(p.getX(), p.getY());
								p.setDead(true);
							}
						}
					}
					
					break;
		
				default:
					break;
			}
			
			map.setObjTab(objtab);
		}
	}

	public Monsters getM() {
		return m;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}
	
}
