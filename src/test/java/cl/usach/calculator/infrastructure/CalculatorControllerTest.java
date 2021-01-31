package cl.usach.calculator.infrastructure;

import cl.usach.calculator.entity.Request;
import cl.usach.calculator.entity.Response;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {"classpath:application.properties"},properties = "management.port=0")
class CalculatorControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @InjectMocks
    CalculatorController controller;

    @Test
    public void calculaIva() throws Exception {

        Request request = new Request();
        request.setMonto("1000");

        ResponseEntity<Response> response = restTemplate.postForEntity(
                new URL("http://localhost:" + port + "/calcula-iva").toString(), request, Response.class);
        assertEquals("1000", response.getBody().getNeto());
        assertEquals("190", response.getBody().getIva());
        assertEquals("1190", response.getBody().getTotal());

    }

    @Test
    void calculaIvaException() {
        try{
            controller.calculaIva(null);
        } catch(Exception e){
            assertEquals("Error Desconocido", e.getMessage());
        }
    }

}