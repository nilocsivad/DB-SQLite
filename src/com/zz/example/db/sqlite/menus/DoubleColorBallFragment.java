/**
 * 
 */
package com.zz.example.db.sqlite.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.db_sqlite.util.AH;
import com.example.db_sqlite.util.db.ISQLiteDBTable;
import com.example.db_sqlite.util.db.PSQLiteDB;
import com.example.db_sqlite.util.db.bean.BeanDoubleColorBall;
import com.example.db_sqlite.util.db.crud.SQLiteDBDoubleColorBall;
import com.example.db_sqlite.util.lottery.dcb.FetchDoubleColorBall;
import com.example.db_sqlite.util.ro.C;
import com.zz.example.db.sqlite.R;

/**
 * @author Colin
 *
 */
public class DoubleColorBallFragment extends Fragment implements C, OnItemClickListener, OnItemLongClickListener, OnClickListener {


	public static final String[] value_keys = { 
												"Date", 
												"Number", 
												"RedA", 
												"RedB", 
												"RedC", 
												"RedD", 
												"RedE", 
												"RedF", 
												"Blue", 
												"Line"
												};
	public static final int[] view_keys = { 
											R.id.double_color_ball_li_tv_date, 
											R.id.double_color_ball_li_tv_number, 
											R.id.double_color_ball_li_tv_redA, 
											R.id.double_color_ball_li_tv_redB, 
											R.id.double_color_ball_li_tv_redC, 
											R.id.double_color_ball_li_tv_redD, 
											R.id.double_color_ball_li_tv_redE, 
											R.id.double_color_ball_li_tv_redF, 
											R.id.double_color_ball_li_tv_blue, 
											R.id.double_color_ball_li_tv_line
											}; 
	
	public static final int LV_SIZE = 10;
	
	public static final int WHAT_FETCH_OK = 54321;
	
	/**
	 * 
	 */
	public DoubleColorBallFragment() {
		// TODO Auto-generated constructor stub
	}
	
	private Handler handler_visibility = new Handler();
	private Runnable runnable_visibility = new Runnable() {
		@Override public void run() {
			ll_operation.setVisibility( View.GONE );
		}
	};
	
	private ListView lv_double_color_balls;
	private LinearLayout ll_operation;

	private List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>( 10 );
	private ISQLiteDBTable<BeanDoubleColorBall> dcbTable;
	private SimpleAdapter adapter;

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
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
		super.onCreateView( inflater, container, savedInstanceState );
		
		View rootView = inflater.inflate( R.layout.fragment_double_color_ball, container, false );
		
		{
			this.lv_double_color_balls = ( ListView ) rootView.findViewById( R.id.double_color_ball_lv_double_color_balls );
			this.ll_operation = ( LinearLayout ) rootView.findViewById( R.id.double_color_ball_ll_operation );
			( ( TextView ) rootView.findViewById( R.id.double_color_ball_tv_refresh ) ).setOnClickListener( this );
		}
		this.initEventAndData();
		
		return rootView;
	}

	/**
	 * 
	 */
	private void initEventAndData() {
		this.adapter = new SimpleAdapter( this.getActivity(), datas, R.layout.li_double_color_ball, value_keys, view_keys );
		this.lv_double_color_balls.setOnItemClickListener( this );
		this.lv_double_color_balls.setOnItemLongClickListener( this );
		this.lv_double_color_balls.setAdapter( adapter );
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		
		this.dcbTable = new SQLiteDBDoubleColorBall();
		
		this.RefreshListViewData();
	}

	/**
	 * 
	 */
	private void RefreshListViewData() {
		
		String sql = PSQLiteDB.SQL_LIST_ALL + this.dcbTable.getTableName() + " ORDER BY number DESC LIMIT " + LV_SIZE + " OFFSET " + this.adapter.getCount();
		List<BeanDoubleColorBall> list = this.dcbTable.lists( sql );
		
		this.datas.addAll( list );
		this.adapter.notifyDataSetChanged();
		
		this.ll_operation.setVisibility( View.GONE );
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemLongClickListener#onItemLongClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public boolean onItemLongClick(AdapterView<?> listView, View itemView, int position, long id) {
		return true;
	}
	
	private void DelayVisibility() {
		this.ll_operation.setVisibility( View.VISIBLE );
		if ( this.adapter.getCount() > 0 )
			this.handler_visibility.postDelayed( this.runnable_visibility, AH.integer( R.integer.double_color_ball_visibility_change_wait_millis ) );
	}
	
	@SuppressLint("HandlerLeak") private Handler handler = new Handler() {
		@Override public void handleMessage(Message msg) {
			switch ( msg.what ) {
			case WHAT_EXCEPTION:
				Exception e = ( Exception ) msg.obj;
				AH.TS( this.getClass().getName() + " throw " + e.getClass().getName() + ": " + e.getMessage() );
				break;
			case WHAT_FETCH_OK:
				RefreshListViewData();
				break;
			}
			super.handleMessage(msg);
		}
	};
	

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View view) {
		switch ( view.getId() ) {
		case R.id.double_color_ball_tv_refresh:
			new Thread() {
				@Override public void run() {
					try {
						FetchDoubleColorBall.getInstance().ORMBangInfo( dcbTable );
						handler.sendEmptyMessage( WHAT_FETCH_OK );
					} catch ( Exception e ) { 
						Message msg = Message.obtain( handler, WHAT_EXCEPTION, e );
						handler.sendMessage( msg );
					}
				}
			}.start();
			break;
		}
	}

	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
	
		this.DelayVisibility();
		
		super.onPrepareOptionsMenu(menu);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onOptionsMenuClosed(android.view.Menu)
	 */
	@Override
	public void onOptionsMenuClosed(Menu menu) {
		
		this.DelayVisibility();
		
		super.onOptionsMenuClosed(menu);
	}


}
