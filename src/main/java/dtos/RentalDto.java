package dtos;

import entities.House;
import entities.Tenant;
import entities.Rental;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Rental} entity
 */
public class RentalDto implements Serializable{

    private final Integer id;
    @Size(max = 45)
    private final String startDate;
    @Size(max = 45)
    private final String endDate;
    @Size(max = 45)
    private final Integer priceAnnual;
    @Size(max = 45)
    @NotNull
    private final Integer deposit;
    @NotNull
    private House houseID;
    private List<Tenant> tenants = new ArrayList<>();


    public RentalDto(Integer id, String startDate, String endDate, int priceAnnual, int deposit, House houseID, List<Tenant> tenants) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceAnnual = priceAnnual;
        this.deposit = deposit;
        this.houseID = houseID;
        this.tenants = tenants;
    }
    public RentalDto(Rental rental){
        this.id = rental.getId();
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
        this.priceAnnual = rental.getPriceAnnual();
        this.deposit = rental.getDeposit();
        if(rental.getJTHouseID() != null){
            this.houseID = rental.getJTHouseID();
        }

    }

    public static List<RentalDto> getRentalDtos(List<Rental> rentals) {
        List<RentalDto> rentalDtoList = new ArrayList<>();
        rentals.forEach(rental -> {
            rentalDtoList.add(new RentalDto(rental));
        });
        return rentalDtoList;
    }

    public Integer getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getPriceAnnual() {
        return priceAnnual;
    }

    public int getDeposit() {
        return deposit;
    }


    public House getHouseID() {
        return houseID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalDto entity = (RentalDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.startDate, entity.startDate) &&
                Objects.equals(this.endDate, entity.endDate) &&
                Objects.equals(this.priceAnnual, entity.priceAnnual) &&
                Objects.equals(this.deposit, entity.deposit) &&
                Objects.equals(this.houseID, entity.houseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, priceAnnual, deposit, houseID);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "startDate = " + startDate + ", " +
                "endDate = " + endDate + ", " +
                "priceAnnual = " + priceAnnual + ", " +
                "deposit = " + deposit + ", " +
                "houseID = " + houseID + ")";
    }
}