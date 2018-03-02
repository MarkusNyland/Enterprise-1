package JEE.Ejb;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

@RunWith(Arquillian.class)
public abstract class EjbTestBase {

    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true,"JEE")
                .addAsResource("META-INF/persistence.xml");
    }

    @EJB
    ResetEjb resetEjb;

    @Before
    public void resetDatabase(){
        resetEjb.deleteAllInDatabase();
    }

}
