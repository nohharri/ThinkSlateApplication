package thinkslate.thinkslateapplication;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;


/**
 * Created by harrisonnoh on 1/5/17.
 */

public class DealsAdapter extends BaseAdapter {
    Context context;
    List<DealItem> dealItems;

    public DealsAdapter(Context _context, List<DealItem> _dealItems) {
        context = _context;
        dealItems = _dealItems;
    }

    @Override
    public int getCount() {
        return dealItems.size();
    }

    @Override
    public Object getItem(int position) {
        return dealItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dealItems.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        private TextView dealName;
        private TextView dealDescr;

        ViewHolder(TextView _dealName, TextView _dealDescr) {
            dealName = _dealName;
            dealDescr = _dealDescr;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.deal_item, null);
            holder = new ViewHolder(
                    (TextView)convertView.findViewById(R.id.dealName),
                    (TextView)convertView.findViewById(R.id.dealDescr));

            DealItem rowPos = dealItems.get(position);

            holder.dealName.setText(rowPos.getDealName());
            holder.dealDescr.setText(rowPos.getDealName());

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
