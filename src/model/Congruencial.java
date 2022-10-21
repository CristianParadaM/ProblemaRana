package model;

import java.util.ArrayList;

public class Congruencial {
	private int k;
	private int c;
	private int g;
	private int t;
	private int numberOfJumps;

	public Congruencial(int k, int c, int g) {
		this.k = k;
		this.c = c;
		this.g = g;
	}

	public Congruencial(int t, int g) {
		this.t = t;
		this.g = g;
	}

	public ArrayList<Object[]> congruenciaLineal() {
		return generateTable(1 + 2 * k, c, (int) Math.pow(2, g));
	}

	public ArrayList<Object[]> congruenciaMultiplicativo() {
		return generateTable(8 * t + 3, 0, (int) Math.pow(2, g));
	}

	public double[] getRi() {
		ArrayList<Object[]> table = t != 0 ? congruenciaMultiplicativo() : congruenciaLineal();
		double[] numbers = new double[table.size()];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = (double) table.get(i)[2];
		}
		return numbers;
	}

	/**
	 * @return the numberOfJumps
	 */
	public int getNumberOfJumps() {
		return numberOfJumps;
	}

	private ArrayList<Object[]> generateTable(int a, int c, int m) {
		ArrayList<Object[]> table = new ArrayList<>();
		double x_i = Math.abs(m - (c + a) - 1);
		for (int i = 1; i <= (c != 0 ? m : m / 4); i++) {
			Double[] row = new Double[3];
			row[0] = (double) i;
			row[1] = (double) ((a * x_i + c) % m);
			row[2] = row[1] / (m - 1);
			if (row[2] != 1 && row[2] != 0) {
				table.add(row);
			}
			x_i = row[1];
		}
		return table;
	}

	public double[][] calculatePositions() {
		double[] ri = getRi();
		double[][] aux = new double[2][1000000];
		int count = 0;
		for (int i = 0; i < 1000000; i++) {
			if (ri[i] > 0.5) {
				aux[0][i] = i + 1;
				aux[1][i] = ++count;
			} else {
				aux[0][i] = i + 1;
				aux[1][i] = --count;
			}
		}
		return aux;
	}

	public double[][] calculatePositions2D() {
		double[] ri = getRi();
		double[][] aux = new double[2][1000000];
		int countX = 0;
		int countY = 0;
		numberOfJumps = 0;
		for (int i = 0; i < 1000000; i++) {
			if (ri[i] < 0.25) {
				aux[0][i] = countX;
				aux[1][i] = countY++;
			} else if (ri[i] < 0.50) {
				aux[0][i] = countX;
				aux[1][i] = countY--;
			} else if (ri[i] < 0.75) {
				aux[0][i] = countX++;
				aux[1][i] = countY;
			} else {
				aux[0][i] = countX--;
				aux[1][i] = countY;
			}
			if (countX == 250 && countY == 300) {
				numberOfJumps = i;
				break;
			}

		}
		return aux;
	}
//
//	public double[] calculatePositions3D() {
//		(45,23,17)
//	}

}
