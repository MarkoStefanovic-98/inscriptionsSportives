package views.Console;

import java.time.LocalDate;
import java.util.ArrayList;

import commandLineMenus.*;
import commandLineMenus.rendering.examples.util.InOut;
import controllers.*;
import entity.Candidat;
import entity.Competition;
import entity.Equipe;
import entity.Personne;

public class MenuCL {

    CustomMenuRenderer menuRenderer = new CustomMenuRenderer();

    /*
     * Menu de base
     */

    public Menu homeMenu() {
        Menu menu = new Menu("Menu | Application Sportives");
        menu.setRenderer( this.menuRenderer );
        menu.add( CompetitionMenu() );
        menu.add( EquipeMenu() );
        menu.add( PersonneMenu() );
        menu.add( QuitMenu() );
        return menu;
    }

    private Menu QuitMenu() {
        Menu menu = new Menu("Quitter", "q");
        menu.add(saveAndQuit());
//        menu.add(quitWhitoutSaving());
        menu.addBack("b");
        return menu;
    }

    private Option saveAndQuit() {
        return new Option("Sauvegarder et Quitter", "s", new Action() {
            @Override
            public void optionSelected() {
                try {
                    Action.QUIT.optionSelected();
                }
                catch (Exception e) {
                    System.out.println("Impossible de quitter le programme ! : " + e);
                }
            }
        });
    }

//    private Option quitWhitoutSaving() { return new Option("Quitter sans sauvegarder", "q", Action.QUIT); }





    /*
     * Menu pour les Competitions
     */

    private Menu CompetitionMenu() {
        Menu menu = new Menu("Menu | Compétitions", "Compétition", "c");
        menu.add( addCompetitionOption() );
        menu.add( displayAllCompetitionOption() );
        menu.add( modifyCompetitionOption() );
        menu.add( deleteCompetitionOption() );
        menu.addBack("b");
        return menu;
    }

    private Option addCompetitionOption() {
        return new Option(
                "Ajouter une compétition", "a",
                new Action() {
                    @Override
                    public void optionSelected()
                    {
                        String nomCompetition = InOut.getString("Nom de la compétition: ");
                        System.out.println("Fin de la compétition :");
                        int jour = InOut.getInt("  Jour (jj) : ");
                        int mois = InOut.getInt("  Mois (mm) : ");
                        int annee = InOut.getInt("  Année (yyyy) : ");
                        int choice = InOut.getInt("La compétition se déroule t'elle en équipe ? (0 pour Non, 1 pour oui) ");
                        boolean isTeam = false;
                        if(choice == 1){
                            isTeam = true;
                        } else if(choice != 0 && choice != 1 ){
                            System.out.println("Erreur de saisie, veuillez entrer 1 ou 0 ! ");
                        }
                        LocalDate dateCloture = LocalDate.of(annee, mois, jour);
                        Application.getApplication().createCompetition(nomCompetition, dateCloture, isTeam);
                    }
                }
        );
    }

    private Option displayAllCompetitionOption() { return new Option(
            "Afficher toutes les compétitions", "d",
            new Action() {
                @Override
                public void optionSelected() {
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + Application.getApplication().getCompetitions());
                }
            });
    }

    private Option modifyCompetitionOption() {
        List<Competition> list = new List<>(
                "Modifier un compétition", "m",
                new ListData<Competition>()
                {
                    @Override
                    public java.util.List<Competition> getList() {
                        return new ArrayList<>(Application.getApplication().getCompetitions());
                    }
                },
                new ListOption<Competition>()
                {
                    @Override
                    public Option getOption(Competition competition) {
                        return modifyCompetition(competition);
                    }
                }
        );

        list.addBack("b");
        return list;
    }

    private Option deleteCompetitionOption() {
        List<Competition> list = new List<>(
                "Supprimer une compétition", "s",
                new ListData<Competition>()
                {
                    @Override
                    public java.util.List<Competition> getList() {
                        return new ArrayList<>(Application.getApplication().getCompetitions());
                    }
                },
                new ListAction<Competition>()
                {
                    @Override
                    public void itemSelected(int i, Competition competition) {
                        competition.delete();
                    }

                }
        );

        list.addBack("b");
        return list;
    }

    private Option modifyCompetition(Competition competition) {
        Menu menu = new Menu(competition.getNom() + " (" + competition.getCandidats().size() + ")");
        menu.add(showCompetitionOption(competition));
        menu.add(changerNomCompetition(competition));
        menu.add(changerDateCompetition(competition));

        if(competition.estEnEquipe()){
            menu.add(addEquipeToCompetition(competition));
            menu.add(removeEquipeToCompetition(competition));
        }else{
            menu.add(addPersonToCompetition(competition));
            menu.add(removePersonToCompetition(competition));
        }

        menu.addBack("b");

        return menu;
    }

    private Option showCompetitionOption(Competition competition) { return new Option(
            "Afficher les participants de la compétition", "d",
            new Action() {
                @Override
                public void optionSelected() {
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + competition.toString() +  "\n\n" + competition.getCandidats());
                }
            });
    }

    private Option changerNomCompetition(Competition competition) {
        return new Option(
                "Changer le nom de la compétition", "n",
                new Action() {
                    @Override
                    public void optionSelected() {
                        competition.setNom(InOut.getString("Nouveau nom de la compétition : "));
                    }
                }
        );
    }

    private Option changerDateCompetition(Competition competition) {
        return new Option(
                "Changer la date de fin de la compétition", "f",
                new Action() {
                    @Override
                    public void optionSelected() {
                        System.out.println("Nouvelle fin de la compétition : ");
                        int jour = InOut.getInt("  Jour (jj) : ");
                        int mois = InOut.getInt("  Mois (mm) : ");
                        int annee = InOut.getInt("  Année (yyyy) : ");
                        LocalDate dateCloture = LocalDate.of(annee, mois, jour);
                        competition.setDateCloture(dateCloture);
                    }
                }
        );
    }

    private Option addPersonToCompetition(Competition competition) {
        List<Personne> list =  new List<>(
                "Ajouter une personne à la competition", "a",
                new ListData<Personne>()
                {
                    @Override
                    public java.util.List<Personne> getList() {

                        ArrayList<Personne> possiblePersonToAdd = new ArrayList<Personne>();
                        for (Personne personne : Application.getApplication().getPersonnes()){
                            if( !competition.getCandidats().contains(personne) ){
                                possiblePersonToAdd.add(personne);
                            }
                        }

                        return possiblePersonToAdd;
                    }
                },
                new ListAction<Personne>()
                {
                    @Override
                    public void itemSelected(int i, Personne personne) {
                        competition.add(personne);
                    }
                }
        );

        list.addBack("b");
        return list;
    }

    private Option addEquipeToCompetition(Competition competition) {
    		List<Equipe> list =  new List<>(
            "Ajouter une équipe à la competition", "a",
            new ListData<Equipe>()
            {
                @Override
                public java.util.List<Equipe> getList() {

                    ArrayList<Equipe> possibleEquipeToAdd = new ArrayList<Equipe>();
                    for (Equipe equipe : Application.getApplication().getEquipes()){
                        if( !competition.getCandidats().contains(equipe) ){
                            possibleEquipeToAdd.add(equipe);
                        }
                    }
                    return possibleEquipeToAdd;
                }
            },
            new ListAction<Equipe>()
            {
                @Override
                public void itemSelected(int i, Equipe equipe) {
                    competition.add(equipe);
                }
            }
        );

        list.addBack("b");
        return list;
    }

    private Option removePersonToCompetition(Competition competition) {
    		List<Candidat> list =  new List<>(
            "Supprimer une personne de la competition", "s",
            new ListData<Candidat>()
            {
                @Override
                public java.util.List<Candidat> getList() {
                    return new ArrayList<>(competition.getCandidats());
                }
            },
            new ListAction<Candidat>()
            {
                @Override
                public void itemSelected(int i, Candidat candidat) {
                    competition.remove(candidat);
                }
            }
        );

        list.addBack("b");
        return list;
    }

    private Option removeEquipeToCompetition(Competition competition) {
        List<Candidat> list =  new List<>(
            "Supprimer une équipe de la competition", "s",
            new ListData<Candidat>()
            {
                @Override
                public java.util.List<Candidat> getList() {
                    return new ArrayList<>(competition.getCandidats());
                }
            },
            new ListAction<Candidat>()
            {
                @Override
                public void itemSelected(int i, Candidat candidat) {
                    competition.remove(candidat);
                }
            }
        );

        list.addBack("b");
        return list;
    }





    /*
     * Menu pour les equipes
     */

    private Menu EquipeMenu() {
        Menu menu = new Menu("Menu | Équipes", "Équipes", "e");
        menu.add( addEquipeOption() );
        menu.add( displayAllEquipeOption() );
        menu.add( deleteEquipeOption() );
        menu.add( modifyEquipeOption() );
        menu.addBack("b");
        return menu;
    }

    private Option displayAllEquipeOption() { return new Option(
            "Afficher toutes les équipes", "d",
            new Action() {
                @Override
                public void optionSelected() {
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + Application.getApplication().getEquipes());
                }
            });
    }

    private Option addEquipeOption() { return new Option(
            "Ajouter une équipe", "a",
            new Action()
            {
                @Override
                public void optionSelected()
                {
                    String a = InOut.getString("Nom de l'équipe: ");
                    Application.getApplication().createEquipe(a);
                }
            }
    ); }

    private Option deleteEquipeOption() {
    		List<Equipe> list =  new List<>(
            "Supprimer une équipe", "s",
            new ListData<Equipe>()
            {
                @Override
                public java.util.List<Equipe> getList() {
                    return new ArrayList<>(Application.getApplication().getEquipes());
                }
            },
            new ListAction<Equipe>()
            {
                @Override
                public void itemSelected(int i, Equipe equipe) {
                    equipe.delete();
                }

            }
        );

        list.addBack("b");
        return list;
    }

    private Option modifyEquipeOption() {
    		List<Equipe> list =  new List<>(
            "Modifier une équipe", "m",
            new ListData<Equipe>(){

                @Override
                public java.util.List<Equipe> getList() {
                    return new ArrayList<>(Application.getApplication().getEquipes());
                }
            },
            new ListOption<Equipe>()
            {
                @Override
                public Option getOption(Equipe equipe) {
                    return modifyEquipe(equipe);
                }
            }
        );

        list.addBack("b");
        return list;
    }

    private Option modifyEquipe(Equipe equipe) {
        Menu menu = new Menu( equipe.getNom() + " (" + equipe.getMembres().size() + ")");
        menu.add(showEquipeOption(equipe));
        menu.add(changerNomEquipe(equipe));
        menu.add(addPersonToEquipeOption(equipe));
        menu.add(removePersonToEquipeOption(equipe));
        menu.addBack("b");

        return menu;
    }

    private Option showEquipeOption(Equipe equipe) { return new Option(
            "Afficher les membres de l'équipe", "d",
            new Action() {
                @Override
                public void optionSelected() {
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + equipe.toString() + "\n\n" + equipe.getMembres());
                }
            });
    }

    private Option changerNomEquipe(Equipe equipe) {
        return new Option(
                "Changer le nom de l'équipe", "n",
                new Action() {
                    @Override
                    public void optionSelected() {
                        equipe.setNom(InOut.getString("Nouveau nom de l'équipe : "));
                    }
                }
        );
    }

    private Option addPersonToEquipeOption(Equipe equipe) {
        List<Personne> list = new List<>(
            "Ajouter une personne à l'équipe", "a",
            new ListData<Personne>()
            {
                @Override
                public java.util.List<Personne> getList() {

                    ArrayList<Personne> possiblePersonToAdd = new ArrayList<Personne>();
                    for (Personne personne : Application.getApplication().getPersonnes()){
                        if( !equipe.getMembres().contains(personne) ){
                            possiblePersonToAdd.add(personne);
                        }
                    }

                    return possiblePersonToAdd;
                }
            },
            new ListAction<Personne>()
            {
                @Override
                public void itemSelected(int i, Personne personne) {
                    equipe.add(personne);
                }

            }
        );

        list.addBack("b");
        return list;
    }

    private Option removePersonToEquipeOption(Equipe equipe) {
        List<Personne> list =  new List<>(
            "Supprimer une personne de l'équipe", "s",
            new ListData<Personne>()
            {
                @Override
                public java.util.List<Personne> getList() {
                    return new ArrayList<>(equipe.getMembres());
                }
            },
            new ListAction<Personne>()
            {
                @Override
                public void itemSelected(int i, Personne personne) {
                    equipe.remove(personne);
                }

            }
        );

        list.addBack("b");
        return list;
    }










    /*
     * Menu pour les personnes
     */

    private Menu PersonneMenu() {
        Menu menu = new Menu("Menu | Menu des personnes", "Personnes", "p");
        menu.add( addPersonneOption() );
        menu.add( displayAllPersonnesOption() );
        menu.add( deletePersonneOption() );
        menu.add( modifyPersonneOption() );
        menu.addBack("b");
        return menu;
    }

    private Option displayAllPersonnesOption() { return new Option(
            "Afficher toutes les personnes", "d",
            new Action() {
                @Override
                public void optionSelected() {
                    System.out.println( "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + Application.getApplication().getPersonnes());
                }
            }
    );
    }

    private Option addPersonneOption() {
        return new Option("Ajouter une personne", "a",
                new Action() {
                    @Override
                    public void optionSelected()
                    {
                        String prenom = InOut.getString("Prénom de la personne: ");
                        String nom = InOut.getString("Nom de la personne: ");
                        String email = InOut.getString("Adresse e-mail de la personne: ");
                        Application.getApplication().createPersonne(nom, prenom, email);
                    }
                }
        );
    }

    private Option deletePersonneOption() {
        return new List<>(
                "Supprimer une personne", "s",
                new ListData<Personne>(){

                    @Override
                    public java.util.List<Personne> getList() {
                        return new ArrayList<>(Application.getApplication().getPersonnes());
                    }
                },
                new ListAction<Personne>()
                {
                    @Override
                    public void itemSelected(int i, Personne personne) {
                        personne.delete();
                    }

                }
        );
    }

    private Option modifyPersonneOption() {
        return new List<>(
            "Modifier une personne", "m",
            new ListData<Personne>(){

                @Override
                public java.util.List<Personne> getList() {
                    return new ArrayList<>(Application.getApplication().getPersonnes());
                }
            },
            new ListOption<Personne>()
            {
                @Override
                public Option getOption(Personne personne) {
                    return modifyPersonne(personne);
                }
            }
        );
    }

    private Option modifyPersonne(Personne personne) {
        Menu menu = new Menu(personne.getNom() + " " + personne.getPrenom());
        menu.add(changerNom(personne));
        menu.add(changerPrenom(personne));
        menu.add(changerMail(personne));
        menu.addBack("b");

        return menu;
    }

    private Option changerNom(Personne personne) {
        return new Option(
                "Changer le nom", "n",
                new Action() {
                    @Override
                    public void optionSelected() {
                        personne.setNom(InOut.getString("Nouveau nom : "));
                    }
                }
        );
    }

    private Option changerPrenom(Personne personne) {
        return new Option(
                "Changer le prénom", "p",
                new Action() {
                    @Override
                    public void optionSelected() {
                        personne.setPrenom(InOut.getString("Nouveau prénom : "));
                    }
                }
        );
    }

    private Option changerMail(Personne personne) {
        return new Option(
                "Changer l'adresse email", "e",
                new Action() {
                    @Override
                    public void optionSelected() {
                        personne.setMail(InOut.getString("Nouvelle adresse email : "));
                    }
                }
        );
    }

}