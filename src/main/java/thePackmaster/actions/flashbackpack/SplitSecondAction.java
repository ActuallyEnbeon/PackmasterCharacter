package thePackmaster.actions.flashbackpack;

import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import thePackmaster.cardmodifiers.flashbackpack.FlashbackModifier;
import thePackmaster.cards.flashbackpack.SplitSecond;

import java.util.Objects;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;

public class SplitSecondAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private CardGroup flashbackCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("SplitSecondAction"));
    public static final String[] TEXT = uiStrings.TEXT;

    public SplitSecondAction() {
        this.p = adp();
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (p.hand.size() == BaseMod.MAX_HAND_SIZE) {
                p.createHandIsFullDialog();
                this.isDone = true;
                return;
            }

            if (p.exhaustPile.isEmpty()) {
                AbstractDungeon.effectList.add(new ThoughtBubble(
                        AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY,
                        3.0F, TEXT[1], true));
                this.isDone = true;
                return;
            }
            if (p.exhaustPile.size() == 1) {
                AbstractCard onlyCard = p.exhaustPile.getTopCard();
                if (isAValidCard(onlyCard))
                    exhumeCard(onlyCard);
                else
                    AbstractDungeon.effectList.add(new ThoughtBubble(
                            AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY,
                            3.0F, TEXT[1], true));
                this.isDone = true;
                return;
            }

            for (AbstractCard nextCard : p.exhaustPile.group)
                if (isAValidCard(nextCard))
                    flashbackCards.addToTop(nextCard);
            if (flashbackCards.isEmpty()) {
                AbstractDungeon.effectList.add(new ThoughtBubble(
                        AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY,
                        3.0F, TEXT[1], true));
                this.isDone = true;
                return;
            }
            if (flashbackCards.size() == 1) {
                AbstractCard onlyCard = flashbackCards.getTopCard();
                if (isAValidCard(onlyCard))
                    exhumeCard(onlyCard);
                else
                    AbstractDungeon.effectList.add(new ThoughtBubble(
                            AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY,
                            3.0F, TEXT[1], true));
                flashbackCards.clear();
                this.isDone = true;
                return;
            }

            AbstractDungeon.gridSelectScreen.open(flashbackCards, 1, TEXT[0], false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
                exhumeCard(c);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            for (AbstractCard c : flashbackCards.group) {
                c.unhover();
                c.target_x = CardGroup.DISCARD_PILE_X;
                c.target_y = 0.0F;
            }
            flashbackCards.clear();
        }
        tickDuration();
    }

    public boolean isAValidCard(AbstractCard c) {
        return CardModifierManager.hasModifier(c, FlashbackModifier.MOD_ID)
                && !Objects.equals(c.cardID, SplitSecond.ID);
    }

    public void exhumeCard(AbstractCard c) {
        c.initializeDescription();
        c.current_x = CardGroup.DISCARD_PILE_X;
        c.current_y = 0.0F;
        p.hand.addToHand(c);
        if (p.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL)
            c.setCostForTurn(-9);
        p.exhaustPile.removeCard(c);
        c.applyPowers();
        c.unhover();
        c.unfadeOut();
        p.hand.refreshHandLayout();
    }
}
