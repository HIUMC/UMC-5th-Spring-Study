package dbpractice.dbhomework.controller;

import dbpractice.dbhomework.domain.Hotelier;
import dbpractice.dbhomework.repository.HotelierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/hoteliers")
@RestController
@RequiredArgsConstructor
public class HotelierController {
    public final HotelierRepository hotelierRepository;

    //    홈화면에서 hotelier의 리스트를 조회하려고 할 때..?!
    @GetMapping("/hoteliers")
    public String list(Model model) {
        List<Hotelier> hoteliers = hotelierRepository.findAll();
        model.addAttribute("hoteliers", hoteliers);
        return "/hoteliers/hotelierlist";
    }
}
