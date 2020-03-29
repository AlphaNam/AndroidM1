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
    //private FragmentScoreListener listener;
    private TextView tv_nom_joueur1;
    private TextView tv_nom_joueur2;
    private boolean j1_sert;

    public void setServer(boolean choice) {
        if(choice){
            tv_nom_joueur1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_yellow_24dp, 0);
            tv_nom_joueur2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_black_24dp, 0);
            j1_sert = true;
        }
        else{
            tv_nom_joueur1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_black_24dp, 0);
            tv_nom_joueur2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_fiber_manual_record_yellow_24dp, 0);
            j1_sert = false;
        }
    }

    public interface FragmentScoreListener{
        void onInputASent(boolean choice);
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

        tv_nom_joueur1 = (TextView)view.findViewById(R.id.tv_score_nomj1);
        tv_nom_joueur2 = (TextView)view.findViewById(R.id.tv_score_nomj2);

        tv_nom_joueur1.setText(getArguments().getString("NOM_JOUEUR_1"));
        tv_nom_joueur2.setText(getArguments().getString("NOM_JOUEUR_2"));

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
        //listener = null;
    }
}
