package com.erkutdemirhan.recipedbeditor.domain;

import com.erkutdemirhan.recipedbeditor.domain.exception.IllegalInputException;
import com.erkutdemirhan.recipedbeditor.resources.Strings;

public class RecipeType {

	private String mName;

	public RecipeType(String name) throws IllegalInputException {
		setName(name);
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) throws IllegalInputException {
		if(name == null || name.length()<1) {
			throw new IllegalInputException(Strings.ERROR_MSG_RECIPETYPE_NONAME.toString());
		}
		if(isIllegalName(name)) {
			throw new IllegalInputException(Strings.ERROR_MSG_RECIPETYPE_ILLEGALNAME.toString());
		}
		String trimmedName          = name.trim().replaceAll(" +", " ");
		String nameFirstLetterUpper = Character.toUpperCase(trimmedName.charAt(0)) +
									trimmedName.substring(1).toLowerCase();		
		mName = nameFirstLetterUpper;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof RecipeType)) return false;
		return this.getName().equals(((RecipeType) obj).getName());
	}
	
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
	
	private boolean isIllegalName(String name) {
		String pattern = "[a-zA-ZöüığşçÖÜİŞÇ ]+";
		return !name.matches(pattern) || name.trim().length()<1;
	}
}
