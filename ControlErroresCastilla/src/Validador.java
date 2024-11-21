import java.util.Scanner;

public class Validador {

    // Método para obtener valores válidos
    public static int[] obtenerValoresValidos() {
        Scanner scanner = new Scanner(System.in);
        boolean datosValidos = false;
        int inicio = 0, fin = 0;

        // Controla si los datos son válidos
        do {
            try {
                System.out.println("Ingrese el valor de inicio:");
                inicio = Integer.parseInt(scanner.nextLine());

                System.out.println("Ingrese el valor de fin:");
                fin = Integer.parseInt(scanner.nextLine());

                // Validar que inicio <= fin
                if (inicio > fin) {
                    throw new IllegalArgumentException("El valor de inicio no puede ser mayor que el de fin.");
                }

                datosValidos = true; // Si todo es válido, salimos del bucle

            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese números válidos.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!datosValidos);

        return new int[]{inicio, fin};
    }
}
