package shop.com.simpleshopper;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vikash on 1/30/2017.
 */

public class ProductAdapter extends BaseAdapter {
    List<Pair<Integer, Product>> mDataSource = new ArrayList<Pair<Integer, Product>>();
    private Context mContext;
    private LayoutInflater mInflater;

    public ProductAdapter(Context context, List<Pair<Integer, Product>> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.product_list, parent, false);
        TextView productName =
                (TextView) rowView.findViewById(R.id.checkoutProductName);

// Get subtitle element
        TextView productPrice =
                (TextView) rowView.findViewById(R.id.checkoutProductPrice);

// Get detail element
        TextView quantity =
                (TextView) rowView.findViewById(R.id.checkoutProductQty);
        TextView productCountPrice =
                (TextView) rowView.findViewById(R.id.checkoutProductCountPrice);
        Pair<Integer, Product> product = (Pair<Integer, Product>) getItem(position);

// 2
        productName.setText(product.second.getName());
        productPrice.setText(product.second.getPrice());
        quantity.setText("Qty : "+product.first.toString());
        productCountPrice.setText((Integer.parseInt(product.second.getPrice().replaceAll("[^-?0-9]+", "")) * product.first) + " Rs.");
        return rowView;
    }
}
