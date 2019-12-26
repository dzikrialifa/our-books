package org.android.dzik.ourbooks.activities;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.android.dzik.ourbooks.R;
import org.android.dzik.ourbooks.fragments.AvengersFragment;
import org.android.dzik.ourbooks.fragments.BlackPanther;
import org.android.dzik.ourbooks.fragments.HasilFragment;
import org.android.dzik.ourbooks.fragments.HomeFragment;
import org.android.dzik.ourbooks.fragments.ListFragment;
import org.android.dzik.ourbooks.fragments.MazeRunner;
import org.android.dzik.ourbooks.fragments.TambahFragment;
import org.android.dzik.ourbooks.fragments.Venom;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,HomeFragment.OnFragmentInteractionListener,
        HasilFragment.OnFragmentInteractionListener,ListFragment.OnFragmentInteractionListener
{

    private static final String TAG = "ourbooks";
    private TambahFragment tambahFragment;
    private HasilFragment hasilFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tambahFragment = new TambahFragment();
        hasilFragment = new HasilFragment();

        loadFragment(new HomeFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


        // write message to database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("testing");

        myRef.setValue("Hello, testing World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.action_home:
                fragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                break;
            case R.id.action_add:
                fragment = new TambahFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment).addToBackStack("tambah")
                        .commit();
                break;
            case R.id.action_listfilm:
                fragment = new ListFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment).addToBackStack("list")
                        .commit();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTryAgain(String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, tambahFragment)
                .commit();
    }


    @Override
    public void onlist1clicked() {
        getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.fragment_container, new BlackPanther())
                .commit();
    }

    @Override
    public void onlist2clicked() {
        getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.fragment_container, new MazeRunner())
                .commit();
    }

    @Override
    public void onlist3clicked() {
        getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.fragment_container, new Venom())
                .commit();
    }
    @Override
    public void onlist4clicked() {
        getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.fragment_container, new AvengersFragment())
                .commit();
    }
}