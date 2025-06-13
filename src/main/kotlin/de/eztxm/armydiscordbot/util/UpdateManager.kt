package de.eztxm.armydiscordbot.util

import de.eztxm.armydiscordbot.ArmyDiscordBot
import de.eztxm.ezlib.config.JsonConfig
import net.dv8tion.jda.api.JDA

class UpdateManager {
    companion object {

        fun updateEmbeds(jda: JDA) {
            ArmyDiscordBot.jsonConfig = JsonConfig("data", "config.json")
            val guild = jda.getGuildById("962473401157574667")
            val channel = guild!!.getTextChannelById("1257682699498557460")
            if (channel!!.iterableHistory.complete().isEmpty()) {
                channel.sendMessageEmbeds(Embed.apply()).setActionRow(Button.apply()).queue()
                return
            }
            val message = channel.retrieveMessageById(channel.latestMessageId).complete()
            val embed = message.embeds.first()
            if (embed.description!! != Embed.apply().description) {
                val edit = message.editMessageEmbeds(Embed.apply())
                if (message.actionRows.isEmpty()) {
                    edit.setActionRow(Button.apply())
                }
                edit.queue()
            }
        }
    }
}
