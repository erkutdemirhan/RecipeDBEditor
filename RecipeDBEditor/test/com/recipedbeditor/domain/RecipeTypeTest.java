package com.recipedbeditor.domain;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.recipedbeditor.domain.exception.IllegalInputException;
import com.recipedbeditor.resources.Strings;

public class RecipeTypeTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}	
	
	@Test
	public void Null_RecipeTypeName_ShouldThrow_IllegalInputException() throws IllegalInputException {
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage(Strings.ERROR_MSG_RECIPETYPE_NONAME.toString());
		RecipeType recipeType = new RecipeType(null);
	}
	
	@Test
	public void Empty_RecipeTypeName_ShouldThrow_IllegalInputException() throws IllegalInputException {
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage(Strings.ERROR_MSG_RECIPETYPE_NONAME.toString());
		RecipeType recipeType = new RecipeType("");
	}
	
	@Test 
	public void RecipeTypeName_ShouldContain_Only_LettersAndSpaces() throws IllegalInputException {
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage(Strings.ERROR_MSG_RECIPETYPE_ILLEGALNAME.toString());
		RecipeType recipeType = new RecipeType("  Ç0rbalar");
	}
	
	@Test
	public void RecipeTypeName_ShouldContain_AtLeast_OneLetter() throws IllegalInputException {
		thrown.expect(IllegalInputException.class);
		thrown.expectMessage(Strings.ERROR_MSG_RECIPETYPE_ILLEGALNAME.toString());
		RecipeType recipeType = new RecipeType("       ");
	}
	
	@Test
	public void RecipeTypeName_ShouldBe_Set_1() throws IllegalInputException {
		RecipeType recipeType = new RecipeType("   çorbAlaR ");
		String expected       = "Çorbalar";
		assertThat(recipeType.getName(), equalTo(expected));
	}
	
	@Test
	public void RecipeTypeName_ShouldBe_Set_2() throws IllegalInputException {
		RecipeType recipeType = new RecipeType(" aNa     Yemekler");
		String expected       = "Ana yemekler";
		assertThat(recipeType.getName(), equalTo(expected));
	}
	
	@Test
	public void RecipeTypes_With_Same_Name_ShouldBe_Equal() throws IllegalInputException {
		RecipeType type1 = new RecipeType("  çorbalAr");
		RecipeType type2 = new RecipeType("ÇOrbalar  ");
		assertThat(type1, equalTo(type2));
	}
	
	@Test
	public void RecipeTypes_With_Different_Names_ShouldNOTBe_Equal() throws IllegalInputException {
		RecipeType type1 = new RecipeType(" Ana yemekler");
		RecipeType type2 = new RecipeType("tatlılar");
		assertThat(type1, not(equalTo(type2)));
	}

}
