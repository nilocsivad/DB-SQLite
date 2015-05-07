/**
 * 
 */
package com.example.db_sqlite.util.lottery.dcb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.db_sqlite.util.db.ISQLiteDBTable;
import com.example.db_sqlite.util.db.bean.BeanDoubleColorBall;
import com.example.db_sqlite.util.ro.C;

/**
 * @author Colin
 *
 */
public final class FetchDoubleColorBall implements C {
	
	/**
	 * the class attribute of page count tag
	 */
	private static final String CLS_PAGE_COUNT = "pg"; 
	
	/**
	 * total web page
	 */
	private int count = 0;
	
	/**
	 * current web page index
	 */
	private int index = 1;
	
	/**
	 * current web page document
	 */
	private Document document;
	
	private BeanDoubleColorBall first, last;

	/**
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * 
	 */
	private FetchDoubleColorBall() throws MalformedURLException, IOException {
		this.document = Jsoup.parse( new URL( URL_DCB_NUMBER + this.index ) , TM_JSOUP_WAIT );
		this.resolveCount();
	}
	
	private static FetchDoubleColorBall fetch;
	
	public static FetchDoubleColorBall getInstance() throws MalformedURLException, IOException {
		if ( fetch == null )
			fetch = new FetchDoubleColorBall();
		return fetch;
	}
	
	public void RefreshInfo() throws MalformedURLException, IOException {
		this.index = 1;
		this.document = Jsoup.parse( new URL( URL_DCB_NUMBER + this.index ) , TM_JSOUP_WAIT );
		this.resolveCount();
	}
	
	private void resolveCount() {
		this.count = Integer.parseInt( this.document.getElementsByClass( CLS_PAGE_COUNT ).get(0).getElementsByTag("strong").get(0).text() );
		
		Elements trs = this.document.getElementsByTag("tr");
		{
			Elements tds = trs.get( 2 ).getElementsByTag("td");
			String[] arr = new String[] { tds.get(0).text(), tds.get(1).text(), tds.get(2).text() };
			String[] balls = arr[2].split( " " );
			String line = arr[2].replace( " ", "" );
			this.first = new BeanDoubleColorBall( arr[0], arr[1], balls[0], balls[1], balls[2], balls[3], balls[4], balls[5], balls[6], line );
		}
		{
			Elements tds = trs.get( 21 ).getElementsByTag("td");
			String[] arr = new String[] { tds.get(0).text(), tds.get(1).text(), tds.get(2).text() };
			String[] balls = arr[2].split( " " );
			String line = arr[2].replace( " ", "" );
			this.last = new BeanDoubleColorBall( arr[0], arr[1], balls[0], balls[1], balls[2], balls[3], balls[4], balls[5], balls[6], line );
		}
	}
	
	public void ORMBangInfo( ISQLiteDBTable<BeanDoubleColorBall> dcbTable ) throws MalformedURLException, IOException, ParseException {
		if ( dcbTable.countAll() == 0 ) {
			do {
				this.ResolveDocument( dcbTable );
				this.index++;
				this.document = Jsoup.parse( new URL( URL_DCB_NUMBER + this.index ) , TM_JSOUP_WAIT );
			} while ( this.index <= this.count );
		} else {
			
			BeanDoubleColorBall lastest = dcbTable.lists( " ORDER BY number DESC LIMIT 1 " ).get( 0 );
			int lastest_num = Integer.parseInt( lastest.getNumber() );
			int new_num = Integer.parseInt( first.getNumber() );
			int lazy_num = Integer.parseInt( last.getNumber() );
			if ( lastest_num == new_num ) {
			} else if ( lastest_num < new_num && lastest_num >= lazy_num ) {
				this.ResolveDocument( dcbTable );
			} else {
				boolean b_continue = true;
				do {sdfasdfasdfasd
					this.ResolveDocument( dcbTable );
					this.index++;
					this.document = Jsoup.parse( new URL( URL_DCB_NUMBER + this.index ) , TM_JSOUP_WAIT );
				} while ( b_continue );
			}
		}
	}
	
	private void ResolveDocument( ISQLiteDBTable<BeanDoubleColorBall> dcbTable ) throws MalformedURLException, IOException {
		
		Elements trs = this.document.getElementsByTag("tr");
		trs.remove(1);
		trs.remove(0);
		trs.remove( trs.size() - 1 );
		
		List<String[]> results = new ArrayList<String[]>( trs.size() );
		
		for ( Element tr : trs ) {
			Elements tds = tr.getElementsByTag("td");
			String[] arr = new String[] { tds.get(0).text(), tds.get(1).text(), tds.get(2).text() };
			results.add( arr );
		}
		
		for ( String[] arr : results ) {
			String[] balls = arr[2].split( " " ); 
			String line = arr[2].replace( " ", "" );
			dcbTable.create( new BeanDoubleColorBall( arr[0], arr[1], balls[0], balls[1], balls[2], balls[3], balls[4], balls[5], balls[6], line ) );
		}
	}

}
