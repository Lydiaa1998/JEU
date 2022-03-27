package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
 * Class that initialize and display the Instruction Panel
 */
public class InstructionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel titlePanel, actionPanel;
	private Image img;
	
	public InstructionPanel(ButtonCustom returnButton) {
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		this.titlePanel = new JPanel();
		this.titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.titlePanel.setBackground(Color.WHITE);
		this.actionPanel = new JPanel();
		this.actionPanel.setLayout(new GridBagLayout());
		this.actionPanel.setBackground(Color.WHITE);
		
		JLabel title = new JLabel("Instructions");
		title.setFont(new Font("Comic sans MS", Font.BOLD, 80));
		title.setHorizontalAlignment((int) JPanel.CENTER_ALIGNMENT);
		titlePanel.add(title);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(15, 50, 15, 50);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		actionPanel.add(returnButton, gbc);
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(actionPanel, BorderLayout.SOUTH);
		
		try {
			img = ImageIO.read(new File("./src/images/Commandes.png"));
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
