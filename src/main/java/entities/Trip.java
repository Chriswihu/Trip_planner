package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    //    @Temporal(TemporalType.DATE)
    private String date; //TODO: Change to Date java.util.Date
    //    @Temporal(TemporalType.TIME)
    private String time; //TODO: Change to Time java.util.Time
    private String location;
    private String duration;
    private String packinglist;
    @ManyToOne
    @JoinColumn(name = "guide_id", referencedColumnName = "id")
    private Guide guide;


    @ManyToMany(mappedBy = "trips")
    private List<User> users;


    public List<String> usersOnTrip() {
        if (users.isEmpty()) {
            return null;
        }
        List<String> usersByName = new ArrayList<>();
        users.forEach((user) -> {
            usersByName.add(user.getUserName());
        });
        return usersByName;
    }

    public Trip() {
    }

    public Trip(String name, String date, String time, String location, String duration, String packinglist) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packinglist = packinglist;
        this.users = new ArrayList<>();
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

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPackinglist() {
        return packinglist;
    }
    public void setPackinglist(String packinglist) {
        this.packinglist = packinglist;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public void addUser(User user) {
        this.users.add(user);
        if(!user.getTrips().contains(this)) {
            user.addTrip(this);
        }
    }

    public Guide getGuide() {
        return guide;
    }
    public void setGuide(Guide guide) {
        this.guide = guide;
    }
    public void addGuide(Guide guide) {
        this.guide = guide;
        if(!guide.getTrips().contains(this)) {
            guide.addTrip(this);
        }
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", duration='" + duration + '\'' +
                ", packinglist='" + packinglist + '\'' +
                '}';
    }



}