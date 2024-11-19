import java.io.Serializable;

public class Adresse implements Serializable {
    // id til serialisering skal være unikt for hver klasse
    private static final long serialVersionUID = 1L;

    private String vej;
    private int postnr;
    private String by;

    public Adresse(String vej, int postnr, String by) {
        this.vej = vej;
        this.postnr = postnr;
        this.by = by;
    }

    @Override
    public String toString() {
        return vej+" "+postnr+" "+by+" /";
    }

}
