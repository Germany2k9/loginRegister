package com.germany.loginregister.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.germany.loginregister.MainActivity;
import com.germany.loginregister.R;
import com.germany.loginregister.model.Products;
import com.germany.loginregister.productDetails;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    List<Products> productsList;

    public ProductAdapter(Context context,  List<Products> productsList) {
        this.productsList= productsList;
        this.context= context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.products_row_item,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        holder.prodImage.setImageResource(productsList.get(position).getImageURL());
        holder.prodName.setText(productsList.get(position).getProductName());
        holder.prodQty.setText(productsList.get(position).getProductQty());
        holder.prodPrice.setText(productsList.get(position).getProductPrice());


        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, productDetails.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(holder.prodImage,"image");
                ActivityOptions activityOptions =  ActivityOptions.makeSceneTransitionAnimation((Activity)context,pairs);
                context.startActivity(i , activityOptions.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
     return  productsList.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView prodImage;
        TextView prodName, prodQty, prodPrice;

        public ProductViewHolder(@NonNull View itemView){
            super(itemView);

            prodImage = itemView.findViewById(R.id.prod_image);
            prodName = itemView.findViewById(R.id.prod_name);
            prodPrice = itemView.findViewById(R.id.prod_price);
            prodQty = itemView.findViewById(R.id.prod_qty);
        }
    }
}
