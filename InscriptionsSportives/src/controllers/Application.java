package controllers;

import entity.Candidat;
import entity.Competition;
import entity.Equipe;
import entity.Personne;
import models.Hibernate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;
import java.time.LocalDate;

/**
 * Point d'entrée dans l'application, un seul objet de type Inscription
 * permet de gérer les compétitions, candidats (de type equipe ou personne)
 * ainsi que d'inscrire des candidats à des compétition.
 */

public class Application implements Serializable
{
	private static final long serialVersionUID = -3095339436048473524L;
	private static final String FILE_NAME = "inscriptions.srz";
	private static Application application;

	private static Equipe.EquipeCreator equipeCreator;
	private static Personne.PersonneCreator personneCreator;
	private static Competition.CompetitionCreator competitionCreator;
	
	public static void setEquipeCreator(Equipe.EquipeCreator equipeCreator) { Application.equipeCreator = equipeCreator; }
	public static void setPersonneCreator(Personne.PersonneCreator personneCreator) { Application.personneCreator = personneCreator; }
	public static void setCompetitionCreator(Competition.CompetitionCreator competitionCreator) { Application.competitionCreator = competitionCreator; }

	private SortedSet<Competition> competitions = new TreeSet<>();
	private SortedSet<Candidat> candidats = new TreeSet<>();
	
	/**
	 * Retourne les compétitions.
	 * @return
	 */
	public SortedSet<Competition> getCompetitions() {
		return Collections.unmodifiableSortedSet(competitions);
	}
	
	/**
	 * Retourne tous les candidats (personnes et équipes confondues).
	 * @return
	 */
	public SortedSet<Candidat> getCandidats() {
		return Collections.unmodifiableSortedSet(candidats);
	}

	/**
	 * Retourne toutes les personnes.
	 * @return
	 */
	public SortedSet<Personne> getPersonnes()
	{
		SortedSet<Personne> personnes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Personne)
				personnes.add((Personne)c);
		return Collections.unmodifiableSortedSet(personnes);
	}

	/**
	 * Retourne toutes les équipes.
	 * @return
	 */
	public SortedSet<Equipe> getEquipes()
	{
		SortedSet<Equipe> equipes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Equipe){
				equipes.add((Equipe)c);
			}
		return Collections.unmodifiableSortedSet(equipes);
	}

	/**
	 * Créée une compétition. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Competition}.
	 * @param nom
	 * @param dateCloture
	 * @param enEquipe
	 * @return
	 */
	public Competition createCompetition(String nom, LocalDate dateCloture, boolean enEquipe)
	{
		Competition competition = competitionCreator.create(this, nom, dateCloture, enEquipe);
		competitions.add(competition);

		Application.saveEntity(competition);
		return competition;
	}

	/**
	 * Créée une Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Personne}.
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	public Personne createPersonne(String nom, String prenom, String mail)
	{
		Personne personne = personneCreator.create(this, nom, prenom, mail);
		candidats.add(personne);

		Application.saveEntity(personne);
		return personne;
	}
	
	/**
	 * Créée une Candidat de type équipe. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Equipe}.
	 * @param nom
	 * @return
	 */
	public Equipe createEquipe(String nom)
	{
		Equipe equipe = equipeCreator.create(this, nom);
		candidats.add(equipe);

		Application.saveEntity(equipe);
		return equipe;
	}

	/**
	 * Supprimer une competition
	 * @param competition
	 */
	public void remove(Competition competition)
	{
		competitions.remove(competition);
		Application.removeEntity(competition);
	}

	/**
	 * Supprimer un candidat (Equipe ou Personne)
	 * @param candidat
	 */
	public void remove(Candidat candidat)
	{
		candidats.remove(candidat);
		Application.removeEntity(candidat);
	}
	
	/**
	 * Retourne l'unique instance de cette classe.
	 * Crée cet objet s'il n'existe déjà.
	 * @return l'unique objet de type {@link Application}.
	 */
	public static Application getApplication()
	{
		if (application == null)
		{
			application = readObject();
			if (application == null)
				application = new Application();
		}
		return application;
	}

	/**
	 * Retourne un object application vide. Ne modifie pas les compétitions
	 * et candidats déjà existants.
	 */
	public Application reinitialiser()
	{
		application = new Application();
		return getApplication();
	}

	/**
	 * Efface toutes les modifications sur Application depuis la dernière sauvegarde.
	 * Ne modifie pas les compétitions et candidats déjà existants.
	 */
	public Application recharger()
	{
		application = null;
		return getApplication();
	}
	
	private static Application readObject()
	{
		ObjectInputStream ois = null;
		try {
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			return (Application)(ois.readObject());
		}
		catch (IOException | ClassNotFoundException e) {
			return null;
		}
		finally {
				try {
					if (ois != null)
						ois.close();
				} catch (IOException e){}
		}	
	}

	/**
	 * Démarre la connexion à la BDD
	 */
	public static void start() {
		Hibernate.open();
	}

	/**
	 * Sauvegarde un entité / La modifie si besoin
	 * @param o
	 */
	public static void saveEntity(Object o){
		Hibernate.save(o);
	}

	/**
	 * Supprime un entité
	 * @param o
	 */
	public static void removeEntity(Object o){
		Hibernate.delete(o);
	}


	@Override
	public String toString()
	{
		return "Candidats : " + getCandidats().toString() + "\nCompetitions  " + getCompetitions().toString();
	}

	/**
	 * Importe les entités de la BDD dans le programme
	 */
	static void importDataFromDatabase() {

		List<Equipe> equipes = Hibernate.getData("Equipe");
		List<Personne> personnes = Hibernate.getData("Personne");
		List<Competition> competitions = Hibernate.getData("Competition");

		List<Candidat> candidats = new ArrayList<Candidat>();

		candidats.addAll(personnes);
		candidats.addAll(equipes);

		Application.getApplication().setCandidats( candidats );
		Application.getApplication().setCompetitions( competitions );
	}

	private void setCandidats(List<Candidat> candidats){
		this.candidats = new TreeSet<Candidat>(candidats);
	}

	private void setCompetitions(List<Competition> competitions){
		this.competitions = (SortedSet<Competition>) new TreeSet<Competition>(competitions);;
	}
	
	public static String convertDate(LocalDate date) {
		
		String monthString = "";
		switch ( date.getMonth().getValue() ) {
			case 1:  monthString = "Janvier";
			        break;
			case 2:  monthString = "Février";
			        break;
			case 3:  monthString = "Mars";
			        break;
			case 4:  monthString = "Avril";
			        break;
			case 5:  monthString = "Mai";
			        break;
			case 6:  monthString = "Juin";
			        break;
			case 7:  monthString = "Juillet";
			        break;
			case 8:  monthString = "Aout";
			        break;
			case 9:  monthString = "Septembre";
			        break;
			case 10: monthString = "Octobre";
			        break;
			case 11: monthString = "Novembre";
			        break;
			case 12: monthString = "Decembre";
			        break;
			default: monthString = "Moi invalide";
			        break;
		}
		
		return date.getDayOfMonth() + " " + monthString + " " + date.getYear();
	}

}
