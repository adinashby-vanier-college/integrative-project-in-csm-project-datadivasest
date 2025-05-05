package edu.vanier.template.models;

public class Elements {

    private final int atomicNumber;
    private final int period;
    private final int group;
    private final String name;
    private final String symbol;

    public Elements(int atomicNumber, int period, int group, String name, String symbol) {
        this.atomicNumber = atomicNumber;
        this.period = period;
        this.group = group;
        this.name = name;
        this.symbol = symbol;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public int getPeriod() {
        return period;
    }

    public int getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getSymbol(){
        return symbol;
    }
}
