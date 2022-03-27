package Datas;

import java.io.Serializable;

/*
 * Child of entities, the data class for the player object
 */
public class Player extends Entities implements Serializable{

	private static final long serialVersionUID = -7968537500671968813L;

	private String type = "player";
	
	/*
	 * Base health of the player
	 */
	private int level = 1;
	private int etage = 1;
	private double xp = 0;
	private double maxXp = 100;
	private double health = 240;
	private double itemHealth = 0;
	private double maxhealth = 240+itemHealth;
	private double itemPower = 0;
	private double power = 1+itemPower+((level-1)/10);
	private boolean hasBeenAttacked = false;
	private boolean isAttacking = false;
	private boolean isDead = false;
	private int attackdirection = -1;

	
	public Player(int x, int y) {
		super(x, y);
	}
	
	public void setHealth(double health) {
		this.health = health;

	}

	public int getEtage() {
		return etage;
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	public String getType() {
		return type;
	}
	
	
	public double getItemHealth() {
		return itemHealth;
	}

	public void setItemHealth(double itemHealth) {
		this.itemHealth = itemHealth;
	}

	public double getItemPower() {
		return itemPower;
	}

	public void setItemPower(double itemPower) {
		this.itemPower = itemPower;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double getHealth() {
		return health;
	}
	
	public double getMaxHealth() {
		return maxhealth;
	}
	
	public double getPower() {
		return power;
	}

	public int getLevel() {
		return level;
	}

	public double getXp() {
		return xp;
	}
	
	public double getMaxXp() {
		return maxXp;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setXp(double xp) {
		this.xp = xp;
	}

	public void setMaxXp(double maxXp) {
		this.maxXp = maxXp;
	}

	public void setMaxhealth(double maxhealth) {
		this.maxhealth = maxhealth;
	}
	
	public boolean getHasBeenAttacked() {
		return hasBeenAttacked;
	}
	
	public void setHasBeenAttacked(boolean isAttacked) {
		hasBeenAttacked = isAttacked;
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	public int getAttackdirection() {
		return attackdirection;
	}

	public void setAttackdirection(int attackdirection) {
		this.attackdirection = attackdirection;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	public void resetPlayer() {
		level = 1;
		etage = 1;
		xp = 0;
		maxXp = 100;
		health = 240;
		itemHealth = 0;
		maxhealth = 240+itemHealth;
		itemPower = 0;
		power = 1+itemPower+((level-1)/10);
		hasBeenAttacked = false;
		isAttacking = false;
		isDead = false;
		attackdirection = -1;
		
	}

}
