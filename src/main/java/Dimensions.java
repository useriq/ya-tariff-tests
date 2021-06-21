import java.math.BigDecimal;

public enum Dimensions {
    SMALL(new BigDecimal(100)),
    BIG(new BigDecimal(200));

    private final BigDecimal tariff;

    Dimensions(BigDecimal tariff) {
        this.tariff = tariff;
    }

    public BigDecimal getTariff() {
        return tariff;
    }
}
