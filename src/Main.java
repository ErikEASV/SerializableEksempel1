/************'
 *
 * Serialization eksempel
 * Model indeholder klasserne Person og Adresse.
 * Hver person kan have flere adresser (én til mange relation)
 * Der lægges passende data i Person (og dermed Adresse)
 * Objekterne fra Person (og dermed Adresse) serialiseres og skrives ud på fil.
 * Objekterne slettes.
 * Objekterne læses ind igen og relationen er genskabt.
 * For at ungå EOF indlæsningsproblem gemmer vi antal poster først på filen.
 * Når vi skal læses filen ind vil første tal være det antal objekter der findes på filen!
 *
 * EK nov. 2024
 */


import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Test af serialisering!\n");

        ArrayList<Person> personer = new ArrayList();

        Person p1 = new Person(1, "Anne");
        p1.tilknytAdresse(new Adresse("Gaden 24", 3450, "Hillerød"));
        p1.tilknytAdresse(new Adresse("Sommervej 23", 9990, "Sommerby"));

        Person p2 = new Person(2, "Karl");
        p2.tilknytAdresse(new Adresse("Gaden 26", 3450, "Hillerød"));
        p2.tilknytAdresse(new Adresse("Vintervej 523", 1050, "Vinterlev"));

        Person p3 = new Person(3, "Jann");
        p3.tilknytAdresse(new Adresse("Bygaden 2", 7450, "Broby"));

        personer.add(p1);
        personer.add(p2);
        personer.add(p3);

        // Udskriv personer
        System.out.println("Listen personer indeholder (før): ");
        for (Person p : personer)
            System.out.println(p);

        // Skriv objekter til fil.
        skrivObjekter(personer);

        // Slet liste, så vi kan se at data faktisk genskabes
        System.out.println("Listen personer slettes\n");
        personer.clear();
        p1 = null;
        p2 = null;
        p3 = null;

        //
        // Læs ind fra fil
        personer = læsObjekter();

        // Udskriv personer
        System.out.println("Listen personer indeholder (efter): ");
        for (Person p : personer)
            System.out.println(p);

    }

    static void skrivObjekter(ArrayList<Person> personer) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("minfil.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        // Udskriv først antal poster til filen - så står det i filen hvor mange poster der skal læses ind.
        objectOutputStream.writeInt(personer.size());
        for (Person p : personer)
            objectOutputStream.writeObject(p);
        System.out.println("Der er udskrevet "+personer.size()+" poster.\n");

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    static ArrayList<Person> læsObjekter() throws IOException, ClassNotFoundException {
        ArrayList<Person> liste = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream("minfil.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        // Først læser vi antallet af poster ind. Derefter ved vi hvor mange poster der skal læses.
        System.out.println("Personer læses ind:");
        int antal = objectInputStream.readInt();
        for (int i=0; i<antal; ++i) {
            Person p = (Person)objectInputStream.readObject();
            System.out.println(p);
            liste.add(p);
        }
        System.out.println("Der er indlæst "+antal+" poster.\n");

        objectInputStream.close();
        return liste;
    }
}