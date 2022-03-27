package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import Datas.Chest;
import Datas.Door;
import Datas.Entities;
import Datas.Monsters;
import Datas.Parameters;
import Datas.Player;
import Datas.SoundType;
import process.Motor;
import process.MotorPlayer;

/*
 * Initialize the different objects on the map such as the player, monsters, walls, ...
 */
public class MapPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Motor motor;
	private BufferedImage playerImage;
	private BufferedImage monsterImage;
	private BufferedImage wallImage;
	private BufferedImage doorImage;
	private BufferedImage chestImage; 
	private BufferedImage attackImage;
	
	private int widthBox;
	private int heightBox;
	private int playerDirection = MotorPlayer.RIGHT;
	
	private Clip clipDoor;
	private boolean soundDoorPlayed = false;
	private boolean isDoorClosed = true;
	private boolean isChestClosed = true;
	
	private Clip clipDamagePlayer;
	private Clip clipDamageMonster;

	
	public MapPanel(Motor motor) {
		this.setPreferredSize(new Dimension(Parameters.MAP_WIDTH, Parameters.MAP_HEIGHT));
		
		this.motor = motor;
		
		this.widthBox = motor.getMapProcessing().getWidth()/motor.getMapProcessing().getGridWidth();
		this.heightBox = motor.getMapProcessing().getHeight()/motor.getMapProcessing().getGridHeight();
		
		if(playerImage == null) {
			playerImage = loadImage("./src/images/heros_right.png");
        }
		
		if(monsterImage == null) {
			monsterImage = loadImage("./src/images/monster.png");
        }
		
		if(wallImage == null) {
			wallImage = loadImage("./src/images/wall.png");
        }
		
		if(doorImage == null) {
			doorImage = loadImage("./src/images/doorBot.png");
        }
		
		if(attackImage == null) {
			attackImage = loadImage("./src/images/attack_h.png");
		}
		
		if(chestImage == null) {
			chestImage = loadImage("./src/images/chest_close.png");
        }
		
		String soundName = "./src/sounds/door.wav";
		try {    
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			clipDoor = AudioSystem.getClip();
			clipDoor.open(audioInputStream);
			clipDoor.setFramePosition(0);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		
		soundName = "./src/sounds/damage_player.wav";
		try {    
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			clipDamagePlayer = AudioSystem.getClip();
			clipDamagePlayer.open(audioInputStream);
			clipDamagePlayer.setFramePosition(0);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		
		soundName = "./src/sounds/damage_monster.wav";
		try {    
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			clipDamageMonster = AudioSystem.getClip();
			clipDamageMonster.open(audioInputStream);
			clipDamageMonster.setFramePosition(0);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Used to load an image.
	 * 
	 * @param filename. URL of the file.
	 */
	private BufferedImage loadImage(String filename) {
        try {
        	return ImageIO.read(new File(filename));
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Entities[][] tab = motor.getMapProcessing().getObjTab();
		
		for(int i = 0 ; i < Parameters.GRID_WIDTH ; i++) {
			for(int j = 0 ; j < Parameters.GRID_HEIGHT ; j++) {
				if(tab[i][j] != null) {
					if(tab[i][j].getType().equals("player")) {
						int x = tab[i][j].getX();
						int y = tab[i][j].getY();
						
						String coord = motor.getMapProcessing().getCellsToCoords()[x][y];
						String[] tmp = coord.split(";");
						
						x = Integer.parseInt(tmp[0]);
						y = Integer.parseInt(tmp[1]);
						
						Player player = (Player) tab[i][j];
						if(player.getHasBeenAttacked()) {
							player.setHasBeenAttacked(false);

							playSound(SoundType.PLAYER_HIT_SOUND);
						}
						drawPlayer(g, x, y, player.getAttackdirection());

					}
					
					else if(tab[i][j].getType().equals("monster")) {
						int x = tab[i][j].getX();
						int y = tab[i][j].getY();
						
						String coord = motor.getMapProcessing().getCellsToCoords()[x][y];
						String[] tmp = coord.split(";");
						
						x = Integer.parseInt(tmp[0]);
						y = Integer.parseInt(tmp[1]);
						
						Monsters monster = (Monsters) tab[i][j];
						
						if(monster.getHasBeenAttacked()) {
							monster.setHasBeenAttacked(false);

							playSound(SoundType.MONSTER_HIT_SOUND);
						}
						
						drawMonster(g, x, y);
					}
					
					else if(tab[i][j].getType().equals("wall")) {
						int x = tab[i][j].getX();
						int y = tab[i][j].getY();
						String coord = motor.getMapProcessing().getCellsToCoords()[x][y];
						String[] tmp = coord.split(";");
						
						x = Integer.parseInt(tmp[0]);
						y = Integer.parseInt(tmp[1]);
						
						drawWalls(g, x, y);
					}
					
					else if(tab[i][j].getType().equals("door")) {
						int x = tab[i][j].getX();
						int y = tab[i][j].getY();
						
						Door door = (Door) tab[i][j];
						String coord = motor.getMapProcessing().getCellsToCoords()[x][y];
						String[] tmp = coord.split(";");
						
						x = Integer.parseInt(tmp[0]);
						y = Integer.parseInt(tmp[1]);
						
						if(door.isOpen() && !isDoorClosed) {
							if(!soundDoorPlayed) {
								if(i == Parameters.GRID_WIDTH/2 - 1) {
									if(j == Parameters.GRID_HEIGHT - 1) {
										doorImage = loadImage("./src/images/doorRight.png");
									}
									
									else {
										doorImage = loadImage("./src/images/doorLeft.png");
									}
								}
								
								else {
									if(j == Parameters.GRID_HEIGHT/2 - 1) {
										doorImage = loadImage("./src/images/doorTop.png");
									}
									
									else {
										doorImage = loadImage("./src/images/doorBot.png");
									}
								}
								
								playSound(SoundType.OPEN_DOOR);
								soundDoorPlayed = true;
								isDoorClosed = true;
							}
						}
						
						if(!door.isOpen() && isDoorClosed) {
							soundDoorPlayed = false;
							
							if(i == Parameters.GRID_WIDTH/2 - 1) {
								if(j == Parameters.GRID_HEIGHT - 1) {
									doorImage = loadImage("./src/images/doorTop.png");
								}
								
								else {
									doorImage = loadImage("./src/images/doorBot.png");
								}
							}
							
							else if (i == 0) {
								if(j == Parameters.GRID_HEIGHT/2 - 1) {
									doorImage = loadImage("./src/images/doorRight.png");
								}
								
								else {
									doorImage = loadImage("./src/images/doorLeft.png");
								}
							}
							
							isDoorClosed = false;
						}
						
						drawLevel(g, x, y);
					}
					
					else if(tab[i][j].getType().equals("chest")) {
						String coord = motor.getMapProcessing().getCellsToCoords()[i][j];
						String[] tmp = coord.split(";");
						
						int x = Integer.parseInt(tmp[0]);
						int y = Integer.parseInt(tmp[1]);
						
						Chest ch = (Chest) tab[i][j];
						
						if(ch.isOpen() && !isChestClosed) {
							chestImage = loadImage("./src/images/chest_open.png");
								
							isChestClosed = true;
						}
						
						if(!ch.isOpen() && isChestClosed) {
							chestImage = loadImage("./src/images/chest_close.png");							
							
							isChestClosed = false;
						}

						drawChest(g, x, y);
					}
					
				}
			}
		}
		
		ArrayList<Monsters> killedMonsters = new ArrayList<>();
		killedMonsters.addAll(motor.getMapProcessing().getKilledMonsters());
		
		for(Monsters monster : killedMonsters) {
			playSound(SoundType.MONSTER_DEATH_SOUND);
			motor.getMapProcessing().removeKilledMonster(monster);
		}
	}
	
	/*
	 * draw the player on the map
	 */
	public void drawPlayer(Graphics g, int x, int y, int attackdirection) {
		g.drawImage(playerImage, x, y, widthBox, heightBox, null);
		switch (attackdirection) {
		case 0: // gauche
			attackImage = loadImage("./src/images/attack_g.png");
			g.drawImage(attackImage, x-20-widthBox/2, y-widthBox/2+5, widthBox+10, heightBox+10, null);
			break;
		case 1: // droite
			attackImage = loadImage("./src/images/attack_d.png");
			g.drawImage(attackImage, x-widthBox/2+20, y-widthBox/2+5, widthBox+10, heightBox+10, null);
			break;
		case 2: // haut
			attackImage = loadImage("./src/images/attack_h.png");
			g.drawImage(attackImage, x+5-widthBox/2, y-widthBox/2-20, widthBox+10, heightBox+10, null);
			break;
		case 3: // bas
			attackImage = loadImage("./src/images/attack_b.png");
			g.drawImage(attackImage, x+5-widthBox/2, y+20-widthBox/2, widthBox+10, heightBox+10, null);
			break;
		default: // n'attaque pas
			break;
		}

	}
	
	/**
	 * Draw a monster on the map.
	 * 
	 * @param g. Object with which we will draw the monster.
	 * @param x. Coordinate in abscissa.
	 * @param y. Coordinate in ordinate.
	 */
	public void drawMonster(Graphics g, int x, int y) {
		g.drawImage(monsterImage, x, y, widthBox, heightBox, null);
	}
	
	/**
	 * Draw walls on the map.
	 * 
	 * @param g. Object with which we will draw the wall.
	 * @param x. Coordinate in abscissa.
	 * @param y. Coordinate in ordinate.
	 */
	public void drawWalls(Graphics g, int x, int y) {
		g.drawImage(wallImage, x, y, widthBox, heightBox, null);
	}
	
	/**
	 * Draw the level on the panel.
	 * 
	 * @param g. Object with which we will draw the level.
	 * @param x. Coordinate in abscissa.
	 * @param y. Coordinate in ordinate.
	 */
	public void drawLevel(Graphics g, int x, int y) {
		g.drawImage(doorImage, x, y, widthBox, heightBox, null);		
	}
	
	/**
	 * Draw walls on the chest.
	 * 
	 * @param g. Object with which we will draw the chest.
	 * @param x. Coordinate in abscissa.
	 * @param y. Coordinate in ordinate.
	 */
	public void drawChest(Graphics g, int x, int y) {
		g.drawImage(chestImage, x, y, widthBox, heightBox, null);
	}
	
	/**
	 * Draw the player according to its orientation.
	 * 
	 * @param direction. The direction of the player.
	 */
	public void playerDirection(int direction) {
		switch(direction) {
			case MotorPlayer.LEFT:
				if(playerDirection != direction) {
					playerImage = loadImage("./src/images/heros_left.png");
					playerDirection = direction;
				}
				break;
			case MotorPlayer.RIGHT:
				if(playerDirection != direction) {
					playerImage = loadImage("./src/images/heros_right.png");
					playerDirection = direction;
				}
				break;
			case MotorPlayer.UP:
				if(playerDirection != direction) {
					playerImage = loadImage("./src/images/heros_up.png");
					playerDirection = direction;
				}
				break;
			case MotorPlayer.DOWN:
				if(playerDirection != direction) {
					playerImage = loadImage("./src/images/heros_down.png");
					playerDirection = direction;
				}
				break;
		}
	}
	
	/**
	 * Play a sound.
	 * 
	 * @param type. Type of the sound that will be played.
	 */
	public void playSound(SoundType type) {
		switch(type) {
			case PLAYER_HIT_SOUND:
				clipDamagePlayer.start();
				clipDamagePlayer.setFramePosition(0);
				break;
			case PLAYER_DEATH_SOUND:
				break;
			case MONSTER_HIT_SOUND:
				clipDamageMonster.start();
				clipDamageMonster.setFramePosition(0);
				break;
			case MONSTER_DEATH_SOUND:
				clipDamageMonster.start();
				clipDamageMonster.setFramePosition(0);
				break;
			case OPEN_DOOR:
				clipDoor.start();
				clipDoor.setFramePosition(0);
				break;
			default:
				break;
		}
	}
	
}
