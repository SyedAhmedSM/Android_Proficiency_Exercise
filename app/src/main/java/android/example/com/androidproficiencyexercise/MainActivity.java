package android.example.com.androidproficiencyexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ArticleItems> articlesDetails;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        GetDataFromServer();
    }

    private void GetDataFromServer() {

        try{
            getJsonData getDataFromServer = new getJsonData();
            String url = "https://dl.dropboxusercontent.com/u/746330/facts.json";
            getDataFromServer.getArticles(url, new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            String responseString = response.body().string();
                            Log.d("responsefromarticles", responseString);
                            articlesDetails = ParseJson(responseString);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //stuff that updates ui
                                    listView.setAdapter(new CustomAdapter(MainActivity.this,articlesDetails));
                                    progress.dismiss();
                                }
                            });
                        }
                    }
            );
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
