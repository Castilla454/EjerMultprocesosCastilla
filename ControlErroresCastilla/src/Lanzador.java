import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {

    public void lanzarProceso(int inicio, int fin, String archivoSalida) {
        ProcessBuilder pb = new ProcessBuilder("java", "Sumador", String.valueOf(inicio), String.valueOf(fin), archivoSalida);
        pb.directory(new File("."));

        try {
            Process proceso = pb.start();
            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int leerResultado(String archivo) {
        int resultado = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            resultado = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static void main(String[] args) {
        Lanzador lanzador = new Lanzador();
        List<String> archivosSalida = new ArrayList<>();
        int sumaTotal = 0;

        // Obtener valores validados
        System.out.println("Configuración del proceso:");
        int[] valores = Validador.obtenerValoresValidos();
        int inicioRango = valores[0];
        int finRango = valores[1];
        int rangoPorProceso = 10;

        for (int i = 0; inicioRango <= finRango; i++) {
            int finProceso = Math.min(inicioRango + rangoPorProceso - 1, finRango);
            String archivoSalida = "suma_parcial_" + i + ".txt";
            archivosSalida.add(archivoSalida);

            lanzador.lanzarProceso(inicioRango, finProceso, archivoSalida);
            inicioRango = finProceso + 1;
        }

        for (String archivo : archivosSalida) {
            sumaTotal += lanzador.leerResultado(archivo);
        }

        System.out.println("La suma total de los números es: " + sumaTotal);
    }
}
