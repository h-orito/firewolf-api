# FIREWOLF

## Overview

- 人狼ゲームがプレイできるサイトです
- Kotlin で API を提供します
- [howling-wolf-api](https://github.com/h-orito/howling-wolf-api)の派生です
- 画面側は[firewolf-ui](https://github.com/h-orito/firewolf-ui)を参照

## Requirement for local development

- Java 21

    - JDK 21以上が必須です

- mysql

    - docker を使うなりインストールするなりして用意してください
    - ポートは 3306 を使用します

- Firebase

    - JWT検証にFirebase Admin Sdkを利用しているため、プロジェクトを作成し、秘密鍵を生成しておいてください （後の手順で秘密鍵のファイルパス、データベースURLを利用します）
      see https://firebase.google.com/docs/admin/setup?hl=ja

## Setup for local development

- clone

    - 好きな場所にこのプロジェクトを clone してください
    - IntelliJ なり Eclipse なり好きな IDE で開いてください
    - gradle プロジェクトとしてインポートすると良いでしょう
    - インポートしたら依存ライブラリを取り込んでおいてください

* DB

    - スキーマ、ユーザ情報は`application.yml`参照
    - 以下実行するとローカル開発用 DB がセットアップされます

  ```bash
  cd dbflute_xxxdb
  sh manage.sh 0
  ```

* 起動

    - 以下の実行引数とともに`FirewolfApplication#main` を実行
        - `--firebase.adminsdk.secretkey.path=${Firebaseサービスアカウント秘密鍵の配置パス}`
        - `--firebase.database.url=${FirebaseデータベースURL}`
    - http://localhost:8087/firewolf/ がベース URL になります

## Run

- 実行例

```
$ java -jar firewolf.jar \
	--spring.profiles.active=prd \
	--spring.datasource.url=jdbc:mysql://${server ip and port}/${database name}?characterEncoding=UTF-8 \
	--spring.datasource.username=${db user name} \
	--spring.datasource.password=${db user pass} \
	--firebase.adminsdk.secretkey.path=${Firebaseサービスアカウント秘密鍵の配置パス} \
	--firebase.database.url=${FirebaseデータベースURL} \
```

## Contribution

- こんな感じでお願いします

```
1. Fork it
2. Create a feature branch from develop ( e.g. feature/my-new-feature )
3. Implement your changes
4. Run JUnit test (please implement) and confirm that it passes
5. Commit your changes
6. Rebase your local changes against the develop branch
7. Create new Pull Request
```

- ブランチ名は`feature/{変更内容}`でお願いします。
    - Issue と紐づく場合は`#{Issue番号}`を含めてください。 e.g. `feature/valid_password#1`

## License

- MIT License

## Author

[h-orito](https://github.com/h-orito)

## Other

- API を提供しますが、後方互換性は特別な場合を除いて保つつもりはありません
