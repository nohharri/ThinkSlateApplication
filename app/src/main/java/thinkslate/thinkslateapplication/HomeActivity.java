package thinkslate.thinkslateapplication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import models.DealItem;

// REMINDER: AppCompatActivity was previously extended and not Fragment
public class HomeActivity extends Fragment implements OnItemClickListener {
    private FirebaseDatabase database;
    private DatabaseReference dealsRef;
    private String dealsRefStr = "deals";
    private List<DealItem> dealItems;
    private View view;

    // View
    private ListView dealsListView;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_home, container, false);

        dealItems = new ArrayList();

        initFirebase();
        initDealsRefListener();
       // initView();

        return view;
    }

    private void initView() {
        dealsListView = (ListView)view.findViewById(R.id.deals_list_view);
        DealsAdapter dealsAdapter = new DealsAdapter(getActivity(), dealItems);
        Log.d("HomeActivity", dealsListView.toString());
        dealsListView.setAdapter(dealsAdapter);

        dealsListView.setOnItemClickListener(this);
    }

    private void initFirebase() {
        database = FirebaseDatabase.getInstance();
        dealsRef = database.getReference(dealsRefStr);
    }

    private void initDealsRefListener() {
        dealsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dealItems.size() == 0) {
                    dealItems = new ArrayList<DealItem>();
                    Log.d("HomeActivity", "Count is: " + dataSnapshot.getChildrenCount());
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        DealItem dealItem = postSnapshot.getValue(DealItem.class);
                        Log.d("HomeActivity", dealItem.name + " " + dealItem.description);
                        dealItems.add(dealItem);
                    }
                    initView();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("HomeActivity", "Failed to read value.", databaseError.toException());
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("HomeActivity", "Item clicked.");
      //  Log.d("HomeActivity", "Deal ite")
        String dealKey = dealItems.get(position).key;
        Log.d("HomeActivity", "dealKey is: " + dealKey);
        Intent intent = new Intent(getActivity(), DealActivity.class);
        intent.putExtra("dealKey", dealKey);
        startActivity(intent);
    }
}
