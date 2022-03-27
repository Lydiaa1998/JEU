package process;


import javax.swing.SwingWorker;

import gui.MapGui;
/*
 * The class that execute the swingworker thread to refresh the GUI.
 */
public class ThreadProcessing extends SwingWorker<Integer, Integer>{

	private MapGui frame;
	private boolean run = true;
	public ThreadProcessing(MapGui frame) {
			this.frame = frame;
	}

	protected Integer doInBackground() throws Exception {
		while(run) {
			frame.update();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}


}
