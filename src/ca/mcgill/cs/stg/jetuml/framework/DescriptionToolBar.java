package ca.mcgill.cs.stg.jetuml.framework;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import ca.mcgill.cs.stg.jetuml.graph.GraphElement;
import ca.mcgill.cs.stg.jetuml.graph.Node;

public class DescriptionToolBar extends JPanel {

	private static DescriptionToolBar instance = null;
	private static JPanel descriptionPanel;
	private static final int WIDTH = 40;
	private static final int SPACING = 5;
	private static final int FONT_SIZE = 14;
	private static JTextPane textPaneName;
	private static JTextPane textPaneDescription;
	private static JTextPane textPaneMethods;

	private DescriptionToolBar() {
		super();
		
		descriptionPanel = new JPanel();
		descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
		
		JLabel lblDescription = new JLabel("Description");
		setFontSize(lblDescription, FONT_SIZE);
		descriptionPanel.add(lblDescription);
		
		JLabel lblName = new JLabel("Name");
		setFontSize(lblName, FONT_SIZE);
		descriptionPanel.add(lblName);
		
	}
	
	private static void setFontSize(JLabel lbl, int fontSize) {
		lbl.setFont(new Font(lbl.getFont().getFontName(), lbl.getFont().getStyle(), fontSize));
	}
	
	public static DescriptionToolBar getInstance() {
		if (instance == null) {
			instance = new DescriptionToolBar();
			
			instance.add(descriptionPanel, BorderLayout.NORTH);
		}
		return instance;
	}
	
	public static void udpateDescription(GraphElement element) {
		
	}
	
	public static void hideDescription() {
		if (instance == null) {
			return;
		}
		instance.hide();
	}
	
	public static void clearDescription() {
		
	}
	
	
}
