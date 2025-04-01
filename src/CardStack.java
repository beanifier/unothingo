import java.util.LinkedList;

public class CardStack {
    public LinkedList<Card> cards;
    public CardStack() {
        cards = new LinkedList<Card>();
    }
    public void addCard(Card card) {
        cards.add(card);
    }
    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.removeLast();
    }
    public Card takeCard(int index) {
        if (index < 0 || index >= cards.size()) {
            return null;
        }
        return cards.remove(index);
    }
    public void shuffle() {
        LinkedList<Card> shuffledCards = new LinkedList<Card>();
        while (!cards.isEmpty()) {
            int randomIndex = (int) (Math.random() * cards.size());
            shuffledCards.add(cards.remove(randomIndex));
        }
        cards = shuffledCards;
    }
    public int findCard(CardColor color) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).color == color) {
                return i;
            }
        }
        return -1;
    }
    public int findCard(CardType type) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).type == type) {
                return i;
            }
        }
        return -1;
    }
    public int findCard(Card card) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).color == card.color && cards.get(i).type == card.type) {
                return i;
            }
        }
        return -1;
    }
    public int size() {
        return cards.size();
    }
    public Card getCard(int index) {
        if (index < 0 || index >= cards.size()) {
            return null;
        }
        return cards.get(index);
    }
    public Card getTopCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.getLast();
    }
    
}
