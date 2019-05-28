package views.IHM.Component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import views.IHM.FenetreMain;
import views.IHM.FenetreList.FenetreList;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;

public class Confirm extends JDialog {

	private static final long serialVersionUID = -8786610942265677834L;
	
	private final JPanel contentPanel = new JPanel();
	JLabel lblConfirmer = new JLabel("Confirmer");
	private final JLabel lblAnnuler = new JLabel("Annuler");

	public Confirm(String title, FenetreMain currentClass) {
		
		setUndecorated(true);
		setAlwaysOnTop(true);
		setMinimumSize(new Dimension(650, 240));
		setLocationRelativeTo(currentClass);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		contentPanel.setBackground(new Color(52, 152, 219));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel(title);
		label.setBorder(new EmptyBorder(0, 20, 0, 20));
		label.setFont(new Font("Roboto", Font.PLAIN, 25));
		label.setForeground(Color.WHITE);
		contentPanel.add(label);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new EmptyBorder(5, 0, 5, 5));
		buttonPane.setBackground(new Color(41, 128, 185));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		lblAnnuler.setOpaque(true);
		lblAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAnnuler.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnuler.setForeground(Color.WHITE);
		lblAnnuler.setBorder(new EmptyBorder(10, 40, 10, 40));
		lblAnnuler.setBackground(new Color(46, 204, 113));
		lblAnnuler.setAlignmentX(0.5f);
		
		buttonPane.add(lblAnnuler);

		lblConfirmer.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblConfirmer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConfirmer.setOpaque(true);
		lblConfirmer.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmer.setForeground(Color.WHITE);
		lblConfirmer.setBorder(new EmptyBorder(10, 40, 10, 40));
		lblConfirmer.setBackground(Color.decode("#e74c3c"));

		buttonPane.add(lblConfirmer);

		lblConfirmer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblConfirmer.setBackground(Color.decode("#BD3E31"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblConfirmer.setBackground(Color.decode("#e74c3c"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				((FenetreList) currentClass).confirmDeleteTrueTriggered();
				
				setVisible(false);
				dispose();
			}
		});

		lblAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAnnuler.setBackground(Color.decode("#27AC5F"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAnnuler.setBackground(Color.decode("#2ecc71"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				confirmDeleteFalseTriggered();
			}
		});
		
		setVisible(true);
	}
	
	public void confirmDeleteFalseTriggered() {
		setVisible(false);
		dispose();
	};

}
