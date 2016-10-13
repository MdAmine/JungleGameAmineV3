package ihm;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;

public class HomePanel extends JPanel {
	private JTextField nameLField;
	private JPasswordField passLField;
	private JTextField nameSField;
	private JPasswordField passSField;
	private JPasswordField confirmSField;
	private HelloPanel panel;
	private JButton btnValider;
	private JButton btnConnecter;

	/**
	 * Create the panel.
	 */
	public HomePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 100, -17, 0, 0};
		gridBagLayout.rowHeights = new int[]{50, 38, 0, 0, 0, 40, 0, 0, 0, 0, 0};
		setLayout(gridBagLayout);

		panel = new HelloPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 10;
		gbc_panel.gridwidth = 5;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);

		JLabel lblConnexion = new JLabel("Connexion ");
		lblConnexion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		GridBagConstraints gbc_lblConnexion = new GridBagConstraints();
		gbc_lblConnexion.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblConnexion.gridwidth = 2;
		gbc_lblConnexion.insets = new Insets(0, 0, 5, 0);
		gbc_lblConnexion.gridx = 7;
		gbc_lblConnexion.gridy = 1;
		add(lblConnexion, gbc_lblConnexion);

		JLabel lblNom = new JLabel("Nom :");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.anchor = GridBagConstraints.EAST;
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 7;
		gbc_lblNom.gridy = 2;
		add(lblNom, gbc_lblNom);

		nameLField = new JTextField();
		GridBagConstraints gbc_nameLField = new GridBagConstraints();
		gbc_nameLField.insets = new Insets(0, 0, 5, 0);
		gbc_nameLField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameLField.gridx = 8;
		gbc_nameLField.gridy = 2;
		add(nameLField, gbc_nameLField);
		nameLField.setColumns(10);

		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.anchor = GridBagConstraints.WEST;
		gbc_lblMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePasse.gridx = 7;
		gbc_lblMotDePasse.gridy = 3;
		add(lblMotDePasse, gbc_lblMotDePasse);

		passLField = new JPasswordField();
		GridBagConstraints gbc_passLField = new GridBagConstraints();
		gbc_passLField.insets = new Insets(0, 0, 5, 0);
		gbc_passLField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passLField.gridx = 8;
		gbc_passLField.gridy = 3;
		add(passLField, gbc_passLField);
		passLField.setColumns(10);

		btnConnecter = new JButton("Connecter");
		GridBagConstraints gbc_btnConnecter = new GridBagConstraints();
		gbc_btnConnecter.anchor = GridBagConstraints.EAST;
		gbc_btnConnecter.insets = new Insets(0, 0, 5, 0);
		gbc_btnConnecter.gridx = 8;
		gbc_btnConnecter.gridy = 4;
		add(btnConnecter, gbc_btnConnecter);

		JLabel lblNouveaucompte = new JLabel("Vous etes nouveau ! ");
		lblNouveaucompte.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		GridBagConstraints gbc_lblNouveaucompte = new GridBagConstraints();
		gbc_lblNouveaucompte.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNouveaucompte.insets = new Insets(0, 0, 5, 0);
		gbc_lblNouveaucompte.gridwidth = 2;
		gbc_lblNouveaucompte.gridx = 7;
		gbc_lblNouveaucompte.gridy = 5;
		add(lblNouveaucompte, gbc_lblNouveaucompte);

		JLabel lblNom_1 = new JLabel("Nom :");
		GridBagConstraints gbc_lblNom_1 = new GridBagConstraints();
		gbc_lblNom_1.anchor = GridBagConstraints.EAST;
		gbc_lblNom_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom_1.gridx = 7;
		gbc_lblNom_1.gridy = 6;
		add(lblNom_1, gbc_lblNom_1);

		JPasswordField aa = new JPasswordField();
		
		
		nameSField = new JTextField();
		GridBagConstraints gbc_nameSField = new GridBagConstraints();
		gbc_nameSField.insets = new Insets(0, 0, 5, 0);
		gbc_nameSField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameSField.gridx = 8;
		gbc_nameSField.gridy = 6;
		add(nameSField, gbc_nameSField);
		nameSField.setColumns(10);

		JLabel lblMotDePasse_1 = new JLabel("Mot de passe :");
		GridBagConstraints gbc_lblMotDePasse_1 = new GridBagConstraints();
		gbc_lblMotDePasse_1.anchor = GridBagConstraints.EAST;
		gbc_lblMotDePasse_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePasse_1.gridx = 7;
		gbc_lblMotDePasse_1.gridy = 7;
		add(lblMotDePasse_1, gbc_lblMotDePasse_1);

		passSField = new JPasswordField();
		GridBagConstraints gbc_passSField = new GridBagConstraints();
		gbc_passSField.insets = new Insets(0, 0, 5, 0);
		gbc_passSField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passSField.gridx = 8;
		gbc_passSField.gridy = 7;
		add(passSField, gbc_passSField);
		passSField.setColumns(10);

		JLabel lblConfirmation = new JLabel("Confirmation :");
		GridBagConstraints gbc_lblConfirmation = new GridBagConstraints();
		gbc_lblConfirmation.anchor = GridBagConstraints.EAST;
		gbc_lblConfirmation.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmation.gridx = 7;
		gbc_lblConfirmation.gridy = 8;
		add(lblConfirmation, gbc_lblConfirmation);

		confirmSField = new JPasswordField();
		GridBagConstraints gbc_confirmSField = new GridBagConstraints();
		gbc_confirmSField.insets = new Insets(0, 0, 5, 0);
		gbc_confirmSField.fill = GridBagConstraints.HORIZONTAL;
		gbc_confirmSField.gridx = 8;
		gbc_confirmSField.gridy = 8;
		add(confirmSField, gbc_confirmSField);
		confirmSField.setColumns(10);

		btnValider = new JButton("Valider");
		GridBagConstraints gbc_btnValider = new GridBagConstraints();
		gbc_btnValider.insets = new Insets(0, 0, 5, 0);
		gbc_btnValider.anchor = GridBagConstraints.EAST;
		gbc_btnValider.gridx = 8;
		gbc_btnValider.gridy = 9;
		add(btnValider, gbc_btnValider);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 0, 5);
		gbc_separator.gridx = 4;
		gbc_separator.gridy = 10;
		add(separator, gbc_separator);

	}

	public JButton getBtnValider() {
		return btnValider;
	}

	public void setBtnValider(JButton btnValider) {
		this.btnValider = btnValider;
	}

	public JButton getBtnConnecter() {
		return btnConnecter;
	}

	public void setBtnConnecter(JButton btnConnecter) {
		this.btnConnecter = btnConnecter;
	}

	public JTextField getNameLField() {
		return nameLField;
	}

	public void setNameLField(JTextField nameLField) {
		this.nameLField = nameLField;
	}

	public JTextField getPassLField() {
		return passLField;
	}

	public void setPassLField(JPasswordField passLField) {
		this.passLField = passLField;
	}

	public JTextField getNameSField() {
		return nameSField;
	}

	public void setNameSField(JTextField nameSField) {
		this.nameSField = nameSField;
	}

	public JTextField getPassSField() {
		return passSField;
	}

	public void setPassSField(JPasswordField passSField) {
		this.passSField = passSField;
	}

	public JTextField getConfirmSField() {
		return confirmSField;
	}

	public void setConfirmSField(JPasswordField confirmSField) {
		this.confirmSField = confirmSField;
	}
	
	
	
}
