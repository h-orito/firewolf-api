package com.ort.dbflute.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import com.ort.dbflute.allcommon.DBMetaInstanceHandler;
import com.ort.dbflute.allcommon.CDef;
import com.ort.dbflute.exentity.*;

/**
 * The entity of VILLAGE_PLAYER_STATUS_TYPE as TABLE. <br>
 * 村参加者ステータス種別
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsVillagePlayerStatusType extends AbstractEntity implements DomainEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** VILLAGE_PLAYER_STATUS_TYPE_CODE: {PK, NotNull, VARCHAR(20), classification=VillagePlayerStatusType} */
    protected String _villagePlayerStatusTypeCode;

    /** VILLAGE_PLAYER_STATUS_TYPE_NAME: {NotNull, VARCHAR(20)} */
    protected String _villagePlayerStatusTypeName;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return DBMetaInstanceHandler.findDBMeta(asTableDbName());
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "village_player_status_type";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        if (_villagePlayerStatusTypeCode == null) { return false; }
        return true;
    }

    // ===================================================================================
    //                                                             Classification Property
    //                                                             =======================
    /**
     * Get the value of villagePlayerStatusTypeCode as the classification of VillagePlayerStatusType. <br>
     * VILLAGE_PLAYER_STATUS_TYPE_CODE: {PK, NotNull, VARCHAR(20), classification=VillagePlayerStatusType} <br>
     * 村参加者ステータス種別
     * <p>It's treated as case insensitive and if the code value is null, it returns null.</p>
     * @return The instance of classification definition (as ENUM type). (NullAllowed: when the column value is null)
     */
    public CDef.VillagePlayerStatusType getVillagePlayerStatusTypeCodeAsVillagePlayerStatusType() {
        return CDef.VillagePlayerStatusType.of(getVillagePlayerStatusTypeCode()).orElse(null);
    }

    /**
     * Set the value of villagePlayerStatusTypeCode as the classification of VillagePlayerStatusType. <br>
     * VILLAGE_PLAYER_STATUS_TYPE_CODE: {PK, NotNull, VARCHAR(20), classification=VillagePlayerStatusType} <br>
     * 村参加者ステータス種別
     * @param cdef The instance of classification definition (as ENUM type). (NullAllowed: if null, null value is set to the column)
     */
    public void setVillagePlayerStatusTypeCodeAsVillagePlayerStatusType(CDef.VillagePlayerStatusType cdef) {
        setVillagePlayerStatusTypeCode(cdef != null ? cdef.code() : null);
    }

    // ===================================================================================
    //                                                              Classification Setting
    //                                                              ======================
    /**
     * Set the value of villagePlayerStatusTypeCode as 恋絆 (LOVE). <br>
     * 恋絆
     */
    public void setVillagePlayerStatusTypeCode_恋絆() {
        setVillagePlayerStatusTypeCodeAsVillagePlayerStatusType(CDef.VillagePlayerStatusType.恋絆);
    }

    // ===================================================================================
    //                                                        Classification Determination
    //                                                        ============================
    /**
     * Is the value of villagePlayerStatusTypeCode 恋絆? <br>
     * 恋絆
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isVillagePlayerStatusTypeCode恋絆() {
        CDef.VillagePlayerStatusType cdef = getVillagePlayerStatusTypeCodeAsVillagePlayerStatusType();
        return cdef != null ? cdef.equals(CDef.VillagePlayerStatusType.恋絆) : false;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    /** VILLAGE_PLAYER_STATUS by VILLAGE_PLAYER_STATUS_CODE, named 'villagePlayerStatusList'. */
    protected List<VillagePlayerStatus> _villagePlayerStatusList;

    /**
     * [get] VILLAGE_PLAYER_STATUS by VILLAGE_PLAYER_STATUS_CODE, named 'villagePlayerStatusList'.
     * @return The entity list of referrer property 'villagePlayerStatusList'. (NotNull: even if no loading, returns empty list)
     */
    public List<VillagePlayerStatus> getVillagePlayerStatusList() {
        if (_villagePlayerStatusList == null) { _villagePlayerStatusList = newReferrerList(); }
        return _villagePlayerStatusList;
    }

    /**
     * [set] VILLAGE_PLAYER_STATUS by VILLAGE_PLAYER_STATUS_CODE, named 'villagePlayerStatusList'.
     * @param villagePlayerStatusList The entity list of referrer property 'villagePlayerStatusList'. (NullAllowed)
     */
    public void setVillagePlayerStatusList(List<VillagePlayerStatus> villagePlayerStatusList) {
        _villagePlayerStatusList = villagePlayerStatusList;
    }

    protected <ELEMENT> List<ELEMENT> newReferrerList() { // overriding to import
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected boolean doEquals(Object obj) {
        if (obj instanceof BsVillagePlayerStatusType) {
            BsVillagePlayerStatusType other = (BsVillagePlayerStatusType)obj;
            if (!xSV(_villagePlayerStatusTypeCode, other._villagePlayerStatusTypeCode)) { return false; }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int doHashCode(int initial) {
        int hs = initial;
        hs = xCH(hs, asTableDbName());
        hs = xCH(hs, _villagePlayerStatusTypeCode);
        return hs;
    }

    @Override
    protected String doBuildStringWithRelation(String li) {
        StringBuilder sb = new StringBuilder();
        if (_villagePlayerStatusList != null) { for (VillagePlayerStatus et : _villagePlayerStatusList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "villagePlayerStatusList")); } } }
        return sb.toString();
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_villagePlayerStatusTypeCode));
        sb.append(dm).append(xfND(_villagePlayerStatusTypeName));
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    @Override
    protected String doBuildRelationString(String dm) {
        StringBuilder sb = new StringBuilder();
        if (_villagePlayerStatusList != null && !_villagePlayerStatusList.isEmpty())
        { sb.append(dm).append("villagePlayerStatusList"); }
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length()).insert(0, "(").append(")");
        }
        return sb.toString();
    }

    @Override
    public VillagePlayerStatusType clone() {
        return (VillagePlayerStatusType)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] VILLAGE_PLAYER_STATUS_TYPE_CODE: {PK, NotNull, VARCHAR(20), classification=VillagePlayerStatusType} <br>
     * 村参加者ステータス種別コード
     * @return The value of the column 'VILLAGE_PLAYER_STATUS_TYPE_CODE'. (basically NotNull if selected: for the constraint)
     */
    public String getVillagePlayerStatusTypeCode() {
        checkSpecifiedProperty("villagePlayerStatusTypeCode");
        return convertEmptyToNull(_villagePlayerStatusTypeCode);
    }

    /**
     * [set] VILLAGE_PLAYER_STATUS_TYPE_CODE: {PK, NotNull, VARCHAR(20), classification=VillagePlayerStatusType} <br>
     * 村参加者ステータス種別コード
     * @param villagePlayerStatusTypeCode The value of the column 'VILLAGE_PLAYER_STATUS_TYPE_CODE'. (basically NotNull if update: for the constraint)
     */
    protected void setVillagePlayerStatusTypeCode(String villagePlayerStatusTypeCode) {
        checkClassificationCode("VILLAGE_PLAYER_STATUS_TYPE_CODE", CDef.DefMeta.VillagePlayerStatusType, villagePlayerStatusTypeCode);
        registerModifiedProperty("villagePlayerStatusTypeCode");
        _villagePlayerStatusTypeCode = villagePlayerStatusTypeCode;
    }

    /**
     * [get] VILLAGE_PLAYER_STATUS_TYPE_NAME: {NotNull, VARCHAR(20)} <br>
     * 村参加者ステータス種別名
     * @return The value of the column 'VILLAGE_PLAYER_STATUS_TYPE_NAME'. (basically NotNull if selected: for the constraint)
     */
    public String getVillagePlayerStatusTypeName() {
        checkSpecifiedProperty("villagePlayerStatusTypeName");
        return convertEmptyToNull(_villagePlayerStatusTypeName);
    }

    /**
     * [set] VILLAGE_PLAYER_STATUS_TYPE_NAME: {NotNull, VARCHAR(20)} <br>
     * 村参加者ステータス種別名
     * @param villagePlayerStatusTypeName The value of the column 'VILLAGE_PLAYER_STATUS_TYPE_NAME'. (basically NotNull if update: for the constraint)
     */
    public void setVillagePlayerStatusTypeName(String villagePlayerStatusTypeName) {
        registerModifiedProperty("villagePlayerStatusTypeName");
        _villagePlayerStatusTypeName = villagePlayerStatusTypeName;
    }

    /**
     * For framework so basically DON'T use this method.
     * @param villagePlayerStatusTypeCode The value of the column 'VILLAGE_PLAYER_STATUS_TYPE_CODE'. (basically NotNull if update: for the constraint)
     */
    public void mynativeMappingVillagePlayerStatusTypeCode(String villagePlayerStatusTypeCode) {
        setVillagePlayerStatusTypeCode(villagePlayerStatusTypeCode);
    }
}
