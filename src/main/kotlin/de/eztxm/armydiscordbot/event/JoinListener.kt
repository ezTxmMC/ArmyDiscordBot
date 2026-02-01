package de.eztxm.armydiscordbot.event

import de.eztxm.armydiscordbot.util.Embeds
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class JoinListener : ListenerAdapter() {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        event.guild.systemChannel!!.sendMessageEmbeds(Embeds.joinServer(event.user)).queue()
    }
}
