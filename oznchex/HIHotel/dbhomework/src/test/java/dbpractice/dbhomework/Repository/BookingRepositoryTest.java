package dbpractice.dbhomework.Repository;

import dbpractice.dbhomework.domain.Booking;
import dbpractice.dbhomework.domain.Customer;
import dbpractice.dbhomework.domain.Hotel;
import dbpractice.dbhomework.domain.HotelRoom;
import dbpractice.dbhomework.repository.BookingRepository;
import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingRepositoryTest extends TestCase {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void saveBooking(){
        // given
        Customer customer = new Customer();
        Hotel hotel = new Hotel();
        HotelRoom hotelRoom = new HotelRoom();

        //
        Booking booking = new Booking(1L, customer, hotel, hotelRoom, 2020/10/16, 2020/11/17);

        // when
        Booking savedBooking = bookingRepository.save(booking);
        // then

        Assertions.assertThat(booking).isSameAs(savedBooking);
    }
}