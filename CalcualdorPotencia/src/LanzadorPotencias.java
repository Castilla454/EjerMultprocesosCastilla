import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LanzadorPotencias {

    // Método para lanzar un proceso de potencia en un rango específico y guardarlo en un archivo
    public static void main(String[] args) {
        LanzadorPotencias lanzador = new LanzadorPotencias();

        int[][] valores = {{10, 2}, {22, 3}, {5, 4}};
        String[] archivosSalida = {"potencia_2.txt", "potencia_3.txt", "potencia_4.txt"};

        for (int i = 0; i < valores.length; i++) {
            // Lanzar un proceso Potencia para cada cÃ¡lculo
            lanzador.lanzarProceso(valores[i][0], valores[i][1], archivosSalida[i]);
        }

        // Leer y mostrar los resultados de los archivos de salida
        for (String archivo : archivosSalida) {
            lanzador.leerResultado(archivo);
        }
    }

    public void lanzarProceso(int base, int exponente, String archivoSalida) {
        ProcessBuilder pb = new ProcessBuilder("java", "Potencia", String.valueOf(base), String.valueOf(exponente), archivoSalida);
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
                double resultado = Double.parseDouble(reader.readLine());
                System.out.println("Resultado de " + archivo + ": " + resultado);
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("El archivo " + archivo + " no existe.");
        }
    }
}
