package com.sparta.jd.pressplay.controllers;

import com.sparta.jd.pressplay.RentalForm;
import com.sparta.jd.pressplay.entities.CustomerEntity;
import com.sparta.jd.pressplay.entities.RentalEntity;
import com.sparta.jd.pressplay.entities.ReservedEntity;
import com.sparta.jd.pressplay.entities.UserEntity;
import com.sparta.jd.pressplay.repositories.*;
import com.sparta.jd.pressplay.services.CustomerService;
import com.sparta.jd.pressplay.services.RentalService;
import com.sparta.jd.pressplay.services.TimestampMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.InvalidParameterException;

@Controller
public class StaffController {
    private final CustomerRepository CUSTOMER_REPOSITORY;
    private final FilmRepository FILM_REPOSITORY;
    private final StoreAddressRepository STORE_REPOSITORY;
    private final RentalRepository RENTAL_REPOSITORY;
    private final ReservedRepository RESERVED_REPOSITORY;
    private final UserRepository USER_REPOSITORY;

    @Autowired
    public StaffController(FilmRepository filmRepository, StoreAddressRepository storeRepository,
                              RentalRepository rentalRepository, CustomerRepository customerRepository,
                              ReservedRepository reservedRepository, UserRepository userRepository) {
        this.CUSTOMER_REPOSITORY = customerRepository;
        this.FILM_REPOSITORY = filmRepository;
        this.STORE_REPOSITORY = storeRepository;
        this.RENTAL_REPOSITORY = rentalRepository;
        this.RESERVED_REPOSITORY = reservedRepository;
        this.USER_REPOSITORY = userRepository;
    }

    @GetMapping("/staff-room")
    public String goToStaffRoom() {
        return "staff/staff-room";
    }

    //Rent////////////////////////////////////////

    @GetMapping("/rent/checkout/reserved")
    public String goToRentCheckoutReserved(Model model) {
        model.addAttribute("filmsData", RESERVED_REPOSITORY.findAll());
        return "staff/rent/rent-checkout-reserved";
    }

    @GetMapping("/rent/checkout/reserved/{id}")
    public String checkoutFilm(@PathVariable Integer id, Model model) {
        RentalForm form = new RentalForm();
        model.addAttribute("form", form);
        model.addAttribute("id", id);
        return "staff/rent/rent-checkout-details";
    }

    @PostMapping("/rent/checkout/complete/{id}")
    public String finishCheckout(@ModelAttribute RentalForm form, @PathVariable Integer id) {
        ReservedEntity reservation = RESERVED_REPOSITORY.findById(id).orElseThrow(()-> new InvalidParameterException("No reservation with ID: " + id));
        RentalEntity rental = new RentalEntity();
        RENTAL_REPOSITORY.save(RentalService.makeRental(reservation, form));
        return "staff/confirmation";
    }

    @GetMapping("/rent/edit")
    public String goToRentEdit(Model model) {
        model.addAttribute("rentData", RENTAL_REPOSITORY.findAll());
        return "staff/rent/rent-picker";
    }

    @GetMapping("/rent/edit/{id}")
    public String rentEditFields(@PathVariable Integer id, Model model) {
        RentalEntity rental = RENTAL_REPOSITORY.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Rental ID: " + id));
        model.addAttribute("rental", rental);
        return "staff/rent/update-rental";
    }

    @PostMapping("/rent-updater/{id}")
    public String updateRental(@ModelAttribute RentalEntity rental, @PathVariable("id") Integer id) {
        RentalEntity rentalEntity = RENTAL_REPOSITORY.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Rental ID: " + id));
        RentalService.updateRental(rentalEntity, rental);
        RENTAL_REPOSITORY.save(rentalEntity);
        return "staff/confirmation";
    }

    @GetMapping("/rent/check-in")
    public String goToRentCheckIn(Model model) {
        model.addAttribute("rentData", RENTAL_REPOSITORY.findAll());
        return "staff/rent/rent-checker-inner";
    }

    @GetMapping("/rent/check-inner/{id}")
    public String checkInRental(@PathVariable Integer id) {
        RentalEntity rentalEntity = RENTAL_REPOSITORY.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Rental ID: " + id));
        rentalEntity.setReturnDate(TimestampMaker.getCurrentDate());
        rentalEntity.setLastUpdate(TimestampMaker.getCurrentDate());
        return "staff/confirmation";
    }

    //////////////////////////////////////////////
    //Users///////////////////////////////////////

    @GetMapping("/add/user")
    public String goToUserAdditionPageIdless(Model model) {
        UserEntity userEntity = new UserEntity();
        model.addAttribute("user", userEntity);
        return "staff/users/add-user";
    }

    @GetMapping("/edit/user")
    public String goToUserEdit(Model model) {
        model.addAttribute("userData", USER_REPOSITORY.findAll());
        return "staff/users/user-picker";
    }

    @GetMapping("/edit/user/{id}")
    public String userEditFields(@PathVariable Integer id, Model model) {
        UserEntity user = USER_REPOSITORY.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid User ID: " + id));
        model.addAttribute("user", user);
        return "staff/users/update-user";
    }

    @PostMapping("/user-updater/{id}")
    public String updateUser(@ModelAttribute UserEntity user, @PathVariable("id") Integer id) {
        UserEntity userEntity = USER_REPOSITORY.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid User ID: " + id));
        CustomerService.updateUser(userEntity, user);
        USER_REPOSITORY.save(userEntity);
        return "staff/confirmation";
    }

    @PostMapping("/user-adder")
    public String saveUser(@ModelAttribute("customer") UserEntity user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        CustomerEntity customer = CUSTOMER_REPOSITORY.findById(user.getCustomerId()).orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID!" + user.getCustomerId()));
        user.setEmail(customer.getEmail());
        user.setPassword(encoder.encode(user.getPassword()));
        USER_REPOSITORY.save(user);
        return "staff/confirmation";
    }

    //////////////////////////////////////////////
    //Customers///////////////////////////////////

    @GetMapping("/add/customer")
    public String goToCustomerAdditionPage(Model model) {
        CustomerEntity customerEntity = new CustomerEntity();
        model.addAttribute("customer", customerEntity);
        return "staff/add-customer";
    }

    @GetMapping("/edit/customer")
    public String goToCustomerEdit(Model model) {
        model.addAttribute("customerData", CUSTOMER_REPOSITORY.findAll());
        return "staff/customer-picker";
    }

    @GetMapping("/edit/customer/{id}")
    public String customerEditFields(@PathVariable Integer id, Model model) {
        CustomerEntity customer = CUSTOMER_REPOSITORY.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID: " + id));
        model.addAttribute("customer", customer);
        return "staff/update-customer";
    }

    @PostMapping("/customer-updater/{id}")
    public String updateCustomer(@ModelAttribute CustomerEntity customer, @PathVariable("id") Integer id) {
        CustomerEntity customerEntity = CUSTOMER_REPOSITORY.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID: " + id));
        CustomerService.updateCustomer(customerEntity, customer);
        CUSTOMER_REPOSITORY.save(customerEntity);
        return "staff/staff-room";
    }

    @PostMapping("/customer-adder")
    public String saveCustomer(@ModelAttribute("customer") CustomerEntity customer) {
        customer.setStoreId(1);
        CUSTOMER_REPOSITORY.save(customer);
        return "staff/staff-room";
    }

    //////////////////////////////////////////////
    //Films///////////////////////////////////////

    @GetMapping("/add/film")
    public String goToFilmAdd() {
        return "staff/add-film";
    }

    @GetMapping("/edit/film")
    public String goToFilmEdit() {
        return "staff/edit-film";
    }

    @GetMapping("/remove/film")
    public String goToFilmDelete() {
        return "staff/remove-film";
    }

    //////////////////////////////////////////////
    //Stores//////////////////////////////////////

    @GetMapping("/add/store")
    public String goToStoreAdd() {
        return "staff/add-store";
    }

    @GetMapping("/edit/store")
    public String goToStoreEdit() {
        return "staff/edit-store";
    }

    @GetMapping("/remove/store")
    public String goToStoreDelete() {
        return "staff/delete-store";
    }


}
