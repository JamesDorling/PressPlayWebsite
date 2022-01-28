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

    public ReservedEntity() {}

    public ReservedEntity(Integer locationID, Integer filmID) {
        this.locationID = locationID;
        this.filmID = filmID;
    }
}
