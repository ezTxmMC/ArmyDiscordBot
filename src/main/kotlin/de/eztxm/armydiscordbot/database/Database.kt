package de.eztxm.armydiscordbot.database

import de.eztxm.armydiscordbot.database.model.ApplicationManagement
import de.eztxm.armydiscordbot.database.model.UserManagement
import de.eztxm.ezlib.api.database.SQLConnection
import de.eztxm.ezlib.config.`object`.JsonObject
import de.eztxm.ezlib.database.MariaDBConnection


class Database {
    var sqlConnection: SQLConnection
    var userManagement: UserManagement
    var applicationManagement: ApplicationManagement

    constructor(jsonObject: JsonObject) {
        sqlConnection = MariaDBConnection(jsonObject.getConverted("Host").asString(), jsonObject.getConverted("Port").asInteger(), jsonObject.getConverted("Database").asString(), jsonObject.getConverted("Username").asString(), jsonObject.getConverted("Password").asString())
        userManagement = UserManagement(this)
        applicationManagement = ApplicationManagement(this)
        userManagement.createTable()
        applicationManagement.createTable()
    }
}
