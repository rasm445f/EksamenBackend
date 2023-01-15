package dtos;

import entities.Owner;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Owner} entity
 */
public class OwnerDto implements Serializable {
    private final Integer id;
    @Size(max = 45)
    private final String ownerName;
    @Size(max = 45)
    private final String ownerAdress;
    @Size(max = 45)
    private final String ownerPhonenum;
    @Size(max = 45)
    private final String ownercol;

    public OwnerDto(Integer id, String ownerName, String ownerAdress, String ownerPhonenum, String ownercol) {
        this.id = id;
        this.ownerName = ownerName;
        this.ownerAdress = ownerAdress;
        this.ownerPhonenum = ownerPhonenum;
        this.ownercol = ownercol;
    }

    public Integer getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerAdress() {
        return ownerAdress;
    }

    public String getOwnerPhonenum() {
        return ownerPhonenum;
    }

    public String getOwnercol() {
        return ownercol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerDto entity = (OwnerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.ownerName, entity.ownerName) &&
                Objects.equals(this.ownerAdress, entity.ownerAdress) &&
                Objects.equals(this.ownerPhonenum, entity.ownerPhonenum) &&
                Objects.equals(this.ownercol, entity.ownercol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerName, ownerAdress, ownerPhonenum, ownercol);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "ownerName = " + ownerName + ", " +
                "ownerAdress = " + ownerAdress + ", " +
                "ownerPhonenum = " + ownerPhonenum + ", " +
                "ownercol = " + ownercol + ")";
    }
}