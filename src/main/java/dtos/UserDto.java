package dtos;

import entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link User}
 */
public class UserDto implements Serializable {
    private final Long id;
    @NotNull
    private final String userName;
    @NotNull
    @Size(min = 1, max = 255)
    private final String userPass;
    private final String address;
    private final String phone;
    private final String email;
    private final String birthyear;
    private final String gender;
    private final List<TripDto> trips = new ArrayList<>();

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.birthyear = user.getBirthyear();
        this.gender = user.getGender();
        user.getTrips().forEach(trip -> this.trips.add(new TripDto(trip)));
    }

    public static List<UserDto> getDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList();
        users.forEach(user -> userDtos.add(new UserDto(user)));
        return userDtos;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public String getGender() {
        return gender;
    }

    public List<TripDto> getTrips() {
        return trips;
    }

}