package dao.user;

import model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

import dao.Dao;

public interface UserDao extends Dao<User, Long>, UserDetailsService {
	User findByName(String name);
}
