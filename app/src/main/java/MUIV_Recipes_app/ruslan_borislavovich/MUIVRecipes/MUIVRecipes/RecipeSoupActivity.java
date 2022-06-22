package MUIV_Recipes_app.ruslan_borislavovich.MUIVRecipes.MUIVRecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeSoupActivity extends AppCompatActivity {

    private final static int countCards = 100;
    TextView title, recipe, description;
    String titleForSearch;
    ImageView imagePizza;
    Toolbar toolbar;
    String context;
    Integer adapterPosition;
    Intent intent;
    Boolean flagOnCreate = false;
    ArrayList<Integer> arrayListFlags;
    public ArrayList <InfoCardItem> favoriteArrayItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_item_contain);

        title = findViewById(R.id.titleTextView);
        recipe = findViewById(R.id.recipeTextView);
        imagePizza = findViewById(R.id.image_basket);
        description = findViewById(R.id.description);
        toolbar = findViewById(R.id.toolbarItem);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent = getIntent();
        if (intent!= null){
            title.setText(intent.getIntExtra("title", 0));
            recipe.setText(intent.getIntExtra("recipe", 0));
            imagePizza.setImageResource(intent.getIntExtra("imageResourse", 0));
            toolbar.setTitle(intent.getIntExtra("title", 0));
            description.setText(intent.getIntExtra("description", 0));
            adapterPosition = intent.getIntExtra("adapterPosition", 0);
            titleForSearch = intent.getStringExtra("title");
        }


    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Intent intent = new Intent(this, salatActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();



    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return true;
    }

}