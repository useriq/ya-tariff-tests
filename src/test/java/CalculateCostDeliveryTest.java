import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculateCostDeliveryTest {

    @Test
    void successCalculate() {
        BigDecimal cost = DeliveryService.calculateCostDelivery(8L, Dimensions.BIG, true, Workload.HIGH);
        assertThat(cost).isEqualByComparingTo(BigDecimal.valueOf((100 + 200 + 300) * 1.4).setScale(2, BigDecimal.ROUND_UP));
    }

    @Test
    void fragileExceptions() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                DeliveryService.calculateCostDelivery(40L, Dimensions.BIG, true, Workload.HIGH));

        assertThat(exception).hasMessage("Расстояние для хрупких грузов должно быть максимум 30 км");
    }
}
