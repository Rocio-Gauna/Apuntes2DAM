import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        Path ruta = Path.of("reservas.csv");
        if (Files.notExists(ruta)) {
            System.out.println("El archivo no existe");
            return;
        }
       Scanner teclado = new Scanner(System.in);
        System.out.print("ID de la reserva: ");
        String entrada = teclado.nextLine();

        try {
            int idBuscado = Integer.parseInt(entrada.trim());

            try {
                List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
                List<String> nuevasLineas = new ArrayList<>();

                boolean encontrada = false;

                if (!lineas.isEmpty()) {
                    nuevasLineas.add(lineas.get(0));
                }

                for (int i = 1; i < lineas.size(); i++) {
                    String linea = lineas.get(i);
                    String[] campos = linea.split(";", -1);

                    boolean eliminar = false;

                    if (campos.length > 0) {
                        try {
                            int id = Integer.parseInt(campos[0].trim());

                            if (id == idBuscado) {
                                eliminar = true;
                                encontrada = true;
                            }

                        } catch (NumberFormatException e) {
                            eliminar = false;
                        }
                    }

                    if (!eliminar) {
                        nuevasLineas.add(linea);
                    }
                }

                if (encontrada) {
                    Files.write(ruta, nuevasLineas, StandardCharsets.UTF_8);
                    System.out.println("Reserva cancelada.");
                } else {
                    System.out.println("No existe una reserva con ese ID.");
                }

            } catch (IOException e) {
                System.out.println("Error al acceder al archivo: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("El ID introducido no es numérico.");
        }

        teclado.close();
    }
}
