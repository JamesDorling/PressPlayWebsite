package com.sparta.jd.pressplay.services;

import com.sparta.jd.pressplay.RentalForm;
import com.sparta.jd.pressplay.entities.RentalEntity;
import com.sparta.jd.pressplay.entities.ReservedEntity;

public class RentalService {
    public static RentalEntity makeRental(ReservedEntity reservation, RentalForm form) {
        RentalEntity rental = new RentalEntity();
        rental.setRentalDate(TimestampMaker.getCurrentDate());
        rental.setLastUpdate(TimestampMaker.getCurrentDate());
        rental.setInventoryId(reservation.getFilmID());
        rental.setCustomerId(form.getCustomerId());
        rental.setStaffId(form.getStaffId());
        rental.setReturnDate(TimestampMaker.getCurrentDatePlusDays(form.getDaysToBeRented()));
        return rental;
    }

    public static void updateRental(RentalEntity originalRental, RentalEntity updatedRental) {
        originalRental.setLastUpdate(TimestampMaker.getCurrentDate());
        originalRental.setReturnDate(updatedRental.getReturnDate());
        originalRental.setCustomerId(updatedRental.getCustomerId());
        originalRental.setStaffId(updatedRental.getStaffId());
        originalRental.setInventoryId(updatedRental.getInventoryId());
    }
}
