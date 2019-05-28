package entity;

import controllers.Application;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Candidat à un événement sportif, soit une personne physique, soit une équipe.
 * b
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Candidat implements Comparable<Candidat>, Serializable
{
	@Transient
	private static final long serialVersionUID = -6035399822298694746L;

	@Transient
	protected Application application;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "nom")
	private String nom;

	@ManyToMany(mappedBy="candidats")
	private Set<Competition> competitions;


	/**
	 * Constructeur pour Hibernante
	 */
	Candidat(){
		this.application = Application.getApplication();
	}

	Candidat(Application application, String nom)
	{
		this.application = application;
		this.nom = nom;
		this.competitions = new TreeSet<>();
	}
	
	/**
	 * Retourne le nom'idenfiant du candidat
	 */
	public int getId()
	{
		return id;
	}
		


	/**
	 * Retourne le nom du candidat.
	 * @return
	 */
	public String getNom()
	{
		return nom;
	}

	/**
	 * Modifie le nom du candidat.
	 * @param nom
	 */
	public void setNom(String nom)
	{
		this.nom = nom;
		Application.saveEntity(this);
	}

	/**
	 * Retourne toutes les compétitions auxquelles ce candidat est inscrit.s
	 * @return
	 */
	public Set<Competition> getCompetitions()
	{
		return Collections.unmodifiableSet(competitions);
	}

	/**
	 * Ajoute une compétition à un candidat
	 * @param competition
	 * @return
	 */
	boolean add(Competition competition) {
		Boolean r = competitions.add(competition);

		Application.saveEntity(this);
		return r;
	}

	/**
	 * Supprimer une compétition à un candidat
	 * @param competition
	 * @return
	 */
	boolean remove(Competition competition) {
		Boolean r = competitions.remove(competition);

		Application.saveEntity(this);
		return r;
	}

	/**
	 * Supprime un candidat de l'application.
	 */
	public void delete()
	{
		for (Competition c : competitions){
				c.remove(this);
		}
		application.remove(this);

	}
	
	@Override
	public int compareTo(Candidat o)
	{
		return o.getId() - this.getId();
	}
}
