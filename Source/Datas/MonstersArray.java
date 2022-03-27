package Datas;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import process.MonsterManager;

/*
 * The class that contains the array containing every monsters present on the map.
 */
public class MonstersArray {
	/*
	 * HashMap containing monsters, inserting his ID as the key will return the monster associated
	 */
	HashMap<Integer, MonsterManager> MonstersTAB = new HashMap<Integer, MonsterManager>(); // return monster associed to the ID
	
	public MonstersArray() {
		
	}

	public HashMap<Integer, MonsterManager> getMonstersTAB() {
		return MonstersTAB;
	}

	public void setMonstersTAB(HashMap<Integer, MonsterManager> monstersTAB) {
		MonstersTAB = monstersTAB;
	}

	public void removeMonsterTAB(int id) {
		MonstersTAB.remove(id);
	}
	
	public void resetMonsterTAB() {
		Iterator<Map.Entry<Integer, MonsterManager>> iter = MonstersTAB.entrySet().iterator();
		while(iter.hasNext()) {
			MonsterManager mm = (MonsterManager) iter.next().getValue();
			mm.setRun(false);
			mm = null;
		
		}
		MonstersTAB.clear();
	}
	
}
