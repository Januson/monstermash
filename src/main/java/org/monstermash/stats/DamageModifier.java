package org.monstermash.stats;

public class DamageModifier implements Attribute {
	private final DamageMods modifier;
	private final ModifierType type;
	private final String value;
	
	public enum ModifierType {
		CONDITION,
		DAMAGE
	}
	public enum DamageMods {
		IMMUNE,
		RESISTANT,
		VULNERABLE
	}

	public DamageModifier(DamageMods modifier, ModifierType type, String value) {
		this.modifier = modifier;
		this.type = type;
		this.value = value;
	}

	@Override
	public String asText() {
		return this.value;
	}

	@Override
	public String toString() {
		String returnValue;
		if (type == ModifierType.CONDITION) {
			returnValue = "Condition ";
		} else {
			returnValue = "Damage ";
		}
		switch (modifier) {
			case IMMUNE:
				returnValue += "Immunity: ";
				break;
			case RESISTANT:
				returnValue += "Resistance: ";
				break;
			default:
				returnValue += "Vulnerability: ";
				break;
		}
		return returnValue;
	}
}
