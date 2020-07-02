package com.example.recyclerdemo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<String> mWordList = new LinkedList<String>();

    private RecyclerView mRecyclerView;

    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        for (int i = 0 ; i < 20 ; i++) {

            mWordList.addLast("Word " + i); //populate the mWordList linked list

        }


        mRecyclerView = findViewById(R.id.recycler_view);

        mAdapter = new WordListAdapter(this, mWordList); //create a new adapter mAdapter for the recycler view from the WordListAdapter class

        mRecyclerView.setAdapter(mAdapter); //make the recycler view use that adapter

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int wordListSize = mWordList.size();

                mWordList.addLast("+ Word " + wordListSize);

                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);

                mRecyclerView.smoothScrollToPosition(wordListSize);

                Snackbar.make(view, wordListSize + "th word added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {

            mWordList.clear();

            for (int i = 0 ; i < 20 ; i++) {

                mWordList.addLast("Word " + i);

            }

            mAdapter.notifyDataSetChanged();

        }

        return super.onOptionsItemSelected(item);
    }
}