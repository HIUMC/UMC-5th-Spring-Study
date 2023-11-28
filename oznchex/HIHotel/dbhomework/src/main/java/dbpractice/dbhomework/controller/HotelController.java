package dbpractice.dbhomework.controller;

import dbpractice.dbhomework.domain.Hotel;
import dbpractice.dbhomework.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/hotels")
@RestController
@RequiredArgsConstructor
public class HotelController {
    private final HotelRepository hotelRepository;

//    홈화면에서 hotel의 리스트를 조회하려고 할 때..?!
    @GetMapping(value = "/hotels")
    private String list(Model model) {
        List<Hotel> hotels =hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        return "/hotels/hotelList";
    }
}
