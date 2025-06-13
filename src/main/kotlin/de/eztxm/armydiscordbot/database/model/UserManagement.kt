package de.eztxm.armydiscordbot.database.model

import de.eztxm.armydiscordbot.database.Database

class UserManagement(internal val database: Database) {

    fun createTable() {
        database.put("CREATE TABLE IF NOT EXISTS users(id VARCHAR(75) PRIMARY KEY, xp BIGINT DEFAULT 0, cookieCount BIGINT DEFAULT 0);")
    }

    fun addUser(id: String?) {
        requireNotNull(id)
        if (userExist(id)) {
            println("TE")
            return
        }
        database.put("INSERT INTO users(id) VALUES (?);", id)
    }

    fun removeUser(id: String?) {
        requireNotNull(id)
        if (userExist(id)) {
            return
        }
        database.put("DELETE FROM users WHERE id = ?;", id)
    }

    fun userExist(id: String?): Boolean {
        requireNotNull(id)
        return try {
            val resultSet = database.query("SELECT id FROM users WHERE id = ?;", id)
            println(resultSet.isClosed)
            resultSet.next()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
