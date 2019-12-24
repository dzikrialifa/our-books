package org.android.dzik.ourbooks.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.android.dzik.ourbooks.R;
import org.android.dzik.ourbooks.model.Film;

public class HasilFragment extends Fragment {

    Film films;
    TextView view;
    private OnFragmentInteractionListener mListener;
    public HasilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hasil,container,false);
        TextView infotext = (TextView) view.findViewById(R.id.text_informasi);
        Bundle data = this.getArguments();

        films = data.getParcelable("film");
        String info = films.getPemesan()+" Total yang dibayar  "+films.getJumlah();
        infotext.setText(info);

        Button tryagain = view.findViewById(R.id.button_tryagain);
        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null){
                    mListener.onTryAgain("FilmAdd");
                }
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onTryAgain(String tag);
    }
}
