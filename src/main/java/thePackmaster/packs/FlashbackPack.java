package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.flashbackpack.*;

import java.util.ArrayList;

public class FlashbackPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("FlashbackPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public FlashbackPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        // TODO: new cards
        // TODO: change pack strings
        cards.add(DeadlyDraw.ID);
        cards.add(ForceOfWill.ID);
        cards.add(HoldTight.ID);
        cards.add(Insurrection.ID);
        cards.add(PhasingStrike.ID);
        cards.add(Resolve.ID);
        cards.add(ShadowShroud.ID);
        cards.add(SplitSecond.ID);
        return cards;
    }
}