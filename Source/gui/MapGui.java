package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


import Datas.Parameters;
import Exceptions.DeathException;
import process.Motor;
import process.MotorPlayer;

/*
 * Initialize and display the main window with the different objects on like such as the player sprite
 */
public class MapGui extends JFrame implements KeyListener{

	private static final long serialVersionUID = 1L;
	private static int WINDOW_WIDTH = Parameters.WINDOW_WIDTH;
	private static int WINDOW_HEIGHT = Parameters.WINDOW_HEIGHT;
	private Motor motor;
	private MenuFrame mf;
	int posx;
	int posy;

	private JPanel panel;
	private MapPanel mapPanel;
	private HudGui hudPanel;

	/*
	 * Constructor, it needs the object motor to initialize itself
	 * Initialize a keylistener to retrieve user inputs.
	 */
	public MapGui(Motor motor, MenuFrame mf) {
		super("EndlessDungeon");
		this.motor = motor;
		this.mf = mf;
		addKeyListener(this);
		initLayout();
	}

	public JPanel getPanel() {
		return panel;
	}

	/*
	 * Initialize the main JFrame and JPanel
	 */
	private void initLayout() {
		panel = new JPanel();
		mapPanel = new MapPanel(motor);
		hudPanel = new HudGui(motor);

		this.setVisible(true);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(panel);

		initPanel();
	}

	/*
	 * Initialize the main JPanel
	 */
	private void initPanel() {
		panel.setLayout(new BorderLayout());

		panel.add(mapPanel, BorderLayout.CENTER);
		panel.add(hudPanel, BorderLayout.SOUTH);

		update();
	}

	/*
	 * Repaint and revalidate HUD and GUI
	 */
	public void update(){
		mapPanel.repaint();
		mapPanel.revalidate();

		hudPanel.repaint();
		hudPanel.revalidate();

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e){
		try {
			if(e.getKeyChar() == 'z'){
				motor.getMotorPlayer().move(MotorPlayer.UP);
				mapPanel.playerDirection(MotorPlayer.UP);
			}

			if(e.getKeyChar() == 's'){
				motor.getMotorPlayer().move(MotorPlayer.DOWN);
				mapPanel.playerDirection(MotorPlayer.DOWN);
			}

			if(e.getKeyChar() == 'q'){
				motor.getMotorPlayer().move(MotorPlayer.LEFT);
				mapPanel.playerDirection(MotorPlayer.LEFT);
			}

			if(e.getKeyChar() == 'd'){
				motor.getMotorPlayer().move(MotorPlayer.RIGHT);
				mapPanel.playerDirection(MotorPlayer.RIGHT);
			}

			if(e.getKeyCode() == KeyEvent.VK_UP){
				motor.getMotorPlayer().attack(MotorPlayer.UP);
			}

			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				motor.getMotorPlayer().attack(MotorPlayer.DOWN);
			}

			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				motor.getMotorPlayer().attack(MotorPlayer.LEFT);
			}

			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				motor.getMotorPlayer().attack(MotorPlayer.RIGHT);
			}
		}
		
		catch (DeathException e1) {
			reset();
		}

		update();
	}

	@Override
	public void keyReleased(KeyEvent e){

	}

	/**
	 * Exits the game and displays the menu.
	 */
	public void reset() {
		mf.getGameFrame().dispose();
		mf.setVisible(true);
	}

}
