package com.philipfeng.phonebooking.controller;


import com.philipfeng.phonebooking.entity.PhoneBooking;
import com.philipfeng.phonebooking.entity.PhoneDetail;
import com.philipfeng.phonebooking.request.PhoneBookRequest;
import com.philipfeng.phonebooking.request.PhoneReturnRequest;
import com.philipfeng.phonebooking.response.PhoneBookResponse;
import com.philipfeng.phonebooking.service.PhoneBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phone")
public class PhoneBookingController {

    private final PhoneBookingService phoneBookingService;

    public PhoneBookingController(PhoneBookingService phoneBookingService){
        this.phoneBookingService = phoneBookingService;
    }

    @GetMapping("/{phoneId}")
    public ResponseEntity<PhoneBookResponse> getPhoneById(@PathVariable(value = "phoneId") Long phoneId) {
        Optional<PhoneDetail> phoneDetailOpt = phoneBookingService.getPhoneDetailByPhoneId(phoneId);
        if(!phoneDetailOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }else{
            PhoneBookResponse response = PhoneBookResponse.builder().phoneDetail(phoneDetailOpt.get()).build();
            List<PhoneBooking> bookings = phoneBookingService.getPhoneBookingByPhoneId(phoneId);
            if(bookings == null || bookings.isEmpty()){
                response.setIsAvailable(true);
            }else{
                response.setIsAvailable(true);
                bookings.stream().forEach(b -> {
                    if(b.getReturnedAt() == null){
                        response.setIsAvailable(false);
                        response.setBookedBy(b.getBookedBy());
                    }
                });
            }
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/book")
    public void bookPhone(@RequestBody PhoneBookRequest request) throws Exception {
        phoneBookingService.bookPhone(request.getPhoneId(), request.getBookedBy());
    }

    @PostMapping("/return")
    public ResponseEntity bookPhone(@RequestBody PhoneReturnRequest request) {
        try{
            phoneBookingService.returnPhone(request.getPhoneId());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
