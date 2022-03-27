package Datas;

import java.io.Serializable;

/**
 * Child of entities, the data class for the wall object.
 */
public class Walls extends Entities implements Serializable{

	private static final long serialVersionUID = -7350513977270964078L;
	private String type = "wall";
	
	public Walls(int x, int y) {
		super(x, y);
	}

	public String getType() {
		return type;
	}

}
