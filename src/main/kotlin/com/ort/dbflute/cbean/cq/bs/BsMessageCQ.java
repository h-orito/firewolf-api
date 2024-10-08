package com.ort.dbflute.cbean.cq.bs;

import java.util.Map;

import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.*;
import org.dbflute.cbean.coption.*;
import org.dbflute.cbean.scoping.*;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.exception.IllegalConditionBeanOperationException;
import com.ort.dbflute.cbean.cq.ciq.*;
import com.ort.dbflute.cbean.*;
import com.ort.dbflute.cbean.cq.*;

/**
 * The base condition-query of message.
 * @author DBFlute(AutoGenerator)
 */
public class BsMessageCQ extends AbstractBsMessageCQ {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected MessageCIQ _inlineQuery;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BsMessageCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                 InlineView/OrClause
    //                                                                 ===================
    /**
     * Prepare InlineView query. <br>
     * {select ... from ... left outer join (select * from message) where FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">inline()</span>.setFoo...;
     * </pre>
     * @return The condition-query for InlineView query. (NotNull)
     */
    public MessageCIQ inline() {
        if (_inlineQuery == null) { _inlineQuery = xcreateCIQ(); }
        _inlineQuery.xsetOnClause(false); return _inlineQuery;
    }

    protected MessageCIQ xcreateCIQ() {
        MessageCIQ ciq = xnewCIQ();
        ciq.xsetBaseCB(_baseCB);
        return ciq;
    }

    protected MessageCIQ xnewCIQ() {
        return new MessageCIQ(xgetReferrerQuery(), xgetSqlClause(), xgetAliasName(), xgetNestLevel(), this);
    }

    /**
     * Prepare OnClause query. <br>
     * {select ... from ... left outer join message on ... and FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">on()</span>.setFoo...;
     * </pre>
     * @return The condition-query for OnClause query. (NotNull)
     * @throws IllegalConditionBeanOperationException When this condition-query is base query.
     */
    public MessageCIQ on() {
        if (isBaseQuery()) { throw new IllegalConditionBeanOperationException("OnClause for local table is unavailable!"); }
        MessageCIQ inlineQuery = inline(); inlineQuery.xsetOnClause(true); return inlineQuery;
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    protected ConditionValue _villageId;
    public ConditionValue xdfgetVillageId()
    { if (_villageId == null) { _villageId = nCV(); }
      return _villageId; }
    protected ConditionValue xgetCValueVillageId() { return xdfgetVillageId(); }

    /**
     * Add order-by as ascend. <br>
     * VILLAGE_ID: {PK, NotNull, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_VillageId_Asc() { regOBA("VILLAGE_ID"); return this; }

    /**
     * Add order-by as descend. <br>
     * VILLAGE_ID: {PK, NotNull, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_VillageId_Desc() { regOBD("VILLAGE_ID"); return this; }

    protected ConditionValue _messageNumber;
    public ConditionValue xdfgetMessageNumber()
    { if (_messageNumber == null) { _messageNumber = nCV(); }
      return _messageNumber; }
    protected ConditionValue xgetCValueMessageNumber() { return xdfgetMessageNumber(); }

    /**
     * Add order-by as ascend. <br>
     * MESSAGE_NUMBER: {PK, NotNull, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_MessageNumber_Asc() { regOBA("MESSAGE_NUMBER"); return this; }

    /**
     * Add order-by as descend. <br>
     * MESSAGE_NUMBER: {PK, NotNull, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_MessageNumber_Desc() { regOBD("MESSAGE_NUMBER"); return this; }

    protected ConditionValue _messageTypeCode;
    public ConditionValue xdfgetMessageTypeCode()
    { if (_messageTypeCode == null) { _messageTypeCode = nCV(); }
      return _messageTypeCode; }
    protected ConditionValue xgetCValueMessageTypeCode() { return xdfgetMessageTypeCode(); }

    /**
     * Add order-by as ascend. <br>
     * MESSAGE_TYPE_CODE: {PK, IX, NotNull, VARCHAR(20)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_MessageTypeCode_Asc() { regOBA("MESSAGE_TYPE_CODE"); return this; }

    /**
     * Add order-by as descend. <br>
     * MESSAGE_TYPE_CODE: {PK, IX, NotNull, VARCHAR(20)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_MessageTypeCode_Desc() { regOBD("MESSAGE_TYPE_CODE"); return this; }

    protected ConditionValue _messageUnixtimestampMilli;
    public ConditionValue xdfgetMessageUnixtimestampMilli()
    { if (_messageUnixtimestampMilli == null) { _messageUnixtimestampMilli = nCV(); }
      return _messageUnixtimestampMilli; }
    protected ConditionValue xgetCValueMessageUnixtimestampMilli() { return xdfgetMessageUnixtimestampMilli(); }

    /**
     * Add order-by as ascend. <br>
     * MESSAGE_UNIXTIMESTAMP_MILLI: {IX, NotNull, BIGINT UNSIGNED(20)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_MessageUnixtimestampMilli_Asc() { regOBA("MESSAGE_UNIXTIMESTAMP_MILLI"); return this; }

    /**
     * Add order-by as descend. <br>
     * MESSAGE_UNIXTIMESTAMP_MILLI: {IX, NotNull, BIGINT UNSIGNED(20)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_MessageUnixtimestampMilli_Desc() { regOBD("MESSAGE_UNIXTIMESTAMP_MILLI"); return this; }

    protected ConditionValue _villageDayId;
    public ConditionValue xdfgetVillageDayId()
    { if (_villageDayId == null) { _villageDayId = nCV(); }
      return _villageDayId; }
    protected ConditionValue xgetCValueVillageDayId() { return xdfgetVillageDayId(); }

    /**
     * Add order-by as ascend. <br>
     * VILLAGE_DAY_ID: {IX, NotNull, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_VillageDayId_Asc() { regOBA("VILLAGE_DAY_ID"); return this; }

    /**
     * Add order-by as descend. <br>
     * VILLAGE_DAY_ID: {IX, NotNull, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_VillageDayId_Desc() { regOBD("VILLAGE_DAY_ID"); return this; }

    protected ConditionValue _villagePlayerId;
    public ConditionValue xdfgetVillagePlayerId()
    { if (_villagePlayerId == null) { _villagePlayerId = nCV(); }
      return _villagePlayerId; }
    protected ConditionValue xgetCValueVillagePlayerId() { return xdfgetVillagePlayerId(); }

    /**
     * Add order-by as ascend. <br>
     * VILLAGE_PLAYER_ID: {IX, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_VillagePlayerId_Asc() { regOBA("VILLAGE_PLAYER_ID"); return this; }

    /**
     * Add order-by as descend. <br>
     * VILLAGE_PLAYER_ID: {IX, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_VillagePlayerId_Desc() { regOBD("VILLAGE_PLAYER_ID"); return this; }

    protected ConditionValue _toVillagePlayerId;
    public ConditionValue xdfgetToVillagePlayerId()
    { if (_toVillagePlayerId == null) { _toVillagePlayerId = nCV(); }
      return _toVillagePlayerId; }
    protected ConditionValue xgetCValueToVillagePlayerId() { return xdfgetToVillagePlayerId(); }

    /**
     * Add order-by as ascend. <br>
     * TO_VILLAGE_PLAYER_ID: {IX, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_ToVillagePlayerId_Asc() { regOBA("TO_VILLAGE_PLAYER_ID"); return this; }

    /**
     * Add order-by as descend. <br>
     * TO_VILLAGE_PLAYER_ID: {IX, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_ToVillagePlayerId_Desc() { regOBD("TO_VILLAGE_PLAYER_ID"); return this; }

    protected ConditionValue _playerId;
    public ConditionValue xdfgetPlayerId()
    { if (_playerId == null) { _playerId = nCV(); }
      return _playerId; }
    protected ConditionValue xgetCValuePlayerId() { return xdfgetPlayerId(); }

    /**
     * Add order-by as ascend. <br>
     * PLAYER_ID: {IX, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_PlayerId_Asc() { regOBA("PLAYER_ID"); return this; }

    /**
     * Add order-by as descend. <br>
     * PLAYER_ID: {IX, INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_PlayerId_Desc() { regOBD("PLAYER_ID"); return this; }

    protected ConditionValue _messageContent;
    public ConditionValue xdfgetMessageContent()
    { if (_messageContent == null) { _messageContent = nCV(); }
      return _messageContent; }
    protected ConditionValue xgetCValueMessageContent() { return xdfgetMessageContent(); }

    /**
     * Add order-by as ascend. <br>
     * MESSAGE_CONTENT: {NotNull, VARCHAR(10000)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_MessageContent_Asc() { regOBA("MESSAGE_CONTENT"); return this; }

    /**
     * Add order-by as descend. <br>
     * MESSAGE_CONTENT: {NotNull, VARCHAR(10000)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_MessageContent_Desc() { regOBD("MESSAGE_CONTENT"); return this; }

    protected ConditionValue _messageDatetime;
    public ConditionValue xdfgetMessageDatetime()
    { if (_messageDatetime == null) { _messageDatetime = nCV(); }
      return _messageDatetime; }
    protected ConditionValue xgetCValueMessageDatetime() { return xdfgetMessageDatetime(); }

    /**
     * Add order-by as ascend. <br>
     * MESSAGE_DATETIME: {NotNull, DATETIME(19)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_MessageDatetime_Asc() { regOBA("MESSAGE_DATETIME"); return this; }

    /**
     * Add order-by as descend. <br>
     * MESSAGE_DATETIME: {NotNull, DATETIME(19)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_MessageDatetime_Desc() { regOBD("MESSAGE_DATETIME"); return this; }

    protected ConditionValue _messageCount;
    public ConditionValue xdfgetMessageCount()
    { if (_messageCount == null) { _messageCount = nCV(); }
      return _messageCount; }
    protected ConditionValue xgetCValueMessageCount() { return xdfgetMessageCount(); }

    /**
     * Add order-by as ascend. <br>
     * MESSAGE_COUNT: {INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_MessageCount_Asc() { regOBA("MESSAGE_COUNT"); return this; }

    /**
     * Add order-by as descend. <br>
     * MESSAGE_COUNT: {INT UNSIGNED(10)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_MessageCount_Desc() { regOBD("MESSAGE_COUNT"); return this; }

    protected ConditionValue _isConvertDisable;
    public ConditionValue xdfgetIsConvertDisable()
    { if (_isConvertDisable == null) { _isConvertDisable = nCV(); }
      return _isConvertDisable; }
    protected ConditionValue xgetCValueIsConvertDisable() { return xdfgetIsConvertDisable(); }

    /**
     * Add order-by as ascend. <br>
     * IS_CONVERT_DISABLE: {NotNull, BIT}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_IsConvertDisable_Asc() { regOBA("IS_CONVERT_DISABLE"); return this; }

    /**
     * Add order-by as descend. <br>
     * IS_CONVERT_DISABLE: {NotNull, BIT}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_IsConvertDisable_Desc() { regOBD("IS_CONVERT_DISABLE"); return this; }

    protected ConditionValue _faceTypeCode;
    public ConditionValue xdfgetFaceTypeCode()
    { if (_faceTypeCode == null) { _faceTypeCode = nCV(); }
      return _faceTypeCode; }
    protected ConditionValue xgetCValueFaceTypeCode() { return xdfgetFaceTypeCode(); }

    /**
     * Add order-by as ascend. <br>
     * FACE_TYPE_CODE: {IX, VARCHAR(20)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_FaceTypeCode_Asc() { regOBA("FACE_TYPE_CODE"); return this; }

    /**
     * Add order-by as descend. <br>
     * FACE_TYPE_CODE: {IX, VARCHAR(20)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_FaceTypeCode_Desc() { regOBD("FACE_TYPE_CODE"); return this; }

    protected ConditionValue _charaName;
    public ConditionValue xdfgetCharaName()
    { if (_charaName == null) { _charaName = nCV(); }
      return _charaName; }
    protected ConditionValue xgetCValueCharaName() { return xdfgetCharaName(); }

    /**
     * Add order-by as ascend. <br>
     * CHARA_NAME: {VARCHAR(40)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_CharaName_Asc() { regOBA("CHARA_NAME"); return this; }

    /**
     * Add order-by as descend. <br>
     * CHARA_NAME: {VARCHAR(40)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_CharaName_Desc() { regOBD("CHARA_NAME"); return this; }

    protected ConditionValue _charaShortName;
    public ConditionValue xdfgetCharaShortName()
    { if (_charaShortName == null) { _charaShortName = nCV(); }
      return _charaShortName; }
    protected ConditionValue xgetCValueCharaShortName() { return xdfgetCharaShortName(); }

    /**
     * Add order-by as ascend. <br>
     * CHARA_SHORT_NAME: {CHAR(1)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_CharaShortName_Asc() { regOBA("CHARA_SHORT_NAME"); return this; }

    /**
     * Add order-by as descend. <br>
     * CHARA_SHORT_NAME: {CHAR(1)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_CharaShortName_Desc() { regOBD("CHARA_SHORT_NAME"); return this; }

    protected ConditionValue _toCharaName;
    public ConditionValue xdfgetToCharaName()
    { if (_toCharaName == null) { _toCharaName = nCV(); }
      return _toCharaName; }
    protected ConditionValue xgetCValueToCharaName() { return xdfgetToCharaName(); }

    /**
     * Add order-by as ascend. <br>
     * TO_CHARA_NAME: {VARCHAR(40)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_ToCharaName_Asc() { regOBA("TO_CHARA_NAME"); return this; }

    /**
     * Add order-by as descend. <br>
     * TO_CHARA_NAME: {VARCHAR(40)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_ToCharaName_Desc() { regOBD("TO_CHARA_NAME"); return this; }

    protected ConditionValue _toCharaShortName;
    public ConditionValue xdfgetToCharaShortName()
    { if (_toCharaShortName == null) { _toCharaShortName = nCV(); }
      return _toCharaShortName; }
    protected ConditionValue xgetCValueToCharaShortName() { return xdfgetToCharaShortName(); }

    /**
     * Add order-by as ascend. <br>
     * TO_CHARA_SHORT_NAME: {CHAR(1)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_ToCharaShortName_Asc() { regOBA("TO_CHARA_SHORT_NAME"); return this; }

    /**
     * Add order-by as descend. <br>
     * TO_CHARA_SHORT_NAME: {CHAR(1)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_ToCharaShortName_Desc() { regOBD("TO_CHARA_SHORT_NAME"); return this; }

    protected ConditionValue _registerDatetime;
    public ConditionValue xdfgetRegisterDatetime()
    { if (_registerDatetime == null) { _registerDatetime = nCV(); }
      return _registerDatetime; }
    protected ConditionValue xgetCValueRegisterDatetime() { return xdfgetRegisterDatetime(); }

    /**
     * Add order-by as ascend. <br>
     * REGISTER_DATETIME: {NotNull, DATETIME(19)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_RegisterDatetime_Asc() { regOBA("REGISTER_DATETIME"); return this; }

    /**
     * Add order-by as descend. <br>
     * REGISTER_DATETIME: {NotNull, DATETIME(19)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_RegisterDatetime_Desc() { regOBD("REGISTER_DATETIME"); return this; }

    protected ConditionValue _registerTrace;
    public ConditionValue xdfgetRegisterTrace()
    { if (_registerTrace == null) { _registerTrace = nCV(); }
      return _registerTrace; }
    protected ConditionValue xgetCValueRegisterTrace() { return xdfgetRegisterTrace(); }

    /**
     * Add order-by as ascend. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_RegisterTrace_Asc() { regOBA("REGISTER_TRACE"); return this; }

    /**
     * Add order-by as descend. <br>
     * REGISTER_TRACE: {NotNull, VARCHAR(64)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_RegisterTrace_Desc() { regOBD("REGISTER_TRACE"); return this; }

    protected ConditionValue _updateDatetime;
    public ConditionValue xdfgetUpdateDatetime()
    { if (_updateDatetime == null) { _updateDatetime = nCV(); }
      return _updateDatetime; }
    protected ConditionValue xgetCValueUpdateDatetime() { return xdfgetUpdateDatetime(); }

    /**
     * Add order-by as ascend. <br>
     * UPDATE_DATETIME: {NotNull, DATETIME(19)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_UpdateDatetime_Asc() { regOBA("UPDATE_DATETIME"); return this; }

    /**
     * Add order-by as descend. <br>
     * UPDATE_DATETIME: {NotNull, DATETIME(19)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_UpdateDatetime_Desc() { regOBD("UPDATE_DATETIME"); return this; }

    protected ConditionValue _updateTrace;
    public ConditionValue xdfgetUpdateTrace()
    { if (_updateTrace == null) { _updateTrace = nCV(); }
      return _updateTrace; }
    protected ConditionValue xgetCValueUpdateTrace() { return xdfgetUpdateTrace(); }

    /**
     * Add order-by as ascend. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_UpdateTrace_Asc() { regOBA("UPDATE_TRACE"); return this; }

    /**
     * Add order-by as descend. <br>
     * UPDATE_TRACE: {NotNull, VARCHAR(64)}
     * @return this. (NotNull)
     */
    public BsMessageCQ addOrderBy_UpdateTrace_Desc() { regOBD("UPDATE_TRACE"); return this; }

    // ===================================================================================
    //                                                             SpecifiedDerivedOrderBy
    //                                                             =======================
    /**
     * Add order-by for specified derived column as ascend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] asc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Asc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsMessageCQ addSpecifiedDerivedOrderBy_Asc(String aliasName) { registerSpecifiedDerivedOrderBy_Asc(aliasName); return this; }

    /**
     * Add order-by for specified derived column as descend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] desc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Desc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsMessageCQ addSpecifiedDerivedOrderBy_Desc(String aliasName) { registerSpecifiedDerivedOrderBy_Desc(aliasName); return this; }

    // ===================================================================================
    //                                                                         Union Query
    //                                                                         ===========
    public void reflectRelationOnUnionQuery(ConditionQuery bqs, ConditionQuery uqs) {
    }

    // ===================================================================================
    //                                                                       Foreign Query
    //                                                                       =============
    protected Map<String, Object> xfindFixedConditionDynamicParameterMap(String property) {
        return null;
    }

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    public Map<String, MessageCQ> xdfgetScalarCondition() { return xgetSQueMap("scalarCondition"); }
    public String keepScalarCondition(MessageCQ sq) { return xkeepSQue("scalarCondition", sq); }

    // ===================================================================================
    //                                                      ExistsReferrer for Compound PK
    //                                                      ==============================
    /**
     * Set up ExistsReferrer (correlated sub-query by compound key). <br>
     * {exists (select ... from message_sendto where ...)}
     * @param subQuery The sub-query of MessageSendtoList for 'exists'. (NotNull)
     */
    public void existsMessageSendto(SubQuery<MessageSendtoCB> subQuery) {
        assertObjectNotNull("subQuery<MessageSendtoCB>", subQuery);
        MessageSendtoCB cb = new MessageSendtoCB(); cb.xsetupForExistsReferrer(this);
        try { lock(); subQuery.query(cb); } finally { unlock(); }
        String pp = keepTwoOrMorePk_ExistsReferrer_MessageSendtoList(cb.query());
        registerExistsReferrer(cb.query(), "VILLAGE_ID, MESSAGE_TYPE_CODE, MESSAGE_NUMBER", "VILLAGE_ID, MESSAGE_TYPE_CODE, MESSAGE_NUMBER", pp, "messageSendtoList");
    }
    public Map<String, MessageSendtoCQ> xdfgetTwoOrMorePk_ExistsReferrer_MessageSendtoList() { return xgetSQueMap("twoOrMorePk_ExistsReferrer_MessageSendtoList"); }
    public String keepTwoOrMorePk_ExistsReferrer_MessageSendtoList(MessageSendtoCQ sq) { return xkeepSQue("twoOrMorePk_ExistsReferrer_MessageSendtoList", sq); }

    /**
     * Set up NotExistsReferrer (correlated sub-query by compound key). <br>
     * {not exists (select ... from message_sendto where ...)}
     * @param subQuery The sub-query of MessageSendtoList for 'not exists'. (NotNull)
     */
    public void notExistsMessageSendto(SubQuery<MessageSendtoCB> subQuery) {
        assertObjectNotNull("subQuery<MessageSendtoCB>", subQuery);
        MessageSendtoCB cb = new MessageSendtoCB(); cb.xsetupForExistsReferrer(this);
        try { lock(); subQuery.query(cb); } finally { unlock(); }
        String pp = keepTwoOrMorePk_NotExistsReferrer_MessageSendtoList(cb.query());
        registerNotExistsReferrer(cb.query(), "VILLAGE_ID, MESSAGE_TYPE_CODE, MESSAGE_NUMBER", "VILLAGE_ID, MESSAGE_TYPE_CODE, MESSAGE_NUMBER", pp, "messageSendtoList");
    }
    public Map<String, MessageSendtoCQ> xdfgetTwoOrMorePk_NotExistsReferrer_MessageSendtoList() { return xgetSQueMap("twoOrMorePk_NotExistsReferrer_MessageSendtoList"); }
    public String keepTwoOrMorePk_NotExistsReferrer_MessageSendtoList(MessageSendtoCQ sq) { return xkeepSQue("twoOrMorePk_NotExistsReferrer_MessageSendtoList", sq); }

    // ===================================================================================
    //                                            (Specify)DerivedReferrer for Compound PK
    //                                            ========================================
    public void xsderiveMessageSendtoList(String fn, SubQuery<MessageSendtoCB> sq, String al, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        MessageSendtoCB cb = new MessageSendtoCB(); cb.xsetupForDerivedReferrer(this);
        try { lock(); sq.query(cb); } finally { unlock(); }
        String pp = keepTwoOrMorePk_SpecifyDerivedReferrer_MessageSendtoList(cb.query());
        registerSpecifyDerivedReferrer(fn, cb.query(), "VILLAGE_ID, MESSAGE_TYPE_CODE, MESSAGE_NUMBER", "VILLAGE_ID, MESSAGE_TYPE_CODE, MESSAGE_NUMBER", pp, "messageSendtoList", al, op);
    }
    public Map<String, MessageSendtoCQ> xdfgetTwoOrMorePk_SpecifyDerivedReferrer_MessageSendtoList() { return xgetSQueMap("twoOrMorePk_SpecifyDerivedReferrer_MessageSendtoList"); }
    public String keepTwoOrMorePk_SpecifyDerivedReferrer_MessageSendtoList(MessageSendtoCQ sq) { return xkeepSQue("twoOrMorePk_SpecifyDerivedReferrer_MessageSendtoList", sq); }

    // ===================================================================================
    //                                              (Query)DerivedReferrer for Compound PK
    //                                              ======================================
    /**
     * Prepare for (Query)DerivedReferrer (correlated sub-query). <br>
     * {FOO &lt;= (select max(BAR) from message_sendto where ...)} <br>
     * message_sendto by VILLAGE_ID, MESSAGE_TYPE_CODE, MESSAGE_NUMBER, named 'messageSendtoAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">derivedMessageSendto()</span>.<span style="color: #CC4747">max</span>(new SubQuery&lt;MessageSendtoCB&gt;() {
     *     public void query(MessageSendtoCB subCB) {
     *         subCB.specify().<span style="color: #CC4747">columnFoo...</span> <span style="color: #3F7E5E">// derived column by function</span>
     *         subCB.query().setBar... <span style="color: #3F7E5E">// referrer condition</span>
     *     }
     * }).<span style="color: #CC4747">greaterEqual</span>(123); <span style="color: #3F7E5E">// condition to derived column</span>
     * </pre>
     * @return The object to set up a function for referrer table. (NotNull)
     */
    public HpQDRFunction<MessageSendtoCB> derivedMessageSendto() {
        return xcreateQDRFunctionMessageSendtoList();
    }
    protected HpQDRFunction<MessageSendtoCB> xcreateQDRFunctionMessageSendtoList() {
        return xcQDRFunc(new HpQDRSetupper<MessageSendtoCB>() {
            public void setup(String fn, SubQuery<MessageSendtoCB> sq, String rd, Object vl, DerivedReferrerOption op) {
                xqderiveMessageSendtoList(fn, sq, rd, vl, op);
            }
        });
    }
    public void xqderiveMessageSendtoList(String fn, SubQuery<MessageSendtoCB> sq, String rd, Object vl, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        MessageSendtoCB cb = new MessageSendtoCB(); cb.xsetupForDerivedReferrer(this);
        try { lock(); sq.query(cb); } finally { unlock(); }
        String sqpp = keepTwoOrMorePk_QueryDerivedReferrer_MessageSendtoList(cb.query()); String prpp = keepTwoOrMorePk_QueryDerivedReferrer_MessageSendtoListParameter(vl);
        registerQueryDerivedReferrer(fn, cb.query(), "VILLAGE_ID, MESSAGE_TYPE_CODE, MESSAGE_NUMBER", "VILLAGE_ID, MESSAGE_TYPE_CODE, MESSAGE_NUMBER", sqpp, "messageSendtoList", rd, vl, prpp, op);
    }
    public Map<String, MessageSendtoCQ> xdfgetTwoOrMorePk_QueryDerivedReferrer_MessageSendtoList() { return xgetSQueMap("twoOrMorePk_QueryDerivedReferrer_MessageSendtoList"); }
    public String keepTwoOrMorePk_QueryDerivedReferrer_MessageSendtoList(MessageSendtoCQ sq) { return xkeepSQue("twoOrMorePk_QueryDerivedReferrer_MessageSendtoList", sq); }
    public Map<String, Object> xdfgetTwoOrMorePk_QueryDerivedReferrer_MessageSendtoListParameter() { return xgetSQuePmMap("twoOrMorePk_QueryDerivedReferrer_MessageSendtoList"); }
    public String keepTwoOrMorePk_QueryDerivedReferrer_MessageSendtoListParameter(Object pm) { return xkeepSQuePm("twoOrMorePk_QueryDerivedReferrer_MessageSendtoList", pm); }

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xCB() { return MessageCB.class.getName(); }
    protected String xCQ() { return MessageCQ.class.getName(); }
    protected String xCHp() { return HpQDRFunction.class.getName(); }
    protected String xCOp() { return ConditionOption.class.getName(); }
    protected String xMap() { return Map.class.getName(); }
}
