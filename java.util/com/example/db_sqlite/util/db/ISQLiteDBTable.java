/**
 * 
 */
package com.example.db_sqlite.util.db;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public interface ISQLiteDBTable<T> {

	/**
	 * get current operating table name
	 * @return table name
	 */
	String getTableName(); 
	
	/**
	 * get table define sql sentence
	 */
	String getTableDefines();
	
	String SQLCountAll();
	
	long countAll();
	
	long count( String where );
	
	String SQLListAll();

	List<T> listAll();
	
	List<T> lists( String where );
	
	Object create( T t );
	
	Object create( Map<?, ?> map );
	
	String SQLRemoveAll();
	
	void removeAll();
	
	Object remove( T t );
	
	Object remove( String where );
	
	Object remove( Map<?, ?> map );
	
}
