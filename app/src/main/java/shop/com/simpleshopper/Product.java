package shop.com.simpleshopper;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vikash on 1/29/2017.
 */

public class Product {
    @SerializedName("product_id")
    private String id;
    @SerializedName("product_name")
    private String name;
    @SerializedName("product_desc")
    private String desc;
    @SerializedName("product_price")
    private String price;
    @SerializedName("product_image")
    private String image;

    public Product(String id, String name, String desc, String price, String image) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
