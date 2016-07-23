package google.firebase.firebasedemo;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Firebase ref_User = null;

    EditText eName, ePassword;
    Button loginButton;
    String name_forauth;
    String password_forauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        eName = (EditText) findViewById(R.id.editname);
        ePassword = (EditText) findViewById(R.id.editpassword);
        loginButton = (Button) findViewById(R.id.buttonlogin);
        onLoginButtonClick();
    }
    protected void onLoginButtonClick() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // getting the user inputs in a string
                name_forauth = eName.getText().toString().trim();
                password_forauth = ePassword.getText().toString().trim();
                // creating a reference for User child.
                ref_User = new Firebase(Config.FIREBASE_USER_NODE);

                ref_User.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild(name_forauth)) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                User user_password = snapshot.getValue(User.class);
                                String user_pass = user_password.getPassword();

                                if (password_forauth.equals(user_pass)) {
                                    Toast.makeText(getApplicationContext(), "USER AUTHENTICATED", Toast.LENGTH_SHORT).show();


                                }
                                else ePassword.setError("Incorrect Password");
                            }
                        } else {
                                eName.setError("User not registered");
                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), "DATABASE ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
