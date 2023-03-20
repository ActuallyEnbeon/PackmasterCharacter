package thePackmaster.actions.flashbackpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.flashbackpack.FlashbackUtil.applyFlashback;
import static thePackmaster.util.Wiz.adp;

public class CardInHandGainsFlashbackAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("CardInHandGainsFlashbackAction"));
    public static final String[] TEXT = uiStrings.TEXT;

    public CardInHandGainsFlashbackAction(int flashbackAmount) {
        this.p = adp();
        this.amount = flashbackAmount;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (p.hand.size() == 1) {
                AbstractCard onlyCard = p.hand.getTopCard();
                applyFlashback(onlyCard, amount);
                onlyCard.flash(Color.PURPLE.cpy());
                this.isDone = true;
                return;
            }
            AbstractDungeon.handCardSelectScreen.open(TEXT[0]+amount, 1, false, false, false, false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard card : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                applyFlashback(card, amount);
                p.hand.addToHand(card);
                card.flash(Color.PURPLE.cpy());
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.p.hand.refreshHandLayout();
        }
        tickDuration();
    }
}
