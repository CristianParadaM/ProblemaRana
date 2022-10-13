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

	private ImageIcon background;
	private JLabel jLabelTitle;
	private JLabel jLabelAutors;
	private JLabel jLabelGraph;
	private JPanel jPanelContainerButton;
	private JPanel jPanelContainerButtonBack;
	private JPanel jPanelContainerGraph;
	private JButton jButtonStart;
	private JButton jButtonBack;
	private JLabel jLabelResult;

	public JPanelMain() {
		super(new BorderLayout());
		this.background = new ImageIcon(getClass().getResource("/res/background.jpg"));
		this.jLabelTitle = new JLabel("Caminatas Aleatorias: El problema de la Rana", JLabel.CENTER);
		this.jLabelAutors = new JLabel("", JLabel.CENTER);
		this.jPanelContainerButton = new JPanel(new GridBagLayout());
		this.jPanelContainerButtonBack = new JPanel(new GridBagLayout());
		this.jButtonStart = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/res/btnrana.png"))
				.getImage().getScaledInstance(450 * JFrameMain.WIDTH_SCREEN / 1920,
						450 * JFrameMain.HEIGHT_SCREEN / 1080, Image.SCALE_SMOOTH)));
		this.jButtonBack = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/res/btnback.png"))
				.getImage().getScaledInstance(50 * JFrameMain.WIDTH_SCREEN / 1920, 30 * JFrameMain.HEIGHT_SCREEN / 1080,
						Image.SCALE_SMOOTH)));
		this.jLabelResult = new JLabel("", JLabel.CENTER);
		init();
	}

	private void init() {
		this.setOpaque(false);
		this.jPanelContainerButton.setOpaque(false);
		this.jPanelContainerButtonBack.setOpaque(false);
		this.jButtonBack.setName("back");
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

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		jPanelContainerButton.add(jButtonStart, gbc);

		this.add(jLabelTitle, BorderLayout.NORTH);
		this.add(jPanelContainerButton, BorderLayout.CENTER);
		this.add(jLabelAutors, BorderLayout.SOUTH);
	}

	private void configureButton(JButton jButton) {
		jButton.setContentAreaFilled(false);
		jButton.setBorderPainted(false);
		jButton.setFocusPainted(false);
		jButton.addMouseListener(Controller.getInstance());
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

	public void showPositions(double[][] positions) {
		removeComponents();
		this.jPanelContainerButtonBack.setVisible(true);
		this.jLabelAutors.setVisible(true);
		this.jPanelContainerGraph = new JPanel(new GridBagLayout());
		this.jPanelContainerGraph.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		generateGraph(positions);
		this.jPanelContainerGraph.add(jLabelGraph, gbc);
		gbc.fill =1;
		gbc.gridy = 1;
		this.jPanelContainerGraph.add(jLabelResult, gbc);
		gbc.gridy = 0;
		gbc.fill =0;
		gbc.weighty = 1;
		this.jPanelContainerButtonBack.add(jButtonBack, gbc);

		this.add(jPanelContainerButtonBack, BorderLayout.NORTH);
		this.add(jPanelContainerGraph, BorderLayout.CENTER);
		this.add(jLabelAutors, BorderLayout.SOUTH);
	}

	private void generateGraph(double[][] positions) {
		DefaultXYDataset dataset = new DefaultXYDataset();
		fillDataSet(dataset, positions);
		JFreeChart jFreeChartxy = ChartFactory.createXYLineChart("", "", "", dataset, PlotOrientation.VERTICAL, true,
				false, false);
		BufferedImage img = jFreeChartxy.createBufferedImage(1200 * JFrameMain.WIDTH_SCREEN / 1920,
				600 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.jLabelGraph = new JLabel(new ImageIcon(img));
		this.jLabelResult.setText("La posicion final de la rana en 1.000.000 de saltos es: "+(int)positions[1][999999]);
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
}
