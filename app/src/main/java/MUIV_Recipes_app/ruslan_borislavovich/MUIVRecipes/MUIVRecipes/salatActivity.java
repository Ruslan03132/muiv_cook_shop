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

public class salatActivity extends AppCompatActivity {

    private RecyclerViewAdapterSalat adapterSalat;
    private ArrayList<InfoCardItem> cardItemsSalat;
    Toolbar toolbarSalat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salat);

        toolbarSalat = findViewById(R.id.salatToolbar);
        setSupportActionBar(toolbarSalat);
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
        cardItemsSalat = new ArrayList<>();
        cardItemsSalat.add( new InfoCardItem(R.drawable.salat1, R.string.salatHeading_1, R.string.salatInfo1,R.string.salat_Structure_1, SearchTitle.salatTitle1));
        cardItemsSalat.add( new InfoCardItem(R.drawable.salat2, R.string.salatHeading_2, R.string.salatInfo2, R.string.salat_Structure_2,SearchTitle.salatTitle2));
        cardItemsSalat.add( new InfoCardItem(R.drawable.salat3, R.string.salatHeading_3, R.string.salatInfo3, R.string.salat_Structure_3,SearchTitle.salatTitle3));
        cardItemsSalat.add( new InfoCardItem(R.drawable.salat4, R.string.salatHeading_4, R.string.salatIndo4, R.string.salat_Structure_4,SearchTitle.salatTitle4));
    }
    private void settingsRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewSalat);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        adapterSalat = new RecyclerViewAdapterSalat(cardItemsSalat, this);

        recyclerView.setAdapter(adapterSalat);
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
                adapterSalat.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}