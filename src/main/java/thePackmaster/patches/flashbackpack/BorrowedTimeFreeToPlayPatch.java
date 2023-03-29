package thePackmaster.patches.flashbackpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.powers.flashbackpack.BorrowedTimePower;

public class BorrowedTimeFreeToPlayPatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = "freeToPlay"
    )
    public static class BorrowedTimeFreeToPlay {
        @SpireInsertPatch(rloc=0)
        public static SpireReturn<Boolean> Insert(AbstractCard __instance) {
            if (AbstractDungeon.player != null && AbstractDungeon.currMapNode != null &&
                    (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                BorrowedTimePower po = (BorrowedTimePower)AbstractDungeon.player.getPower(BorrowedTimePower.POWER_ID);
                if (po != null && po.shouldActivate(0)) {
                    return SpireReturn.Return(true);
                }
            }
            return SpireReturn.Continue();
        }
    }
}

