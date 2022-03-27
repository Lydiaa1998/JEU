package Datas;

import java.io.Serializable;

/*
 * Child of Entities, has some custom attributes like health points or id.
 */
public class Monsters extends Entities implements Serializable{

	private static final long serialVersionUID = -7729084766052881862L;
	private String type = "monster";
	/*
	 * Health points left for the monster, 3 by default, can be redefined in his childs
	 */
	int level = 1;
	double hp = 3+((level-1)/2);
	int id;
	double dmg = 20+((level-1)/2);
	private boolean hasBeenAttacked = false;

	
	public double getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public Monsters(int id, int x, int y, int level) {
		super(x, y);
		this.id = id;
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

	public String getType() {
		return type;
	}

	public double getHp() {
		return hp;
	}

	public int getId() {
		return id;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void isAttacked(double p) {
		this.hp -= p;
	}
	
	public boolean getHasBeenAttacked() {
		return hasBeenAttacked;
	}
	
	public void setHasBeenAttacked(boolean isAttacked) {
		hasBeenAttacked = isAttacked;
	}
}
