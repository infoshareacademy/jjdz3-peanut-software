package domain;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Mariusz Szymanski
 */
@Stateless
public class UserActivityStorageService {

    @PersistenceContext
    private EntityManager em;

    public List<UserActivity> getAllUsersActivity() {
        return em.createQuery("select distinct u from UserActivity u", UserActivity.class)
                .getResultList();
    }
}
