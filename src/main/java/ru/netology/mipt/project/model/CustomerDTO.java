package ru.netology.mipt.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CustomerDTO implements Serializable {
    private  int id;
    private  String name;
}
