import lombok.Getter;

@Getter
public class PlayingCard implements Comparable<PlayingCard> {
    String name;
    CardValue value;
    CardSuit suit;

    public PlayingCard(String name) {
        if (name.length() != 2) {
            throw new IllegalArgumentException("IllegalCardNameArgument");
        }
        this.name = name;
        switch (name.charAt(0)) {
            case '2' -> this.value = CardValue.TWO;
            case '3' -> this.value = CardValue.THREE;
            case '4' -> this.value = CardValue.FOUR;
            case '5' -> this.value = CardValue.FIVE;
            case '6' -> this.value = CardValue.SIX;
            case '7' -> this.value = CardValue.SEVEN;
            case '8' -> this.value = CardValue.EIGHT;
            case '9' -> this.value = CardValue.NINE;
            case 'T' -> this.value = CardValue.TEN;
            case 'J' -> this.value = CardValue.JACK;
            case 'Q' -> this.value = CardValue.QUEEN;
            case 'K' -> this.value = CardValue.KING;
            case 'A' -> this.value = CardValue.ACE;
            default -> throw new IllegalArgumentException("IllegalCardValueArgument");
        }
        switch (name.charAt(1)) {
            case 'H' -> this.suit = CardSuit.HEARTS;
            case 'D' -> this.suit = CardSuit.DIAMONDS;
            case 'C' -> this.suit = CardSuit.CLUBS;
            case 'S' -> this.suit = CardSuit.SPADES;
            default -> throw new IllegalArgumentException("IllegalCardSuitArgument");
        }
    }

    @Override
    public int compareTo(PlayingCard o) {
        int res = value.compareTo(o.getValue());
        return res == 0 ? suit.compareTo(o.getSuit()) : res;
    }

}
