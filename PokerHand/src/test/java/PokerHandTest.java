import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест класса PokerHand")
public class PokerHandTest {
    private ArrayList<PokerHand> hands;

    @BeforeEach
    void setUp() {
        hands = new ArrayList<>();
    }

    @Test
    @DisplayName("Передано более 5 карт в строке")
    void moreThenFiveCardsInputString() {
        final String input = "2D 2S 2C 3D 3S 7S";
        assertThrows(Throwable.class, () -> hands.add(new PokerHand(input)),
                "Не выброшено исключение при количестве элементов в строке больше 5");
    }

    @Test
    @DisplayName("Передано менее 5 карт в строке")
    void lessThenFiveCardsInputString() {
        final String input = "2D 2S 2C 3D";
        assertThrows(Throwable.class, () -> hands.add(new PokerHand(input)),
                "Не выброшено исключение при количестве элементов в строке меньше 5");
    }

    @Test
    @DisplayName("Передано 2 одинаковые карты в строке")
    void sameCardsInputString() {
        final String input = "2D 2S 2C 3D 3D";
        assertThrows(Throwable.class, () -> hands.add(new PokerHand(input)),
                "Не выброшено исключение при 2 одинаковых элементах в строке");
    }

    @Test
    @DisplayName("Неверный формат масти одной карты")
    void wrongSuitFormat() {
        final String input = "2D 2S 2C 3D 3P";
        assertThrows(Throwable.class, () -> hands.add(new PokerHand(input)),
                "Не выброшено исключение при неверном формате карты -> " + input);
    }

    @Test
    @DisplayName("Неверный формат значения одной карты")
    void wrongValueFormat() {
        final String input = "2D 2S 2C 3D 1H";
        assertThrows(Throwable.class, () -> hands.add(new PokerHand(input)),
                "Не выброшено исключение при неверном формате карты -> " + input);
    }

    @Test
    @DisplayName("Тест добавления так себе, но корректных данных")
    void insertSoSoCorrectData() {
        final PokerHand hand = new PokerHand("Kd 7S 2C  3D tS");
        final String name = "2C 3D 7S TS KD";
        assertEquals(name, hand.toString());
    }

    @Test
    @DisplayName("Тест сравнения двух корректных карт - каре бьет фул-хаус")
    void compareCorrectCards1() {
        final PokerHand hand1 = new PokerHand("AS AC AD AH QS");
        final PokerHand hand2 = new PokerHand("2S 2C 2D 3S 3C");
        assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    @DisplayName("Тест сравнения двух корректных карт - равные")
    void compareCorrectCards2() {
        final PokerHand hand1 = new PokerHand("6S AC AD KH 2S");
        final PokerHand hand2 = new PokerHand("AS KC 6D AH 2D");
        assertEquals(0, hand1.compareTo(hand2));
    }
    @Test
    @DisplayName("Тест сравнения двух корректных карт - пара бьет пару")
    void compareCorrectCards3() {
        final PokerHand hand1 = new PokerHand("9S 9C 7D TH 3S");
        final PokerHand hand2 = new PokerHand("2S 5C AD 2H 4H");
        assertEquals(-1, hand1.compareTo(hand2));
    }
    @Test
    @DisplayName("Тест сравнения двух корректных карт - стрит бьет две пары")
    void compareCorrectCards4() {
        final PokerHand hand1 = new PokerHand("8D 9S TC JD QS");
        final PokerHand hand2 = new PokerHand("TD JD 2D TS JC");
        assertEquals(-1, hand1.compareTo(hand2));
    }
    @Test
    @DisplayName("Тест сравнения двух корректных карт - пара бьет такую же пару по доп картам")
    void compareCorrectCards5() {
        final PokerHand hand1 = new PokerHand("2S 2C 8D QS 4S");
        final PokerHand hand2 = new PokerHand("2D 2S 8C QD 3S");
        assertEquals(-1, hand1.compareTo(hand2));
    }
}
