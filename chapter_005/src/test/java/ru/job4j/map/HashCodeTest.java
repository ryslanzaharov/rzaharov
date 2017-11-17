package ru.job4j.map;

public class HashCodeTest {

    private boolean booleanValue = true;
    private char charValue = 'd';
    private String stringValue = "TestClass";
    private long longValue = 34829245849498300l;
    private float floatValue = 345832400.93f;
    private double doubleValue = 98584292348454.9834;
    private byte[] arrayValue = {1, 2, 3};

    public int hashCode() {
        int result = 17;
        result = 31 * result + (booleanValue ? 1 : 0);
        result = 31 * result + (int) charValue;
        result = 31 * result + (stringValue == null ? 0 : stringValue.hashCode());
        result = 31 * result + (int) (longValue ^ (longValue >>> 32));
        result = 31 * result + Float.floatToIntBits(floatValue);
        long longV = Double.doubleToLongBits(doubleValue);
        result = 31 * result + (int) (longV ^ (longV >>> 32));
        for (byte b : arrayValue) {
            result = 31 * result + (int)b;
        }
        return result;
    }
}
