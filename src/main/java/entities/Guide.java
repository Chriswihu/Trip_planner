package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guide")
public class Guide implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String gender;
    private String birthyear;
    private String profile;
    private String imageUrl;
    @OneToMany(mappedBy = "guide", cascade = CascadeType.PERSIST)
    private List<Trip> trips;

    public Guide() {
    }

    public Guide(String name, String gender, String birthyear, String profile, String imageUrl) {
        this.name = name;
        this.gender = gender;
        this.birthyear = birthyear;
        this.profile = profile;
        this.imageUrl = imageUrl;
        //TODO: Add Date object
        // this.date = new Date()
        this.trips = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthyear() {
        return birthyear;
    }
    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Trip> getTrips() {
        return trips;
    }
    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
    public void addTrip(Trip trip) {
        this.trips.add(trip);
        trip.setGuide(this);
    }

    @Override
    public String toString() {
        return "Guide{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthyear='" + birthyear + '\'' +
                ", profile='" + profile + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}