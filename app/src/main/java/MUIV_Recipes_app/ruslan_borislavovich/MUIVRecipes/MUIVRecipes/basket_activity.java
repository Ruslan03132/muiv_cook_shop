package MUIV_Recipes_app.ruslan_borislavovich.MUIVRecipes.MUIVRecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class basket_activity extends AppCompatActivity {

    Toolbar toolbar;

    TextView title, recipe, description;
    String titleForSearch;
    ImageView image;
    HashMap<String, Object> map = new HashMap<>();


    ArrayList<HashMap<String, Object>> listBasket;
    String context;
    Integer adapterPosition;
    Intent intent;
    private static final String TITLE = "titleRecipe";
    private static final String DRAWABLE = "drawable";
    private static final String PRICE = "price";
    private static final String WEIGHT = "weight";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        ListView listView = findViewById(R.id.listViewBasket);
        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent = getIntent();




        if (intent != null){
            context = intent.getStringExtra("context");
            if (context.equals("pizza") || context.equals("salat") || context.equals("soup") || context.equals("meat")){

                Log.v("222222",context);


                loadDataBasket();
                saveDataBasket();



                SimpleAdapter adapter = new SimpleAdapter(this, listBasket,
                        R.layout.layout_basket, new String[]{TITLE, DRAWABLE, PRICE, WEIGHT},
                        new int[]{R.id.title_backet, R.id.image_basket, R.id.price_basket, R.id.weight_basket});

                SharedPreferences sharedPreferences = getSharedPreferences("basket", MODE_PRIVATE);
                String json = sharedPreferences.getString("basketList", null);
                Log.v("json2",json);

                listView.setAdapter(adapter);



            } else if (context.equals("basket")){
                loadDataBasket();
                SimpleAdapter adapter = new SimpleAdapter(this, listBasket,
                        R.layout.layout_basket, new String[]{TITLE, DRAWABLE, PRICE, WEIGHT},
                        new int[]{R.id.title_backet, R.id.image_basket, R.id.price_basket, R.id.weight_basket});

                listView.setAdapter(adapter);
            }

            TextView textViewTotal = (TextView) findViewById(R.id.total);
            Integer total = 0;
            for (int i = 0; i < listBasket.size();i++) {
                Log.v("loadchangeType","loadchangeType");
                for(HashMap.Entry<String, Object> entry : listBasket.get(i).entrySet()) {
                    String key = entry.getKey();

                    if (entry.getKey().equals("price")){
                        Log.v("total", entry.getKey() );
                        total = total +  Integer.parseInt(String.valueOf(entry.getValue()));
                    }

                }

            }
            textViewTotal.setText("Итого: " + total + " рублей");

        }



    }
    public void loadDataBasket(){
        Log.v("loadDataBasket","loadDataBasket");

        SharedPreferences sharedPreferences = getSharedPreferences("basket", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("basketList", null);
        Type type = new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType();

        listBasket = gson.fromJson(json, type);
        if (listBasket == null){
            listBasket = new ArrayList<>();
            Log.v("listBasketNULL","basketNULL");
        }

        for (int i = 0; i < listBasket.size();i++) {
            Log.v("loadchangeType","loadchangeType");
            for(HashMap.Entry<String, Object> entry : listBasket.get(i).entrySet()) {
                String key = entry.getKey();
                /*if (entry.getKey().equals("drawable")){
                     Double mdobule = (Double) entry.getValue();
                    Integer mdobule2 = mdobule.intValue();
                    Log.v("mdobule2", mdobule + "");
                    map.put(key, mdobule2);

                }*/



            }
            for(HashMap.Entry<String, Object> entry : listBasket.get(i).entrySet()) {
                if (entry.getKey().equals("drawable")){
                    Log.v("drawable3", entry.getValue() + "");
                }

            }

        }


        Log.v("listBasketCount",String.valueOf(listBasket.size()));


    }
    public void saveDataBasket(){

        TextView mTextView = findViewById(R.id.invisible);
        mTextView.setText(intent.getIntExtra("title", 0));
        String mTitle = mTextView.getText().toString();
        mTextView.setText("");

        map.put("titleRecipe",mTitle);
        map.put("drawable" ,   intent.getIntExtra("imageResourse", 0));
        Log.v("imageResourse",intent.getIntExtra("imageResourse",0)+ "");
        map.put("price" , intent.getIntExtra("price",0));
        map.put("weight" ,  intent.getIntExtra("weight",0));
        Log.v("listBasketCount",String.valueOf(listBasket.size()));
        listBasket.add(map);



        for (int i = 0; i < listBasket.size();i++) {

            Log.v("loadchangeType","loadchangeType");
            for(HashMap.Entry<String, Object> entry : listBasket.get(i).entrySet()) {
                String key = entry.getKey();
                if (entry.getKey().equals("drawable")){
                    Log.v("drawable2", entry.getValue() + "");

                }


            }

        }



        Log.v("listBasketCount",String.valueOf(listBasket.size()));
        SharedPreferences sharedPreferences = getSharedPreferences("basket", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listBasket);
        Log.v("json",json);
        editor.putString("basketList", json);
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.basket_menu, menu);
        MenuItem basketItem = menu.findItem(R.id.buttonBasket);


        basketItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {






            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.buttonBasket){
                    Toast.makeText(basket_activity.this, "Корзина очищена!", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("basket", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(listBasket);
                    Log.v("json",json);
                    editor.clear();
                    editor.apply();
                }


                return true;
            }
        });

        return true;
    }

    public void sendEmail(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("basket", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("basketList", null);
        Type type = new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType();

        listBasket = gson.fromJson(json, type);
        if (listBasket == null){
            listBasket = new ArrayList<>();
            Log.v("listBasketNULL","basketNULL");
        }

        String titleMail = "", textMail = "";

        for (int i = 0; i < listBasket.size();i++) {
            Log.v("loadchangeType","loadchangeType");
            for(HashMap.Entry<String, Object> entry : listBasket.get(i).entrySet()) {
                String key = entry.getKey();

                if (entry.getKey().equals("titleRecipe")){

                    titleMail = titleMail + entry.getValue() + " \n";
                }
                if (entry.getKey().equals("titleRecipe")
                        || entry.getKey().equals("price")
                        || entry.getKey().equals("weight") ){

                    textMail = textMail + entry.getValue() + " \n";
                }


            }

        }
        TextView textViewTotal = (TextView) findViewById(R.id.total);
        textMail = textMail + textViewTotal.getText();



        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"chekushin_2013@mail.ru"});
        intent.putExtra(Intent.EXTRA_SUBJECT, titleMail);
        intent.putExtra(Intent.EXTRA_TEXT   , textMail);
        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(basket_activity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
