package com.example.orquesta;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity {

    // Declarar las variables de instancia para las vistas
    static final ArrayList<Cuenta> cuentas = new ArrayList<>();

    private EditText etNombre, etApellidos, etEmail, etContraseña, etConfContraseña, etTelefono, etTrayectoria, etInstrumentos;
    private Spinner spinnerTipo;
    private Button btnRegister;
    private TextView tvLoginAhora, tvTrayectoria, tvInstrumentos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar vistas
        etNombre = findViewById(R.id.et_nombre);
        etApellidos = findViewById(R.id.et_apellidos);
        etEmail = findViewById(R.id.et_email_reg);
        etContraseña = findViewById(R.id.et_contraseña_reg);
        etConfContraseña = findViewById(R.id.et_conf_contraseña);
        etTelefono = findViewById(R.id.et_telefono);
        etTrayectoria = findViewById(R.id.et_trayectoria);
        spinnerTipo = findViewById(R.id.spinner_tipo);
        btnRegister = findViewById(R.id.btnRegister);
        tvLoginAhora = findViewById(R.id.tv_login_ahora);

        // Configurar el ArrayAdapter usando el array de strings y un spinner predeterminado
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.usuario_tipo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);

        // Configurar el listener del botón si es necesario
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recuperar los valores de los campos de texto y el spinner

                String nombre = etNombre.getText().toString();
                String apellidos = etApellidos.getText().toString();
                String email = etEmail.getText().toString().trim();
                String contraseña = etContraseña.getText().toString().trim();
                String confContraseña = etConfContraseña.getText().toString().trim();
                String telefono = etTelefono.getText().toString();
                String trayectoria = etTrayectoria.getText().toString();
                String tipo = spinnerTipo.getSelectedItem().toString();
                ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

                // Validación de campos vacíos
                if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || contraseña.isEmpty() ||
                        confContraseña.isEmpty() || telefono.isEmpty() || titulacion.isEmpty()) {
                    Snackbar.make(constraintLayout, "Por favor, rellena todos los campos.", Snackbar.LENGTH_LONG).show();
                    return;
                }


                // Validación del formato de correo electrónico
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Snackbar.make(constraintLayout, "Por favor, introduce un correo electrónico válido.", Snackbar.LENGTH_LONG).show();
                    return; // Detener la ejecución si el formato de correo no es válido
                }

                // Comprobar que las contraseñas coincidan
                if (!contraseña.equals(confContraseña)) {
                    if (!contraseña.equals(confContraseña)) {
                        Snackbar.make(constraintLayout, "Las contraseñas no coinciden", Snackbar.LENGTH_LONG).show();
                        return; // Detener la ejecución si las contraseñas no coinciden
                    }
                }

                // Comprobar si ya existe una cuenta con el mismo email
                for (Cuenta cuenta : cuentas) {
                    if (cuenta.email.equalsIgnoreCase(email)) {
                        Snackbar.make(constraintLayout, "Ya existe una cuenta con este correo electrónico", Snackbar.LENGTH_LONG).show();
                        return; // Detener la ejecución si se encuentra un correo electrónico duplicado
                    }
                }
                // Crear y añadir la nueva cuenta
                Cuenta nuevaCuenta = new Cuenta(nombre, apellidos, email, contraseña, telefono, trayectoria, tipo);
                cuentas.add(nuevaCuenta);

                // Redirigir al usuario a la actividad correspondiente
                Intent intent;
                switch (tipo) {
                    case "Miembro":
                        intent = new Intent(RegistroActivity.this, MiembroActivity.class);
                        break;
                    case "Director":
                        intent = new Intent(RegistroActivity.this, DirectorActivity.class);
                        break;
                    default:
                        throw new IllegalStateException("Tipo de usuario no válido: " + tipo);
                }
                startActivity(intent);
                finish(); // Finalizar la actividad actual
            }
        });

        // Configurar el listener para el TextView tvLoginAhora
        tvLoginAhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar LoginActivity
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                // Iniciar la actividad
                startActivity(intent);

                // Finalizar la actividad actual sin que el usuario regrese a ella presionando el botón Atrás
                finish();
            }

            // Más configuración si es necesario...
        });
    }
}
