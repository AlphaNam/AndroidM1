package fr.android.tennistracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentScore extends Fragment {
    private FragmentScoreListener listener;
    private TextView tv_nom_joueur1;
    private TextView tv_nom_joueur2;

    public interface FragmentScoreListener{
        void onInputASent(CharSequence input);
    }

    public FragmentScore() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_score, container, false);
        tv_nom_joueur1 = view.findViewById(R.id.tv_score_nomj1);
        tv_nom_joueur2 = view.findViewById(R.id.tv_score_nomj2);



        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof  FragmentScoreListener){
            listener = (FragmentScoreListener)context;
        } else {
            throw new RuntimeException((context.toString())
                    + "must implement FragmentScoreListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void updateTextViewJoueur1(CharSequence newTextJoueur1){
        tv_nom_joueur1.setText(newTextJoueur1);
    }

    public void updateTextViewJoueur2(CharSequence newTextJoueur2){
        tv_nom_joueur2.setText(newTextJoueur2);
    }
}
