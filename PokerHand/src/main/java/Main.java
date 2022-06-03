import java.util.*;

public class Main {
    public static void main(String[] args) {

        PokerHand hand1 = new PokerHand("AS AC AD AH QS");
        PokerHand hand2 = new PokerHand("2S 2C 2D 3S 4S");
        PokerHand hand3 = new PokerHand("2D 2S 2C 3D 3S");
        PokerHand hand4 = new PokerHand("TD JD AD KD QD");

        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        hands.add(hand3);
        hands.add(hand4);

        Collections.sort(hands);

        hands.forEach(System.out::println);
   }
}
