package es.curso.ad.t04;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.Sorts;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class ClubDocumentalDao implements AutoCloseable {
    private final MongoClient cliente;
    private final MongoCollection<Document> clubes;

    public ClubDocumentalDao() {
        cliente = MongoClients.create("mongodb://localhost:27018");
        MongoDatabase db = cliente.getDatabase("ad_ud2");
        clubes = db.getCollection("club");
    }

    public void guardarEjemplos() {
        Document granada = new Document("_id", 1)
                .append("nombre", "Granada CF")
                .append("ciudad", "Granada")
                .append("jugadores", List.of(
                        new Document("nombre", "Lucía").append("dorsal", 10),
                        new Document("nombre", "Pablo").append("dorsal", 7)));
        Document malaga = new Document("_id", 2)
                .append("nombre", "Málaga CF")
                .append("ciudad", "Málaga")
                .append("jugadores", List.of(
                        new Document("nombre", "Sara").append("dorsal", 9)));
        ReplaceOptions opciones = new ReplaceOptions().upsert(true);
        clubes.replaceOne(eq("_id", 1), granada, opciones);
        clubes.replaceOne(eq("_id", 2), malaga, opciones);
    }

    public List<Document> listar() {
        return clubes.find().sort(Sorts.ascending("_id"))
                .into(new ArrayList<>());
    }

    public Document buscar(int id) {
        return clubes.find(eq("_id", id)).first();
    }

    public boolean cambiarCiudad(int id, String nuevaCiudad) {
        return clubes.updateOne(eq("_id", id), set("ciudad", nuevaCiudad))
                .getMatchedCount() == 1;
    }

    public boolean borrar(int id) {
        return clubes.deleteOne(eq("_id", id)).getDeletedCount() == 1;
    }

    @Override
    public void close() {
        cliente.close();
    }
}
