package ca.mcgill.cs.stg.jetuml.framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

final class ActionZoomWheel implements MouseWheelListener {
	private EditorFrame thisFrame;
	private ActionListener zoomOut;
	private ActionListener zoomIn;
	public ActionZoomWheel(EditorFrame thisFrame,ActionListener zoomOut, ActionListener zoomIn ){
		this.zoomIn = zoomIn;
		this.zoomOut = zoomOut;
		this.thisFrame = thisFrame;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.isControlDown()) {
            if (e.getWheelRotation() < 0) {
            	((GraphFrame) thisFrame.aTabbedPane.getSelectedComponent()).getGraphPanel().changeZoom(-1);
            } else {
            	((GraphFrame) thisFrame.aTabbedPane.getSelectedComponent()).getGraphPanel().changeZoom(-1);
            }
        }
		// TODO Auto-generated method stub
		
	}
}