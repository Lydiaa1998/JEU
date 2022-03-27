package Datas;

import java.io.Serializable;

/**
 * Child of entities, the data class for the door object.
 */
public class Door extends Entities implements Serializable{

	private static final long serialVersionUID = 3104003097002607064L;
	private String type = "door";
	private boolean isOpen;
	
	public Door(int x, int y) {
		super(x, y);
		this.isOpen = false;
	}

	public String getType() {
		return type;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public void setDoorOpen(boolean opened) {
		this.isOpen = opened;
	}

}
