package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Elements content;
    public ArrayList<String> List = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    public ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.list_view);
        new NewThread().execute();
        adapter = new ArrayAdapter<String>(this, R.layout.list, R.id.item, List);

    }

    public class NewThread extends AsyncTask<String,Void,String>{
        protected String doInBackground(String... arg){
            Document doc;
            try{
                doc = Jsoup.connect("https://jsoup.org/cookbook/input/load-document-from-url").get(); //official jsoup site
                content = doc.select(".col1");
                List.clear();
                for(Element el:content){
                    List.add(el.text());
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String result){
            lv.setAdapter(adapter);
        }
    }
}
