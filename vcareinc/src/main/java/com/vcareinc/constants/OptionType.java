package com.vcareinc.constants;

public enum OptionType {
	LISTING(Boolean.TRUE), EVENT(Boolean.TRUE), CLASSIFIED(Boolean.TRUE), ARTICLE(Boolean.TRUE), DEAL(Boolean.FALSE);

	private Boolean levelRequired;
	private OptionType(Boolean levelRequired) {
		this.setLevelRequired(levelRequired);
	}
	public Boolean getLevelRequired() {
		return levelRequired;
	}
	public void setLevelRequired(Boolean levelRequired) {
		this.levelRequired = levelRequired;
	}
}
