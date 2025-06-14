package de.eztxm.armydiscordbot.database.model

import de.eztxm.armydiscordbot.database.Database

class UserManagement(internal val database: Database) {

    fun createTable() {
        database.sqlConnection.put("CREATE TABLE IF NOT EXISTS users(id VARCHAR(75) PRIMARY KEY, xp BIGINT DEFAULT 0, cookieCount BIGINT DEFAULT 0);")
    }

    fun addUser(id: String?) {
        requireNotNull(id)
        if (userExist(id)) {
            println("TE")
            return
        }
        database.sqlConnection.put("INSERT INTO users(id) VALUES (?);", id)
    }

    fun removeUser(id: String?) {
        requireNotNull(id)
        if (userExist(id)) {
            return
        }
        database.sqlConnection.put("DELETE FROM users WHERE id = ?;", id)
    }

    fun userExist(id: String?): Boolean {
        requireNotNull(id)
        return try {
            val resultSet = database.sqlConnection.query("SELECT id FROM users WHERE id = ?;", id)
            println(resultSet.next())
            resultSet?.next() == true && resultSet.getString("id") == id
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
