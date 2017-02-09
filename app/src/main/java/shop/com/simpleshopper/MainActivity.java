package shop.com.simpleshopper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static shop.com.simpleshopper.CheckoutPage.PRODUCTS_CHECKOUT;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView text;
    private List<String> data = new ArrayList<String>();
    private List<Product> movies = new ArrayList<Product>();
    private FloatingActionButton m_floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<Product>> call = apiService.getTopRatedMovies();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                movies = response.body();
                Log.d("no error", "Number of movies received: " + movies.size());
                for (Product product : movies) {
                    Log.d("name", product.getDesc());
                    data.add(product.getName());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Error", t.toString());
            }
        });
        text = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

        ArrayAdapter adapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, data);

        text.setAdapter(adapter);
        text.setThreshold(1);

        text.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("show Name", data.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("show Name", parent.getItemAtPosition(position).toString());
                for (Product products : movies) {
                    if (parent.getItemAtPosition(position).toString().equals(products.getName())) {
                        ProductPageActivity.CURRENT_PRODUCT = products;
                    }
                }
                Intent myIntent = new Intent(MainActivity.this, ProductPageActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        m_floatingActionButton = (FloatingActionButton) findViewById(R.id.checkoutButtonMainActivity);
        m_floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PRODUCTS_CHECKOUT.size() > 0) {
                    Intent myIntent = new Intent(MainActivity.this, CheckoutPage.class);
                    MainActivity.this.startActivity(myIntent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Your cart is empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

}