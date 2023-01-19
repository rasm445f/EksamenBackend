package facades;


import dtos.RentalDto;
import entities.Rental;
import entities.Tenant;
import errorhandling.API_Exception;
import javassist.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class RentalFacade {
    private static EntityManagerFactory emf;
    private static RentalFacade instance;

    private RentalFacade() {
    }

    public static RentalFacade getRentalFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RentalFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Rental createRental(Rental rental) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rental);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return rental;
    }

    public Rental assignTenantToRental(int tenantID, int rentalID) throws API_Exception{
        EntityManager em = getEntityManager();
        Tenant tenant = em.find(Tenant.class,tenantID);
        Rental rental = em.find(Rental.class,rentalID);
        try {
            em.getTransaction().begin();
            rental.getTenants().add(tenant);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (tenant == null) {
                throw new API_Exception("Can't find a user with the tenantID: " + tenantID,400,e);
            }
            if (rental == null) {
                throw  new API_Exception("Can't find a retal with the id: "+rentalID,400,e);
            }
        } finally {
            em.close();
        }
        return rental;
    }


//    public Rental createRental(Rental rental, Harbor harbor) {
//        EntityManager em = getEntityManager();
//
//        if(harbor==null){
//            Harbor defaultHarbor = new Harbor();
//            rental.addRole(defaultRole);
//        }
//        else{
//            rental.addRole(role);
//        }
//        try {
//            em.getTransaction().begin();
//            em.persist(rental);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//        return rental;
//    }


    public List<RentalDto> getAllRentals() throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Rental> query = em.createQuery("SELECT u FROM Rental u", Rental.class);
            if (query == null) {
                throw new NotFoundException("Can't find any rentals");
            }
            List<Rental> rentals = query.getResultList();
            return RentalDto.getRentalDtos(rentals);
        } finally {
            em.close();
        }
    }

    public List<Rental> getRentalsByTenantId(Integer tenantId) {
        EntityManager em = getEntityManager();
        TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r JOIN r.tenants t WHERE t.id = :tenantId", Rental.class);
        query.setParameter("tenantId", tenantId);
        return query.getResultList();
    }

    public List<RentalDto> getAllRentalsOfTenantID(int tenantId) throws NotFoundException {
        EntityManager em = getEntityManager();
        Tenant tenant = em.find(Tenant.class,tenantId);

        try {
            List<Rental> rentals = tenant.getRentalsNEW();
            return RentalDto.getRentalDtos(rentals);
        } finally {
            em.close();
        }
    }

    public RentalDto getRentalById(int id) throws API_Exception {
        EntityManager em = getEntityManager();

        Rental rental = em.find(Rental.class, id);
        if (rental == null)
            throw new API_Exception("There's no rental with that id", 404);
        em.close();
        return new RentalDto(rental);


    }

    public RentalDto deleteRental(int id) throws API_Exception {
        EntityManager em = getEntityManager();
        Rental rental;
        try {
            rental = em.find(Rental.class, id);
            if (rental == null) {
                throw new API_Exception("Can't find a rental with the rentalname: " + id);
            }
            em.getTransaction().begin();
            em.remove(rental);
            em.getTransaction().commit();
            return new RentalDto(rental);
        } finally {
            em.close();
        }
    }


    public RentalDto updateRental(int id, Rental rentalUpdate) {
        EntityManager em = getEntityManager();
        Rental rental;
        try {
            rental = em.find(Rental.class, id);
            em.getTransaction().begin();
            rental.setStartDate(rentalUpdate.getStartDate());
            rental.setEndDate(rentalUpdate.getEndDate());
            rental.setPriceAnnual(rentalUpdate.getPriceAnnual());
            rental.setDeposit(rentalUpdate.getDeposit());
            em.getTransaction().commit();
            return new RentalDto(rental);
        } finally {
            em.close();
        }

    }

//    public OwnerDto addOwner(Boat boat) throws API_Exception {
//        EntityManager em = getEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(boat);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//        BoatDto theFan = new BoatDto(boat);
//        return theFan;
//    }
//    public List<BoatDto> getAllBoatsFromID(int id){
//        EntityManager em = getEntityManager();
//        try {
//            TypedQuery<Boat> query = em.createQuery("SELECT f FROM Boat f WHERE f.userid = ?1", Boat.class)
//                    .setParameter(1,id);
//            List<Boat> favoritesList = query.getResultList();
//            return BoatDto.getFavoriteDTOs(favoritesList);
//        } finally {
//            em.close();
//        }
//    }


}


