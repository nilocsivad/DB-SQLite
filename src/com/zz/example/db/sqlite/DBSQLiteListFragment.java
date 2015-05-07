/**
 * 
 */
package com.zz.example.db.sqlite;

import com.example.db_sqlite.R;
import com.example.db_sqlite.util.AH;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author Colin
 *
 */
public class DBSQLiteListFragment extends ListFragment {
	
	private OnDBSQLiteMenuItemClick callback;
	
	public interface OnDBSQLiteMenuItemClick {
		public void onClickDBSQLiteMenuItem( String fragmentClassName );
	}

	/**
	 * 
	 */
	public DBSQLiteListFragment() {
		this.setListAdapter( new ArrayAdapter<String>( this.getActivity(), android.R.layout.simple_list_item_1, AH.strArr( R.array.menu_item ) ) );
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
		// TODO Auto-generated method stub
		return super.onCreateView( inflater, container, savedInstanceState );
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.ListFragment#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	public void onListItemClick( ListView l, View v, int position, long id ) {
		super.onListItemClick( l, v, position, id );
		
		if ( callback != null )
			callback.onClickDBSQLiteMenuItem( AH.strArr( R.array.menu_item_classes )[ position ] );
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach( Activity activity ) {
		super.onAttach( activity );
		
		if ( activity instanceof OnDBSQLiteMenuItemClick )
			callback = ( OnDBSQLiteMenuItemClick ) activity;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		// TODO Auto-generated method stub
		super.onCreate( savedInstanceState );
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onDetach()
	 */
	@Override
	public void onDetach() {
		super.onDetach();
		
		callback = null;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
