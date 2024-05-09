package com.philipfeng.phonebooking.repository;

import com.philipfeng.phonebooking.entity.PhoneBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneBookingRepository extends JpaRepository<PhoneBooking, Long> {
    List<PhoneBooking> findByPhoneId(Long phoneId);
}
