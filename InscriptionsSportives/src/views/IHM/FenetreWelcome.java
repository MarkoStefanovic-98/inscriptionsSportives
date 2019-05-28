package views.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.Application;
import views.IHM.FenetreList.FenetreListCompetition;
import views.IHM.FenetreList.FenetreListEquipe;
import views.IHM.FenetreList.FenetreListPersonne;

public class FenetreWelcome extends FenetreMain {
	
	private static final long serialVersionUID = 4180040234850857229L;
	
	JPanel tileMember = new JPanel();
	JPanel tileTeam = new JPanel();
	JPanel tileCompetitions = new JPanel();
	
	JLabel footerLabelCompetitions = new JLabel("XX Compétitions");
	JLabel footerLabelMembers = new JLabel("XX Personnes");
	JLabel footerLabelTeams = new JLabel("XX Équipes");
	JLabel footerLabelUsername = new JLabel("Baptiste Vasseur");
	JLabel footerLabelApplication = new JLabel("Application M2L - JAVA");

	public FenetreWelcome(Application application, FenetreMain fenetreParent) {
		super(application, fenetreParent);
		init();
	}

	public FenetreWelcome(Application application) {
		super(application);
		init();
	}

	private void init() {
		initializeWindow();

		getHeader();
		getMainPanelContent();
		getFooter();
		
		getListener();
		
		footerLabelCompetitions.setText(application.getCompetitions().size() + " Compétitions");
		footerLabelMembers.setText(application.getPersonnes().size() + " Membres");
		footerLabelTeams.setText(application.getEquipes().size() + " Équipes");
	}

	public void getHeader() {
		
		super.getHeader();
		
		headerLabelApplication.setFont(new Font("Roboto", Font.PLAIN, 25));
		headerLabelApplication.setForeground(new Color(255, 255, 255, 90));
		
		headerLabelHello.setFont(new Font("Roboto", Font.PLAIN, 36));
		headerLabelHello.setForeground(Color.WHITE);

		headerPanel.add(headerTopPanel, BorderLayout.NORTH);
		headerBottomPanel.setBorder(new EmptyBorder(0, 10, 50, 0));
	}

	public void getMainPanelContent() {
		JPanel contentPanel = new JPanel();
		contentPanel.setAlignmentY(1.0f);
		contentPanel.setBorder(new EmptyBorder(30, 0, 30, 0));
		contentPanel.setFocusable(false);
		contentPanel.setBackground(new Color(41, 128, 185));	
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		Component verticalGlue = Box.createVerticalGlue();
		contentPanel.add(verticalGlue);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		contentPanel.add(panel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);

		
		panel.add(tileMember);
		tileMember.setPreferredSize(new Dimension(251, 151));
		tileMember.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tileMember.setBorder(new LineBorder(new Color(255, 255, 255)));
		tileMember.setBackground(new Color(0, 151, 230));

		JLabel tileMemberIcon = new JLabel("");
		tileMemberIcon.setBorder(new EmptyBorder(35, 0, 0, 0));
		tileMemberIcon.setHorizontalAlignment(SwingConstants.CENTER);
		tileMemberIcon.setIcon(new ImageIcon(FenetreWelcome.class.getResource("/images/people.png")));
		tileMember.setLayout(new BorderLayout(0, 0));
		tileMember.add(tileMemberIcon, BorderLayout.CENTER);

		JLabel tileMemberLbl = new JLabel("Personnes");
		tileMemberLbl.setBorder(new EmptyBorder(0, 0, 40, 0));
		tileMemberLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tileMemberLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		tileMemberLbl.setForeground(Color.WHITE);
		tileMember.add(tileMemberLbl, BorderLayout.SOUTH);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel.add(horizontalGlue_2);

		panel.add(tileTeam);
		tileTeam.setPreferredSize(new Dimension(251, 151));
		tileTeam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tileTeam.setBorder(new LineBorder(new Color(255, 255, 255)));
		tileTeam.setBackground(new Color(0, 151, 230));
		tileTeam.setLayout(new BorderLayout(0, 0));

		JLabel tileTeamIcon = new JLabel("");
		tileTeamIcon.setBorder(new EmptyBorder(35, 0, 0, 0));
		tileTeamIcon.setIcon(new ImageIcon(FenetreWelcome.class.getResource("/images/team.png")));
		tileTeamIcon.setHorizontalAlignment(SwingConstants.CENTER);
		tileTeam.add(tileTeamIcon);

		JLabel tileTeamLbl = new JLabel("Équipes");
		tileTeamLbl.setBorder(new EmptyBorder(0, 0, 40, 0));
		tileTeamLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tileTeamLbl.setForeground(Color.WHITE);
		tileTeamLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		tileTeam.add(tileTeamLbl, BorderLayout.SOUTH);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		panel.add(horizontalGlue_3);

		panel.add(tileCompetitions);
		tileCompetitions.setPreferredSize(new Dimension(251, 151));
		tileCompetitions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tileCompetitions.setBorder(new LineBorder(new Color(255, 255, 255)));
		tileCompetitions.setBackground(new Color(0, 151, 230));
		tileCompetitions.setLayout(new BorderLayout(0, 0));

		JLabel tileCompetitionIcon = new JLabel("");
		tileCompetitionIcon.setBorder(new EmptyBorder(35, 0, 0, 0));
		tileCompetitionIcon.setIcon(new ImageIcon(FenetreWelcome.class.getResource("/images/trophy.png")));
		tileCompetitionIcon.setHorizontalAlignment(SwingConstants.CENTER);
		tileCompetitions.add(tileCompetitionIcon, BorderLayout.CENTER);

		JLabel tileCompetitionLbl = new JLabel("Compétitions");
		tileCompetitionLbl.setBorder(new EmptyBorder(0, 0, 40, 0));
		tileCompetitionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tileCompetitionLbl.setForeground(Color.WHITE);
		tileCompetitionLbl.setFont(new Font("Arial", Font.PLAIN, 16));
		tileCompetitions.add(tileCompetitionLbl, BorderLayout.SOUTH);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel.add(horizontalGlue_1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		contentPanel.add(verticalStrut);
	}

	public void getFooter() {	
		
		super.getFooter();

		JPanel footerTopPanel = new JPanel();
		footerTopPanel.setBorder(new EmptyBorder(30, 80, 30, 80));
		footerPanel.add(footerTopPanel, BorderLayout.NORTH);
		footerTopPanel.setBackground(new Color(52, 152, 219));
		footerTopPanel.setLayout(new BorderLayout(0, 0));
				
		footerTopPanel.add(footerLabelTeams, BorderLayout.CENTER);
		footerLabelTeams.setHorizontalAlignment(SwingConstants.CENTER);
		footerLabelTeams.setFont(new Font("Roboto", Font.PLAIN, 20));
		footerLabelTeams.setForeground(new Color(102, 204, 255));

		footerTopPanel.add(footerLabelCompetitions, BorderLayout.EAST);
		footerLabelCompetitions.setHorizontalAlignment(SwingConstants.CENTER);
		footerLabelCompetitions.setForeground(new Color(102, 204, 255));
		footerLabelCompetitions.setFont(new Font("Roboto", Font.PLAIN, 20));

		footerTopPanel.add(footerLabelMembers, BorderLayout.WEST);
		footerLabelMembers.setHorizontalAlignment(SwingConstants.CENTER);
		footerLabelMembers.setForeground(new Color(102, 204, 255));
		footerLabelMembers.setFont(new Font("Roboto", Font.PLAIN, 20));

		footerBottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	protected void getListener() {
		
		super.getListener();
		
		headerLabelHello.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				headerLabelHello.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				headerLabelHello.setForeground(Color.WHITE);
			}
		});
		
		tileMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tileMember.setBackground(new Color(0, 151, 230).darker());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				tileMember.setBackground(new Color(0, 151, 230));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				FenetreMain fenetre = new FenetreListPersonne(application, currentClass);
				fenetre.setVisible(true);
				dispose();
			}
		});

		tileTeam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tileTeam.setBackground(new Color(0, 151, 230).darker());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				tileTeam.setBackground(new Color(0, 151, 230));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				FenetreMain fenetre = new FenetreListEquipe(application, currentClass);
				fenetre.setVisible(true);
				dispose();
			}
		});
		
		tileCompetitions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tileCompetitions.setBackground(new Color(0, 151, 230).darker());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				tileCompetitions.setBackground(new Color(0, 151, 230));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				FenetreMain fenetre = new FenetreListCompetition(application, currentClass);
				fenetre.setVisible(true);
				dispose();
			}
		});
	}
	
	
	public void changeWindow(FenetreMain window) {
		
		window.getHeader();
		window.getFooter();
		window.getListener();
	}
}
