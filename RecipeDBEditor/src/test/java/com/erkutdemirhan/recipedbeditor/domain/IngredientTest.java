package com.erkutdemirhan.recipedbeditor.domain;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.erkutdemirhan.recipedbeditor.domain.Ingredient;
import com.erkutdemirhan.recipedbeditor.domain.exception.IllegalInputException;
import com.erkutdemirhan.recipedbeditor.resources.Strings;

public class IngredientTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void Null_IngredientName_ShouldThrow_IllegalInputException() throws IllegalInputException {
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage(Strings.ERROR_MSG_INGREDIENT_NONAME.toString());
		Ingredient ingredient = new Ingredient(null, null);
	}
	
	@Test
	public void Empty_IngredientName_ShouldThrow_IllegalInputException() throws IllegalInputException {
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage(Strings.ERROR_MSG_INGREDIENT_NONAME.toString());
		Ingredient ingredient = new Ingredient("", null);
	}
	
	@Test
	public void NonAlphabetic_IngredientName_ShouldThrow_IllegalInputException() throws IllegalInputException {
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage(Strings.ERROR_MSG_INGREDIENT_ILLEGALNAME.toString());
		Ingredient ingredient = new Ingredient("1abcd", null);
	}
	
	@Test
	public void AllWhiteSpace_IngredientName_ShouldThrow_IllegalInputException() throws IllegalInputException {
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage(Strings.ERROR_MSG_INGREDIENT_ILLEGALNAME.toString());
		Ingredient ingredient = new Ingredient("   ", null);
	}
	
	@Test
	public void IngredientName_ShouldBe_Set_1() throws IllegalInputException {
		Ingredient ingredient = new Ingredient("   a   ", null);
		String expected = "A";
		assertThat(ingredient.getName(), equalTo(expected));
	}
	
	@Test
	public void IngredientName_ShouldBe_Set_2() throws IllegalInputException {
		Ingredient ingredient = new Ingredient("Üzüm yağı", null);
		String expected = "Üzüm yağı";
		assertThat(ingredient.getName(), equalTo(expected));
	}
	
	@Test
	public void IngredientName_ShouldBe_Set_3() throws IllegalInputException {
		Ingredient ingredient = new Ingredient("   domates      salçası ", null);
		String expected = "Domates salçası";
		assertThat(ingredient.getName(), equalTo(expected));
	}	
	
	@Test 
	public void Ingredients_With_Different_Names_ShouldBe_Different() throws IllegalInputException {
		Ingredient ingredient1 = new Ingredient(" Do ma tes Salçası", null);
		Ingredient ingredient2 = new Ingredient("   DOM a tes  salÇAsı", null);
		assertThat(ingredient1, not(equalTo(ingredient2)));
	}
	
	@Test
	public void Ingredients_With_Same_Names_ShouldBe_Equal() throws IllegalInputException {
		Ingredient ingredient1 = new Ingredient(" domates    SalÇası", null);
		Ingredient ingredient2 = new Ingredient(" Domates   SalçAsı ", null);
		assertThat(ingredient1, equalTo(ingredient2));
	}
	
	@Test
	public void NullAmount_ShouldBe_SetTo_EmptyString() throws IllegalInputException {
		Ingredient ingredient = new Ingredient("Ingr name", null);
		String expectedAmount = "";
		assertThat(ingredient.getAmount(), equalTo(expectedAmount));
	}
	
	@Test
	public void Amount_ShouldBe_AlphaNumeric() throws IllegalInputException {
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage(Strings.ERROR_MSG_INGREDIENT_ILLEGALAMOUNT.toString());
		Ingredient ingredient = new Ingredient("Ingr name", "\n 3 Çay k@şığı");
	}
	
	@Test 
	public void Amount_ShouldBe_Set_1() throws IllegalInputException {
		Ingredient ingredient = new Ingredient("Ingr name", "      ");
		String expectedAmount = "";
		assertThat(ingredient.getAmount(), equalTo(expectedAmount));
	}
	
	@Test 
	public void Amount_ShouldBe_Set_2() throws IllegalInputException {
		Ingredient ingredient = new Ingredient("Ingr name", " 3 Çay kaşığı  ");
		String expectedAmount = "3 Çay kaşığı";
		assertThat(ingredient.getAmount(), equalTo(expectedAmount));
	}
}
