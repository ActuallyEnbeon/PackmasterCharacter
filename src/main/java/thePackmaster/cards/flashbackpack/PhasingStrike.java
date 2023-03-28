package thePackmaster.cards.flashbackpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.flashbackpack.FlashbackModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.flashbackpack.FlashbackUtil.upgradeFlashback;
import static thePackmaster.util.Wiz.atb;

public class PhasingStrike extends FlashpackCard {
    public final static String ID = makeID("PhasingStrike");

    public PhasingStrike() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        CardModifierManager.addModifier(this, new FlashbackModifier(4));
        damage = baseDamage = 6;
        this.exhaust = true;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        atb(new ExhaustAction(1, false, false, false));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeFlashback(this, 1);
    }
}