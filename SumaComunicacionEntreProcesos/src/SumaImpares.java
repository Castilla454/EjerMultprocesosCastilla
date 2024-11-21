import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SumaImpares {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: java SumaImpares <inicio> <fin> <archivoSalida>");
            return;
        }

        int inicio = Integer.parseInt(args[0]);
        int fin = Integer.parseInt(args[1]);
        String archivoSalida = args[2];

        int suma = 0;
        for (int i = inicio; i <= fin; i++) {
            if (i % 2 != 0) {
                suma += i;
            }
        }

        // Guardar el resultado en el archivo de salida
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            writer.write(String.valueOf(suma));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}