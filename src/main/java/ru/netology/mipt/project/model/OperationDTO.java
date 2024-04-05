package ru.netology.mipt.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OperationDTO implements Serializable {

    private int id;
    private BigDecimal value;
    private LocalDateTime time;
}
