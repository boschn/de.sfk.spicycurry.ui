package de.sfk.spicycurry.ui.NATSample;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class Example_001 extends AbstractNatExample {

	public static void run() throws Exception {
		StandaloneRunner.run(800, 600, new Example_001());
	}
	
	@Override
	public String getDescription() {
		return
				"The easiest NatTable instance to create (no arguments, other than the parent composite) builds this default example " +
				"table. A lot of functionality is available in it to try out. Here are some things you can do:\n" +
				"\n" +
				"* RESIZE COLUMNS/ROWS by clicking on a column/row boundary in the column/row header and dragging it.\n" +
				"* AUTO-RESIZE COLUMNS/ROWS by double-clicking on a column/row boundary.\n" +
				"\n" +
				"* REORDER COLUMNS by clicking on a column header and dragging it.\n" +
				"\n" +
				"* SELECT A CELL by clicking on it.\n" +
				"* SELECT A REGION OF CELLS by dragging.\n" +
				"* SELECT A COLUMN/ROW by clicking on a column/row header.\n" +
				"* ADD TO SELECTION using the ctrl and shift modifiers.\n" +
				"\n" +
				"* EDIT A SELECTED CELL by typing F2 or edit directly by typing alphanumeric content; if multiple cells with the same " +
				"editor type are selected, you can edit all of them at once.\n" +
				"\n" +
				"* FIND DATA in the body area of the table with ctrl-f.\n" +
				"* COPY SELECTED CELLS into the clipboard with ctrl-c.\n" +
				"* EXPORT AS EXCEL with ctrl-e.\n" +
				"* PRINT with ctrl-p.";
	}
	
	public Control createExampleControl(Composite parent) {
		return new NatTable(parent, SWT.BORDER | SWT.FILL);
	}

}
