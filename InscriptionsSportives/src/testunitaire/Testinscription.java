package testunitaire;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import inscriptions.Inscriptions;

public class Testinscription {
	private	Inscriptions macaron;
	@Before
	public void setUp() throws Exception {
		macaron = Inscriptions.getInscriptions();
	}

	@Test
	public void testInscriptions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCompetitions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCandidats() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPersonnes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEquipes() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateCompetition() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreatePersonne() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateEquipe() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCompetition() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCandidat() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInscriptions() {
		assertTrue(null!= macaron);
	}

	@Test
	public void testReinitialiser() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecharger() {
		fail("Not yet implemented");
	}

	@Test
	public void testSauvegarder() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}

