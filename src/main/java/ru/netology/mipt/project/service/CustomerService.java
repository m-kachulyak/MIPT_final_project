package ru.netology.mipt.project.service;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.netology.mipt.project.exceptions.CustomerNotFoundException;
import ru.netology.mipt.project.model.Customer;
import ru.netology.mipt.project.model.CustomerDTO;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final StorageService<Customer> customerStorage;

    public CustomerService(StorageService<Customer> customerStorage) {
        this.customerStorage = customerStorage;
    }

    @PostConstruct
    public void initStorage() {
        customerStorage.addData(new Customer(1, "Spring123", "spring"));
        customerStorage.addData(new Customer(2, "Boot123", "boot"));

    }

    public Customer addCustomer(String name, String login) {
        int maxId = findAll().stream()
                .map(Customer::getId)
                .max(Integer::compare).orElse(0);
        Customer customer = new Customer(maxId + 1, name, login);
        customerStorage.addData(customer);
        return customer;
    }
    public Customer findCustomer(int customerId) throws CustomerNotFoundException {
        Optional<Customer> first = customerStorage.getAllData().stream().filter(c -> c.getId() == customerId).findFirst();
        if (first.isPresent()) {
            return first.get();
        } else throw new CustomerNotFoundException();
    }

    public Customer updateCustomer(CustomerDTO customer) throws CustomerNotFoundException {
        Customer customer1 = findCustomer(customer.getId());
        customer1.setName(customer.getName());
        return customer1;
    }

    public List<Customer> findAll() {
        return customerStorage.getAllData();
    }

    public void deleteCustomer(int id) throws CustomerNotFoundException {
        Customer customer = findCustomer(id);
        customerStorage.deleteData(customer);
    }


}
