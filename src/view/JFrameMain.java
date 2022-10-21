package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class JFrameMain extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public void init() {
		this.jPanelMain = new JPanelMain();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1300 * WIDTH_SCREEN / 1920, 800 * HEIGHT_SCREEN / 1080);
		this.setLocationRelativeTo(null);
		this.setContentPane(jPanelMain);
		this.setVisible(true);

	}

	public void showMenu() {
		jPanelMain.showMenu();
	}

	public void showGraphics(Object[] data) {
		jPanelMain.showGraphics(data);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "goAhead":
			jPanelMain.goNext();
			break;
		case "goBack":
			jPanelMain.goBack();
			break;
		case "back":
			jPanelMain.changeView(0);
			break;
		default:
			break;
		}
	}

}
