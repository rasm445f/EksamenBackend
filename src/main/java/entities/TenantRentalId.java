package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TenantRentalId implements Serializable {
    private static final long serialVersionUID = 4141125191110680660L;
    @NotNull
    @Column(name = "JTrentalID", nullable = false)
    private Integer jTrentalID;

    @NotNull
    @Column(name = "JTtenantID", nullable = false)
    private Integer jTtenantID;

    public Integer getJTrentalID() {
        return jTrentalID;
    }

    public void setJTrentalID(Integer jTrentalID) {
        this.jTrentalID = jTrentalID;
    }

    public Integer getJTtenantID() {
        return jTtenantID;
    }

    public void setJTtenantID(Integer jTtenantID) {
        this.jTtenantID = jTtenantID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantRentalId entity = (TenantRentalId) o;
        return Objects.equals(this.jTtenantID, entity.jTtenantID) &&
                Objects.equals(this.jTrentalID, entity.jTrentalID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jTtenantID, jTrentalID);
    }

}