package com.erkutdemirhan.recipedbeditor.domain;

import com.erkutdemirhan.recipedbeditor.domain.exception.IllegalInputException;
import com.erkutdemirhan.recipedbeditor.resources.Strings;

public class Ingredient {

	private String mName;
	private String mAmount;
	
	public Ingredient(String name, String amount) throws IllegalInputException {
		setName(name);
		setAmount(amount);
	}
	
	public void setName(String name) throws IllegalInputException {
		if(name == null || name.length() < 1) {
			throw new IllegalInputException(Strings.ERROR_MSG_INGREDIENT_NONAME.toString());
		}
		if(isIllegalName(name)) {
			throw new IllegalInputException(Strings.ERROR_MSG_INGREDIENT_ILLEGALNAME.toString());
		}
		mName = prepareName(name);
	}
	
	public void setAmount(String amount) throws IllegalInputException {
		if(amount == null) {
			mAmount = "";
			return;
		}
		if(isIlleagalAmount(amount)) {
			throw new IllegalInputException(Strings.ERROR_MSG_INGREDIENT_ILLEGALAMOUNT.toString());
		}
		mAmount = amount.trim().replaceAll(" +", " ");
	}
	
	public String getName() {
		return mName;
	}
	
	public String getAmount() {
		return mAmount;
	}
	
	private boolean isIllegalName(String name) {
		String pattern      = "[a-zA-ZöüığşçÖÜİŞÇ ]+";
		String noWhitespace = name.replaceAll(" ", ""); 
		return !name.matches(pattern) || noWhitespace.length() < 1;
	}
	
	private boolean isIlleagalAmount(String amount) {
		String pattern      = "[0-9a-zA-ZöüığşçÖÜİŞÇ ]+";
		return !amount.matches(pattern);
	}
	
	private String prepareName(String name) {
		String trimmed = name.trim();
		trimmed        = trimmed.replaceAll(" +", " ");
		return Character.toUpperCase(trimmed.charAt(0)) + trimmed.substring(1).toLowerCase();
	}	
	
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof Ingredient)) return false;
		return this.getName().equals(((Ingredient) obj).getName());
	}
	
	public int hashCode() {
		return this.getName().hashCode();
	}
}
