/**
 * 
 */
package model;

import javax.swing.JFrame;

import org.math.plot.Plot3DPanel;

/**
 * Clase que representa
 * 
 * @author Julian Huertas Henao 19/10/2022
 */
public class Model3D {

	public static void main(String[] args) {
		double[] x = new double[] { 1, 80, 3, 14, 58, 69 };
		double[] y = new double[] { 91, 21, 387, 41, 5, 16 };
		double[] z = new double[] { 11, 27, 43, 64, 55, 6 };
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
