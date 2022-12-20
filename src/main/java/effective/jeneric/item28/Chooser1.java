package effective.jeneric.item28;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 제네릭 적용이 필요한 클래스
 * choose 메서드를 호출할 때마다 반환된 Object를 원하는 타입으로 형변환 해야한다.
 */
public class Chooser1 {
    private final Object[] choiceArray;

    public Chooser1(Collection choices) {
        this.choiceArray = choices.toArray();
    }

    public Object choose() {
        Random random = ThreadLocalRandom.current();
        return choiceArray[random.nextInt(choiceArray.length)];
    }
}
