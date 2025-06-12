package de.eztxm.armydiscordbot.listener

import de.eztxm.armydiscordbot.util.Embed
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.channel.attribute.IPostContainer
import net.dv8tion.jda.api.entities.channel.concrete.ForumChannel
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.utils.messages.MessageCreateData
import org.json.JSONObject

class ModalListener : ListenerAdapter() {

    override fun onModalInteraction(event: ModalInteractionEvent) {
        when (event.interaction.modalId) {
            "apply" -> {
                val applicationForum = event.guild!!.getForumChannelById("1382785949364125877")
                val embed = Embed.application(JSONObject()
                    .put("nameAge", event.getValue("nameAge"))
                    .put("position", event.getValue("position"))
                    .put("hobbies", event.getValue("hobbies"))
                    .put("strongWeek", event.getValue("strongWeek"))
                    .put("experience", event.getValue("experience"))
                )
                applicationForum?.createForumPost("Bewerbung ", MessageCreateData.fromEmbeds(listOf(embed)))
            }
        }
    }
}