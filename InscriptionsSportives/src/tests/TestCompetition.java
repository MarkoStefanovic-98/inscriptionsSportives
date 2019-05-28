package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import controllers.Application;
import org.junit.Test;

import entity.Competition;
import entity.Equipe;
import entity.Personne;

public class TestCompetition {

	Application i = Application.getApplication();
	LocalDate dateclôture1 = LocalDate.of(2019, Month.DECEMBER, 31);
	LocalDate dateclôture2 = LocalDate.of(2019, Month.JANUARY, 31);
	LocalDate newdateclôture = LocalDate.of(2019, Month.JANUARY, 31);
	Competition comp_personnes = i.createCompetition("nomCompetition", dateclôture1 , false);
	Competition comp_equipe = i.createCompetition("nomCompetition", dateclôture2 , true);
	Personne p = i.createPersonne("Duchien","Robert","Duchien.Robert@tesfesse.com");
	Equipe e = i.createEquipe("nomEquipe");

	@Test
	public void testGetNom()
	{
		assertEquals("nomCompetition", comp_personnes.getNom());
	}

	@Test
	public void testInscriptionsOuvertes()
	{
		assertTrue(comp_personnes.inscriptionsOuvertes());
		assertFalse(comp_equipe.inscriptionsOuvertes());
	}


	@Test(expected = RuntimeException.class)
	public void testSetDateClôture() throws Exception
	{
		comp_personnes.setDateCloture(newdateclôture);
	}

	@Test
	public void testGetDateCloture()
	{
		assertEquals(LocalDate.of(2019, Month.DECEMBER, 31), comp_personnes.getDateCloture());
		assertEquals(LocalDate.of(2019, Month.JANUARY, 31), comp_equipe.getDateCloture());
	}

	@Test
	public void testEstEnEquipe()
	{
		assertTrue(comp_equipe.estEnEquipe());
		assertFalse(comp_personnes.estEnEquipe());
	}

	@Test
	public void testAddPersonne()
	{
		assertTrue(comp_personnes.add(p));
		assertTrue(comp_personnes.getCandidats().contains(p));
	}

	@Test
	public void testAddEquipe()
	{
		assertTrue(comp_equipe.add(e));
		assertTrue(comp_equipe.getCandidats().contains(e));
	}

	@Test
	public void testRemovePersonne()
	{
		assertTrue(comp_personnes.add(p));
		assertTrue(comp_personnes.remove(p));
		assertFalse(comp_personnes.getCandidats().contains(p));
	}

	@Test
	public void testRemoveEquipe()
	{
		assertTrue(comp_equipe.add(e));
		assertTrue(comp_equipe.remove(e));
		assertFalse(comp_equipe.getCandidats().contains(e));
	}
}