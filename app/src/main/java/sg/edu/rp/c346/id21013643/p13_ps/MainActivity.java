package sg.edu.rp.c346.id21013643.p13_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvTrafficImages;
    AsyncHttpClient client;
    ImageView iv;
    //CustomAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTrafficImages = findViewById(R.id.listviewTrafficImages);
        iv = findViewById(R.id.imageView);
        client = new AsyncHttpClient();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("onResume", "in onResume");
        ArrayList<Traffic> alTrafficImages = new ArrayList<>();
        client.get("https://api.data.gov.sg/v1/transport/traffic-images", new JsonHttpResponseHandler() {

            String timestamp;
            String image;
            int camera_id;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrForecasts = firstObj.getJSONArray("cameras");
                    for (int i = 0; i < jsonArrForecasts.length(); i++) {
                        JSONObject jsonObjForecast = jsonArrForecasts.getJSONObject(i);
                        timestamp = jsonObjForecast.getString("timestamp");
                        image = jsonObjForecast.getString("image");
                        camera_id = jsonObjForecast.getInt("camera_id");
                        Traffic trafficImages = new Traffic (timestamp, image, camera_id);
                        alTrafficImages.add(trafficImages);
                    }
                } catch (JSONException ignored) {

                    //POINT X – Code to display List View
                    ArrayAdapter<Traffic> aaTrafficImages = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, alTrafficImages);
                    // ca = new CustomAdapter(this, R.layout.row, alTrafficImages);
                    lvTrafficImages.setAdapter(aaTrafficImages);
                }
            };
        })//end onResume
        ;}
}