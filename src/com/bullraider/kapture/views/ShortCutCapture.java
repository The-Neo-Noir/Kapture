package com.bullraider.kapture.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import org.eclipse.ui.part.*;

import com.bullraider.kapture.KaptureActivator;
import com.bullraider.kapture.preferences.PreferenceConstants;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import org.eclipse.core.commands.IExecutionListener;

import org.eclipse.core.commands.NotHandledException;

import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.*;

import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.internal.keys.WorkbenchKeyboard;

import org.eclipse.swt.SWT;

import java.util.List;
import javax.inject.Inject;

@SuppressWarnings("restriction")
public class ShortCutCapture extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bullraider.kapture.views.ShortCutCapture";

	@Inject
	IWorkbench workbench;
	private TextViewer outputViewer;
	
	private boolean showAnimation=true;
	private boolean playSound;
	

	public ShortCutCapture() {
		KaptureActivator.getDefault().getPreferenceStore()
		.addPropertyChangeListener(new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty() == PreferenceConstants.PLAY_SOUND) {
					String value = event.getNewValue().toString();
					playSound=Boolean.valueOf(event.getNewValue().toString());
					System.out.println(value);
					// do something with the new value
				}
				if (event.getProperty() == PreferenceConstants.SHOW_ANIMATION) {
					String value = event.getNewValue().toString();
					showAnimation=Boolean.valueOf(event.getNewValue().toString());
					System.out.println(value);
					// do something with the new value
				}
			}
		});
	}

	@Override
	public void createPartControl(Composite parent) {
		  outputViewer=new TextViewer(parent, SWT.SINGLE | SWT.CENTER);
		  parent.setLayout(new GridLayout(1, false));
		  outputViewer.getTextWidget().setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		  //outputViewer.getTextWidget().setFont(JFaceResources.getFont(JFaceResources.TEXT_FONT));
		  Font boldFont = new Font(workbench.getDisplay(), new FontData("Arial", 42, SWT.BOLD | SWT.CENTER));
		  outputViewer.getTextWidget().setFont(boldFont);
		  outputViewer.setEditable(false);
		  
		  Document document = new Document();
		  outputViewer.setDocument(document);
		  
		  document.set("                ");
		  
		  ICommandService command = PlatformUI.getWorkbench().getAdapter(ICommandService.class);
			
			command.addExecutionListener(new IExecutionListener() {
	
				@Override
				public void preExecute(String arg0, ExecutionEvent arg1) {
					Event e = (Event) arg1.getTrigger();
					if (e != null) {
						List possibleKeyList = WorkbenchKeyboard.generatePossibleKeyStrokes(e);
						if (possibleKeyList.size() > 0) {
							String format = findFormattedKey(possibleKeyList);
						
							document.set(format);
							if(playSound)
								workbench.getDisplay().beep();
						}
					}
				}

				@Override
				public void notHandled(String arg0, NotHandledException arg1) {
				}

				@Override
				public void postExecuteFailure(String arg0, ExecutionException arg1) {
				}

				@Override
				public void postExecuteSuccess(String arg0, Object arg1) {
				}
			});
		  
	}
	
	private String findFormattedKey(List possibleKeyList) {
		String format = "";
		try {
			format = possibleKeyList.get(0).toString();
			format = KeySequence.getInstance(possibleKeyList.get(0).toString()).format();
		} catch (ParseException e1) {
			// TODO: Remove SYstem to logger
			System.out.println(e1.getMessage());
		}
		return format;
	}
	@Override public void setFocus() {}
}