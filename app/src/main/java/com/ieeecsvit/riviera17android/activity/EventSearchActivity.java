package com.ieeecsvit.riviera17android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.RVEventListAdapter;
import com.ieeecsvit.riviera17android.RealmController;

public class EventSearchActivity extends AppCompatActivity implements TextWatcher{

    EditText inputSearch;
    RVEventListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_search);

        inputSearch = (EditText) findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RVEventListAdapter(RealmController.with(this).getEvents(), this, false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
        // When user changed the Text
        this.adapter.filter(cs.toString());
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterTextChanged(Editable arg0) {
        // TODO Auto-generated method stub
    }
}
