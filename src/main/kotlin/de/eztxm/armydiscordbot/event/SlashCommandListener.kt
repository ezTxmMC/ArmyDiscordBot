package de.eztxm.armydiscordbot.event

import de.eztxm.armydiscordbot.command.UpdateEmbedsCommand
import de.eztxm.armydiscordbot.command.UserCommand
import de.eztxm.armydiscordbot.command.UsersCommand
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class SlashCommandListener : ListenerAdapter() {

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        when (event.name.lowercase()) {
            "keks" -> event.reply("Hier ist ein Keks für dich :cookie: <:yummy:1042816867699523654>").setEphemeral(false).queue()
            "updateembeds" -> UpdateEmbedsCommand.execute(event)
            "user" -> UserCommand.execute(event)
            "users" -> UsersCommand.execute(event)
            else -> event.reply("Invalider Befehl.")
        }
    }
}
