package de.eztxm.armydiscordbot.util

import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.text.TextInput
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle
import net.dv8tion.jda.api.interactions.modals.Modal

class Modal {
    companion object {

        fun apply(): Modal {
            val nameAge = TextInput.create("nameAge", "Name und Alter", TextInputStyle.SHORT)
                .setPlaceholder("Maximus")
                .setMaxLength(20)
                .setRequired(true)
                .build()
            val position = TextInput.create("position", "Stelle", TextInputStyle.SHORT)
                .setPlaceholder("Cutter")
                .setMinLength(5)
                .setMaxLength(30)
                .setRequired(true)
                .build()
            val hobbies = TextInput.create("hobbies", "Hobbys", TextInputStyle.PARAGRAPH)
                .setMinLength(40)
                .setRequired(true)
                .build()
            val strongWeek = TextInput.create("strongWeek", "Stärken und Schwächen", TextInputStyle.PARAGRAPH)
                .setMinLength(40)
                .setRequired(true)
                .build()
            val experiences = TextInput.create("experiences", "Erfahrung(en)", TextInputStyle.PARAGRAPH)
                .setMinLength(50)
                .setRequired(true)
                .build()
            return Modal.create("apply", "Bewerbungsformular")
                .addComponents(ActionRow.of(nameAge), ActionRow.of(position), ActionRow.of(hobbies), ActionRow.of(strongWeek), ActionRow.of(experiences))
                .build()
        }
    }
}
