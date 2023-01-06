package effective.jeneric.item30;

import java.util.function.UnaryOperator;

public class GenericSingleton {
    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    /**
     * 항등함수란 입력 값을 수정 없이 그대로 반환하는 특성을 가진 함수이므로,
     * T가 어떤 타입이든 타입 안전하다.
     * @return
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    public static void main(String[] args) {
        String[] strings = {"봄", "여름", "가을", "겨울"};
        UnaryOperator<String> sameString = identityFunction();
        for (String s : strings)
            System.out.println("s = " + s);

        Number[] numbers = {1, 2.0, 3L};
        UnaryOperator<Number> sameNumber = identityFunction();
        for (Number n : numbers)
            System.out.println("n = " + n);
    }

}
