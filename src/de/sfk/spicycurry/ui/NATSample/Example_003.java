package de.sfk.spicycurry.ui.NATSample;


	import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.eclipse.jface.layout.GridDataFactory;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ReflectiveColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultCornerDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.CornerLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.GridLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.RowHeaderLayer;
import org.eclipse.nebula.widgets.nattable.hideshow.ColumnHideShowLayer;
import org.eclipse.nebula.widgets.nattable.layer.AbstractLayerTransform;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.reorder.ColumnReorderLayer;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.viewport.ViewportLayer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import de.sfk.spicycurry.ui.NATSample.Example_002.*;

	//import com.vogella.model.person.Person;
/**
 * https://eclipse.org/nattable/documentation.php?page=examples
 * @author boris.schneider
 *
 */
	// import com.vogella.model.person.PersonService;

	public class Example_003 {
		
		private IDataProvider bodyDataProvider;
		private BodyLayerStack bodyLayerStack;
		private RowHeaderLayerStack rowHeaderLayerStack;
		private ColumnHeaderLayerStack columnHeaderLayerStack;
		private NatTable natTable;
		
		private IDataProvider setupBodyDataProvider() {
			Map<String, String> propertyToLabels;
			String[] propertyNames;
			
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
		
		public class RowHeaderLayerStack extends AbstractLayerTransform {
			protected DataLayer dataLayer;
			protected RowHeaderLayer rowHeaderLayer;
			
			public RowHeaderLayerStack(IDataProvider dataProvider) {
				this.dataLayer = new DataLayer(dataProvider, 50, 20);
				rowHeaderLayer = 
					new RowHeaderLayer(
						dataLayer, bodyLayerStack, bodyLayerStack.selectionLayer);
				setUnderlyingLayer(rowHeaderLayer);
			}
		}
		
		public class ColumnHeaderLayerStack extends AbstractLayerTransform {
			protected DataLayer dataLayer;
			protected ColumnHeaderLayer colHeaderLayer;
			
			public ColumnHeaderLayerStack(IDataProvider dataProvider) {
				this.dataLayer = new DataLayer(dataProvider);
				colHeaderLayer = 
					new ColumnHeaderLayer(
						this.dataLayer, bodyLayerStack, bodyLayerStack.selectionLayer);
				setUnderlyingLayer(colHeaderLayer);
			}
		}
		public class BodyLayerStack extends AbstractLayerTransform {

			protected SelectionLayer selectionLayer;
			protected DataLayer dataLayer;
			
			public BodyLayerStack(IDataProvider dataProvider) {
				this.dataLayer = new DataLayer(dataProvider);
				
				ColumnReorderLayer columnReorderLayer = 	new ColumnReorderLayer(dataLayer);
				ColumnHideShowLayer columnHideShowLayer = 	new ColumnHideShowLayer(columnReorderLayer);
				
				selectionLayer = new SelectionLayer(columnHideShowLayer);
				ViewportLayer viewportLayer = new ViewportLayer(selectionLayer);
				setUnderlyingLayer(viewportLayer);
			}

			
		}
	        @PostConstruct
	        public void postConstruct(Composite parent /*, PersonService personService*/) {
	                parent.setLayout(new GridLayout());

	                // property names of the Person class
	                // String[] propertyNames = {"firstName", "lastName", "gender", "married", "birthday"};

	                // create the data provider
	                //IColumnPropertyAccessor<Person> columnPropertyAccessor =
	                //                new ReflectiveColumnPropertyAccessor<Person>(propertyNames);
	                
	                bodyDataProvider = this.setupBodyDataProvider();
	                bodyLayerStack = new BodyLayerStack(bodyDataProvider);
	                bodyLayerStack.dataLayer.setColumnPercentageSizing(true);
	                columnHeaderLayerStack = new ColumnHeaderLayerStack(bodyDataProvider);
	                rowHeaderLayerStack = new RowHeaderLayerStack(bodyDataProvider);
	                
	               
	                DefaultCornerDataProvider cornerDataProvider = 
	                		// colHeaderDataProvider, rowHeaderDataProvider
	                		new DefaultCornerDataProvider(columnHeaderLayerStack.dataLayer.getDataProvider(), 
	                									  rowHeaderLayerStack.dataLayer.getDataProvider());
	                
	                CornerLayer cornerLayer = 
	                		new CornerLayer(
	                						new DataLayer(cornerDataProvider), 	
	                						rowHeaderLayerStack.rowHeaderLayer, 
	                						columnHeaderLayerStack.colHeaderLayer);
	                
	                GridLayer gridLayer = 
	                		new GridLayer(bodyLayerStack, 
	                				columnHeaderLayerStack.colHeaderLayer, 
	                				rowHeaderLayerStack.rowHeaderLayer, 
	                				cornerLayer);
	                
	                // use different style bits to avoid rendering of inactive scrollbars for small table
	                
	                // Note: The enabling/disabling and showing of the scrollbars is handled by the ViewportLayer.
	                //                Without the ViewportLayer the scrollbars will always be visible with the default
	                //                style bits of NatTable.
	                natTable = new NatTable(
	                                parent,
	                                SWT.NO_REDRAW_RESIZE | SWT.DOUBLE_BUFFERED | SWT.BORDER,
	                                gridLayer);
	                
	                // GridDataFactory.fillDefaults().grab(true, true).applyTo(natTable);
	        }
	}

