package ru.netology.mipt.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.mipt.project.model.CustomersGetResponse;
import ru.netology.mipt.project.model.CustomerDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @Test
    public void getClientsTest(){
        CustomersGetResponse customers = customerController.getCustomers();
        CustomerDTO customer1 = customers.getCustomers().get(0);

        assertEquals(1, customer1.getId());
        assertEquals("Spring123", customer1.getName());
    }

}