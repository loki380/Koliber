package models;

import annotations.KoliberDescription;
import annotations.KoliberFieldDescription;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@KoliberDescription(comment = "Adres")
public class Address {

    @KoliberFieldDescription(comment = "Miasto")
    private String city;
    @KoliberFieldDescription(comment = "Kod pocztowy")
    private String postalCode;
    @KoliberFieldDescription(comment = "Ulica")
    private String street;
}
