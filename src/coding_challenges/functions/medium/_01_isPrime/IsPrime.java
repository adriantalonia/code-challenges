package coding_challenges.functions.medium._01_isPrime;

public class IsPrime {
    public boolean isPrime(int value) {
        if (value <= 1) return false;
        if (value == 2) return true;
        if (value % 2 == 0) return false;

        for (int i = 3; (long) i * i <= value; i += 2) {
            if (value % i == 0) {
                return false;
            }
        }

        return true;
    }

    /** Runs the assertion-based unit tests when this class is executed directly. */
    public static void main(String[] args) {
        IsPrime isPrime = new IsPrime();

        testValuesBelowTwo(isPrime);
        testSmallPrimes(isPrime);
        testCompositeNumbers(isPrime);
        testLargerValues(isPrime);

        System.out.println("All IsPrime tests passed.");
    }

    private static void testValuesBelowTwo(IsPrime isPrime) {
        assertPrime(isPrime, Integer.MIN_VALUE, false, "the smallest int");
        assertPrime(isPrime, -7, false, "a negative number");
        assertPrime(isPrime, 0, false, "zero");
        assertPrime(isPrime, 1, false, "one");
    }

    private static void testSmallPrimes(IsPrime isPrime) {
        assertPrime(isPrime, 2, true, "the only even prime");
        assertPrime(isPrime, 3, true, "the smallest odd prime");
        assertPrime(isPrime, 5, true, "a small prime");
        assertPrime(isPrime, 11, true, "a prime after several candidates");
    }

    private static void testCompositeNumbers(IsPrime isPrime) {
        assertPrime(isPrime, 4, false, "an even composite");
        assertPrime(isPrime, 9, false, "a perfect square");
        assertPrime(isPrime, 15, false, "an odd composite");
        assertPrime(isPrime, 49, false, "a larger perfect square");
    }

    private static void testLargerValues(IsPrime isPrime) {
        assertPrime(isPrime, 97, true, "a larger prime");
        assertPrime(isPrime, 7919, true, "a larger prime near the limit");
        assertPrime(isPrime, 10000, false, "a larger even composite");
        assertPrime(isPrime, Integer.MAX_VALUE, true, "the largest int");
    }

    private static void assertPrime(
            IsPrime isPrime, int value, boolean expected, String description) {
        boolean actual = isPrime.isPrime(value);

        if (actual != expected) {
            throw new AssertionError(
                    description + " failed: expected " + expected + " but was " + actual);
        }
    }
}
