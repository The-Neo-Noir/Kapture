package com.bullraider.kapture.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import com.bullraider.kapture.KaptureActivator;

public class KapturePreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public KapturePreferencePage() {
		super(GRID);
		setPreferenceStore(KaptureActivator.getDefault().getPreferenceStore());
		//setDescription("A demonstration of a preference page implementation");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		
		addField(
			new BooleanFieldEditor(
				PreferenceConstants.PLAY_SOUND,
				"&Play Sound",
				getFieldEditorParent()));
		addField(
				new BooleanFieldEditor(
					PreferenceConstants.SHOW_ANIMATION,
					"&Animate",
					getFieldEditorParent()));


//		addField(new RadioGroupFieldEditor(
//				PreferenceConstants.P_CHOICE,
//			"An example of a multiple-choice preference",
//			1,
//			new String[][] { { "&Play sound", "sound" }, {
//				"S&how Animation", "animation" }
//		}, getFieldEditorParent()));
//		addField(
//			new StringFieldEditor(PreferenceConstants.P_STRING, "A &text preference:", getFieldEditorParent()));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}