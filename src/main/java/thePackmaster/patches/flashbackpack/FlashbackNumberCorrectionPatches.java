package thePackmaster.patches.flashbackpack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import thePackmaster.cardmodifiers.flashbackpack.FlashbackModifier;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.cardvars.FlashbackField.FlashbackFields;

import java.util.ArrayList;

public class FlashbackNumberCorrectionPatches {
    @SpirePatch(
            clz = AbstractCard.class,
            method = "displayUpgrades"
    )
    public static class FlashbackDisplayUpgrades {
        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance) {
            if (FlashbackFields.upgradedFlashback.get(__instance)) {
                FlashbackFields.flashback.set(__instance, FlashbackFields.baseFlashback.get(__instance));
                FlashbackFields.isFlashbackModified.set(__instance, true);
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "makeStatEquivalentCopy"
    )
    public static class StatEquivalenceForFlashback {
        @SpireInsertPatch(locator=Locator.class, localvars={"card"})
        public static SpireReturn<AbstractCard> Insert(AbstractCard __instance, AbstractCard card) {
            card.freeToPlayOnce = __instance.freeToPlayOnce;
            if (CardModifierManager.hasModifier(__instance, FlashbackModifier.MOD_ID)) {
                int originalCardFlashback = FlashbackFields.flashback.get(__instance);
                // The baseFlashback being set to the original card's flashback here is intentional
                // It means that the correct number shows up on the card when the Exhaust pile is viewed
                FlashbackFields.baseFlashback.set(card, originalCardFlashback);
                FlashbackFields.flashback.set(card, originalCardFlashback);
            } else
                CardModifierManager.removeModifiersById(card, FlashbackModifier.MOD_ID, true);
            // The next lines are to make sure that increased secondDamage appears properly in the Exhaust pile
            if (card instanceof AbstractPackmasterCard && __instance instanceof AbstractPackmasterCard)
                ((AbstractPackmasterCard) card).baseSecondDamage = ((AbstractPackmasterCard) __instance).baseSecondDamage;
            return SpireReturn.Return(card);
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "freeToPlayOnce");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }
}
