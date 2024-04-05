package ru.netology.mipt.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.netology.mipt.project.util.Printable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Operation implements Printable, Serializable {
    private int id;
    private BigDecimal value;
    private LocalDateTime time;
    private int clientId;

    public void print(){
        System.out.println("Сумма операции : " + value + "; Время проведения операции: " + time);
    }
}
