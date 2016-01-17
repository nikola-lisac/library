package org.bildit.library.dao.impl;

import java.util.List;

import org.bildit.library.config.HibernateConfig;
import org.bildit.library.dao.AbstractDao;
import org.bildit.library.dao.UserDao;
import org.bildit.library.model.User;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ognjen Mišiæ
 *
 */
// repository anotacija je malo specifiènija vrsta @component anotacije koja
// govori da je ova klasa "izvor" podataka
@Repository("userRepo")
@Transactional
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {
	// obratite pažnju da se ime tabele (from User) ne piše kao bukvalno ime
	// tabele iz baze veæ kao ime klase koja je anotirana sa Entity
	private static final String COUNT_RECORDS = "select count(*) from User";

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return getAllEntities();
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		int numOfRowsPreUpdate = ((Number) getSession().createQuery(COUNT_RECORDS).uniqueResult()).intValue();
		Query query = getSession().createSQLQuery("delete from User where username = :username");
		query.setString("username", user.getUsername());
		query.executeUpdate();
		int numOfRowsPostUpdate = ((Number) getSession().createQuery(COUNT_RECORDS).uniqueResult()).intValue();
		return numOfRowsPostUpdate == (numOfRowsPreUpdate - 1);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return (User) createEntityCriteria().add(Restrictions.eq("username", username)).uniqueResult();
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		update(user);
	}

	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		int numOfRowsPreUpdate = ((Number) getSession().createQuery(COUNT_RECORDS).uniqueResult()).intValue();
		persist(user);
		int numOfRowsPostUpdate = ((Number) getSession().createQuery(COUNT_RECORDS).uniqueResult()).intValue();
		return numOfRowsPostUpdate == (numOfRowsPreUpdate + 1);
	}

	@Override
	public boolean batchAddUsers(List<User> users) {
		// TODO Auto-generated method stub
		int numOfRowsPreUpdate = ((Number) getSession().createQuery(COUNT_RECORDS).uniqueResult()).intValue();
		int batchSize = new HibernateConfig().getBatchSize();
		int counter = 0;
		for (User u : users) {
			getSession().persist(u);
			counter++;
			// once the batch of inserts increases the predefined batch size,
			// flush and release memory
			if (counter % batchSize == 0) {
				getSession().flush();
				getSession().clear();
			}
		}
		int numOfRowsPostUpdate = ((Number) getSession().createQuery(COUNT_RECORDS).uniqueResult()).intValue();
		return numOfRowsPostUpdate == (numOfRowsPreUpdate + users.size());
	}

}
