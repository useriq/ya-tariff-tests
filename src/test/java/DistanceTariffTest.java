import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class DistanceTariffTest {
    private enum Tariff {
        LESS_2_KM(2L, new BigDecimal(50)),
        LESS_10_KM(10L, new BigDecimal(100)),
        LESS_30_KM(30L, new BigDecimal(200)),
        MORE_30_KM(31L, new BigDecimal(300));


        private final BigDecimal tariff;
        private final Long distance;

        Tariff(Long distance, BigDecimal tariff) {
            this.distance = distance;
            this.tariff = tariff;
        }

        public BigDecimal getTariff() {
            return tariff;
        }

        public Long getDistance() {
            return distance;
        }
    }

    @ParameterizedTest
    @EnumSource(value = Tariff.class)
    void checkCalculationByDistance(Tariff tariff) {
        Long distance = tariff.getDistance();
        BigDecimal cost = DeliveryService.calculateTariffByDistance(distance);
        assertThat(cost).as("Стоимость доставки до %s км", distance).isEqualByComparingTo(tariff.getTariff());
    }
}
