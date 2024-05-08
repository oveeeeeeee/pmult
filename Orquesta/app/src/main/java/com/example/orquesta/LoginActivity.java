package com.example.orquesta;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etContraseña;
    private Button btnIniciar;
    private TextView tvRegistrarAhora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicialización de las vistas
        etEmail = findViewById(R.id.et_email);
        etContraseña = findViewById(R.id.et_contraseña);
        btnIniciar = findViewById(R.id.btn_iniciar);
        tvRegistrarAhora = findViewById(R.id.tv_registrar_ahora);

        // Configurar el evento clic del botón de inicio de sesión
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString().trim();
                String contraseña = etContraseña.getText().toString();

                // Verificar el formato del correo electrónico
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Snackbar.make(v, "Por favor, introduce un correo electrónico válido.", Snackbar.LENGTH_LONG).show();
                    return;
                }

                // Verificar si el correo electrónico existe y si la contraseña coincide
                boolean encontrado = false;
                for (Cuenta cuenta : RegistroActivity.cuentas) {
                    if (cuenta.email.equalsIgnoreCase(email) && cuenta.contraseña.equals(contraseña)) {
                        encontrado = true;
                        // Abrir la Activity correspondiente al tipo de cuenta
                        switch (cuenta.tipo) {
                            case "Miembro":
                                startActivity(new Intent(LoginActivity.this, MiembroActivity.class));
                                break;
                            case "Director":
                                startActivity(new Intent(LoginActivity.this, DirectorActivity.class));
                                break;
                        }
                        finish(); // Finalizar LoginActivity
                        break;
                    }
                }

                for (Cuenta cuenta : AlmacenCuentas.cuentas) {
                    if (cuenta.getEmail().equalsIgnoreCase(email) && cuenta.getContraseña().equals(contraseña)) {
                        encontrado = true;
                        // Solo aquí pasas el email del usuario a EditarPerfilActivity
                        Intent intent = new Intent(LoginActivity.this, EditarPerfilActivity.class);
                        intent.putExtra("emailCuenta", email);
                        startActivity(intent);
                        finish();
                        break;
                    }
                }

                if (!encontrado) {
                    // Si no se encuentra el correo o no coincide la contraseña
                    Snackbar.make(v, "Correo electrónico o contraseña incorrectos.", Snackbar.LENGTH_LONG).show();
                }
                // Pasar el email del usuario
                Intent intent = new Intent(LoginActivity.this, EditarPerfilActivity.class);
                intent.putExtra("emailCuenta", email);

                startActivity(intent);

            }

        });

        // Configurar el evento clic para el TextView tvRegistrarAhora
        tvRegistrarAhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar RegistroActivity
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                // Iniciar la actividad RegistroActivity

                startActivity(intent);
            }
        });
    }

    // Puedes agregar más métodos si es necesario...
}
