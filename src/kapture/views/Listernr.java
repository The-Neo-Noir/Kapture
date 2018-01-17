package kapture.views;

import org.eclipse.swt.widgets.Event;

public class Listernr implements org.eclipse.swt.widgets.Listener{

	@Override
	public void handleEvent(Event arg0) {
		System.out.println(arg0.keyCode);
	}

}
