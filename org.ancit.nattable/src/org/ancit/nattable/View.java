package org.ancit.nattable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultColumnHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultRowHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.GridLayer;
import org.eclipse.nebula.widgets.nattable.layer.CompositeLayer;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.viewport.ViewportLayer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

import PersonModel.Person;
import PersonModel.PersonModelFactory;

public class View extends ViewPart {
	public static final String ID = "org.ancit.nattable.view";

	@Inject IWorkbench workbench;
	

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout());

		
        List<Person> persons = new ArrayList<>();
		Person person = PersonModelFactory.eINSTANCE.createPerson();
		person.setFirstName("Mahesh");
		person.setLastName("Bhagirth");
		person.setGender("Male");
		person.setMarried(true);
		person.setAge(35);
		persons.add(person);
		
		   IColumnPropertyAccessor columnPropertyAccessor =
	                new PersonWithAddressColumnPropertyAccessor();
		 IDataProvider bodyDataProvider =
	                new ListDataProvider<>(
	                       persons,
	                        columnPropertyAccessor);
	        final DataLayer bodyDataLayer =
	                new DataLayer(bodyDataProvider);
	        final SelectionLayer selectionLayer =
	                new SelectionLayer(bodyDataLayer);
	        ViewportLayer viewportLayer =
	                new ViewportLayer(selectionLayer);

	        ILayer columnHeaderLayer =
	                new ColumnHeaderLayer(
	                        new DataLayer(createColumnHeaderDataProvider()),
	                        viewportLayer,
	                        selectionLayer);
	        
	        DataLayer rowHeaderDataLayer = new DataLayer(
	                new DefaultRowHeaderDataProvider(bodyDataProvider));
	        rowHeaderDataLayer.setDefaultColumnWidth(41);


	        // set the region labels to make default configurations work, e.g.
	        // selection
	        CompositeLayer compositeLayer = new CompositeLayer(1, 2);
	        compositeLayer.setChildLayer(GridRegion.COLUMN_HEADER, columnHeaderLayer, 0, 0);
	        compositeLayer.setChildLayer(GridRegion.BODY, viewportLayer, 0, 1);


        NatTable natTable = new NatTable(parent, SWT.NO_REDRAW_RESIZE | SWT.DOUBLE_BUFFERED | SWT.BORDER,
        		compositeLayer);

        GridDataFactory.fillDefaults().grab(true, true).applyTo(natTable);

	}

	/**
     * Creates the {@link IDataProvider} for the column header of this
     * {@link GridLayer}. Should always return the same column count and values
     * for all columns that are defined within the {@link IDataProvider} of the
     * body layer stack. Uses the {@link DefaultColumnHeaderDataProvider} which
     * simply checks for the property name within the propertyNames array and
     * returns the corresponding value out of the propertyToLabelMap. Another
     * approach is to implement a completely new {@link IDataProvider}
     */
    protected IDataProvider createColumnHeaderDataProvider() {

    	Map propertyToLabelMap = new HashMap<>();
        propertyToLabelMap.put(DataModelConstants.FIRSTNAME_PROPERTYNAME, "Firstname");
        propertyToLabelMap.put(DataModelConstants.LASTNAME_PROPERTYNAME, "Lastname");
        propertyToLabelMap.put(DataModelConstants.GENDER_PROPERTYNAME, "Gender");
        propertyToLabelMap.put(DataModelConstants.MARRIED_PROPERTYNAME, "Married");
        propertyToLabelMap.put(DataModelConstants.AGE_PROPERTYNAME, "Birthday");

        return new DefaultColumnHeaderDataProvider(DataModelConstants.PERSONWITHADDRESS_PROPERTY_NAMES, propertyToLabelMap);

    }

    /**
     * This is an implementation for a custom IColumnPropertyAccessor to access
     * PersonWithAddress objects in a NatTable. It is used for the
     * ListDataProvider in the body aswell as for the column header labels.
     */
    class PersonWithAddressColumnPropertyAccessor implements IColumnPropertyAccessor {

        @Override
        public Object getDataValue(Object obj, int columnIndex) {
        	Person rowObject = (Person) obj;
            switch (columnIndex) {
                case DataModelConstants.FIRSTNAME_COLUMN_POSITION:
                    return rowObject.getFirstName();
                case DataModelConstants.LASTNAME_COLUMN_POSITION:
                    return rowObject.getLastName();
                case DataModelConstants.GENDER_COLUMN_POSITION:
                    return rowObject.getGender();
                case DataModelConstants.MARRIED_COLUMN_POSITION:
                    return rowObject.isMarried();
                case DataModelConstants.AGE_COLUMN_POSITION:
                    return rowObject.getAge();
            }
            return "";
        }

        /**
         * Very simple implementation without any type checks.
         */
        @Override
        public void setDataValue(Object obj, int columnIndex,
                Object newValue) {
        	Person rowObject = (Person) obj;
            switch (columnIndex) {
                case DataModelConstants.FIRSTNAME_COLUMN_POSITION:
                    rowObject.setFirstName((String) newValue);
                    break;
                case DataModelConstants.LASTNAME_COLUMN_POSITION:
                    rowObject.setLastName((String) newValue);
                    break;
                case DataModelConstants.GENDER_COLUMN_POSITION:
                    rowObject.setGender((String) newValue);
                    break;
                case DataModelConstants.MARRIED_COLUMN_POSITION:
                    rowObject.setMarried((Boolean) newValue);
                    break;
                case DataModelConstants.AGE_COLUMN_POSITION:
                    rowObject.setAge((int) newValue);
                    break;
            }
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public String getColumnProperty(int columnIndex) {
            return DataModelConstants.PERSONWITHADDRESS_PROPERTY_NAMES[columnIndex];
        }

        @Override
        public int getColumnIndex(String propertyName) {
            return Arrays.asList(DataModelConstants.PERSONWITHADDRESS_PROPERTY_NAMES).indexOf(propertyName);
        }

    }


    
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}


}