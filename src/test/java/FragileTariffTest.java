import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FragileTariffTest {

    private enum FragileTariff {
        FRAGILE_LESS_30_KM(true, 30L, new BigDecimal(300)),
        NOT_FRAGILE_LESS_30_KM(false, 30L, new BigDecimal(0));


        private final BigDecimal tariff;
        private final Long distance;
        private final Boolean isFragile;

        FragileTariff(Boolean isFragile, Long distance, BigDecimal tariff) {
            this.distance = distance;
            this.tariff = tariff;
            this.isFragile = isFragile;
        }

        public BigDecimal getTariff() {
            return tariff;
        }

        public Long getDistance() {
            return distance;
        }

        public Boolean isFragile(){
            return isFragile;
        }
    }

    @ParameterizedTest
    @EnumSource(value = FragileTariff.class)
    void checkCalculationByFragile(FragileTariff tariff){
       BigDecimal cost =  DeliveryService.calculateTariffByFragile(tariff.isFragile(), tariff.getDistance());
       assertThat(cost).isEqualByComparingTo(tariff.getTariff());
    }

    @Test
    void fragileMoreThan30kmTest(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                DeliveryService.calculateTariffByFragile(true, 31L));

        assertThat(exception).hasMessage("Расстояние для хрупких грузов должно быть максимум 30 км");
    }

}
