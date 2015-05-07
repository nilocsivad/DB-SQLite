/**
 * 
 */
package com.example.db_sqlite.util;

import android.content.Context;
import android.content.res.Resources;

/**
 * @author Colin
 *
 */
public final class S {

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
	}

}
