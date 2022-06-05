import lombok.Getter;
import java.util.*;

@Getter
public class PokerHand implements Comparable<PokerHand> {
    final private ArrayList<PlayingCard> cards = new ArrayList<>();
    final private Integer status;

    public PokerHand(String expression) {
        expression = expression.toUpperCase();
        String[] items = expression.split("\\s+");
        if (items.length != 5) {
            throw new IllegalArgumentException("IllegalCardsNumberArgument");
        }
        for (int i = 0; i < 5; i++) {
            PlayingCard card = new PlayingCard(items[i]);
            cards.add(card);
        }
        Collections.sort(cards);
        if ((cards.get(0).getName().equals(cards.get(1).getName()))
                || (cards.get(1).getName().equals(cards.get(2).getName()))
                || (cards.get(2).getName().equals(cards.get(3).getName()))
                || (cards.get(3).getName().equals(cards.get(4).getName()))) {
            throw new IllegalArgumentException("IllegalTheSameCardsArgument");
        }
        status = defineStatus();
    }

    @Override
    public String toString() {
        String str = "";
        for (PlayingCard card : cards) {
            str += " " + card.getName();
        }
        return str.substring(1);
    }

    @Override
    public int compareTo(PokerHand o) {
        return status.compareTo(o.getStatus());
    }

    private int defineStatus() {
        boolean flash = true;
        boolean street = true;

        for (int i = 1; flash && (i < 5); i++) {
            flash = flash && (cards.get(0).suit == cards.get(i).suit);
        }
        for (int i = 1; street && (i < 5) ; i++) {
            street = street && (cards.get(i - 1).value.ordinal() == cards.get(i).value.ordinal() - 1);
        }

        //1-9 стрит-флеш
        if (flash && street) {
            return 9 - cards.get(0).value.ordinal();
        }

        boolean r1 = (cards.get(0).value == cards.get(1).value);
        boolean r2 = (cards.get(1).value == cards.get(2).value);
        boolean r3 = (cards.get(2).value == cards.get(3).value);
        boolean r4 = (cards.get(3).value == cards.get(4).value);
        int v0 = cards.get(0).value.ordinal();
        int v1 = cards.get(1).value.ordinal();
        int v2 = cards.get(2).value.ordinal();
        int v3 = cards.get(3).value.ordinal();
        int v4 = cards.get(4).value.ordinal();

        //33-199 каре
        if (r1 && r2 && r3) {
            return 200 - v0 * 13 - v4;
        }
        if (r2 && r3 && r4) {
            return 200 - v4 * 13 - v0;
        }

        //333-499 фул-хаус
        if (r1 && r2 && r4) {
            return 500 - v0 * 13 - v4;
        }
        if (r1 && r3 && r4) {
            return 500 - v4 * 13 - v0;
        }

        //1113-80000 флеш
        if (flash) {
            return 80000 - (v4 - 5) * 10000 - (v3 - 3)  * 1000
                    - (v2 - 2) * 100 - (v1 - 1) * 10 - v0;
        }

        //80092-80100 стрит
        if (street) {
            return 80100 - v0;
        }

        //80819-82973 тройка
        if (r1 && r2) {
            return 83000 - v0 * 169 - v4 * 13 - v3;
        }
        if (r2 && r3) {
            return 83000 - v1 * 169 - v4 * 13 - v0;
        }
        if (r3 && r4) {
            return 83000 - v2 * 169 - v1 * 13 - v0;
        }

        //83819-85829 две-пары
        if (r1 && r4) {
            return 86000 - v4 * 169 - v0 * 13 - v2;
        }
        if (r2 && r4) {
            return 86000 - v4 * 169 - v2 * 13 - v0;
        }
        if (r1 && r3) {
            return 86000 - v2 * 169 - v0 * 13 - v4;
        }

        //91638-119466 одна-пара
        if (r1) {
            return 120000 - v0 * 2197 - v4 * 169 - v3 * 13 - v2;
        }
        if (r2) {
            return 120000 - v1 * 2197 - v4 * 169 - v3 * 13 - v0;
        }
        if (r3) {
            return 120000 - v2 * 2197 - v4 * 169 - v1 * 13 - v0;
        }
        if (r4) {
            return 120000 - v3 * 2197 - v2 * 169 - v1 * 13 - v0;
        }

        //131287-350253 просто-карта
        return 500000 - v4 * 28561 - v3 * 2197 - v2 * 169 - v1 * 13 - v0;
    }
}
