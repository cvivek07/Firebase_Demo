package google.firebase.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Config;
import com.firebase.client.Firebase;

public class RegisterActivity extends AppCompatActivity {


    Firebase ref = null;
    User user = null;
    private EditText eName, eEmail, eContactNumber, ePassword;
    private Button registerbutton;
    private String nameString, emailString, ContactNumberString, PasswordString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firebase.setAndroidContext(this);
    }

    protected void onStart() {
        super.onStart();
        eName = (EditText) findViewById(R.id.editName);
        eEmail = (EditText) findViewById(R.id.editEmail);
        eContactNumber = (EditText) findViewById(R.id.editContactNumber);
        ePassword = (EditText) findViewById(R.id.editPassword);

        registerbutton = (Button) findViewById(R.id.buttonRegister);
        onRegisterButtonClick();
    }

    void onRegisterButtonClick() {
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = new Firebase(google.firebase.firebasedemo.Config.FIREBASE_URL);
                nameString = eName.getText().toString().trim(); // getting the user inputs in a string
                emailString = eEmail.getText().toString().trim();
                ContactNumberString = eContactNumber.getText().toString().trim();
                PasswordString = ePassword.getText().toString().trim();

                // create a user object
                User user = new User();
                user.setName(nameString);
                user.setEmail(emailString);
                user.setContactNumber(ContactNumberString);
                user.setPassword(PasswordString);

                ref.child("Users").child(nameString).setValue(user); // saving the user object in database.
            }
        });

    }
}
