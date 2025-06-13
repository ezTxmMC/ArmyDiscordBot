package de.eztxm.armydiscordbot

import de.eztxm.armydiscordbot.database.Database
import de.eztxm.armydiscordbot.event.*
import de.eztxm.armydiscordbot.util.UpdateManager
import de.eztxm.ezlib.config.JsonConfig
import de.eztxm.ezlib.config.`object`.JsonArray
import de.eztxm.ezlib.config.`object`.JsonObject
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object ArmyDiscordBot {
    internal lateinit var jsonConfig: JsonConfig
    internal lateinit var database: Database
    internal lateinit var jda: JDA

    @JvmStatic
    fun main(args: Array<String>) {
        jsonConfig = JsonConfig("data", "config.json")
        jsonConfig.addDefault("Token", "< insert bot token >")
        val embeds = JsonObject()
        val applyEmbed = JsonArray<String>()
        applyEmbed.add("This is the apply embed")
        embeds.put("ApplyEmbed", applyEmbed)
        jsonConfig.addDefault("Embeds", embeds)
        val databaseData = JsonObject()
        databaseData.put("Path", "data")
        databaseData.put("File", "sqlite.db")
        database = Database(databaseData)
        jda = JDABuilder.createDefault(jsonConfig["Token"].asString())
            .enableCache(CacheFlag.MEMBER_OVERRIDES)
            .disableCache(CacheFlag.STICKER, CacheFlag.EMOJI, CacheFlag.VOICE_STATE)
            .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_PRESENCES)
            .addEventListeners(
                JoinListener(),
                LeaveListener(),
                ModalListener(),
                ButtonListener(),
                SlashCommandListener()
            )
            .build().awaitReady()
        val commands = jda.updateCommands()
        commands.addCommands(
            Commands.slash("keks", "Du bekommst einen Keks."),
            Commands.slash("updateembeds", "Update die Embeds.").setDefaultPermissions(
                DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)
            ),
            Commands.slash("user", "User Management Befehl.")
                .addSubcommands(
                    SubcommandData(
                        "add",
                        "User zum Management manuell hinzufügen."
                    ).addOption(OptionType.USER, "user", "Benutzer angeben"),
                    SubcommandData(
                        "remove",
                        "User vom Management manuell entfernen."
                    ).addOption(OptionType.USER, "user", "Benutzer angeben")
                )
        ).queue()
        val executor = Executors.newScheduledThreadPool(2)
        executor.scheduleAtFixedRate({
            UpdateManager.updateEmbeds(jda)
        }, 0, 3, TimeUnit.HOURS)
        Runtime.getRuntime().addShutdownHook(Thread {
            jda.awaitShutdown()
        })
    }
}
