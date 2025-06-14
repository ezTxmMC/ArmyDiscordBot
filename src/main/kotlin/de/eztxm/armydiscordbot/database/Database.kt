package de.eztxm.armydiscordbot.database

import de.eztxm.armydiscordbot.database.model.UserManagement
import de.eztxm.ezlib.api.database.SQLConnection
import de.eztxm.ezlib.config.`object`.JsonObject
import de.eztxm.ezlib.database.SQLiteConnection


class Database {
    var sqlConnection: SQLConnection
    var userManagement: UserManagement

    constructor(jsonObject: JsonObject) {
        sqlConnection = SQLiteConnection(jsonObject.getConverted("Path").asString(), jsonObject.getConverted("File").asString())
        userManagement = UserManagement(this)
        userManagement.createTable()
    }
}
