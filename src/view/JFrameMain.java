package view;

import java.awt.Toolkit;
import javax.swing.JFrame;

public class JFrameMain extends JFrame {

	private static JFrameMain frameMain = null;
	public static final int WIDTH_SCREEN = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT_SCREEN = Toolkit.getDefaultToolkit().getScreenSize().height;
	private JPanelMain jPanelMain;
	
	public static JFrameMain getInstance() {
		if (frameMain == null) {
			frameMain = new JFrameMain();
		}
		return frameMain;
	}

	public JFrameMain() {
		this.jPanelMain = new JPanelMain();
	}

	public void init() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1300 * WIDTH_SCREEN / 1920, 800 * HEIGHT_SCREEN / 1080);
		this.setLocationRelativeTo(null);
		this.setContentPane(jPanelMain);
		this.setVisible(true);
	}

	public void showPositions(double[][] positions) {
		jPanelMain.showPositions(positions);
	}

	public void showMenu() {
		jPanelMain.showMenu();
	}

}
