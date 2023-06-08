package dtos;

import entities.Trip;
import org.w3c.dom.ls.LSException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link Trip}
 */
public class TripDto implements Serializable {
    private final Long id;
    private final String name;
    private final String date;
    private final String time;
    private final String location;
    private final String duration;
    private final String packinglist;
    private final List<UserDto> users = new ArrayList<>();

    public TripDto(Trip trip) {
        this.id = trip.getId();
        this.name = trip.getName();
        this.date = trip.getDate();
        this.time = trip.getTime();
        this.location = trip.getLocation();
        this.duration = trip.getDuration();
        this.packinglist = trip.getPackinglist();
        trip.getUsers().forEach(user -> this.users.add(new UserDto(user)));
    }

    public static List<TripDto> toList(List<Trip> trips) {
        List<TripDto> tripDtos = new ArrayList<>();
        trips.forEach(trip -> tripDtos.add(new TripDto(trip)));
        return tripDtos;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getDuration() {
        return duration;
    }

    public String getPackinglist() {
        return packinglist;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripDto entity = (TripDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.time, entity.time) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.duration, entity.duration) &&
                Objects.equals(this.packinglist, entity.packinglist) &&
                Objects.equals(this.users, entity.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, time, location, duration, packinglist, users);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "date = " + date + ", " +
                "time = " + time + ", " +
                "location = " + location + ", " +
                "duration = " + duration + ", " +
                "packinglist = " + packinglist + ", " +
                "users = " + users + ")";
    }
}