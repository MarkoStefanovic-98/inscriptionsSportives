package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import controllers.Application;
import entity.Personne;

public class TestPersonne {
	private Personne lol;
	private Personne lol2;

	@Before
	public void setUp() throws Exception {
		lol = Application.getApplication().createPersonne("Duchien", "Robert", "Duchien.Robert@tesfesse.com");
		lol = Application.getApplication().createPersonne("Duchat", "Patrik", "Duchat.Patrik@tesfesse.com");
	}

	@Test
	public void testToString() {
		assertEquals("\nDuchien -> inscrit à [] membre de []", lol.toString());
	}

	@Test
	public void testGetPrenom() {
		assertEquals("Robert", lol.getPrenom());
		assertEquals("Patrik", lol2.getPrenom());
	}

	@Test
	public void testSetPrenom() {
		lol.setPrenom("Malaria");
		lol2.setPrenom("NuitBlanche");
		assertEquals("Malaria", lol.getPrenom());
		assertEquals("NuitBlanche", lol2.getPrenom());
	}

	@Test
	public void testGetMail() {
		assertEquals("Duchien.Robert@tesfesse.com", lol.getMail());
		assertEquals("Duchat.Patrik@tesfesse.com", lol2.getMail());
	}

	@Test
	public void testSetMail() {
		lol.setMail("Malaria.Robert@tesfesse.com");
		lol2.setMail("NuitBlanche.Patrik@tesfesse.com");
		assertEquals("Malaria.Robert@tesfesse.com", lol.getMail());
		assertEquals("NuitBlanche.Patrik@tesfesse.com", lol2.getMail());
	}

	@Test
	public void testGetNom() {
		assertEquals("Duchien", lol.getNom());
		assertEquals("Duchat", lol2.getNom());
	}

	@Test
	public void testSetNom() {
		lol.setNom("Decaca");
		assertEquals("Decaca", lol.getNom());
		lol2.setNom("Duchamaux");
		assertEquals("Duchamaux", lol2.getNom());
	}

	@Test
	public void testCompareTo() {
		assertEquals(lol.getNom().compareTo(lol2.getNom()), lol.compareTo(lol2));
	}

}
