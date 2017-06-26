package ca.mcgill.cs.stg.jetuml.framework;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DescriptionToolBar extends JPanel {

	private static DescriptionToolBar instance = null;
	private static JPanel descriptionPanel;
	private static final int WIDTH = 40;
	private static final int SPACING = 5;
	private static final int FONT_SIZE = 14;

	private DescriptionToolBar() {
		super();
		
		descriptionPanel = new JPanel();
		descriptionPanel.setLocation(SPACING, SPACING);
		
		JLabel lbl = new JLabel();
		
		lbl.setText("Description");
		lbl.setFont(new Font(lbl.getFont().getFontName(), lbl.getFont().getStyle(), FONT_SIZE));
		descriptionPanel.add(lbl);
		
	}
	
	public static DescriptionToolBar getInstance() {
		if (instance == null) {
			instance = new DescriptionToolBar();
			
			instance.setLayout(new BorderLayout());
			instance.add(descriptionPanel, BorderLayout.NORTH);
		}
		return instance;
	}
	
	
}
