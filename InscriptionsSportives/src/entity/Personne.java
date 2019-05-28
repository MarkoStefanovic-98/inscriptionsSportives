package entity;

import controllers.Application;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import static controllers.Application.getApplication;

/**
 * Représente une personne physique pouvant s'inscrire à une compétition.
 */

@Entity
public class Personne extends Candidat
{
	public interface PersonneCreator{ Personne create(Application application, String nom, String prenom, String mail); }

	static {
		getApplication();
		Application.setPersonneCreator(Personne::new);
	}

	@Transient
	private static final long serialVersionUID = 4434646724271327254L;

	@Column(name = "prenom")
	private String prenom;

	@Column(name = "mail")
	private String mail;

	@ManyToMany(mappedBy="membres")
	@OrderBy("id ASC")
	private Set<Equipe> equipes;





	/**
	 * Constructeur pour Hibernante
	 */
	Personne(){ }

	Personne(Application application, String nom, String prenom, String mail)
	{
		super(application, nom);
		this.prenom = prenom;
		this.mail = mail;
		this.equipes = new TreeSet<>();
	}


	/**
	 * Retourne le prénom de la personne.
	 * @return
	 */
	public String getPrenom()
	{
		return prenom;
	}

	/**
	 * Modifie le prénom de la personne.
	 * @param prenom
	 */
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
		Application.saveEntity(this);
	}

	/**
	 * Retourne l'adresse électronique de la personne.
	 * @return
	 */
	public String getMail() { return mail; }

	/**
	 * Modifie l'adresse électronique de la personne.
	 * @param mail
	 */
	public void setMail(String mail)
	{
		this.mail = mail;
		Application.saveEntity(this);
	}

	/**
	 * Retoure les équipes dont cette personne fait partie.
	 * @return
	 */
	public Set<Equipe> getEquipes()
	{
		return Collections.unmodifiableSet(equipes);
	}

	/**
	 * Ajoute une équipe à une personne
	 * @param equipe
	 */
	void add(Equipe equipe)  {
		equipes.add(equipe);
		Application.saveEntity(this);
	}

	/**
	 * Supprimer une équipe à une personne
	 * @param equipe
	 */
	void remove(Equipe equipe)  {
		equipes.remove(equipe);
		Application.saveEntity(this);
	}

	/**
	 * Supprime une personne de toutes les équipes.
	 */
	@Override
	public void delete() {
		for (Equipe e : equipes){
			e.remove(this);
		}
		super.delete();
	}
	
	@Override
	public String toString() {
		return "Personne : " + this.getNom() + " " + this.getPrenom() + " <" + this.getMail() + "> ";
	}
}
