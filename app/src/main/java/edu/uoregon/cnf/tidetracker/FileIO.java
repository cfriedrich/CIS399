package edu.uoregon.cnf.tidetracker;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Christopher on 7/7/2016.
 */
public class FileIO {
    private String FILENAME = "tidereadings.xml";

    private Context context = null;

    public FileIO (Context context) { this.context = context;}

    public ParsedData readFile() {
        try {
            // get the XML reader
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();

            // set content handler
            DataParser dataParser = new DataParser();
            xmlreader.setContentHandler(dataParser);

            AssetManager assetManager = context.getAssets();
            // read the file from internal storage
            InputStream inStream = assetManager.open(FILENAME);

            // parse the data
            InputSource inSource = new InputSource(inStream);
            xmlreader.parse(inSource);

            // set the feed in the activity
            ParsedData parsedData = dataParser.getFeed();
            return parsedData;
        }
        catch (Exception e) {
            Log.e("News reader", e.toString());
            return null;
        }
    }
}
