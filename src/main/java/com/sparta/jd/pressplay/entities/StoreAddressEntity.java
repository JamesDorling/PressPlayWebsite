package com.sparta.jd.pressplay.entities;

import javax.persistence.*;

@Entity
public class StoreAddressEntity {
    @Id
    @GeneratedValue
    private Integer location_id;

    private String locationName;

    private String postCode;

    private String openingTimes;

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getOpeningTimes() {
        return openingTimes;
    }

    public void setOpeningTimes(String openingTimes) {
        this.openingTimes = openingTimes;
    }

    public StoreAddressEntity() {}

    public StoreAddressEntity(Integer location_id, String locationName, String postCode, String openingTimes) {
        this.location_id = location_id;
        this.locationName = locationName;
        this.postCode = postCode;
        this.openingTimes = openingTimes;
    }
}
