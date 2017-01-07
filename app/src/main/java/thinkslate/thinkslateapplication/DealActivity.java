package thinkslate.thinkslateapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import models.DealItem;

public class DealActivity extends AppCompatActivity {
    private String dealKey;
    private DealItem dealItem;

    private FirebaseDatabase database;
    private DatabaseReference dealRef;
    private String dealRefStr;

    // View
    TextView name;
    TextView description;
    TextView longDescription;
    Button acceptBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        
        dealKey = getIntent().getStringExtra("dealKey");
        dealRefStr = "deals";
        initView();
        initFirebase();
        initDealsRefListener();
    }

    private void initFirebase() {
        database = FirebaseDatabase.getInstance();
        dealRef = database.getReference(dealRefStr);
    }

    private void initDealsRefListener() {
        dealRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dealItem = dataSnapshot.child(dealKey).getValue(DealItem.class);
                updateView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("DealActivity", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private void initView() {
        name = (TextView)findViewById(R.id.name);
        description = (TextView)findViewById(R.id.description);
        longDescription = (TextView)findViewById(R.id.longDescription);
        acceptBtn = (Button) findViewById(R.id.acceptBtn);
    }

    private void updateView() {
        name.setText(dealItem.name);
        description.setText(dealItem.description);
        longDescription.setText(dealItem.longDescription);
    }
}