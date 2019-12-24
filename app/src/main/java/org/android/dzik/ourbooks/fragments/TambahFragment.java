package org.android.dzik.ourbooks.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.android.dzik.ourbooks.R;
import org.android.dzik.ourbooks.model.Film;


public class TambahFragment extends Fragment implements View.OnClickListener {

    DatabaseReference database;
    DatabaseReference RefDb;
    RadioGroup seatGroup;
    EditText namaPemesanView,anakTextView,dewasaTextView;
    Button submit;
    public TambahFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tambah, container, false);
        namaPemesanView = view.findViewById(R.id.input_pemesan);
        anakTextView = view.findViewById(R.id.input_anak);
        dewasaTextView = view.findViewById(R.id.input_dewasa);
        seatGroup = view.findViewById(R.id.group_tempatduduk);
        submit = (Button)view.findViewById(R.id.button_checkout);
        submit.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        RefDb = database.getReference("Result");
        String nama = namaPemesanView.getText().toString();

        int checkId =  seatGroup.getCheckedRadioButtonId();
        String dewasaString = dewasaTextView.getText().toString();
        String anakString = anakTextView.getText().toString();


        if ((checkId != -1) && !TextUtils.isEmpty(nama) && !TextUtils.isEmpty(dewasaString)) {
            if (TextUtils.isEmpty(anakString)){ // Paket SWEET
                int dewasa = Integer.parseInt(dewasaString);
                int seat = 0;
                if(checkId == R.id.radio_sweet){
                    seat = Film.SWEET;
                }
                Film sumFilm = new Film(nama, 0, dewasa, seat);
                RefDb.child("hasil")
                        .push()
                        .setValue(sumFilm);
                Toast.makeText(getContext(),"Data berhasil ditambah",Toast.LENGTH_SHORT).show();

                Bundle args = new Bundle();
                args.putParcelable("film",sumFilm);
                Fragment HasilFragment = new HasilFragment();
                HasilFragment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, HasilFragment);
                fragmentTransaction.commit();

            }else{
                int dewasa = Integer.parseInt(dewasaString);
                int anak = Integer.parseInt(anakString);
                int seat = 0;
                if(checkId == R.id.radio_reguler){
                    seat =   Film.REGULER;
                }else if(checkId == R.id.radio_sweet){
                    seat = Film.SWEET;
                }else{
                    seat = Film.FAMILY;
                }
                Film sumFilm = new Film(nama, anak, dewasa, seat);
                RefDb.child("hasil")
                        .push()
                        .setValue(sumFilm);
                Toast.makeText(getContext(),"Data berhasil ditambah",Toast.LENGTH_SHORT).show();

                Bundle args = new Bundle();
                args.putParcelable("film",sumFilm);
                Fragment HasilFragment = new HasilFragment();
                HasilFragment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, HasilFragment);
                fragmentTransaction.commit();

            }

//            int dewasa = Integer.parseInt(dewasaString);
//            int anak = Integer.parseInt(anakString);
//            int seat = 0;
//                    if(checkId == R.id.radio_reguler){
//                      seat =   Film.REGULER;
//                    }else if(checkId == R.id.radio_sweet){
//                            seat = Film.SWEET;
//                    }else{
//                        seat = Film.FAMILY;
//                    }
//            Film sumFilm = new Film(nama, anak, dewasa, seat);
//            RefDb.child("hasil")
//                    .push()
//                    .setValue(sumFilm);
//            Toast.makeText(getContext(),"Data berhasil ditambah",Toast.LENGTH_SHORT).show();
//
//            Bundle args = new Bundle();
//            args.putParcelable("film",sumFilm);
//            Fragment HasilFragment = new HasilFragment();
//            HasilFragment.setArguments(args);
//            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
//                    .beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container, HasilFragment);
//            fragmentTransaction.commit();

        }else{
            Toast.makeText(getActivity(), "Seat tidak boleh kosong !", Toast.LENGTH_SHORT).show();
        }

    }

}
