package com.example.weatherproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    URL url;
    URL weather;
    EditText pp;
    Button b;
    public String zipCode;
    URLConnection urlC;
    InputStream is;
    String info;
    String info2;
    JSONObject jo;
    TextView currentTemp;
    URLConnection urlC2;
    InputStream is2;
    JSONObject jo2;
    double lat;
    double lon;
    BufferedReader br;
    BufferedReader br2;
    String city;
    double tmin1;
    double tmin2;
    double tmin3;
    double tmin4;
    double tmin5;
    double tmax1;
    double tmax2;
    double tmax3;
    double tmax4;
    double tmax5;
    String d1;
    String d2;
    String d3;
    String d4;
    String d5;
    String t1;
    String t2;
    String t3;
    String t4;
    String t5;
    String desc1;
    String desc2;
    String desc3;
    String desc4;
    String desc5;
    double temp;
    boolean am;

    TextView location;

    TextView day2;
    TextView day3;
    TextView day4;
    TextView day5;

    TextView time2;
    TextView time3;
    TextView time4;
    TextView time5;

    TextView max1;
    TextView max2;
    TextView max3;
    TextView max4;
    TextView max5;

    TextView min1;
    TextView min2;
    TextView min3;
    TextView min4;
    TextView min5;

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;

    TextView latT;
    TextView lonT;

    TextView currentDay;
    TextView description;
    TextView start;

    ArrayList<String> descs;
    ArrayList<String> times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pp = findViewById(R.id.editTextTextPersonName);
        b = findViewById(R.id.button);

        location = findViewById(R.id.location);

        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        day5 = findViewById(R.id.day5);

        time2 = findViewById(R.id.time2);
        time3 = findViewById(R.id.time3);
        time4 = findViewById(R.id.time4);
        time5 = findViewById(R.id.time5);

        max1 = findViewById(R.id.max1);
        max2 = findViewById(R.id.max2);
        max3 = findViewById(R.id.max3);
        max4 = findViewById(R.id.max4);
        max5 = findViewById(R.id.max5);

        min1 = findViewById(R.id.min1);
        min2 = findViewById(R.id.min2);
        min3 = findViewById(R.id.min3);
        min4 = findViewById(R.id.min4);
        min5 = findViewById(R.id.min5);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.imageday2);
        img3 = findViewById(R.id.imageday3);
        img4 = findViewById(R.id.imageday4);
        img5 = findViewById(R.id.imageday5);

        latT = findViewById(R.id.lat);
        lonT = findViewById(R.id.lon);

        currentTemp = findViewById(R.id.realTemp);
        currentDay = findViewById(R.id.day1);
        description = findViewById(R.id.description);
        start = findViewById(R.id.start);

        descs = new ArrayList<String>();
        times = new ArrayList<String>();

        pp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                zipCode = editable.toString();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.setText("");
                new LongRunningTask().execute(info);
            }
        });

    }

    public class LongRunningTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try{
                info = "";
                url = new URL("https://api.openweathermap.org/geo/1.0/zip?zip=" + zipCode + ",US&appid=da9e40f8517fd2635550c635817ed576");
                urlC = url.openConnection();
                is = urlC.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                for(String i = br.readLine(); i != null; i = br.readLine())
                    info += i;
                br.close();
                jo = new JSONObject(info);

                info2 = "";
                url = new URL("https://api.openweathermap.org/data/2.5/forecast?lat=" + jo.get("lat") + "&lon=" + jo.get("lon") + "&units=imperial&appid=da9e40f8517fd2635550c635817ed576");
                urlC = url.openConnection();
                is = urlC.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                for(String i = br.readLine(); i != null; i = br.readLine())
                {
                    info2 += i;
                }

                br.close();



            } catch(IOException | JSONException e) {
                e.printStackTrace();
            }
            return info2;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // add loading icon
        }

        @Override
        protected void onPostExecute(String info) {
            //super.onPostExecute(jo);
            try {
                jo = new JSONObject(info);
                lat = jo.getJSONObject("city").getJSONObject("coord").getDouble("lat");
                lon = jo.getJSONObject("city").getJSONObject("coord").getDouble("lon");
                city = jo.getJSONObject("city").getString("name");
                tmin1 = ((JSONObject)(jo.getJSONArray("list").get(0))).getJSONObject("main").getDouble("temp_min");
                tmin2 = ((JSONObject)(jo.getJSONArray("list").get(1))).getJSONObject("main").getDouble("temp_min");
                tmin3 = ((JSONObject)(jo.getJSONArray("list").get(2))).getJSONObject("main").getDouble("temp_min");
                tmin4 = ((JSONObject)(jo.getJSONArray("list").get(3))).getJSONObject("main").getDouble("temp_min");
                tmin5 = ((JSONObject)(jo.getJSONArray("list").get(4))).getJSONObject("main").getDouble("temp_min");
                temp = ((JSONObject)(jo.getJSONArray("list").get(0))).getJSONObject("main").getDouble("temp");
                d1 = ((JSONObject)(jo.getJSONArray("list").get(0))).getString("dt_txt").substring(0,10);
                d2 = ((JSONObject)(jo.getJSONArray("list").get(1))).getString("dt_txt").substring(0,10);
                d3 = ((JSONObject)(jo.getJSONArray("list").get(2))).getString("dt_txt").substring(0,10);
                d4 = ((JSONObject)(jo.getJSONArray("list").get(3))).getString("dt_txt").substring(0,10);
                d5 = ((JSONObject)(jo.getJSONArray("list").get(4))).getString("dt_txt").substring(0,10);
                t1 = ((JSONObject)(jo.getJSONArray("list").get(0))).getString("dt_txt").substring(11);
                t2 = ((JSONObject)(jo.getJSONArray("list").get(1))).getString("dt_txt").substring(11);
                t3 = ((JSONObject)(jo.getJSONArray("list").get(2))).getString("dt_txt").substring(11);
                t4 = ((JSONObject)(jo.getJSONArray("list").get(3))).getString("dt_txt").substring(11);
                t5 = ((JSONObject)(jo.getJSONArray("list").get(4))).getString("dt_txt").substring(11);
                times.add(t1);
                if(Integer.parseInt(t1.substring(0,2)) == 24)
                    t1 = "12:00 am";
                else if(Integer.parseInt(t1.substring(0,2)) == 12)
                    t1 = "12:00 pm";
                else{
                    if(Integer.parseInt(t1.substring(0,2)) > 12)
                        t1 = Integer.parseInt(t1.substring(0,2)) % 12 + ":00 pm";
                    else
                        t1 = Integer.parseInt(t1.substring(0,2)) % 12 + ":00 am";
                }
                times.add(t2);
                if(Integer.parseInt(t2.substring(0,2)) == 24)
                    t2 = "12:00 am";
                else if(Integer.parseInt(t2.substring(0,2)) == 12)
                    t2 = "12:00 pm";
                else{
                    if(Integer.parseInt(t2.substring(0,2)) > 12)
                        t2 = Integer.parseInt(t2.substring(0,2)) % 12 + ":00 pm";
                    else
                        t2 = Integer.parseInt(t2.substring(0,2)) % 12 + ":00 am";
                }
                times.add(t3);
                if(Integer.parseInt(t3.substring(0,2)) == 24)
                    t3 = "12:00 am";
                else if(Integer.parseInt(t3.substring(0,2)) == 12)
                    t3 = "12:00 pm";
                else{
                    if(Integer.parseInt(t3.substring(0,2)) > 12)
                        t3 = Integer.parseInt(t3.substring(0,2)) % 12 + ":00 pm";
                    else
                        t3 = Integer.parseInt(t3.substring(0,2)) % 12 + ":00 am";
                }
                times.add(t4);
                if(Integer.parseInt(t4.substring(0,2)) == 24)
                    t4 = "12:00 am";
                else if(Integer.parseInt(t4.substring(0,2)) == 12)
                    t4 = "12:00 pm";
                else{
                    if(Integer.parseInt(t4.substring(0,2)) > 12)
                        t1 = Integer.parseInt(t4.substring(0,2)) % 12 + ":00 pm";
                    else
                        t4 = Integer.parseInt(t4.substring(0,2)) % 12 + ":00 am";
                }
                times.add(t5);
                if(Integer.parseInt(t5.substring(0,2)) == 24)
                    t5 = "12:00 am";
                else if(Integer.parseInt(t5.substring(0,2)) == 12)
                    t5 = "12:00 pm";
                else{
                    if(Integer.parseInt(t5.substring(0,2)) > 12)
                        t5 = Integer.parseInt(t5.substring(0,2)) % 12 + ":00 pm";
                    else
                        t5 = Integer.parseInt(t5.substring(0,2)) % 12 + ":00 am";
                }
                tmax1 = ((JSONObject)(jo.getJSONArray("list").get(0))).getJSONObject("main").getDouble("temp_max");
                tmax2 = ((JSONObject)(jo.getJSONArray("list").get(1))).getJSONObject("main").getDouble("temp_max");
                tmax3 = ((JSONObject)(jo.getJSONArray("list").get(2))).getJSONObject("main").getDouble("temp_max");
                tmax4 = ((JSONObject)(jo.getJSONArray("list").get(3))).getJSONObject("main").getDouble("temp_max");
                tmax5 = ((JSONObject)(jo.getJSONArray("list").get(4))).getJSONObject("main").getDouble("temp_max");
                desc1 = jo.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("id").toString();
                desc2 = jo.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).get("id").toString();
                desc3 = jo.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).get("id").toString();
                desc4 = jo.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).get("id").toString();
                desc5 = jo.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).get("id").toString();
                descs.add(desc1);
                descs.add(desc2);
                descs.add(desc3);
                descs.add(desc4);
                descs.add(desc5);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            location.setText(city);

            day2.setText(d2);
            day3.setText(d3);
            day4.setText(d4);
            day5.setText(d5);

            time2.setText(t2);
            time3.setText(t3);
            time4.setText(t4);
            time5.setText(t5);

            max1.setText("Hi: " + tmax1 + " °F");
            max2.setText("Hi: " + tmax2 + " °F");
            max3.setText("Hi: " + tmax3 + " °F");
            max4.setText("Hi: " + tmax4 + " °F");
            max5.setText("Hi: " + tmax5 + " °F");

            min1.setText("Lo: " + tmin1 + " °F");
            min2.setText("Lo: " + tmin2 + " °F");
            min3.setText("Lo: " + tmin3 + " °F");
            min4.setText("Lo: " + tmin4 + " °F");
            min5.setText("Lo: " + tmin5 + " °F");

            latT.setText("Lat: " + lat);
            lonT.setText("Lon: " + lon);

            currentTemp.setText(temp + " °F");
            currentDay.setText(d1);
            switch(desc1.substring(0,1)){
                case "2":
                    img1.setImageResource(R.drawable.thunderstorm);
                    description.setText("\"THUNDER[storm], feel the THUNDER. LIGHTNING and the THUNDER\" - Imagine Dragons");
                    break;
                case "5":
                    img1.setImageResource(R.drawable.rain);
                    description.setText("\"It RAINS, it pours, it RAINS, it pours\" - A$AP Rocky");
                    break;
                case "6":
                    img1.setImageResource(R.drawable.snow);
                    description.setText("\"Let it SNOW! Let it SNOW! Let it SNOW!\" - Sammy Cahn and Jule Styne");
                    break;
                case "7":
                    if(desc1.charAt(1) == '0' || desc1.charAt(1) == '2' || desc1.charAt(1) == '4') {
                        img1.setImageResource(R.drawable.mist);
                        description.setText("\"I'm too MISTY, and too much in love\" - Ella Fitzgerald");
                    }
                    else if(desc1.substring(1,3).equals("62")) {
                        img1.setImageResource(R.drawable.volcano);
                        description.setText("\"Pompeii\" - Bastille. The city of Pompeii and its people were wiped out after the ERUPTION of the VOLCANO: Mount Vesuvius");
                    }
                    else if(desc1.charAt(1) == '7') {
                        img1.setImageResource(R.drawable.wind);
                        description.setText("\"Doves in the WIND\" - SZA. Squalls are sudden bursts of wind");
                    }
                    else if(desc1.charAt(1) == '8')
                        img1.setImageResource(R.drawable.tornado);
                case "8":
                    if(desc1.equals("800")) {
                        img1.setImageResource(R.drawable.sun);
                        description.setText("\"Baby, baby, you're my SUN and moon\" - Anees");
                    }
                    else {
                        img1.setImageResource(R.drawable.cloud);
                        description.setText("\"Get off my CLOUD\" - The Rolling Stones");
                    }
                    break;
            }
            switch(desc2.substring(0,1)){
                case "2":
                    img2.setImageResource(R.drawable.thunderstorm);
                    break;
                case "5":
                    img2.setImageResource(R.drawable.rain);
                    break;
                case "6":
                    img2.setImageResource(R.drawable.snow);
                    break;
                case "8":
                    if(desc2.equals("800"))
                        img2.setImageResource(R.drawable.sun);
                    else
                        img2.setImageResource(R.drawable.cloud);
                    break;
            }
            switch(desc3.substring(0,1)){
                case "2":
                    img3.setImageResource(R.drawable.thunderstorm);
                    break;
                case "5":
                    img3.setImageResource(R.drawable.rain);
                    break;
                case "6":
                    img3.setImageResource(R.drawable.snow);
                    break;
                case "8":
                    if(desc3.equals("800"))
                        img3.setImageResource(R.drawable.sun);
                    else
                        img3.setImageResource(R.drawable.cloud);
                    break;
            }
            switch(desc4.substring(0,1)){
                case "2":
                    img4.setImageResource(R.drawable.thunderstorm);
                    break;
                case "5":
                    img4.setImageResource(R.drawable.rain);
                    break;
                case "6":
                    img4.setImageResource(R.drawable.snow);
                    break;
                case "8":
                    if(desc4.equals("800"))
                        img4.setImageResource(R.drawable.sun);
                    else
                        img4.setImageResource(R.drawable.cloud);
                    break;
            }
            switch(desc5.substring(0,1)){
                case "2":
                    img5.setImageResource(R.drawable.thunderstorm);
                    break;
                case "5":
                    img5.setImageResource(R.drawable.rain);
                    break;
                case "6":
                    img5.setImageResource(R.drawable.snow);
                    break;
                case "8":
                    if(desc5.equals("800"))
                        img5.setImageResource(R.drawable.sun);
                    else
                        img5.setImageResource(R.drawable.cloud);
                    break;
            }

            // update UI
        }
    }
}