package thePackmaster.cardmodifiers.flashbackpack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.util.cardvars.FlashbackField.FlashbackFields;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FlashbackModifier extends AbstractCardModifier {
    public int amount;
    public boolean upgraded;
    public static String MOD_ID = makeID("flashbackMod");
    public UIStrings flashbackText = CardCrawlGame.languagePack.getUIString(makeID("FlashbackCardText"));

    public FlashbackModifier(int flashbackAmount, boolean isUpgraded) {
        this.amount = flashbackAmount;
        this.upgraded = isUpgraded;
    }

    public FlashbackModifier(int flashbackAmount) {
        this(flashbackAmount, false);
    }

    @Override
    public String identifier(AbstractCard card) {
        return MOD_ID;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return flashbackText.TEXT[0] + rawDescription;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        FlashbackFields.baseFlashback.set(card, amount);
        FlashbackFields.flashback.set(card, amount);
        FlashbackFields.upgradedFlashback.set(card, upgraded);
    }

    @Override
    public void onRemove(AbstractCard card) {
        FlashbackFields.baseFlashback.set(card, -1);
        FlashbackFields.flashback.set(card, -1);
        FlashbackFields.upgradedFlashback.set(card, false);
        FlashbackFields.isFlashbackModified.set(card, false);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new FlashbackModifier(amount, upgraded);
    }
}
