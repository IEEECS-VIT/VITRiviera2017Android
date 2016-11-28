package com.ieeecsvit.riviera17android;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.utility.Consts;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    ImageView catimage, back_image;
    String getCat, getSubCat;
    TextView catTextView, subCatTextView;
    View view;
    LinearLayout subCatLinearLayout;
    ListView lvSubCategory;
    RecyclerView recyclerView;
    List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getCat = getIntent().getStringExtra(Consts.INTENT_CATEGORY);
        getSubCat = getIntent().getStringExtra(Consts.INTENT_SUB_CATEGORY);

        getSupportActionBar().setTitle("Riviera'17");

        catTextView = (TextView) findViewById(R.id.typeCategory);
        catTextView.setText(getCat);

        subCatTextView = (TextView) findViewById(R.id.tvSubCategory);

        subCatLinearLayout = (LinearLayout) findViewById(R.id.ll_sub_category);
        lvSubCategory = (ListView) findViewById(R.id.lv_sub_category);

        stringList = new ArrayList<>();
        stringList.add("Words Worth English");
        stringList.add("Words Worth Hindi");
        stringList.add("Words Worth Tamil");
        stringList.add("Drama");
        stringList.add("Workshop");
        stringList.add("Dance");
        stringList.add("Cyber Engage");
        stringList.add("Quiz Events");
        stringList.add("Others");

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        view = findViewById(R.id.catLine);
        back_image = (ImageView) findViewById(R.id.back_image);
        catimage = (ImageView) findViewById(R.id.categoryimage);

        recyclerView = (RecyclerView) findViewById(R.id.rv_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        switch (getCat) {
            case "Pre-Riviera":
                catimage.setImageResource(R.drawable.preriv_ic);
                back_image.setImageResource(R.drawable.preriv_back);
                view.setBackgroundColor(Color.parseColor("#FDA736"));
                recyclerView.setAdapter(new RVEventListAdapter(RealmController.with(this).getEvents(getCat), this, true));
                break;
            case "Workshop":
                catimage.setImageResource(R.drawable.preriv_ic);
                back_image.setImageResource(R.drawable.workshop_back);
                view.setBackgroundColor(Color.parseColor("#FDA736"));
                recyclerView.setAdapter(new RVEventListAdapter(RealmController.with(this).getEvents(getCat), this, true));
                break;
            case "Formal":
                catimage.setImageResource(R.drawable.preriv_ic);
                back_image.setImageResource(R.drawable.formal_back);
                view.setBackgroundColor(Color.parseColor("#FDA736"));
                if (getSubCat == null) {
                    showSubCategories();
                } else {
                    showSubEvents();
                }
                break;
            case "Informal":
                catimage.setImageResource(R.drawable.preriv_ic);
                back_image.setImageResource(R.drawable.informal_back);
                view.setBackgroundColor(Color.parseColor("#FDA736"));
                if (getSubCat == null) {
                    showSubCategories();
                } else {
                    showSubEvents();
                }
                break;
            case "Adventure Sports":
                catimage.setImageResource(R.drawable.preriv_ic);
                back_image.setImageResource(R.drawable.cyber);
                view.setBackgroundColor(Color.parseColor("#FDA736"));
                recyclerView.setAdapter(new RVEventListAdapter(RealmController.with(this).getEvents(getCat), this, true));
        }
    }

    private void showSubCategories() {
        lvSubCategory.setAdapter(new SubCategoryListAdapter(this, stringList, getCat));
        subCatLinearLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void showSubEvents() {
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(new RVEventListAdapter(RealmController.with(this).getSubEvents(getCat, getSubCat), this, true));
        subCatTextView.setVisibility(View.VISIBLE);
        subCatTextView.setText(getIntent().getStringExtra(Consts.INTENT_SUB_CATEGORY));
    }
}
