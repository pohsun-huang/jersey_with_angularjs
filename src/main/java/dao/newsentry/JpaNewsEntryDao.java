package dao.newsentry;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.NewsEntry;

import org.springframework.transaction.annotation.Transactional;

import dao.JpaDao;

public class JpaNewsEntryDao extends JpaDao<NewsEntry, Long> implements
		NewsEntryDao {

	public JpaNewsEntryDao() {
		super(NewsEntry.class);
	}

	@Transactional(readOnly = true)
	public List<NewsEntry> findAll() {
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<NewsEntry> cq = cb.createQuery(NewsEntry.class);

		Root<NewsEntry> root = cq.from(NewsEntry.class);
		cq.orderBy(cb.desc(root.get("date")));
		TypedQuery<NewsEntry> typedQuery = this.getEntityManager().createQuery(
				cq);
		return typedQuery.getResultList();
	}

}
