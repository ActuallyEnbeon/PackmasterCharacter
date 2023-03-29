package thePackmaster.powers.flashbackpack;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BorrowedTimePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("BorrowedTimePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public int usesThisTurn;

    public BorrowedTimePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        usesThisTurn = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (shouldActivate(1)) {
            flash();
            action.exhaustCard = true;
        }
    }

    public boolean shouldActivate(int offset) {
        return AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - offset < this.amount;
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}