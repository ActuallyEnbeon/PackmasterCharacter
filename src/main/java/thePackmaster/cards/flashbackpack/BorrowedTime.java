package thePackmaster.cards.flashbackpack;

import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.flashbackpack.BorrowedTimePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class BorrowedTime extends FlashpackCard {
    public final static String ID = makeID("BorrowedTime");

    public BorrowedTime() {
        super(ID, 2, AbstractCard.CardType.POWER, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhumeAction(false));
        applyToSelf(new BorrowedTimePower(p, magicNumber));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}
