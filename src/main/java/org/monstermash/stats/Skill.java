package org.monstermash.stats;

public class Skill implements Attribute {
    private final String name;
    private final int modifier;

    public Skill(String name, int modifier) {
        this.name = name;
        this.modifier = modifier;
    }

    @Override
    public String asText() {
        return String.format("%s +%d", name, modifier);
    }

    @Override
    public String toString() {
        return "Skill: " + this.asText();
    }

}
