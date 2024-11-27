import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class LanzadorCalculadoraDistribuida {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Calculadora Distribuida");
        System.out.println("Seleccione una operación:");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Multiplicar");
        System.out.println("4. Dividir");

        int opcion = scanner.nextInt();

        System.out.print("Ingrese el primer número: ");
        int num1 = scanner.nextInt();
        System.out.print("Ingrese el segundo número: ");
        int num2 = scanner.nextInt();

        scanner.close();

        String clase = "";
        String archivoSalida = "resultado_operacion.txt";

        switch (opcion) {
            case 1:
                clase = "Sumador";
                break;
            case 2:
                clase = "Restador";
                break;
            case 3:
                clase = "Multiplicador";
                break;
            case 4:
                clase = "Divisor";
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        LanzadorCalculadoraDistribuida lanzador = new LanzadorCalculadoraDistribuida();
        lanzador.lanzarProceso(clase, num1, num2, archivoSalida);

        // Leer y mostrar el resultado
        double resultado = lanzador.leerResultado(archivoSalida);
        System.out.println("El resultado es: " + resultado);
    }

    public void lanzarProceso(String clase, int num1, int num2, String archivoSalida) {
        ProcessBuilder pb = new ProcessBuilder("java", clase, String.valueOf(num1), String.valueOf(num2), archivoSalida);
        pb.directory(new File(".")); // Directorio actual

        try {
            Process proceso = pb.start();
            proceso.waitFor(); // Esperar a que termine el proceso
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public double leerResultado(String archivo) {
        if (Files.exists(Paths.get(archivo))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                return Double.parseDouble(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("El archivo " + archivo + " no existe.");
        }
        return 0;
    }


}