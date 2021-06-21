import java.math.BigDecimal;

public enum Workload {
    NORMAL(1.0),
    MEDIUM(1.2),
    HIGH(1.4),
    VERY_HIGH(1.6);

    private final Double coefficient;

    Workload(Double coefficient) {
        this.coefficient = coefficient;
    }

    public BigDecimal getCoefficient() {
        return new BigDecimal(coefficient);
    }
    }
