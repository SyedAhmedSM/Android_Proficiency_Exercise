package android.example.com.androidproficiencyexercise;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ArticleItems> articlesDetails;
    ListView listView;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        callProgressDialog();
        GetDataFromServer();
    }

    private void callProgressDialog(){
        progress = new ProgressDialog(this);
        progress.setMessage("Loading.....");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
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


    public ArrayList<ArticleItems> ParseJson(String response){
        ArrayList<ArticleItems> resultantData = new ArrayList<>();
        try{
            JSONObject jsonObj = new JSONObject(response);
            final String title = jsonObj.getString("title");
            Log.d("title",title);
            //Only the original thread that created a view hierarchy can touch its views.
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    getSupportActionBar().setTitle(title);
                }
            });

            JSONArray articles = jsonObj.getJSONArray("rows");
            Log.d("All articles:", articles.toString());
            resultantData = new ArrayList<>();
            for(int i=0;i<articles.length();i++){
                JSONObject catObj = (JSONObject) articles.get(i);
                final ArticleItems storeEachArticle = new ArticleItems();
                storeEachArticle.setTitle(catObj.getString("title"));
                storeEachArticle.setDescription(catObj.getString("description"));
                storeEachArticle.setHref(catObj.getString("imageHref"));
                resultantData.add(storeEachArticle);
            }
        } catch(JSONException e){
            e.printStackTrace();
        }
        return resultantData;
    }
}
