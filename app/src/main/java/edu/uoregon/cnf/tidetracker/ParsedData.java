package edu.uoregon.cnf.tidetracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Christopher on 7/7/2016.
 */
public class ParsedData {

    private ArrayList<DataItem> items;

    public ParsedData() {
        items = new ArrayList<DataItem>();
    }

    public int addItem(DataItem item) {
        items.add(item);
        return items.size();
    }

    public DataItem getItem(int index) {
        return items.get(index);
    }

    public ArrayList<DataItem> getAllItems() {
        return items;
    }
}
