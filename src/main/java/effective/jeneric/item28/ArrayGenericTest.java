package effective.jeneric.item28;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayGenericTest {

    @Test
    @DisplayName("배열은 공변")
    void array() {
        Object[] objectArray = new Long[1];
        objectArray[0] = "타입이 달라 넣을 수 없다."; // fail! Compile 통과, Long용 저장소에 String 값을 넣을 수 없어 런타임에 ArrayStoreException 발생
    }

    @Test
    @DisplayName("제네릭은 불공변")
    void generic() {
        //List<Object> ol = new ArrayList<Long>(); // fail! 호환되지 않는 타입, 컴파일 되지 않는다.
        //ol.("타입이 달라 넣을 수 없다.");
    }

    @Test
    @DisplayName("")
    void genericListExample() {
        List<String> list = new ArrayList<>(Arrays.asList("s1", "s2", "s3"));
        Chooser3<String> chooser = new Chooser3<>(list);

        Assertions.assertTrue(list.contains(chooser.choose()));
        //Assertions.assertTrue(list.contains("s4")); // fail!
    }
}
