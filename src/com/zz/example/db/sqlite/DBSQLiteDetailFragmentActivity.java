/**
 * 
 */
package com.zz.example.db.sqlite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.zz.example.db.sqlite.util.AH;

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
	
	private Fragment fragment;
	
	@Override
    protected void onCreate( Bundle bundle ) {
        super.onCreate( bundle );
        
        this.setContentView( R.layout.activity_detail );
        
        if ( bundle == null ) {
	        String fragmentClassName = this.getIntent().getStringExtra( KEY );
	        try {
				Class<?> cls = Class.forName( fragmentClassName );
				fragment = ( Fragment ) cls.newInstance();
				this.getSupportFragmentManager().beginTransaction().replace( R.id.detail_container, fragment ).commit();
			} catch ( Exception e ) { AH.TS( this.getClass().getName() + " throw " + e.getClass().getName() + ": " + e.getMessage() ); 
			}
        }
    }

	/* (non-Javadoc)
	 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if ( this.fragment != null )
			this.fragment.onPrepareOptionsMenu( menu );
		
		return super.onPrepareOptionsMenu(menu);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsMenuClosed(android.view.Menu)
	 */
	@Override
	public void onOptionsMenuClosed(Menu menu) {
		if ( this.fragment != null )
			this.fragment.onOptionsMenuClosed( menu );
		
		super.onOptionsMenuClosed(menu);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		this.fragment = null;
	}

}
