package com.ort.dbflute.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.Entity;
import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import org.dbflute.optional.OptionalEntity;
import com.ort.dbflute.allcommon.DBMetaInstanceHandler;
import com.ort.dbflute.allcommon.CDef;
import com.ort.dbflute.exentity.*;

/**
 * The entity of SKILL as TABLE. <br>
 * 役職
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsSkill extends AbstractEntity implements DomainEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** SKILL_CODE: {PK, NotNull, VARCHAR(20), classification=Skill} */
    protected String _skillCode;

    /** SKILL_NAME: {NotNull, VARCHAR(20)} */
    protected String _skillName;

    /** SKILL_SHORT_NAME: {NotNull, CHAR(1)} */
    protected String _skillShortName;

    /** CAMP_CODE: {IX, NotNull, VARCHAR(20), FK to camp, classification=Camp} */
    protected String _campCode;

    /** DISP_ORDER: {NotNull, INT UNSIGNED(10)} */
    protected Integer _dispOrder;

    /** DESCRIPTION: {NotNull, TEXT(65535)} */
    protected String _description;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return DBMetaInstanceHandler.findDBMeta(asTableDbName());
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "skill";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        if (_skillCode == null) { return false; }
        return true;
    }

    // ===================================================================================
    //                                                             Classification Property
    //                                                             =======================
    /**
     * Get the value of skillCode as the classification of Skill. <br>
     * SKILL_CODE: {PK, NotNull, VARCHAR(20), classification=Skill} <br>
     * 役職
     * <p>It's treated as case insensitive and if the code value is null, it returns null.</p>
     * @return The instance of classification definition (as ENUM type). (NullAllowed: when the column value is null)
     */
    public CDef.Skill getSkillCodeAsSkill() {
        return CDef.Skill.of(getSkillCode()).orElse(null);
    }

    /**
     * Set the value of skillCode as the classification of Skill. <br>
     * SKILL_CODE: {PK, NotNull, VARCHAR(20), classification=Skill} <br>
     * 役職
     * @param cdef The instance of classification definition (as ENUM type). (NullAllowed: if null, null value is set to the column)
     */
    public void setSkillCodeAsSkill(CDef.Skill cdef) {
        setSkillCode(cdef != null ? cdef.code() : null);
    }

    /**
     * Get the value of campCode as the classification of Camp. <br>
     * CAMP_CODE: {IX, NotNull, VARCHAR(20), FK to camp, classification=Camp} <br>
     * 陣営
     * <p>It's treated as case insensitive and if the code value is null, it returns null.</p>
     * @return The instance of classification definition (as ENUM type). (NullAllowed: when the column value is null)
     */
    public CDef.Camp getCampCodeAsCamp() {
        return CDef.Camp.of(getCampCode()).orElse(null);
    }

    /**
     * Set the value of campCode as the classification of Camp. <br>
     * CAMP_CODE: {IX, NotNull, VARCHAR(20), FK to camp, classification=Camp} <br>
     * 陣営
     * @param cdef The instance of classification definition (as ENUM type). (NullAllowed: if null, null value is set to the column)
     */
    public void setCampCodeAsCamp(CDef.Camp cdef) {
        setCampCode(cdef != null ? cdef.code() : null);
    }

    // ===================================================================================
    //                                                              Classification Setting
    //                                                              ======================
    /**
     * Set the value of skillCode as パン屋 (BAKERY). <br>
     * パン屋
     */
    public void setSkillCode_パン屋() {
        setSkillCodeAsSkill(CDef.Skill.パン屋);
    }

    /**
     * Set the value of skillCode as C国狂人 (CMADMAN). <br>
     * C国狂人
     */
    public void setSkillCode_C国狂人() {
        setSkillCodeAsSkill(CDef.Skill.C国狂人);
    }

    /**
     * Set the value of skillCode as 検死官 (CORONER). <br>
     * 検死官
     */
    public void setSkillCode_検死官() {
        setSkillCodeAsSkill(CDef.Skill.検死官);
    }

    /**
     * Set the value of skillCode as 求愛者 (COURTSHIP). <br>
     * 求愛者
     */
    public void setSkillCode_求愛者() {
        setSkillCodeAsSkill(CDef.Skill.求愛者);
    }

    /**
     * Set the value of skillCode as 呪狼 (CURSEWOLF). <br>
     * 呪狼
     */
    public void setSkillCode_呪狼() {
        setSkillCodeAsSkill(CDef.Skill.呪狼);
    }

    /**
     * Set the value of skillCode as 情緒 (EMOTION). <br>
     * 情緒
     */
    public void setSkillCode_情緒() {
        setSkillCodeAsSkill(CDef.Skill.情緒);
    }

    /**
     * Set the value of skillCode as 狂信者 (FANATIC). <br>
     * 狂信者
     */
    public void setSkillCode_狂信者() {
        setSkillCodeAsSkill(CDef.Skill.狂信者);
    }

    /**
     * Set the value of skillCode as 妖狐 (FOX). <br>
     * 妖狐
     */
    public void setSkillCode_妖狐() {
        setSkillCodeAsSkill(CDef.Skill.妖狐);
    }

    /**
     * Set the value of skillCode as 導師 (GURU). <br>
     * 導師
     */
    public void setSkillCode_導師() {
        setSkillCodeAsSkill(CDef.Skill.導師);
    }

    /**
     * Set the value of skillCode as 冷やし中華 (HIYASICHUKA). <br>
     * 冷やし中華
     */
    public void setSkillCode_冷やし中華() {
        setSkillCodeAsSkill(CDef.Skill.冷やし中華);
    }

    /**
     * Set the value of skillCode as 狩人 (HUNTER). <br>
     * 狩人
     */
    public void setSkillCode_狩人() {
        setSkillCodeAsSkill(CDef.Skill.狩人);
    }

    /**
     * Set the value of skillCode as 背徳者 (IMMORAL). <br>
     * 背徳者
     */
    public void setSkillCode_背徳者() {
        setSkillCodeAsSkill(CDef.Skill.背徳者);
    }

    /**
     * Set the value of skillCode as おまかせ (LEFTOVER). <br>
     * おまかせ
     */
    public void setSkillCode_おまかせ() {
        setSkillCodeAsSkill(CDef.Skill.おまかせ);
    }

    /**
     * Set the value of skillCode as 強運者 (LUCKYMAN). <br>
     * 強運者
     */
    public void setSkillCode_強運者() {
        setSkillCodeAsSkill(CDef.Skill.強運者);
    }

    /**
     * Set the value of skillCode as 狂人 (MADMAN). <br>
     * 狂人
     */
    public void setSkillCode_狂人() {
        setSkillCodeAsSkill(CDef.Skill.狂人);
    }

    /**
     * Set the value of skillCode as 共有者 (MASON). <br>
     * 共有者
     */
    public void setSkillCode_共有者() {
        setSkillCodeAsSkill(CDef.Skill.共有者);
    }

    /**
     * Set the value of skillCode as 霊能者 (MEDIUM). <br>
     * 霊能者
     */
    public void setSkillCode_霊能者() {
        setSkillCodeAsSkill(CDef.Skill.霊能者);
    }

    /**
     * Set the value of skillCode as 猫又 (MONSTERCAT). <br>
     * 猫又
     */
    public void setSkillCode_猫又() {
        setSkillCodeAsSkill(CDef.Skill.猫又);
    }

    /**
     * Set the value of skillCode as 占い師 (SEER). <br>
     * 占い師
     */
    public void setSkillCode_占い師() {
        setSkillCodeAsSkill(CDef.Skill.占い師);
    }

    /**
     * Set the value of skillCode as 聖痕者 (STIGMATA). <br>
     * 聖痕者
     */
    public void setSkillCode_聖痕者() {
        setSkillCodeAsSkill(CDef.Skill.聖痕者);
    }

    /**
     * Set the value of skillCode as 共鳴者 (SYMPATHIZER). <br>
     * 共鳴者
     */
    public void setSkillCode_共鳴者() {
        setSkillCodeAsSkill(CDef.Skill.共鳴者);
    }

    /**
     * Set the value of skillCode as 村人 (VILLAGER). <br>
     * 村人
     */
    public void setSkillCode_村人() {
        setSkillCodeAsSkill(CDef.Skill.村人);
    }

    /**
     * Set the value of skillCode as おまかせ村人陣営 (VILLAGERS). <br>
     * おまかせ（村人陣営）
     */
    public void setSkillCode_おまかせ村人陣営() {
        setSkillCodeAsSkill(CDef.Skill.おまかせ村人陣営);
    }

    /**
     * Set the value of skillCode as 風来狩人 (WANDERER). <br>
     * 風来狩人
     */
    public void setSkillCode_風来狩人() {
        setSkillCodeAsSkill(CDef.Skill.風来狩人);
    }

    /**
     * Set the value of skillCode as 人狼 (WEREWOLF). <br>
     * 人狼
     */
    public void setSkillCode_人狼() {
        setSkillCodeAsSkill(CDef.Skill.人狼);
    }

    /**
     * Set the value of skillCode as おまかせ人狼陣営 (WEREWOLFS). <br>
     * おまかせ（人狼陣営）
     */
    public void setSkillCode_おまかせ人狼陣営() {
        setSkillCodeAsSkill(CDef.Skill.おまかせ人狼陣営);
    }

    /**
     * Set the value of skillCode as 賢者 (WISE). <br>
     * 賢者
     */
    public void setSkillCode_賢者() {
        setSkillCodeAsSkill(CDef.Skill.賢者);
    }

    /**
     * Set the value of skillCode as 智狼 (WISEWOLF). <br>
     * 智狼
     */
    public void setSkillCode_智狼() {
        setSkillCodeAsSkill(CDef.Skill.智狼);
    }

    /**
     * Set the value of campCode as 狐陣営 (FOX). <br>
     * 狐陣営
     */
    public void setCampCode_狐陣営() {
        setCampCodeAsCamp(CDef.Camp.狐陣営);
    }

    /**
     * Set the value of campCode as 恋人陣営 (LOVERS). <br>
     * 恋人陣営
     */
    public void setCampCode_恋人陣営() {
        setCampCodeAsCamp(CDef.Camp.恋人陣営);
    }

    /**
     * Set the value of campCode as 村人陣営 (VILLAGER). <br>
     * 村人陣営
     */
    public void setCampCode_村人陣営() {
        setCampCodeAsCamp(CDef.Camp.村人陣営);
    }

    /**
     * Set the value of campCode as 人狼陣営 (WEREWOLF). <br>
     * 人狼陣営
     */
    public void setCampCode_人狼陣営() {
        setCampCodeAsCamp(CDef.Camp.人狼陣営);
    }

    // ===================================================================================
    //                                                        Classification Determination
    //                                                        ============================
    /**
     * Is the value of skillCode パン屋? <br>
     * パン屋
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCodeパン屋() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.パン屋) : false;
    }

    /**
     * Is the value of skillCode C国狂人? <br>
     * C国狂人
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCodeC国狂人() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.C国狂人) : false;
    }

    /**
     * Is the value of skillCode 検死官? <br>
     * 検死官
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode検死官() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.検死官) : false;
    }

    /**
     * Is the value of skillCode 求愛者? <br>
     * 求愛者
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode求愛者() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.求愛者) : false;
    }

    /**
     * Is the value of skillCode 呪狼? <br>
     * 呪狼
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode呪狼() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.呪狼) : false;
    }

    /**
     * Is the value of skillCode 情緒? <br>
     * 情緒
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode情緒() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.情緒) : false;
    }

    /**
     * Is the value of skillCode 狂信者? <br>
     * 狂信者
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode狂信者() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.狂信者) : false;
    }

    /**
     * Is the value of skillCode 妖狐? <br>
     * 妖狐
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode妖狐() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.妖狐) : false;
    }

    /**
     * Is the value of skillCode 導師? <br>
     * 導師
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode導師() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.導師) : false;
    }

    /**
     * Is the value of skillCode 冷やし中華? <br>
     * 冷やし中華
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode冷やし中華() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.冷やし中華) : false;
    }

    /**
     * Is the value of skillCode 狩人? <br>
     * 狩人
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode狩人() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.狩人) : false;
    }

    /**
     * Is the value of skillCode 背徳者? <br>
     * 背徳者
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode背徳者() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.背徳者) : false;
    }

    /**
     * Is the value of skillCode おまかせ? <br>
     * おまかせ
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCodeおまかせ() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.おまかせ) : false;
    }

    /**
     * Is the value of skillCode 強運者? <br>
     * 強運者
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode強運者() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.強運者) : false;
    }

    /**
     * Is the value of skillCode 狂人? <br>
     * 狂人
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode狂人() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.狂人) : false;
    }

    /**
     * Is the value of skillCode 共有者? <br>
     * 共有者
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode共有者() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.共有者) : false;
    }

    /**
     * Is the value of skillCode 霊能者? <br>
     * 霊能者
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode霊能者() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.霊能者) : false;
    }

    /**
     * Is the value of skillCode 猫又? <br>
     * 猫又
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode猫又() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.猫又) : false;
    }

    /**
     * Is the value of skillCode 占い師? <br>
     * 占い師
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode占い師() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.占い師) : false;
    }

    /**
     * Is the value of skillCode 聖痕者? <br>
     * 聖痕者
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode聖痕者() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.聖痕者) : false;
    }

    /**
     * Is the value of skillCode 共鳴者? <br>
     * 共鳴者
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode共鳴者() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.共鳴者) : false;
    }

    /**
     * Is the value of skillCode 村人? <br>
     * 村人
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode村人() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.村人) : false;
    }

    /**
     * Is the value of skillCode おまかせ村人陣営? <br>
     * おまかせ（村人陣営）
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCodeおまかせ村人陣営() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.おまかせ村人陣営) : false;
    }

    /**
     * Is the value of skillCode 風来狩人? <br>
     * 風来狩人
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode風来狩人() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.風来狩人) : false;
    }

    /**
     * Is the value of skillCode 人狼? <br>
     * 人狼
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode人狼() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.人狼) : false;
    }

    /**
     * Is the value of skillCode おまかせ人狼陣営? <br>
     * おまかせ（人狼陣営）
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCodeおまかせ人狼陣営() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.おまかせ人狼陣営) : false;
    }

    /**
     * Is the value of skillCode 賢者? <br>
     * 賢者
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode賢者() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.賢者) : false;
    }

    /**
     * Is the value of skillCode 智狼? <br>
     * 智狼
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isSkillCode智狼() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null ? cdef.equals(CDef.Skill.智狼) : false;
    }

    /**
     * 囁きを見られる <br>
     * The group elements:[人狼, 呪狼, 智狼, C国狂人]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_ViewableWerewolfSay() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isViewableWerewolfSay();
    }

    /**
     * 囁き可能 <br>
     * The group elements:[人狼, 呪狼, 智狼, C国狂人]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_AvailableWerewolfSay() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isAvailableWerewolfSay();
    }

    /**
     * 共鳴発言を見られる <br>
     * The group elements:[共鳴者]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_ViewableSympathizeSay() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isViewableSympathizeSay();
    }

    /**
     * 共鳴発言可能 <br>
     * The group elements:[共鳴者]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_AvailableSympathizeSay() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isAvailableSympathizeSay();
    }

    /**
     * 襲撃対象に選べない <br>
     * The group elements:[人狼, 呪狼, 智狼]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_NotSelectableAttack() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isNotSelectableAttack();
    }

    /**
     * 占い結果が人狼になる <br>
     * The group elements:[人狼, 呪狼, 智狼]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_DivineResultWolf() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isDivineResultWolf();
    }

    /**
     * 霊能結果が人狼になる <br>
     * The group elements:[人狼, 呪狼, 智狼]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_PsychicResultWolf() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isPsychicResultWolf();
    }

    /**
     * 襲撃能力を持つ <br>
     * The group elements:[人狼, 呪狼, 智狼]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_HasAttackAbility() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isHasAttackAbility();
    }

    /**
     * 占い能力を持つ <br>
     * The group elements:[占い師]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_HasDivineAbility() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isHasDivineAbility();
    }

    /**
     * 役職占い能力を持つ <br>
     * The group elements:[賢者]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_HasWiseDivineAbility() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isHasWiseDivineAbility();
    }

    /**
     * 護衛能力を持つ <br>
     * The group elements:[狩人]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_HasGuardAbility() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isHasGuardAbility();
    }

    /**
     * 風来護衛能力を持つ <br>
     * The group elements:[風来狩人]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_HasWandererGuardAbility() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isHasWandererGuardAbility();
    }

    /**
     * 霊能能力を持つ <br>
     * The group elements:[霊能者]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_HasPsychicAbility() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isHasPsychicAbility();
    }

    /**
     * 役職霊能能力を持つ <br>
     * The group elements:[導師]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_HasGuruPsychicAbility() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isHasGuruPsychicAbility();
    }

    /**
     * 検死能力を持つ <br>
     * The group elements:[検死官]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_HasAutopsyAbility() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isHasAutopsyAbility();
    }

    /**
     * 襲撃役職占い能力を持つ <br>
     * The group elements:[智狼]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_HasWiseWolfAbility() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isHasWiseWolfAbility();
    }

    /**
     * パン焼き能力を持つ <br>
     * The group elements:[パン屋]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_HasBakeryAbility() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isHasBakeryAbility();
    }

    /**
     * 強運能力を持つ <br>
     * The group elements:[強運者]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_HasLuckyAbility() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isHasLuckyAbility();
    }

    /**
     * 勝敗判定時狼にカウントする <br>
     * The group elements:[人狼, 呪狼, 智狼]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_CountWolf() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isCountWolf();
    }

    /**
     * 勝敗判定時に人間としてカウントしない <br>
     * The group elements:[妖狐]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_NoCount() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isNoCount();
    }

    /**
     * おまかせ系 <br>
     * The group elements:[おまかせ, おまかせ村人陣営, おまかせ人狼陣営]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_SomeoneSkill() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isSomeoneSkill();
    }

    /**
     * 共有者としてお互いに認知できる <br>
     * The group elements:[共有者]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_RecognizableEachMason() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isRecognizableEachMason();
    }

    /**
     * 共鳴者としてお互いに認知できる <br>
     * The group elements:[共鳴者]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_RecognizableEachSympathizer() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isRecognizableEachSympathizer();
    }

    /**
     * 人狼を認知できる <br>
     * The group elements:[狂信者]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_RecognizableWolf() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isRecognizableWolf();
    }

    /**
     * 妖狐を認知できる <br>
     * The group elements:[妖狐, 背徳者]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_RecognizableFox() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isRecognizableFox();
    }

    /**
     * 襲撃耐性を持つ <br>
     * The group elements:[妖狐]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_NoDeadByAttack() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isNoDeadByAttack();
    }

    /**
     * 占いにより死亡する <br>
     * The group elements:[妖狐]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_DeadByDivine() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isDeadByDivine();
    }

    /**
     * 占いにより占った側が死亡する <br>
     * The group elements:[呪狼]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_CounterDeadByDivine() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isCounterDeadByDivine();
    }

    /**
     * 死亡時道連れにする <br>
     * The group elements:[猫又]
     * @return The determination, true or false.
     */
    public boolean isSkillCode_ForceDoubleSuicide() {
        CDef.Skill cdef = getSkillCodeAsSkill();
        return cdef != null && cdef.isForceDoubleSuicide();
    }

    /**
     * Is the value of campCode 狐陣営? <br>
     * 狐陣営
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isCampCode狐陣営() {
        CDef.Camp cdef = getCampCodeAsCamp();
        return cdef != null ? cdef.equals(CDef.Camp.狐陣営) : false;
    }

    /**
     * Is the value of campCode 恋人陣営? <br>
     * 恋人陣営
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isCampCode恋人陣営() {
        CDef.Camp cdef = getCampCodeAsCamp();
        return cdef != null ? cdef.equals(CDef.Camp.恋人陣営) : false;
    }

    /**
     * Is the value of campCode 村人陣営? <br>
     * 村人陣営
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isCampCode村人陣営() {
        CDef.Camp cdef = getCampCodeAsCamp();
        return cdef != null ? cdef.equals(CDef.Camp.村人陣営) : false;
    }

    /**
     * Is the value of campCode 人狼陣営? <br>
     * 人狼陣営
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isCampCode人狼陣営() {
        CDef.Camp cdef = getCampCodeAsCamp();
        return cdef != null ? cdef.equals(CDef.Camp.人狼陣営) : false;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    /** CAMP by my CAMP_CODE, named 'camp'. */
    protected OptionalEntity<Camp> _camp;

    /**
     * [get] CAMP by my CAMP_CODE, named 'camp'. <br>
     * Optional: alwaysPresent(), ifPresent().orElse(), get(), ...
     * @return The entity of foreign property 'camp'. (NotNull, EmptyAllowed: when e.g. null FK column, no setupSelect)
     */
    public OptionalEntity<Camp> getCamp() {
        if (_camp == null) { _camp = OptionalEntity.relationEmpty(this, "camp"); }
        return _camp;
    }

    /**
     * [set] CAMP by my CAMP_CODE, named 'camp'.
     * @param camp The entity of foreign property 'camp'. (NullAllowed)
     */
    public void setCamp(OptionalEntity<Camp> camp) {
        _camp = camp;
    }

    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    /** COMING_OUT by SKILL_CODE, named 'comingOutList'. */
    protected List<ComingOut> _comingOutList;

    /**
     * [get] COMING_OUT by SKILL_CODE, named 'comingOutList'.
     * @return The entity list of referrer property 'comingOutList'. (NotNull: even if no loading, returns empty list)
     */
    public List<ComingOut> getComingOutList() {
        if (_comingOutList == null) { _comingOutList = newReferrerList(); }
        return _comingOutList;
    }

    /**
     * [set] COMING_OUT by SKILL_CODE, named 'comingOutList'.
     * @param comingOutList The entity list of referrer property 'comingOutList'. (NullAllowed)
     */
    public void setComingOutList(List<ComingOut> comingOutList) {
        _comingOutList = comingOutList;
    }

    /** VILLAGE_PLAYER by REQUEST_SKILL_CODE, named 'villagePlayerByRequestSkillCodeList'. */
    protected List<VillagePlayer> _villagePlayerByRequestSkillCodeList;

    /**
     * [get] VILLAGE_PLAYER by REQUEST_SKILL_CODE, named 'villagePlayerByRequestSkillCodeList'.
     * @return The entity list of referrer property 'villagePlayerByRequestSkillCodeList'. (NotNull: even if no loading, returns empty list)
     */
    public List<VillagePlayer> getVillagePlayerByRequestSkillCodeList() {
        if (_villagePlayerByRequestSkillCodeList == null) { _villagePlayerByRequestSkillCodeList = newReferrerList(); }
        return _villagePlayerByRequestSkillCodeList;
    }

    /**
     * [set] VILLAGE_PLAYER by REQUEST_SKILL_CODE, named 'villagePlayerByRequestSkillCodeList'.
     * @param villagePlayerByRequestSkillCodeList The entity list of referrer property 'villagePlayerByRequestSkillCodeList'. (NullAllowed)
     */
    public void setVillagePlayerByRequestSkillCodeList(List<VillagePlayer> villagePlayerByRequestSkillCodeList) {
        _villagePlayerByRequestSkillCodeList = villagePlayerByRequestSkillCodeList;
    }

    /** VILLAGE_PLAYER by SECOND_REQUEST_SKILL_CODE, named 'villagePlayerBySecondRequestSkillCodeList'. */
    protected List<VillagePlayer> _villagePlayerBySecondRequestSkillCodeList;

    /**
     * [get] VILLAGE_PLAYER by SECOND_REQUEST_SKILL_CODE, named 'villagePlayerBySecondRequestSkillCodeList'.
     * @return The entity list of referrer property 'villagePlayerBySecondRequestSkillCodeList'. (NotNull: even if no loading, returns empty list)
     */
    public List<VillagePlayer> getVillagePlayerBySecondRequestSkillCodeList() {
        if (_villagePlayerBySecondRequestSkillCodeList == null) { _villagePlayerBySecondRequestSkillCodeList = newReferrerList(); }
        return _villagePlayerBySecondRequestSkillCodeList;
    }

    /**
     * [set] VILLAGE_PLAYER by SECOND_REQUEST_SKILL_CODE, named 'villagePlayerBySecondRequestSkillCodeList'.
     * @param villagePlayerBySecondRequestSkillCodeList The entity list of referrer property 'villagePlayerBySecondRequestSkillCodeList'. (NullAllowed)
     */
    public void setVillagePlayerBySecondRequestSkillCodeList(List<VillagePlayer> villagePlayerBySecondRequestSkillCodeList) {
        _villagePlayerBySecondRequestSkillCodeList = villagePlayerBySecondRequestSkillCodeList;
    }

    /** VILLAGE_PLAYER by SKILL_CODE, named 'villagePlayerBySkillCodeList'. */
    protected List<VillagePlayer> _villagePlayerBySkillCodeList;

    /**
     * [get] VILLAGE_PLAYER by SKILL_CODE, named 'villagePlayerBySkillCodeList'.
     * @return The entity list of referrer property 'villagePlayerBySkillCodeList'. (NotNull: even if no loading, returns empty list)
     */
    public List<VillagePlayer> getVillagePlayerBySkillCodeList() {
        if (_villagePlayerBySkillCodeList == null) { _villagePlayerBySkillCodeList = newReferrerList(); }
        return _villagePlayerBySkillCodeList;
    }

    /**
     * [set] VILLAGE_PLAYER by SKILL_CODE, named 'villagePlayerBySkillCodeList'.
     * @param villagePlayerBySkillCodeList The entity list of referrer property 'villagePlayerBySkillCodeList'. (NullAllowed)
     */
    public void setVillagePlayerBySkillCodeList(List<VillagePlayer> villagePlayerBySkillCodeList) {
        _villagePlayerBySkillCodeList = villagePlayerBySkillCodeList;
    }

    protected <ELEMENT> List<ELEMENT> newReferrerList() { // overriding to import
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected boolean doEquals(Object obj) {
        if (obj instanceof BsSkill) {
            BsSkill other = (BsSkill)obj;
            if (!xSV(_skillCode, other._skillCode)) { return false; }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int doHashCode(int initial) {
        int hs = initial;
        hs = xCH(hs, asTableDbName());
        hs = xCH(hs, _skillCode);
        return hs;
    }

    @Override
    protected String doBuildStringWithRelation(String li) {
        StringBuilder sb = new StringBuilder();
        if (_camp != null && _camp.isPresent())
        { sb.append(li).append(xbRDS(_camp, "camp")); }
        if (_comingOutList != null) { for (ComingOut et : _comingOutList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "comingOutList")); } } }
        if (_villagePlayerByRequestSkillCodeList != null) { for (VillagePlayer et : _villagePlayerByRequestSkillCodeList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "villagePlayerByRequestSkillCodeList")); } } }
        if (_villagePlayerBySecondRequestSkillCodeList != null) { for (VillagePlayer et : _villagePlayerBySecondRequestSkillCodeList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "villagePlayerBySecondRequestSkillCodeList")); } } }
        if (_villagePlayerBySkillCodeList != null) { for (VillagePlayer et : _villagePlayerBySkillCodeList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "villagePlayerBySkillCodeList")); } } }
        return sb.toString();
    }
    protected <ET extends Entity> String xbRDS(org.dbflute.optional.OptionalEntity<ET> et, String name) { // buildRelationDisplayString()
        return et.get().buildDisplayString(name, true, true);
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_skillCode));
        sb.append(dm).append(xfND(_skillName));
        sb.append(dm).append(xfND(_skillShortName));
        sb.append(dm).append(xfND(_campCode));
        sb.append(dm).append(xfND(_dispOrder));
        sb.append(dm).append(xfND(_description));
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    @Override
    protected String doBuildRelationString(String dm) {
        StringBuilder sb = new StringBuilder();
        if (_camp != null && _camp.isPresent())
        { sb.append(dm).append("camp"); }
        if (_comingOutList != null && !_comingOutList.isEmpty())
        { sb.append(dm).append("comingOutList"); }
        if (_villagePlayerByRequestSkillCodeList != null && !_villagePlayerByRequestSkillCodeList.isEmpty())
        { sb.append(dm).append("villagePlayerByRequestSkillCodeList"); }
        if (_villagePlayerBySecondRequestSkillCodeList != null && !_villagePlayerBySecondRequestSkillCodeList.isEmpty())
        { sb.append(dm).append("villagePlayerBySecondRequestSkillCodeList"); }
        if (_villagePlayerBySkillCodeList != null && !_villagePlayerBySkillCodeList.isEmpty())
        { sb.append(dm).append("villagePlayerBySkillCodeList"); }
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length()).insert(0, "(").append(")");
        }
        return sb.toString();
    }

    @Override
    public Skill clone() {
        return (Skill)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] SKILL_CODE: {PK, NotNull, VARCHAR(20), classification=Skill} <br>
     * 役職コード
     * @return The value of the column 'SKILL_CODE'. (basically NotNull if selected: for the constraint)
     */
    public String getSkillCode() {
        checkSpecifiedProperty("skillCode");
        return convertEmptyToNull(_skillCode);
    }

    /**
     * [set] SKILL_CODE: {PK, NotNull, VARCHAR(20), classification=Skill} <br>
     * 役職コード
     * @param skillCode The value of the column 'SKILL_CODE'. (basically NotNull if update: for the constraint)
     */
    protected void setSkillCode(String skillCode) {
        checkClassificationCode("SKILL_CODE", CDef.DefMeta.Skill, skillCode);
        registerModifiedProperty("skillCode");
        _skillCode = skillCode;
    }

    /**
     * [get] SKILL_NAME: {NotNull, VARCHAR(20)} <br>
     * 役職名
     * @return The value of the column 'SKILL_NAME'. (basically NotNull if selected: for the constraint)
     */
    public String getSkillName() {
        checkSpecifiedProperty("skillName");
        return convertEmptyToNull(_skillName);
    }

    /**
     * [set] SKILL_NAME: {NotNull, VARCHAR(20)} <br>
     * 役職名
     * @param skillName The value of the column 'SKILL_NAME'. (basically NotNull if update: for the constraint)
     */
    public void setSkillName(String skillName) {
        registerModifiedProperty("skillName");
        _skillName = skillName;
    }

    /**
     * [get] SKILL_SHORT_NAME: {NotNull, CHAR(1)} <br>
     * 役職略称
     * @return The value of the column 'SKILL_SHORT_NAME'. (basically NotNull if selected: for the constraint)
     */
    public String getSkillShortName() {
        checkSpecifiedProperty("skillShortName");
        return convertEmptyToNull(_skillShortName);
    }

    /**
     * [set] SKILL_SHORT_NAME: {NotNull, CHAR(1)} <br>
     * 役職略称
     * @param skillShortName The value of the column 'SKILL_SHORT_NAME'. (basically NotNull if update: for the constraint)
     */
    public void setSkillShortName(String skillShortName) {
        registerModifiedProperty("skillShortName");
        _skillShortName = skillShortName;
    }

    /**
     * [get] CAMP_CODE: {IX, NotNull, VARCHAR(20), FK to camp, classification=Camp} <br>
     * 陣営コード
     * @return The value of the column 'CAMP_CODE'. (basically NotNull if selected: for the constraint)
     */
    public String getCampCode() {
        checkSpecifiedProperty("campCode");
        return convertEmptyToNull(_campCode);
    }

    /**
     * [set] CAMP_CODE: {IX, NotNull, VARCHAR(20), FK to camp, classification=Camp} <br>
     * 陣営コード
     * @param campCode The value of the column 'CAMP_CODE'. (basically NotNull if update: for the constraint)
     */
    protected void setCampCode(String campCode) {
        checkClassificationCode("CAMP_CODE", CDef.DefMeta.Camp, campCode);
        registerModifiedProperty("campCode");
        _campCode = campCode;
    }

    /**
     * [get] DISP_ORDER: {NotNull, INT UNSIGNED(10)} <br>
     * 並び順
     * @return The value of the column 'DISP_ORDER'. (basically NotNull if selected: for the constraint)
     */
    public Integer getDispOrder() {
        checkSpecifiedProperty("dispOrder");
        return _dispOrder;
    }

    /**
     * [set] DISP_ORDER: {NotNull, INT UNSIGNED(10)} <br>
     * 並び順
     * @param dispOrder The value of the column 'DISP_ORDER'. (basically NotNull if update: for the constraint)
     */
    public void setDispOrder(Integer dispOrder) {
        registerModifiedProperty("dispOrder");
        _dispOrder = dispOrder;
    }

    /**
     * [get] DESCRIPTION: {NotNull, TEXT(65535)} <br>
     * 説明
     * @return The value of the column 'DESCRIPTION'. (basically NotNull if selected: for the constraint)
     */
    public String getDescription() {
        checkSpecifiedProperty("description");
        return convertEmptyToNull(_description);
    }

    /**
     * [set] DESCRIPTION: {NotNull, TEXT(65535)} <br>
     * 説明
     * @param description The value of the column 'DESCRIPTION'. (basically NotNull if update: for the constraint)
     */
    public void setDescription(String description) {
        registerModifiedProperty("description");
        _description = description;
    }

    /**
     * For framework so basically DON'T use this method.
     * @param skillCode The value of the column 'SKILL_CODE'. (basically NotNull if update: for the constraint)
     */
    public void mynativeMappingSkillCode(String skillCode) {
        setSkillCode(skillCode);
    }

    /**
     * For framework so basically DON'T use this method.
     * @param campCode The value of the column 'CAMP_CODE'. (basically NotNull if update: for the constraint)
     */
    public void mynativeMappingCampCode(String campCode) {
        setCampCode(campCode);
    }
}
