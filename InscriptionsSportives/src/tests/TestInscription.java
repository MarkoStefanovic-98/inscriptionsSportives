package tests;

import static org.junit.Assert.assertTrue;
import java.time.LocalDate;

import controllers.Application;
import entity.Competition;
import entity.Personne;
import org.junit.Test;

public class TestInscription {

	@Test
	public void testGetPersonnes() {
		Application inscription = Application.getApplication();
		Personne Personne1 = inscription.createPersonne("d", "e", "f");
		assertTrue(inscription.getPersonnes().contains(Personne1));
	}

	@Test
	public void testGetCompetitions() {
		Application inscription = Application.getApplication();
		Competition compet = inscription.createCompetition("a", LocalDate.now().plusDays(10), true);
		Competition compet1 = inscription.createCompetition("b", LocalDate.now().plusDays(10), true);

		assertTrue(inscription.getCompetitions().contains(compet));
		assertTrue(inscription.getCompetitions().contains(compet1));
	}


}
