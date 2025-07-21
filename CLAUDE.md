# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## プロジェクト概要

Firewolf APIは人狼ゲームのバックエンドAPIを提供するKotlin + Spring Bootアプリケーションです。

## 開発コマンド

### ビルド・実行
```bash
# ビルドとテスト
./gradlew build

# テストのみ実行
./gradlew test

# 開発環境で起動
./gradlew bootRun --args='--firebase.adminsdk.secretkey.path=${秘密鍵パス} --firebase.database.url=${FirebaseデータベースURL}'

# Dockerイメージビルド（Jib使用）
./gradlew jibDockerBuild
```

### データベース管理（DBFlute）
```bash
# DBセットアップ（スキーマ作成、初期データ投入）
cd dbflute_firewolfdb
sh manage.sh 0

# DBFlute Entityの再生成
cd dbflute_firewolfdb
sh manage.sh regenerate
```

## アーキテクチャ概要

### レイヤー構成
- **api層** (`com.ort.firewolf.api`): REST APIコントローラー
- **application層** (`com.ort.firewolf.application`): ビジネスロジック、トランザクション境界
- **domain層** (`com.ort.firewolf.domain`): ドメインモデル、ビジネスルール
- **infrastructure層** (`com.ort.firewolf.infrastructure`): 外部システム連携、データアクセス実装

### 重要な技術要素
- **認証**: Firebase Admin SDKによるJWT検証
- **データアクセス**: DBFluteによるタイプセーフなDB操作
- **キャッシュ**: Caffeineによるインメモリキャッシュ
- **外部連携**: Twitter API、Slack API、Discord Webhook

### DBFluteの使用方法
- Entityは自動生成されるため直接編集しない
- ビジネスロジックは`exbhv`（拡張Behavior）に実装
- 検索条件は`ConditionBean`を使用してタイプセーフに記述

## デプロイ
GitHub Actionsで自動デプロイ（`.github/workflows/deploy.yml`）
- mainブランチへのpush時に自動実行
- ARM64向けDockerイメージをビルドしてKubernetesへデプロイ

## 開発方針

### 要件定義と設計の管理
開発を進める際は以下の手順に従ってください：

1. **要件確認**: `.claude/requirements.md`に記載された要件を確認
2. **設計作成**: 実装前に`.claude/designs.md`に設計を記載
3. **実装**: 設計に基づいて実装を進める

この方針により、要件の見落としを防ぎ、設計と実装の一貫性を保つことができます。

### コミットの単位
適切な粒度でコミットを行い、変更履歴を追跡しやすくします：

- **1つのコミットには1つの論理的な変更**を含める
- 依存関係の更新、リファクタリング、機能追加は別々のコミットに分ける
- ビルドが通る状態でコミットする
- コミットメッセージは変更内容を明確に説明する

例：
- ✅ 良い例: "Gradle 6.4.1から8.14.3へアップグレード"
- ✅ 良い例: "Spring Boot 2.3.0から2.7.18へ移行"
- ❌ 悪い例: "いろいろ更新"

### 現在進行中のタスク
- **Spring Boot 3.4への移行**（`.claude/designs.md`参照）
  - 段階的移行アプローチを採用
  - Java 8 → Java 21（最新LTS）への移行が必要
  - Jakarta EEへの移行とSpring Security設定の更新が必要