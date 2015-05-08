/**
 * 
 */
package com.zz.example.db.sqlite.util;

import android.widget.Toast;

/**
 * @author Colin
 *
 */
public final class AH {

	/**
	 * 
	 */
	public AH() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * show long time {@link Toast}
	 * @param text
	 */
	public static void TL( CharSequence text ) {
		Toast.makeText( S.CONTEXT, text, Toast.LENGTH_LONG ).show();
	}
	
	/**
	 * show short time {@link Toast}
	 * @param text
	 */
	public static void TS( CharSequence text ) {
		Toast.makeText( S.CONTEXT, text, Toast.LENGTH_SHORT ).show();
	}
	
	public static String[] strArr( int resId ) {
		return S.RESOURCES.getStringArray( resId );
	}
	
	public static int integer( int resId ) {
		return S.RESOURCES.getInteger( resId );
	}

}
