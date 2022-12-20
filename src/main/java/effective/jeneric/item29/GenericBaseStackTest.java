package effective.jeneric.item29;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class GenericBaseStackTest {

    @Test
    @DisplayName("제네릭 기반 스택")
    void stackTest() {
        // given
        GenericBaseStack<String> stack = new GenericBaseStack<>();
        List<String> strings = new ArrayList<>(Arrays.asList("s1", "s2", "s3", "s4"));

        // when
        strings.stream().forEach(s -> { stack.push(s); });

        // then
        while (!stack.isEmpty()) {
            Assertions.assertTrue(strings.contains(stack.pop())); // success!
            // Assertions.assertTrue(strings.contains(stack.pop().toUpperCase())); // fail!
        }
    }

    @Test
    @DisplayName("제네릭 Stack 클래스 사용하기")
    void stackTest2() {
        // given
        GenericBaseStack<List<String>> stack = new GenericBaseStack<>();
        List<String> strings = new ArrayList<>(Arrays.asList("s1", "s2", "s3", "s4"));

        // when
        stack.push(strings);

        // then
        while (!stack.isEmpty()) {
            Assertions.assertTrue(strings.containsAll(stack.pop()));
        }
    }
}
