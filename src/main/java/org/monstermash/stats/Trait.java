package org.monstermash.stats;

public class Trait implements Attribute {
    private final String name;
    private final String description;

    public Trait(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    @Override
    public String asText() {
        return String.format("%s +%s", name, description);
    }

    @Override
    public String toString() {
        return "Skill: " + this.asText();
    }

}
