package org.monstermash.statblock;

public enum Size implements Translatable {
        TINY("ui.monster.size.tiny"),
        SMALL("ui.monster.size.small"),
        MEDIUM("ui.monster.size.medium"),
        LARGE("ui.monster.size.large"),
        HUGE("ui.monster.size.huge"),
        GARGANTUAN("ui.monster.size.gargantuan");

        private final String key;

        Size(final String key) {
            this.key = key;
        }

        @Override
        public String key() {
            return this.key;
        }
    }