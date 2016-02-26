package com.erkutdemirhan.recipedbeditor.resources;

public enum Strings {
	ERROR_MSG_INGREDIENT_NONAME("The ingredient has no name!"), 
	ERROR_MSG_INGREDIENT_ILLEGALNAME("The ingredient name should only contain letters and spaces!"),
	ERROR_MSG_INGREDIENT_ILLEGALAMOUNT("The amount should only contaion letters, numbers and spaces!"), 
	ERROR_MSG_RECIPETYPE_NONAME("The recipe type has no name!"), 
	ERROR_MSG_RECIPETYPE_ILLEGALNAME("The recipe type name should only contain letters and spaces!");
	
	private final String mMsg;
	
	Strings(String msg) {
		mMsg = msg;
	}
	
	@Override
	public String toString() {
		return mMsg;
	}
}
