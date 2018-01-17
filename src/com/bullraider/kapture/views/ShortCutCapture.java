package com.bullraider.kapture.views;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
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
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;

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
	private Text text;

	@Override
	public void createPartControl(Composite parent) {
		text = new Text(parent, SWT.NONE);
		parent.setLayout( new GridLayout( 1, false ) );
		GridData data = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		Font boldFont = new Font( text.getDisplay(), new FontData( "Arial", 45, SWT.BOLD |SWT.CENTER) );
		text.setFont( boldFont );
		text.setText("Bold Label");
		data.heightHint=95;
		text.setLayoutData(data);
		
	
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}
