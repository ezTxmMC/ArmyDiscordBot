package de.eztxm.armydiscordbot

import de.eztxm.armydiscordbot.listener.ButtonListener
import de.eztxm.armydiscordbot.listener.JoinListener
import de.eztxm.armydiscordbot.listener.LeaveListener
import de.eztxm.armydiscordbot.listener.ModalListener
import de.eztxm.armydiscordbot.util.UpdateManager
import de.eztxm.config.JsonConfig
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object ArmyDiscordBot {
    internal var jda: JDA? = null
    internal var jsonConfig: JsonConfig? = null

    @JvmStatic
    fun main(args: Array<String>) {
        jsonConfig = JsonConfig("data", "config.json")
        jsonConfig!!.addDefault("Token", "< insert bot token >")
        val embeds = JSONObject()
        val applyEmbed = JSONArray()
        applyEmbed.put("This is the apply embed")
        embeds.put("ApplyEmbed", applyEmbed)
        jsonConfig!!.addDefault("Embeds", embeds)
        jda = JDABuilder.createDefault(jsonConfig!!["Token"].asString())
            .enableCache(CacheFlag.MEMBER_OVERRIDES)
            .disableCache(CacheFlag.STICKER, CacheFlag.EMOJI, CacheFlag.VOICE_STATE)
            .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_PRESENCES)
            .addEventListeners(JoinListener())
            .addEventListeners(LeaveListener())
            .addEventListeners(ModalListener())
            .addEventListeners(ButtonListener())
            .build().awaitReady()
        val executor = Executors.newScheduledThreadPool(2)
        executor.scheduleAtFixedRate({
            UpdateManager.updateEmbeds(jda!!)
        }, 0, 3, TimeUnit.HOURS)
    }
}
