package MUIV_Recipes_app.ruslan_borislavovich.MUIVRecipes.MUIVRecipes;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.Menu;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>   {
    private ArrayList<InfoCardItem> cardItems;
    private Context context;
    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView description;
        public TextView priceView;
        public TextView weightView;
        public Button buttonBasket;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageView = itemView.findViewById(R.id.image_basket);
            this.title = itemView.findViewById(R.id.Name);
            this.description = itemView.findViewById(R.id.Text);
            this.priceView = itemView.findViewById(R.id.Price);
            this.weightView = itemView.findViewById(R.id.Weight);
/*            this.buttonBasket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    InfoCardItem cardItem = cardItems.get(position);
                    Intent intent = new Intent(context, basket_activity.class);
                    intent.putExtra("title", cardItem.getTitle());
                    intent.putExtra("description", cardItem.getDescription());
                    intent.putExtra("recipe", cardItem.getRecipe());
                    intent.putExtra("imageResourse", cardItem.getImageView());
                    intent.putExtra("adapterPosition", position);
                    intent.putExtra("context", "faivorite");
                    context.startActivity(intent);
                }
            });*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position  = getAdapterPosition();
                    InfoCardItem cardItem = cardItems.get(position);

                    Intent intent = new Intent(context, RecipeActivityPizza.class);

                    intent.putExtra("title" , cardItem.getTitle());
                    intent.putExtra("description" , cardItem.getDescription());
                    intent.putExtra("recipe" , cardItem.getRecipe());
                    intent.putExtra("imageResourse" , cardItem.getImageView());
                    intent.putExtra("adapterPosition" , position);
                    intent.putExtra("context","favorite");
                    context.startActivity(intent);

                }
            });
        }
    }

    public FavoriteAdapter(ArrayList<InfoCardItem> cardItems, Context context){
        this.cardItems = cardItems;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        InfoCardItem cardItem = cardItems.get(position);
        holder.imageView.setImageResource(cardItem.getImageView());
        holder.title.setText(cardItem.getTitle());
        holder.description.setText(cardItem.getDescription());

        int price, weight;
        Menu menu = MainActivity.databaseHandler.getRecipes(position + 1, "salatRecipes");
        Log.d("recipesSalat", String.valueOf(menu.getPrice()));
        price = menu.getPrice();
        weight = menu.getWeight();
        holder.priceView.setText(String.valueOf(price));
        holder.weightView.setText(String.valueOf(weight));
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }
}
