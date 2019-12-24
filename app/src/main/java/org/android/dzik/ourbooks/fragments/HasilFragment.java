package org.android.dzik.ourbooks.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.android.dzik.ourbooks.R;
import org.w3c.dom.Text;

public class HasilFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    String informasi;

    public HasilFragment() {
        // Required empty public constructor
    }

    public void setInformasi(String informasi) {
        this.informasi = informasi;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hasil,container,false);
        TextView infotext = view.findViewById(R.id.text_informasi);
        infotext.setText(informasi);
        Button tryagain = view.findViewById(R.id.button_tryagain);
        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onTryAgain("repeat");
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
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
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
