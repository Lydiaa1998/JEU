package process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Datas.Chest;
import Datas.Door;
import Datas.Entities;
import Datas.Monsters;
import Datas.MonstersArray;
import Datas.Parameters;
import Datas.Player;
import Datas.Walls;

/*
 * The class that contains the methods to initialize the entities
 */
public class InitObjects {
	
	private Player p1;
	private Door d1;
	private MapProcessing map;
	
	public InitObjects(MapProcessing map) {
		this.map = map;
	}
	
	/**
	 * Initialize the player object to x and y coordinates and place him on the grid
	 * MUST BE INITIALIZED BEFORE MONSTERS
	 * 
	 * @param x. Coordinate in abscissa.
	 * @param y. Coordinate in ordinate.
	 * @return The initialized player.
	 */
	public Player initPlayer(int x, int y) {
		p1 = new Player(x, y);
		map.changeObject(x, y, p1);
		
		return p1;
	}
	
	/**
	 * Initialize the chest object at a random location on the map.
	 * 
	 * @return The initialized chest.
	 */
	public Chest initChest() {
		Random r = new Random();
		
		int x = r.nextInt(Parameters.GRID_WIDTH-2);
		int y = r.nextInt(Parameters.GRID_HEIGHT-2);
		
		while(map.getEntity(x, y) != null) {
			x = r.nextInt(Parameters.GRID_WIDTH-2);
			y = r.nextInt(Parameters.GRID_HEIGHT-2);
		}
		
		Chest ch = new Chest(x, y);
		map.changeObject(x, y, ch);
		
		return ch;
	}
	
	/**
	 * Allows you to define the position of the player at a specific location on the map.
	 * 
	 * @param p. The player from whom we change his coordinates.
	 */
	public void resetPlayerPos(Player p) {
		map.changeObject(Parameters.GRID_WIDTH/2 - 1,Parameters.GRID_HEIGHT-2, p);
		p.setX(Parameters.GRID_WIDTH/2 - 1);
		p.setY(Parameters.GRID_HEIGHT-2);
	}
	
	/*
	 * Initialize a certain quantity of monsters randomly on the grid.
	 */
	public void initMonster(MonstersArray ma, Player p) {
		p1 = p;
		Random r = new Random();
		int quantity = r.nextInt(3)+p1.getEtage();
		HashMap<Integer, MonsterManager> maNew = ma.getMonstersTAB();
		for(int i=0;i<quantity;i++) {
			r = new Random(); // generate an ID
			
			int id = r.nextInt(999999999);
			int x = r.nextInt(Parameters.GRID_WIDTH-2);
			int y = r.nextInt(Parameters.GRID_HEIGHT-2);
			
			if(map.getEntity(x, y) == null) {
				Monsters m = new Monsters(id, x, y, p1.getEtage());
				MonsterManager mm = new MonsterManager(m, p1, map);
				
				maNew.put(id, mm);
				map.changeObject(x, y, m);
				mm.start();
			}
			
			else {
				i--;
			}
		}
	}
	
	/**
	 * Initialize a monster on the grid.
	 * 
	 * @param x. X-coordinate of the monster.
	 * @param y. Y-coordinate of the monster
	 * @param ma. The list of the monsters present on the grid.
	 */
	public void initMonster(int x, int y, MonstersArray ma, Player p) {
		p1 = p;
		HashMap<Integer, MonsterManager> maNew = ma.getMonstersTAB();
		
		Random r = new Random(); // generate an ID
		
		int id = r.nextInt(999999999);
		
		Monsters m = new Monsters(id, x, y, p1.getEtage());
		MonsterManager mm = new MonsterManager(m, p, map);
		
		
		maNew.put(id, mm);
		map.changeObject(x, y, m);
		mm.start();
		//return m;
	}
	
	public Door initDoor(int x, int y){
		d1 = new Door(x, y);
		map.changeObject(x, y, d1);
		return d1;
	}
	public Walls initWall(int i, int j) {
		Walls w = new Walls(i, j);
		map.changeObject(i, j, w);
		return w;
	}

	
	/*
	 * Initialize walls randomly on the grid
	 */
	public void initWalls() {
		int create;
		int direction;
		int longueur;
		Entities[][] objtab = map.getObjTab();
		
		//Create the walls around the map
		for(int i=0;i<Parameters.GRID_WIDTH;i++) {
			for(int j=0;j<Parameters.GRID_HEIGHT;j++) {
				if(i == 0 || i == Parameters.GRID_WIDTH-1 || j == 0 || j == Parameters.GRID_HEIGHT-1) {
					if(map.getEntity(i, j) == null) {
						Walls w = new Walls(i, j);
						map.changeObject(i, j, w);
					}
					
				}
			}
		}
		
		//Create random walls on the map
		for(int i=1;i<Parameters.GRID_WIDTH-1; i = i + 5) {
			for(int j=1;j<Parameters.GRID_HEIGHT-1;j = j + 5) {
				
				if(objtab[i][j] == null) {
					Random r = new Random();
					create = r.nextInt(100);
					
					if(create < 75) {
						direction = r.nextInt(4);
						Walls w = new Walls(i,j);
						objtab[i][j] = w;
						longueur = r.nextInt(8) + 3;
						
						if(direction == 0) {
							for (int l=1; l<longueur;l++) {
								if(i+l>1 && i+l<Parameters.GRID_WIDTH) {
									if(objtab[i+l][j] == null) {
										w = new Walls(i+l,j);
										objtab[i+l][j] = w;
									}
								}
							}
						}
						
						else if(direction == 1) {
							for (int l=1; l<longueur;l++) {
								if(i-l>1 && i-l<Parameters.GRID_WIDTH) {
									if(objtab[i-l][j] == null) {
										w = new Walls(i-l,j);
										objtab[i-l][j] = w;
									}
								}
							}
						}
						
						else if(direction == 2) {
							for (int l=1; l<longueur;l++) {
								if(j+l>1 && j+l<Parameters.GRID_HEIGHT-1) {
									if(objtab[i][j+l] == null) {
										w = new Walls(i,j+l);
										objtab[i][j+l] = w;
									}
								}
							}
						}
						
						else if(direction == 3) {
							for (int l=1; l<longueur;l++) {
								if(j-l>1 && j-l<Parameters.GRID_HEIGHT) {
									if(objtab[i][j-l] == null) {
										w = new Walls(i,j-l);
										objtab[i][j-l] = w;
									}
								}
							}
						}
					}
				}
			}
		}
		
		/* delete walls around the player */ 
		if((p1.getY() == 1) || (p1.getY() == 28)) {
			for(int i=0; i<5; i++) {
				for(int j=-4; j<5; j++) {
					if (i != 0 || j != 0) {
						if(p1.getY() == 1) {
							objtab[p1.getX()-j][p1.getY()+i] = null;
						}
						else if(p1.getY() == 28) {
							objtab[p1.getX()-j][p1.getY()-i] = null;
						}
					}
				}
			}
		}
		else if((p1.getX() == 1) || (p1.getX() == 38)) {
			for(int i=0; i<5; i++) {
				for(int j=-4; j<5; j++) {
					if (i != 0 || j != 0) {
						if(p1.getX() == 1) {
							objtab[p1.getX()+i][p1.getY()-j] = null;
						}
						else if(p1.getX() == 38) {
							objtab[p1.getX()-i][p1.getY()-j] = null;
						}
					}
				}
			}
		}
		
		/* delete walls around the door*/
		if((d1.getY() == 0) || (d1.getY() == 29)) {
			for(int i=1; i<5; i++) {
				for(int j=-4; j<5; j++) {
					if (i != 0 || j != 0) {
						if(d1.getY() == 0) {
							objtab[d1.getX()-j][d1.getY()+i] = null;
						}
						else if(d1.getY() == 29) {
							objtab[d1.getX()-j][d1.getY()-i] = null;
						}
					}
				}
			}
		}
		else if((d1.getX() == 0) || (d1.getX() == 39)) {
			for(int i=1; i<5; i++) {
				for(int j=-4; j<5; j++) {
					if (i != 0 || j != 0) {
						if(d1.getX() == 0) {
							objtab[d1.getX()+i][d1.getY()-j] = null;
						}
						else if(d1.getX() == 39) {
							objtab[d1.getX()-i][d1.getY()-j] = null;
						}
					}
				}
			}
		}
		
		//Get all the accessible case on the map by the player
		ArrayList<String> listCoordChecked = new ArrayList<String>();
		ArrayList<String> listCoordInProgress = new ArrayList<String>();
		ArrayList<String> listCoord = new ArrayList<String>();
		int x, y, i, j;
		
		listCoordInProgress.add(p1.getX() +";"+ p1.getY());
		
		while(!listCoordInProgress.isEmpty()) {
			for(String s : listCoordInProgress) {
				listCoordChecked.add(s);
				String[] sArray = s.split(";");
				x = Integer.parseInt(sArray[0]);
				y = Integer.parseInt(sArray[1]);
				
				if(objtab[x][y+1] == null) {
					j = y+1;
					if (!listCoordChecked.contains(x +";"+ j) && !listCoord.contains(x +";"+ j)) {
						listCoord.add(x +";"+ j);
					}
				}
				if(objtab[x][y-1] == null) {
					j = y-1;
					if (!listCoordChecked.contains(x +";"+ j) && !listCoord.contains(x +";"+ j)) {
						listCoord.add(x +";"+ j);
					}
				}
				if(objtab[x+1][y] == null) {
					i = x+1;
					if (!listCoordChecked.contains(i +";"+ y) && !listCoord.contains(i +";"+ y)) {
						listCoord.add(i +";"+ y);
					}
				}
				if(objtab[x-1][y] == null) {
					i = x-1;
					if (!listCoordChecked.contains(i +";"+ y) && !listCoord.contains(i +";"+ y)) {
						listCoord.add(i +";"+ y);
					}
				}
			}
			
			listCoordInProgress.clear();
			listCoordInProgress.addAll(listCoord);
			listCoord.clear();
		}
		
		//Create wall in inaccessible area
		for(i=0;i<Parameters.GRID_WIDTH;i++) {
			for(j=0;j<Parameters.GRID_HEIGHT;j++) {
				if(!listCoordChecked.contains(i+";"+j)) {
					if(objtab[i][j] == null) {
						Walls w = new Walls(i, j);
						map.changeObject(i, j, w);
					}
				}
			}
		}
		
		listCoordChecked.clear();
	}
	
	/**
	 * Allows to change the level.
	 * 
	 * @param entry. Direction from which the player passes through the door.
	 * @param ma. List of monsters.
	 * @param p. The player.
	 */
	public void nextLevel(String entry, MonstersArray ma, Player p) {
		p1 = p;
		map.resetMap();
		File f = new File("save.ser");
		f.delete();
		int x;
		switch (entry) {
			case "North":
				p1.setX(Parameters.GRID_WIDTH/2 - 1);
				p1.setY(Parameters.GRID_HEIGHT - 2);
				x = 0;
				break;
			case "South":
				p1.setX(Parameters.GRID_WIDTH/2 - 1);
				p1.setY(1);
				x = 1;
				break;
			case "East":
				p1.setX(1);
				p1.setY(Parameters.GRID_HEIGHT/2 - 1);
				x = 2;
				break;
			case "West":
				p1.setX(Parameters.GRID_WIDTH - 2);
				p1.setY(Parameters.GRID_HEIGHT/2 - 1);
				x = 3;
				break;
			default:
				p1.setX(Parameters.GRID_WIDTH/2 - 1);
				p1.setY(Parameters.GRID_HEIGHT-2);
				x = 1;
				break;
		}

		if(p1.getHealth()+(p1.getHealth()/3) > p1.getMaxHealth()) {
			p1.setHealth(p1.getMaxHealth());
		}
		
		else {
			p1.setHealth(p1.getHealth()+(p1.getHealth()/3));
		}
		
		map.changeObject(p1.getX(), p1.getY(), p1);
		
		Random r = new Random(); // generate an ID
		int id = r.nextInt(4);
		
		while (id == x) {
			id = r.nextInt(4);
		}
		
		switch (id) {
			case 0:
				d1.setX(Parameters.GRID_WIDTH/2 - 1);
				d1.setY(Parameters.GRID_HEIGHT - 1);
				break;
			case 1:
				d1.setX(Parameters.GRID_WIDTH/2 - 1);
				d1.setY(0);
				break;
			case 2:
				d1.setX(0);
				d1.setY(Parameters.GRID_HEIGHT/2 - 1);
				break;
			case 3:
				d1.setX(Parameters.GRID_WIDTH - 1);
				d1.setY(Parameters.GRID_HEIGHT/2 - 1);
				break;
			default:
				d1.setX(Parameters.GRID_WIDTH/2 - 1);
				d1.setY(0);
				break;
		}
		
		d1.setDoorOpen(false);
		map.changeObject(d1.getX(), d1.getY(), d1);
		p1.setEtage((p1.getEtage()+1));
		
		this.initWalls();
		this.initMonster(ma, p1);
		this.initChest();
		
		try {
			Save();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Used to save the game's progress.
	 */
	private void Save() throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(
			    new FileOutputStream("save.ser")
		);
		
		out.writeObject(map.getObjTab());
		out.flush();
		out.close();
	}

}
