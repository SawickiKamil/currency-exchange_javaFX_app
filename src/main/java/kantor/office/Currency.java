package kantor.office;

public enum Currency {

    PLN("PLN"),
    EUR("EUR"),
    RUB("RUB"),
    JPY("JPY");


    private String name;

    Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
