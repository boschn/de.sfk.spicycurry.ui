package de.sfk.spicycurry.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import de.sfk.spicycurry.ui.NATSample.Example_001;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.FillLayout;

public class Application implements Runnable {

	protected Shell shlSpicycurry;
	protected FeatureTable featureTable;
	protected Composite comFeatures;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Application window = new Application();
			window.run ();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * run
	 */
	public void run(){
		   
			 this.open();
	}
	/**
	 * open the window.
	 */
	public void open() {
		// create display
		Display display = Display.getDefault();
		
		// create the default contents
		createContents();
		
		// create the featureTable
		featureTable = new FeatureTable(this.comFeatures);
		featureTable.createContents();
		// open 
		shlSpicycurry.open();
		
		// wait
		while (!shlSpicycurry.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSpicycurry = new Shell();
		shlSpicycurry.setImage(SWTResourceManager.getImage(Application.class, "/de/sfk/spicycurry/images/spicyheatmapIcon.png"));
		shlSpicycurry.setLayout(new BorderLayout(0, 0));
		
		CTabFolder tabFolder = new CTabFolder(shlSpicycurry, SWT.BORDER);
		tabFolder.setLayoutData(BorderLayout.CENTER);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmNewItem = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		tbtmNewItem.setImage(SWTResourceManager.getImage(Application.class, "/de/sfk/spicycurry/images/jira.PNG"));
		tbtmNewItem.setText("Features");
		
		comFeatures = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(comFeatures);
		comFeatures.setLayout(new FillLayout(SWT.HORIZONTAL));

		CoolBar coolBar = new CoolBar(shlSpicycurry, SWT.FLAT);
		coolBar.setLayoutData(BorderLayout.SOUTH);
		
		Menu menu = new Menu(shlSpicycurry, SWT.BAR);
		shlSpicycurry.setMenuBar(menu);
		
		MenuItem mntmFile_1 = new MenuItem(menu, SWT.CASCADE);
		mntmFile_1.setText("File");
		
		Menu menu_1 = new Menu(mntmFile_1);
		mntmFile_1.setMenu(menu_1);
		
		MenuItem mntmQuit = new MenuItem(menu_1, SWT.NONE);
		mntmQuit.setText("Quit");

	}
}
