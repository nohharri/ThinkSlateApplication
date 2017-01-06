package thinkslate.thinkslateapplication;

public class DealItem  {
    private String dealName;
    private String dealDescription;

    public DealItem(String _dealName, String _dealDescription) {
        dealName = _dealName;
        dealDescription = _dealDescription;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String _dealName) {
        dealName = _dealName;
    }
}
