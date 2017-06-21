package domain;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Mariusz Szymanski
 */

@Stateless
public class SurveyStorageService {

    @PersistenceContext
    private EntityManager em;

    public List<Survey> getAllSurveys() {
        return em.createQuery("select distinct s from Survey s", Survey.class)
                .getResultList();
    }

    public Survey getSurvey(Long id) {
        try {
            TypedQuery<Survey> query = em.createQuery(
                    "select distinct s from Survey s where s.id = :id", Survey.class);
            return query.setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
