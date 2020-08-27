package com.example.eclipseweatherforecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

static URLConnection urlconn = null;
static InputStream is = null;
static BufferedReader reader = null;
static JSONObject jObj = null;
static String json = "";

// constructor
public JSONParser() {
}

public JSONObject getJSONFromUrl(String url) {
	// Making URL request
	try {		
		URL ur = new URL(url);
		urlconn = ur.openConnection();
		
		is = urlconn.getInputStream();	
		reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
		StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            buffer.append(line+"\n");
            Log.d("Response: ", "> " + line);   //here u ll get whole response
        }
        is.close();
		System.out.println("out="+buffer.toString());
		json = buffer.toString();		
		Log.v("tag", "VAL= " + json);
	}
	catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	} 
	catch (IOException e) {
		e.printStackTrace();
	}
	catch (Exception e) {
		Log.e("Buffer Error", "Error converting result " + e.toString());
	}	
	
	// try parse the string to a JSON object
	try {
		jObj = new JSONObject(json);
	} 
	catch (JSONException e) {
		Log.e("JSON Parser", "Error parsing data " + e.toString());
	}
	finally {
        try {
            if (reader != null) 
                reader.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	// return JSON String
	return jObj;
	}
}
