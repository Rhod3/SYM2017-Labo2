package ch.heig.sym_labo2.utils;

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
