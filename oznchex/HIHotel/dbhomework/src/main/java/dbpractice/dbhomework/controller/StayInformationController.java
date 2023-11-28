package dbpractice.dbhomework.controller;


import dbpractice.dbhomework.domain.StayInformation;
import dbpractice.dbhomework.repository.StayInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/stayinfomations")
@RestController
@RequiredArgsConstructor
public class StayInformationController {
    public final StayInformationRepository stayInformationRepository;

    @GetMapping("/stayinformations")
    public String list(Model model){
        List<StayInformation> stayinformations = stayInformationRepository.findAll();
        model.addAttribute("stayinformations", stayinformations);
        return "/stayinformations/stayinformationList";
    }

}
