package model;


public class AsiaFood {
    String name;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRestaurent_name() {
        return restaurent_name;
    }

    public void setRestaurent_name(String restaurent_name) {
        this.restaurent_name = restaurent_name;
    }

    String price;
    Integer imageUrl;
    String rating;
    String restaurent_name;

    public AsiaFood(String name, String price, Integer imageUrl, String rating, String restaurent_name) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.restaurent_name = restaurent_name;
    }



    public AsiaFood(String name,String price,Integer imageUrl) {
        this.name = name;
        this.price=price;
        this.imageUrl=imageUrl;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
