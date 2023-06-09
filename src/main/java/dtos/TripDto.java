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
    private final GuideDto guide;
    private final List<UserDto> users = new ArrayList<>();

    public TripDto(Trip trip) {
        this.id = trip.getId();
        this.name = trip.getName();
        this.date = trip.getDate();
        this.time = trip.getTime();
        this.location = trip.getLocation();
        this.duration = trip.getDuration();
        this.packinglist = trip.getPackinglist();
        this.guide = new GuideDto(trip.getGuide());
        trip.getUsers().forEach(user -> this.users.add(new UserDto(user)));
    }

    public static List<TripDto> toList(List<Trip> trips) {
        List<TripDto> tripDtos = new ArrayList<>();
        trips.forEach(trip -> tripDtos.add(new TripDto(trip)));
        return tripDtos;
    }

    public static List<TripDto> getDtos(List<Trip> trips) {
        List<TripDto> tripDtos = new ArrayList();
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

    public GuideDto getGuide() {
        return guide;
    }

    public List<UserDto> getUsers() {
        return users;
    }


}