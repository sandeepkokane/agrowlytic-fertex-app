package com.boot.customer.controller;

import com.boot.customer.model.dto.CustomerDTO;
import com.boot.customer.service.CustomerExcelGenerator;
import com.boot.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.boot.customer.service.ICustomerService.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping
    public String getAllCustomers(Map<String, Object> map) {
        map.put(CUSTOMER_LIST, customerService.getAllCustomers());
        return "customers/customers";
    }

    @GetMapping("/new")
    public String newCustomerView(Map<String, Object> map) {
        map.put(CUSTOMER, new CustomerDTO());
        return "customers/new-customer";
    }

    @PostMapping
    public String saveCustomer(@ModelAttribute(CUSTOMER) CustomerDTO customerDTO,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "customers/new-customer";
        }
        customerService.saveCustomer(customerDTO);
        return "customers/customers";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute(CUSTOMER) CustomerDTO customerDTO) {
        customerService.updateCustomer(id, customerDTO);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

//    @GetMapping("/search")
//    public ResponS

    @GetMapping("/download")
    public String downloadAllCustomers(HttpServletResponse response) throws IOException {

        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
        if (CollectionUtils.isEmpty(customerDTOList)) {
            log.error("You dont have any customers");
        } else {
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + CUSTOMER_FILENAME;
            response.setHeader(headerKey, headerValue);
            final CustomerExcelGenerator generator = new CustomerExcelGenerator(customerDTOList);
            generator.generateExcelFile(response);
        }
        return "redirect:/customers";
    }
}
