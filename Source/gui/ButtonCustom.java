package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * This class is used to define a JButton containing mouse events.
 */
public class ButtonCustom extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private Color color;
	
	public ButtonCustom(String nom, boolean isWhite) {
		super(nom);
		
		this.setBorder(null);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setFont(new Font("Comic sans MS", Font.BOLD, 40));
		this.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 0) , 5));
		
		if(isWhite) {
			this.setForeground(Color.WHITE);
		}
		
		else {
			this.setForeground(Color.BLACK);
		}
		
		this.addMouseListener(this);
	}
	
	/**
	 * When the user has his mouse on it, the button will be surrounded by the white or black color.
	 */
	public void mouseEntered(MouseEvent e) {
		this.setBorder(BorderFactory.createLineBorder(color, 5));
	}

	/**
	 * When the user removes the mouse from the JButton, the outline will disappear.
	 */
	public void mouseExited(MouseEvent e) {
		this.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 0) , 5));
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}
