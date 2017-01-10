package thinkslate.thinkslateapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import models.CouponItem;


public class UserActivity extends Fragment {
    private FirebaseDatabase database;
    private DatabaseReference couponsRef;
    private String couponRefStr = "coupons";
    private List<CouponItem> couponItems;
    private View view;
    private ListView couponsListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_user, container, false);

        couponItems = new ArrayList();

        initFirebase();
        initDealsRefListener();
        initView();

        return view;
    }

    private void initView() {
        couponsListView = (ListView)view.findViewById(R.id.coupons_list_view);
        CouponsAdapter couponsAdapter = new CouponsAdapter(getActivity(), couponItems);
        couponsListView.setAdapter(couponsAdapter);
    }

    private void initFirebase() {
        database = FirebaseDatabase.getInstance();
        couponsRef = database.getReference(couponRefStr);
    }

    private void initDealsRefListener() {
        couponsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                couponItems = new ArrayList<CouponItem>();
                Log.d("UserActivity", "Count is: " + dataSnapshot.getChildrenCount());
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    CouponItem couponItem = postSnapshot.getValue(CouponItem.class);
                    couponItems.add(couponItem);
                }
                initView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("HomeActivity", "Failed to read value.", databaseError.toException());
            }
        });
    }

}
