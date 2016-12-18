package com.ieeecsvit.riviera17android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.RVEventListAdapter;
import com.ieeecsvit.riviera17android.RealmController;
import com.ieeecsvit.riviera17android.WishlistAdapter;
import com.ieeecsvit.riviera17android.models.Event;

import org.w3c.dom.Text;

public class WishlistActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    WishlistAdapter wishlistAdapter,item_countAdapter;
    TextView wish_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Wishlist");

        wish_text=(TextView)findViewById(R.id.wish_text);
        wish_text.setText("");

        recyclerView = (RecyclerView) findViewById(R.id.rv_wishlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wishlistAdapter= new WishlistAdapter(RealmController.with(this).getEvents(),this);

        item_countAdapter=new WishlistAdapter(RealmController.with(this).getRealm().where(Event.class).equalTo("checked",true).findAll(),this);

        if(item_countAdapter.getItemCount()==0){
            recyclerView.setAdapter(new RVEventListAdapter(RealmController.with(this).getRealm().where(Event.class).equalTo("checked",true).findAll(),this,true));
            wish_text.setText("No Events Added To Wishlist!");
        }
        else if(item_countAdapter.getItemCount()>0){
            recyclerView.setAdapter(new RVEventListAdapter(RealmController.with(this).getRealm().where(Event.class).equalTo("checked",true).findAll(),this,true));
            wish_text.setText("");
        }
    }
}
