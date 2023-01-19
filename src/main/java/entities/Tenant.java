package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tenant")
public class Tenant {
    @Id
    @Column(name = "idTenant", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "tenant_name", length = 45)
    private String tenantName;

    @Size(max = 45)
    @Column(name = "tenant_job", length = 45)
    private String tenantJob;

    @ManyToMany
    @JoinTable(name = "tenant_rental",
            joinColumns = @JoinColumn(name = "JTtenantID"),
            inverseJoinColumns = @JoinColumn(name = "JTrentalID"))
    private List<Rental> rentals = new ArrayList<>();



    @ManyToMany(mappedBy = "tenants", cascade = CascadeType.PERSIST)
    private List<Rental> rentalsNEW = new ArrayList<>();

    public List<Rental> getRentalsNEW() {
        return rentalsNEW;
    }

    public void setRentalsNEW(List<Rental> rentalsNEW) {
        this.rentalsNEW = rentalsNEW;
    }

    public Tenant() {
    }

    public Tenant(String tenantName, String tenantJob, List<Rental> rentals) {
        this.tenantName = tenantName;
        this.tenantJob = tenantJob;
        this.rentalsNEW = rentals;
    }

    public Tenant(String tenantName, String tenantJob) {
        this.tenantName = tenantName;
        this.tenantJob = tenantJob;
    }

    public Tenant(int idTenant,String tenantName, String tenantJob) {
        this.tenantName = tenantName;
        this.tenantJob = tenantJob;
        this.id = idTenant;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantJob() {
        return tenantJob;
    }

    public void setTenantJob(String tenantJob) {
        this.tenantJob = tenantJob;
    }

}