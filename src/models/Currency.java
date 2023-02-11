package models;

import annotations.KoliberDescription;
import annotations.KoliberFieldDescription;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@KoliberDescription(comment = "Waluta")
public class Currency {
    @KoliberFieldDescription(comment = "Kod")
    private String code;
}
