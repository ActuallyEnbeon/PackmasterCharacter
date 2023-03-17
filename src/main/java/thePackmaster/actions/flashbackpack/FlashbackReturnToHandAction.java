package thePackmaster.actions.flashbackpack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import thePackmaster.cardmodifiers.flashbackpack.FlashbackModifier;
import thePackmaster.util.cardvars.FlashbackField.FlashbackFields;

import java.util.ArrayList;

import static thePackmaster.util.Wiz.adp;

public class FlashbackReturnToHandAction extends AbstractGameAction {
    public ArrayList<AbstractCard> cards;
    public AbstractPlayer p;

    public FlashbackReturnToHandAction(ArrayList<AbstractCard> cards) {
        this.cards = cards;
        this.p = adp();
    }

    @Override
    public void update() {
        for (AbstractCard c : cards) {
            int amountOnCard = FlashbackFields.flashback.get(c);
            amountOnCard -= 1;
            if (amountOnCard <= 0)
                CardModifierManager.removeModifiersById(c, FlashbackModifier.MOD_ID, true);
            else {
                FlashbackFields.flashback.set(c, amountOnCard);
                if (amountOnCard != FlashbackFields.baseFlashback.get(c))
                    FlashbackFields.isFlashbackModified.set(c, true);
            }
            c.initializeDescription();
            c.current_x = CardGroup.DISCARD_PILE_X;
            c.current_y = 0.0F;
            p.hand.addToHand(c);
            if (p.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL)
                c.setCostForTurn(-9);
            p.exhaustPile.removeCard(c);
            c.applyPowers();
            c.unfadeOut();
            // TODO: sfx
            c.flash(Color.PURPLE.cpy());
        }
        p.hand.refreshHandLayout();
        this.isDone = true;
    }
}
