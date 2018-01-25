package com.bullraider.kapture.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.bullraider.kapture.*;;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = KaptureActivator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.SHOW_ANIMATION, true);
		store.setDefault(PreferenceConstants.PLAY_SOUND, false);
		store.setDefault(PreferenceConstants.P_STRING,
				"Default value");
	}

}
