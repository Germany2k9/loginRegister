package com.germany.loginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.germany.loginregister.adapter.ProductAdapter;
import com.germany.loginregister.adapter.ProductCategoryAdapter;
import com.germany.loginregister.model.Products;
import com.germany.loginregister.model.productCategory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     ProductCategoryAdapter productCategoryAdapter;
     RecyclerView productCatRecycler, prodItemRecycler;
     ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<productCategory> productCategoryList = new ArrayList<>();
            productCategoryList.add(new productCategory( 1, "Most Popular"));
            productCategoryList.add(new productCategory( 2, "All body Products"));
            productCategoryList.add(new productCategory( 3, "Skin Care"));
            productCategoryList.add(new productCategory( 4, "Hair"));

            setProductRecycler(productCategoryList);



        List<Products>  productsList = new ArrayList<>();

            productsList.add(new Products(1, "Japanese Cherry Blosson", "250 ml", "$ 17.00", R.drawable.prod2));
            productsList.add(new Products(2, "African Mango Shower Gel", "470 ml", "$ 20.00", R.drawable.prod1));
            productsList.add(new Products(3, "Japanese Cherry Blosson", "250 ml", "$ 17.00", R.drawable.prod2));
            productsList.add(new Products(4, "African Mango Shower Gel", "470 ml", "$ 20.00", R.drawable.prod1));
            productsList.add(new Products(5, "Japanese Cherry Blosson", "250 ml", "$ 17.00", R.drawable.prod2));
            productsList.add(new Products(6, "African Mango Shower Gel", "470 ml", "$ 20.00", R.drawable.prod1));

            setProdItemRecycler(productsList);
    }



    private  void setProductRecycler(List<productCategory> productCategoryList){

        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(this, productCategoryList);
        productCatRecycler.setAdapter(productCategoryAdapter);
    }

    private  void setProdItemRecycler(List<Products> productsList){

        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, productsList);
        prodItemRecycler.setAdapter(productAdapter);
    }
}