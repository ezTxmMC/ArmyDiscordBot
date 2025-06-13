package de.eztxm.armydiscordbot.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.pool.HikariPool
import de.eztxm.armydiscordbot.database.model.UserManagement
import de.eztxm.ezlib.config.`object`.JsonObject
import de.eztxm.ezlib.database.Arguments
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException


class Database {
    lateinit var pool: HikariPool
    var tableName: String

    var userManagement: UserManagement

    constructor(jsonObject: JsonObject) {
        connect(jsonObject.getConverted("Path").asString(), jsonObject.getConverted("File").asString())
        tableName = jsonObject.getConverted("Table").asString()
        userManagement = UserManagement(this)
        userManagement.createTable()
    }

    fun connect(path: String, file: String): HikariPool? {
        val config = HikariConfig()
        config.connectionTimeout = 7500L
        config.setMaximumPoolSize(8)
        config.setMinimumIdle(1)
        config.setJdbcUrl(
            java.lang.String.format(
                "jdbc:sqlite:%s/%s",
                System.getProperty("user.dir") + "/" + path,
                file
            )
        )
        pool = HikariPool(config)
        try {
            pool.connection.use { connection ->
                val statement: PreparedStatement = connection.prepareStatement("SELECT 1")
                statement.queryTimeout = 15
                statement.executeQuery()
                return pool
            }
        } catch (e: SQLException) {
            return null
        }
    }

    fun put(query: String, vararg objects: Any) {
        pool.connection.use { connection ->
            val statement: PreparedStatement = connection.prepareStatement(query)
            statement.queryTimeout = 15
            Arguments.set(objects, statement)
            statement.close();
        }
    }

    /***
     * TODO: Nicht funktionstüchtig - ResultSet ist immer closed.
     */
    fun query(query: String, vararg objects: Any): ResultSet {
        pool.connection.use { connection ->
            val statement: PreparedStatement = connection.prepareStatement(query)
            statement.queryTimeout = 15
            Arguments.set(objects, statement)
            return statement.executeQuery();
        }
    }
}
