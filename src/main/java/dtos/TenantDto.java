package dtos;

import entities.Rental;
import entities.Tenant;
;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Tenant} entity
 */
public class TenantDto implements Serializable {
    private final Integer id;
    @Size(max = 45)
    @NotNull
    private final String name;
    @Size(max = 45)
    private final String job;
    private List<Rental> rentals;

    public TenantDto(Integer id, String name, String job, List<Rental> rentals) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.rentals = rentals;
    }

    public TenantDto(Tenant tenant){
        this.id = tenant.getId();
        this.name = tenant.getTenantName();
        this.job = tenant.getTenantJob();
        if(tenant.getRentalsNEW() != null){
            this.rentals = tenant.getRentalsNEW();
        }

    }

    public static List<TenantDto> getTenantsDtos(List<Tenant> tenants) {
        List<TenantDto> tenantDtos = new ArrayList<>();
        tenants.forEach(tenant -> {
            tenantDtos.add(new TenantDto(tenant));
        });
        return tenantDtos;
    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }


    public List<Rental> getRentals() {
        return rentals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantDto entity = (TenantDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.job, entity.job) &&
                Objects.equals(this.rentals, entity.rentals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, job, rentals);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "job = " + job + ", " +
                "rentals = " + rentals + ")";
    }
}