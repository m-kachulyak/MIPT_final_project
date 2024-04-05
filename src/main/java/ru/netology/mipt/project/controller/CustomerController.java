package ru.netology.mipt.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.mipt.project.exceptions.CustomerNotFoundException;
import ru.netology.mipt.project.model.Customer;
import ru.netology.mipt.project.model.CustomersGetResponse;
import ru.netology.mipt.project.model.CustomerDTO;
import ru.netology.mipt.project.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "mipt/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public CustomersGetResponse getCustomers() {
        List<Customer> customers = customerService.findAll();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(this::customerToDTO)
                .collect(Collectors.toList());
        return new CustomersGetResponse(customerDTOS);
    }

    @GetMapping("/find/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") int id) throws CustomerNotFoundException {
        Customer customer = customerService.findCustomer(id);
        return customerToDTO(customer);
    }

    @PostMapping("/add")
    public CustomerDTO addNewCustomer(@RequestParam String name, @RequestParam String login) {
        Customer customer = customerService.addCustomer(name, login);
        return customerToDTO(customer);
    }

    @PutMapping("/update")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customer) throws CustomerNotFoundException {
        customerService.updateCustomer(customer);
        return customer;
    }

    @DeleteMapping("/del")
    public void deleteCustomer(@RequestBody String customerId) throws CustomerNotFoundException {
        customerService.deleteCustomer(Integer.parseInt(customerId));
    }

    private CustomerDTO customerToDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName());
    }
}
