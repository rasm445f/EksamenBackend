package dtos;

import entities.Boat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link Boat} entity
 */
public class BoatDto implements Serializable {
    private final Integer id;
    @Size(max = 45)
    private final String brand;
    @Size(max = 45)
    private final String make;
    @Size(max = 45)
    private final String image;
    @Size(max = 45)
    @NotNull
    private final String name;
    @NotNull
    private final HarborDto harborID;

    public BoatDto(Integer id, String brand, String make, String image, String name, HarborDto harborID) {
        this.id = id;
        this.brand = brand;
        this.make = make;
        this.image = image;
        this.name = name;
        this.harborID = harborID;
    }


    public Integer getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getMake() {
        return make;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public HarborDto getHarborID() {
        return harborID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoatDto entity = (BoatDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.brand, entity.brand) &&
                Objects.equals(this.make, entity.make) &&
                Objects.equals(this.image, entity.image) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.harborID, entity.harborID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, make, image, name, harborID);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "brand = " + brand + ", " +
                "make = " + make + ", " +
                "image = " + image + ", " +
                "name = " + name + ", " +
                "harborID = " + harborID + ")";
    }
}