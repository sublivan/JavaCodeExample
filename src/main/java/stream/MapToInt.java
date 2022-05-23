package stream;

import java.util.Arrays;

public class MapToInt {

    public static void main(String[] args) {

        String str = "11 22 33 44 55";
        int[] intArr = stringToIntArray(str);
        System.out.println("stringToIntArr = " + Arrays.toString(intArr));

    }

    /**
     * 문자열을 int 배열로 변환하기
     * @param str
     * @return int[]
     */
    public static int[] stringToIntArray(String str) {
        String[] strArr = str.split(" ");
        return Arrays.stream(strArr).mapToInt(Integer::parseInt).toArray();
    }
}
