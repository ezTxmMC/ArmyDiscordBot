package de.eztxm.armydiscordbot.listener

import de.eztxm.armydiscordbot.util.Embed
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class JoinListener : ListenerAdapter() {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        event.guild.systemChannel!!.sendMessageEmbeds(Embed.joinServer(event.user)).queue()
    }
}
