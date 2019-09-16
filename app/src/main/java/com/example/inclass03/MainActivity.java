package com.example.inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    RadioGroup radioGroup;
    RadioButton male;
    RadioButton female;
    ImageView imageView;
    Button save;
    String flagImage;
    static String USERKEY;
    public static final String TAGIMAGE = "photoproof";
    public static final String USER = "USER";
    public static final String MALE = "Male";
    public static final String FEMALE = "Female";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Profile");

        firstName = findViewById(R.id.firstName);
        firstName.setError("Enter First Name");
        lastName = findViewById(R.id.lastName);
        lastName.setError("Enter Last Name");
        radioGroup = findViewById(R.id.radioGroupNew);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        imageView = findViewById(R.id.imageView);
        save = findViewById(R.id.save);
        Toast.makeText(MainActivity.this,"Select Gender",Toast.LENGTH_LONG).show();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(radioGroup.getCheckedRadioButtonId()) {
                    case R.id.female:
                        imageView.setImageDrawable(getDrawable((R.drawable.female)));
                        flagImage = FEMALE;
                        break;
                    case R.id.male:
                        imageView.setImageDrawable(getDrawable((R.drawable.male)));
                        flagImage = MALE;
                        break;
                    default:
                        break;
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean names = false;
                boolean genderChecked = false;
                if ( (lastName.getText().toString().equals("") || firstName.getText().toString().equals("") )) {
                 names = true;
                }
                if((!male.isChecked() && !female.isChecked())) {
                    genderChecked = true;
                }

                if (names  || genderChecked) {

                    lastName.setError(
                            lastName.getText().toString().equals("")
                                    ? "Enter Last Name" : null
                    );

                    firstName.setError(
                            firstName.getText().toString().equals("")
                                    ? "Enter First Name" : null
                    );

                    if( genderChecked) {
                        Toast.makeText(MainActivity.this,"Select Gender",Toast.LENGTH_LONG).show();
                    }
                    return;
                }

                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                User user = new User(
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        flagImage
                );
                Bundle sentData = new Bundle();
                sentData.putSerializable(USER, user);
                intent.putExtra(TAGIMAGE,sentData);
                startActivity(intent);
                finish();
            }
        });
    }
}
