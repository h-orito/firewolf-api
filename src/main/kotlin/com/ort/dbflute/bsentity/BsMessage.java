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
 * The entity of MESSAGE as TABLE. <br>
 * メッセージ
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsMessage extends AbstractEntity implements DomainEntity, EntityDefinedCommonColumn {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** VILLAGE_ID: {PK, NotNull, INT UNSIGNED(10)} */
    protected Integer _villageId;

    /** MESSAGE_NUMBER: {PK, NotNull, INT UNSIGNED(10)} */
    protected Integer _messageNumber;

    /** MESSAGE_TYPE_CODE: {PK, IX, NotNull, VARCHAR(20)} */
    protected String _messageTypeCode;

    /** MESSAGE_UNIXTIMESTAMP_MILLI: {IX, NotNull, BIGINT UNSIGNED(20)} */
    protected Long _messageUnixtimestampMilli;

    /** VILLAGE_DAY_ID: {IX, NotNull, INT UNSIGNED(10)} */
    protected Integer _villageDayId;

    /** VILLAGE_PLAYER_ID: {IX, INT UNSIGNED(10)} */
    protected Integer _villagePlayerId;

    /** TO_VILLAGE_PLAYER_ID: {IX, INT UNSIGNED(10)} */
    protected Integer _toVillagePlayerId;

    /** PLAYER_ID: {IX, INT UNSIGNED(10)} */
    protected Integer _playerId;

    /** MESSAGE_CONTENT: {NotNull, VARCHAR(10000)} */
    protected String _messageContent;

    /** MESSAGE_DATETIME: {NotNull, DATETIME(19)} */
    protected java.time.LocalDateTime _messageDatetime;

    /** MESSAGE_COUNT: {INT UNSIGNED(10)} */
    protected Integer _messageCount;

    /** IS_CONVERT_DISABLE: {NotNull, BIT} */
    protected Boolean _isConvertDisable;

    /** FACE_TYPE_CODE: {IX, VARCHAR(20)} */
    protected String _faceTypeCode;

    /** CHARA_NAME: {VARCHAR(40)} */
    protected String _charaName;

    /** CHARA_SHORT_NAME: {CHAR(1)} */
    protected String _charaShortName;

    /** TO_CHARA_NAME: {VARCHAR(40)} */
    protected String _toCharaName;

    /** TO_CHARA_SHORT_NAME: {CHAR(1)} */
    protected String _toCharaShortName;

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
        return "message";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        if (_villageId == null) { return false; }
        if (_messageNumber == null) { return false; }
        if (_messageTypeCode == null) { return false; }
        return true;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    /** MESSAGE_SENDTO by VILLAGE_ID, MESSAGE_TYPE_CODE, MESSAGE_NUMBER, named 'messageSendtoList'. */
    protected List<MessageSendto> _messageSendtoList;

    /**
     * [get] MESSAGE_SENDTO by VILLAGE_ID, MESSAGE_TYPE_CODE, MESSAGE_NUMBER, named 'messageSendtoList'.
     * @return The entity list of referrer property 'messageSendtoList'. (NotNull: even if no loading, returns empty list)
     */
    public List<MessageSendto> getMessageSendtoList() {
        if (_messageSendtoList == null) { _messageSendtoList = newReferrerList(); }
        return _messageSendtoList;
    }

    /**
     * [set] MESSAGE_SENDTO by VILLAGE_ID, MESSAGE_TYPE_CODE, MESSAGE_NUMBER, named 'messageSendtoList'.
     * @param messageSendtoList The entity list of referrer property 'messageSendtoList'. (NullAllowed)
     */
    public void setMessageSendtoList(List<MessageSendto> messageSendtoList) {
        _messageSendtoList = messageSendtoList;
    }

    protected <ELEMENT> List<ELEMENT> newReferrerList() { // overriding to import
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected boolean doEquals(Object obj) {
        if (obj instanceof BsMessage) {
            BsMessage other = (BsMessage)obj;
            if (!xSV(_villageId, other._villageId)) { return false; }
            if (!xSV(_messageNumber, other._messageNumber)) { return false; }
            if (!xSV(_messageTypeCode, other._messageTypeCode)) { return false; }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int doHashCode(int initial) {
        int hs = initial;
        hs = xCH(hs, asTableDbName());
        hs = xCH(hs, _villageId);
        hs = xCH(hs, _messageNumber);
        hs = xCH(hs, _messageTypeCode);
        return hs;
    }

    @Override
    protected String doBuildStringWithRelation(String li) {
        StringBuilder sb = new StringBuilder();
        if (_messageSendtoList != null) { for (MessageSendto et : _messageSendtoList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "messageSendtoList")); } } }
        return sb.toString();
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_villageId));
        sb.append(dm).append(xfND(_messageNumber));
        sb.append(dm).append(xfND(_messageTypeCode));
        sb.append(dm).append(xfND(_messageUnixtimestampMilli));
        sb.append(dm).append(xfND(_villageDayId));
        sb.append(dm).append(xfND(_villagePlayerId));
        sb.append(dm).append(xfND(_toVillagePlayerId));
        sb.append(dm).append(xfND(_playerId));
        sb.append(dm).append(xfND(_messageContent));
        sb.append(dm).append(xfND(_messageDatetime));
        sb.append(dm).append(xfND(_messageCount));
        sb.append(dm).append(xfND(_isConvertDisable));
        sb.append(dm).append(xfND(_faceTypeCode));
        sb.append(dm).append(xfND(_charaName));
        sb.append(dm).append(xfND(_charaShortName));
        sb.append(dm).append(xfND(_toCharaName));
        sb.append(dm).append(xfND(_toCharaShortName));
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
        StringBuilder sb = new StringBuilder();
        if (_messageSendtoList != null && !_messageSendtoList.isEmpty())
        { sb.append(dm).append("messageSendtoList"); }
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length()).insert(0, "(").append(")");
        }
        return sb.toString();
    }

    @Override
    public Message clone() {
        return (Message)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] VILLAGE_ID: {PK, NotNull, INT UNSIGNED(10)} <br>
     * 村ID
     * @return The value of the column 'VILLAGE_ID'. (basically NotNull if selected: for the constraint)
     */
    public Integer getVillageId() {
        checkSpecifiedProperty("villageId");
        return _villageId;
    }

    /**
     * [set] VILLAGE_ID: {PK, NotNull, INT UNSIGNED(10)} <br>
     * 村ID
     * @param villageId The value of the column 'VILLAGE_ID'. (basically NotNull if update: for the constraint)
     */
    public void setVillageId(Integer villageId) {
        registerModifiedProperty("villageId");
        _villageId = villageId;
    }

    /**
     * [get] MESSAGE_NUMBER: {PK, NotNull, INT UNSIGNED(10)} <br>
     * メッセージ番号
     * @return The value of the column 'MESSAGE_NUMBER'. (basically NotNull if selected: for the constraint)
     */
    public Integer getMessageNumber() {
        checkSpecifiedProperty("messageNumber");
        return _messageNumber;
    }

    /**
     * [set] MESSAGE_NUMBER: {PK, NotNull, INT UNSIGNED(10)} <br>
     * メッセージ番号
     * @param messageNumber The value of the column 'MESSAGE_NUMBER'. (basically NotNull if update: for the constraint)
     */
    public void setMessageNumber(Integer messageNumber) {
        registerModifiedProperty("messageNumber");
        _messageNumber = messageNumber;
    }

    /**
     * [get] MESSAGE_TYPE_CODE: {PK, IX, NotNull, VARCHAR(20)} <br>
     * メッセージ種別コード
     * @return The value of the column 'MESSAGE_TYPE_CODE'. (basically NotNull if selected: for the constraint)
     */
    public String getMessageTypeCode() {
        checkSpecifiedProperty("messageTypeCode");
        return convertEmptyToNull(_messageTypeCode);
    }

    /**
     * [set] MESSAGE_TYPE_CODE: {PK, IX, NotNull, VARCHAR(20)} <br>
     * メッセージ種別コード
     * @param messageTypeCode The value of the column 'MESSAGE_TYPE_CODE'. (basically NotNull if update: for the constraint)
     */
    public void setMessageTypeCode(String messageTypeCode) {
        registerModifiedProperty("messageTypeCode");
        _messageTypeCode = messageTypeCode;
    }

    /**
     * [get] MESSAGE_UNIXTIMESTAMP_MILLI: {IX, NotNull, BIGINT UNSIGNED(20)} <br>
     * メッセージUNIXタイムスタンプミリ秒
     * @return The value of the column 'MESSAGE_UNIXTIMESTAMP_MILLI'. (basically NotNull if selected: for the constraint)
     */
    public Long getMessageUnixtimestampMilli() {
        checkSpecifiedProperty("messageUnixtimestampMilli");
        return _messageUnixtimestampMilli;
    }

    /**
     * [set] MESSAGE_UNIXTIMESTAMP_MILLI: {IX, NotNull, BIGINT UNSIGNED(20)} <br>
     * メッセージUNIXタイムスタンプミリ秒
     * @param messageUnixtimestampMilli The value of the column 'MESSAGE_UNIXTIMESTAMP_MILLI'. (basically NotNull if update: for the constraint)
     */
    public void setMessageUnixtimestampMilli(Long messageUnixtimestampMilli) {
        registerModifiedProperty("messageUnixtimestampMilli");
        _messageUnixtimestampMilli = messageUnixtimestampMilli;
    }

    /**
     * [get] VILLAGE_DAY_ID: {IX, NotNull, INT UNSIGNED(10)} <br>
     * 村日付ID
     * @return The value of the column 'VILLAGE_DAY_ID'. (basically NotNull if selected: for the constraint)
     */
    public Integer getVillageDayId() {
        checkSpecifiedProperty("villageDayId");
        return _villageDayId;
    }

    /**
     * [set] VILLAGE_DAY_ID: {IX, NotNull, INT UNSIGNED(10)} <br>
     * 村日付ID
     * @param villageDayId The value of the column 'VILLAGE_DAY_ID'. (basically NotNull if update: for the constraint)
     */
    public void setVillageDayId(Integer villageDayId) {
        registerModifiedProperty("villageDayId");
        _villageDayId = villageDayId;
    }

    /**
     * [get] VILLAGE_PLAYER_ID: {IX, INT UNSIGNED(10)} <br>
     * 村参加者ID
     * @return The value of the column 'VILLAGE_PLAYER_ID'. (NullAllowed even if selected: for no constraint)
     */
    public Integer getVillagePlayerId() {
        checkSpecifiedProperty("villagePlayerId");
        return _villagePlayerId;
    }

    /**
     * [set] VILLAGE_PLAYER_ID: {IX, INT UNSIGNED(10)} <br>
     * 村参加者ID
     * @param villagePlayerId The value of the column 'VILLAGE_PLAYER_ID'. (NullAllowed: null update allowed for no constraint)
     */
    public void setVillagePlayerId(Integer villagePlayerId) {
        registerModifiedProperty("villagePlayerId");
        _villagePlayerId = villagePlayerId;
    }

    /**
     * [get] TO_VILLAGE_PLAYER_ID: {IX, INT UNSIGNED(10)} <br>
     * 秘話相手の村参加者ID
     * @return The value of the column 'TO_VILLAGE_PLAYER_ID'. (NullAllowed even if selected: for no constraint)
     */
    public Integer getToVillagePlayerId() {
        checkSpecifiedProperty("toVillagePlayerId");
        return _toVillagePlayerId;
    }

    /**
     * [set] TO_VILLAGE_PLAYER_ID: {IX, INT UNSIGNED(10)} <br>
     * 秘話相手の村参加者ID
     * @param toVillagePlayerId The value of the column 'TO_VILLAGE_PLAYER_ID'. (NullAllowed: null update allowed for no constraint)
     */
    public void setToVillagePlayerId(Integer toVillagePlayerId) {
        registerModifiedProperty("toVillagePlayerId");
        _toVillagePlayerId = toVillagePlayerId;
    }

    /**
     * [get] PLAYER_ID: {IX, INT UNSIGNED(10)} <br>
     * プレイヤーID
     * @return The value of the column 'PLAYER_ID'. (NullAllowed even if selected: for no constraint)
     */
    public Integer getPlayerId() {
        checkSpecifiedProperty("playerId");
        return _playerId;
    }

    /**
     * [set] PLAYER_ID: {IX, INT UNSIGNED(10)} <br>
     * プレイヤーID
     * @param playerId The value of the column 'PLAYER_ID'. (NullAllowed: null update allowed for no constraint)
     */
    public void setPlayerId(Integer playerId) {
        registerModifiedProperty("playerId");
        _playerId = playerId;
    }

    /**
     * [get] MESSAGE_CONTENT: {NotNull, VARCHAR(10000)} <br>
     * メッセージ内容
     * @return The value of the column 'MESSAGE_CONTENT'. (basically NotNull if selected: for the constraint)
     */
    public String getMessageContent() {
        checkSpecifiedProperty("messageContent");
        return convertEmptyToNull(_messageContent);
    }

    /**
     * [set] MESSAGE_CONTENT: {NotNull, VARCHAR(10000)} <br>
     * メッセージ内容
     * @param messageContent The value of the column 'MESSAGE_CONTENT'. (basically NotNull if update: for the constraint)
     */
    public void setMessageContent(String messageContent) {
        registerModifiedProperty("messageContent");
        _messageContent = messageContent;
    }

    /**
     * [get] MESSAGE_DATETIME: {NotNull, DATETIME(19)} <br>
     * メッセージ日時
     * @return The value of the column 'MESSAGE_DATETIME'. (basically NotNull if selected: for the constraint)
     */
    public java.time.LocalDateTime getMessageDatetime() {
        checkSpecifiedProperty("messageDatetime");
        return _messageDatetime;
    }

    /**
     * [set] MESSAGE_DATETIME: {NotNull, DATETIME(19)} <br>
     * メッセージ日時
     * @param messageDatetime The value of the column 'MESSAGE_DATETIME'. (basically NotNull if update: for the constraint)
     */
    public void setMessageDatetime(java.time.LocalDateTime messageDatetime) {
        registerModifiedProperty("messageDatetime");
        _messageDatetime = messageDatetime;
    }

    /**
     * [get] MESSAGE_COUNT: {INT UNSIGNED(10)} <br>
     * 何回目の発言か : その日のその発言種別において何回目の発言か
     * @return The value of the column 'MESSAGE_COUNT'. (NullAllowed even if selected: for no constraint)
     */
    public Integer getMessageCount() {
        checkSpecifiedProperty("messageCount");
        return _messageCount;
    }

    /**
     * [set] MESSAGE_COUNT: {INT UNSIGNED(10)} <br>
     * 何回目の発言か : その日のその発言種別において何回目の発言か
     * @param messageCount The value of the column 'MESSAGE_COUNT'. (NullAllowed: null update allowed for no constraint)
     */
    public void setMessageCount(Integer messageCount) {
        registerModifiedProperty("messageCount");
        _messageCount = messageCount;
    }

    /**
     * [get] IS_CONVERT_DISABLE: {NotNull, BIT} <br>
     * 変換無効か
     * @return The value of the column 'IS_CONVERT_DISABLE'. (basically NotNull if selected: for the constraint)
     */
    public Boolean getIsConvertDisable() {
        checkSpecifiedProperty("isConvertDisable");
        return _isConvertDisable;
    }

    /**
     * [set] IS_CONVERT_DISABLE: {NotNull, BIT} <br>
     * 変換無効か
     * @param isConvertDisable The value of the column 'IS_CONVERT_DISABLE'. (basically NotNull if update: for the constraint)
     */
    public void setIsConvertDisable(Boolean isConvertDisable) {
        registerModifiedProperty("isConvertDisable");
        _isConvertDisable = isConvertDisable;
    }

    /**
     * [get] FACE_TYPE_CODE: {IX, VARCHAR(20)} <br>
     * 表情種別コード
     * @return The value of the column 'FACE_TYPE_CODE'. (NullAllowed even if selected: for no constraint)
     */
    public String getFaceTypeCode() {
        checkSpecifiedProperty("faceTypeCode");
        return convertEmptyToNull(_faceTypeCode);
    }

    /**
     * [set] FACE_TYPE_CODE: {IX, VARCHAR(20)} <br>
     * 表情種別コード
     * @param faceTypeCode The value of the column 'FACE_TYPE_CODE'. (NullAllowed: null update allowed for no constraint)
     */
    public void setFaceTypeCode(String faceTypeCode) {
        registerModifiedProperty("faceTypeCode");
        _faceTypeCode = faceTypeCode;
    }

    /**
     * [get] CHARA_NAME: {VARCHAR(40)} <br>
     * キャラクター名
     * @return The value of the column 'CHARA_NAME'. (NullAllowed even if selected: for no constraint)
     */
    public String getCharaName() {
        checkSpecifiedProperty("charaName");
        return convertEmptyToNull(_charaName);
    }

    /**
     * [set] CHARA_NAME: {VARCHAR(40)} <br>
     * キャラクター名
     * @param charaName The value of the column 'CHARA_NAME'. (NullAllowed: null update allowed for no constraint)
     */
    public void setCharaName(String charaName) {
        registerModifiedProperty("charaName");
        _charaName = charaName;
    }

    /**
     * [get] CHARA_SHORT_NAME: {CHAR(1)} <br>
     * キャラクター略称
     * @return The value of the column 'CHARA_SHORT_NAME'. (NullAllowed even if selected: for no constraint)
     */
    public String getCharaShortName() {
        checkSpecifiedProperty("charaShortName");
        return convertEmptyToNull(_charaShortName);
    }

    /**
     * [set] CHARA_SHORT_NAME: {CHAR(1)} <br>
     * キャラクター略称
     * @param charaShortName The value of the column 'CHARA_SHORT_NAME'. (NullAllowed: null update allowed for no constraint)
     */
    public void setCharaShortName(String charaShortName) {
        registerModifiedProperty("charaShortName");
        _charaShortName = charaShortName;
    }

    /**
     * [get] TO_CHARA_NAME: {VARCHAR(40)} <br>
     * 秘話相手のキャラクター名
     * @return The value of the column 'TO_CHARA_NAME'. (NullAllowed even if selected: for no constraint)
     */
    public String getToCharaName() {
        checkSpecifiedProperty("toCharaName");
        return convertEmptyToNull(_toCharaName);
    }

    /**
     * [set] TO_CHARA_NAME: {VARCHAR(40)} <br>
     * 秘話相手のキャラクター名
     * @param toCharaName The value of the column 'TO_CHARA_NAME'. (NullAllowed: null update allowed for no constraint)
     */
    public void setToCharaName(String toCharaName) {
        registerModifiedProperty("toCharaName");
        _toCharaName = toCharaName;
    }

    /**
     * [get] TO_CHARA_SHORT_NAME: {CHAR(1)} <br>
     * 秘話相手のキャラクター略称
     * @return The value of the column 'TO_CHARA_SHORT_NAME'. (NullAllowed even if selected: for no constraint)
     */
    public String getToCharaShortName() {
        checkSpecifiedProperty("toCharaShortName");
        return convertEmptyToNull(_toCharaShortName);
    }

    /**
     * [set] TO_CHARA_SHORT_NAME: {CHAR(1)} <br>
     * 秘話相手のキャラクター略称
     * @param toCharaShortName The value of the column 'TO_CHARA_SHORT_NAME'. (NullAllowed: null update allowed for no constraint)
     */
    public void setToCharaShortName(String toCharaShortName) {
        registerModifiedProperty("toCharaShortName");
        _toCharaShortName = toCharaShortName;
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
