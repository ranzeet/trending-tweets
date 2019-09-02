package com.example.twitterclient;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import javax.xml.datatype.Duration;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MainActivity extends AppCompatActivity {
    private Button startBtn, stopBtn;
    private TextView myTextView;
    private RecyclerView myRecyclerView;
    private TweetAdapter tweetAdapter;
    private ProgressBar mProgressBar;
    private LinearLayoutManager layoutManager;
    private ArrayList<String> tweetList = new ArrayList<>();
    private ActionBar actionBar;

    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.start);
        stopBtn = (Button) findViewById(R.id.stop);
        myTextView = (TextView) findViewById(R.id.output);
        myRecyclerView = findViewById(R.id.list_names);
        mProgressBar = findViewById(R.id.loading_progress);
        client = new OkHttpClient();

        layoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, true);
        myRecyclerView.setLayoutManager(layoutManager);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Connection Closed !",
                        Toast.LENGTH_SHORT).show();
                client.dispatcher().executorService().shutdown();
                finishAndRemoveTask();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_close:
                Toast.makeText(getApplicationContext(),"Connection Closed !",
                        Toast.LENGTH_SHORT).show();
                client.dispatcher().executorService().shutdown();
                finishAndRemoveTask();
                break;

                default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);

    }

    private void start() {
        startBtn.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        Request request = new Request.Builder().url("ws://192.168.0.104:8080/socket").build();
        MyWebSocketListener listener = new MyWebSocketListener();

        //Connection Establishment
        WebSocket ws = client.newWebSocket(request, listener);
    }

    private void output() {
        /*tweetAdapter = new TweetAdapter(MainActivity.this, tweetList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, true);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(tweetAdapter);*/

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tweetAdapter = new TweetAdapter(MainActivity.this, tweetList);
                myRecyclerView.setAdapter(tweetAdapter);
                layoutManager.smoothScrollToPosition(myRecyclerView,null,tweetList.size() - 1);
                mProgressBar.setVisibility(View.GONE);
                myRecyclerView.setVisibility(View.VISIBLE);

            }
        });
    }

    private final class MyWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            webSocket.send("Hello, I am Android Client");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            if (!tweetList.contains(text)) {
                tweetList.add(text);
                output();
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {

        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, "Good Bye!");
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            tweetList.add(t.getMessage());
            output();
        }
    }
}