/**
 * 
 */
package com.zz.example.db.sqlite.util.db;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zz.example.db.sqlite.util.AH;
import com.zz.example.db.sqlite.util.ro.C;

/**
 * @author Administrator
 * @param <T>
 *
 */
public abstract class PSQLiteDB<T> implements C, ISQLiteDBTable<T> {
	
	public static final String SQL_VALID_TABLE = "SELECT COUNT(*) FROM sqlite_master WHERE TYPE = 'table' AND NAME = ?";
	
	public static final String SQL_COUNT_ALL = "SELECT COUNT(*) FROM ";
	
	public static final String SQL_LIST_ALL = "SELECT * FROM ";
	
	protected SQLiteDatabase db;

	/**
	 * 
	 */
	protected PSQLiteDB() {
		if ( db == null )
			db = ROSQLiteDB.getInstance().getDB();
		
		this.validateTable();
	}
	
	/**
	 * check whether exists table
	 */
	private void validateTable() {
		Cursor cursor = this.db.rawQuery( SQL_VALID_TABLE, new String[] { this.getTableName().trim() } );
		if ( cursor.moveToFirst() ) {
			
			long count = cursor.getLong( 0 );
			if ( count == 0 ) 
				this.db.execSQL( this.getTableDefines() );
			
		} else {
			AH.TL( DATABASE_NAME + " not found!" );
		}
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#SQLCountAll()
	 */
	@Override
	public String SQLCountAll() {
		return SQL_COUNT_ALL + this.getTableName();
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#countAll()
	 */
	@Override
	public long countAll() {
		return this.count( "" );
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#count(java.lang.String)
	 */
	@Override
	public long count( String where ) {
		Cursor cursor = this.db.rawQuery( this.SQLCountAll() + where, null );
		long l = 0;
		if ( cursor.moveToFirst() ) {
			l = cursor.getLong( 0 );
		}
		return l;
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#SQLListAll()
	 */
	@Override
	public String SQLListAll() {
		return SQL_LIST_ALL + this.getTableName();
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#listAll()
	 */
	@Override
	public List<T> listAll() {
		return this.lists( this.SQLListAll() );
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#SQLRemoveAll()
	 */
	@Override
	public String SQLRemoveAll() {
		return "DELETE FROM " + this.getTableName();
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#removeAll()
	 */
	@Override
	public void removeAll() {
		this.db.execSQL( this.SQLRemoveAll() );
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#remove(java.lang.String)
	 */
	@Override
	public Object remove(String where) {
		this.db.execSQL(  this.SQLCountAll() + where, null );
		return 1;
	}

}
