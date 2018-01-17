package com.bullraider.kapture.views;

import java.awt.Event;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class MyStartup implements IStartup {

	@Override
	public void earlyStartup() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		Display display = workbench.getDisplay();
		display.asyncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if (window != null) {
					display.addFilter(SWT.KeyUp, new Listener() {
						@Override
						public void handleEvent(org.eclipse.swt.widgets.Event event) {
							System.out.println("Something azmaing"+event.character+" "+ event.keyCode+" "+event.keyLocation);
						}
					}); 
				}
			}
		});

	}

}