package com.sparta.jd.pressplay.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReservedEntity {
    @Id
    @GeneratedValue
    private Integer reservationID;

    private Integer locationID;

    private Integer filmID;

    private String filmName;

    private String customer_email;

    public Integer getReservationID() {
        return reservationID;
    }

    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public Integer getFilmID() {
        return filmID;
    }

    public void setFilmID(Integer filmID) {
        this.filmID = filmID;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getCustomerEmail() {
        return customer_email;
    }

    public void setCustomerEmail(String customer_name) {
        this.customer_email = customer_name;
    }

    public ReservedEntity() {}

    public ReservedEntity(Integer locationID, Integer filmID, String filmName, String customer_name) {
        this.locationID = locationID;
        this.filmID = filmID;
        this.filmName = filmName;
        this.customer_email = customer_name;
    }
}
