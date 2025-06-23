package de.eztxm.armydiscordbot.database.model

import de.eztxm.armydiscordbot.database.Database

class ApplicationManagement(internal val database: Database) {

    fun createTable() {
        database.sqlConnection.put("CREATE TABLE IF NOT EXISTS applications(id INTEGER PRIMARY KEY AUTO_INCREMENT, authorId VARCHAR(75) NOT NULL, threadId VARCHAR(75) NOT NULL, isOpen TINYINT(1));")
    }

    fun addApplication(authorId: String?, threadId: String?) {
        requireNotNull(authorId)
        requireNotNull(threadId)
        if (applicationOpened(authorId)) {
            return
        }
        database.sqlConnection.put("INSERT INTO applications(authorId,threadId,isOpen) VALUES (?,?,1);", authorId, threadId)
    }

    fun closeApplication(authorId: String?) {
        requireNotNull(authorId)
        if (!applicationOpened(authorId)) {
            return
        }
        database.sqlConnection.put("UPDATE applications SET isOpen = 0 WHERE authorId = ?;", authorId)
    }

    fun getUserByThread(threadId: String?): String? {
        requireNotNull(threadId)
        try {
            val resultSet = database.sqlConnection.query("SELECT authorId FROM applications WHERE threadId = ?;", threadId)
            if (resultSet.next()) {
                return resultSet.getString("authorId")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun applicationOpened(authorId: String?): Boolean {
        requireNotNull(authorId)
        try {
            val resultSet = database.sqlConnection.query("SELECT isOpen FROM applications WHERE authorId = ?;", authorId)
            if (resultSet.next()) {
                return resultSet.getInt("isOpen") == 1
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}
