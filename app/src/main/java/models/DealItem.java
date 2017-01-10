package models;

public class DealItem  {
    public String name;
    public String description;
    public String key;
    public String longDescription;
    public String imageUrl;
    public int points;

    public DealItem() {
        name = "";
        description = "";
        key = "";
        imageUrl = "";
        points = 0;
    }

    public DealItem(String _name, String _description, String _key, String _longDescription, String _imageUrl, int _points) {
        name = _name;
        description = _description;
        key = _key;
        longDescription = _longDescription;
        imageUrl = _imageUrl;
        points = _points;
    }
}
