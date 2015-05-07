/**
 * 
 */
package com.example.db_sqlite.util.lottery.dcb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
	 * current web page index
	 */
	private int index = 1;
	
	/**
	 * current web page document
	 */
	private Document document;

	/**
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * 
	 */
	public FetchDoubleColorBall() throws MalformedURLException, IOException {
		this.document = Jsoup.parse( new URL(URL_DCB_NUMBER + this.index) , TM_JSOUP_WAIT );
	}
	
	public void ORMBangInfo( ISQLiteDBTable<BeanDoubleColorBall> dcbTable ) {
		
		Elements trs = this.document.getElementsByTag("tr");
		trs.remove(1);
		trs.remove(0);
		trs.remove( trs.size() - 1 );
		
		List<String[]> results = new ArrayList<String[]>( trs.size() );
		
		for ( Element tr : trs ) {
			Elements tds = tr.getElementsByTag("td");
			results.add( new String[] { tds.get(0).text(), tds.get(1).text(), tds.get(2).text() } );
		}
		
		for ( String[] arr : results ) {
			String[] balls = arr[2].split( " " ); 
			String line = arr[2].replace( " ", "" );
			dcbTable.create( new BeanDoubleColorBall( arr[0], arr[1], balls[0], balls[1], balls[2], balls[3], balls[4], balls[5], balls[6], line ) );
		}
	}

}
