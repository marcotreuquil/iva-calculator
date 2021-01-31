package cl.usach.calculator.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String neto;
    private String iva;
    private String total;
}
