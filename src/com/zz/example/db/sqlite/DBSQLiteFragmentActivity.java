package com.zz.example.db.sqlite;

import com.example.db_sqlite.R;
import com.example.db_sqlite.util.AH;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DBSQLiteFragmentActivity extends FragmentActivity implements DBSQLiteListFragment.OnDBSQLiteMenuItemClick {

    /**
	 * 
	 */
	public DBSQLiteFragmentActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_db_sqlite );
    }

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.DBSQLiteListFragment.OnDBSQLiteMenuItemClick#onClickDBSQLiteMenuItem(java.lang.String)
	 */
	@Override
	public void onClickDBSQLiteMenuItem( String fragmentClassName ) {
		try {
			Class<?> cls = Class.forName( fragmentClassName );
			Fragment fragment = ( Fragment ) cls.newInstance();
			this.getFragmentManager().beginTransaction().replace( R.id.db_sqlite_fragment, fragment ).commit();
		} catch ( Exception e ) { AH.TS( this.getClass().getName() + " throw exception: " + e.getMessage() ); }
	}

}
