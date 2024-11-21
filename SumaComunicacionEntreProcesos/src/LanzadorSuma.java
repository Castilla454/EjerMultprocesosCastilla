import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class LanzadorSuma {

    public static void main(String[] args) {
        LanzadorSuma lanzador = new LanzadorSuma();
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario los valores de inicio y fin
        int inicio, fin;


            System.out.print("Ingrese el valor de inicio: ");
            inicio = scanner.nextInt();
            System.out.print("Ingrese el valor de fin: ");
             fin = scanner.nextInt();


        scanner.close();

        // Archivos de salida
        String archivoPares = "suma_pares.txt";
        String archivoImpares = "suma_impares.txt";
        String archivoTotal = "suma_total.txt";

        // Lanzar procesos SumaPares y SumaImpares
        lanzador.lanzarProceso("SumaPares", inicio, fin, archivoPares);
        lanzador.lanzarProceso("SumaImpares", inicio, fin, archivoImpares);

        // Leer resultados parciales y mostrarlos
        int sumaPares = lanzador.leerResultado(archivoPares);
        int sumaImpares = lanzador.leerResultado(archivoImpares);
        System.out.println("Suma de pares: " + sumaPares);
        System.out.println("Suma de impares: " + sumaImpares);

        // Ampliación: Lanzar el proceso SumadorTotal
        lanzador.lanzarProceso("SumadorTotal", archivoPares, archivoImpares, archivoTotal);

        // Leer y mostrar el resultado total
        int sumaTotal = lanzador.leerResultado(archivoTotal);
        System.out.println("Suma total: " + sumaTotal);
    }

    // Método para lanzar un proceso con argumentos
    public void lanzarProceso(String clase, int inicio, int fin, String archivoSalida) {
        ProcessBuilder pb = new ProcessBuilder("java", clase, String.valueOf(inicio), String.valueOf(fin), archivoSalida);
        pb.directory(new File("."));
        try {
            Process proceso = pb.start();
            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para lanzar un proceso que suma resultados parciales
    public void lanzarProceso(String clase, String archivoPares, String archivoImpares, String archivoSalida) {
        ProcessBuilder pb = new ProcessBuilder("java", clase, archivoPares, archivoImpares, archivoSalida);
        pb.directory(new File("."));
        try {
            Process proceso = pb.start();
            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para leer el valor de un archivo
    public int leerResultado(String archivo) {
        if (Files.exists(Paths.get(archivo))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                return Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("El archivo " + archivo + " no existe.");
        }
        return 0;
    }
}