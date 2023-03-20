package thePackmaster.cards.flashbackpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.flashbackpack.FlashbackModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.flashbackpack.FlashbackUtil.upgradeFlashback;

public class Insurrection extends FlashpackCard {
    public final static String ID = makeID("Insurrection");

    public Insurrection() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        CardModifierManager.addModifier(this, new FlashbackModifier(3));
        damage = baseDamage = 5;
        magicNumber = baseMagicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractGameAction.AttackEffect effect;
        for (int i = 0; i <= magicNumber; i++) {
            if (i < 3) effect = AbstractGameAction.AttackEffect.BLUNT_LIGHT;
            else effect = AbstractGameAction.AttackEffect.BLUNT_HEAVY;
            dmg(m, effect);
        }
    }

    @Override
    public void triggerOnExhaust() {
        magicNumber = baseMagicNumber += 1;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (baseMagicNumber == 1)
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        else if (baseMagicNumber > 0)
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (baseMagicNumber == 1)
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        else if (baseMagicNumber > 0)
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void upp() {
        upgradeDamage(1);
        upgradeFlashback(this, 1);
    }
}