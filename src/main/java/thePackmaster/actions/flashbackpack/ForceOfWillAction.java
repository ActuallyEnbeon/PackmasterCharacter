package thePackmaster.actions.flashbackpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.actions.ChangePlayedCardExhaustAction;

import static thePackmaster.util.Wiz.*;

public class ForceOfWillAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private AbstractCard c;
    private boolean flipFlop;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public ForceOfWillAction(AbstractCard callingCard, int strengthGain) {
        this.p = adp();
        this.c = callingCard;
        this.amount = strengthGain;
        this.flipFlop = true;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (p.hand.isEmpty()) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    applyToSelfTop(new StrengthPower(p, amount));
                }
                att(new ChangePlayedCardExhaustAction(c, true));
                this.isDone = true;
                return;
            }

            p.hand.group.add(c);
            c.targetTransparency = 0.5F;
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);
            tickDuration();
            return;
        }

        if (flipFlop) {
            p.hand.group.remove(c);
            c.targetTransparency = 1F;
            flipFlop = false;
            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard card : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                if (card.type == AbstractCard.CardType.ATTACK) {
                    applyToSelfTop(new StrengthPower(p, amount));
                }
                if (card == c) {
                    att(new ChangePlayedCardExhaustAction(c, true));
                } else {
                    p.hand.moveToExhaustPile(card);
                }
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            p.hand.refreshHandLayout();
        }
        tickDuration();
    }
}
