package thePackmaster.util.cardvars;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class FlashbackField {

    @SpirePatch(
            clz = AbstractCard.class,
            method = "<class>"
    )
    public static class FlashbackFields {
        public static SpireField<Integer> flashback = new SpireField<>(() -> -1);
        public static SpireField<Integer> baseFlashback = new SpireField<>(() -> -1);
        public static SpireField<Boolean> upgradedFlashback = new SpireField<>(() -> false);
        public static SpireField<Boolean> isFlashbackModified = new SpireField<>(() -> false);
    }
}
