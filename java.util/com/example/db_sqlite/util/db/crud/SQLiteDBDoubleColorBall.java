/**
 * 
 */
package com.example.db_sqlite.util.db.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.database.Cursor;

import com.example.db_sqlite.util.db.PSQLiteDB;
import com.example.db_sqlite.util.db.bean.BeanDoubleColorBall;

/**
 * @author Administrator
 *
 */
public class SQLiteDBDoubleColorBall extends PSQLiteDB<BeanDoubleColorBall> {

	public static final String TABLE_NAME = "tbl_bang_dcb";
	
	/**
	 * 
	 */
	public SQLiteDBDoubleColorBall() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.PSQLiteDB#getTableName()
	 */
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.PSQLiteDB#getTableDefines()
	 */
	@Override
	public String getTableDefines() {
		return "create table " + TABLE_NAME +
				   "(" + 
				   "	date char(10) not null," +
				   "	number char(7) not null," +
				   "	redA char(2)," +
				   "	redB char(2)," +
				   "	redC char(2)," +
				   "	redD char(2)," +
				   "	redE char(2)," +
				   "	redF char(2)," +
				   "	blue char(2)," +
				   "	line char(14)," +
				   "	primary key (date, number)" +
				   ")";
	}
	
	public String InsertSQL() {
		return "INSERT INTO " + TABLE_NAME +
				   "(" + 
				   "	date," +
				   "	number," +
				   "	redA," +
				   "	redB," +
				   "	redC," +
				   "	redD," +
				   "	redE," +
				   "	redF," +
				   "	blue," +
				   "	line" +
				   ") " + 
				   "VALUES" +
				   "(" + 
				   "	?," +
				   "	?," +
				   "	?," +
				   "	?," +
				   "	?," +
				   "	?," +
				   "	?," +
				   "	?," +
				   "	?," +
				   "	?" +
				   ")";
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#listAll()
	 */
	@Override
	public List<BeanDoubleColorBall> listAll() {
		return this.lists( SQL_LIST_ALL + TABLE_NAME );
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#lists(java.lang.String)
	 */
	@Override
	public List<BeanDoubleColorBall> lists( String where ) {
		List<BeanDoubleColorBall> list = new ArrayList<BeanDoubleColorBall>( 10 );
		Cursor cursor = this.db.rawQuery( where, /*new String[] { TABLE_NAME }*/ null );
		while ( cursor.moveToNext() ) {
			String date = cursor.getString( cursor.getColumnIndex( "date" ) );
			String number = cursor.getString( cursor.getColumnIndex( "number" ) );
			String redA = cursor.getString( cursor.getColumnIndex( "redA" ) );
			String redB = cursor.getString( cursor.getColumnIndex( "redB" ) );
			String redC = cursor.getString( cursor.getColumnIndex( "redC" ) );
			String redD = cursor.getString( cursor.getColumnIndex( "redD" ) );
			String redE = cursor.getString( cursor.getColumnIndex( "redE" ) );
			String redF = cursor.getString( cursor.getColumnIndex( "redF" ) );
			String blue = cursor.getString( cursor.getColumnIndex( "blue" ) );
			String line = cursor.getString( cursor.getColumnIndex( "line" ) );
			list.add( new BeanDoubleColorBall( date, number, redA, redB, redC, redD, redE, redF, blue, line ) );
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#create(java.lang.Object)
	 */
	@Override
	public Object create(BeanDoubleColorBall t) {
		if ( this.count( " WHERE date = '" + t.getDate() + "' AND number = '" + t.getNumber() + "' " ) == 0 )
			this.db.execSQL( this.InsertSQL(), new Object[] { t.getDate(), t.getNumber(), t.getRedA(), t.getRedB(), t.getRedC(), t.getRedD(), t.getRedE(), t.getRedF(), t.getBlue() } );
		return null;
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#create(java.util.Map)
	 */
	@Override
	public Object create(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#remove(java.lang.Object)
	 */
	@Override
	public Object remove(BeanDoubleColorBall t) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.util.db.ISQLiteDBTable#remove(java.util.Map)
	 */
	@Override
	public Object remove(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
