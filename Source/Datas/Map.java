package Datas;

/**
 * This class represents the map on which the game will take place.
 */
public class Map {

	private int Width = Parameters.MAP_WIDTH;
	private int Height = Parameters.MAP_HEIGHT - 5;
	private int gridWidth = Parameters.GRID_WIDTH;
	private int gridHeight = Parameters.GRID_HEIGHT;
	
	private String[][] cellsToCoords;
	private Entities[][] ObjTab;
	
	public Map() {
		cellsToCoords = new String[gridWidth][gridHeight];
		ObjTab = new Entities[gridWidth][gridHeight];

		convertCoordsToCells();
	}

	/*
	 * Converts each cell of the grid to a set of coordinates for graphics display purpose
	 */
	public void convertCoordsToCells() {
		int x = 0;
		int y = 0;
		int i = 0;
		int j = 0;
		
		while(j < gridHeight) {
			while(i < gridWidth) {
				cellsToCoords[i][j] = x + ";" + y;
				x += Width / gridWidth;
				i++;
			}
			
			j++;
			x = 0;
			y += Height / gridHeight;
			i = 0;
		}
	}

	public void resetMap() {
		for(int i = 0 ; i < gridWidth ; i++) {
			for(int j = 0 ; j < gridHeight ; j++) {
				ObjTab[i][j] = null;
			}
		}
	}

	public String[][] getCellsToCoords() {
		return cellsToCoords;
	}

	public Entities[][] getObjTab() {
		return ObjTab;
	}

	public void changeObject(int x, int y, Entities e) {
		ObjTab[x][y] = e;
	}

	public Entities getEntity(int x, int y) {
		return ObjTab[x][y];
	}

	public void setObjTab(Entities[][] objTab) {
		ObjTab = objTab;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public int getWidth() {
		return Width;
	}

	public int getHeight() {
		return Height;
	}

}
