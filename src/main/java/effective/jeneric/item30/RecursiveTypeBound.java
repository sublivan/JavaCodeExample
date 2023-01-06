package effective.jeneric.item30;

import java.util.*;

public class RecursiveTypeBound {

    public static <E extends Comparable<E>> E max(Collection<E> c) throws IllegalArgumentException {
        if (c.isEmpty()) {
            throw new IllegalArgumentException("컬렉션이 비어있음");
        }
        E result = null;
        for (E e : c) {
            if ((result == null) || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e); //requireNonNull() 널체크를 위한 메소드
            }
        }
        return result;
    }

    public static void main(String[] args) throws IllegalAccessException {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 200, 300, 40, 7000));
        List<String> strings = Arrays.asList("가", "하", "마바사아자차", "카타아", "타마");
        List<Integer> emptyList = new ArrayList<>();

        System.out.println("max(numbers) = " + max(numbers));
        System.out.println("max(strings) = " + max(strings));
        //System.out.println("emptyList = " + max(emptyList));
    }
}
