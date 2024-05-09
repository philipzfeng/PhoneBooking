package com.philipfeng.phonebooking.response;


import com.philipfeng.phonebooking.entity.PhoneDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneBookResponse {
    private PhoneDetail phoneDetail;

    private Boolean isAvailable;

    private String bookedBy;
}
