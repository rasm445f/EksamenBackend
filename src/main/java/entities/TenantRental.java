package entities;

import javax.persistence.*;

@Entity
@Table(name = "tenant_rental")
public class TenantRental {
    @EmbeddedId
    private TenantRentalId id;

    @MapsId("jTrentalID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "JTrentalID", nullable = false)
    private Rental jTrentalID;

    @MapsId("jTtenantID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "JTtenantID", nullable = false)
    private Tenant jTtenantID;

    public TenantRentalId getId() {
        return id;
    }

    public void setId(TenantRentalId id) {
        this.id = id;
    }

    public Rental getJTrentalID() {
        return jTrentalID;
    }

    public void setJTrentalID(Rental jTrentalID) {
        this.jTrentalID = jTrentalID;
    }

    public Tenant getJTtenantID() {
        return jTtenantID;
    }

    public void setJTtenantID(Tenant jTtenantID) {
        this.jTtenantID = jTtenantID;
    }

}