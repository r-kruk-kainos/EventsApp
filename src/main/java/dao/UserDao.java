package dao;


import domain.Person;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.Optional;

public class UserDao extends AbstractDAO<Person>{

    @Inject
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Person> findUserByUsernameAndPassword(String username, String password) {
        Criteria criteria = currentSession().createCriteria(Person.class)
                .add(Restrictions.eq("email", username))
                .add(Restrictions.eq("password", password));
        return java.util.Optional.ofNullable(uniqueResult(criteria));
    }
}
