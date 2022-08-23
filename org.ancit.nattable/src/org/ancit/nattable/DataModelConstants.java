package org.ancit.nattable;

public class DataModelConstants {

	public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
	
	public static final int FIRSTNAME_COLUMN_POSITION = 0;
	public static final int LASTNAME_COLUMN_POSITION = 1;
	public static final int GENDER_COLUMN_POSITION = 2;
	public static final int MARRIED_COLUMN_POSITION = 3;
	public static final int AGE_COLUMN_POSITION = 4;
	
	
	public static final String FIRSTNAME_PROPERTYNAME = "firstName";
	public static final String LASTNAME_PROPERTYNAME = "lastName";
	public static final String GENDER_PROPERTYNAME = "gender";
	public static final String MARRIED_PROPERTYNAME = "married";
	public static final String AGE_PROPERTYNAME = "age";
	

	public static final String[] PERSON_PROPERTY_NAMES = {
		FIRSTNAME_PROPERTYNAME, 
		LASTNAME_PROPERTYNAME, 
		GENDER_PROPERTYNAME, 
		MARRIED_PROPERTYNAME, 
		AGE_PROPERTYNAME};

	public static final String[] PERSONWITHADDRESS_PROPERTY_NAMES = {
		FIRSTNAME_PROPERTYNAME, 
		LASTNAME_PROPERTYNAME, 
		GENDER_PROPERTYNAME, 
		MARRIED_PROPERTYNAME, 
		AGE_PROPERTYNAME,
		};
}
