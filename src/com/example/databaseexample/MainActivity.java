package com.example.databaseexample;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	EditText editname;
	Button btninsert;
	DataHelper dbhelper;
	ListView listview;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editname = (EditText)findViewById(R.id.editText1);
		btninsert = (Button)findViewById(R.id.button1);
		listview = (ListView)findViewById(R.id.listView1);
		
		dbhelper = new DataHelper(getApplicationContext());
		
		
		
		
		
		btninsert.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			dbhelper.openToWrite();
			dbhelper.insert(editname.getText().toString());
			dbhelper.close();
		
			final ArrayList<String> array = new ArrayList<>();
			dbhelper.openToRead();
			ArrayList<dataclass> dataarray = dbhelper.retrivedata();
			
			for(int i=0 ;i<dataarray.size();i++)
			{
				String name = dataarray.get(i).getName();
				array.add(name);
			}
			dbhelper.close();
			
			final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,array);
			Log.e("list",array+"");
			listview.setAdapter(adapter);
			}
		});
		
	}
}
