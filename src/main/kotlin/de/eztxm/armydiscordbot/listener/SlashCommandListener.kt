package de.eztxm.armydiscordbot.listener

import de.eztxm.armydiscordbot.command.UpdateEmbedsCommand
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class SlashCommandListener : ListenerAdapter() {

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        when (event.commandId.lowercase()) {
            "keks" -> event.reply("Hier ist ein Keks für dich :cookie: <:yummy:1042816867699523654>").setEphemeral(true).queue()
            "updateembeds" -> UpdateEmbedsCommand.execute(event)
        }
    }
}
