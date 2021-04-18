package org.monstermash.statblock;

import javafx.beans.property.Property;

public record Stat(Property<String> name, Property<Integer> value) {}
