package Init;

import Datas.MonstersArray;
import gui.MenuFrame;
import process.InitObjects;
import process.MapProcessing;
import process.Motor;

/*
 * The class that initialize every objects an pass as parameters when needed.
 */
public class Starter {

	private MonstersArray ma = new MonstersArray();
	private MapProcessing map = new MapProcessing();
	private InitObjects objs = new InitObjects(map);
	private Motor motor = new Motor(ma, map, objs);

	public Starter() {
		new MenuFrame(objs, ma, motor);
	}

}
