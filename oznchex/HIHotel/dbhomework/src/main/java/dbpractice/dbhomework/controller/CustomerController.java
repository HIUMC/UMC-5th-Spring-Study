package dbpractice.dbhomework.controller;

import dbpractice.dbhomework.domain.Address;
import dbpractice.dbhomework.domain.Customer;
import dbpractice.dbhomework.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/customers")
@RestController
@RequiredArgsConstructor
public class CustomerController {
//    원래 서비스가 위치하는 자리..?
    private final CustomerRepository customerRepository;

//    HTTP Get Method에 해당하는 단축 표현으로 서버의 리소스를 조회할 때 사용한다.
    @GetMapping(value = "/customers/new")
    public String createForm(Model model) {
        model.addAttribute("customerForm", new CustomerForm());
        return "customers/createCustomerForm";
    }

//    HTTP Post Method에 해당하는 단축 표현으로 서버에 리소스를 등록(저장)할 때 사용한다.
    @PostMapping("/customers/new")
    public String create(@Valid CustomerForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "customers/createCustomerForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Customer customer = new Customer();
        customer.setName(form.getName());
        customer.setAddress(address);

        customerRepository.save(customer);
        return "redirect:/";
    }


//    홈화면에서 customer 리스트를 조회하려고 할 때..?!
    @GetMapping(value = "/customers")
    public String list(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "/customers/customerList";
    }
}
