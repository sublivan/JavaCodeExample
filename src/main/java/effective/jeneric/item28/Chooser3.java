package effective.jeneric.item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 제네릭과 리스트를 활용하여 비검사 형변환 경고 제거
 * 타입 안정성 확보
 */
public class Chooser3<T> {
    private final List<T> choiceList;

    public Chooser3(Collection<T> choices) {
        choiceList = new ArrayList<>(choices);
    }

    public Object choose() {
        Random random = ThreadLocalRandom.current();
        return choiceList.get(random.nextInt(choiceList.size()));
    }
}
