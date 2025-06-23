package de.eztxm.armydiscordbot.database.model

import de.eztxm.armydiscordbot.database.Database

class UserManagement(internal val database: Database) {

    fun createTable() {
        database.sqlConnection.put("CREATE TABLE IF NOT EXISTS users(id VARCHAR(75) PRIMARY KEY, xp BIGINT DEFAULT 0, cookieCount BIGINT DEFAULT 0);")
    }

    fun addUser(id: String?) {
        requireNotNull(id)
        if (userExist(id)) {
            return
        }
        database.sqlConnection.put("INSERT INTO users(id) VALUES (?);", id)
    }

    fun removeUser(id: String?) {
        requireNotNull(id)
        if (!userExist(id)) {
            return
        }
        database.sqlConnection.put("DELETE FROM users WHERE id = ?;", id)
    }

    fun addUserXP(id: String?, xp: Long) {
        try {
            val resultSet = database.sqlConnection.query("SELECT xp FROM users WHERE id = ?;", id)
            if (resultSet.next()) {
                val xp = resultSet.getLong("xp") + xp
                database.sqlConnection.put("UPDATE users SET xp = ? WHERE id = ?;", xp, id)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun userExist(id: String?): Boolean {
        requireNotNull(id)
        try {
            val resultSet = database.sqlConnection.query("SELECT id FROM users WHERE id = ?;", id)
            if (resultSet.next()) {
                return resultSet.getString("id") == id
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}
