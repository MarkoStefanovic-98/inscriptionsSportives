package views.IHM.FenetreList;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import controllers.Application;
import entity.Competition;
import entity.Equipe;
import entity.Personne;
import views.IHM.FenetreMain;

public abstract class FenetreList extends FenetreMain{

	private static final long serialVersionUID = -8762190807781391397L;

	FenetreList(Application application, FenetreMain fenetre) {
		super(application, fenetre);
	}
	
	public void confirmDeleteTrueTriggered() {};

    List<Competition> getCompetitions() { return new ArrayList<>( application.getCompetitions() ); }
    List<Equipe> getEquipes() { return new ArrayList<>( application.getEquipes() ); }
    List<Personne> getPersonnes() {  return new ArrayList<>( application.getPersonnes() ); }

    public JTable getEquipeTable(){ return new JTable( this.equipeTableModel( getEquipes() ) ); }
    public JTable getPersonneTable(){ return new JTable( this.personneTableModel( getPersonnes() ) ); }
    
    List<Personne> personnesList = getPersonnes();
    List<Equipe> equipesList = getEquipes();
    List<Competition> competitionsList = getCompetitions();
    
    public void updateCompetitions() { 
    	competitionsList = getCompetitions();
    	table.setModel( competitionTableModel( competitionsList ) ); 
    }
    
    public void updateEquipes() { 
    	equipesList = getEquipes();
    	table.setModel( equipeTableModel( equipesList ) ); 
    }
    
    public void updatePersonnes() { 
    	personnesList = getPersonnes();
    	table.setModel( personneTableModel( personnesList ) ); 
    }
	
	protected void getListener() {
		super.getListener();
		
		headerLabelApplication.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				headerLabelApplication.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				headerLabelApplication.setForeground(Color.WHITE);
			}
		});
	}
	
	public TableModel competitionTableModel(List<Competition> competitions)  {
        return new TableModel()  {

            @Override
            public int getRowCount() { return competitions.size(); }

            @Override
            public int getColumnCount() { return 2; }

            @Override
            public String getColumnName(int columnIndex) {
                switch (columnIndex) {
                    case 0 : return "Nom";
                    case 1 : return "Date de clôture";
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) { return String.class; }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0 : return competitions.get(rowIndex).getNom();
                    case 1 : return Application.convertDate( competitions.get(rowIndex).getDateCloture() );
                }
                return null;
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) { }

            @Override
            public void addTableModelListener(TableModelListener l) {  }

            @Override
            public void removeTableModelListener(TableModelListener l) { }
        };
    }

    public TableModel equipeTableModel(List<Equipe> equipes) {
        return new TableModel() {

            @Override
            public int getRowCount() { return equipes.size(); }

            @Override
            public int getColumnCount() { return 2; }

            @Override
            public String getColumnName(int columnIndex) {
                switch (columnIndex) {
                    case 0 : return "Nom";
                    case 1 : return "Membres";
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) { return String.class; }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0 : return equipes.get(rowIndex).getNom();
                    case 1 : return equipes.get(rowIndex).getMembres().size();
                }
                return null;

            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) { }

            @Override
            public void addTableModelListener(TableModelListener l) { }

            @Override
            public void removeTableModelListener(TableModelListener l) { }
        };
    }

    public TableModel personneTableModel(List<Personne> personnes) {
        return new TableModel() {
            @Override
            public int getRowCount() { return personnes.size(); }

            @Override
            public int getColumnCount() { return 2; }

            @Override
            public String getColumnName(int columnIndex) {
                switch (columnIndex) {
                    case 0 : return "Nom";
                    case 1 : return "Prénom";
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) { return String.class; }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0 : return personnes.get(rowIndex).getNom();
                    case 1 : return personnes.get(rowIndex).getPrenom();
                }
                return null;
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) { }

            @Override
            public void addTableModelListener(TableModelListener l) { }

            @Override
            public void removeTableModelListener(TableModelListener l) { }
        };
    }
    
    public Component getTableComponent(Component containerTable){
        JScrollPane tablePane = new JScrollPane( containerTable );
        return tablePane;
    }

}
