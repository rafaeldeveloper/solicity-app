package com.example.gleilson.soliceservices.dao;

/**
 * Created by gleilson on 15/10/16.
 */
public class ScriptSql {

    public static String getCreateProfile() {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE profile (");
        sqlBuilder.append("  id        INTEGER PRIMARY KEY");
        sqlBuilder.append(", email     TEXT NOT NULL");
        sqlBuilder.append(", firstname TEXT NOT NULL");
        sqlBuilder.append(", lastname  TEXT NOT NULL");
        sqlBuilder.append(", url_image TEXT");
        sqlBuilder.append(", site      TEXT");
        sqlBuilder.append(", phone     TEXT");
        sqlBuilder.append(", token     TEXT");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

    public static String getCreateCategory() {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE categories (");
        sqlBuilder.append("  id        INTEGER PRIMARY KEY");
        sqlBuilder.append(", name      TEXT NOT NULL");
        sqlBuilder.append(", url_image TEXT");
        sqlBuilder.append(", qty       INTEGER");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

    public static String getCreateProfessionals() {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE professionals (");
        sqlBuilder.append("  id         INTEGER PRIMARY KEY");
        sqlBuilder.append(", email      TEXT NOT NULL");
        sqlBuilder.append(", firstname  TEXT NOT NULL");
        sqlBuilder.append(", lastname   TEXT NOT NULL");
        sqlBuilder.append(", url_image  TEXT");
        sqlBuilder.append(", site       TEXT");
        sqlBuilder.append(", phone      TEXT");
        sqlBuilder.append(", categories TEXT");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

}
