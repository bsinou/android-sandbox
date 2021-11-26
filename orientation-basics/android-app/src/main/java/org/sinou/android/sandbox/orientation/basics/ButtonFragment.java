package org.sinou.android.sandbox.orientation.basics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ButtonFragment extends Fragment {

    private static final String STATE_COUNTER = "counter";

    private NavController navController;

    private TextView counterDisplay;
    private int mCounter = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ButtonFragment.onCreate(): "+this.toString());

        if (savedInstanceState != null) {
            // Restore some state before we've even inflated our own layout
            // This could be generic things like an ID that our Fragment represents
            mCounter = savedInstanceState.getInt(STATE_COUNTER, 0);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_button, container, false);

        // Set the current value
        TextView tv = (TextView) root.findViewById(R.id.countDisplay);
        tv.setText(String.format("%d", mCounter));

        return root;
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navController = Navigation.findNavController(view);
        counterDisplay = (TextView) view.findViewById(R.id.countDisplay);

        Button btn = view.findViewById(R.id.incrementor);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter++;
                counterDisplay.setText(String.valueOf(mCounter));
                System.out.println("plus one clicked" );
            }
        });

        Button btn2 = view.findViewById(R.id.navigate);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.list_fragment);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_COUNTER, mCounter);
    }
}

