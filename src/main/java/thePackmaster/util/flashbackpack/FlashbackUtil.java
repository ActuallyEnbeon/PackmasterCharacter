package thePackmaster.util;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.actions.flashbackpack.FlashbackReturnToHandAction;
import thePackmaster.cardmodifiers.flashbackpack.FlashbackModifier;
import thePackmaster.util.cardvars.FlashbackField.FlashbackFields;

import java.util.ArrayList;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class FlashbackUtil {
    public static void applyFlashback(AbstractCard card, int amountToApply) {
        int currentAmount = FlashbackFields.flashback.get(card);
        if (currentAmount == -1)
            CardModifierManager.addModifier(card, new FlashbackModifier(amountToApply));
        else
            FlashbackFields.flashback.set(card, currentAmount + amountToApply);
        FlashbackFields.isFlashbackModified.set(card, true);
    }

    public static void upgradeFlashback(AbstractCard card, int amount) {
        if (CardModifierManager.hasModifier(card, FlashbackModifier.MOD_ID)) {
            int newAmount = FlashbackFields.baseFlashback.get(card) + amount;
            CardModifierManager.removeModifiersById(card, FlashbackModifier.MOD_ID, true);
            CardModifierManager.addModifier(card, new FlashbackModifier(newAmount, true));
        }
    }

    public static void onPlayerTurnStart() {
        ArrayList<AbstractCard> cardsToFlashback = new ArrayList<>();
        for (AbstractCard c : adp().exhaustPile.group)
            if (CardModifierManager.hasModifier(c, FlashbackModifier.MOD_ID))
                cardsToFlashback.add(c);
        atb(new FlashbackReturnToHandAction(cardsToFlashback));
    }
}
