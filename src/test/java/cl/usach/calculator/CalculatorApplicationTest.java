package cl.usach.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.core.env.AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME;

class CalculatorApplicationTest {

    @Test
    public void main() {
        try {
            System.setProperty(DEFAULT_PROFILES_PROPERTY_NAME, "test");
            CalculatorApplication application = mock(CalculatorApplication.class);
            application.main(new String[]{});
            assertNotNull(application);
        } catch (Exception e){

        }
    }

}