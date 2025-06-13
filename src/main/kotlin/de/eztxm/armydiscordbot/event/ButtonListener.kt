package de.eztxm.armydiscordbot.event

import de.eztxm.armydiscordbot.util.Modal
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class ButtonListener : ListenerAdapter() {

    override fun onButtonInteraction(event: ButtonInteractionEvent) {
        when (event.interaction.button.id) {
            "apply" -> event.replyModal(Modal.apply()).queue()
        }
    }
}
