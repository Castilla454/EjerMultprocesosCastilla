

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Multiplicador {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Por favor, ingrese los valores de inicio, fin y el nombre del archivo de salida.");
            return;
        }

        try {
            // Parseo de los valores de inicio y fin
            int inicio = Integer.parseInt(args[0]);
            int fin = Integer.parseInt(args[1]);
            String archivoSalida = args[2];

            // Cálculo de la multiplicación de los números entre inicio y fin
            BigInteger producto = BigInteger.ONE;
            for (int i = inicio; i <= fin; i++) {
                producto = producto.multiply(BigInteger.valueOf(i));
            }

            System.out.println("Multiplicando números del " + inicio + " al " + fin + " = " + producto); // Depuración

            // Escribir el resultado en el archivo de salida
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
                writer.write(producto.toString());
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}