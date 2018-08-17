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

public class RegistryActivity extends AppCompatActivity {


    FirebaseAuth auth;
    EditText txtEmail, txtPassword;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        auth = FirebaseAuth.getInstance();

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnRegistrar = findViewById(R.id.btnRegister);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Debe de ingresar un correo", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Debe de ingresar su contraseña", Toast.LENGTH_SHORT).show();
                }
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegistryActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_LONG).show();

                                Intent mainActivity=new Intent(RegistryActivity.this,MainActivity.class);
                                startActivity(mainActivity);
                                finish();
                            }
                        }
                    });

            }
        });
    }
}
