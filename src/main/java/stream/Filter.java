package stream;

import java.util.Arrays;

public class Filter {

    public static void main(String[] args) {

        String[] strArr = {"a2","a1","b1","c1","c3","c2","e1","d2","a3"};
        String[] collectAType = collectA(strArr);

        System.out.println("collectAType = " + Arrays.toString(collectAType));
        collectAToString(strArr);

    }


    public static String[] collectA(String[] strArr) {
        return Arrays.stream(strArr).filter(str -> str.startsWith("a")).toArray(String[]::new);
    }

    public static void collectAToString(String[] strArr) {
        Arrays.stream(strArr).filter(str -> str.startsWith("a")).forEach(str -> System.out.print(str + " "));
    }
}
