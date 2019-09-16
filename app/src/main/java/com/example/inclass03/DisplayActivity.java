/*
   InClass 03: My Profile
   Group #: 28
   Saloni Gupta 801080992
   Renju Hanna Robin 801076715
*/

package com.example.inclass03;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    ImageView displayImage;
    Button editButton;
    TextView displayName;
    TextView displayGender;
    final public static String USERTODISPLAY = "USERTODISPLAY";
    final public static String BUNDLETOEDIT = "BUNDLETOEDIT";
    final public static String TOEDIT = "TOEDIT";




    final int REQ_CODE = 200;

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK &&  data!= null) {


                final Bundle extraFromMain = data.getExtras().getBundle(MainActivity.TAGIMAGE);
                final User user = (User)extraFromMain.getSerializable(USERTODISPLAY);

                if (user != null) {

                    displayName.setText("Name: " + user.getFirstName() + " " + user.getLastName());
                    displayGender.setText(user.getGender());
                    if (user.getGender().equals(MainActivity.MALE)) {
                        displayImage.setImageDrawable(getDrawable(R.drawable.male));
                    } else {
                        displayImage.setImageDrawable(getDrawable(R.drawable.female));
                    }

                }




            }
        }

    }

    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        setTitle("My Profile");

        displayName = findViewById(R.id.displayName);
        displayImage = findViewById(R.id.displayImage);
        displayGender = findViewById(R.id.displayGender);
        editButton = findViewById(R.id.editButton);
        final Bundle extrasFromMain = getIntent().getExtras().getBundle(MainActivity.TAGIMAGE);
        final User user = (User)extrasFromMain.getSerializable(MainActivity.USER);
        displayImage = findViewById(R.id.displayImage);
        if (user != null) {
            if(user.getGender().equals(MainActivity.MALE)) {
                displayImage.setImageDrawable(getDrawable(R.drawable.male));

            } else {
                displayImage.setImageDrawable(getDrawable(R.drawable.female));
            }
        }

        displayName.setText("Name: "+user.getFirstName() + " " + user.getLastName());
        displayGender.setText(user.getGender());

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent toEdit = new Intent(DisplayActivity.this, EditActivity.class);
                    Bundle sentData = new Bundle();
                    sentData.putSerializable(BUNDLETOEDIT, user);
                    toEdit.putExtra(TOEDIT,sentData);

                    startActivityForResult(toEdit,REQ_CODE);
                }
            });

    }
}
