package MUIV_Recipes_app.ruslan_borislavovich.MUIVRecipes.MUIVRecipes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;



public class RecipeActivityPizza extends AppCompatActivity   {
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
        toolbar = findViewById(R.id.toolbarItem);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadData();
        loadDataFlag();

        title = findViewById(R.id.titleTextView);
        recipe = findViewById(R.id.recipeTextView);
        imagePizza = findViewById(R.id.image_basket);
        description = findViewById(R.id.description);
        intent = getIntent();
        if (intent != null){
            context = intent.getStringExtra("context");
            Log.v("222222",context);


                title.setText(intent.getIntExtra("title", 0));
                recipe.setText(intent.getIntExtra("recipe", 0));
                imagePizza.setImageResource(intent.getIntExtra("imageResourse", 0));
                toolbar.setTitle(intent.getIntExtra("title", 0));
                description.setText(intent.getIntExtra("description", 0));
                adapterPosition = intent.getIntExtra("adapterPosition", 0);
                titleForSearch = intent.getStringExtra("title");


            switch (context) {
                case "favorite":
                    context ="favorite";
                    break;
                case "main":
                    context ="main";
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        if (context.equals("main")){
            Intent intent = new Intent(this, PizzaActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }
        else if (context.equals("favorite")){
            Intent intent = new Intent(this, FavoriteActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("favoritelist", null);
        Type type = new TypeToken<ArrayList<InfoCardItem>>(){}.getType();
        favoriteArrayItems = gson.fromJson(json, type);
        if (favoriteArrayItems == null){
            favoriteArrayItems = new ArrayList<>();
        }
    }
    private void loadDataFlag(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPreferences.getString("arrayListFlags", null);
        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        arrayListFlags = gson.fromJson(json, type);

        if (arrayListFlags == null){
            arrayListFlags = new ArrayList<>(countCards);
        }
    }
    private void saveDataFlag(){

        Gson gson = new Gson();
        String json = gson.toJson(arrayListFlags);
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("arrayListFlags", json);
        editor.apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_menu, menu);
        MenuItem favoriteItem = menu.findItem(R.id.favorite);
        for (int i = 0 ; i < arrayListFlags.size(); i++) {
            if (context.equals("main")) {
                if (arrayListFlags.get(i) == adapterPosition) {
                    favoriteItem.setIcon(R.drawable.ic_favorite_red_24dp);
                    flagOnCreate = true;
                    break;
                } else {
                    favoriteItem.setIcon(R.drawable.ic_favorite_white_24dp);
                    flagOnCreate = false;
                }
            }
            else if (context.equals("favorite")){
                if (i == adapterPosition) {
                    favoriteItem.setIcon(R.drawable.ic_favorite_red_24dp);
                    flagOnCreate = true;
                    break;
                } else {
                    favoriteItem.setIcon(R.drawable.ic_favorite_white_24dp);
                    flagOnCreate = false;
                }
            }

        }

        favoriteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {


            private void saveDataAddRecipe(){
                Log.v("favoriteArrayItemsCount",String.valueOf(favoriteArrayItems.size()));

                favoriteArrayItems.add(new InfoCardItem(intent.getIntExtra("imageResourse",0),
                        intent.getIntExtra("title",0),
                        intent.getIntExtra("description",0),
                        intent.getIntExtra("recipe",0)));
                Log.v("favoriteArrayItemsCount",String.valueOf(favoriteArrayItems.size()));
                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(favoriteArrayItems);
                editor.putString("favoritelist", json);

                editor.apply();
            }
            private void saveDataDeleteRecipe(){
                for (int i = 0; i < arrayListFlags.size(); i++){
                    if (context.equals("main")) {
                        if (arrayListFlags.get(i) == adapterPosition) {
                            favoriteArrayItems.remove(i);
                            arrayListFlags.remove(i);
                        }
                    }
                    else if (context.equals("favorite")){
                        if (i == adapterPosition) {
                            favoriteArrayItems.remove(i);
                            arrayListFlags.remove(i);
                        }
                    }

                }

                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(favoriteArrayItems);
                editor.putString("favoritelist", json);
                editor.apply();
            }


            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){

                    case (R.id.favorite):

                        if (flagOnCreate) {
                            Toast.makeText(RecipeActivityPizza.this, "Блюдо удалено из Избранного!", Toast.LENGTH_SHORT).show();
                            item.setIcon(R.drawable.ic_favorite_white_24dp);
                            flagOnCreate = false;
                            saveDataDeleteRecipe();
                            saveDataFlag();
                        }
                        else {
                            Toast.makeText(RecipeActivityPizza.this, "Блюдо добавлено в Избранное!", Toast.LENGTH_SHORT).show();
                            item.setIcon(R.drawable.ic_favorite_red_24dp);
                            flagOnCreate = true;
                            arrayListFlags.add(adapterPosition);
                            saveDataAddRecipe();
                            saveDataFlag();
                        }
                }
                return true;
            }
        });

        return true;
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
