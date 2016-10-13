package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import game.Board;
import game.User;
import observer.Observateur;

public class MainFrame extends JFrame {

	private Board board = new Board();
	private BoardPanel boardPanel = new BoardPanel(board);

	private User user = new User();;

	private JToolBar toolBar = new JToolBar();
	private JLabel lblScore = new JLabel("0");

	private JMenuBar menuBar;
	private JMenu partieMenu;
	private JMenu scoreMenu;
	private JMenu aproposMenu;
	private JMenuItem MesScoreItem;
	private JMenuItem nouveauItem;
	private JMenuItem chargerMenu;
	private JMenuItem sauvegarderItem;
	private JMenuItem fermerItem;

	private HomePanel homePanel;

	private Dialogue dialogue;
	private MainFrame frame = this;

	public MainFrame() {

		homePanel = new HomePanel();
		add(homePanel);

		toolBar.add(lblScore);

		homePanel.getBtnConnecter().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String userName = homePanel.getNameLField().getText();
				String password = homePanel.getPassLField().getText();

				user = user.checkUserLogin(userName, password);
				if (user != null) {
					board = new Board();

					board.setUser(user);

					addObservateurToBoard(board);

					remove(homePanel);
					setUpMenu();
					lblScore.setText("Votre Score est : " + board.getScorePartie());
					add(toolBar, "North");
					boardPanel = new BoardPanel(board);
					add(boardPanel);

					revalidate();
				} else {
					JOptionPane.showMessageDialog(null, "information incorrects", "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		homePanel.getBtnValider().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String userName = homePanel.getNameSField().getText();
				String password = homePanel.getPassSField().getText();
				String confirm = homePanel.getConfirmSField().getText();

				user = user.findByUserName(userName);
				if (user == null && password.equals(confirm)) {
					User newUser = new User(userName, password);
					if ((user = newUser.createUser(newUser)) != null) {
						board = new Board();

						addObservateurToBoard(board);

						board.setUser(user);
						lblScore.setText("Votre Score est " + board.getScorePartie());
						setUpMenu();
						JOptionPane.showMessageDialog(null, "vous étes enregistré avec succées", "Information",
								JOptionPane.INFORMATION_MESSAGE);
						remove(homePanel);
						add(toolBar, "North");
						add(boardPanel = new BoardPanel(board));
						revalidate();
					}
				}
				if (user != null) {
					JOptionPane.showMessageDialog(null, "ce nom d'utilisateur existe deja", "Information",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (!password.equals(confirm)) {
					JOptionPane.showMessageDialog(null, "confirmation du mot de passe incorrect", "error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		setUpLook("Nimbus");
		setSize(550, 550);
		setVisible(true);
	}

	private void setUpMenu() {

		menuBar = new JMenuBar();
		partieMenu = new JMenu("Partie");

		scoreMenu = new JMenu("Score");
		MesScoreItem = new JMenuItem("Mes Score");
		MesScoreItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogue = new Dialogue(frame, user.getId());
				dialogue.setVisible(true);

			}
		});

		aproposMenu = new JMenu("?");
		
		nouveauItem = new JMenuItem("Nouvelle Partie");
		chargerMenu = new JMenu("Charger Partie");

		List<Board> boardList = board.findByUserId(user.getId(), 1);

		JMenu[] boardMenu = new JMenu[boardList.size()];

		JMenuItem[][] boardItem = new JMenuItem[boardList.size()][2];

		for (int i = 0; i < boardList.size(); i++) {
			boardMenu[i] = new JMenu(boardList.get(i).getId() + "    " + boardList.get(i).getDatePartie().toString()
					+ "  " + boardList.get(i).getTimePartie().toString());

			boardItem[i][0] = new JMenuItem("charger");
			final int ii = i;
			boardItem[i][0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Board board = new Board();
					board = board.find(boardList.get(ii).getId());

					remove(boardPanel);
					addObservateurToBoard(board);
					boardPanel = new BoardPanel(board);

					add(boardPanel);

					revalidate();
				}
			});

			boardItem[i][1] = new JMenuItem("supprimer");
			boardItem[i][1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					board.delete(boardList.get(ii));
					setUpMenu();
				}
			});

			boardMenu[i].add(boardItem[i][0]);
			boardMenu[i].add(boardItem[i][1]);
			chargerMenu.add(boardMenu[i]);
		}

		nouveauItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Board board = new Board();
				remove(boardPanel);

				addObservateurToBoard(board);

				lblScore.setText("Votre Score est : " + board.getScorePartie());

				boardPanel = new BoardPanel(board);

				add(boardPanel);

				revalidate();

			}
		});

		sauvegarderItem = new JMenuItem("Sauvegader Partie");
		sauvegarderItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.setCompleted(false);
				board.saveBoard(board);
				setUpMenu();
			}
		});

		fermerItem = new JMenuItem("Fermer");
		fermerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		partieMenu.add(nouveauItem);
		partieMenu.add(chargerMenu);
		partieMenu.add(sauvegarderItem);
		
		scoreMenu.add(MesScoreItem);

		partieMenu.add(fermerItem);
		menuBar.add(partieMenu);
		menuBar.add(scoreMenu);
		menuBar.add(aproposMenu);
		setJMenuBar(menuBar);
	}

	private void setUpLook(String look) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if (look.equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					SwingUtilities.updateComponentTreeUI(this);
					break;
				}
			}
		} catch (Exception e) {
			System.err.println("Look & Feel intouvable");
		}
	}

	public void addObservateurToBoard(Board b) {
		b.addObservateur(new Observateur() {
			public void update(int sc) {
				lblScore.setText("Votre Score est : " + sc);
				toolBar.revalidate();
			}
		});
	}

	public static void main(String[] args) {
		new MainFrame();

	}

}
