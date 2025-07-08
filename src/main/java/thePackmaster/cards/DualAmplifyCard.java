package thePackmaster.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.cards.marisapack.AmplifyCard;
import thePackmaster.util.Wiz;

public interface DualAmplifyCard extends AmplifyCard {
    /**
     * @param thisCard Instance of AbstractCard that should only ever be the card this interface instance is on
     *                 Example: ((AmplifyCard)c).shouldAmplify(c)
     * @param testCost The card is tested as though its total Amplify cost were this value
     * @return boolean that is true if the card would be amplified if it is played now with the tested cost.
     *                 This should be used in applyPowers/calculateCardDamage
     */
    default boolean shouldAmplifyWithTest(AbstractCard thisCard, int testCost) {
        int cardCost = Wiz.getLogicalCardCost(thisCard);
        return EnergyPanel.totalCount >= cardCost + this._costLogic(testCost);
    }

    @Override
    default void useAmplified(AbstractPlayer p, AbstractMonster m) {
        boolean includeSecondEffect = (getAmplifyCost() >= getMaxAmplifyCost());
        useAmplified(p, m, includeSecondEffect);
    }

    //The use method that gets executed when an amplified card is played, happens after a card's normal use
    // "includeSecondEffect" is true if both Amplify costs were paid, false otherwise
    void useAmplified(AbstractPlayer p, AbstractMonster m, boolean includeSecondEffect);

    @Override
    default int getAmplifyCost() {
        if (shouldAmplifyWithTest((AbstractCard)this, getMaxAmplifyCost())) {
            return getMaxAmplifyCost();
        }
        return getFirstAmplifyCost();
    }

    //Defines the amount of energy you need to activate the first Amplify effect (on top of the card's normal cost)
    int getFirstAmplifyCost();

    //Defines the amount of energy you need to activate the second Amplify effect (on top of the first Amplify cost)
    int getSecondAmplifyCost();

    default int getMaxAmplifyCost() {
        return getFirstAmplifyCost() + getSecondAmplifyCost();
    }
}
