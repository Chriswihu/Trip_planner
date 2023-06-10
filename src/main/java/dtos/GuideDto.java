package dtos;

import entities.Guide;

import java.io.Serializable;
import java.util.List;
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

    public static List<GuideDto> getDtos(List<Guide> guides) {
        List<GuideDto> guideDtos = null;
        guides.forEach(guide -> guideDtos.add(new GuideDto(guide)));
        return guideDtos;
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



}