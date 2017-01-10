package thinkslate.thinkslateapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import models.DealItem;


public class DealActivity extends AppCompatActivity {
    private String dealKey;
    private DealItem dealItem;

    private FirebaseDatabase database;
    private DatabaseReference dealRef;
    private String dealRefStr;

    // View
    ImageView image;
    TextView name;
    TextView description;
    TextView longDescription;
    Button acceptBtn;
    TextView points;

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
        image = (ImageView)findViewById(R.id.image);
        name = (TextView)findViewById(R.id.name);
        description = (TextView)findViewById(R.id.description);
        longDescription = (TextView)findViewById(R.id.longDescription);
        acceptBtn = (Button) findViewById(R.id.acceptBtn);
        points = (TextView)findViewById(R.id.points);
    }

    private void updateView() {
        new DownloadImageTask(image).execute(dealItem.imageUrl);
        name.setText(dealItem.name);
        description.setText(dealItem.description);
        longDescription.setText(dealItem.longDescription);
        String pointsStr = dealItem.points + " points!";
        points.setText(pointsStr);
    }

    public void accept(View view) {

    }
}
