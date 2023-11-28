package dbpractice.dbhomework.controller;

import dbpractice.dbhomework.domain.HotelRoom;
import dbpractice.dbhomework.repository.HotelRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/hotelrooms")
@RestController
@RequiredArgsConstructor
public class HotelRoomController {
    public final HotelRoomRepository hotelRoomRepository;

    //    홈화면에서 hotelroom의 리스트를 조회하려고 할 때..?!
    @GetMapping("/hotelrooms")
    public String list(Model model){
        List<HotelRoom> hotelrooms = hotelRoomRepository.findAll();
        model.addAttribute("hotelrooms", hotelrooms);
        return "/hotelrooms/hotelroomsList";
    }

}