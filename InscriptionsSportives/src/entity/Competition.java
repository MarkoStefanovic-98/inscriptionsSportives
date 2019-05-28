package entity;

import controllers.Application;

import javax.persistence.*;

import static controllers.Application.getApplication;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Représente une compétition, c'est-à-dire un ensemble de candidats 
 * inscrits à un événement, les application sont closes à la date dateCloture.
 *
 */

@Entity
public class Competition implements Comparable<Competition>, Serializable
{
	
	public interface CompetitionCreator{ Competition create(Application application, String nom, LocalDate dateCloture, boolean enEquipe); }

	static {
		getApplication();
		Application.setCompetitionCreator(Competition::new);
	}
	
	@Transient
	private static final long serialVersionUID = -2882150118573759729L;

	@Transient
	private Application application;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "nom")
	private String nom;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "Competition_Candidat",
			joinColumns = { @JoinColumn(name = "competition_id") },
			inverseJoinColumns = { @JoinColumn(name = "candidat_id") }
	)
	private Set<Candidat> candidats;

	@Column(name = "dateCloture")
	private LocalDate dateCloture;

	@Column(name = "enEquipe")
	private boolean enEquipe = false;






	/**
	 * Constructeur pour Hibernante
	 */
	Competition(){
		this.application = Application.getApplication();
	}

	Competition(Application application, String nom, LocalDate dateCloture, boolean enEquipe)
	{
		this.enEquipe = enEquipe;
		this.application = application;
		this.nom = nom;
		this.dateCloture = dateCloture;
		this.candidats = new TreeSet<>();
	}

	/**
	 * Retourne le nom de la compétition.
	 * @return
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Modifie le nom de la compétition.
	 */
	public void setNom(String nom)
	{
		this.nom = nom ;
		Application.saveEntity(this);
	}
	
	/**
	 * Retourne vrai si les application sont encore ouvertes,
	 * faux si les application sont closes.
	 * @return
	 */
	public boolean inscriptionsOuvertes()
	{
		return this.dateCloture.isAfter( LocalDate.now() );
	}
	
	/**
	 * Retourne la date de cloture des application.
	 * @return
	 */
	public LocalDate getDateCloture()
	{
		return dateCloture;
	}
	
	/**
	 * Est vrai si et seulement si les application sont réservées aux équipes.
	 * @return
	 */
	public boolean estEnEquipe()
	{
		return enEquipe;
	}
	
	/**
	 * Modifie le type de la compétition.
	 */
	public void setEstEnEquipe(Boolean estEnEquipe)
	{
		enEquipe = estEnEquipe;
		Application.saveEntity(this);
	}
	
	/**
	 * Modifie la date de cloture des application. Il est possible de la reculer
	 * mais pas de l'avancer.
	 * @param dateCloture
	 */
	public void setDateCloture(LocalDate dateCloture){

//		if(dateCloture.isAfter(this.dateCloture)){
			this.dateCloture = dateCloture;
			Application.saveEntity(this);
//		}else{
// 			throw new RuntimeException("Date de clôture invalide !");
//		}
	}
	
	/**
	 * Retourne l'ensemble des candidats inscrits.
	 * @return
	 */
	public Set<Candidat> getCandidats()
	{
		return Collections.unmodifiableSet(candidats);
	}
	
	/**
	 * Inscrit un candidat de type Personne à la compétition. Provoque une
	 * exception si la compétition est réservée aux équipes ou que les 
	 * application sont closes.
	 * @param personne
	 * @return
	 */
	public boolean add(Personne personne)
	{
//		if ( this.enEquipe ){
//			throw new RuntimeException();
//		}

		if( this.inscriptionsOuvertes() ) {
			personne.add(this);
			Boolean r = candidats.add(personne);

			Application.saveEntity(this);
			return r;
		} else {
			throw new RuntimeException("Les inscriptions sont closes !");
		}
	}

	/**
	 * Inscrit un candidat de type Equipe à la compétition. Provoque une
	 * exception si la compétition est réservée aux personnes ou que
	 * les application sont closes.
	 * @param equipe
	 * @return
	 */
	public boolean add(Equipe equipe)
	{
//		if ( !this.enEquipe ){
//			throw new RuntimeException();
//		}

		if( this.inscriptionsOuvertes() ) {
			equipe.add(this);
			Boolean r = candidats.add(equipe);

			Application.saveEntity(this);
			return r;
		}
		return false;
	}

	/**
	 * Désinscrit un candidat.
	 * @param candidat
	 * @return
	 */
	public boolean remove(Candidat candidat)
	{
		Boolean r = candidats.remove(candidat);
		Application.saveEntity(this);
		return r;
	}

	public void clear(){
		this.candidats = new TreeSet<Candidat>();
	}
	
	/**
	 * Supprime la compétition de l'application.
	 */
	public void delete()
	{
		for (Candidat candidat : candidats){
			remove(candidat);
		}
		System.out.println(application.hashCode());
		application.remove(this);
	}
	
	@Override
	public int compareTo(Competition o)
	{
		return getNom().compareTo(o.getNom());
	}
	
	@Override
	public String toString()
	{
		return "\n" + "Compétition : " + getNom();
	}
}
