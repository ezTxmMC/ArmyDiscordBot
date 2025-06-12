package de.eztxm.armydiscordbot.util

import de.eztxm.armydiscordbot.ArmyDiscordBot
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.User
import org.json.JSONArray
import org.json.JSONObject
import java.awt.Color
import java.lang.StringBuilder

class Embed {
    companion object {

        fun joinServer(user: User): MessageEmbed {
            val embedBuilder = EmbedBuilder()
            embedBuilder.setTitle("ezArmy")
            embedBuilder.setFooter("powered by eztxm.de")
            embedBuilder.setColor(Color(0, 95, 255))
            embedBuilder.setDescription("Herzlich willkommen auf dem Community-Discord von ezTxmMC `" + user.effectiveName + "`.")
            return embedBuilder.build()
        }

        fun leaveServer(user: User): MessageEmbed {
            val embedBuilder = EmbedBuilder()
            embedBuilder.setTitle("ezArmy")
            embedBuilder.setFooter("powered by eztxm.de")
            embedBuilder.setColor(Color(0, 95, 255))
            embedBuilder.setDescription("`" + user.effectiveName + "` hat den Server leider verlassen.")
            return embedBuilder.build()
        }

        fun apply(): MessageEmbed {
            val embedBuilder = EmbedBuilder()
            val jsonArray = ArmyDiscordBot.jsonConfig!!.get("Embeds").asJsonObject().get("ApplyEmbed").asObject() as JSONArray
            val stringBuilder = StringBuilder()
            for (any in jsonArray) {
                val string = any as String
                stringBuilder.append(string).append("\n")
            }
            embedBuilder.setTitle("**Bewerbungsticket**")
            embedBuilder.setFooter("powered by eztxm.de")
            embedBuilder.setColor(Color(0, 95, 255))
            embedBuilder.setDescription(stringBuilder.toString())
            return embedBuilder.build()
        }

        fun application(application: JSONObject): MessageEmbed {
            val embedBuilder = EmbedBuilder()
            embedBuilder.setTitle("ezArmy")
            embedBuilder.setFooter("powered by eztxm.de")
            embedBuilder.setColor(Color(0, 95, 255))
            embedBuilder.addField("Name und Alter", application.getString("nameAge"), false)
            embedBuilder.addField("Stelle", application.getString("position"), false)
            embedBuilder.addField("Hobbys", application.getString("hobbies"), false)
            embedBuilder.addField("Stärken und Schwächen", application.getString("strongWeek"), false)
            embedBuilder.addField("Erfahrung", application.getString("experience"), false)
            return embedBuilder.build()
        }
    }
}
