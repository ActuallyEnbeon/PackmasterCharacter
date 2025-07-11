package thePackmaster.actions.rimworldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.cards.rimworldpack.Despair;

public class KillThirstAction extends AbstractGameAction {

    private DamageInfo info;
    private AbstractCard card;

    //Copied from Sunder but changed to lose Mood if not fatal instead of gain E if fatal
    public KillThirstAction(AbstractCard card, AbstractCreature target, DamageInfo info) {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.card = card;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FASTER &&
                target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            target.damage(info);
            if (!((target).isDying || target.currentHealth <= 0))
            {
                addToBot(new MakeTempCardInDrawPileAction(new Despair(), 1, true, true));
                card.damage = card.baseDamage *= 2;
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }
        tickDuration();
    }
}