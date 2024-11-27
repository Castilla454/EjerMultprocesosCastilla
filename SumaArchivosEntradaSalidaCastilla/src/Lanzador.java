import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Lanzador {
    public static void crearArchivoEntrada() {
        try {
            // Creamos el archivo de entrada y escribimos los valores
            BufferedWriter bw = new BufferedWriter(new FileWriter("entrada.txt"));
            bw.write("1 5\n");
            bw.write("10 15\n");
            bw.write("20 25\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ejecutarSumador() {
        try {
            // Ejecutamos el Sumador
            Process process = new ProcessBuilder("java", "Sumador").start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        crearArchivoEntrada();  // Crea el archivo de entrada
        ejecutarSumador();  // Ejecuta el Sumador
    }
}