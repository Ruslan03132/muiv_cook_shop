package MUIV_Recipes_app.ruslan_borislavovich.MUIVRecipes.MUIVRecipes;

import android.content.Intent;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Data.DatabaseHandler;
import Model.Menu;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    public static DatabaseHandler databaseHandler;

    ArrayList<HashMap<String, Object>> listBasket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingsToolbarNavigationViewDrawerLayout();
        dataBaseCreate();

        /*Gson gson = new Gson();
        String json = sharedPreferences.getString("shared preferences", null);
        Type type = new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType();
        listBasket = gson.fromJson(json, type);
        Log.v("listBasketCount",String.valueOf(listBasket.size()));*/
    }

    public void dataBaseCreate(){
        databaseHandler = new DatabaseHandler(this);

/*        databaseHandler.addRecipe(new Menu("Простая пицца", 300, 350), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца с копченой колбасой", 300, 370), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца Маргарита", 400, 400), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца Неаполитана", 340, 450), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца Три сыра с зеленым салатом", 300, 300), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца Ортолана", 310, 300), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца черноморская", 350, 200), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца Пепперони", 360, 300), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Овощная пицца с моцареллой", 300, 300), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца с колбасой, помидорами и сыром", 300, 300), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца с грибами", 380, 330), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Тонкая пицца", 300, 330), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца с тунцом, морской капустой и пармезаном", 300, 330), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Кабачковая пицца", 330, 330), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца BBQ", 305, 200), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца со слабосоленым лососем", 300, 380), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца Италия", 309, 380), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца Домашняя", 399, 380), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Яблочная пицца", 355, 380), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца со шпинатом, помидорами и клубникой", 300, 380), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца мои друзья", 345, 200), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца Диабло", 370, 200), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Гавайская пицца с ананасом и окороком, на сковороде", 300, 310), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Белая пицца с овощами и песто", 300, 310), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца с беконом", 308, 310), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца Барбекю", 308, 310), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Сырная пицца с брокколи", 300, 310), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца с чоризо", 304, 200), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Пицца с креветками и овощами", 415, 310), "pizzaRecipes");
        databaseHandler.addRecipe(new Menu("Тесто для пиццы (базовый рецепт)", 349, 310), "pizzaRecipes");


        databaseHandler.addRecipe(new Menu("Салат с сухариками \"Королевский\"", 250, 250), "salatRecipes");
        databaseHandler.addRecipe(new Menu("Салат \"Мужские слезы\"", 270, 260), "salatRecipes");
        databaseHandler.addRecipe(new Menu("Cалат из грибов и курицы", 299, 270), "salatRecipes");
        databaseHandler.addRecipe(new Menu("Салат с копчёной курицей", 349, 280), "salatRecipes");


        databaseHandler.addRecipe(new Menu("Сырный крем-суп", 359, 380), "soupRecipes");
        databaseHandler.addRecipe(new Menu("Постный гороховый суп", 357, 380), "soupRecipes");
        databaseHandler.addRecipe(new Menu("Постный луковый суп", 399, 380), "soupRecipes");
        databaseHandler.addRecipe(new Menu("Финский рыбный суп с копченой семгой", 459, 380), "soupRecipes");

                databaseHandler.addRecipe(new Menu("Плов из баранины", 352, 420), "meatRecipes");
        databaseHandler.addRecipe(new Menu("Ленивые голубцы с фаршем и рисом", 359, 400), "meatRecipes");
        databaseHandler.addRecipe(new Menu("Классический шашлык из баранины", 419, 410), "meatRecipes");
        databaseHandler.addRecipe(new Menu("Капустная запеканка с фаршем в духовке", 379, 390), "meatRecipes");*/

        List<Menu> mListSalat = databaseHandler.getAllRecipes("salatRecipes");
        List<Menu> mListPizza = databaseHandler.getAllRecipes("pizzaRecipes");
        List<Menu> mListSoup = databaseHandler.getAllRecipes("soupRecipes");
        List<Menu> mListMeat = databaseHandler.getAllRecipes("meatRecipes");
/*        for (Reciepes reciepes: mListSalat ) {
            databaseHandler.deleteRecipe(reciepes, "salatRecipes");
        }
        for (Reciepes reciepes: mListPizza ) {
            databaseHandler.deleteRecipe(reciepes, "pizzaRecipes");
        }*/

        for (Menu menu : mListSalat ){
            Log.d("SalatBaseInfo", menu.getId() + menu.getTitle() + menu.getPrice() + menu.getWeight());
        }
        for (Menu menu : mListPizza ){
            Log.d("PizzaBaseInfo", menu.getId() + menu.getTitle() + menu.getPrice() + menu.getWeight());
        }
        for (Menu menu : mListSoup ){
            Log.d("SoupBaseInfo", menu.getId() + menu.getTitle() + menu.getPrice() + menu.getWeight());
        }
        for (Menu menu : mListMeat ){
            Log.d("MeatBaseInfo", menu.getId() + menu.getTitle() + menu.getPrice() + menu.getWeight());
        }



    }

    private void settingsToolbarNavigationViewDrawerLayout(){
        toolbar = findViewById(R.id.toolbarMainActivity);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open ,R.string.navigation_drawer_close){
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.favorite:
                Intent intentFavorite = new Intent(this, FavoriteActivity.class);
                startActivity(intentFavorite);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.basket:
                Intent intentBasket = new Intent(this, basket_activity.class);
                intentBasket.putExtra("context","basket");
                startActivity(intentBasket);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.thumb:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.more:
                Intent intentMore = new Intent(this, MoreActivity.class);
                startActivity(intentMore);
                drawer.closeDrawer(GravityCompat.START);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return true;
    }

    public void openPizza(View view) {
        Intent intent = new Intent(this, PizzaActivity.class);

        startActivity(intent);
    }
    public void openSalat (View view){
        Intent intent = new Intent(this, salatActivity.class);
        startActivity(intent);
    }
    public void openSoup (View view){
        Intent intent = new Intent(this, activity_soup.class);
        startActivity(intent);
    }
    public void openMeat (View view){
        Intent intent = new Intent(this, activity_meat.class);
        startActivity(intent);
    }
}