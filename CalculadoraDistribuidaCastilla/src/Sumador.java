import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Sumador {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: java Sumador <num1> <num2> <archivoSalida>");
            return;
        }

        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);
        String archivoSalida = args[2];

        int resultado = num1 + num2;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            writer.write(String.valueOf(resultado));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
