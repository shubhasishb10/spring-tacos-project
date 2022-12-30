package tacos.data;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Shubhasish
 */
public class Order {

    private Long id;
    private Date placedAt;
    @NotBlank(message = "Name is required")
    private String deliveryName;
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    @NotBlank(message = "City is required")
    private String deliveryCity;
    @NotBlank(message = "State is required")
    private String deliveryState;
    @NotBlank(message = "Zip is required")
    private String deliveryZip;
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber; // 123456789007
    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([1-9][0-9])$", message = "Expiration date should be in format MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    private List<Taco> tacos = new ArrayList<>();

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryZip() {
        return deliveryZip;
    }

    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public List<Taco> getTacos() {
        return tacos;
    }

    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
    }

    public void addDesign(Taco taco) {
        if(taco != null) {
            tacos.add(taco);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId().equals(order.getId());
        //return getName().equals(order.getName()) && getStreet().equals(order.getStreet()) && getCity().equals(order.getCity()) && getState().equals(order.getState()) && getZip().equals(order.getZip()) && getCcNumber().equals(order.getCcNumber()) && getCcExpiration().equals(order.getCcExpiration()) && getCcCVV().equals(order.getCcCVV());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
        //return Objects.hash(getName(), getStreet(), getCity(), getState(), getZip(), getCcNumber(), getCcExpiration(), getCcCVV());
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + deliveryName + '\'' +
                ", street='" + deliveryStreet + '\'' +
                ", city='" + deliveryCity + '\'' +
                ", state='" + deliveryState + '\'' +
                ", zip='" + deliveryZip + '\'' +
                ", ccNumber='" + ccNumber + '\'' +
                ", ccExpiration='" + ccExpiration + '\'' +
                ", ccCVV='" + ccCVV + '\'' +
                '}';
    }

    public static class EligibleStates {
        public static final List<String> states = Arrays.asList("Delhi", "Mumbai", "Chennai", "West Bengal", "Gujarat", "Arunachal Pradesh", "Kashmir", "Bihar", "Uttar Pradesh");
    }
}
