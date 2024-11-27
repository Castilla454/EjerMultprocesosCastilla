import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteLanzador {
    public static void main(String[] args) {
        String servidor = "localhost";  // Dirección del servidor (localhost para pruebas locales)
        int puerto = 12345;  // Puerto del servidor

        try (Socket socket = new Socket(servidor, puerto)) {
            // Crear los flujos de entrada y salida para comunicar con el servidor
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            // Enviar los números al servidor
            int inicio = 1;
            int fin = 5;
            output.writeInt(inicio);
            output.writeInt(fin);


            // Recibir el resultado de la suma
            int resultado = input.readInt();

            // Mostrar el resultado
            System.out.println("La suma de los números entre " + inicio + " y " + fin + " es: " + resultado);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
