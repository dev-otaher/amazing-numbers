package numbers;

import java.util.Objects;

public class NumberProperty {
    private Property property;
    private Sign sign;

    public NumberProperty(Property property) {
        this.property = property;
        this.sign = Sign.POSITIVE;

    }

    public NumberProperty(Property property, Sign sign) {
        this.property = property;
        this.sign = sign;
    }

    public Property getProperty() {
        return property;
    }

    public Sign getSign() {
        return sign;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        String sign = (this.sign.equals(Sign.POSITIVE) ? "" : "-");
        return sign + property.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.property);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberProperty that = (NumberProperty) o;
        return property == that.property && sign.equals(that.getSign());
    }
}


