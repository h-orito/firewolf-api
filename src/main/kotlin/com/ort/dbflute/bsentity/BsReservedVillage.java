package com.ort.dbflute.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import com.ort.dbflute.allcommon.EntityDefinedCommonColumn;
import com.ort.dbflute.allcommon.DBMetaInstanceHandler;
import com.ort.dbflute.exentity.*;

/**
 * The entity of RESERVED_VILLAGE as TABLE. <br>
 * 予約村
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsReservedVillage extends AbstractEntity implements DomainEntity, EntityDefinedCommonColumn {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** RESERVED_VILLAGE_ID: {PK, ID, NotNull, INT UNSIGNED(10)} */
    protected Integer _reservedVillageId;

    /** CREATE_DATETIME: {NotNull, DATETIME(19)} */
    protected java.time.LocalDateTime _createDatetime;

    /** START_DATETIME: {NotNull, DATETIME(19)} */
    protected java.time.LocalDateTime _startDatetime;

    /** ORGANIZATION: {NotNull, VARCHAR(255)} */
    protected String _organization;

    /** SILENT_HOURS: {NotNull, INT UNSIGNED(10)} */
    protected Integer _silentHours;

    /** IS_AVAILABLE_DUMMY_SKILL: {NotNull, BIT} */
    protected Boolean _isAvailableDummySkill;

    /** REGISTER_DATETIME: {NotNull, DATETIME(19)} */
    protected java.time.LocalDateTime _registerDatetime;

    /** REGISTER_TRACE: {NotNull, VARCHAR(64)} */
    protected String _registerTrace;

    /** UPDATE_DATETIME: {NotNull, DATETIME(19)} */
    protected java.time.LocalDateTime _updateDatetime;

    /** UPDATE_TRACE: {NotNull, VARCHAR(64)} */
    protected String _updateTrace;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return DBMetaInstanceHandler.findDBMeta(asTableDbName());
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "reserved_village";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        if (_reservedVillageId == null) { return false; }
        return true;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    protected <ELEMENT> List<ELEMENT> newReferrerList() { // overriding to import
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected boolean doEquals(Object obj) {
        if (obj instanceof BsReservedVillage) {
            BsReservedVillage other = (BsReservedVillage)obj;
            if (!xSV(_reservedVillageId, other._reservedVillageId)) { return false; }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int doHashCode(int initial) {
        int hs = initial;
        hs = xCH(hs, asTableDbName());
        hs = xCH(hs, _reservedVillageId);
        return hs;
    }

    @Override
    protected String doBuildStringWithRelation(String li) {
        return "";
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_reservedVillageId));
        sb.append(dm).append(xfND(_createDatetime));
        sb.append(dm).append(xfND(_startDatetime));
        sb.append(dm).append(xfND(_organization));
        sb.append(dm).append(xfND(_silentHours));
        sb.append(dm).append(xfND(_isAvailableDummySkill));
        sb.append(dm).append(xfND(_registerDatetime));
        sb.append(dm).append(xfND(_registerTrace));
        sb.append(dm).append(xfND(_updateDatetime));
        sb.append(dm).append(xfND(_updateTrace));
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    @Override
    protected String doBuildRelationString(String dm) {
        return "";
    }

    @Override
    public ReservedVillage clone() {
        return (ReservedVillage)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] RESERVED_VILLAGE_ID: {PK, ID, NotNull, INT UNSIGNED(10)} <br>
     * 予約村ID
     * @return The value of the column 'RESERVED_VILLAGE_ID'. (basically NotNull if selected: for the constraint)
     */
    public Integer getReservedVillageId() {
        checkSpecifiedProperty("reservedVillageId");
        return _reservedVillageId;
    }

    /**
     * [set] RESERVED_VILLAGE_ID: {PK, ID, NotNull, INT UNSIGNED(10)} <br>
     * 予約村ID
     * @param reservedVillageId The value of the column 'RESERVED_VILLAGE_ID'. (basically NotNull if update: for the constraint)
     */
    public void setReservedVillageId(Integer reservedVillageId) {
        registerModifiedProperty("reservedVillageId");
        _reservedVillageId = reservedVillageId;
    }

    /**
     * [get] CREATE_DATETIME: {NotNull, DATETIME(19)} <br>
     * 村作成日時
     * @return The value of the column 'CREATE_DATETIME'. (basically NotNull if selected: for the constraint)
     */
    public java.time.LocalDateTime getCreateDatetime() {
        checkSpecifiedProperty("createDatetime");
        return _createDatetime;
    }

    /**
     * [set] CREATE_DATETIME: {NotNull, DATETIME(19)} <br>
     * 村作成日時
     * @param createDatetime The value of the column 'CREATE_DATETIME'. (basically NotNull if update: for the constraint)
     */
    public void setCreateDatetime(java.time.LocalDateTime createDatetime) {
        registerModifiedProperty("createDatetime");
        _createDatetime = createDatetime;
    }

    /**
     * [get] START_DATETIME: {NotNull, DATETIME(19)} <br>
     * 村開始日時
     * @return The value of the column 'START_DATETIME'. (basically NotNull if selected: for the constraint)
     */
    public java.time.LocalDateTime getStartDatetime() {
        checkSpecifiedProperty("startDatetime");
        return _startDatetime;
    }

    /**
     * [set] START_DATETIME: {NotNull, DATETIME(19)} <br>
     * 村開始日時
     * @param startDatetime The value of the column 'START_DATETIME'. (basically NotNull if update: for the constraint)
     */
    public void setStartDatetime(java.time.LocalDateTime startDatetime) {
        registerModifiedProperty("startDatetime");
        _startDatetime = startDatetime;
    }

    /**
     * [get] ORGANIZATION: {NotNull, VARCHAR(255)} <br>
     * 編成
     * @return The value of the column 'ORGANIZATION'. (basically NotNull if selected: for the constraint)
     */
    public String getOrganization() {
        checkSpecifiedProperty("organization");
        return convertEmptyToNull(_organization);
    }

    /**
     * [set] ORGANIZATION: {NotNull, VARCHAR(255)} <br>
     * 編成
     * @param organization The value of the column 'ORGANIZATION'. (basically NotNull if update: for the constraint)
     */
    public void setOrganization(String organization) {
        registerModifiedProperty("organization");
        _organization = organization;
    }

    /**
     * [get] SILENT_HOURS: {NotNull, INT UNSIGNED(10)} <br>
     * 沈黙時間
     * @return The value of the column 'SILENT_HOURS'. (basically NotNull if selected: for the constraint)
     */
    public Integer getSilentHours() {
        checkSpecifiedProperty("silentHours");
        return _silentHours;
    }

    /**
     * [set] SILENT_HOURS: {NotNull, INT UNSIGNED(10)} <br>
     * 沈黙時間
     * @param silentHours The value of the column 'SILENT_HOURS'. (basically NotNull if update: for the constraint)
     */
    public void setSilentHours(Integer silentHours) {
        registerModifiedProperty("silentHours");
        _silentHours = silentHours;
    }

    /**
     * [get] IS_AVAILABLE_DUMMY_SKILL: {NotNull, BIT} <br>
     * 役欠けありか
     * @return The value of the column 'IS_AVAILABLE_DUMMY_SKILL'. (basically NotNull if selected: for the constraint)
     */
    public Boolean getIsAvailableDummySkill() {
        checkSpecifiedProperty("isAvailableDummySkill");
        return _isAvailableDummySkill;
    }

    /**
     * [set] IS_AVAILABLE_DUMMY_SKILL: {NotNull, BIT} <br>
     * 役欠けありか
     * @param isAvailableDummySkill The value of the column 'IS_AVAILABLE_DUMMY_SKILL'. (basically NotNull if update: for the constraint)
     */
    public void setIsAvailableDummySkill(Boolean isAvailableDummySkill) {
        registerModifiedProperty("isAvailableDummySkill");
        _isAvailableDummySkill = isAvailableDummySkill;
    }

    /**
     * [get] REGISTER_DATETIME: {NotNull, DATETIME(19)} <br>
     * 登録日時
     * @return The value of the column 'REGISTER_DATETIME'. (basically NotNull if selected: for the constraint)
     */
    public java.time.LocalDateTime getRegisterDatetime() {
        checkSpecifiedProperty("registerDatetime");
        return _registerDatetime;
    }

    /**
     * [set] REGISTER_DATETIME: {NotNull, DATETIME(19)} <br>
     * 登録日時
     * @param registerDatetime The value of the column 'REGISTER_DATETIME'. (basically NotNull if update: for the constraint)
     */
    public void setRegisterDatetime(java.time.LocalDateTime registerDatetime) {
        registerModifiedProperty("registerDatetime");
        _registerDatetime = registerDatetime;
    }

    /**
     * [get] REGISTER_TRACE: {NotNull, VARCHAR(64)} <br>
     * 登録トレース
     * @return The value of the column 'REGISTER_TRACE'. (basically NotNull if selected: for the constraint)
     */
    public String getRegisterTrace() {
        checkSpecifiedProperty("registerTrace");
        return convertEmptyToNull(_registerTrace);
    }

    /**
     * [set] REGISTER_TRACE: {NotNull, VARCHAR(64)} <br>
     * 登録トレース
     * @param registerTrace The value of the column 'REGISTER_TRACE'. (basically NotNull if update: for the constraint)
     */
    public void setRegisterTrace(String registerTrace) {
        registerModifiedProperty("registerTrace");
        _registerTrace = registerTrace;
    }

    /**
     * [get] UPDATE_DATETIME: {NotNull, DATETIME(19)} <br>
     * 更新日時
     * @return The value of the column 'UPDATE_DATETIME'. (basically NotNull if selected: for the constraint)
     */
    public java.time.LocalDateTime getUpdateDatetime() {
        checkSpecifiedProperty("updateDatetime");
        return _updateDatetime;
    }

    /**
     * [set] UPDATE_DATETIME: {NotNull, DATETIME(19)} <br>
     * 更新日時
     * @param updateDatetime The value of the column 'UPDATE_DATETIME'. (basically NotNull if update: for the constraint)
     */
    public void setUpdateDatetime(java.time.LocalDateTime updateDatetime) {
        registerModifiedProperty("updateDatetime");
        _updateDatetime = updateDatetime;
    }

    /**
     * [get] UPDATE_TRACE: {NotNull, VARCHAR(64)} <br>
     * 更新トレース
     * @return The value of the column 'UPDATE_TRACE'. (basically NotNull if selected: for the constraint)
     */
    public String getUpdateTrace() {
        checkSpecifiedProperty("updateTrace");
        return convertEmptyToNull(_updateTrace);
    }

    /**
     * [set] UPDATE_TRACE: {NotNull, VARCHAR(64)} <br>
     * 更新トレース
     * @param updateTrace The value of the column 'UPDATE_TRACE'. (basically NotNull if update: for the constraint)
     */
    public void setUpdateTrace(String updateTrace) {
        registerModifiedProperty("updateTrace");
        _updateTrace = updateTrace;
    }
}
