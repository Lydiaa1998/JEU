package Datas;

import java.io.Serializable;

/*
 * Parent Data class for every entities on the map.
 */
public class Entities implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9039144452581696324L;
	/*
	 * Type of the entity, if we create a Entities object, it will be unknown.
	 * In the classes that inherit of this class, the type is defined as, for example, "player"
	 */
	private String type = "unknown";
	/*
	 * position of this object on the map on the x-axis
	 */
	private int x;
	/*
	 * position of this object on the y-axis
	 */
	private int y;
	/*
	 * tell us if the entity is dead
	 */
	private boolean isDead;
	
	/*
	 * Constructor, initial position is needed in order to initialize this object
	 */
	public Entities(int x, int y){
		this.x = x;
		this.y = y;
		this.isDead = false;
	}
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public String getType() {
		return type;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public void died() {
		isDead = true;
	}
	
}
