package com.bullraider.kapture.views;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import javax.inject.Inject;
import javax.swing.text.LabelView;

import org.eclipse.jface.viewers.StyledString;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ShortCutCapture extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "kapture.views.CaptureView";

	@Inject IWorkbench workbench;
	
	private TableViewer viewer;
	private StyledString textViewer;
	private Action action1;
	private Action action2;
	private Action doubleClickAction;

	private StyledString styledString;

	private TextViewer textViewer2;

	private LabelView labelView;

	private Label label;

	private Text text;
	 

	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}
		@Override
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		@Override
		public Image getImage(Object obj) {
			return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	@Override
	public void createPartControl(Composite parent) {
		text = new Text(parent, SWT.SINGLE | SWT.BORDER);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		text.setText("Some Text");
		text.setSize(122, 1222);
		data.widthHint = 350;
		text.setLayoutData(data);
		
		
		
		//textViewer2 = new TextViewer(parent, SWT.SINGLE);
		//textViewer2.setEditable(false);
		//textViewer2.pro
		//viewer.setContentProvider(ArrayContentProvider.getInstance());
		//viewer.setInput(new String[] { "One", "Two", "Three" });
		//viewer.setLabelProvider(new ViewLabelProvider());

		//viewer= new 
		// Create the help context id for the viewer's control
		//workbench.getHelpSystem().setHelp(viewer.getControl(), "Kapture.viewer");
		//getSite().setSelectionProvider(viewer);
	
	}


	@Override
	public void setFocus() {
		//viewer.getControl().setFocus();
		textViewer2.getControl().setFocus();
		
	}
}
