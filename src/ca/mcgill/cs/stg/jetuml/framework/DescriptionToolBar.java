package ca.mcgill.cs.stg.jetuml.framework;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import ca.mcgill.cs.stg.jetuml.graph.ClassNode;
import ca.mcgill.cs.stg.jetuml.graph.GraphElement;
import ca.mcgill.cs.stg.jetuml.graph.InterfaceNode;
import ca.mcgill.cs.stg.jetuml.graph.PackageNode;

public class DescriptionToolBar extends JPanel {

	private static DescriptionToolBar instance = null;
	private static JPanel descriptionPanel;
	private static final int TOP_SPACING = 20;
	private static final int SPACING = 5;
	private static final int FONT_SIZE = 14;
	private static JTextPane textPaneName = new JTextPane();
	private static JTextPane textPane1 = new JTextPane();
	private static JTextPane textPane2 = new JTextPane();
	private static JLabel labelName = new JLabel("Name");
	private static JLabel label1 = new JLabel();
	private static JLabel label2 = new JLabel();
	private static boolean active = false;

	private DescriptionToolBar() {
		super();

		descriptionPanel = new JPanel();
		descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));

		addVerticalSpacing(TOP_SPACING);

		addLabel(labelName);
		addVerticalSpacing(SPACING);
		addTextPane(textPaneName, new Dimension(200, 50));
		addVerticalSpacing(SPACING);

		addLabel(label1);
		addVerticalSpacing(SPACING);
		addTextPane(textPane1, new Dimension(200, 300));
		addVerticalSpacing(SPACING);

		addLabel(label2);
		addVerticalSpacing(SPACING);
		addTextPane(textPane2, new Dimension(200, 300));

		setEditableTextPanes(false);

	}

	private static void setFontSize(JLabel lbl, int fontSize) {
		lbl.setFont(new Font(lbl.getFont().getFontName(), lbl.getFont().getStyle(), fontSize));
	}

	private static void addVerticalSpacing(int spacing) {
		Component space = Box.createRigidArea(new Dimension(0, spacing));
		descriptionPanel.add(space);
	}

	private static void addLabel(JLabel lbl) {
		setFontSize(lbl, FONT_SIZE);
		descriptionPanel.add(lbl);
	}

	private static void addTextPane(JTextPane tp, Dimension dimension) {
		tp.setPreferredSize(dimension);
		JScrollPane stp = new JScrollPane(tp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		descriptionPanel.add(stp, BorderLayout.WEST);
	}

	private static void setEditableTextPanes(boolean editable) {
		textPaneName.setEditable(editable);
		textPane1.setEditable(editable);
		textPane2.setEditable(editable);
	}
	
	private static void setVisibleChildren(boolean b) {
		labelName.setVisible(b);
		label1.setVisible(b);
		label2.setVisible(b);
		textPaneName.setVisible(b);
		textPane1.setVisible(b);
		textPane2.setVisible(b);
	}

	public static DescriptionToolBar getInstance() {
		if (instance == null) {
			instance = new DescriptionToolBar();
			setVisibleChildren(false);
			instance.add(descriptionPanel);
		}
		return instance;
	}

	public static void updateDescription(GraphElement element) {
		
		if (!active) {
			return;
		}
		
		HashMap<String, String> description = PropertySheet.getValidAttributes(element);
		
		setVisibleChildren(false);
				
		switch(element.getClass().getSimpleName()) {
		
		case("ClassNode") :
			
			description.put("Attributes", description.get("Attributes").replace("|", "\n"));
			description.put("Methods", description.get("Methods").replace("|", "\n"));
			label1.setText("Attributes");
			textPane1.setText(description.get("Attributes"));
			label2.setText("Methods");
			textPane2.setText(description.get("Methods"));
			
			label2.setVisible(true);
			textPane2.setVisible(true);
			
			break;
		
		case("InterfaceNode") :
			
			description.put("Methods", description.get("Methods").replace("|", "\n"));
			label1.setText("Methods");
			textPane1.setText(description.get("Methods"));
		
			break;
			
		case("PackageNode") :
			
			description.put("Contents", description.get("Contents").replace("|", "\n"));
			label1.setText("Contents");
			textPane1.setText(description.get("Contents"));
		
			break;
			
		default :
			hideDescription();
			return;
		}
		
		description.put("Name", description.get("Name").replace("|", "\n"));
		textPaneName.setText(description.get("Name"));
		
		labelName.setVisible(true);
		textPaneName.setVisible(true);
		label1.setVisible(true);
		textPane1.setVisible(true);

		unhideDescription();
		
		setEditableTextPanes(false);
	}

	public static void hideDescription() {
		if (!active) {
			return;
		}
		if (instance == null) {
			return;
		}
		instance.setVisible(false);
	}
	
	public static void unhideDescription() {
		if (!active) {
			return;
		}
		if (instance == null) {
			return;
		}
		instance.setVisible(true);
	}

	public static void clearDescription() {

		textPaneName.setText("");
		textPane1.setText("");
		textPane2.setText("");
		
		setVisibleChildren(false);
		
		setEditableTextPanes(false);
		
		hideDescription();
	}

	public static void setActive(boolean b) {
		active = b;
	}

}
