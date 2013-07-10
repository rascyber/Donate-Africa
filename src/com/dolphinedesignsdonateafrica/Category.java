package com.dolphinedesignsdonateafrica;

import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Category extends Activity {
	    	// All static variables
	    	static final String URL = "http://api.androidhive.info/music/music.xml";
	    	// XML node keys
	    	static final String KEY_ID = "id";
			static final String KEY_TITLE = "title";
			static final String KEY_INFO = "info";
			static final String KEY_THUMB_URL = "thumb_url";
			
			ListView list;
		    LazyAdapter adapter;

	    	@Override
	    	public void onCreate(Bundle savedInstanceState) {
	    		super.onCreate(savedInstanceState);
	    		setContentView(R.layout.activity_donate_africa);

	    		ArrayList<HashMap<String, String>> storyList = new ArrayList<HashMap<String, String>>();
		        
		        XMLParser parser = new XMLParser();
				String xml = parser.getXmlFromUrl(URL); // getting XML from URL
				Document doc = parser.getDomElement(xml); // getting DOM element
				
				NodeList nl = doc.getElementsByTagName(KEY_ID);
				// looping through all song nodes <story>;
				for (int i = 0; i < nl.getLength(); i++) {
					// creating new HashMap
					HashMap<String, String> map = new HashMap<String, String>();
					Element e = (Element) nl.item(i);
					// adding each child node to HashMap key => value
					map.put(KEY_ID, parser.getValue(e, KEY_ID));
					map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
					map.put(KEY_INFO, parser.getValue(e, KEY_INFO));
					map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));

					// adding HashList to ArrayList
					storyList.add(map);
				}

	    		list=(ListView)findViewById(R.id.nativeList);

	    		// Getting adapter by passing xml data ArrayList
		        adapter=new LazyAdapter(this, storyList);
		        list.setAdapter(adapter);

	            // Click event for single list row
	            list.setOnItemClickListener(new OnItemClickListener() {

	    		

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						
					}
	    		});
	    	}
	    }
