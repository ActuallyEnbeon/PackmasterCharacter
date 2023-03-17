package thePackmaster.cards.flashbackpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import thePackmaster.cardmodifiers.flashbackpack.FlashbackModifier;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.FlashbackUtil.upgradeFlashback;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.applyToSelf;

public class ShadowShroud extends AbstractPackmasterCard {
    public final static String ID = makeID("ShadowShroud");

    public ShadowShroud() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        CardModifierManager.addModifier(this, new FlashbackModifier(1));
        block = baseBlock = 14;
        magicNumber = baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void triggerOnExhaust() {
        applyToSelf(new PlatedArmorPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
        upgradeFlashback(this, 1);
    }
}