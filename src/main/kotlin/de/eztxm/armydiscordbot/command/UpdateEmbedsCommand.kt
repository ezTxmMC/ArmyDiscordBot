package de.eztxm.armydiscordbot.command

import de.eztxm.armydiscordbot.util.UpdateManager
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class UpdateEmbedsCommand {
    companion object {

        fun execute(event: SlashCommandInteractionEvent) {
            UpdateManager.updateEmbeds(event.jda)
        }
    }
}