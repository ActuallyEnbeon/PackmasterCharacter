package thePackmaster.cards.flashbackpack;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FeelNoPainPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.flashbackpack.FeelNoPainDownPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class RepressPain extends AbstractPackmasterCard {
    public final static String ID = makeID("RepressPain");

    public RepressPain() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       applyToSelf(new FeelNoPainPower(p, magicNumber));
       applyToSelf(new FeelNoPainDownPower(p, magicNumber));
       atb(new ExhaustAction(1, false, false, false));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}