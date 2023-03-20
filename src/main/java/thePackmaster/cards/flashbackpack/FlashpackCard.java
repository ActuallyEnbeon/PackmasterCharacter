package thePackmaster.cards.flashbackpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class FlashpackCard extends AbstractPackmasterCard {
    public FlashpackCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "flashpack");
    }
}