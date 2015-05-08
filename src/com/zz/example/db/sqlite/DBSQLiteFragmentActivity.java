package com.zz.example.db.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.zz.example.db.sqlite.util.S;

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
        
        S.init( this );
        
        this.setContentView( R.layout.activity_menus );
    }

	/* (non-Javadoc)
	 * @see com.example.db_sqlite.DBSQLiteListFragment.OnDBSQLiteMenuItemClick#onClickDBSQLiteMenuItem(java.lang.String)
	 */
	@Override
	public void onClickDBSQLiteMenuItem( String fragmentClassName ) {
		Intent intent = new Intent( this, DBSQLiteDetailFragmentActivity.class );
		intent.putExtra( DBSQLiteDetailFragmentActivity.KEY, fragmentClassName );
		this.startActivity( intent );
	}

}
