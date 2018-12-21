package client;

import java.time.LocalDate;
import java.util.ArrayList;

import commandLineMenus.*;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.*;

public class MenuCL {

    /*
     * Menu de base
     */

    public Menu homeMenu() {
        Menu menu = new Menu("Menu | Inscriptions Sportives");
        menu.add( CompetitionMenu() );
        menu.add( QuitMenu() );
        return menu;
    }

    private Menu QuitMenu() {
        Menu menu = new Menu("Quitter", "q");
        menu.add(saveAndQuit());
        menu.add(quitWhitoutSaving());
        menu.addBack("b");
        return menu;
    }

    private Option saveAndQuit() {
        return new Option("Sauvegarder et Quitter", "s", new Action() {
            @Override
            public void optionSelected() {
                try {
                    Inscriptions.getInscriptions().sauvegarder();
                    Action.QUIT.optionSelected();
                }
                catch (Exception e) {
                    System.out.println("Impossible de sauvegarder les données ! : " + e);
                }
            }
        });
    }

    private Option quitWhitoutSaving() { return new Option("Quitter sans sauvegarder", "q", Action.QUIT); }





    /*
     * Menu pour les Competitions
     */

    private Menu CompetitionMenu() {
        Menu menu = new Menu("Menu | Compétitions", "Compétition", "c");
        menu.add( addCompetitionOption() );
        menu.add( displayCompetitionOption() );
        menu.add( manageCompetitionMenu() );
        menu.addBack("b");
        return menu;
    }

    private Option displayCompetitionOption() { return new Option(
            "Afficher les compétitions", "2",
            new Action() {
                @Override
                public void optionSelected() {
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + Inscriptions.getInscriptions().getCompetitions());
                }
            });
    }

    private Option addCompetitionOption() {
        return new Option(
                "Ajouter une compétition", "1",
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
                        Inscriptions.getInscriptions().createCompetition(nomCompetition, dateCloture, isTeam);
                    }
                }
        );
    }

    private Option deleteCompetitionOption() {
        return new List<>(
                "Supprimer une compétition", "1",
                new ListData<Competition>()
                {
                    @Override
                    public java.util.List<Competition> getList() {
                        return new ArrayList<>(Inscriptions.getInscriptions().getCompetitions());
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
    }

    private Menu manageCompetitionMenu() {
        Menu menu = new Menu("Menu | Gestion des compétitions", "Gestion des compétitions", "3");
        menu.add( deleteCompetitionOption() );
        menu.add( modifyCompetitionOption() );
        menu.addBack("b");
        return menu;
    }

    private Option modifyCompetitionOption() {
        return new List<>(
                "Modifier une compétition", "2",
                new ListData<Competition>()
                {
                    @Override
                    public java.util.List<Competition> getList() {
                        return new ArrayList<>(Inscriptions.getInscriptions().getCompetitions());
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
    }

    private Option modifyCompetition(Competition competition) {
        Menu menu = new Menu("Menu | Modification competition : " + competition.getNom());
        menu.add(changerNomCompetition(competition));
        menu.add(changerDateCompetition(competition));

        if(competition.estEnEquipe()){
            menu.add(addEquipeToCompetition(competition));
        }else{
            menu.add(addPersonToCompetition(competition));
        }
        menu.addBack("b");

        return menu;
    }

    private Option changerNomCompetition(Competition competition) {
        return new Option(
                "Changer le nom de la compétition", "1",
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
                "Changer la date de fin de la compétition", "2",
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
        return new List<>(
                "Ajouter une personne à la competition", "3",
                new ListData<Personne>()
                {
                    @Override
                    public java.util.List<Personne> getList() {
                        return new ArrayList<>(Inscriptions.getInscriptions().getPersonnes());
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
    }

    private Option addEquipeToCompetition(Competition competition) {
        return new List<>(
                "Ajouter une équipe à la competition", "4",
                new ListData<Equipe>()
                {
                    @Override
                    public java.util.List<Equipe> getList() {
                        return new ArrayList<>(Inscriptions.getInscriptions().getEquipes());
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
    }
}





    