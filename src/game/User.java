package game;

import dao.UserDAO;

public class User {

	private Long id;
	private String name;
	private String password;
	private UserDAO daoUser = new UserDAO();
	
	
	public User() {
	}

	public User(Long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	
	

	public User checkUserLogin(String userName, String password) {
		User user = daoUser.findByUserName(userName);

		if (user != null) {
			if (user.getPassword().equals(password)) {
				return user;
			}
		}

		return null;

	}

	public User createUser(User user) {
		return daoUser.create(user);
	}

	public User findByUserName(String userName) {
		return daoUser.findByUserName(userName);
	}
	
	
	
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
