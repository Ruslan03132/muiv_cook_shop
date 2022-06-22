package MUIV_Recipes_app.ruslan_borislavovich.MUIVRecipes.MUIVRecipes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Model.Menu;

public class RecyclerViewAdapterSalat extends RecyclerView.Adapter<RecyclerViewAdapterSalat.ViewHolder> implements Filterable {

    private ArrayList<InfoCardItem> cardItemsSalat;
    private ArrayList<InfoCardItem> cardItemsFullSalat;
    public Context context;


    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        public TextView description;
        public TextView priceView;
        public TextView weightView;
        Button buttonBasket;


        private ViewHolder(@NonNull View itemView) {
            super(itemView);



            this.imageView = itemView.findViewById(R.id.image_basket);
            this.title = itemView.findViewById(R.id.Name);
            this.description = itemView.findViewById(R.id.Text);
            this.priceView = itemView.findViewById(R.id.Price);
            this.weightView = itemView.findViewById(R.id.Weight);
            this.buttonBasket = itemView.findViewById(R.id.buttonBasket);
            this.buttonBasket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Menu menu = MainActivity.databaseHandler.getRecipes(position + 1, "salatRecipes");
                    InfoCardItem cardItem = cardItemsSalat.get(position);
                    Intent intent = new Intent(context, basket_activity.class);
                    intent.putExtra("title", cardItem.getTitle());
                    intent.putExtra("description", cardItem.getDescription());
                    intent.putExtra("recipe", cardItem.getRecipe());
                    intent.putExtra("imageResourse", cardItem.getImageView());
                    intent.putExtra("adapterPosition", position);
                    intent.putExtra("context", "salat");
                    intent.putExtra("price", menu.getPrice());
                    intent.putExtra("weight", menu.getWeight());
                    intent.putExtra("titleString", cardItem.getTitleString());
                    context.startActivity(intent);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position  = getAdapterPosition();

                    Log.v("3333",context.getClass().getSimpleName());
                    InfoCardItem cardItem = cardItemsSalat.get(position);

                    Intent intent = new Intent(context, RecipeSalatActivity.class);
                    intent.putExtra("title" , cardItem.getTitle());
                    intent.putExtra("description" , cardItem.getDescription());
                    intent.putExtra("recipe" , cardItem.getRecipe());
                    intent.putExtra("imageResourse" , cardItem.getImageView());
                    intent.putExtra("adapterPosition" , position);
                    intent.putExtra("context","salat");

                    context.startActivity(intent);



                }
            });

        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    RecyclerViewAdapterSalat(ArrayList<InfoCardItem> cardItems , Context context){
        this.cardItemsSalat = cardItems;
        this.context = context;
        this.cardItemsFullSalat = new ArrayList<>(cardItems);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InfoCardItem CardItem = cardItemsSalat.get(position);
        holder.imageView.setImageResource(CardItem.getImageView());
        holder.title.setText(CardItem.getTitle());
        holder.description.setText(CardItem.getDescription());
        int price, weight;
        Menu menu = MainActivity.databaseHandler.getRecipes(position + 1, "salatRecipes");
        Log.d("recipesSalat", String.valueOf(menu.getPrice()));
        price = menu.getPrice();
        weight = menu.getWeight();
        holder.priceView.setText(String.valueOf(price));
        holder.weightView.setText(String.valueOf(weight));






    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);

    }



    @Override
    public int getItemCount() {
        return cardItemsSalat.size();
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<InfoCardItem> filteredList  = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(cardItemsFullSalat);

            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (InfoCardItem item: cardItemsFullSalat){
                    if (item.getTitleString().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results  = new FilterResults();
            results.values = filteredList;
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cardItemsSalat.clear();
            cardItemsSalat.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
