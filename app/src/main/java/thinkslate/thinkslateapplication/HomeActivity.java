package thinkslate.thinkslateapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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


public class HomeActivity extends AppCompatActivity implements OnItemClickListener {
    private FirebaseDatabase database;
    private DatabaseReference dealsRef;
    private String dealsRefStr = "deals";
    private List<DealItem> dealItems;

    // View
    private ListView dealsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dealItems = new ArrayList<DealItem>();

        initFirebase();
        initDealsRefListener();
        initView();
    }

    private void initView() {
        dealsListView = (ListView)findViewById(R.id.deals_list_view);
        DealsAdapter dealsAdapter = new DealsAdapter(this, dealItems);
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
                dealItems = new ArrayList<DealItem>();
                Log.d("HomeActivity", "Count is: " + dataSnapshot.getChildrenCount());
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    DealItem dealItem = postSnapshot.getValue(DealItem.class);
                    Log.d("HomeActivity", dealItem.name + " " + dealItem.description);
                    dealItems.add(dealItem);
                }
                initView();
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
        Intent intent = new Intent(HomeActivity.this, DealActivity.class);
        intent.putExtra("dealKey", dealKey);
        startActivity(intent);
    }
}
