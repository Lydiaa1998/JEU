package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that initialize and display the Menu Panel
 */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Image img;
	
	public MenuPanel(ButtonCustom play, ButtonCustom instruction, ButtonCustom exit) {
		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(50, 50, 50, 50);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		JLabel title = new JLabel("EndlessDungeon");
		title.setFont(new Font("Comic sans MS", Font.BOLD, 80));
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment((int) JPanel.CENTER_ALIGNMENT);
		this.add(title, gbc);
		
		gbc.gridy++;
		this.add(play, gbc);
		gbc.gridy++;
		this.add(instruction, gbc);
		gbc.gridy++;
		this.add(exit, gbc);
		
		try {
			img = ImageIO.read(new File("./src/images/menu.png"));
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		super.paintComponent(g2d);

		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
}
