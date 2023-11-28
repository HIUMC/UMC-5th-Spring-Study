package dbpractice.dbhomework.controller;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter

public class BookingForm {

//    모르겠는 점 : Booking의 Customer, Hotel, HotelRoom은...??

    @NotEmpty(message = "예약 날짜 선정은 필수입니다.")
    private Date bookingcheckindate;
    private Date bookingcheckoutdate;
}
