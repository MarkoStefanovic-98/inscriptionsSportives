package entity;

import controllers.Application;
import javax.persistence.*;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import static controllers.Application.*;

/**
 * Représente une Equipe. C'est-à-dire un ensemble de personnes pouvant 
 * s'inscrire à une compétition.
 * 
 */

@Entity
public class Equipe extends Candidat
{
	public interface EquipeCreator{ Equipe create(Application application, String nom); }

	static {
		getApplication();
		Application.setEquipeCreator(Equipe::new);
	}

	@Transient
	private static final long serialVersionUID = 4147819927233466035L;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "Equipe_Personne",
			joinColumns = { @JoinColumn(name = "equipe_id") },
			inverseJoinColumns = { @JoinColumn(name = "personne_id") }
	)
	@OrderBy("id ASC")
	private SortedSet<Personne> membres = new TreeSet<Personne>();



	/**
	 * Constructeur pour Hibernante
	 */
	Equipe(){ }

	Equipe(Application application, String nom)
	{
		super(application, nom);
	}

	/**
	 * Retourne l'ensemble des personnes formant l'équipe.
	 */
	public SortedSet<Personne> getMembres()
	{
		return Collections.unmodifiableSortedSet(membres);
	}
	
	/**
	 * Ajoute une personne dans l'équipe.
	 * @param membre
	 * @return
	 */
	public boolean add(Personne membre)
	{
		membre.add(this);
		Boolean r = membres.add(membre);
		Application.saveEntity(this);
		return r;
	}

	/**
	 * Supprime une personne de l'équipe. 
	 * @param membre
	 * @return
	 */
	public boolean remove(Personne membre)
	{
		Boolean r =  membres.remove(membre);
		Application.saveEntity(this);
		return r;
	}

	public void clear(){
		this.membres = new TreeSet<Personne>();
	}

	@Override
	public void delete()
	{
		for (Personne p : membres){
			p.remove(this);
		}
		super.delete();
	}
	
	@Override
	public String toString()
	{
		return "Equipe : " + this.getNom() + " (" + this.getMembres().size() + ")";
	}
}
