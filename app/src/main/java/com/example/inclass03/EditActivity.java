package com.example.inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText firstNameNew;
    EditText lastNameNew;
    RadioGroup radioGroupNew;
    RadioButton maleNew;
    RadioButton femaleNew;
    ImageView imageViewNew;
    Button saveNew;
    String flagImageNew;
    public static final String DEFAULT = "DEFAULT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        firstNameNew = findViewById(R.id.firstNameNew);
        lastNameNew = findViewById(R.id.lastNameNew);
        radioGroupNew = findViewById(R.id.radioGroupNew);
        maleNew = findViewById(R.id.maleNew);
        femaleNew = findViewById(R.id.femaleNew);
        imageViewNew = findViewById(R.id.imageViewNew);
        saveNew = findViewById(R.id.saveButtonNew);
        setTitle("My Profile");

        final Bundle extrasFromDisplay = getIntent().getExtras().getBundle(DisplayActivity.TOEDIT);
        User user = (User) extrasFromDisplay.getSerializable(DisplayActivity.BUNDLETOEDIT);
        firstNameNew.setText(user.getFirstName());
        lastNameNew.setText(user.getLastName());

        if(user != null) {
            if (user.getGender().equals(MainActivity.MALE)) {
                maleNew.setChecked(true);
                flagImageNew = MainActivity.MALE;
                imageViewNew.setImageDrawable(getDrawable(R.drawable.male));
            } else {
                femaleNew.setChecked(true);
                flagImageNew = MainActivity.FEMALE;
                imageViewNew.setImageDrawable(getDrawable(R.drawable.female));
            }
        }

        radioGroupNew.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch(radioGroupNew.getCheckedRadioButtonId()) {

                    case R.id.femaleNew:
                        imageViewNew.setImageDrawable(getDrawable((R.drawable.female)));
                        flagImageNew = MainActivity.FEMALE;
                        break;
                    case R.id.maleNew:
                        imageViewNew.setImageDrawable(getDrawable((R.drawable.male)));
                        flagImageNew = MainActivity.MALE;
                        break;
                    default:
                        flagImageNew=DEFAULT;
                        break;
                }
            }
        });

        saveNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean names = false;
                boolean genderChecked = false;
                if ( (lastNameNew.getText().toString().equals("") || firstNameNew.getText().toString().equals("") )) {
                    names = true;
                }
                if((!maleNew.isChecked() && !femaleNew.isChecked())) {
                    genderChecked = true;
                }

                if (names  || genderChecked) {

                    lastNameNew.setError(
                            lastNameNew.getText().toString().equals("")
                                    ? "Enter Last Name" : null
                    );

                    firstNameNew.setError(
                            firstNameNew.getText().toString().equals("")
                                    ? "Enter First Name" : null
                    );

                    if( genderChecked) {
                        Toast.makeText(EditActivity.this,"Select Gender",Toast.LENGTH_LONG).show();
                    }
                    return;
                }
                User user  =new User (
                        firstNameNew.getText().toString(),
                        lastNameNew.getText().toString(),
                        flagImageNew
                );
                Bundle sentData = new Bundle();
                sentData.putSerializable(DisplayActivity.USERTODISPLAY,user);
                Intent intent = new Intent(EditActivity.this, DisplayActivity.class);
                intent.putExtra(MainActivity.TAGIMAGE,sentData);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}
