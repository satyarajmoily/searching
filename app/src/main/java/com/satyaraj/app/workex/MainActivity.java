package com.satyaraj.app.workex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.satyaraj.app.workex.pojo.Work;

import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private Adapter mAdapter;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);

        search = findViewById(R.id.search);

        MainRepository mainRepository = new MainRepository();
        final MainPresenter mainPresenter = new MainPresenter(this, mainRepository);
        mAdapter = new Adapter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mainPresenter.getList();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mainPresenter.searchChar(s);
            }
        });
    }

    public void displayList(List<Work> workList){
        mAdapter.updateList(workList);
    }

}
