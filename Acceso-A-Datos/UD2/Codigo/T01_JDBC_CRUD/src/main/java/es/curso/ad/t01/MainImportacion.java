package es.curso.ad.t01;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

public class MainImportacion {
    public static void main(String[] args) {
        try {
            int importados = new ImportarTxt().importar(
                    Path.of("datos", "clubes_importacion.txt"));
            System.out.println("Clubes importados: " + importados);
        } catch (IOException | SQLException | IllegalArgumentException e) {
            System.err.println("No se pudo importar: " + e.getMessage());
        }
    }
}
