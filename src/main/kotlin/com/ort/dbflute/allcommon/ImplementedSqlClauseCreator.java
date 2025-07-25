package com.ort.dbflute.allcommon;

import org.dbflute.cbean.ConditionBean;
import org.dbflute.cbean.cipher.GearedCipherManager;
import org.dbflute.cbean.sqlclause.*;
import org.dbflute.dbmeta.DBMetaProvider;
import org.dbflute.dbway.DBDef;

/**
 * The creator of SQL clause.
 * @author DBFlute(AutoGenerator)
 */
public class ImplementedSqlClauseCreator implements SqlClauseCreator {

    // ===================================================================================
    //                                                                      Implementation
    //                                                                      ==============
    /**
     * Create SQL clause. {for condition-bean}
     * @param cb Condition-bean. (NotNull)
     * @return SQL clause. (NotNull)
     */
    public SqlClause createSqlClause(ConditionBean cb) {
        String tableDbName = cb.asTableDbName();
        SqlClause sqlClause = createSqlClause(tableDbName);
        return sqlClause;
    }

    /**
     * Create SQL clause.
     * @param tableDbName The DB name of table. (NotNull)
     * @return SQL clause. (NotNull)
     */
    public SqlClause createSqlClause(String tableDbName) {
        SqlClause sqlClause = doCreateSqlClause(tableDbName);
        setupSqlClauseOption(sqlClause);
        return sqlClause;
    }

    // ===================================================================================
    //                                                                            Creation
    //                                                                            ========
    protected SqlClause doCreateSqlClause(String tableDbName) {
        AbstractSqlClause sqlClause; // dynamic resolution but no perfect (almost static)
        if (isCurrentDBDef(DBDef.MySQL)) {
            sqlClause = createSqlClauseMySql(tableDbName);
        } else if (isCurrentDBDef(DBDef.PostgreSQL)) {
            sqlClause = createSqlClausePostgreSql(tableDbName);
        } else if (isCurrentDBDef(DBDef.Oracle)) {
            sqlClause = createSqlClauseOracle(tableDbName);
        } else if (isCurrentDBDef(DBDef.DB2)) {
            sqlClause = createSqlClauseDb2(tableDbName);
        } else if (isCurrentDBDef(DBDef.SQLServer)) {
            sqlClause = createSqlClauseSqlServer(tableDbName);
        } else if (isCurrentDBDef(DBDef.H2)) {
            sqlClause = createSqlClauseH2(tableDbName);
        } else if (isCurrentDBDef(DBDef.Derby)) {
            sqlClause = createSqlClauseDerby(tableDbName);
        } else if (isCurrentDBDef(DBDef.SQLite)) {
            sqlClause = createSqlClauseSqlite(tableDbName);
        } else if (isCurrentDBDef(DBDef.MSAccess)) {
            sqlClause = createSqlClauseMsAccess(tableDbName);
        } else if (isCurrentDBDef(DBDef.Firebird)) {
            sqlClause = createSqlClauseFirebird(tableDbName);
        } else if (isCurrentDBDef(DBDef.Sybase)) {
            sqlClause = createSqlClauseSybase(tableDbName);
        } else {
            // as the database when generating
            sqlClause = createSqlClauseMySql(tableDbName);
        }
        prepareSupporterComponent(sqlClause);
        return sqlClause;
    }

    protected SqlClauseMySql createSqlClauseMySql(String tableDbName) {
        return new SqlClauseMySql(tableDbName);
    }

    protected SqlClausePostgreSql createSqlClausePostgreSql(String tableDbName) {
        return new SqlClausePostgreSql(tableDbName);
    }

    protected SqlClauseOracle createSqlClauseOracle(String tableDbName) {
        return new SqlClauseOracle(tableDbName);
    }

    protected SqlClauseDb2 createSqlClauseDb2(String tableDbName) {
        return new SqlClauseDb2(tableDbName);
    }

    protected SqlClauseSqlServer createSqlClauseSqlServer(String tableDbName) {
        return new SqlClauseSqlServer(tableDbName);
    }

    protected SqlClauseH2 createSqlClauseH2(String tableDbName) {
        return new SqlClauseH2(tableDbName);
    }

    protected SqlClauseDerby createSqlClauseDerby(String tableDbName) {
        return new SqlClauseDerby(tableDbName);
    }

    protected SqlClauseSqlite createSqlClauseSqlite(String tableDbName) {
        return new SqlClauseSqlite(tableDbName);
    }

    protected SqlClauseMsAccess createSqlClauseMsAccess(String tableDbName) {
        return new SqlClauseMsAccess(tableDbName);
    }

    protected SqlClauseFirebird createSqlClauseFirebird(String tableDbName) {
        return new SqlClauseFirebird(tableDbName);
    }

    protected SqlClauseSybase createSqlClauseSybase(String tableDbName) {
        return new SqlClauseSybase(tableDbName);
    }

    protected SqlClause createSqlClauseDefault(String tableDbName) {
        return new SqlClauseDefault(tableDbName);
    }

    protected void prepareSupporterComponent(AbstractSqlClause sqlClause) {
        sqlClause.dbmetaProvider(getDBMetaProvider()).cipherManager(getGearedCipherManager());
    }

    // ===================================================================================
    //                                                                           Supporter
    //                                                                           =========
    protected DBMetaProvider getDBMetaProvider() {
        return DBMetaInstanceHandler.getProvider();
    }

    protected GearedCipherManager getGearedCipherManager() {
        return DBFluteConfig.getInstance().getGearedCipherManager();
    }

    // ===================================================================================
    //                                                                              Option
    //                                                                              ======
    protected void setupSqlClauseOption(SqlClause sqlClause) {
        doSetupSqlClauseInnerJoinAutoDetect(sqlClause);
        doSetupSqlClauseThatsBadTimingDetect(sqlClause);
        doSetupSqlClauseNullOrEmptyQuery(sqlClause);
        doSetupSqlClauseEmptyStringQuery(sqlClause);
        doSetupSqlClauseOverridingQuery(sqlClause);
        doSetupSqlClauseInvalidQueryAllowedWarning(sqlClause);
        doSetupSqlClauseColumnNullObject(sqlClause);
        doSetupSqlClauseColumnNullObjectGearedToSpecify(sqlClause);
        doSetupSqlClauseTruncateConditionDatetimePrecision(sqlClause);
        doSetupSqlClauseSelectIndex(sqlClause);
    }

    protected void doSetupSqlClauseInnerJoinAutoDetect(SqlClause sqlClause) {
        if (isInnerJoinAutoDetect()) {
            sqlClause.enableInnerJoinAutoDetect();
        }
    }

    protected void doSetupSqlClauseThatsBadTimingDetect(SqlClause sqlClause) {
        if (isThatsBadTimingDetect()) {
            sqlClause.enableThatsBadTimingDetect();
        }
        if (isThatsBadTimingWarningOnly()) {
            sqlClause.enableThatsBadTimingWarningOnly();
        }
        if (isOrScopeQueryPurposeCheckWarningOnly()) {
            sqlClause.enableOrScopeQueryPurposeCheckWarningOnly();
        }
    }

    protected void doSetupSqlClauseNullOrEmptyQuery(SqlClause sqlClause) {
        if (isNullOrEmptyQueryAllowed()) { // default for 1.0.5
            sqlClause.ignoreNullOrEmptyQuery();
        } else { // default for 1.1
            sqlClause.checkNullOrEmptyQuery();
        }
    }

    protected void doSetupSqlClauseEmptyStringQuery(SqlClause sqlClause) {
        if (isEmptyStringQueryAllowed()) {
            sqlClause.enableEmptyStringQuery();
        }
    }

    protected void doSetupSqlClauseOverridingQuery(SqlClause sqlClause) {
        if (isOverridingQueryAllowed()) { // default for 1.0.5
            sqlClause.enableOverridingQuery();
        } else { // default for 1.1
            sqlClause.disableOverridingQuery();
        }
    }

    protected void doSetupSqlClauseInvalidQueryAllowedWarning(SqlClause sqlClause) { // since 1.2.7
        if (isInvalidQueryAllowedWarning()) {
            sqlClause.enableInvalidQueryAllowedWarning();
        } else {
            sqlClause.disableInvalidQueryAllowedWarning();
        }
    }

    protected void doSetupSqlClauseColumnNullObject(SqlClause sqlClause) {
        if (isColumnNullObjectAllowed()) {
            sqlClause.enableColumnNullObject();
        } else {
            sqlClause.disableColumnNullObject();
        }
    }

    protected void doSetupSqlClauseColumnNullObjectGearedToSpecify(SqlClause sqlClause) {
        if (isColumnNullObjectGearedToSpecify()) {
            sqlClause.enableColumnNullObjectGearedToSpecify();
        } else {
            sqlClause.disableColumnNullObjectGearedToSpecify();
        }
    }

    protected void doSetupSqlClauseTruncateConditionDatetimePrecision(SqlClause sqlClause) {
        if (isDatetimePrecisionTruncationOfCondition()) {
            sqlClause.enableDatetimePrecisionTruncationOfCondition();
        } else {
            sqlClause.disableDatetimePrecisionTruncationOfCondition();
        }
    }

    protected void doSetupSqlClauseSelectIndex(SqlClause sqlClause) {
        if (isDisableSelectIndex()) {
            sqlClause.disableSelectIndex();
        }
    }

    // ===================================================================================
    //                                                                       Determination
    //                                                                       =============
    protected boolean isCurrentDBDef(DBDef currentDBDef) {
        return DBCurrent.getInstance().isCurrentDBDef(currentDBDef);
    }

    protected boolean isInnerJoinAutoDetect() {
        return DBFluteConfig.getInstance().isInnerJoinAutoDetect();
    }

    protected boolean isThatsBadTimingDetect() {
        return DBFluteConfig.getInstance().isThatsBadTimingDetect();
    }

    protected boolean isThatsBadTimingWarningOnly() {
        return DBFluteConfig.getInstance().isThatsBadTimingWarningOnly();
    }

    protected boolean isOrScopeQueryPurposeCheckWarningOnly() {
        return DBFluteConfig.getInstance().isOrScopeQueryPurposeCheckWarningOnly();
    }

    protected boolean isNullOrEmptyQueryAllowed() {
        return DBFluteConfig.getInstance().isNullOrEmptyQueryAllowed();
    }

    protected boolean isEmptyStringQueryAllowed() {
        return DBFluteConfig.getInstance().isEmptyStringQueryAllowed();
    }

    protected boolean isOverridingQueryAllowed() {
        return DBFluteConfig.getInstance().isOverridingQueryAllowed();
    }

    protected boolean isInvalidQueryAllowedWarning() {
        return DBFluteConfig.getInstance().isInvalidQueryAllowedWarning();
    }

    protected boolean isColumnNullObjectAllowed() {
        return DBFluteConfig.getInstance().isColumnNullObjectAllowed();
    }

    protected boolean isColumnNullObjectGearedToSpecify() {
        return DBFluteConfig.getInstance().isColumnNullObjectGearedToSpecify();
    }

    protected boolean isDatetimePrecisionTruncationOfCondition() {
        return DBFluteConfig.getInstance().isDatetimePrecisionTruncationOfCondition();
    }

    protected boolean isDisableSelectIndex() {
        return DBFluteConfig.getInstance().isDisableSelectIndex();
    }
}
