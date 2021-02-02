package org.monstermash.stats;

public class Language implements Attribute {
	private final String lang;
	
	public Language(String lang) {
		this.lang = lang;
	}

	@Override
	public String asText() {
		return this.lang;
	}

	@Override
	public String toString() {
		return "Language: " + this.asText();
	}
}
