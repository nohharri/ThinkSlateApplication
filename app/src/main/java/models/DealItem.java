package models;

public class DealItem  {
    public String name;
    public String description;

    public DealItem() {
        name = "";
        description = "";
    }

    public DealItem(String _name, String _description) {
        name = _name;
        description = _description;
    }
}
