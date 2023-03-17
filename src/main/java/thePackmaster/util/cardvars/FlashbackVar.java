package thePackmaster.util.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.util.cardvars.FlashbackField.FlashbackFields;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FlashbackVar extends DynamicVariable {
    @Override
    public String key() {
        return makeID("fbk");
    }

    @Override
    public int value(AbstractCard card) {
        return FlashbackFields.flashback.get(card);
    }

    @Override
    public int baseValue(AbstractCard card) {
        return FlashbackFields.baseFlashback.get(card);
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return FlashbackFields.isFlashbackModified.get(card);
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return FlashbackFields.upgradedFlashback.get(card);
    }
}
