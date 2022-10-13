package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import model.Congruencial;
import view.JFrameMain;

public class Controller implements MouseListener {

	private static Controller controller = null;
	private JFrameMain view;

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public void startApp() {
		view = JFrameMain.getInstance();
		view.init();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName() == "start") {
			double[][] positions = new Congruencial(e.getX() % 2 != 0 ? e.getX() + 1 : e.getX(), e.getY() % 2 == 0 ? e.getY() + 1 : e.getY(), 20)
					.calculatePositions();
			view.showPositions(positions);
		}else {
			view.showMenu();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getComponent().getName() == "start") {
			((JButton) e.getComponent()).setBorderPainted(true);
			((JButton) e.getComponent()).setBorder(BorderFactory.createDashedBorder(Color.WHITE, 5f, 10f, 5f, true));
			((JButton) e.getComponent()).setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getComponent().getName() == "start") {
			((JButton) e.getComponent()).setBorderPainted(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public static void main(String[] args) {
		Controller.getInstance().startApp();
	}
}
