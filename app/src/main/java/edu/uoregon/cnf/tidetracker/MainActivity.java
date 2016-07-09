package edu.uoregon.cnf.tidetracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
    implements AdapterView.OnItemClickListener {

    private ParsedData dataCollection;
    private FileIO fileIO;

    private TextView titleTextView;
    private ListView readingsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileIO = new FileIO(getApplicationContext());

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        readingsListView = (ListView) findViewById(R.id.readingsListView);

        readingsListView.setOnItemClickListener(this);

        dataCollection = fileIO.readFile();
        MainActivity.this.updateDisplay();

//        new ReadFeed().execute();
    }

//    class DownloadFeed extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected Void doInBackground(Void... params) {
//            io.downloadFile();
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            Log.d("News reader", "Feed downloaded");
//            new ReadFeed().execute();
//        }
//    }
//
//    class ReadFeed extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected Void doInBackground(Void... params) {

//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            // update the display for the activity

//        }
//    }

    public void updateDisplay()
    {
        if (dataCollection == null) {
            titleTextView.setText("Unable to read XML file");
            return;
        }

        // set the title for the feed
        titleTextView.setText("Tide Readings for Florence, Oregon");

        ArrayList<DataItem> dataItems = dataCollection.getAllItems();
        // get the items for the feed

        ArrayList<HashMap<String, String>> data =
                new ArrayList<HashMap<String, String>>();
        for (DataItem item : dataItems) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("date", item.getDate() + " " + item.getDay());
            map.put("line2", item.getHighlow() + " " + item.getTime());
            data.add(map);
        }
//        ArrayList<HashMap<String, String>> data = new ArrayList<~>();

        // create a List of Map<String, ?> objects
//        ArrayList<String[]> data =
//                new ArrayList<String[]>();
//        for (DataItem item : dataItems) {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("date", item.getFormattedDate());
//            map.put("line2", item.getHighlow() + ": " + item.getTime());
//            data.add(map);
//        }
//            String[] reading = new String[5];
//            reading[0] = item.getFormattedDate();
//            reading[1] = item.getTime();
//            reading[2] = item.getFeet();
//            reading[3] = item.getCentimeters();
//            reading[4] = item.getHighlow();
//            data.add(reading);
//        }

        // create the resource, from, and to variables
        int resource = R.layout.listview_item;
        String[] from = {"date", "line2"};
        int[] to = {R.id.dateTextView, R.id.timeTextView};

        // create and set the adapter
        SimpleAdapter adapter = new SimpleAdapter(this, data, resource, from, to);
        readingsListView.setAdapter(adapter);

     //   Log.d("News reader", "Feed displayed");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v,
                            int position, long id) {

        // get the item at the specified position
        DataItem item = dataCollection.getItem(position);

        Context context = getApplicationContext();
        CharSequence text = item.getFeet() + " ft, " + item.getCentimeters() + "cm";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        // create an intent
//        Intent intent = new Intent(this, ItemActivity.class);
//
//        intent.putExtra("pubdate", item.getPubDate());
//        intent.putExtra("title", item.getTitle());
//        intent.putExtra("description", item.getDescription());
//        intent.putExtra("link", item.getLink());
//
//        this.startActivity(intent);
    }
}
