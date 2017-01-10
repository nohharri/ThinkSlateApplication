package thinkslate.thinkslateapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import models.CouponItem;

/**
 * Created by harrisonnoh on 1/9/17.
 */

public class CouponsAdapter extends BaseAdapter {
    Context context;
    List<CouponItem> couponItems;

    public CouponsAdapter(Context _context, List<CouponItem> _couponItems) {
        context = _context;
        couponItems = _couponItems;
    }

    @Override
    public int getCount() {
        return couponItems.size();
    }

    @Override
    public Object getItem(int position) {
        return couponItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return couponItems.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        TextView description;
        ImageView image;

        ViewHolder(TextView _description, ImageView _image) {
            description = _description;
            image = _image;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CouponsAdapter.ViewHolder holder = null;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.coupon_item, null);
            holder = new CouponsAdapter.ViewHolder(
                    (TextView)convertView.findViewById(R.id.couponDescription),
                    (ImageView) convertView.findViewById(R.id.couponImage)
            );

            CouponItem rowPos = couponItems.get(position);

            holder.description.setText(rowPos.description);
            new DownloadImageTask(holder.image).execute(rowPos.imageUrl);

            convertView.setTag(holder);
        }
        else {
            holder = (CouponsAdapter.ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
