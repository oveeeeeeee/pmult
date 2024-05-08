package com.example.orquesta;

import java.util.ArrayList;

public class AlmacenCuentas {
    public static ArrayList<Cuenta> cuentas = new ArrayList<>();

    // Método para añadir cuentas al almacenamiento (simulando un registro)
    public static void añadirCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    // Método para obtener una cuenta específica por email
    public static Cuenta obtenerCuentaPorEmail(String email) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getEmail().equalsIgnoreCase(email)) {
                return cuenta;
            }
        }
        return null;
    }


    // Método para actualizar una cuenta específica
    public static void actualizarCuenta(Cuenta cuentaActualizada) {
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getEmail().equals(cuentaActualizada.getEmail())) {
                cuentas.set(i, cuentaActualizada);
                break;
            }
        }
    }
}