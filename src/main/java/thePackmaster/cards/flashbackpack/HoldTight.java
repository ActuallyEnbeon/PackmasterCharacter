package thePackmaster.cards.flashbackpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.flashbackpack.CardInHandGainsFlashbackAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class HoldTight extends AbstractPackmasterCard {
    public final static String ID = makeID("HoldTight");

    public HoldTight() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new CardInHandGainsFlashbackAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}