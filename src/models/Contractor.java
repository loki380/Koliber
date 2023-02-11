package models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Contractor {
    private String name;
    private String nip;
    private List<Address> addresses;
}
