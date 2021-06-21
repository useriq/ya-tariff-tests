import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateParamTest {
    @Test
    void negativeDistanceTest(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                DeliveryService.validateParam(-21L,Dimensions.BIG,true, Workload.HIGH));

        assertThat(exception).hasMessage("Значение параметра 'distance' должно быть больше 0");
    }
}
