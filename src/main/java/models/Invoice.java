package models;

import annotations.KoliberDescription;
import annotations.KoliberFieldDescription;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@KoliberDescription(comment = "Opis")
public class Invoice {

    @KoliberFieldDescription(comment = "Numer")
    private String number;
    @KoliberFieldDescription(comment = "Kwota", priority = 2)
    private BigDecimal amount;
    @KoliberDescription(comment = "Waluta", priority = 3)
    private Currency currency;
    @KoliberDescription(comment = "Kontrahent", priority = 1)
    private Contractor contractor;
}