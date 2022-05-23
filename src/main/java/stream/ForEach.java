package stream;

import java.util.Arrays;

public class ForEach {

    public static void main(String[] args) {
        String[] strArr = {"a2","a1","b1","c1","c3","c2","e1","d2","a3"};

        Arrays.stream(strArr).forEach(str -> System.out.print(str + " "));

    }
}
