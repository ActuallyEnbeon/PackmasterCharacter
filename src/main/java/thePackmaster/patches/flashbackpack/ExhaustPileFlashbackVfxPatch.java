package thePackmaster.patches.flashbackpack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.ExhaustPileViewScreen;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import thePackmaster.cardmodifiers.flashbackpack.FlashbackModifier;

import java.util.ArrayList;

public class ExhaustPileFlashbackVfxPatch {
    @SpirePatch(
            clz = ExhaustPileViewScreen.class,
            method = "open"
    )
    public static class FlashbackDisplayUpgrades {
        @SpireInsertPatch(locator = Locator.class, localvars = {"toAdd"})
        public static void Insert(ExhaustPileViewScreen __instance, AbstractCard toAdd) {
            if (CardModifierManager.hasModifier(toAdd, FlashbackModifier.MOD_ID)) {
                toAdd.glowColor = Color.valueOf("673B90").cpy();
                toAdd.beginGlowing();
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "lighten");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }
}
