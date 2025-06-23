package de.eztxm.armydiscordbot.event

import de.eztxm.armydiscordbot.ArmyDiscordBot
import de.eztxm.armydiscordbot.util.Embed
import de.eztxm.armydiscordbot.util.Modal
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class ButtonListener : ListenerAdapter() {

    override fun onButtonInteraction(event: ButtonInteractionEvent) {
        when (event.interaction.button.id) {
            "apply" -> event.replyModal(Modal.apply()).queue()
            "acceptApplication" -> {
                val threadId = (event.channel as ThreadChannel).id
                val authorId = ArmyDiscordBot.database.applicationManagement.getUserByThread(threadId)
                if (!ArmyDiscordBot.database.applicationManagement.applicationOpened(authorId)) {
                    return
                }
                ArmyDiscordBot.database.applicationManagement.closeApplication(authorId)
                val member = event.guild!!.getMemberById(authorId!!)
                if (member == null) {
                    return
                }
                event.guild!!.getCategoryById("1257682614064517190")!!
                    .createTextChannel("\uD83D\uDCD1｜${member.user.globalName}").queue { textChannel ->
                        textChannel.upsertPermissionOverride(event.guild!!.publicRole)
                            .setDenied(Permission.VIEW_CHANNEL)
                            .queue()
                        textChannel.upsertPermissionOverride(member).setAllowed(Permission.VIEW_CHANNEL).queue()
                        textChannel.sendMessageEmbeds(Embed.applicationAnswer(true)).queue()
                        textChannel.sendMessage(event.member!!.asMention).queue()
                    }
                event.reply("${event.member!!.user.effectiveName} hat die Bewerbung angenommen.").setEphemeral(false)
                    .queue()
            }

            "declineApplication" -> {
                val threadId = (event.channel as ThreadChannel).id
                val authorId = ArmyDiscordBot.database.applicationManagement.getUserByThread(threadId)
                if (!ArmyDiscordBot.database.applicationManagement.applicationOpened(authorId)) {
                    return
                }
                ArmyDiscordBot.database.applicationManagement.closeApplication(authorId)
                val member = event.guild!!.getMemberById(authorId!!)
                if (member == null) {
                    return
                }
                event.guild!!.getCategoryById("1257682614064517190")!!
                    .createTextChannel("\uD83D\uDCD1｜${member.user.globalName}").queue { textChannel ->
                        textChannel.upsertPermissionOverride(event.guild!!.publicRole)
                            .setDenied(Permission.VIEW_CHANNEL)
                            .queue()
                        textChannel.upsertPermissionOverride(member).setAllowed(Permission.VIEW_CHANNEL).setDenied(
                            Permission.MESSAGE_SEND
                        ).queue()
                        textChannel.sendMessageEmbeds(Embed.applicationAnswer(false)).queue()
                        textChannel.sendMessage(event.member!!.asMention).queue()
                    }
                event.reply("${event.member!!.user.effectiveName} hat die Bewerbung abgelehnt.").setEphemeral(false)
                    .queue()
            }
        }
    }
}
