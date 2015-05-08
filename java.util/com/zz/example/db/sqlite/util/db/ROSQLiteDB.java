/**
 * 
 */
package com.zz.example.db.sqlite.util.db;

import java.io.File;

import android.database.sqlite.SQLiteDatabase;

import com.zz.example.db.sqlite.util.S;

/**
 * @author Colin
 *
 */
public final class ROSQLiteDB {
	
	private SQLiteDatabase database;

	/**
	 * 
	 */
	private ROSQLiteDB() {
		File dbFile = new File( S.getDBPath() );
		if ( !dbFile.getParentFile().exists() )
			dbFile.getParentFile().mkdirs();
		database = SQLiteDatabase.openOrCreateDatabase( dbFile, null );
	}
	
	private static final class MSQLiteDBSingle {
		private static final ROSQLiteDB db = new ROSQLiteDB();
	}
	
	public static ROSQLiteDB getInstance() {
		return MSQLiteDBSingle.db;
	}
	
	public SQLiteDatabase getDB() {
		return database;
	}

}
