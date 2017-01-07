package models;

public class DealItem  {
    public String name;
    public String description;
    public String key;
    public String longDescription;

    public DealItem() {
        name = "";
        description = "";
        key = "";
    }

    public DealItem(String _name, String _description, String _key, String _longDescription) {
        name = _name;
        description = _description;
        key = _key;
        longDescription = _longDescription;
    }
}
