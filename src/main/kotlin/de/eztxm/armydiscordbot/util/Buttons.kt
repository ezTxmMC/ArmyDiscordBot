package de.eztxm.armydiscordbot.util

import net.dv8tion.jda.api.components.buttons.Button
import net.dv8tion.jda.api.entities.emoji.Emoji

class Buttons {
    companion object {

        fun apply(): Button {
            return Button.primary("apply", "Bewerbe dich jetzt!").withEmoji(Emoji.fromUnicode("📑"))
        }

        fun acceptApplication(): Button {
            return Button.success("acceptApplication", "Annehmen")
        }

        fun declineApplication(): Button {
            return Button.danger("declineApplication", "Ablehnen")
        }
    }
}