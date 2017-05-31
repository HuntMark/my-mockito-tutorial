package my;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class CustomerDAO {

    @PersistenceContext
    EntityManager em;

    public CustomerDAO(EntityManager em) {
        this.em = em;
    }

    public Optional<Customer> findById(long id) throws Exception {
        return Optional.ofNullable(em.find(Customer.class, id));
    }
}