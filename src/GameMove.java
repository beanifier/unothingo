public class GameMove {
    public final Player player;
    public final Card playCard;
    public final int playCardIndex;
    public final boolean isDraw;
    public boolean didPlay;
    public Player nextPlayer;
    public final CardFunctionType cardFunction;
    public final CardType cardType;
    
    public GameMove(Player playerObject, int cardIndex) throws Exception {
        player = playerObject;
        if (player.hand.size() > cardIndex && cardIndex >= 0) {
            playCard = player.hand.getCard(cardIndex);
            playCardIndex = cardIndex;
        } else {
            throw new Exception("Invalid card index");
        }
        cardFunction = Card.getCardFunctionTypeFromCardType(playCard.type);
        cardType = playCard.type;
        isDraw = false;
        didPlay = false;
    }
    public GameMove() {
        player = null;
        playCard = null;
        playCardIndex = -1;
        cardFunction = null;
        cardType = null;
        isDraw = false;
        didPlay = false;
    }
    public GameMove(Player playerObject, boolean draw) {
        player = playerObject;
        playCard = null;
        playCardIndex = -1;
        cardFunction = null;
        cardType = null;
        isDraw = draw;
        didPlay = false;
    }
    public void play(Player next) {
        nextPlayer = next;
        didPlay = true;
    }
}
