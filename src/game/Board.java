package game;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import Animals.Animal;
import Animals.Chat;
import Animals.Chien;
import Animals.Elephant;
import Animals.Lion;
import Animals.Loup;
import Animals.Panthere;
import Animals.Rat;
import Animals.Tigre;
import dao.BoardDAO;
import observer.Observable;
import observer.Observateur;

public class Board implements Observable {

	private List<Animal> animals = new ArrayList<Animal>();

	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();

	private int curPlayer = 1;
	private Date datePartie;
	private Time timePartie;
	private User user;
	private int scorePartie;
	private boolean completed;
	private Long id = new Long(0);

	private int p1 = 8;
	private int p2 = 8;

	private Position[] trap = { new Position(0, 2), new Position(0, 3), new Position(0, 4), new Position(1, 3),
			new Position(7, 3), new Position(8, 2), new Position(8, 3), new Position(8, 4) };
	private Position[] sanctuary = { new Position(0, 3), new Position(8, 3) };
	private Position[] river = { new Position(3, 1), new Position(3, 2), new Position(4, 1), new Position(4, 2),
			new Position(5, 1), new Position(5, 2), new Position(3, 4), new Position(3, 5), new Position(4, 4),
			new Position(4, 5), new Position(5, 4), new Position(5, 5) };

	private BoardDAO boardDAO = new BoardDAO();

	public Board() {

		animals.add(new Lion(this, 2, new Position(0, 0), "lion.png"));
		animals.add(new Tigre(this, 2, new Position(0, 6), "tigre.png"));
		animals.add(new Chien(this, 2, new Position(1, 1), "chien.png"));
		animals.add(new Chat(this, 2, new Position(1, 5), "chat.png"));
		animals.add(new Rat(this, 2, new Position(2, 0), "rat.png"));
		animals.add(new Panthere(this, 2, new Position(2, 2), "panther.png"));
		animals.add(new Loup(this, 2, new Position(2, 4), "loup.png"));
		animals.add(new Elephant(this, 2, new Position(2, 6), "elephant.png"));

		animals.add(new Lion(this, 1, new Position(8, 6), "lion.png"));
		animals.add(new Tigre(this, 1, new Position(8, 0), "tigre.png"));
		animals.add(new Chien(this, 1, new Position(7, 5), "chien.png"));
		animals.add(new Chat(this, 1, new Position(7, 1), "chat.png"));
		animals.add(new Rat(this, 1, new Position(6, 6), "rat.png"));
		animals.add(new Panthere(this, 1, new Position(6, 4), "panther.png"));
		animals.add(new Loup(this, 1, new Position(6, 2), "loup.png"));
		animals.add(new Elephant(this, 1, new Position(6, 0), "elephant.png"));

	}

	public Board(long id, int curPlayer, Date datePartie, Time timePartie, int scorePartie, boolean completed,
			User user, List<Animal> animals) {
		this.id = id;
		this.curPlayer = curPlayer;
		this.datePartie = datePartie;
		this.timePartie = timePartie;
		this.scorePartie = scorePartie;
		this.completed = completed;
		this.user = user;
		this.animals = animals;
	}

	public Animal getAnimalAt(Position p) {
		for (Animal animal : animals) {
			if (animal.getPosition().equals(p))
				return animal;
		}
		return null;
	}

	public void removeAnimal(Animal a) {

		animals.remove(a);
	}

	public boolean isPosInEchequier(Position p) {
		return (p.getX() >= 0 && p.getX() <= 8) && (p.getY() >= 0 && p.getY() <= 6);
	}

	public boolean InRiver(Position p) {
		for (Position pos : river) {
			if (pos.equals(p))
				return true;
		}
		return false;
	}

	public Position up(Position position) throws PosistionOutOfRangeException {

		Position newpos = new Position(position.getX() - 1, position.getY());

		if (!isPosInEchequier(newpos))
			throw new PosistionOutOfRangeException();

		return newpos;
	}

	public Position down(Position position) throws PosistionOutOfRangeException {

		Position newpos = new Position(position.getX() + 1, position.getY());

		if (!isPosInEchequier(newpos))
			throw new PosistionOutOfRangeException();

		return newpos;

	}

	public Position right(Position position) throws PosistionOutOfRangeException {

		Position newpos = new Position(position.getX(), position.getY() + 1);

		if (!isPosInEchequier(newpos))
			throw new PosistionOutOfRangeException();

		return newpos;
	}

	public Position left(Position position) throws PosistionOutOfRangeException {

		Position newpos = new Position(position.getX(), position.getY() - 1);

		if (!isPosInEchequier(newpos))
			throw new PosistionOutOfRangeException();

		return newpos;

	}

	public List<Position> allPossibleMoves(Position p) {

		List<Position> moves = new ArrayList<Position>();

		try {
			moves.add(up(p));
		} catch (PosistionOutOfRangeException ex) {
		}

		try {
			moves.add(down(p));
		} catch (PosistionOutOfRangeException ex) {
		}
		try {
			moves.add(right(p));
		} catch (PosistionOutOfRangeException ex) {
		}

		try {
			moves.add(left(p));
		} catch (PosistionOutOfRangeException ex) {
		}

		return moves;
	}

	public int move(Animal animal, Position nextPos) {

		if (getAnimalAt(nextPos) != null) {
			if (curPlayer == 1)
				p2--;
			if (curPlayer == 2)
				p1--;
		}

		Animal a = getAnimalAt(nextPos);
		if (a != null) {
			removeAnimal(a);

			if (a.getPlayer() == 2)
				scorePartie++;
		}

		updateObservateur();

		animal.setPosition(nextPos);
		animal.setTrapped(false);

		if (p2 == 0)
			return 1;
		if (p1 == 0)
			return 2;

		if (nextPos.equals(sanctuary[0]) && curPlayer == 1) {
			return 1;
		}

		if (nextPos.equals(sanctuary[1]) && curPlayer == 2) {
			return 2;
		}

		for (Position pos : trap) {
			if (nextPos.equals(pos))
				getAnimalAt(nextPos).setTrapped(true);
		}

		return 0;
	}

	public Board saveBoard(Board board) {

		return boardDAO.create(board);
	}

	public Board find(long id) {
		return boardDAO.find(id);
	}

	public List<Board> findByUserId(long id,int x) {
		return boardDAO.findByUserId(id,x);
	}

	public void delete(Board board) {
		boardDAO.delete(board);
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	public Position[] getTrap() {
		return trap;
	}

	public Position[] getSanctuary() {
		return sanctuary;
	}

	public Position[] getRiver() {
		return river;
	}

	public int getCurPlayer() {
		return curPlayer;
	}

	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}

	public Date getDatePartie() {
		return datePartie;
	}

	public void setDatePartie(Date datePartie) {
		this.datePartie = datePartie;
	}

	public Time getTimePartie() {
		return timePartie;
	}

	public void setTimePartie(Time timePartie) {
		this.timePartie = timePartie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getScorePartie() {
		return scorePartie;
	}

	public void setScorePartie(int scorePartie) {
		this.scorePartie = scorePartie;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addObservateur(Observateur obs) {
		this.listObservateur.add(obs);
	}

	public void delObservateur() {
		this.listObservateur = new ArrayList<Observateur>();
	}

	public void updateObservateur() {
		for (Observateur obs : this.listObservateur)
			obs.update(scorePartie);
	}

}
