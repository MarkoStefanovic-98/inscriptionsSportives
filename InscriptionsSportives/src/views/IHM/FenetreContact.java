package views.IHM;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controllers.Application;
import controllers.Mail;
import views.IHM.FenetreMain;
import views.IHM.Component.Popup;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class FenetreContact extends FenetreMain {
	
	private static final long serialVersionUID = -8091442122798940374L;
	
	JTextField emailInput = new JTextField();
	JTextPane messageInput = new JTextPane();
	JLabel lblSend = new JLabel("Envoyer le message");

	public FenetreContact(Application application, FenetreMain fenetreParent) {
		super(application, fenetreParent);
		init();
	}
	
	public FenetreContact(Application application, FenetreMain fenetreParent, String email) {
		super(application, fenetreParent);
		init();
		
		emailInput.setText(email);
	}


	private void init() {
		initializeWindow();
		getHeader();
		getMainPanelContent();
		getFooter();
		getListener();
	}
	
	public void getHeader(){
		super.getHeader();
		
		headerLabelApplication.setFont(new Font("Roboto", Font.PLAIN, 25));
		headerLabelApplication.setForeground(new Color(255, 255, 255, 90));
		
		headerLabelContact.setFont(new Font("Roboto", Font.PLAIN, 36));
		headerLabelContact.setForeground(Color.WHITE);
	}

	private void getMainPanelContent() {
		JPanel contentPanel = new JPanel();
		contentPanel.setAlignmentY(1.0f);
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPanel.setFocusable(false);
		contentPanel.setBackground(new Color(41, 128, 185));	
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JLabel containerTitle = new JLabel("Contacter un membre");
		containerTitle.setBorder(new EmptyBorder(0, 0, 20, 0));
		containerTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		containerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		containerTitle.setFont(new Font("Roboto", Font.PLAIN, 25));
		containerTitle.setForeground(new Color(255, 255,255));
		contentPanel.add(containerTitle, BorderLayout.NORTH);

		JPanel containerInfoPanel = new JPanel();
		containerInfoPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
		containerInfoPanel.setBackground(new Color(0, 0, 0, 50));
		contentPanel.add(containerInfoPanel, BorderLayout.CENTER);
		containerInfoPanel.setLayout(new BoxLayout(containerInfoPanel, BoxLayout.X_AXIS));

		Component space1 = Box.createHorizontalStrut(20);
		space1.setPreferredSize(new Dimension(50, 0));
		containerInfoPanel.add(space1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		containerInfoPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelTop.setBorder(new EmptyBorder(0, 0, 20, 0));
		panelTop.setBackground(new Color(0, 0, 0, 0));
		panel.add(panelTop, BorderLayout.NORTH);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBorder(new EmptyBorder(0, 0, 0, 20));
		lblEmail.setFont(new Font("Roboto", Font.BOLD, 23));
		lblEmail.setForeground(Color.WHITE);
		panelTop.add(lblEmail);

		emailInput.setPreferredSize(new Dimension(10, 51));
		emailInput.setColumns(35);
		panelTop.add(emailInput);

		messageInput.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel.add(messageInput);

		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(new EmptyBorder(20, 0, 0, 0));
		panelBottom.setBackground(new Color(0, 0, 0, 0));
		panel.add(panelBottom, BorderLayout.SOUTH);

		lblSend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSend.setOpaque(true);
		lblSend.setHorizontalAlignment(SwingConstants.CENTER);
		lblSend.setForeground(Color.WHITE);
		lblSend.setBorder(new EmptyBorder(10, 25, 10, 25));
		lblSend.setBackground(new Color(0, 153, 204));
		lblSend.setAlignmentX(0.5f);
		panelBottom.add(lblSend);

		Component spacer2 = Box.createHorizontalStrut(20);
		spacer2.setPreferredSize(new Dimension(50, 0));
		containerInfoPanel.add(spacer2);
		
	}
	
	protected void getListener() {
		
		super.getListener();
		
		headerLabelContact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				headerLabelContact.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				headerLabelContact.setForeground(Color.WHITE);
			}
		});
		
		lblSend.addMouseListener(new MouseAdapter() { // Boutton envoyer message
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSend.setBackground(new Color(0, 153, 204).darker());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSend.setBackground(new Color(0, 153, 204));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Mail.sendMail(emailInput.getText(), "Application M2L - Nouveau message", messageInput.getText());
					new Popup("Votre message à bien été envoyé !", currentClass);
					emailInput.setText("");
					messageInput.setText("");
					
				}catch(Exception exception) {
					new Popup("Votre message n'à pas pu être envoyé !", currentClass);
				}
			}
		});
		
	}
}
