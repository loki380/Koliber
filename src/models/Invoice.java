package models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Invoice {

    private String number;
    private BigDecimal amount;
    private Currency currency;
    private Contractor contractor;
}