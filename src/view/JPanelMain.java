package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import controller.Controller;
import view.utils.Constants;

public class JPanelMain extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JLabel jLabelTitle;
	private JLabel jLabelAutors;
	private JLabel jLabelGraph;
	private JPanel jPanelContainerButton;
	private JPanel jPanelContainerButtonBack;
	private JPanel jPanelContainerGraph;
	private JButton jButtonStart;
	private JButton jButtonBack;
	private JButton jButtonGo;
	private JButton jButtonGoBack;
	private JLabel jLabelResult;
	private JFreeChart jFreeChartxy;
	private JFreeChart jFreeChartxy2D;
	private Object[] data;
	private int index;

	public JPanelMain() {
		super(new BorderLayout());
		this.index = 0;
		this.background = new ImageIcon(getClass().getResource("/res/background.jpg"));
		this.jLabelTitle = new JLabel("Caminatas Aleatorias: El problema de la Rana", JLabel.CENTER);
		this.jLabelAutors = new JLabel("", JLabel.CENTER);
		this.jPanelContainerButton = new JPanel(new GridBagLayout());
		this.jPanelContainerButtonBack = new JPanel(new GridBagLayout());
		this.jLabelGraph = new JLabel();
		this.jButtonStart = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/res/btnrana.png"))
				.getImage().getScaledInstance(450 * JFrameMain.WIDTH_SCREEN / 1920,
						450 * JFrameMain.HEIGHT_SCREEN / 1080, Image.SCALE_SMOOTH)));
		this.jButtonBack = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/res/btnback.png"))
				.getImage().getScaledInstance(50 * JFrameMain.WIDTH_SCREEN / 1920, 30 * JFrameMain.HEIGHT_SCREEN / 1080,
						Image.SCALE_SMOOTH)));
		this.jButtonGo = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/res/btnGo.png")).getImage()
				.getScaledInstance(50 * JFrameMain.WIDTH_SCREEN / 1920, 30 * JFrameMain.HEIGHT_SCREEN / 1080,
						Image.SCALE_SMOOTH)));
		this.jButtonGoBack = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/res/btn1D.png"))
				.getImage().getScaledInstance(50 * JFrameMain.WIDTH_SCREEN / 1920, 30 * JFrameMain.HEIGHT_SCREEN / 1080,
						Image.SCALE_SMOOTH)));
		this.jLabelResult = new JLabel("", JLabel.CENTER);

		this.jPanelContainerGraph = new JPanel(new GridBagLayout());

		init();
	}

	private void init() {
		this.setOpaque(false);
		this.jPanelContainerGraph.setOpaque(false);
		this.jPanelContainerButton.setOpaque(false);
		this.jPanelContainerButtonBack.setOpaque(false);
		this.jButtonBack.setActionCommand("back");
		this.jButtonGo.setActionCommand("goAhead");
		this.jButtonGoBack.setActionCommand("goBack");
		this.jButtonStart.setName("start");
		addComponents();
		animate();
	}

	private void animate() {
		new Thread(() -> {
			int close = 0;
			while (close == 0) {
				try {
					String text = Constants.AUTORS;
					for (int i = 0; i < text.length(); i++) {
						jLabelAutors.setText(jLabelAutors.getText() + text.charAt(i));
						Thread.sleep(100);
					}
					Thread.sleep(5000);
					jLabelAutors.setText("");
				} catch (InterruptedException e) {
				}
			}
		}).start();
	}

	private void addComponents() {
		configureLabels(jLabelTitle, Constants.FONT_APP, Constants.FONT_SIZE_TITLES, Font.BOLD, true);
		configureLabels(jLabelAutors, Constants.FONT_APP, Constants.FONT_SIZE_TEXT, Font.PLAIN, false);
		configureLabels(jLabelResult, Constants.FONT_APP, Constants.FONT_SIZE_TEXT, Font.PLAIN, false);
		configureButton(jButtonStart);
		configureButton(jButtonBack);
		configureButton(jButtonGo);
		configureButton(jButtonGoBack);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		jPanelContainerButton.add(jButtonStart, gbc);

		changeView(0);

	}

	public void goNext() {
		index = (++index) == 4 ? 1 : index;
		changeView(index);
	}

	public void goBack() {
		index = (--index) == 0 ? 3 : index;
		changeView(index);
	}

	private void configureButton(JButton jButton) {
		jButton.setContentAreaFilled(false);
		jButton.setBorderPainted(false);
		jButton.setFocusPainted(false);
		if (jButton.getName() == "start") {
			jButton.addMouseListener(Controller.getInstance());
		} else {
			jButton.addActionListener(JFrameMain.getInstance());
		}
	}

	private void configureLabels(JLabel jLabel, String fontApp, int fontSize, int style, boolean border) {
		jLabel.setFont(new Font(fontApp, style, fontSize));
		jLabel.setForeground(Color.WHITE);
		jLabel.setPreferredSize(new Dimension(0, 60));
		if (border) {
			jLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.WHITE));
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
		g.setColor(new Color(0, 0, 0, 0.71f));
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paint(g);
	}

	private void removeComponents() {
		for (int i = 0; i < this.getComponentCount(); i++) {
			this.getComponent(i).setVisible(false);
		}
		this.removeAll();
	}

	public void changeView(int value) {

		switch (value) {
		case 0:
			showMenu();
			break;
		case 1:
			showPositions();
			break;
		case 2:
			showPositions2D();
			break;
		case 3:

			break;
		}
		this.updateUI();
	}

	public void showPositions() {
		removeComponents();
		this.jPanelContainerButtonBack.setVisible(true);
		this.jLabelAutors.setVisible(true);
		this.jButtonGo.setVisible(true);
		this.jButtonGoBack.setVisible(true);
		this.jPanelContainerGraph.setVisible(true);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		this.jLabelGraph.setIcon(new ImageIcon(jFreeChartxy.createBufferedImage(1100 * JFrameMain.WIDTH_SCREEN / 1920,
				600 * JFrameMain.HEIGHT_SCREEN / 1080)));
		this.jLabelResult.setText(
				"La posicion final de la rana en 1.000.000 de saltos es: " + (int)((double[][]) data[0])[1][999999]);
		this.jPanelContainerGraph.add(jLabelGraph, gbc);
		gbc.fill = 1;
		gbc.gridy = 1;
		this.jPanelContainerGraph.add(jLabelResult, gbc);
		gbc.gridy = 0;
		gbc.fill = 0;
		gbc.weighty = 1;
		this.jPanelContainerButtonBack.add(jButtonBack, gbc);

		this.add(jPanelContainerButtonBack, BorderLayout.NORTH);
		this.add(jPanelContainerGraph, BorderLayout.CENTER);
		this.add(jLabelAutors, BorderLayout.SOUTH);
		this.add(jButtonGo, BorderLayout.LINE_END);
		this.add(jButtonGoBack, BorderLayout.LINE_START);
	}

	public void showPositions2D() {
		removeComponents();
		this.jPanelContainerButtonBack.setVisible(true);
		this.jLabelAutors.setVisible(true);
		this.jButtonGo.setVisible(true);
		this.jButtonGoBack.setVisible(true);
		this.jPanelContainerGraph.setVisible(true);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		this.jLabelGraph.setIcon(new ImageIcon(jFreeChartxy2D.createBufferedImage(1100 * JFrameMain.WIDTH_SCREEN / 1920,
				600 * JFrameMain.HEIGHT_SCREEN / 1080)));
		this.jLabelResult.setText("La posicion final de la rana en 1.000.000 de saltos es: " + "("
				+ (int)((double[][]) data[1])[0][999999] + "," + (int)((double[][]) data[1])[1][999999] + ")");
		this.jPanelContainerGraph.add(jLabelGraph, gbc);
		gbc.fill = 1;
		gbc.gridy = 1;
		this.jPanelContainerGraph.add(jLabelResult, gbc);
		gbc.gridy = 0;
		gbc.fill = 0;
		gbc.weighty = 1;
		this.jPanelContainerButtonBack.add(jButtonBack, gbc);

		this.add(jPanelContainerButtonBack, BorderLayout.NORTH);
		this.add(jPanelContainerGraph, BorderLayout.CENTER);
		this.add(jLabelAutors, BorderLayout.SOUTH);
		this.add(jButtonGo, BorderLayout.LINE_END);
		this.add(jButtonGoBack, BorderLayout.LINE_START);
	}

	private void generateGraph() {
		double[][] positions = (double[][]) this.data[0];
		DefaultXYDataset dataset = new DefaultXYDataset();
		fillDataSet(dataset, positions);
		jFreeChartxy = ChartFactory.createXYLineChart("", "", "", dataset, PlotOrientation.VERTICAL, true, false,
				false);
	}

	private void generateGraph2D() {
		double[][] positions = (double[][]) this.data[1];
		DefaultXYDataset dataset = new DefaultXYDataset();
		fillDataSet(dataset, positions);
		jFreeChartxy2D = ChartFactory.createXYLineChart("", "", "", dataset, PlotOrientation.VERTICAL, true, false,
				false);

	}

	private void fillDataSet(DefaultXYDataset dataset, double[][] positions) {
		dataset.addSeries("", positions);
	}

	public void showMenu() {
		removeComponents();
		this.jLabelTitle.setVisible(true);
		this.jPanelContainerButton.setVisible(true);
		this.jLabelAutors.setVisible(true);
		this.add(jLabelTitle, BorderLayout.NORTH);
		this.add(jPanelContainerButton, BorderLayout.CENTER);
		this.add(jLabelAutors, BorderLayout.SOUTH);
	}

	public void remove1D() {
		this.jPanelContainerButtonBack.setVisible(false);
		this.jLabelAutors.setVisible(false);
		this.jPanelContainerGraph.setVisible(false);
		this.jLabelGraph.setVisible(false);
		this.jLabelResult.setVisible(false);

	}

	public void show1D() {
		this.jPanelContainerButtonBack.setVisible(true);
		this.jLabelAutors.setVisible(true);
		this.jPanelContainerGraph.setVisible(true);
		this.jLabelGraph.setVisible(true);
		this.jLabelResult.setVisible(true);

	}

	public void remove2D() {
		removeComponents();

	}

	/**
	 * @param data
	 */
	public void showGraphics(Object[] data) {
		this.data = data;
		generateGraph();
		generateGraph2D();
		goNext();
	}

}
