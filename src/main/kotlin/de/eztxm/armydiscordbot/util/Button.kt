package de.eztxm.armydiscordbot.util

import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.interactions.components.buttons.Button

class Button {
    companion object {

        fun apply(): Button {
            return Button.primary("apply", "Bewerbe dich jetzt!").withEmoji(Emoji.fromUnicode("📑"))
        }
    }
}