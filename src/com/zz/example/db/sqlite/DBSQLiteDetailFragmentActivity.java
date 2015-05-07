/**
 * 
 */
package com.zz.example.db.sqlite;

import com.example.db_sqlite.util.AH;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * @author Administrator
 *
 */
public class DBSQLiteDetailFragmentActivity extends FragmentActivity {
	
	public static final String KEY = "CLICKED_CLASS_NAME";

	/**
	 * 
	 */
	public DBSQLiteDetailFragmentActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
    protected void onCreate( Bundle bundle ) {
        super.onCreate( bundle );
        
        this.setContentView( R.layout.activity_detail );
        
        if ( bundle == null ) {
	        String fragmentClassName = this.getIntent().getStringExtra( KEY );
	        try {
				Class<?> cls = Class.forName( fragmentClassName );
				Fragment fragment = ( Fragment ) cls.newInstance();
				this.getSupportFragmentManager().beginTransaction().replace( R.id.detail_container, fragment ).commit();
			} catch ( Exception e ) { AH.TS( this.getClass().getName() + " throw exception: " + e.getMessage() ); }
        }
    }

}
