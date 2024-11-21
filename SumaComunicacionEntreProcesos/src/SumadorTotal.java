import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SumadorTotal {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: java SumadorTotal <archivoPares> <archivoImpares> <archivoSalida>");
            return;
        }

        String archivoPares = args[0];
        String archivoImpares = args[1];
        String archivoSalida = args[2];

        int sumaTotal = 0;

        // Leer suma de pares
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoPares))) {
            sumaTotal += Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Leer suma de impares
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoImpares))) {
            sumaTotal += Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Guardar el resultado total en el archivo de salida
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            writer.write(String.valueOf(sumaTotal));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}