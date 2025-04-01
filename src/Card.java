public class Card {
    public CardColor color;
    public CardType type;

    public Card(CardColor color, CardType type) {
        this.color = color;
        this.type = type;
    }
    public static Boolean canGoOnto(Card topCard, Card newCard) {
        return topCard.color == newCard.color || topCard.type == newCard.type;
    }
    public static CardFunctionType gCardFunctionTypeFromCardType(CardType type) {
        switch (type) {
            case SKIP:
                return CardFunctionType.SKIP;
            case REVERSE:
                return CardFunctionType.REVERSE;
            case DRAW2:
                return CardFunctionType.DRAW;
            case WILD:
                return CardFunctionType.WILD;
            case WILD_DRAW4:
                return CardFunctionType.WILD_DRAW;
            default:
                return CardFunctionType.CONTINUE;
        }
    }
}
