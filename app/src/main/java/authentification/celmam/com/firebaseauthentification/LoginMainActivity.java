package authentification.celmam.com.firebaseauthentification;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginMainActivity extends AppCompatActivity {

    FirebaseAuth auth;

    private EditText txtEmail;
    private EditText textPassword;
    private Button btnRegistrar;
    private Button btnIngresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        auth = FirebaseAuth.getInstance();

        txtEmail = findViewById(R.id.txtEmail);
        textPassword = findViewById(R.id.txtPassword);
        btnIngresar = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegister);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String password = textPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Debe de ingresar un correo", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Debe de ingresar su contraseña", Toast.LENGTH_SHORT).show();
                }


                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginMainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent mainActivity = new Intent(LoginMainActivity.this, MainActivity.class);
                                    startActivity(mainActivity);
                                    finish();
                                }
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Se produjo un error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(LoginMainActivity.this, RegistryActivity.class);
                startActivity(registrar);
            }
        });
    }
}
