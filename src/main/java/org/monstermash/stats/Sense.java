package org.monstermash.stats;

public class Sense implements Attribute {
    private final String sense;
    private final int range;

    public Sense(String sense, int range) {
        this.sense = sense;
        this.range = range;
    }

    @Override
    public String asText() {
        return String.format("%s %d ft", sense, range);
    }

    @Override
    public String toString() {
        return "Sense: " + this.asText();
    }
}
