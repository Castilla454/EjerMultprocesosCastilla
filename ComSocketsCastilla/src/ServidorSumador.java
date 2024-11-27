import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorSumador {
    public static class ClienteHandler implements Runnable {
        private Socket socket;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // Obtener los flujos de entrada y salida
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                // Leer los dos números enviados por el cliente
                int inicio = input.readInt();
                int fin = input.readInt();

                // Calcular la suma
                int resultado = 0;
                for (int i = inicio; i <= fin; i++) {
                    resultado += i;
                }

                // Enviar el resultado de vuelta al cliente
                output.writeInt(resultado);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int puerto = 12345;  // Puerto en el que el servidor escucha
        ExecutorService pool = Executors.newFixedThreadPool(10);  // Pool para manejar múltiples clientes

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                // Aceptar nuevas conexiones de clientes
                Socket clienteSocket = servidor.accept();
                System.out.println("Nuevo cliente conectado");

                // Crear un nuevo hilo para manejar la conexión del cliente
                pool.submit(new ClienteHandler(clienteSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
