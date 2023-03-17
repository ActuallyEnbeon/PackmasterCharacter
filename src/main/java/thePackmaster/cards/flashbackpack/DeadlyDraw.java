package thePackmaster.cards.flashbackpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.flashbackpack.FlashbackModifier;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.hermitpack.EnumPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class DeadlyDraw extends AbstractPackmasterCard {
    public final static String ID = makeID("DeadlyDraw");

    public DeadlyDraw() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        CardModifierManager.addModifier(this, new FlashbackModifier(9));
        damage = baseDamage = 28;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, EnumPatch.HERMIT_GUN);
    }

    public void triggerWhenDrawn() {
        att(new ExhaustSpecificCardAction(this, adp().hand));
    }

    public void upp() {
        upgradeDamage(7);
    }
}