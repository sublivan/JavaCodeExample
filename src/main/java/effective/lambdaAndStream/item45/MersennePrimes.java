package effective.lambdaAndStream.item45;

import java.math.BigInteger;
import java.util.stream.Stream;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;

/**
 * 메르센 소수 출력 프로그램
 * 메르센 수는 2^p -1 형태의 수
 * p가 소수이면 해당 메르센 수도 소수 일 수 있다. 이때의 수를 메르센 소수라한다.
 */
public class MersennePrimes {

    private static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }

    public static void main(String[] args) {
        primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                //.forEach(System.out::println);
                .forEach(mp -> System.out.println(mp.bitLength() + ": " + mp)); // 메르센 소수 앞에 지수 출력
    }
}
