package com.example.productivitywell.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.productivitywell.R;
import com.example.productivitywell.Statsdata;
import com.example.productivitywell.User;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link settingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String TAG = "settings Fragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public settingsFragment() {
        // Required empty public constructor
    }
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
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        System.out.println(" This is the before the query user");
        queryUser();
        System.out.println(" This is after the query User");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    public void queryUser() {
        ParseQuery<User> query = ParseQuery.getQuery(User.class);
        query.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> users, ParseException e) {
                if (e != null){
                    Log.e(TAG,"issues with getting post", e);
                    return;
                }
                Log.i(TAG, " current user ID " + User.KEY_USER);
                Log.i(TAG, " current user ID parse user" + ParseUser.getCurrentUser());

                for (User user: users){
                    Log.i(TAG, " The user name is" + user.getUsername());

                }

                System.out.println(" After the loopAfter the 2 loops in the query");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
