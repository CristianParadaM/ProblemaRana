/**
 * 
 */
package model;

import javax.swing.JFrame;

import org.math.plot.Plot3DPanel;
import org.math.plot.canvas.Plot3DCanvas;

/**
 * Clase que representa
 * 
 * @author Julian Huertas Henao 19/10/2022
 */
public class Model3D {

	public static void main(String[] args) {
		double[] x = new double[] { 1, 2, 3, 4, 5, 6 };
		double[] y = new double[] { 1, 2, 3, 4, 5, 6 };
		double[] z = new double[] { 1, 2, 3, 4, 5, 6 };
		Plot3DPanel panel = new Plot3DPanel("NORTH");
		panel.addLinePlot("", x, y, z);
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(frame);
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}
