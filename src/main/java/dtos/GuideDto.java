package dtos;

import entities.Guide;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Guide}
 */
public class GuideDto implements Serializable {
    private final Long id;
    private final String name;
    private final String gender;
    private final String birthyear;
    private final String profile;
    private final String imageUrl;

    public GuideDto(Guide guide) {
        this.id = guide.getId();
        this.name = guide.getName();
        this.gender = guide.getGender();
        this.birthyear = guide.getBirthyear();
        this.profile = guide.getProfile();
        this.imageUrl = guide.getImageUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public String getProfile() {
        return profile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuideDto entity = (GuideDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.birthyear, entity.birthyear) &&
                Objects.equals(this.profile, entity.profile) &&
                Objects.equals(this.imageUrl, entity.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, birthyear, profile, imageUrl);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "gender = " + gender + ", " +
                "birthyear = " + birthyear + ", " +
                "profile = " + profile + ", " +
                "imageUrl = " + imageUrl + ")";
    }
}