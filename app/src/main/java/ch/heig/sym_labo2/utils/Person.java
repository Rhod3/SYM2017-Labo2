package ch.heig.sym_labo2.utils;

/**
 * Created by Rhod3 on 17.11.2017.
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
