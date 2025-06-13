package de.eztxm.armydiscordbot.event

import de.eztxm.armydiscordbot.util.Embed
import de.eztxm.ezlib.config.`object`.JsonObject
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.utils.messages.MessageCreateData

class ModalListener : ListenerAdapter() {

    override fun onModalInteraction(event: ModalInteractionEvent) {
        when (event.interaction.modalId) {
            "apply" -> {
                val applicationForum = event.guild!!.getForumChannelById("1382785949364125877")
                val application = JsonObject()
                application.put("nameAge", event.getValue("nameAge")?.asString)
                application.put("position", event.getValue("position")?.asString)
                application.put("hobbies", event.getValue("hobbies")?.asString)
                application.put("strongWeek", event.getValue("strongWeek")?.asString)
                application.put("experience", event.getValue("experience")?.asString)
                val embed = Embed.application(application)
                applicationForum?.createForumPost(
                    "Bewerbung $event.user.effectiveName",
                    MessageCreateData.fromEmbeds(listOf(embed))
                )?.queue()
                event.reply("Deine Bewerbung wurde ans Team vermittelt.").setEphemeral(true).queue()
            }
        }
    }
}
