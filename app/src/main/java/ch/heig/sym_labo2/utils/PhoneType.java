package ch.heig.sym_labo2.utils;

/**
 * Enum utilisé pour la transmission par sérialisation en XML afin de respecter la DTD sur le serveur
 */
public enum PhoneType {
    HOME("home"),
    WORK("work"),
    MOBILE("mobile");

    private final String type;

    private PhoneType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}