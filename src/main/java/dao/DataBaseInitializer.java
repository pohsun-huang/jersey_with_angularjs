package dao;

import java.util.Date;

import model.NewsEntry;
import model.User;

import org.springframework.security.crypto.password.PasswordEncoder;

import dao.newsentry.NewsEntryDao;
import dao.user.UserDao;

public class DataBaseInitializer {

	private NewsEntryDao newsEntryDao;
	private UserDao userDao;
	private PasswordEncoder passwordEncoder;

	public DataBaseInitializer(NewsEntryDao newsEntryDao, UserDao userDao,
			PasswordEncoder passwordEncoder) {
		super();
		this.newsEntryDao = newsEntryDao;
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}

	public void initDataBase() {
		User user = new User("user", this.passwordEncoder.encode("user"));
		user.addRole("user");
		this.userDao.save(user);

		User admin = new User("admin", this.passwordEncoder.encode("admin"));
		admin.addRole("user");
		admin.addRole("admin");
		this.userDao.save(admin);

		long timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 24;
		for (int i = 0; i < 10; i++) {
			NewsEntry newsEntry = new NewsEntry();
			newsEntry.setContent("This is example content " + i);
			newsEntry.setDate(new Date(timestamp));
			this.newsEntryDao.save(newsEntry);
			timestamp += 1000 * 60 * 60;
		}
	}

}
