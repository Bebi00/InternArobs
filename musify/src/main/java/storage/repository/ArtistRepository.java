package storage.repository;

import com.example.musify.entities.Artist;
import com.example.musify.exceptions.InvalidArtistException;
import storage.hibernate.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ArtistRepository {
    public Artist getById(int id) {
        Artist artist = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Artist WHERE id=:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            artist = (Artist) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        if (artist != null) {
            return artist;
        } else {
            try {
                throw new InvalidArtistException("Artist was not found");
            } catch (InvalidArtistException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Artist> getAll() {
        List artists = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Artist";
            Query query = session.createQuery(hql);
            artists = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return (List<Artist>) artists;
    }

    public Artist save(Artist artist) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(artist);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        Criteria criteria = session.createCriteria(Artist.class);
        criteria.add(Restrictions.eq("stageName", artist.getStageName()));
        List result = criteria.list();
        if (result.size() == 1) {
            return (Artist) result.get(0);
        } else {
            return null;
        }
    }

    @Transactional
    public Artist update(Artist artist) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {

            String hql = "UPDATE Artist SET firstName =:firstName,lastName=:lastName,stageName=:stageName," +
                    "birthday=:birthday,startDateActivePeriod=:startDateActivePeriod,endDateActivePeriod=:endDateActivePeriod WHERE id=:id";
            Query query = session.createQuery(hql);
            query.setParameter("firstName", artist.getFirstName());
            query.setParameter("lastName", artist.getLastName());
            query.setParameter("stageName", artist.getStageName());
            query.setParameter("birthday", artist.getBirthday());
            query.setParameter("startDateActivePeriod", artist.getStartDateActivePeriod());
            query.setParameter("endDateActivePeriod", artist.getEndDateActivePeriod());
            query.setParameter("id", artist.getId());
            transaction = session.beginTransaction();
            int modified = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        Artist artistUpdated = getById(Math.toIntExact(artist.getId()));
        if (artistUpdated != null) {
            return artistUpdated;
        } else {
            try {
                throw new InvalidArtistException("Artist was not found");
            } catch (InvalidArtistException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Artist delete(Artist artist) {
        Transaction transaction = null;
        List result = null;
        Artist dbArtist = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(Artist.class);
            criteria.add(Restrictions.eq("id", artist.getId()));
            result = criteria.list();

            if (result.size() == 1) {
                dbArtist = (Artist) result.get(0);
                dbArtist = session.get(Artist.class, dbArtist.getId());
                transaction = session.beginTransaction();
                session.remove(dbArtist);
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        assert result != null;
        return dbArtist;
    }
}
