package es.curso.ad.t04;

import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        try (ClubDocumentalDao dao = new ClubDocumentalDao()) {
            dao.guardarEjemplos();
            System.out.println("CLUBES EN MONGODB");
            for (Document club : dao.listar()) {
                System.out.println(club.toJson());
            }
            System.out.println("Buscar 1: " + dao.buscar(1).toJson());
            System.out.println("Actualizar 2: " + dao.cambiarCiudad(2, "Málaga"));
            System.out.println("Borrar inexistente: " + dao.borrar(99));
        } catch (RuntimeException e) {
            System.err.println("Error documental: " + e.getMessage());
        }
    }
}
