import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculateCostDeliveryTest {

    @Test
    void successCalculate() {
        BigDecimal cost = DeliveryService.calculateCostDelivery(8L, Dimensions.BIG, true, Workload.HIGH);

        BigDecimal expectedCost = valueOf(100)
                .add(valueOf(200))
                .add(valueOf(300))
                .multiply(valueOf(1.4)).setScale(2, BigDecimal.ROUND_UP);

        assertThat(cost).isEqualByComparingTo(expectedCost);
    }

    @Test
    void fragileExceptions() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                DeliveryService.calculateCostDelivery(40L, Dimensions.BIG, true, Workload.HIGH));

        assertThat(exception).hasMessage("Расстояние для хрупких грузов должно быть максимум 30 км");
    }
}
