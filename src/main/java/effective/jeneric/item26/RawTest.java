package effective.jeneric.item26;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RawTest {

    @DisplayName("런타임 실패 - unsafeAdd 메서드가 로 타입(List) 사용")
    @Test
    void rawType() {
        Assertions.assertThrows(ClassCastException.class, () -> {
            List<String> strings = new ArrayList<>();
            unsafeAdd(strings, Integer.valueOf(42));
            String s = strings.get(0);  // 컴파일러가 자동으로 형변환 코드를 넣어준다. Integer를 String으로 변환 시도
        });
    }

    void unsafeAdd(List list, Object o) {
        list.add(o);
    }

    @DisplayName("비한정적 와일드 카드 타입")
    @Test
    void unboundedWildcard() {
        Set<String> s1 = Set.of("s1", "s2", "s3");
        Set<String> s2 = Set.of("s2", "s4", "s5");
        Assertions.assertEquals(1, numElementsInCommon(s1, s2));
    }

    int numElementsInCommon(Set<?> s1, Set<?> s2) { // 타입 안전하며 유연하다.
        int result = 0;

        for(Object o1 : s1) {
            if (s2.contains(o1)) {
                result++;
            }
        }
        return result;
    }

}
