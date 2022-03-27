package process;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import Datas.Chest;
import Datas.Door;
import Datas.Entities;
import Datas.Monsters;
import Datas.MonstersArray;
import Datas.Parameters;
import Datas.Player;
import Exceptions.DeathException;

/*
 * The class that contains the player's object methods
 */
public class MotorPlayer {

	private Player player;
	private Door door;
	private Entities[][] objtab;
	private MapProcessing mp;
	private boolean playerExist = false;
	private boolean doorExist = false;
	private MonstersArray ma;
	private InitObjects objs;
	private int attackingDelay = 3;
	public final static int UP = 0;
	public final static int DOWN = 1;
	public final static int LEFT = 2;
	public final static int RIGHT = 3;
	
	/*
	 * Constructor, needs motor object and the grid of objects on the motors to initialize itself
	 */
	public MotorPlayer(MapProcessing mp, MonstersArray ma, InitObjects objs) {
		this.mp = mp;
		this.objtab = mp.getObjTab();
		this.ma = ma;
		this.objs = objs;
	}
	
	/*
	 * Get the player object and put it in the attributes of this class
	 */
	private void getPlayerObject() {
		for(int i = 0 ; i < Parameters.GRID_WIDTH ; i++) {
			for(int j = 0 ; j < Parameters.GRID_HEIGHT ; j++) {
				if(objtab[i][j] != null) {
					if(objtab[i][j].getType().equals("player")) {
						player = (Player) objtab[i][j];
					}
				}
			}
		}
	}
	
	/*
	 * Get the door object and put it in the attributes of this class
	 */
	private void getDoorObject() {
		for(int i = 0 ; i < Parameters.GRID_WIDTH ; i++) {
			for(int j = 0 ; j < Parameters.GRID_HEIGHT ; j++) {
				if(objtab[i][j] != null) {
					if(objtab[i][j].getType().equals("door")) {
						door = (Door) objtab[i][j];
					}
				}
			}
		}
	}
	
	/*
	 * Makes the player move to a direction if conditions are OK (walls, monsters)
	 */
	public void move(int direction) throws DeathException {
		if(!playerExist) {
			getPlayerObject();
			playerExist = true;
		}
		death();
		switch(direction) {
			case UP:
				if(objtab[player.getX()][player.getY()-1] != null) {
					if(objtab[player.getX()][player.getY()-1].getType().equals("door")) {
						Door d = (Door) objtab[player.getX()][player.getY()-1];
						if (d.isOpen()) {
							objs.nextLevel("North", ma, player);
						}
					}
				}
				else {
					mp.moveEntity(MapProcessing.MOVE_UP, player);
				}
				break;
			case DOWN:
				
				if(objtab[player.getX()][player.getY()+1] != null) {
					if(objtab[player.getX()][player.getY()+1].getType().equals("door")) {
						Door d = (Door) objtab[player.getX()][player.getY()+1];
						if (d.isOpen()) {
							objs.nextLevel("South", ma, player);
						}
					}
				}
				else {
					mp.moveEntity(MapProcessing.MOVE_DOWN, player);
				}
				break;
			case LEFT:
				
				if(objtab[player.getX()-1][player.getY()] != null) {
					if(objtab[player.getX()-1][player.getY()].getType().equals("door")) {
						Door d = (Door) objtab[player.getX()-1][player.getY()];
						if (d.isOpen()) {
							objs.nextLevel("West", ma, player);
						}
					}
				}
				else {
					mp.moveEntity(MapProcessing.MOVE_LEFT, player);
				}
				break;
			case RIGHT:
				if(objtab[player.getX()+1][player.getY()] != null) {
					if(objtab[player.getX()+1][player.getY()].getType().equals("door")) {
						Door d = (Door) objtab[player.getX()+1][player.getY()];
						if (d.isOpen()) {
							objs.nextLevel("East", ma, player);
						}
					}
				}
				else {
					mp.moveEntity(MapProcessing.MOVE_RIGHT, player);
				}
				break;
			default:
				break;
		}
	}
	
	/**
	 * Makes the player attack to a direction.
	 * 
	 * @param direction. Direction in which the player attacks.
	 */
	public void attack(int direction) throws DeathException {
		if(!playerExist) {
			getPlayerObject();
			playerExist = true;
		}
		death();
		if(!player.isAttacking()) {
			int posx, posy;
			Entities e;
			switch(direction) {
				case UP:
					posx = player.getX();
					posy = player.getY();
					
					e = objtab[posx][posy-1];
					
					if(e != null && e.getType() == "monster") {
						AttackCooldown();
						Monsters m = (Monsters)objtab[posx][posy-1];
						m.isAttacked(player.getPower());
						m.setHasBeenAttacked(true);
						player.setAttackdirection(2);
						
						if(m.getHp() < 0) {
							mp.supressEntity(posx, posy-1);
		
							giveXP(m.getLevel());
							ma.removeMonsterTAB(m.getId());
						}
						
						else {
							mp.moveEntity(MapProcessing.MOVE_UP, m);
						}
						
						mp.setObjTab(objtab);
						
						if(ma.getMonstersTAB().isEmpty()) {
							if(!doorExist) {
								getDoorObject();
								doorExist = true;
							}
							
							door.setDoorOpen(true);
						}
					}
					else if(e != null && e.getType() == "chest") {
						Chest ch = (Chest) e;
						if (!ch.isOpen()) {
							ch.setOpen(true);
						}
			            else {
			                getChestContent(posx, posy-1);
			            }
					}
					break;
				case DOWN:
					
					posx = player.getX();
					posy = player.getY();
					
					e = objtab[posx][posy+1];
					
					if(e != null && e.getType() == "monster") {
						AttackCooldown();
						Monsters m = (Monsters)objtab[posx][posy+1];
						m.isAttacked(player.getPower());
						m.setHasBeenAttacked(true);
						player.setAttackdirection(3);
						
						if(m.getHp() < 0) {
							mp.supressEntity(posx, posy+1);
		
							giveXP(m.getLevel());
							ma.removeMonsterTAB(m.getId());
						}
						
						else {
							mp.moveEntity(MapProcessing.MOVE_DOWN, m);
						}
						
						mp.setObjTab(objtab);
						
						if(ma.getMonstersTAB().isEmpty()) {
							if(!doorExist) {
								getDoorObject();
								doorExist = true;
							}
							
							door.setDoorOpen(true);
						}
					}
					else if(e != null && e.getType() == "chest") {
						Chest ch = (Chest) e;
						if (!ch.isOpen()) {
							ch.setOpen(true);
						}
			            else {
			                getChestContent(posx, posy+1);
			            }
					}
					break;
				case LEFT:
					posx = player.getX();
					posy = player.getY();
					
					e = objtab[posx-1][posy];
					
					if(e != null && e.getType() == "monster") {
						AttackCooldown();
						Monsters m = (Monsters)objtab[posx-1][posy];
						m.isAttacked(player.getPower());
						m.setHasBeenAttacked(true);
						player.setAttackdirection(0);
						
						if(m.getHp() < 0) {
							mp.supressEntity(posx-1, posy);
		
							giveXP(m.getLevel());
							ma.removeMonsterTAB(m.getId());
						}
						
						else {
							mp.moveEntity(MapProcessing.MOVE_LEFT, m);
						}
						
						mp.setObjTab(objtab);
						
						if(ma.getMonstersTAB().isEmpty()) {
							if(!doorExist) {
								getDoorObject();
								doorExist = true;
							}
							
							door.setDoorOpen(true);
						}
					}
					else if(e != null && e.getType() == "chest") {
						Chest ch = (Chest) e;
						if (!ch.isOpen()) {
							ch.setOpen(true);
						}
			            else {
			                getChestContent(posx-1, posy);
			            }
					}
					break;
				case RIGHT:
					posx = player.getX();
					posy = player.getY();
					
					e = objtab[posx+1][posy];
					
					if(e != null && e.getType() == "monster") {
						AttackCooldown();
						Monsters m = (Monsters)objtab[posx+1][posy];
						m.isAttacked(player.getPower());
						m.setHasBeenAttacked(true);
						player.setAttackdirection(1);
						
						if(m.getHp() < 0) {
							mp.supressEntity(posx+1, posy);
		
							giveXP(m.getLevel());
							ma.removeMonsterTAB(m.getId());
						}
						
						else {
							mp.moveEntity(MapProcessing.MOVE_RIGHT, m);
						}
						
						mp.setObjTab(objtab);
						
						if(ma.getMonstersTAB().isEmpty()) {
							if(!doorExist) {
								getDoorObject();
								doorExist = true;
							}
							
							door.setDoorOpen(true);
						}
					}
					else if(e != null && e.getType() == "chest") {
						Chest ch = (Chest) e;
						if (!ch.isOpen()) {
							ch.setOpen(true);
						}
			            else {
			                getChestContent(posx+1, posy);
			            }
					}
					break;
				default:
					break;
			}
		}
		
	}
	
	/**
	 * Gives experience to the player when he kills a monster.
	 * 
	 * @param monster_level. The level of the Monster that he has killed.
	 */
	public void giveXP(int monster_level) {
		player.setXp(player.getXp()+(5*(monster_level/player.getLevel())));
		
		if(player.getXp() > player.getMaxXp()) {
			player.setXp(player.getXp() - player.getMaxXp());
			player.setLevel(player.getLevel()+1);
			player.setMaxhealth(player.getMaxHealth()+(5*(player.getLevel()-1)));
			player.setMaxXp(player.getMaxXp()+(3*(player.getLevel()-1)));
		}
	}
	
	public Player getPlayer() {
		return player;
	}

	/**
	 * Lets you randomly retrieve an item from the open chest.
	 * 
	 * @param x. Coordinate in abscissa of the chest.
	 * @param y. Coordinate in ordinate of the chest.
	 */
	public void getChestContent(int x, int y){
		Random r = new Random();
		int a = r.nextInt(3);
		
		switch(a) {
			case 1 :
				double old = player.getPower();
				player.setItemPower(player.getItemPower()+0.2);
				player.setPower(player.getPower()+player.getItemPower());
				double n = player.getPower();
				JOptionPane.showMessageDialog(null, "You found a better sword in the chest !\n Damage: "+old+">"+n);
				break;
			case 2 :
				double olda = player.getMaxHealth();
				player.setItemHealth(player.getItemHealth()+20);
				player.setMaxhealth(player.getMaxHealth()+player.getItemHealth());
				double na = player.getMaxHealth();
				JOptionPane.showMessageDialog(null, "You found a better armor in the chest !\n Health: "+olda+">"+na);
				break;
			default :
				JOptionPane.showMessageDialog(null, "The chest is empty.");
				break;
		}
		
		objtab[x][y] = null;
	}
	
	/**
	 * Defines a cooldown during which the player cannot attack.
	 */
	public void AttackCooldown() {
		player.setAttacking(true);
		TimerTask cd = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				player.setAttacking(false);
				player.setAttackdirection(-1);
				

			}
		};
		
		Timer t = new Timer();
		t.schedule(cd, 600);
	}

	public int getAttackingDelay() {
		return attackingDelay;
	}

	public void setAttackingDelay(int attackingDelay) {
		this.attackingDelay = attackingDelay;
	}
	
	/**
	 * Defines the death of the player.
	 */
	public void death() throws DeathException {
		if(player.isDead() && player.getHealth() <= 0) {
			File file = new File("save.ser");
			
			if(file != null) {
				file.delete();
			}
			
			JOptionPane.showMessageDialog(null, "You died.");
			throw new DeathException();
		}
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
