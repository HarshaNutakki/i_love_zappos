package shop.com.simpleshopper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import static shop.com.simpleshopper.CheckoutPage.PRODUCTS_CHECKOUT;


public class ProductPageActivity extends AppCompatActivity {

    public static Product CURRENT_PRODUCT;

    private TextView m_productName;
    private TextView m_productPrice;
    private TextView m_productDesc;
    private ImageView m_productImage;
    private Button m_buyButton;
    private FloatingActionButton m_floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        m_productName = (TextView) findViewById(R.id.productName);
        m_productDesc = (TextView) findViewById(R.id.productDesc);
        m_productPrice = (TextView) findViewById(R.id.productPrice);
        m_productImage = (ImageView) findViewById(R.id.productImage);

        m_productName.setText(CURRENT_PRODUCT.getName());
        m_productDesc.setText(CURRENT_PRODUCT.getDesc());
        m_productPrice.setText(CURRENT_PRODUCT.getPrice());

        Log.i("Image URL", CURRENT_PRODUCT.getImage());
        new DownloadImageTask(m_productImage)
                .execute(CURRENT_PRODUCT.getImage());
        m_buyButton = (Button) findViewById(R.id.buyButton);
        m_buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Product added to cart", Toast.LENGTH_SHORT);
                toast.show();
                int count = 0;
                boolean found = false;
                for (Pair<Integer, Product> productdata : PRODUCTS_CHECKOUT) {
                    if (productdata.second.getId().equals(CURRENT_PRODUCT.getId())) {
                        found = true;
                        break;
                    }
                    count++;
                }
                if (!found) {
                    PRODUCTS_CHECKOUT.add(new Pair<Integer, Product>(1, CURRENT_PRODUCT));
                } else {
                    int totalItem = (PRODUCTS_CHECKOUT.get(count).first + 1);
                    PRODUCTS_CHECKOUT.remove(count);
                    PRODUCTS_CHECKOUT.add(new Pair<Integer, Product>(totalItem, CURRENT_PRODUCT));
                }
            }
        });
        m_floatingActionButton = (FloatingActionButton) findViewById(R.id.checkoutButton);
        m_floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PRODUCTS_CHECKOUT.size() > 0) {
                    Intent myIntent = new Intent(ProductPageActivity.this, CheckoutPage.class);
                    ProductPageActivity.this.startActivity(myIntent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Your cart is empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
