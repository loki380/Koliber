package models;

import annotations.KoliberDescription;
import annotations.KoliberFieldDescription;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@KoliberDescription(comment = "Kontrahent")
public class Contractor {
    @KoliberFieldDescription(comment = "Nazwa")
    private String name;
    @KoliberFieldDescription(comment = "NIP")
    private String nip;
    @KoliberDescription(comment = "Adresy")
    private List<Address> addresses;
}
