package effective.jeneric.item30;

import java.util.HashSet;
import java.util.Set;

public class GenericMethod {

    public static void main(String[] args) {
        Set<String> boys = Set.of("톰", "해리", "제임스");
        Set<String> girls = Set.of("제인", "에밀리", "사라");
        Set<String> myFriends = union(boys, girls);

        System.out.println("myFriends = " + myFriends);
    }

    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }
}
