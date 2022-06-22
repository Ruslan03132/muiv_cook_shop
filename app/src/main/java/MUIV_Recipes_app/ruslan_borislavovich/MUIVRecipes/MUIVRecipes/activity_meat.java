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

public class activity_meat extends AppCompatActivity {

    private RecyclerViewAdapterMeat adapterMeat;
    private ArrayList<InfoCardItem> cardItemsMeat;;
    Toolbar toolbarMeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat);

        toolbarMeat = findViewById(R.id.meatToolbar);
        setSupportActionBar(toolbarMeat);
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
        cardItemsMeat = new ArrayList<>();
        cardItemsMeat.add( new InfoCardItem(R.drawable.meat1, R.string.meatHeading_1, R.string.meatInfo1,R.string.meat_Structure_1, SearchTitle.meatTitle1));
        cardItemsMeat.add( new InfoCardItem(R.drawable.meat2, R.string.meatHeading_2, R.string.meatInfo2, R.string.meat_Structure_2,SearchTitle.meatTitle2));
        cardItemsMeat.add( new InfoCardItem(R.drawable.meat3, R.string.meatHeading_3, R.string.meatInfo3, R.string.meat_Structure_3,SearchTitle.meatTitle3));
        cardItemsMeat.add( new InfoCardItem(R.drawable.meat4, R.string.meatHeading_4, R.string.meatInfo4, R.string.meat_Structure_4,SearchTitle.meatTitle4));
    }
    private void settingsRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMeat);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        adapterMeat = new RecyclerViewAdapterMeat(cardItemsMeat, this);

        recyclerView.setAdapter(adapterMeat);
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
                adapterMeat.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}