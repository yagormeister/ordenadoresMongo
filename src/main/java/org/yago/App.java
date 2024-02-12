package org.yago;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import model.Ordenador;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConnectionString connString = new ConnectionString("mongodb://localhost:27017");
        CodecRegistry pojoCodecRegistry = fromProviders(
                PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .codecRegistry(codecRegistry)
                .build();

        try(MongoClient mc = MongoClients.create(clientSettings)){
            MongoDatabase mdb = mc.getDatabase("midb");
            MongoCollection<Ordenador> mco = mdb.getCollection("ordenadores", Ordenador.class);
            mco.drop();

            Ordenador o1 = new Ordenador(1,"Ordenador Streaming", Arrays.asList("Teclado","Raton", "Monitor"),1350.2);
            Ordenador o2 = new Ordenador(2,"Ordenador Juegos", Arrays.asList("Teclado","Raton", "Monitor", "Barebone"),2300);
            Ordenador o3 = new Ordenador(3,"Ordenador Trabajo", Arrays.asList("Torre"),1100);
            Ordenador o4 = new Ordenador(4,"Ordenador Servidor", Arrays.asList("Torre"),1100);


            //insert one
            mco.insertOne(o1);

            //insertMany
            mco.insertMany(Arrays.asList(o2,o3,o4));
            Ordenador oDevuelto = mco.find().first();


            System.out.println("El ordenador devuelto es " + oDevuelto);

            System.out.println("Lista de ordenadores");
            List<Ordenador> ordenadores = mco.find().into(new ArrayList<Ordenador>());
            for (Ordenador o :
                    ordenadores) {
                System.out.println(o);
            }

            //ordenadores que lleven teclado
            System.out.println("Ordenadores que llevan teclado");
            List<Ordenador> resFilt = mco.find(Filters.elemMatch("componentes", Filters.eq("teclado"))).into(new ArrayList<Ordenador>());
            System.out.println(resFilt);





        }
    }
}
