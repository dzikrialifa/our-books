package org.android.dzik.ourbooks.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.android.dzik.ourbooks.R;
import org.android.dzik.ourbooks.model.Buku;


public class TambahFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private DatabaseReference database;
    EditText namaFilm,namaPemesan,emails,jmlhtotal;
    public TambahFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance().getReference();

        View view = inflater.inflate(R.layout.fragment_tambah,container,false);
        namaFilm = view.findViewById(R.id.nama_film);
        namaPemesan = view.findViewById(R.id.nama_pemesan);
        emails = view.findViewById(R.id.emails);
        jmlhtotal = view.findViewById(R.id.jmlh_total);
        Button simpan = view.findViewById(R.id.button_smpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String film = namaFilm.getText().toString();
                String pemesan = namaPemesan.getText().toString();
                String email = emails.getText().toString();
                int total = Integer.parseInt(jmlhtotal.getText().toString());

                if (film.equals("")){
                    namaFilm.setError("masukkan nama film");
                    namaFilm.requestFocus();
                }else if(pemesan.equals("")){
                    namaPemesan.setError("pemesan no null");
                    namaPemesan.requestFocus();
                }else if(emails.equals("")){
                    emails.setError("email tidak kosong");
                    emails.requestFocus();
                }else if(jmlhtotal == null){
                    jmlhtotal.setError("jumlah penonton tidak boleh kosong");
                    jmlhtotal.requestFocus();
                }
                else{
                    submitBuku(new Buku(
                            film.toLowerCase(),
                            pemesan.toLowerCase(),
                            email.toLowerCase(),
                            total
                            ));
                }

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void submitBuku(Buku buku) {
        database.child("Result")
                .push()
                .setValue(buku)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        namaFilm.setText("");
                        emails.setText("");
                        Toast.makeText(getContext(),"Data berhasil ditambah",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSaveData();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()  + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSaveData();
    }
}
