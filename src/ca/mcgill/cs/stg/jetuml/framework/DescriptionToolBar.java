package ca.mcgill.cs.stg.jetuml.framework;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class DescriptionToolBar extends JPanel {

	private static DescriptionToolBar instance = null;
	private static JPanel descriptionPanel = new JPanel(new VerticalLayout());
	private static final int WIDTH = 40;
	private static final int SPACING = 5;
	private static final int FONT_SIZE = 14;

	private DescriptionToolBar() {
		super();
		this.add(descriptionPanel);
	}
	
	public static DescriptionToolBar getInstance() {
		if (instance == null) {
			instance = new DescriptionToolBar();
		}
		return instance;
	}
	
	
}
