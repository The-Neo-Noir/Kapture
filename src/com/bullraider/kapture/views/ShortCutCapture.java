package com.bullraider.kapture.views;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.Event;

import org.eclipse.ui.part.*;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import org.eclipse.core.commands.IExecutionListener;

import org.eclipse.core.commands.NotHandledException;

import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.ParseException;

import org.eclipse.ui.*;

import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.internal.keys.WorkbenchKeyboard;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;

import java.util.List;

import javax.inject.Inject;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

@SuppressWarnings("restriction")
public class ShortCutCapture extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bullraider.kapture.views.ShortCutCapture";

	@Inject
	IWorkbench workbench;
	private Text text;

	@Override

	public void createPartControl(Composite parent) {
		text = new Text(parent, SWT.NONE);

		GridData data = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		
		Font boldFont = new Font(text.getDisplay(), new FontData("Arial", 45, SWT.BOLD | SWT.CENTER));
		text.setFont(boldFont);

		text.setEditable(false);

		// data.horizontalIndent=SWT.CENTER;
		text.setLayoutData(data);
		parent.setLayout(new GridLayout(1, false));

		// IBindingService srv = (IBindingService)
		// PlatformUI.getWorkbench().getService(IBindingService.class);
		// Binding[] bindings = srv.getBindings();
		// BindingService bindingService = (BindingService)
		// PlatformUI.getWorkbench().getAdapter(IBindingService.class);
		ICommandService command = PlatformUI.getWorkbench().getAdapter(ICommandService.class);
		command.addExecutionListener(new IExecutionListener() {

			@Override
			public void preExecute(String arg0, ExecutionEvent arg1) {
				Event e = (Event) arg1.getTrigger();

				if (e != null) {
					List possibleKeyList = WorkbenchKeyboard.generatePossibleKeyStrokes(e);
					if (possibleKeyList.size() > 0) {
						String format = "";
						try {
							format = possibleKeyList.get(0).toString();
							format = KeySequence.getInstance(possibleKeyList.get(0).toString()).format();
						} catch (ParseException e1) {
							// TODO: Remove SYstem to logger
							System.out.println(e1.getMessage());
						}
						//data.widthHint = text.getBounds().width + 10;
						
						text.setText(format);
						Point size = text.computeSize(SWT.DEFAULT, SWT.DEFAULT);
						
						text.setText(format);
						System.out.println(size+" ");
						text.setBounds(12, 12, size.x,45);
						data.widthHint = size.x + 10;
						
					}
				}
			}

			@Override
			public void postExecuteSuccess(String arg0, Object arg1) {
				// NO op
			}

			@Override
			public void postExecuteFailure(String arg0, ExecutionException arg1) {
				// NO op
			}

			@Override
			public void notHandled(String arg0, NotHandledException arg1) {
				// NO op
			}

		});

		// srv.getPerfectMatch(new )
		// srv.addBindingManagerListener(new IBindingManagerListener() {
		//
		// @Override
		// public void bindingManagerChanged(BindingManagerEvent arg0) {
		// // TODO Auto-generated method stub
		// arg0.
		//
		// }
		// });
		// srv.
		// srv.setKeyFilterEnabled(false);
		// Display display = workbench.getDisplay();
		//
		// workbench.getActiveWorkbenchWindow().getShell().addKeyListener(new
		// org.eclipse.swt.events.KeyListener() {
		//
		// @Override
		// public void keyReleased(org.eclipse.swt.events.KeyEvent arg0) {
		// int accelerator = SWTKeySupport.convertEventToUnmodifiedAccelerator(arg0);
		// KeySequence sequence = KeySequence.getInstance((KeyStroke)arg0.getSource());
		// System.out.println("sdf");
		// }
		//
		// @Override
		// public void keyPressed(org.eclipse.swt.events.KeyEvent arg0) {
		// int accelerator = SWTKeySupport.convertEventToUnmodifiedAccelerator(arg0);
		// KeySequence sequence = KeySequence.getInstance((KeyStroke)arg0.getSource());
		// System.out.println("sdf");
		// }
		// });
		//
		// display.asyncExec(new Runnable() {
		//
		// public void run() {
		// System.out.println(this);
		// IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		// if (window != null) {
		//
		// display.addFilter(SWT.KeyUp , new Listener() {
		// String s="";
		// @Override
		// public void handleEvent(org.eclipse.swt.widgets.Event event) {
		// List generatePossibleKeyStrokes =
		// WorkbenchKeyboard.generatePossibleKeyStrokes(event);
		//
		// System.out.println("Something azmaing Threadid: "+ event.keyCode+"
		// "+event.keyLocation+" "+event.character+" "+event.stateMask);
		// char c =event.character;
		// if((event.stateMask & SWT.CTRL )!=0) {
		// s="⌘ +"+c;
		// }
		// if((event.stateMask & SWT.CTRL)!=0) {
		// s="⌃ + "+c;
		// }
		// if((event.stateMask & SWT.COMMAND)!=0) {
		// s="⌘ + "+c;
		// }
		// if((event.stateMask & SWT.SHIFT)!=0) {
		// s="⇧ + "+c;
		// }
		// if((event.stateMask & SWT.ALT)!=0) {
		// s="⌥ + "+c;
		// }
		// if((event.stateMask & SWT.ALT)!=0 && (event.stateMask & SWT.COMMAND)!=0) {
		// s="⌥ + ⌘ +"+c;
		// }
		//
		// // text.setText(generatePossibleKeyStrokes.toString());
		//
		// //for(int i=0;i<255;i++) {
		// //text.setBack(display.getSystemColor(44));
		// //}
		// }
		// });
		// }
		// }
		// });
		// System.out.println(getSite().getKeyBindingService().getScopes());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
}