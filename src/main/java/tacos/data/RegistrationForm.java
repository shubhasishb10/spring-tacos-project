package tacos.data;

import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.security.domain.DomainUser;

/**
 * @author Shubhasish
 */
public class RegistrationForm {

    private String username;
    private String password;
    private String fullName;
    private String street;
    private String city;
    private String zip;
    private String state;
    private String phone;

    public DomainUser toUser(PasswordEncoder passwordEncoder) {
        return new DomainUser(
                username,
                passwordEncoder.encode(password),
                fullName,
                street,
                city,
                state,
                zip,
                phone
        );
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
