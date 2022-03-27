package process;

import Datas.MonstersArray;


/*
 * The main motor class that does general tasks for the game like getting each grid cell coordinates.
 */
public class Motor{
	
	private MapProcessing mp;
	private MotorPlayer motorPlayer;
	private MonstersArray ma;
	
	public Motor(MonstersArray ma, MapProcessing mp, InitObjects objs) {
		this.mp = mp;
		this.ma = ma;
		this.motorPlayer = new MotorPlayer(mp, ma, objs);
	}
	
	public MapProcessing getMapProcessing() {
		return mp;
	}
	
	public MotorPlayer getMotorPlayer() {
		return motorPlayer;
	}

	public MapProcessing getMp() {
		return mp;
	}

	public void setMp(MapProcessing mp) {
		this.mp = mp;
	}

	public void setMotorPlayer(MotorPlayer motorPlayer) {
		this.motorPlayer = motorPlayer;
	}

	public MonstersArray getMa() {
		return ma;
	}

	public void setMa(MonstersArray ma) {
		this.ma = ma;
	}
	
	
	

}
