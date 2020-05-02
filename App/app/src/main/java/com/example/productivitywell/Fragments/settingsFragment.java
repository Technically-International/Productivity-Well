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
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    private RecyclerView frameSettings;
    public String TAG = "settingsFragment";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

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
    private Button submit_button;
    private Button update_button;
    private ImageView imageView;
    private File photoFile;
    public String photoFileName = "photo.jpg";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE= 42;

    private EditText editText;
    private AlertDialog dialog;
    private EditText editText_password;
    private AlertDialog dialog_password;
    private EditText editText_email;
    private AlertDialog dialog_email;

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
        setContentView(R.layout.activity.main)

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


        dialog= new AlertDialog.Builder(this).create();
        editText = new EditText(this);

        dialog.setTitle("Edit username here");
        dialog.setView(editText);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE TEXT", new DialogInterface.OnClickListener()(
                @Override
                public void onClick(DialogInterface dialogInterface, int i){
                    username_content(editText.getText());
        }
    });

        username_content.setOnClickLister (new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
            editText.setText(username_content.getText());
            dialog.show();


    }
    });


    dialog_password= new AlertDialog.Builder(this).create();
    editText_password = new EditText(this);

        dialog_password.setTitle("Edit username here");
        dialog_password.setView(editText_password);

        dialog_password.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE TEXT", new DialogInterface.OnClickListener()(
    @Override
    public void onClick(DialogInterface dialogInterface, int i){
        password_content(editText_password.getText());
    }
});

        password_content.setOnClickLister (new View.OnClickListener()

        {
@Override
public void onClick (View view){
        editText_password.setText(password_content.getText());
        dialog.show();


        }
        });


        dialog_email= new AlertDialog.Builder(this).create();
        editText_email = new EditText(this);

        dialog_email.setTitle("Edit username here");
        dialog_email.setView(editText_email);

        dialog_email.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE TEXT", new DialogInterface.OnClickListener()(
@Override
public void onClick(DialogInterface dialogInterface, int i){
        email_content(editText_email.getText());
        }
        });

        email_content.setOnClickLister (new View.OnClickListener()

        {
@Override
public void onClick (View view){
        editText_email.setText(email_content.getText());
        dialog.show();


        }
        });

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//        System.out.println(" This is the before the query user");
//        queryUser();
//        System.out.println(" This is after the query User");


     update_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchCamera();
        }
    });

        submit_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = username_content.toString();
            String email = email_content.toString();
            String password = password_content.toString();
            if (username.isEmpty()) {
                Toast.makeText( getContext(), "Username cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (photoFile == null || imageView.getDrawable() == null) {
                Toast.makeText( getContext(), "There is no image!", Toast.LENGTH_SHORT).show();
                return;
            }
            ParseUser currentUser = ParseUser.getCurrentUser();
            savePost(username, email, password, currentUser, photoFile);
        }
    });

}

    private void launchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference to access to future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview
                imageView.setImageBitmap(takenImage);
            } else { // Result was a failure
                Toast.makeText(getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        return new File(mediaStorageDir.getPath() + File.separator + fileName);
    }

    private void savePost(String username,String email,String password, ParseUser currentUser, File photoFile) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setProfilepic(new ParseFile(photoFile));
        user.setUser(currentUser);
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(getContext(),"Error while saving!", Toast.LENGTH_SHORT).show();
                }

                Log.i (TAG,"Change was saved successfully");
                username_content.setText(user.getUser().getUsername);
                password.setText(user.getPassword);
                email_content.setText(user.getEmail);
                //imageView.setImage(user.getProfilepic);
                ParseFile image = user.getProfilepic();
                if (image != null){
                Glide.with(context).load(user.getProfilepic().getUrl()).into(imageView);
                }
        });

    }
}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            FrameLayout frameSettings = view.findViewById(R.id.frameSettings);

            username_content.setText(user.getUser().getUsername);
            password.setText(user.getPassword);
            email_content.setText(user.getEmail);
            imageView.setImage(user.getProfilepic);

            ParseFile image = user.getProfilepic();
            if (image != null){
                Glide.with(context).load(user.getProfilepic().getUrl()).into(imageView);
            }


public void queryUser() {
        System.out.println("This is the beginning if the  function");
        ParseQuery<User> query = ParseQuery.getQuery(User.class);
//        query.whereEqualTo(User.KEY_USERNAME, ParseUser.getCurrentUser());
        System.out.println("Before queryyyyyyy");
        query.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> users, ParseException e) {
                if (e != null){
                    Log.e(TAG,"issues with getting post", e);
                    return;
                }
                Log.i(TAG, " list of users" + users);

                for (User user: users){
                    Log.i(TAG, " The user name is" + user.getUsername());
                }
                System.out.println(" After the loopAfter the 2 loops in the query");
            }
        });
    }
}
