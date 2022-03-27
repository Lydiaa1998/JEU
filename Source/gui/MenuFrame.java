package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Datas.MonstersArray;
import Datas.Parameters;
import process.GameManagement;
import process.InitObjects;
import process.Motor;
import process.ThreadProcessing;

/**
 * Class that initialize and display the Frame of the application
 */
public class MenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MenuPanel menuPanel;
	private InstructionPanel instructionPanel;
	private ButtonCustom playButton, instructionButton, returnButton, exitButton;
	private MapGui gameFrame;
	private Motor motor;
	private GameManagement gameManagement;
	private ThreadProcessing thread;

	/**
	 * Main disposition elements
	 */
	public MenuFrame(InitObjects objs, MonstersArray ma, Motor motor) {
		this.motor = motor;
		this.gameManagement = new GameManagement(objs, ma, motor);

		this.playButton = new ButtonCustom("Jouer", true);
		this.playButton.addActionListener(new ButtonListener());
		this.instructionButton = new ButtonCustom("Instruction", true);
		this.instructionButton.addActionListener(new ButtonListener());
		this.returnButton = new ButtonCustom("Retour", false);
		this.returnButton.addActionListener(new ButtonListener());
		this.exitButton = new ButtonCustom("Quitter", true);
		this.exitButton.addActionListener(new ExitListener());

		this.menuPanel = new MenuPanel(playButton, instructionButton, exitButton);
		this.instructionPanel = new InstructionPanel(returnButton);

		this.setTitle("ENDLESS DUNGEON");
		this.setSize(Parameters.WINDOW_WIDTH, Parameters.WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		this.setContentPane(menuPanel);
	}

	/**
	 * Used to switch the current panel on the menu.
	 * 
	 * @param isInstruction. Tell us if the panel is the menu panel or if it's the instruction panel.
	 */
	private void switchMenuPanel(boolean isInstruction) {
		if(!isInstruction) {
			menuPanel.repaint();
			this.setContentPane(menuPanel);
		}

		else {
			instructionPanel.repaint();
			this.setContentPane(instructionPanel);
		}

		this.revalidate();
	}

	/**
	 * Used to start the game and close the menu.
	 */
	public void startGame() {
		gameFrame = new MapGui(motor, this);
		ThreadProcessing thread = new ThreadProcessing(gameFrame);

		thread.execute();
		this.dispose();
	}

	/**
	 * Used to close the application.
	 */
	public void exit() {
		this.dispose();
		System.exit(1);
	}

	/**
	 * Used to initialized the game.
	 */
	public void initGame() {
		gameFrame = new MapGui(motor, this);
		thread = new ThreadProcessing(gameFrame);
		thread.execute();
		this.dispose();

	}

	public MapGui getGameFrame() {
		return gameFrame;
	}

	/**
	 * Call the "game's frame"
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e)  {
			Object source = e.getSource();

			if(source == playButton) {
				if(gameFrame != null) {
					gameFrame = null;
					gameManagement.resetGame();
				}
				else {
					gameManagement.initGame();
					startGame();
				}

			}

			else if(source == instructionButton) {
				switchMenuPanel(true);
			}

			else if(source == returnButton) {
				switchMenuPanel(false);
			}
		}
	}

	/**
	 * This class will execute the close function when clicking.
	 */
	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e)  {
			exit();
		}
	}

}
