import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    // id til serialisering skal være unikt for hver klasse
    private static final long serialVersionUID = 2L;

    private int id;
    private String navn;
    private List<Adresse> adresser = new ArrayList<Adresse>();

    public Person(int id, String navn) {
        this.id = id;
        this.navn = navn;
    }

    public void tilknytAdresse(Adresse adr) {
        adresser.add(adr);
    }

    @Override
    public String toString() {
        String al = "";
        for (Adresse a : adresser) al += " "+a;
        return id+" "+navn+" "+ al;
    }

}
