package tests;

import static org.junit.Assert.*;

import controllers.Application;
import org.junit.Test;
import entity.Equipe;
import entity.Personne;

public class TestEquipe
{
	Application i = Application.getApplication();
	Equipe e = i.createEquipe("nomEquipe");
	Personne p = i.createPersonne("Duchien","Robert","Duchien.Robert@tesfesse.com");

	@Test
	public void testAddPersonne()
	{
		assertTrue(e.add(p));
		assertTrue(e.getMembres().contains(p));
	}

	@Test
	public void testRemovePersonne()
	{
		assertTrue(e.add(p));
		assertTrue(e.remove(p));
		assertFalse(e.getMembres().contains(p));
	}
}