import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entities.*;

import errorhandling.API_Exception;
import facades.HouseFacade;
import facades.RentalFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.management.relation.RoleList;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws API_Exception {
        System.out.println("hello");

        EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
        UserFacade FACADE =  UserFacade.getUserFacade(EMF);
        HouseFacade HFACADE = HouseFacade.getHouseFacade(EMF);
        RentalFacade RFACADE = RentalFacade.getRentalFacade(EMF);


        Role adminRole = new Role("admin");
        Role userRole = new Role("user");
        List AdminList = new ArrayList<>();
        List UserList = new ArrayList<>();
        UserList.add(userRole);
        AdminList.add(adminRole);

        List RentalList = new ArrayList();
        //RentalList.add(new Tenant("Oliver","Student",));


        House house1 = new House(1,"address2","testby2",30);
        House house2 = new House(2,"address3","testby3",40);
        House house3 = new House(3,"address4","testby4",50);

        Rental rental1 = new Rental("2222-02-02","2222-22-22",2222,2222,house2);
        Rental rental2 = new Rental("1111-11-11","1111-11-11",1111,1111,house1);
        Rental rental3 = new Rental("3333-33-33","3333-33-33",3333,3333,house3);

        Tenant tenant = new Tenant(1,"Oliver","Student");
        Tenant tenant2 = new Tenant(2,"Oliver2","Student2");
        Tenant tenant3 = new Tenant(3,"Oliver3","Student3");
        Tenant tenant4 = new Tenant(4,"Oliver4","Student4");

        rental1.addTenant(tenant);
        rental2.addTenant(tenant2);
        rental3.addTenant(tenant3);
        rental3.addTenant(tenant4);





        User user1 = new User("rasm445f","snr94sps",AdminList);
        User user2 = new User("admin","admin",UserList);





       FACADE.createUser(user1);
         FACADE.createUser(user2);
        //HFACADE.createHouse(house);
        RFACADE.createRental(rental1);
       RFACADE.createRental(rental2);
        RFACADE.createRental(rental3);

        //RFACADE.assignTenantToRental(1,1);
    }
}

