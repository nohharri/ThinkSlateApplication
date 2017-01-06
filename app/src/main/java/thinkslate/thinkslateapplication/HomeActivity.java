package thinkslate.thinkslateapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.List;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference dealsRef;
    private String dealsRefStr = "deals";
    private List<DealItem> dealItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("HomeActivity", "PRINT SOETHING");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = FirebaseDatabase.getInstance();
        dealsRef = database.getReference(dealsRefStr);

        dealsRef.setValue("Hello world!");

        initDealsRefListener();
    }

    private void initDealsRefListener() {
        dealsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String otherVal = dataSnapshot.getValue(String.class);
                Log.d("HomeActivity", "Value is: " + otherVal);
                String value = dataSnapshot.child(dealsRefStr).getValue(String.class);
                Log.d("HomeActivity", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("HomeActivity", "Failed to read value.", databaseError.toException());
            }
        });
    }
}
