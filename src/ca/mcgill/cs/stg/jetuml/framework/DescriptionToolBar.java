package ca.mcgill.cs.stg.jetuml.framework;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import ca.mcgill.cs.stg.jetuml.graph.GraphElement;
import ca.mcgill.cs.stg.jetuml.graph.Node;

public class DescriptionToolBar extends JPanel {

	private static DescriptionToolBar instance = null;
	private static JPanel descriptionPanel;
	private static final int TOP_SPACING = 20;
	private static final int SPACING = 5;
	private static final int FONT_SIZE = 14;
	private static final int LABEL_HEIGHT = 20;
	private static final int LABEL_WIDTH = 60;
	private static JTextPane textPaneName = new JTextPane();
	private static JTextPane textPaneAttributes = new JTextPane();
	private static JTextPane textPaneMethods = new JTextPane();

	private DescriptionToolBar() {
		super();
		
		descriptionPanel = new JPanel();
		descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
		
		addVerticalSpacing(TOP_SPACING);
		
		addLabel("Name:");
		addVerticalSpacing(SPACING);
		addTextPane(textPaneName, new Dimension(200, 50));
		addVerticalSpacing(SPACING);
		
		addLabel("Attributes:");
		addVerticalSpacing(SPACING);
		addTextPane(textPaneAttributes, new Dimension(200, 300));
		addVerticalSpacing(SPACING);
		
		addLabel("Methods:");
		addVerticalSpacing(SPACING);
		addTextPane(textPaneMethods, new Dimension(200, 300));
		
		setEditableTextPanes(false);
		
	}
	
	private static void setFontSize(JLabel lbl, int fontSize) {
		lbl.setFont(new Font(lbl.getFont().getFontName(), lbl.getFont().getStyle(), fontSize));
	}
	
	private static void addVerticalSpacing(int spacing) {
		Component space = Box.createRigidArea(new Dimension(0, spacing));
		descriptionPanel.add(space);
	}
	
	private static void addLabel(String text){
		JLabel lbl = new JLabel(text);
		setFontSize(lbl, FONT_SIZE);
		descriptionPanel.add(lbl);
	}
	
	private static void addTextPane(JTextPane tp, Dimension dimension) {
		tp.setPreferredSize(dimension);
		JScrollPane stp = new JScrollPane(tp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		descriptionPanel.add(stp, BorderLayout.WEST);
	}
	
	private static void setEditableTextPanes(boolean editable) {
		textPaneName.setEditable(editable);
		textPaneAttributes.setEditable(editable);
		textPaneMethods.setEditable(editable);
	}
	
	public static DescriptionToolBar getInstance() {
		if (instance == null) {
			instance = new DescriptionToolBar();
			instance.add(descriptionPanel);
		}
		return instance;
	}
	
	public static void updateDescription(GraphElement element) {
		
		HashMap<String, String> description = PropertySheet.getValidAttributes(element); 
		System.out.println(description.toString());
		
		description.put("Name", description.get("Name").replace("|", "\n"));
		description.put("Attributes", description.get("Attributes").replace("|", "\n"));
		description.put("Methods", description.get("Methods").replace("|", "\n"));
		
		System.out.println(description.toString());
		
		textPaneName.setText(description.get("Name"));
		textPaneAttributes.setText(description.get("Attributes"));
		textPaneMethods.setText(description.get("Methods"));
		setEditableTextPanes(true);
	}
	
	public static void hideDescription() {
		if (instance == null) {
			return;
		}
		instance.setVisible(false);
	}
	
	public static void clearDescription() {
		textPaneName.setText("");
		textPaneAttributes.setText("");
		textPaneMethods.setText("");
		setEditableTextPanes(false);
	}
	
	
}
