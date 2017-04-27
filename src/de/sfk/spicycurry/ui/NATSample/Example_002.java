/**
 * 
 */
package de.sfk.spicycurry.ui.NATSample;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ReflectiveColumnPropertyAccessor;
// import org.eclipse.nebula.widgets.nattable.examples.AbstractNatExample;
// import org.eclipse.nebula.widgets.nattable.examples.fixtures.Person;
// import org.eclipse.nebula.widgets.nattable.examples.runner.StandaloneNatExampleRunner;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultColumnHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultCornerDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultRowHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.CornerLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.GridLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.RowHeaderLayer;
import org.eclipse.nebula.widgets.nattable.hideshow.ColumnHideShowLayer;
import org.eclipse.nebula.widgets.nattable.layer.AbstractLayerTransform;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.reorder.ColumnReorderLayer;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.util.ObjectUtils;
import org.eclipse.nebula.widgets.nattable.viewport.ViewportLayer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class Example_002 extends AbstractNatExample {
	
		private IDataProvider bodyDataProvider;
		private String[] propertyNames;
		private BodyLayerStack bodyLayer;
		private Map<String, String> propertyToLabels;
		
		public static void run(){
				StandaloneRunner.run(800, 800, new Example_002());
		}
		
		public Control createExampleControl(Composite parent) {
			
			// data
			bodyDataProvider = setupBodyDataProvider();
			// header
			DefaultColumnHeaderDataProvider colHeaderDataProvider = new DefaultColumnHeaderDataProvider(propertyNames, propertyToLabels);
			// row
			DefaultRowHeaderDataProvider rowHeaderDataProvider = new DefaultRowHeaderDataProvider(bodyDataProvider);
			
			// bodylayer
			bodyLayer = new BodyLayerStack(bodyDataProvider);
			// column header layer
			ColumnHeaderLayerStack columnHeaderLayer = new ColumnHeaderLayerStack(colHeaderDataProvider);
			// row header layer
			RowHeaderLayerStack rowHeaderLayer = new RowHeaderLayerStack(rowHeaderDataProvider);
			
			DefaultCornerDataProvider cornerDataProvider = new DefaultCornerDataProvider(colHeaderDataProvider, rowHeaderDataProvider);
			
			CornerLayer cornerLayer = new CornerLayer(new DataLayer(cornerDataProvider), rowHeaderLayer, columnHeaderLayer);
			
			GridLayer gridLayer = new GridLayer (bodyLayer, columnHeaderLayer, rowHeaderLayer, cornerLayer);
			
			// the table
			NatTable natTable = new NatTable(parent, SWT.BORDER | SWT.FULL_SELECTION, gridLayer);
				
			this.log("data created");
			return natTable;
		}
		private IDataProvider setupBodyDataProvider() {
			final List<Person> people = Arrays.asList(
					new Person(100, "Mickey Mouse", new Date(1000000)), 
					new Person(110, "Batman", new Date(2000000)), 
					new Person(120, "Bender", new Date(3000000)), 
					new Person(130, "Cartman", new Date(4000000)), 
					new Person(140, "Dogbert", new Date(5000000)));
			
			propertyToLabels = new HashMap<String, String>();
			propertyToLabels.put("id", "ID");
			propertyToLabels.put("lastName", "Name");
			propertyToLabels.put("birthday", "Geburtstag");
			propertyNames = new String[] { "id", "lastName", "birthday" };
			
			return new ListDataProvider<Person>(people, new ReflectiveColumnPropertyAccessor<Person>(propertyNames));
		}
		
		public class BodyLayerStack extends AbstractLayerTransform {
			private SelectionLayer selectionLayer;
			
			public BodyLayerStack(IDataProvider dataProvider) {
				DataLayer bodyDataLayer = new DataLayer(dataProvider);
				ColumnReorderLayer columnReorderLayer = new ColumnReorderLayer(bodyDataLayer);
				ColumnHideShowLayer columnHideShowLayer = new ColumnHideShowLayer(columnReorderLayer);
				selectionLayer = new SelectionLayer(columnHideShowLayer);
				ViewportLayer viewportLayer = new ViewportLayer(selectionLayer);
				setUnderlyingLayer(viewportLayer);
			}
			public SelectionLayer getSelectionLayer() {
				return selectionLayer;
			}
		}
		public class ColumnHeaderLayerStack extends AbstractLayerTransform {
			
			public ColumnHeaderLayerStack(IDataProvider dataProvider) {
				DataLayer dataLayer = new DataLayer(dataProvider);
				ColumnHeaderLayer colHeaderLayer = new ColumnHeaderLayer(dataLayer, bodyLayer, bodyLayer.getSelectionLayer());
				setUnderlyingLayer(colHeaderLayer);
			}
		}
		public class RowHeaderLayerStack extends AbstractLayerTransform {
			
			public RowHeaderLayerStack(IDataProvider dataProvider) {
				DataLayer dataLayer = new DataLayer(dataProvider, 50, 20);
				RowHeaderLayer rowHeaderLayer = new RowHeaderLayer(dataLayer, bodyLayer, bodyLayer.getSelectionLayer());
				setUnderlyingLayer(rowHeaderLayer);
			}
		}
	}

