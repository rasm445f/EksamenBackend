package facades;

import dtos.RentalDto;
import entities.House;
import entities.Tenant;
import entities.Rental;
import errorhandling.API_Exception;
import javassist.NotFoundException;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RentalFacadeTest {

    private static EntityManagerFactory emf;
    private static RentalFacade facade;
    Rental rental1;
    Rental rental2;

    List<Tenant> tenants;
    House house;
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = RentalFacade.getRentalFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Rental.deleteAllRows").executeUpdate();
            tenants = new ArrayList<>();
            house = new House(1,"address2","testby2",30);
            rental1 = new Rental("2222-02-02","2222-22-22",2222,2222,house);
            rental2 = new Rental("1111-11-11","1111-11-11",1111,1111,house);
            em.persist(rental1);
            em.persist(rental2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAllRentals() throws NotFoundException {
        List<RentalDto> actual = facade.getAllRentals();
        int expected = 2;
        assertEquals(expected,actual.size());
    }

    @Test
    void createRental() throws NotFoundException {
        Rental rental = new Rental("2222-02-02","2222-22-22",2222,2222,house);
        facade.createRental(rental);
        List<RentalDto> actual = facade.getAllRentals();
        int expected = 3;
        assertEquals(expected,actual.size());
    }

    @Test
    void deleteRental() throws NotFoundException, API_Exception {
        facade.deleteRental(rental1.getId());
        List<RentalDto> actual = facade.getAllRentals();
        int expected = 1;
        assertEquals(expected,actual.size());
    }
}
