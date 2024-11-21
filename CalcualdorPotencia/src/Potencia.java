import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Potencia {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Por favor, ingrese los valores de la base, el exponente y el nombre del archivo de salida.");
            return;
        }

        try {
            // Parseo de los valores de la base y exponente
            int base = Integer.parseInt(args[0]);
            int exponente = Integer.parseInt(args[1]);
            String archivoSalida = args[2];

            // Cálculo de la potencia
            double potencia = Math.pow(base, exponente);
            System.out.println("Calculando " + base + "^" + exponente + " = " + potencia); // Depuración

            // Escribir el resultado en el archivo de salida
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
                writer.write(String.valueOf(potencia));
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
