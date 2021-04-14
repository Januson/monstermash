package org.monstermash.statblock;

public enum Type implements Translatable {
        BEAST("ui.monster.type.beast"),
        HUMANOID("ui.monster.type.humanoid"),
        UNDEAD("ui.monster.type.undead");

        private final String key;

        Type(final String key) {
            this.key = key;
        }

        @Override
        public String key() {
            return this.key;
        }
    }