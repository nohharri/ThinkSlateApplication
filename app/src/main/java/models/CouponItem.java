package models;

/**
 * Created by harrisonnoh on 1/9/17.
 */

public class CouponItem {
    public String description;
    public String imageUrl;

    public CouponItem() {
        description = "";
        imageUrl = "";
    }

    public CouponItem(String _description, String _imageUrl) {
        description = _description;
        imageUrl = _imageUrl;
    }
}
