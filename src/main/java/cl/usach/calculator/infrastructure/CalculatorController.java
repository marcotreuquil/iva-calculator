package cl.usach.calculator.infrastructure;

import cl.usach.calculator.entity.Request;
import cl.usach.calculator.entity.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CalculatorController {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @PostMapping(value = "/calcula-iva")
    public ResponseEntity<Response> calculaIva(@RequestBody Request request) {

        Response response = new Response();
        try {
            Integer valor = Integer.parseInt(request.getMonto());
            Integer iva = ((Double)(valor * 0.25)).intValue();
            Integer total = valor + iva;
            response.setNeto(request.getMonto());
            response.setIva(iva.toString());
            response.setTotal(total.toString());
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
