package MUIV_Recipes_app.ruslan_borislavovich.MUIVRecipes.MUIVRecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import java.util.ArrayList;

public class activity_soup extends AppCompatActivity {

    private RecyclerViewAdapterSoup adapterSoup;
    private ArrayList<InfoCardItem> cardItemsSoup;;
    Toolbar toolbarSoup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soup);

        toolbarSoup = findViewById(R.id.soupToolbar);
        setSupportActionBar(toolbarSoup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        salatCardItemsInitialization();
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
    private void salatCardItemsInitialization(){
        cardItemsSoup = new ArrayList<>();
        cardItemsSoup.add( new InfoCardItem(R.drawable.soup1, R.string.soupHeading_1, R.string.soupInfo1,R.string.soup_Structure_1, SearchTitle.soupTitle1));
        cardItemsSoup.add( new InfoCardItem(R.drawable.soup2, R.string.soupHeading_2, R.string.soupInfo2, R.string.soup_Structure_2,SearchTitle.soupTitle2));
        cardItemsSoup.add( new InfoCardItem(R.drawable.soup3, R.string.soupHeading_3, R.string.soupInfo3, R.string.soup_Structure_3,SearchTitle.soupTitle3));
        cardItemsSoup.add( new InfoCardItem(R.drawable.soup4, R.string.soupHeading_4, R.string.soupInfo4, R.string.soup_Structure_4,SearchTitle.soupTitle4));
    }
    private void settingsRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewSoup);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        adapterSoup = new RecyclerViewAdapterSoup(cardItemsSoup, this);

        recyclerView.setAdapter(adapterSoup);
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
                adapterSoup.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}