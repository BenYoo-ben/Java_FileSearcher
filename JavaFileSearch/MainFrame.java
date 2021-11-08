import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

class MainFrame extends JFrame {

	private static final long serialVersionUID = 2;
	private EventHandler eh;

	MainFrame(double resol, EventHandler eh) {
		this.eh = eh;
		startScreen(resol);

		MainSearchPanel msp = new MainSearchPanel(this.eh);

		this.add(msp);

		this.setVisible(true);
	}

	private void startScreen(double size) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		//arrange to middle of screen + set size based on screen resolution
		this.setSize((int) (width * size), (int) (height * size) / 2);
		this.setLocation((int) (width / 2 - (width * size) / 2), (int) (height / 2 - (height * size) / 4));

		eh.getMainFrame(this);
		this.addWindowListener(eh);
		this.setLayout(new GridLayout(1, 1));

		this.setVisible(true);
		this.setTitle("Java GUI based FileSearch");
	}
}