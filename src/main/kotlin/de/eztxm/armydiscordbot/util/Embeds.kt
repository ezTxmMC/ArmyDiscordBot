package de.eztxm.armydiscordbot.util

import de.eztxm.armydiscordbot.ArmyDiscordBot
import de.eztxm.ezlib.config.`object`.JsonArray
import de.eztxm.ezlib.config.`object`.JsonObject
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.User
import java.awt.Color

class Embeds {
    companion object {

        fun joinServer(user: User): MessageEmbed {
            val embedBuilder = EmbedBuilder()
            embedBuilder.setTitle("ezArmy")
            embedBuilder.setFooter("powered by eztxm.de")
            embedBuilder.setColor(Color(0, 95, 255))
            embedBuilder.setDescription("Herzlich willkommen auf dem Community-Discord der ezArmy `" + user.effectiveName + "`.")
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
            val jsonArray = ArmyDiscordBot.jsonConfig.get("Embeds").asJsonObject()["ApplyEmbed"] as JsonArray<*>
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

        fun application(application: JsonObject): MessageEmbed {
            val embedBuilder = EmbedBuilder()
            embedBuilder.setTitle("ezArmy")
            embedBuilder.setFooter("powered by eztxm.de")
            embedBuilder.setColor(Color(0, 95, 255))
            embedBuilder.addField("Name und Alter", application.getConverted("nameAge").asString(), false)
            embedBuilder.addField("Stelle", application.getConverted("position").asString(), false)
            embedBuilder.addField("Hobbys", application.getConverted("hobbies").asString(), false)
            embedBuilder.addField("Stärken und Schwächen", application.getConverted("strongWeek").asString(), false)
            embedBuilder.addField("Erfahrung", application.getConverted("experience").asString(), false)
            return embedBuilder.build()
        }

        fun applicationAnswer(accept: Boolean): MessageEmbed {
            val embedBuilder = EmbedBuilder()
            embedBuilder.setTitle("ezArmy")
            embedBuilder.setFooter("powered by eztxm.de")
            embedBuilder.setColor(Color(0, 95, 255))
            embedBuilder.setDescription(
                if (accept) {
                    "Herzlichen Glückwunsch! Deine Bewerbung wurde angenommen. Wir melden uns die nächsten Tage bei dir, um einen Termin für ein Bewerbungsgespräch festzulegen.\n\nWir wünschen dir weiterhin viel erfolg!"
                } else {
                    "Leider wurde deine Bewerbung abgelehnt. Versuch es gerne in 3 Monaten noch einmal."
                }
            )
            return embedBuilder.build()
        }
    }
}
