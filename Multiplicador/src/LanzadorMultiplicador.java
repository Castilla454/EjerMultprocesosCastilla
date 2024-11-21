//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class LanzadorMultiplicador {

    public static void main(String[] args) {
        LanzadorMultiplicador lanzador = new LanzadorMultiplicador();
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario los valores de inicio y fin
        System.out.print("Ingrese el valor de inicio: ");
        int inicio = scanner.nextInt();
        System.out.print("Ingrese el valor de fin: ");
        int fin = scanner.nextInt();
        scanner.close();

        String archivoSalida = "multiplicacion_resultado.txt";

        // Lanzar el proceso Multiplicador
        lanzador.lanzarProceso(inicio, fin, archivoSalida);

        // Leer y mostrar el resultado del archivo de salida
        lanzador.leerResultado(archivoSalida);
    }

    public void lanzarProceso(int inicio, int fin, String archivoSalida) {
        ProcessBuilder pb = new ProcessBuilder("java", "Multiplicador", String.valueOf(inicio), String.valueOf(fin), archivoSalida);
        pb.directory(new File(".")); // Ajusta la ruta si es necesario

        try {
            Process proceso = pb.start();
            proceso.waitFor(); // Esperar a que el proceso termine
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void leerResultado(String archivo) {
        if (Files.exists(Paths.get(archivo))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String resultado = reader.readLine();
                System.out.println("Resultado de la multiplicaciÃ³n: " + resultado);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("El archivo " + archivo + " no existe.");
        }
    }
}
