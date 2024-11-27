import java.io.*;

public class Sumador {
    public static int sumaInterior(int inicio, int fin) {
        // Realiza la suma de los números entre inicio y fin, inclusive
        int suma = 0;
        for (int i = inicio; i <= fin; i++) {
            suma += i;
        }
        return suma;
    }

    public static void procesarArchivo(String archivoEntrada, String archivoSalida) {
        try {
            // Abrimos el archivo de entrada para leer
            BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
            // Creamos el archivo de salida para escribir
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida));

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                int inicio = Integer.parseInt(partes[0]);
                int fin = Integer.parseInt(partes[1]);

                // Realizamos la suma
                int resultado = sumaInterior(inicio, fin);

                // Escribimos el resultado en el archivo de salida
                bw.write("Resultado de la suma entre " + inicio + " y " + fin + ": " + resultado + "\n");
            }

            // Cerramos los archivos
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Procesamos el archivo de entrada y salida
        procesarArchivo("entrada.txt", "salida.txt");
    }
}
