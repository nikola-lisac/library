package org.bildit.library.dao.impl;

import java.util.List;

import org.bildit.library.config.HibernateConfig;
import org.bildit.library.dao.AbstractDao;
import org.bildit.library.dao.BookDao;
import org.bildit.library.model.Book;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository("bookRepo")
@Transactional
public class BookDaoImpl extends AbstractDao<Long, Book> implements BookDao {

	private static final String COUNT_RECORDS = "select count(*) from Book";

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return getAllEntities();
	}

	@Override
	public boolean deleteBook(Book book) {
		// TODO Auto-generated method stub
		int numOfRowsPreUpdate = ((Number) getSession().createQuery(COUNT_RECORDS).uniqueResult()).intValue();
		Query query = getSession().createSQLQuery("delete from Book where bookName = :bookName");
		query.setString("bookName", book.getBookName());
		query.executeUpdate();
		int numOfRowsPostUpdate = ((Number) getSession().createQuery(COUNT_RECORDS).uniqueResult()).intValue();
		return numOfRowsPostUpdate == (numOfRowsPreUpdate - 1);
	}

	@Override
	public Book getBookById(Long id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public Book getBookByName(String bookName) {
		// TODO Auto-generated method stub
		// bookName must equal to the class' variable name, not database column name
		return (Book) createEntityCriteria().add(Restrictions.eq("bookName", bookName)).uniqueResult();
	}

	@Override
	public boolean saveBook(Book book) {
		// TODO Auto-generated method stub
		int numOfRowsPreUpdate = ((Number) getSession().createQuery(COUNT_RECORDS).uniqueResult()).intValue();
		persist(book);
		int numOfRowsPostUpdate = ((Number) getSession().createQuery(COUNT_RECORDS).uniqueResult()).intValue();
		return numOfRowsPostUpdate == (numOfRowsPreUpdate + 1);
	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		update(book);
	}

	@Override
	public boolean batchAddBooks(List<Book> books) {
		// TODO Auto-generated method stub
		int numOfRowsPreUpdate = ((Number) getSession().createQuery(COUNT_RECORDS).uniqueResult()).intValue();
		int batchSize = new HibernateConfig().getBatchSize();
		int counter = 0;
		for (Book b : books) {
			getSession().persist(b);
			counter++;
			// once the batch of inserts increases the predefined batch size,
			// flush and release memory
			if (counter % batchSize == 0) {
				getSession().flush();
				getSession().clear();
			}
		}
		int numOfRowsPostUpdate = ((Number) getSession().createQuery(COUNT_RECORDS).uniqueResult()).intValue();
		return numOfRowsPostUpdate == (numOfRowsPreUpdate + books.size());
	}

}
