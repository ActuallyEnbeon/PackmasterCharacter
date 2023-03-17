package thePackmaster.cards.flashbackpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.flashbackpack.ForceOfWillAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ForceOfWill extends AbstractPackmasterCard {
    public final static String ID = makeID("ForceOfWill");

    public ForceOfWill() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new ForceOfWillAction(this, 1));
    }

    public void upp() {
        upgradeDamage(2);
    }
}