package Datas;

import java.io.Serializable;

/**
 * Child of entities, the data class for the chest object.
 */
public class Chest extends Entities implements Serializable{

	private static final long serialVersionUID = 3104003097002607064L;
	private String type = "chest";
	private boolean isOpen = false;
	
	public Chest(int x, int y) {
		super(x, y);
		this.isOpen = false;
	}

	public String getType() {
		return type;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public void setOpen(boolean opened) {
		this.isOpen = opened;
	}

}
