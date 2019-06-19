package lucasdasilvac.dev.controller;

import lucasdasilvac.dev.connection.ConnectionPostgres;
import lucasdasilvac.dev.dao.UserDAO;
import lucasdasilvac.dev.entity.User;

public class UserController {
	ConnectionPostgres connPostgres = new ConnectionPostgres();
	UserDAO userDao = new UserDAO(connPostgres);
	
	public User searchUserById(Long id) {
		return userDao.read(id);
	}
	
	public boolean registerUser(String name, String email, String adress) {
		if(name.equals("")) {
			return false;
		} else if (!name.matches("[a-zA-Z ÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*")) {
			return false;
		}
		
		if(email.equals("")) {
			return false;
		} else if (!email.matches("[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+")) {
			return false;
		}
		
		if(adress.equals("")) {
			return false;
		}
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setAdress(adress);
		return userDao.create(user);
	}
	
	public boolean updateUser(Long id, String name, String email, String adress) {
		if(id <= 0) {
			return false;
		}
		if(name.equals("")) {
			return false;
		} else if (!name.matches("[a-zA-Z ÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*")) {
			return false;
		}
		
		if(email.equals("")) {
			return false;
		} else if (!email.matches("[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+")) {
			return false;
		}
		
		if(adress.equals("")) {
			return false;
		}
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setEmail(email);
		user.setAdress(adress);
		return userDao.update(user);
	}
	
	public boolean deleteUser(Long id) {
		if(id <= 0) {
			return false;
		}
		return userDao.delete(id);
	}
}
