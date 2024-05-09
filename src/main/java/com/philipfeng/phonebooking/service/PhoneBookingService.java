package com.philipfeng.phonebooking.service;

import com.philipfeng.phonebooking.entity.PhoneBooking;
import com.philipfeng.phonebooking.entity.PhoneDetail;
import com.philipfeng.phonebooking.repository.PhoneBookingRepository;
import com.philipfeng.phonebooking.repository.PhoneDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneBookingService {

    @Autowired
    private PhoneDetailRepository phoneDetailRepository;

    @Autowired
    private PhoneBookingRepository phoneBookingRepository;

    public Optional<PhoneDetail> getPhoneDetailByPhoneId(Long phoneId){
        return phoneDetailRepository.findById(phoneId);
    }

    public List<PhoneBooking> getPhoneBookingByPhoneId(Long phoneId){
        return phoneBookingRepository.findByPhoneId(phoneId);
    }

    public void bookPhone(Long phoneId, String bookedBy) throws Exception {
        List<PhoneBooking> bookings = getPhoneBookingByPhoneId(phoneId);
        for (PhoneBooking b : bookings) {
            if (b.getReturnedAt() == null) {
                throw new Exception("Phone is already booked");
            }
        }
        PhoneBooking phoneBooking = PhoneBooking.builder()
                .phoneId(phoneId)
                .bookedAt(LocalDateTime.now())
                .bookedBy(bookedBy)
                .build();
        phoneBookingRepository.save(phoneBooking);
    }

    public void returnPhone(Long phoneId) throws Exception {
        List<PhoneBooking> bookings = getPhoneBookingByPhoneId(phoneId);
        if(bookings == null || bookings.isEmpty()
            || !bookings.stream().filter(b -> b.getReturnedAt() == null).findAny().isPresent()){
            throw new Exception("Phone is not booked yet");
        }
        bookings.stream().filter(b -> b.getReturnedAt() == null).findAny().ifPresent(b -> {
            b.setReturnedAt(LocalDateTime.now());
            phoneBookingRepository.save(b);
        });
    }
}
