package ihm;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

public class HelloPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HelloPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{120, 0};
		gridBagLayout.rowHeights = new int[]{14, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblBinevenueJunglegame = new JLabel("Binevenue \u00E0 JungleGame");
		GridBagConstraints gbc_lblBinevenueJunglegame = new GridBagConstraints();
		gbc_lblBinevenueJunglegame.gridx = 0;
		gbc_lblBinevenueJunglegame.gridy = 0;
		add(lblBinevenueJunglegame, gbc_lblBinevenueJunglegame);

	}

}
