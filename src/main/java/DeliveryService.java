import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static java.util.Objects.requireNonNull;

public class DeliveryService {
    private static final BigDecimal FIFTY = new BigDecimal(50);
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private static final BigDecimal TWO_HUNDRED = new BigDecimal(200);
    private static final BigDecimal THREE_HUNDRED = new BigDecimal(300);

    private static final BigDecimal MINIMAL_SUM = new BigDecimal(400);

    public static final String FRAGILE_EXCEPTION_TEXT = "Расстояние для хрупких грузов должно быть максимум 30 км";

    public static BigDecimal calculateCostDelivery(Long distance, Dimensions dimensions, Boolean isFragile, Workload workload) {

        validateParam(distance, dimensions, isFragile, workload);

        BigDecimal sum = new BigDecimal(0)
                .add(calculateTariffByFragile(isFragile, distance))
                .add(dimensions.getTariff())
                .add(calculateTariffByDistance(distance))
                .multiply(workload.getCoefficient())
                .setScale(2, HALF_UP);

        if (sum.compareTo(MINIMAL_SUM) < 1) {
            return MINIMAL_SUM;
        } else {
            return sum;
        }
    }

    public static BigDecimal calculateTariffByDistance(Long distance) {
        BigDecimal sum = new BigDecimal(0).setScale(2, HALF_UP);

        if (distance <= 0) {
            throw new IllegalArgumentException("");
        } else if (distance <= 2) {
            sum = sum.add(FIFTY);
        } else if (distance <= 10) {
            sum = sum.add(ONE_HUNDRED);
        } else if (distance <= 30) {
            sum = sum.add(TWO_HUNDRED);
        } else {
            sum = sum.add(THREE_HUNDRED);
        }

        return sum;
    }

    public static BigDecimal calculateTariffByFragile(Boolean isFragile, Long distance) {
        BigDecimal sum = new BigDecimal(0).setScale(2, HALF_UP);

        if (isFragile) {
            if (distance <= 30) {
                sum = sum.add(THREE_HUNDRED);
            } else {
                throw new IllegalArgumentException(FRAGILE_EXCEPTION_TEXT);
            }
        }

        return sum;
    }

    public static void validateParam(Long distance, Dimensions dimensions, Boolean isFragile, Workload workload) {
        requireNonNull(distance, "Ошибка валидации, 'distance' не может быть null");
        requireNonNull(dimensions, "Ошибка валидации, 'dimensions' не может быть null");
        requireNonNull(isFragile, "Ошибка валидации, 'isFragile' не может быть null");
        requireNonNull(workload, "Ошибка валидации, 'workload' не может быть null");

        if (distance <= 0) {
            throw new IllegalArgumentException("Значение параметра 'distance' должно быть больше 0");
        }
    }
}
