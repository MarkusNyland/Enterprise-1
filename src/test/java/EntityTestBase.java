import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class EntityTestBase {
    private EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    @Before
    public void init(){
        entityManagerFactory = Persistence.createEntityManagerFactory("H2-Database");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void tearDown(){
        entityManager.close();
        entityManagerFactory.close();
    }

    protected boolean persistInTransaction(Object... objectsToPersist){
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            for (Object o : objectsToPersist) {
                entityManager.persist(o);
            }
            entityTransaction.commit();

        }catch (Exception e){
            entityTransaction.rollback();
            return false;
        }
        return true;
    }
}
