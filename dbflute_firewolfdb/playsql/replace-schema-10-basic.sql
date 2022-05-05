

/* Create Tables */

CREATE TABLE ABILITY
(
	ABILITY_TYPE_CODE VARCHAR(20) NOT NULL COMMENT '能力種別コード',
	VILLAGE_DAY_ID INT UNSIGNED NOT NULL COMMENT '村日付ID',
	VILLAGE_PLAYER_ID INT UNSIGNED NOT NULL COMMENT '行使元村参加者ID',
	TARGET_VILLAGE_PLAYER_ID INT UNSIGNED COMMENT '行使対象村参加者ID',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (ABILITY_TYPE_CODE, VILLAGE_DAY_ID, VILLAGE_PLAYER_ID)
) COMMENT = '能力行使';


CREATE TABLE ABILITY_TYPE
(
	ABILITY_TYPE_CODE VARCHAR(20) NOT NULL COMMENT '能力種別コード',
	ABILITY_TYPE_NAME VARCHAR(20) NOT NULL COMMENT '能力種別',
	PRIMARY KEY (ABILITY_TYPE_CODE)
) COMMENT = '能力種別';


CREATE TABLE APP_VERSION
(
	CLIENT_VERSION INT UNSIGNED NOT NULL COMMENT 'クライアントのバージョン',
	PRIMARY KEY (CLIENT_VERSION)
) COMMENT = 'バージョン';


CREATE TABLE AUTHORITY
(
	AUTHORITY_CODE VARCHAR(20) NOT NULL COMMENT '権限コード',
	AUTHORITY_NAME VARCHAR(20) NOT NULL COMMENT '権限名',
	PRIMARY KEY (AUTHORITY_CODE)
) COMMENT = '権限';


CREATE TABLE CAMP
(
	CAMP_CODE VARCHAR(20) NOT NULL COMMENT '陣営コード',
	CAMP_NAME VARCHAR(20) NOT NULL COMMENT '陣営名',
	DISP_ORDER INT UNSIGNED NOT NULL COMMENT '並び順',
	PRIMARY KEY (CAMP_CODE)
) COMMENT = '陣営';


CREATE TABLE CHARA
(
	CHARA_ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'キャラクターID',
	CHARA_NAME VARCHAR(40) NOT NULL COMMENT 'キャラクター名',
	CHARA_SHORT_NAME CHAR(1) NOT NULL COMMENT 'キャラクター略称',
	CHARA_GROUP_ID INT UNSIGNED NOT NULL COMMENT 'キャラクターグループID',
	DEFAULT_JOIN_MESSAGE VARCHAR(200) COMMENT '入村時デフォルト発言',
	DEFAULT_FIRSTDAY_MESSAGE VARCHAR(200) COMMENT 'デフォルト1日目発言',
	DISPLAY_WIDTH INT UNSIGNED NOT NULL COMMENT '表示時横幅',
	DISPLAY_HEIGHT INT UNSIGNED NOT NULL COMMENT '表示時縦幅',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (CHARA_ID)
) COMMENT = 'キャラクター';


CREATE TABLE CHARA_GROUP
(
	CHARA_GROUP_ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'キャラクターグループID',
	CHARA_GROUP_NAME VARCHAR(40) NOT NULL COMMENT 'キャラクターグループ名',
	DESIGNER_ID INT UNSIGNED NOT NULL COMMENT 'デザイナーID',
	DESCRIPTION_URL TEXT COMMENT 'キャラチップURL : キャラチップの利用規約や配布サイトのURL',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (CHARA_GROUP_ID)
) COMMENT = 'キャラクターグループ';


CREATE TABLE CHARA_IMAGE
(
	CHARA_ID INT UNSIGNED NOT NULL COMMENT 'キャラクターID',
	FACE_TYPE_CODE VARCHAR(20) NOT NULL COMMENT '表情種別コード',
	CHARA_IMG_URL VARCHAR(100) NOT NULL COMMENT 'キャラクター画像URL',
	PRIMARY KEY (CHARA_ID, FACE_TYPE_CODE)
) COMMENT = 'キャラクター画像';


CREATE TABLE COMING_OUT
(
	VILLAGE_PLAYER_ID INT UNSIGNED NOT NULL COMMENT '村参加者ID',
	SKILL_CODE VARCHAR(20) NOT NULL COMMENT '役職コード',
	PRIMARY KEY (VILLAGE_PLAYER_ID, SKILL_CODE)
) COMMENT = 'カミングアウト';


CREATE TABLE COMMIT
(
	VILLAGE_PLAYER_ID INT UNSIGNED NOT NULL COMMENT '村参加者ID',
	VILLAGE_DAY_ID INT UNSIGNED NOT NULL COMMENT '村日付ID',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (VILLAGE_PLAYER_ID, VILLAGE_DAY_ID)
) COMMENT = 'コミット';


CREATE TABLE DEAD_REASON
(
	DEAD_REASON_CODE VARCHAR(20) NOT NULL COMMENT '死亡理由コード',
	DEAD_REASON_NAME VARCHAR(20) NOT NULL COMMENT '死亡理由',
	PRIMARY KEY (DEAD_REASON_CODE)
) COMMENT = '死亡理由';


CREATE TABLE DESIGNER
(
	DESIGNER_ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'デザイナーID',
	DESIGNER_NAME VARCHAR(40) NOT NULL COMMENT 'デザイナー名',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (DESIGNER_ID)
) COMMENT = 'デザイナー';


CREATE TABLE FACE_TYPE
(
	FACE_TYPE_CODE VARCHAR(20) NOT NULL COMMENT '表情種別コード',
	FACE_TYPE_NAME VARCHAR(20) NOT NULL COMMENT '表情種別名',
	DISP_ORDER INT UNSIGNED NOT NULL COMMENT '並び順',
	PRIMARY KEY (FACE_TYPE_CODE)
) COMMENT = '表情種別';


CREATE TABLE MESSAGE
(
	VILLAGE_ID INT UNSIGNED NOT NULL COMMENT '村ID',
	MESSAGE_NUMBER INT UNSIGNED NOT NULL COMMENT 'メッセージ番号',
	MESSAGE_TYPE_CODE VARCHAR(20) NOT NULL COMMENT 'メッセージ種別コード',
	MESSAGE_UNIXTIMESTAMP_MILLI BIGINT UNSIGNED NOT NULL COMMENT 'メッセージUNIXタイムスタンプミリ秒',
	VILLAGE_DAY_ID INT UNSIGNED NOT NULL COMMENT '村日付ID',
	VILLAGE_PLAYER_ID INT UNSIGNED COMMENT '村参加者ID',
	TO_VILLAGE_PLAYER_ID INT UNSIGNED COMMENT '秘話相手の村参加者ID',
	PLAYER_ID INT UNSIGNED COMMENT 'プレイヤーID',
	MESSAGE_CONTENT VARCHAR(10000) NOT NULL COMMENT 'メッセージ内容',
	MESSAGE_DATETIME DATETIME NOT NULL COMMENT 'メッセージ日時',
	MESSAGE_COUNT INT UNSIGNED COMMENT '何回目の発言か : その日のその発言種別において何回目の発言か',
	IS_CONVERT_DISABLE BOOLEAN NOT NULL COMMENT '変換無効か',
	FACE_TYPE_CODE VARCHAR(20) COMMENT '表情種別コード',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (VILLAGE_ID, MESSAGE_NUMBER, MESSAGE_TYPE_CODE)
) COMMENT = 'メッセージ';


CREATE TABLE MESSAGE_RESTRICTION
(
	VILLAGE_ID INT UNSIGNED NOT NULL COMMENT '村ID',
	MESSAGE_TYPE_CODE VARCHAR(20) NOT NULL COMMENT 'メッセージ種別コード',
	MESSAGE_MAX_NUM INT UNSIGNED NOT NULL COMMENT '最大発言回数',
	MESSAGE_MAX_LENGTH INT UNSIGNED NOT NULL COMMENT '最大文字数',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (VILLAGE_ID, MESSAGE_TYPE_CODE)
) COMMENT = '発言制限 : レコードなしの場合は無制限';


CREATE TABLE MESSAGE_TYPE
(
	MESSAGE_TYPE_CODE VARCHAR(20) NOT NULL COMMENT 'メッセージ種別コード',
	MESSAGE_TYPE_NAME VARCHAR(20) NOT NULL COMMENT 'メッセージ種別名',
	PRIMARY KEY (MESSAGE_TYPE_CODE)
) COMMENT = 'メッセージ種別';


CREATE TABLE NOONNIGHT
(
	NOONNIGHT_CODE VARCHAR(20) NOT NULL COMMENT '昼夜コード',
	NOONNIGHT_NAME VARCHAR(20) NOT NULL COMMENT '昼夜名',
	DISP_ORDER INT UNSIGNED NOT NULL COMMENT '並び順',
	PRIMARY KEY (NOONNIGHT_CODE)
) COMMENT = '昼夜';


CREATE TABLE PLAYER
(
	PLAYER_ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'プレイヤーID',
	UID VARCHAR(100) NOT NULL UNIQUE COMMENT 'ユーザID',
	NICKNAME VARCHAR(50) NOT NULL COMMENT 'ニックネーム',
	TWITTER_USER_NAME VARCHAR(15) NOT NULL COMMENT 'twitterのusername',
	AUTHORITY_CODE VARCHAR(20) NOT NULL COMMENT '権限コード',
	IS_RESTRICTED_PARTICIPATION BOOLEAN NOT NULL COMMENT '入村制限されているか',
	SHOULD_CHECK_ACCESS_INFO BOOLEAN NOT NULL COMMENT 'アクセス情報を確認するか',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (PLAYER_ID)
) COMMENT = 'プレイヤー';


CREATE TABLE PLAYER_DETAIL
(
	PLAYER_ID INT UNSIGNED NOT NULL COMMENT 'プレイヤーID',
	OTHER_SITE_NAME VARCHAR(20) COMMENT '他サイトでの名前',
	INTRODUCTION VARCHAR(2000) COMMENT '自己紹介',
	PRIMARY KEY (PLAYER_ID)
) COMMENT = 'プレイヤー詳細';


CREATE TABLE RESERVED_VILLAGE
(
	RESERVED_VILLAGE_ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '予約村ID',
	CREATE_DATETIME DATETIME NOT NULL COMMENT '村作成日時',
	START_DATETIME DATETIME NOT NULL COMMENT '村開始日時',
	ORGANIZATION VARCHAR(255) NOT NULL COMMENT '編成',
	SILENT_HOURS INT UNSIGNED NOT NULL COMMENT '沈黙時間',
	IS_AVAILABLE_DUMMY_SKILL BOOLEAN NOT NULL COMMENT '役欠けありか',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (RESERVED_VILLAGE_ID)
) COMMENT = '予約村';


CREATE TABLE SKILL
(
	SKILL_CODE VARCHAR(20) NOT NULL COMMENT '役職コード',
	SKILL_NAME VARCHAR(20) NOT NULL COMMENT '役職名',
	SKILL_SHORT_NAME CHAR(1) NOT NULL COMMENT '役職略称',
	CAMP_CODE VARCHAR(20) NOT NULL COMMENT '陣営コード',
	DISP_ORDER INT UNSIGNED NOT NULL COMMENT '並び順',
	DESCRIPTION TEXT NOT NULL COMMENT '説明',
	PRIMARY KEY (SKILL_CODE)
) COMMENT = '役職';


CREATE TABLE VILLAGE
(
	VILLAGE_ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '村ID',
	VILLAGE_DISPLAY_NAME VARCHAR(40) NOT NULL COMMENT '村表示名',
	CREATE_PLAYER_ID INT UNSIGNED NOT NULL COMMENT '村作成プレイヤーID',
	VILLAGE_STATUS_CODE VARCHAR(20) NOT NULL COMMENT '村ステータスコード',
	EPILOGUE_DAY INT UNSIGNED COMMENT 'エピローグ日',
	WIN_CAMP_CODE VARCHAR(20) COMMENT '勝利陣営コード',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (VILLAGE_ID)
) COMMENT = '村';


CREATE TABLE VILLAGE_CHARA_GROUP
(
    VILLAGE_CHARA_GROUP_ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '村キャラクターグループID',
	VILLAGE_ID INT UNSIGNED NOT NULL COMMENT '村ID',
	CHARA_GROUP_ID INT UNSIGNED NOT NULL COMMENT 'キャラクターグループID',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (VILLAGE_CHARA_GROUP_ID)
) COMMENT = '村キャラクターグループ';


CREATE TABLE VILLAGE_DAY
(
	VILLAGE_DAY_ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '村日付ID',
	VILLAGE_ID INT UNSIGNED NOT NULL COMMENT '村ID',
	DAY INT UNSIGNED NOT NULL COMMENT '何日目か',
	NOONNIGHT_CODE VARCHAR(20) NOT NULL COMMENT '昼夜コード',
	DAYCHANGE_DATETIME DATETIME NOT NULL COMMENT '日付更新日時',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (VILLAGE_DAY_ID),
	CONSTRAINT UQ_village_day UNIQUE (VILLAGE_ID, DAY, NOONNIGHT_CODE)
) COMMENT = '村日付';


CREATE TABLE VILLAGE_PLAYER
(
	VILLAGE_PLAYER_ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '村参加者ID',
	VILLAGE_ID INT UNSIGNED NOT NULL COMMENT '村ID',
	PLAYER_ID INT UNSIGNED NOT NULL COMMENT 'プレイヤーID',
	CHARA_ID INT UNSIGNED NOT NULL COMMENT 'キャラクターID',
	SKILL_CODE VARCHAR(20) COMMENT '役職コード',
	REQUEST_SKILL_CODE VARCHAR(20) COMMENT '希望役職コード',
	SECOND_REQUEST_SKILL_CODE VARCHAR(20) COMMENT '第二希望役職コード',
	IS_DEAD BOOLEAN NOT NULL COMMENT '死亡しているか',
	IS_SPECTATOR BOOLEAN NOT NULL COMMENT '見学者か',
	DEAD_REASON_CODE VARCHAR(20) COMMENT '死亡理由コード',
	DEAD_VILLAGE_DAY_ID INT UNSIGNED COMMENT '何日目に死亡したか',
	IS_GONE BOOLEAN NOT NULL COMMENT '退村済みか',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (VILLAGE_PLAYER_ID)
) COMMENT = '村参加者';


CREATE TABLE VILLAGE_SETTING
(
	VILLAGE_ID INT UNSIGNED NOT NULL COMMENT '村ID',
	VILLAGE_SETTING_ITEM_CODE VARCHAR(100) NOT NULL COMMENT '村設定項目コード',
	VILLAGE_SETTING_TEXT VARCHAR(1000) COMMENT '村設定項目内容',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (VILLAGE_ID, VILLAGE_SETTING_ITEM_CODE)
) COMMENT = '村設定';


CREATE TABLE VILLAGE_SETTING_ITEM
(
	VILLAGE_SETTING_ITEM_CODE VARCHAR(100) NOT NULL COMMENT '村設定項目コード',
	VILLAGE_SETTING_ITEM_NAME VARCHAR(20) NOT NULL COMMENT '村設定項目名',
	DISP_ORDER INT UNSIGNED NOT NULL COMMENT '並び順',
	PRIMARY KEY (VILLAGE_SETTING_ITEM_CODE)
) COMMENT = '村設定項目';


CREATE TABLE VILLAGE_STATUS
(
	VILLAGE_STATUS_CODE VARCHAR(20) NOT NULL COMMENT '村ステータスコード',
	VILLAGE_STATUS_NAME VARCHAR(20) NOT NULL COMMENT '村ステータス名',
	PRIMARY KEY (VILLAGE_STATUS_CODE)
) COMMENT = '村ステータス';


CREATE TABLE VOTE
(
	VILLAGE_DAY_ID INT UNSIGNED NOT NULL COMMENT '村日付ID',
	VILLAGE_PLAYER_ID INT UNSIGNED NOT NULL COMMENT '投票元村参加者ID',
	TARGET_VILLAGE_PLAYER_ID INT UNSIGNED COMMENT '投票先村参加者ID',
	REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
	REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
	UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
	UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
	PRIMARY KEY (VILLAGE_DAY_ID, VILLAGE_PLAYER_ID)
) COMMENT = '投票';


CREATE TABLE VILLAGE_PLAYER_ACCESS_INFO
(
    VILLAGE_PLAYER_ACCESS_INFO_ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '村参加者アクセス情報ID',
    VILLAGE_PLAYER_ID INT UNSIGNED NOT NULL COMMENT '村参加者ID',
    IP_ADDRESS VARCHAR(64) NOT NULL COMMENT 'IPアドレス',
    REGISTER_DATETIME DATETIME NOT NULL COMMENT '登録日時',
    REGISTER_TRACE VARCHAR(64) NOT NULL COMMENT '登録トレース',
    UPDATE_DATETIME DATETIME NOT NULL COMMENT '更新日時',
    UPDATE_TRACE VARCHAR(64) NOT NULL COMMENT '更新トレース',
    PRIMARY KEY (VILLAGE_PLAYER_ACCESS_INFO_ID),
    CONSTRAINT UQ_village_player_access_info UNIQUE (VILLAGE_PLAYER_ID, IP_ADDRESS)
) COMMENT = '村参加者アクセス情報';


/* Create Indexes */

CREATE INDEX IX_MESSAGE_UNIXTIMESTAMP USING BTREE ON MESSAGE (MESSAGE_UNIXTIMESTAMP_MILLI DESC);



/* Create Foreign Keys */

ALTER TABLE ABILITY
	ADD CONSTRAINT FK_ABILITY_ABILITY_TYPE FOREIGN KEY (ABILITY_TYPE_CODE)
	REFERENCES ABILITY_TYPE (ABILITY_TYPE_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PLAYER
	ADD CONSTRAINT FK_PLAYER_AUTHORITY FOREIGN KEY (AUTHORITY_CODE)
	REFERENCES AUTHORITY (AUTHORITY_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SKILL
	ADD CONSTRAINT FK_SKILL_CAMP FOREIGN KEY (CAMP_CODE)
	REFERENCES CAMP (CAMP_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE
	ADD CONSTRAINT FK_VILLAGE_CAMP FOREIGN KEY (WIN_CAMP_CODE)
	REFERENCES CAMP (CAMP_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CHARA_IMAGE
	ADD CONSTRAINT FK_CHARA_IMAGE_CHARA FOREIGN KEY (CHARA_ID)
	REFERENCES CHARA (CHARA_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_PLAYER
	ADD CONSTRAINT FK_VILLAGE_PLAYER_CHARA FOREIGN KEY (CHARA_ID)
	REFERENCES CHARA (CHARA_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CHARA
	ADD CONSTRAINT FK_CHARA_CHARA_GROUP FOREIGN KEY (CHARA_GROUP_ID)
	REFERENCES CHARA_GROUP (CHARA_GROUP_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_PLAYER
	ADD CONSTRAINT FK_VILLAGE_PLAYER_DEAD_REASON FOREIGN KEY (DEAD_REASON_CODE)
	REFERENCES DEAD_REASON (DEAD_REASON_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CHARA_GROUP
	ADD CONSTRAINT FK_CHARA_GROUP_DESIGNER FOREIGN KEY (DESIGNER_ID)
	REFERENCES DESIGNER (DESIGNER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE CHARA_IMAGE
	ADD CONSTRAINT FK_CHARA_IMAGE_FACE_TYPE FOREIGN KEY (FACE_TYPE_CODE)
	REFERENCES FACE_TYPE (FACE_TYPE_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MESSAGE
	ADD CONSTRAINT FK_MESSAGE_FACE_TYPE FOREIGN KEY (FACE_TYPE_CODE)
	REFERENCES FACE_TYPE (FACE_TYPE_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MESSAGE
	ADD CONSTRAINT FK_MESSAGE_MESSAGE_TYPE FOREIGN KEY (MESSAGE_TYPE_CODE)
	REFERENCES MESSAGE_TYPE (MESSAGE_TYPE_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MESSAGE_RESTRICTION
	ADD CONSTRAINT FK_MESSAGE_RESTRICTION_MESSAGE_TYPE FOREIGN KEY (MESSAGE_TYPE_CODE)
	REFERENCES MESSAGE_TYPE (MESSAGE_TYPE_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_DAY
	ADD CONSTRAINT FK_VILLAGE_DAY_NOONNIGHT FOREIGN KEY (NOONNIGHT_CODE)
	REFERENCES NOONNIGHT (NOONNIGHT_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MESSAGE
	ADD CONSTRAINT FK_MESSAGE_PLAYER FOREIGN KEY (PLAYER_ID)
	REFERENCES PLAYER (PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PLAYER_DETAIL
	ADD CONSTRAINT FK_PLAYER_DETAIL_PLAYER FOREIGN KEY (PLAYER_ID)
	REFERENCES PLAYER (PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE
	ADD CONSTRAINT FK_VILLAGE_PLAYER FOREIGN KEY (CREATE_PLAYER_ID)
	REFERENCES PLAYER (PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_PLAYER
	ADD CONSTRAINT FK_VILLAGE_PLAYER_PLAYER FOREIGN KEY (PLAYER_ID)
	REFERENCES PLAYER (PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE COMING_OUT
	ADD CONSTRAINT FK_COMMING_OUT_SKILL FOREIGN KEY (SKILL_CODE)
	REFERENCES SKILL (SKILL_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_PLAYER
	ADD CONSTRAINT FK_VILLAGE_PLAYER_SKILL FOREIGN KEY (SKILL_CODE)
	REFERENCES SKILL (SKILL_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_PLAYER
	ADD CONSTRAINT FK_VILLAGE_PLAYER_SKILL_REQUEST FOREIGN KEY (REQUEST_SKILL_CODE)
	REFERENCES SKILL (SKILL_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_PLAYER
	ADD CONSTRAINT FK_VILLAGE_PLAYER_SECOND_SKILL_REQ FOREIGN KEY (SECOND_REQUEST_SKILL_CODE)
	REFERENCES SKILL (SKILL_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MESSAGE
	ADD CONSTRAINT FK_MESSAGE_VILLAGE FOREIGN KEY (VILLAGE_ID)
	REFERENCES VILLAGE (VILLAGE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MESSAGE_RESTRICTION
	ADD CONSTRAINT FK_MESSAGE_RESTRICTION_VILLAGE FOREIGN KEY (VILLAGE_ID)
	REFERENCES VILLAGE (VILLAGE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_DAY
	ADD CONSTRAINT FK_VILLAGE_DAY_VILLAGE FOREIGN KEY (VILLAGE_ID)
	REFERENCES VILLAGE (VILLAGE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_PLAYER
	ADD CONSTRAINT FK_VILLAGE_PLAYER_VILLAGE FOREIGN KEY (VILLAGE_ID)
	REFERENCES VILLAGE (VILLAGE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_SETTING
	ADD CONSTRAINT FK_VILLAGE_SETTINGS_VILLAGE FOREIGN KEY (VILLAGE_ID)
	REFERENCES VILLAGE (VILLAGE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ABILITY
	ADD CONSTRAINT FK_ABILITY_VILLAGE_DAY FOREIGN KEY (VILLAGE_DAY_ID)
	REFERENCES VILLAGE_DAY (VILLAGE_DAY_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE COMMIT
	ADD CONSTRAINT FK_COMMIT_VILLAGE_DAY FOREIGN KEY (VILLAGE_DAY_ID)
	REFERENCES VILLAGE_DAY (VILLAGE_DAY_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MESSAGE
	ADD CONSTRAINT FK_MESSAGE_VILLAGE_DAY FOREIGN KEY (VILLAGE_DAY_ID)
	REFERENCES VILLAGE_DAY (VILLAGE_DAY_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_PLAYER
	ADD CONSTRAINT FK_VILLAGE_PLAYER_VILLAGE_DAY FOREIGN KEY (DEAD_VILLAGE_DAY_ID)
	REFERENCES VILLAGE_DAY (VILLAGE_DAY_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VOTE
	ADD CONSTRAINT FK_VOTE_VILLAGE_DAY FOREIGN KEY (VILLAGE_DAY_ID)
	REFERENCES VILLAGE_DAY (VILLAGE_DAY_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ABILITY
	ADD CONSTRAINT FK_ABILITY_TARGET_VILLAGE_PLAYER FOREIGN KEY (TARGET_VILLAGE_PLAYER_ID)
	REFERENCES VILLAGE_PLAYER (VILLAGE_PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ABILITY
	ADD CONSTRAINT FK_ABILITY_VILLAGE_PLAYER FOREIGN KEY (VILLAGE_PLAYER_ID)
	REFERENCES VILLAGE_PLAYER (VILLAGE_PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE COMING_OUT
	ADD CONSTRAINT FK_COMMING_OUT_VILLAGE_PLAYER FOREIGN KEY (VILLAGE_PLAYER_ID)
	REFERENCES VILLAGE_PLAYER (VILLAGE_PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE COMMIT
	ADD CONSTRAINT FK_COMMIT_VILLAGE_PLAYER FOREIGN KEY (VILLAGE_PLAYER_ID)
	REFERENCES VILLAGE_PLAYER (VILLAGE_PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MESSAGE
	ADD CONSTRAINT FK_MESSAGE_VILLAGE_PLAYER FOREIGN KEY (VILLAGE_PLAYER_ID)
	REFERENCES VILLAGE_PLAYER (VILLAGE_PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MESSAGE
	ADD CONSTRAINT FK_MESSAGE_VILLAGE_PLAYER_TO FOREIGN KEY (TO_VILLAGE_PLAYER_ID)
	REFERENCES VILLAGE_PLAYER (VILLAGE_PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VOTE
	ADD CONSTRAINT FK_VOTE_VILLAGE_PLAYER FOREIGN KEY (VILLAGE_PLAYER_ID)
	REFERENCES VILLAGE_PLAYER (VILLAGE_PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VOTE
	ADD CONSTRAINT FK_VOTE_TARGET_VILLAGE_PLAYER FOREIGN KEY (TARGET_VILLAGE_PLAYER_ID)
	REFERENCES VILLAGE_PLAYER (VILLAGE_PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_SETTING
	ADD CONSTRAINT FK_VILLAGE_SETTING_VILLAGE_SETTING_ITEM FOREIGN KEY (VILLAGE_SETTING_ITEM_CODE)
	REFERENCES VILLAGE_SETTING_ITEM (VILLAGE_SETTING_ITEM_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_CHARA_GROUP
	ADD CONSTRAINT FK_VILLAGE_CHARA_GROUP_VILLAGE_ID FOREIGN KEY (VILLAGE_ID)
	REFERENCES VILLAGE (VILLAGE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_CHARA_GROUP
	ADD CONSTRAINT FK_VILLAGE_CHARA_GROUP_CHARA_GROUP_ID FOREIGN KEY (CHARA_GROUP_ID)
	REFERENCES CHARA_GROUP (CHARA_GROUP_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE
	ADD CONSTRAINT FK_VILLAGE_VILLAGE_STATUS FOREIGN KEY (VILLAGE_STATUS_CODE)
	REFERENCES VILLAGE_STATUS (VILLAGE_STATUS_CODE)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE VILLAGE_PLAYER_ACCESS_INFO
	ADD CONSTRAINT FK_VILLAGE_PLAYER_ACCESS_INFO_VILLAGE_PLAYER FOREIGN KEY (VILLAGE_PLAYER_ID)
	REFERENCES VILLAGE_PLAYER (VILLAGE_PLAYER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
