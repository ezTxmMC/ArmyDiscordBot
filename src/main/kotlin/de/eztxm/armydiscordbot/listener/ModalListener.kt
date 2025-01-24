package de.eztxm.armydiscordbot.listener

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class ModalListener : ListenerAdapter() {

    override fun onModalInteraction(event: ModalInteractionEvent) {
        when (event.interaction.modalId) {
            "apply" -> {

            }
        }
    }
}