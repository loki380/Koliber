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
    @KoliberFieldDescription(comment = "Kwota")
    private BigDecimal amount;
    @KoliberDescription(comment = "Waluta")
    private Currency currency;
    @KoliberDescription(comment = "Kontrahent")
    private Contractor contractor;
}