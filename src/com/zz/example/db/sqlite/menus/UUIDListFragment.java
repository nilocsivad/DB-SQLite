/**
 * 
 */
package com.zz.example.db.sqlite.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zz.example.db.sqlite.R;
import com.zz.example.db.sqlite.util.AH;

/**
 * @author Administrator
 *
 */
public class UUIDListFragment extends ListFragment implements OnTouchListener, OnScrollListener, OnClickListener, OnItemClickListener {
	
	public static final int LV_SIZE = 30;

	/**
	 * 
	 */
	public UUIDListFragment() {
		// TODO Auto-generated constructor stub
	}
	
	private final List<UUID> datas = new ArrayList<UUID>( LV_SIZE );
	
	private ArrayAdapter<UUID> adapter;
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
		return super.onCreateView( inflater, container, savedInstanceState );
	}

	/**
	 * 
	 */
	@SuppressLint("InflateParams")
	private void initEventAndData() {
		
		if ( this.getListView().getFooterViewsCount() == 0 ) {
			View footView = this.getActivity().getLayoutInflater().inflate( R.layout.li_uuid_foot, null );
			{
				( ( TextView ) footView.findViewById( R.id.uuid_tv_refresh ) ).setOnClickListener( this );
				this.getListView().addFooterView( footView, null, false );
			}
		}
		
		List<UUID> list = new ArrayList<UUID>( LV_SIZE );
		for ( int i = 0; i < LV_SIZE; ++i ) {
			list.add( UUID.randomUUID() );
		}
		this.datas.addAll( list );
		
		this.adapter = new ArrayAdapter<UUID>( this.getActivity(), android.R.layout.simple_list_item_1, this.datas );
		this.getListView().setAdapter( this.adapter );
		this.getListView().setOnTouchListener( this );
		this.getListView().setOnScrollListener( this );
		this.getListView().setOnItemClickListener( this );
		
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach( Activity activity ) {
		// TODO Auto-generated method stub
		super.onAttach( activity );
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
		// TODO Auto-generated method stub
		super.onDetach();
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		this.initEventAndData();
//		this.RefreshListView();
	}

	/**
	 * 
	 */
	private void RefreshListView() {
		
//		int selection = this.adapter.getCount();
		
		List<UUID> list = new ArrayList<UUID>( LV_SIZE );
		for ( int i = 0; i < LV_SIZE; ++i ) {
			list.add( UUID.randomUUID() );
		}
		this.datas.addAll( list );
		this.adapter.notifyDataSetChanged();
		
//		this.BottomMenuVisibility( false );
//		this.getListView().setSelection( selection );
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
	 */
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		// TODO Auto-generated method stub
		Log.e( "com.zz.example.db.sqlite", view.getClass().getName() + " onTouch --> " + event.getAction() );
		return false;
	}
	
	/*
	private void BottomMenuVisibility( boolean isShow ) {
		if ( isShow ) {
			this.ll_visibility.setVisibility( View.VISIBLE );
			this.getListView().setSelection( this.adapter.getCount() - 1 );
		} else {
			this.ll_visibility.setVisibility( View.GONE );
		}
	}*/

	/* (non-Javadoc)
	 * @see android.widget.AbsListView.OnScrollListener#onScroll(android.widget.AbsListView, int, int, int)
	 */
	@Override
	public void onScroll( AbsListView view, int firstVisibleIndex, int visibleItemCount, int totalItemCount ) {
		Log.e( "com.zz.example.db.sqlite", view.getClass().getName() + " onScroll --> " + firstVisibleIndex + ", " + visibleItemCount + ", " + totalItemCount );
	}

	/* (non-Javadoc)
	 * @see android.widget.AbsListView.OnScrollListener#onScrollStateChanged(android.widget.AbsListView, int)
	 */
	@Override
	public void onScrollStateChanged( AbsListView view, int scrollState ) {
		Log.e( "com.zz.example.db.sqlite", this.getListView().getLastVisiblePosition() + ";" + this.getListView().getBottom() + " onScrollStateChanged --> " + scrollState );
		if ( scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE ) {
//			this.BottomMenuVisibility( ( this.getListView().getLastVisiblePosition() == this.adapter.getCount() - 1 ) );
		}
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View view) {
		this.RefreshListView();
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
		AH.TL( ( ( TextView ) view ).getText() );
	}

}
