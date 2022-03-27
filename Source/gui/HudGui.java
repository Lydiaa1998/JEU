package gui;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import Datas.Entities;
import Datas.Parameters;
import Datas.Player;
import process.Motor;

/*
 * Class that initialize and display the HUD
 */
public class HudGui extends JPanel {

	private static final long serialVersionUID = 1L;

	private Motor motor;
	private Player player;

	/*
	 * This constructor needs the object initialized from the motor class to initialize itself.
	 * It will launch the method to get the player object in order to be able to display his current health
	 */
	public HudGui(Motor motor) {
		this.setPreferredSize(new Dimension(Parameters.MAP_WIDTH, Parameters.HUD_HEIGHT));
		this.setBackground(new Color(150, 150, 255));
		
		this.motor = motor;
		if(player == null) {
			GetPlayerObject();
		}
	}
	/*
	 * The method that retrieve the player object and put it in its attribute
	 */
	private void GetPlayerObject() {
		Entities[][] tab = motor.getMapProcessing().getObjTab();
		
		for(int i = 0 ; i < Parameters.GRID_WIDTH ; i++) {
			for(int j = 0 ; j < Parameters.GRID_HEIGHT ; j++) {
				if(tab[i][j] != null && tab[i][j].getType().equals("player")) {
					player = (Player) tab[i][j];
				}
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawHealth(g);
		drawExperience(g);
		drawLevelInfos(g);
	}
	
	/*
	 * Display the current health of the player
	 */
	public void drawHealth(Graphics g) {
		double hp = player.getHealth();
		
		if(hp > 0) {
			double maxhp = player.getMaxHealth();
			int x = Parameters.WINDOW_WIDTH / 20;
			int width = 150;

			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("Arial", Font.PLAIN, 22));
			g.drawString("HP :", x, (int)(Parameters.HUD_HEIGHT/1.7));
			
			x = Parameters.WINDOW_WIDTH / 8;
			
			g.setColor(new Color(255, 255, 255));
			g.fillRect(x, Parameters.HUD_HEIGHT/4, width, Parameters.HUD_HEIGHT/2);
			
			width =(int) Math.round(hp * width / maxhp);
			
			g.setColor(new Color(50, 255, 50));
			g.fillRect(x, Parameters.HUD_HEIGHT/4, width, Parameters.HUD_HEIGHT/2);
			
			if(hp < 10) {
				x += 40;
			}
			
			else if (hp < 100) {
				x += 30;
			}

			else {
				x += 20;
			}
			
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString(Math.round(hp) + "/" + maxhp, x, (int)(Parameters.HUD_HEIGHT/1.7));
		}
	}
	
	/*
	 * Display the current number of experience of the player
	 */
	public void drawExperience(Graphics g) {
		double xp = player.getXp();
		
		double maxxp = player.getMaxXp();
		int x = Parameters.WINDOW_WIDTH / 3;
		int width = 150;

		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font("Arial", Font.PLAIN, 22));
		g.drawString("XP :", x, (int)(Parameters.HUD_HEIGHT/1.7));
		
		x = Parameters.WINDOW_WIDTH / 3 + 70;
		
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Level : " + player.getLevel(), x, Parameters.HUD_HEIGHT/3);
		
		g.setColor(new Color(255, 255, 255));
		g.fillRect(x, (int)(Parameters.HUD_HEIGHT/2.5), width, Parameters.HUD_HEIGHT/2);
		
		width =(int) Math.round(xp * width / maxxp);
		
		g.setColor(new Color(5, 255, 5));
		g.fillRect(x, (int)(Parameters.HUD_HEIGHT/2.5), width, Parameters.HUD_HEIGHT/2);
		
		if(xp < 10) {
			x += 35;
		}
		
		else if (xp < 100) {
			x += 25;
		}

		else {
			x += 15;
		}
		
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString(xp + "/" + maxxp, x, (int)(Parameters.HUD_HEIGHT/1.35));
	}
	
	/**
	 * Display informations about the level.
	 */
	public void drawLevelInfos(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman",Font.PLAIN,30));
		g.drawString("Etage N°"+player.getEtage(), 600, 50);
	}
	
}
