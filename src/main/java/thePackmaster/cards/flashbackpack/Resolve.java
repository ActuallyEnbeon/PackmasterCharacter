package thePackmaster.cards.flashbackpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.flashbackpack.FlashbackModifier;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.flashbackpack.FlashbackUtil.upgradeFlashback;
import static thePackmaster.util.Wiz.atb;

public class Resolve extends AbstractPackmasterCard {
    public final static String ID = makeID("Resolve");

    public Resolve() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        block = baseBlock = 6;
        magicNumber = baseMagicNumber = 2;
        CardModifierManager.addModifier(this, new FlashbackModifier(2));
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ModifyBlockAction(this.uuid, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeFlashback(this, 1);
    }
}