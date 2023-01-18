package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rental")
public class Rental {
    @Id()
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRental", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "startDate", length = 45)
    private String startDate;

    @Size(max = 45)
    @Column(name = "endDate", length = 45)
    private String endDate;


    @Column(name = "priceAnnual")
    private Integer priceAnnual;


    @NotNull
    @Column(name = "deposit")
    private Integer deposit;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "JThouseID")
    private House JThomeID;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tenant_rental",
            joinColumns = @JoinColumn(name = "JTrentalID"),
            inverseJoinColumns = @JoinColumn(name = "JTtenantID"))
    private List<Tenant> tenants = new ArrayList<>();

//    public Rental(String startDate, String endDate, String priceAnnual, String deposit, List<Tenant> tenants) {
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.priceAnnual = priceAnnual;
//        this.deposit = deposit;
//        this.tenants= tenants;
//    }
    public Rental(String startDate, String endDate, int priceAnnual, int deposit, House JThomeID, List<Tenant> tenants) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceAnnual = priceAnnual;
        this.deposit = deposit;
        this.JThomeID = JThomeID;
        this.tenants = tenants;
    }

    public Rental(String startDate, String endDate, int priceAnnual, int deposit, House JThomeID) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceAnnual = priceAnnual;
        this.deposit = deposit;
        this.JThomeID = JThomeID;
    }

    public Rental(){

    }

    public void addTenant(Tenant tenant){
        this.tenants.add(tenant);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPriceAnnual() {
        return priceAnnual;
    }

    public void setPriceAnnual(int priceAnnual) {
        this.priceAnnual = priceAnnual;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public House getJTHouseID() {
        return JThomeID;
    }

    public void setHouseID(House houseID) {
        this.JThomeID = JThomeID;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> Tenants) {
        this.tenants = tenants;
    }
}