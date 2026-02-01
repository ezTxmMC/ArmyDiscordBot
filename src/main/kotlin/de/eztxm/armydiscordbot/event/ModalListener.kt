package de.eztxm.armydiscordbot.event

import de.eztxm.armydiscordbot.ArmyDiscordBot
import de.eztxm.armydiscordbot.util.Buttons
import de.eztxm.armydiscordbot.util.Embeds
import de.eztxm.ezlib.config.`object`.JsonObject
import net.dv8tion.jda.api.components.actionrow.ActionRow
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder

class ModalListener : ListenerAdapter() {

    override fun onModalInteraction(event: ModalInteractionEvent) {
        when (event.interaction.modalId) {
            "apply" -> {
                if (ArmyDiscordBot.database.applicationManagement.applicationOpened(event.user.id)) {
                    event.reply("Du hast bereits eine offene Bewerbung.").setEphemeral(true).queue()
                    return
                }
                val applicationForum = event.guild!!.getForumChannelById("1382785949364125877")
                val application = JsonObject()
                application.put("nameAge", event.getValue("nameAge")?.asString)
                application.put("position", event.getValue("position")?.asString)
                application.put("hobbies", event.getValue("hobbies")?.asString)
                application.put("strongWeek", event.getValue("strongWeek")?.asString)
                application.put("experience", event.getValue("experience")?.asString)
                val embed = Embeds.application(application)
                val messageData = MessageCreateBuilder()
                    .setEmbeds(embed)
                    .setComponents(
                        ActionRow.of(
                            Buttons.acceptApplication(),
                            Buttons.declineApplication()
                        )
                    )
                    .build()
                applicationForum?.createForumPost(
                    "Bewerbung ${event.user.effectiveName}",
                    messageData
                )?.queue { forumPost ->
                    ArmyDiscordBot.database.applicationManagement.addApplication(
                        event.user.id,
                        forumPost.threadChannel.id
                    )
                }
                event.reply("Deine Bewerbung wurde ans Team vermittelt.").setEphemeral(true).queue()
            }
        }
    }
}
