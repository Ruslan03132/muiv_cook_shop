package MUIV_Recipes_app.ruslan_borislavovich.MUIVRecipes.MUIVRecipes;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class PizzaActivity extends AppCompatActivity  {


    private RecyclerViewAdapter adapter;
    private ArrayList <InfoCardItem> cardItems;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_activity);

        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cardItemsInitialization();
        settingsRecyclerView();
    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return true;
    }


    private void cardItemsInitialization(){
        cardItems = new ArrayList<>();
        cardItems.add(new InfoCardItem(R.drawable.pizza1, R.string.pizzaHeading_1, R.string.pizzaInfo_1, R.string.pizzaStructure_1,SearchTitle.pizzaTitle1));
        cardItems.add(new InfoCardItem(R.drawable.pizza2, R.string.pizzaHeading_2, R.string.pizzaInfo_2 ,R.string.pizzaStructure_2 ,SearchTitle.pizzaTitle2));
        cardItems.add(new InfoCardItem(R.drawable.pizza3, R.string.pizzaHeading_3, R.string.pizzaInfo_3, R.string.pizzaStructure_3,SearchTitle.pizzaTitle3));
        cardItems.add(new InfoCardItem(R.drawable.pizza4, R.string.pizzaHeading_4, R.string.pizzaInfo_4,R.string.pizzaStructure_4,SearchTitle.pizzaTitle4));
        cardItems.add(new InfoCardItem(R.drawable.pizza5,R.string.pizzaHeading_5, R.string.pizzaInfo_5,R.string.pizzaStructure_5,SearchTitle.pizzaTitle5));
        cardItems.add(new InfoCardItem(R.drawable.pizza6,R.string.pizzaHeading_6, R.string.pizzaInfo_6,R.string.pizzaStructure_6,SearchTitle.pizzaTitle6));
        cardItems.add(new InfoCardItem(R.drawable.pizza7, R.string.pizzaHeading_7, R.string.pizzaInfo_7,R.string.pizzaStructure_7,SearchTitle.pizzaTitle7));
        cardItems.add(new InfoCardItem(R.drawable.pizza8, R.string.pizzaHeading_8, R.string.pizzaInfo_8,R.string.pizzaStructure_8,SearchTitle.pizzaTitle8));
        cardItems.add(new InfoCardItem(R.drawable.pizza9, R.string.pizzaHeading_9,R.string.pizzaInfo_9,R.string.pizzaStructure_9,SearchTitle.pizzaTitle9));
        cardItems.add(new InfoCardItem(R.drawable.pizza10, R.string.pizzaHeading_10,R.string.pizzaInfo_10,R.string.pizzaStructure_10,SearchTitle.pizzaTitle10));
        cardItems.add(new InfoCardItem(R.drawable.pizza11, R.string.pizzaHeading_11,R.string.pizzaInfo_11,R.string.pizzaStructure_11,SearchTitle.pizzaTitle11));
        cardItems.add(new InfoCardItem(R.drawable.pizza12, R.string.pizzaHeading_12,R.string.pizzaInfo_12,R.string.pizzaStructure_12,SearchTitle.pizzaTitle12));
        cardItems.add(new InfoCardItem(R.drawable.pizza13, R.string.pizzaHeading_13,R.string.pizzaInfo_13,R.string.pizzaStructure_13,SearchTitle.pizzaTitle13));
        cardItems.add(new InfoCardItem(R.drawable.pizza14, R.string.pizzaHeading_14,R.string.pizzaInfo_14,R.string.pizzaStructure_14,SearchTitle.pizzaTitle14));
        cardItems.add(new InfoCardItem(R.drawable.pizza15, R.string.pizzaHeading_15,R.string.pizzaInfo_15,R.string.pizzaStructure_15,SearchTitle.pizzaTitle15));
        cardItems.add(new InfoCardItem(R.drawable.pizza16, R.string.pizzaHeading_16,R.string.pizzaInfo_16,R.string.pizzaStructure_16,SearchTitle.pizzaTitle16));
        cardItems.add(new InfoCardItem(R.drawable.pizza17, R.string.pizzaHeading_17,R.string.pizzaInfo_17,R.string.pizzaStructure_17,SearchTitle.pizzaTitle17));
        cardItems.add(new InfoCardItem(R.drawable.pizza18, R.string.pizzaHeading_18,R.string.pizzaInfo_18,R.string.pizzaStructure_18,SearchTitle.pizzaTitle18));
        cardItems.add(new InfoCardItem(R.drawable.pizza19, R.string.pizzaHeading_19,R.string.pizzaInfo_19,R.string.pizzaStructure_19,SearchTitle.pizzaTitle19));
        cardItems.add(new InfoCardItem(R.drawable.pizza20, R.string.pizzaHeading_20,R.string.pizzaInfo_20,R.string.pizzaStructure_20,SearchTitle.pizzaTitle20));
        cardItems.add(new InfoCardItem(R.drawable.pizza21, R.string.pizzaHeading_21,R.string.pizzaInfo_21,R.string.pizzaStructure_21,SearchTitle.pizzaTitle21));
        cardItems.add(new InfoCardItem(R.drawable.pizza22, R.string.pizzaHeading_22,R.string.pizzaInfo_22,R.string.pizzaStructure_22,SearchTitle.pizzaTitle22));
        cardItems.add(new InfoCardItem(R.drawable.pizza23, R.string.pizzaHeading_23,R.string.pizzaInfo_23,R.string.pizzaStructure_23,SearchTitle.pizzaTitle23));
        cardItems.add(new InfoCardItem(R.drawable.pizza24, R.string.pizzaHeading_24,R.string.pizzaInfo_24,R.string.pizzaStructure_24,SearchTitle.pizzaTitle24));
        cardItems.add(new InfoCardItem(R.drawable.pizza25, R.string.pizzaHeading_25,R.string.pizzaInfo_25,R.string.pizzaStructure_25,SearchTitle.pizzaTitle25));
        cardItems.add(new InfoCardItem(R.drawable.pizza26, R.string.pizzaHeading_26,R.string.pizzaInfo_26,R.string.pizzaStructure_26,SearchTitle.pizzaTitle26));
        cardItems.add(new InfoCardItem(R.drawable.pizza27, R.string.pizzaHeading_27,R.string.pizzaInfo_27,R.string.pizzaStructure_27,SearchTitle.pizzaTitle27));
        cardItems.add(new InfoCardItem(R.drawable.pizza28, R.string.pizzaHeading_28,R.string.pizzaInfo_28,R.string.pizzaStructure_28,SearchTitle.pizzaTitle28));
        cardItems.add(new InfoCardItem(R.drawable.pizza29, R.string.pizzaHeading_29,R.string.pizzaInfo_29,R.string.pizzaStructure_29,SearchTitle.pizzaTitle29));
        cardItems.add(new InfoCardItem(R.drawable.pizza30, R.string.pizzaHeading_30,R.string.pizzaInfo_30,R.string.pizzaStructure_30,SearchTitle.pizzaTitle30));

    }
    private void settingsRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMain);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        adapter = new RecyclerViewAdapter(cardItems, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);


        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


}
