package effective.lambdaAndStream.item45;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Card {

    public enum Suit { SPADE, HEART, DIAMOND, CLUB } // 무늬
    public enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN,
                        EIGHT, NINE, TEN, JACK, QUEEN, KING } // 카드숫자

    private static final List<Card> NEW_DECK = newDeck2(); //newDeck2()

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // 데카르트 곱 계산
    private static List<Card> newDeck1() { // 반복 방식
        List<Card> result = new ArrayList<>();
        for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                result.add(new Card(suit, rank));
        return result;
    }

    private static List<Card> newDeck2() { // 스트림 방식
        return Stream.of(Suit.values())
                .flatMap(suit -> Stream.of(Rank.values())
                .map(rank -> new Card(suit, rank)))
                .collect(toList());
    }


    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("NEW_DECK = " + NEW_DECK);
    }
}
