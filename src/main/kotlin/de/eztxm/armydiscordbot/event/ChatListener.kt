package de.eztxm.armydiscordbot.event

import de.eztxm.armydiscordbot.ArmyDiscordBot
import net.dv8tion.jda.api.entities.MessageType
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class ChatListener : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.member is null) {
            return
        }
        when (event.message.type) {
            MessageType.GUILD_MEMBER_JOIN -> ArmyDiscordBot.database.userManagement.addUserXP(event.member!!.id, 50)
            MessageType.GUILD_MEMBER_BOOST -> ArmyDiscordBot.database.userManagement.addUserXP(event.member!!.id, 100)
            MessageType.THREAD_CREATED -> ArmyDiscordBot.database.userManagement.addUserXP(event.member!!.id, 20)
            MessageType.SLASH_COMMAND -> ArmyDiscordBot.database.userManagement.addUserXP(event.member!!.id, 15)
            MessageType.INLINE_REPLY -> ArmyDiscordBot.database.userManagement.addUserXP(event.member!!.id, 25)
            MessageType.DEFAULT -> ArmyDiscordBot.database.userManagement.addUserXP(event.member!!.id, 10)
            else -> null
        }
    }
}
