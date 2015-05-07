/**
 * 
 */
package com.zz.example.db.sqlite.menus;

import org.jsoup.nodes.Document;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.db_sqlite.util.AH;
import com.example.db_sqlite.util.db.ISQLiteDBTable;
import com.example.db_sqlite.util.db.bean.BeanDoubleColorBall;
import com.example.db_sqlite.util.db.crud.SQLiteDBDoubleColorBall;
import com.example.db_sqlite.util.ro.C;
import com.zz.example.db.sqlite.R;

/**
 * @author Colin
 *
 */
public class OutputFragment extends Fragment implements C, View.OnClickListener {
	
	public static final int WHAT_JSOUP = 9999;

	/**
	 * 
	 */
	public OutputFragment() {
		// TODO Auto-generated constructor stub
	}
	
	private TextView tv_output;

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
		
		View rootView = inflater.inflate( R.layout.fragment_output, container, false );
		
		{
			( this.tv_output = ( TextView ) rootView.findViewById( R.id.output_tv_text ) ).setOnClickListener( this );
		}
		this.initEventAndData();
		
		return rootView;
	}
	
	@SuppressLint("HandlerLeak") private Handler handler = new Handler() {
		@Override public void handleMessage( Message msg ) {
			
			Document doc = ( Document ) msg.obj;
			String text = doc.getAllElements().html();
			tv_output.setText( tv_output.getText() + "\r\n" + text );
			
			super.handleMessage(msg);
		}
	};

	/**
	 * 
	 */
	private void initEventAndData() {
//		new Thread() {
//			@Override public void run() {
//				super.run();
//				try {
//					Document doc = Jsoup.parse( new URL( "https://www.baidu.com" ), 10000 );
//					Message message = Message.obtain( handler, WHAT_JSOUP, doc );
//					handler.sendMessage( message );
//				} catch (MalformedURLException e) { AH.TS( this.getClass().getName() + " throw " + e.getClass().getName() + ": " + e.getMessage() ); 
//				} catch (IOException e) { AH.TS( this.getClass().getName() + " throw " + e.getClass().getName() + ": " + e.getMessage() ); 
//				}
//			}
//		}.start();
		
		ISQLiteDBTable<BeanDoubleColorBall> dcbTable = new SQLiteDBDoubleColorBall();
		tv_output.setText( tv_output.getText() + "\r\n" + dcbTable.countAll() );
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View view) {
		switch ( view.getId() ) {
		case R.id.output_tv_text:
			AH.TL( ( ( TextView ) view ).getText() );
			break;
		}
	}

}
