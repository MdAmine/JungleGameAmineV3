package ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import game.Board;

public class ModelJTable extends AbstractTableModel {
	private List<Board> parties = new ArrayList<Board>();

	private final String[] entetes = { "id", "datePartie", "timePartie", "scorePartie" };

	public ModelJTable(Long id) {
		Board board = new Board();
		parties = board.findByUserId(id,2);
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return parties.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return parties.get(rowIndex).getId();
		case 1:
			return parties.get(rowIndex).getDatePartie();
		case 2:
			return parties.get(rowIndex).getTimePartie();
		case 3:
			return parties.get(rowIndex).getScorePartie();

		default:
			return null; // Ne devrait jamais arriver
		}
	}

}
