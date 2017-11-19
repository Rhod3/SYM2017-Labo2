package ch.heig.sym_labo2.utils;

/**
 * Modèle sérialisable utilisé pour la transmission par sérialisation (en JSON ou en XML)
 */
public class Person {
    private String name;
    private String firstname;

    public Person(String name, String firstname) {
        this.name = name;
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
