/**
 * 
 */
package com.zz.example.db.sqlite.util;

import java.io.File;

import com.zz.example.db.sqlite.util.ro.C;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;

/**
 * @author Colin
 *
 */
public final class S implements C {

	/**
	 * 
	 */
	private S() {
		// TODO Auto-generated constructor stub
	}
	
	public static Context CONTEXT;
	public static Resources RESOURCES;
	
	public static void init( Context context ) {
		
		CONTEXT = context.getApplicationContext();
		
		RESOURCES = CONTEXT.getResources();
		
		if ( !Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED ) ) {
			AH.TL( "Media unmounted!");
		}
	}
	
	public static String getDBPath() {
		String path = DATABASE_NAME;
		if ( Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED ) )
			path = /*Environment.getExternalStorageDirectory().getAbsolutePath()*/ Environment.getExternalStorageDirectory().getPath() + File.separator + P_APP_FOLDER + File.separator + DATABASE_NAME;		
		return path;
	}

}
