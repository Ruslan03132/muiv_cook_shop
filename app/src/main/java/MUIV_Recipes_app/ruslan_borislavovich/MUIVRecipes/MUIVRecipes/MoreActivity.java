package MUIV_Recipes_app.ruslan_borislavovich.MUIVRecipes.MUIVRecipes;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import Model.Menu;


public class MoreActivity extends AppCompatActivity {

Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView more =  findViewById(R.id.more);
        TextView moreVersion =  findViewById(R.id.moreVersion);


        more.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD_ITALIC);
        moreVersion.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD_ITALIC);




        Menu menu = MainActivity.databaseHandler.getRecipes(1, "salatRecipes");
        Log.d("recipesSalat", String.valueOf(menu.getPrice()));
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();


        }
        return true;
    }


}
