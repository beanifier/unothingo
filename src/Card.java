public class Card {
    public CardColor color;
    public CardType type;

    public Card(CardColor color, CardType type) {
        this.color = color;
        this.type = type;
    }
    public static Boolean canGoOnto(Card topCard, Card newCard) {
        return topCard.color == newCard.color || topCard.type == newCard.type || newCard.color == CardColor.WILD || newCard.type == CardType.WILD || newCard.type == CardType.WILD_DRAW4;
    }
    public static CardFunctionType getCardFunctionTypeFromCardType(CardType type) {
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

    public String toString() {
        String colorString = "";
        switch (color) {
            case RED:
                colorString = "\u001b[31m";
                break;
            case GREEN:
                colorString = "\u001b[32m";
                break;
            case BLUE:
                colorString = "\u001b[34m";
                break;
            case YELLOW:
                colorString = "\u001b[33m";
                break;
            case WILD:
                colorString = "\u001b[37m";
                break;
        }
        String name = "";
        switch (type) {
            case SKIP:
                name = " S";
                break;
            case REVERSE:
                name = " <";
                break;
            case DRAW2:
                name = "+2";
                break;
            case WILD:
                name = " #";
                break;
            case WILD_DRAW4:
                name = "+4";
                break;
            case NUM0:
                name = " 0";
                break;
            case NUM1:
                name = " 1";
                break;
            case NUM2:
                name = " 2";
                break;
            case NUM3:
                name = " 3";
                break;
            case NUM4:
                name = " 4";
                break;
            case NUM5:
                name = " 5";
                break;
            case NUM6:
                name = " 6";
                break;
            case NUM7:
                name = " 7";
                break;
            case NUM8:
                name = " 8";
                break;
            case NUM9:
                name = " 9";
                break;
        }
        return colorString + name + "\u001b[0m";
    }
}
