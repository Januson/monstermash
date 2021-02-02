package org.monstermash.stats;

public class Ability implements Attribute {
    private final String name;
    private final String description;

    public Ability(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String asText() {
        return String.format("<html><b><i>%s.</i></b> %s<br><br></html>",
            this.name, this.description);
    }

    @Override
    public String toString() {
        return "Ability: " + this.asText();
    }
}
