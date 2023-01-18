package dtos;

import entities.House;
import entities.Tenant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.House} entity
 */
public class HouseDto implements Serializable {
    private final Integer id;
    @Size(max = 45)
    @NotNull
    private final String address;
    @Size(max = 45)
    @NotNull
    private final String city;
    @NotNull
    private final Integer numberOfRooms;

    public HouseDto(Integer id, String address, String city, int numberOfRooms) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.numberOfRooms = numberOfRooms;
    }
    public HouseDto(House house){
        this.id = house.getId();
        this.address = house.getAddress();
        this.city = house.getCity();
        this.numberOfRooms = house.getNumberOfRooms();
    }

    public static List<HouseDto> getHouseDtos(List<House> houses) {
        List<HouseDto> houseDtoS = new ArrayList<>();
        houses.forEach(house -> {
            houseDtoS.add(new HouseDto(house));
        });
        return houseDtoS;
    }
    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseDto entity = (HouseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.numberOfRooms, entity.numberOfRooms);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, city, numberOfRooms);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "address = " + address + ", " +
                "city = " + city + ", " +
                "numberOfRooms = " + numberOfRooms + ")";
    }
}



//package dtos;
//
//import entities.House;
//import entities.House;
//
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
///**
// * A DTO for the {@link entities.House} entity
// */
//public class HouseDto implements Serializable {
//    private final Integer id;
//    @Size(max = 45)
//    @NotNull
//    private final String address;
//    @Size(max = 45)
//    @NotNull
//    private final String city;
//    @NotNull
//    private final Integer numberOfRooms;
//
//    public HouseDto(Integer id, String address, String city, Integer numberOfRooms) {
//        this.id = id;
//        this.address = address;
//        this.city = city;
//        this.numberOfRooms = numberOfRooms;
//    }
//
//    public HouseDto(House house) {
//        this.id = house.getId();
//        this.address = house.getAddress();
//        this.city = house.getCity();
//        this.numberOfRooms = house.getNumberOfRooms();
//    }
//
//    public static List<HouseDto> getHouseDtos(List<House> houses) {
//        List<HouseDto> HouseDtoList = new ArrayList<>();
//        houses.forEach(house -> {
//            HouseDtoList.add(new HouseDto(house));
//        });
//        return HouseDtoList;
//    }
//
//
//    public Integer getId() {
//        return id;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public Integer getNumberOfRooms() {
//        return numberOfRooms;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        HouseDto entity = (HouseDto) o;
//        return Objects.equals(this.id, entity.id) &&
//                Objects.equals(this.address, entity.address) &&
//                Objects.equals(this.city, entity.city) &&
//                Objects.equals(this.numberOfRooms, entity.numberOfRooms);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, address, city, numberOfRooms);
//    }
//
//    @Override
//    public String toString() {
//        return getClass().getSimpleName() + "(" +
//                "id = " + id + ", " +
//                "address = " + address + ", " +
//                "city = " + city + ", " +
//                "numberOfRooms = " + numberOfRooms + ")";
//    }
//
//    public House toHouse() {
//        return new House(address, city, numberOfRooms);
//    }
//}