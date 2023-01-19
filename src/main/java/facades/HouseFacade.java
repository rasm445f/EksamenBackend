package facades;

import dtos.TenantDto;
import dtos.HouseDto;
import entities.House;
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
public class HouseFacade {
    private static EntityManagerFactory emf;
    private static HouseFacade instance;

    private HouseFacade() {
    }

    public static HouseFacade getHouseFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HouseFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public House createHouse(House house) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(house);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return house;
    }


    public List<HouseDto> getAllHouses() throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<House> query = em.createQuery("SELECT u FROM House u", House.class);
            if (query == null) {
                throw new NotFoundException("Can't find any houses");
            }
            List<House> houses = query.getResultList();
            return HouseDto.getHouseDtos(houses);
        } finally {
            em.close();
        }
    }


    public HouseDto getHouseById(int id) throws API_Exception {
        EntityManager em = getEntityManager();

        House house = em.find(House.class, id);
        if (house == null)
            throw new API_Exception("There's no house with that id", 404);
        em.close();
        return new HouseDto(house);


    }

    public HouseDto deleteHouse(int id) throws API_Exception {
        EntityManager em = getEntityManager();
        House house;
        try {
            house = em.find(House.class, id);
            if (house == null) {
                throw new API_Exception("Can't find a house with the housename: " + id);
            }
            em.getTransaction().begin();
            em.remove(house);
            em.getTransaction().commit();
            return new HouseDto(house);
        } finally {
            em.close();
        }
    }

    public List<TenantDto> getAllTenantsFromHouseId(int houseId) throws API_Exception {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Tenant> query = em.createQuery("SELECT t FROM Tenant t JOIN t.rentals r WHERE r.JThomeID = :houseId", Tenant.class);
            query.setParameter("houseId", houseId);
            query.setParameter("houseId",houseId);
            List<Tenant> tenants = query.getResultList();//"SELECT t FROM Tenant t JOIN t.rentals r WHERE r.houseID = :houseId", Tenant.class);
            query.setParameter("houseId", houseId);
            return TenantDto.getTenantsDtos(tenants);

        } finally {
            em.close();
        }
    }

//    public List<TenantDto> getAllTenantsOfHouseID(int houseId) throws NotFoundException {
//        EntityManager em = getEntityManager();
//
//        try {
//            TypedQuery<Tenant> query = em.createQuery("SELECT t FROM Tenant t JOIN t.rentals r JOIN r.houseID h WHERE h.id = :houseId", Tenant.class);
//            query.setParameter("houseId", houseId);
//            if (query == null) {
//                throw new NotFoundException("Can't find any rentals");
//            }
//            List<Tenant> tenants = query.getResultList();
//            return TenantDto.getTenantDtos(tenants);
//        } finally {
//            em.close();
//        }
//    }



//    public HouseDto updateHouse(int id, House houseUpdate) {
//        EntityManager em = getEntityManager();
//        House house;
//        try {
//            house = em.find(House.class, id);
//            em.getTransaction().begin();
//            house.setStartDate(houseUpdate.getStartDate());
//            house.setEndDate(houseUpdate.getEndDate());
//            house.setPriceAnnual(houseUpdate.getPriceAnnual());
//            house.setDeposit(houseUpdate.getDeposit());
//            em.getTransaction().commit();
//            return new HouseDto(house);
//        } finally {
//            em.close();
//        }
//
//    }

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



