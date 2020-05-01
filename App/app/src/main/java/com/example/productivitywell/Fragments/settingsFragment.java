package com.example.productivitywell.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.productivitywell.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link settingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

//    public static final String TAG= "SettingsFragment";
//
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public settingsFragment() {
//        // Required empty public constructor

    private TextView text_title;
    private TextView username_heading;
    private TextView username_content;
    private TextView email_content;
    private TextView email_heading;
    private TextView password_heading;
    private TextView password_content;
    private AlertDialog dialog;
    private Button submit_button;
    private Button update_button;
    private ImageView imageView;
    private EditText editText;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment settingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static settingsFragment newInstance(String param1, String param2) {
        settingsFragment fragment = new settingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        text_title = view.findViewById(R.id.text_title);
        username_heading = view.findViewById(R.id.username_heading);
        username_content = view.findViewById(R.id.username_content);
        email_heading = view.findViewById(R.id.email_heading);
        email_content = view.findViewById(R.id.email_content);
        password_heading = view.findViewById(R.id.password_heading);
        password_content = view.findViewById(R.id.password_content);
        submit_button = view.findViewById(R.id.submit_button);
        update_button = view.findViewById(R.id.update_button);
        imageView = view.findViewById(R.id.imageView);
        editText =view.findViewById(R.id.editText);



//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}
