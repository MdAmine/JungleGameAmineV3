package ihm;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Dialogue extends JDialog {

	public Dialogue(JFrame frame, Long idUser) {
		super(frame, "Listes Des Parties", true);

		setLayout(new FlowLayout());
		JTable tableau = new JTable(new ModelJTable(idUser));

		add(new JScrollPane(tableau));

		pack();
	}

}
