package de.sfk.spicycurry.ui.NATSample;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface INatExample {
	
		public static final String BASE_PACKAGE = "org.eclipse.nebula.widgets.nattable.examples.examples";
		
		public static final String BASE_PATH = "/org/eclipse/nebula/widgets/nattable/examples/examples";
		public String getName();
		
		public String getShortDescription();
		
		public String getDescription();
		
		public Control createExampleControl(Composite parent);
		
		public void onStart();
		
		public void onStop();
		
	
}
