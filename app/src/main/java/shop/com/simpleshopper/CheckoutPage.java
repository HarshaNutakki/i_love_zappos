package shop.com.simpleshopper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends AppCompatActivity {
    public static List<Pair<Integer, Product>> PRODUCTS_CHECKOUT = new ArrayList<Pair<Integer, Product>>();
    private ListView mListView;
    private Button m_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page);
        mListView = (ListView) findViewById(R.id.checkoutProductList);
        ArrayList<Product> products = new ArrayList<Product>();
        for (Pair<Integer, Product> productdata : PRODUCTS_CHECKOUT) {
            products.add(productdata.second);
        }
        ProductAdapter adapter = new ProductAdapter(this, PRODUCTS_CHECKOUT);
        mListView.setAdapter(adapter);
        m_button = (Button) findViewById(R.id.doCheckout);
        m_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PRODUCTS_CHECKOUT.clear();
                Toast toast = Toast.makeText(getApplicationContext(), "Thank you for shopping with us.", Toast.LENGTH_SHORT);
                toast.show();
                Intent myIntent = new Intent(CheckoutPage.this, MainActivity.class);
                CheckoutPage.this.startActivity(myIntent);

            }
        });

    }
}
