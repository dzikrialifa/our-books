package org.android.dzik.ourbooks.fragments;

import android.content.Context;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.android.dzik.ourbooks.R;
import org.android.dzik.ourbooks.model.Film;


public class TambahFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private DatabaseReference database;
    EditText namaPemesanView,anakTextView,dewasaTextView;
    public TambahFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance().getReference();

        View view = inflater.inflate(R.layout.fragment_tambah,container,false);
        namaPemesanView = view.findViewById(R.id.input_pemesan);
        anakTextView = view.findViewById(R.id.input_anak);
        dewasaTextView = view.findViewById(R.id.input_dewasa);
        final RadioGroup seatGroup = view.findViewById(R.id.group_tempatduduk);

        Button simpan = view.findViewById(R.id.button_checkout);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pemesan = namaPemesanView.getText().toString();
                String dewasaString = namaPemesanView.getText().toString();
                String anakString = anakTextView.getText().toString();
                int checkID = seatGroup.getCheckedRadioButtonId();

                if ((checkID != -1) && !TextUtils.isEmpty(pemesan) && !TextUtils.isEmpty(dewasaString) && !TextUtils.isEmpty(anakString)){
                    int dewasa = Integer.parseInt(dewasaString);
                    int anak = Integer.parseInt(anakString);
                    int seat = (checkID== R.id.radio_reguler) ? Film.REGULER :
                            (checkID==R.id.radio_sweet) ? Film.SWEET :
                                    Film.FAMILY;
                    Film sumFilm = new Film(pemesan,anak,dewasa,seat);
                    mListener.onSaveData(sumFilm.getIndex());
                    submitBuku(new Film(pemesan,anak,dewasa,seat));
                }else{
                    Toast.makeText(getActivity(),"Please fill amount adult,child and SEAT",
                            Toast.LENGTH_SHORT).show();
                }
//                if (film.equals("")){
//                    namaFilm.setError("masukkan nama film");
//                    namaFilm.requestFocus();
//                }else if(pemesan.equals("")){
//                    namaPemesan.setError("pemesan no null");
//                    namaPemesan.requestFocus();
//                }else if(emails.equals("")){
//                    emails.setError("email tidak kosong");
//                    emails.requestFocus();
//                }else if(jmlhtotal == null){
//                    jmlhtotal.setError("jumlah penonton tidak boleh kosong");
//                    jmlhtotal.requestFocus();
//                }
//                else{
//                    submitBuku(new Film(
//                            total,total,total
//                            ));
//                }

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void submitBuku(Film film) {
        database.child("Result")
                .push()
                .setValue(film)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        namaPemesanView.setText("");
                        anakTextView.setText("");
                        dewasaTextView.setText("");
                        Toast.makeText(getContext(),"Data berhasil ditambah",Toast.LENGTH_SHORT).show();
                    }
                });
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
        void onSaveData(int index);
    }
}
