package de.eztxm.armydiscordbot.event

import de.eztxm.armydiscordbot.util.Embed
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class LeaveListener : ListenerAdapter() {

    override fun onGuildMemberRemove(event: GuildMemberRemoveEvent) {
        event.guild.systemChannel!!.sendMessageEmbeds(Embed.leaveServer(event.user)).queue()
    }
}
