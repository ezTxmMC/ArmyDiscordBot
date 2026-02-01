package de.eztxm.armydiscordbot.util

import net.dv8tion.jda.api.components.label.Label
import net.dv8tion.jda.api.components.textinput.TextInput
import net.dv8tion.jda.api.components.textinput.TextInputStyle
import net.dv8tion.jda.api.modals.Modal

class Modals {
    companion object {

        fun apply(): Modal {
            val nameAge = TextInput.create("nameAge", TextInputStyle.SHORT)
                .setPlaceholder("Maximus")
                .setMaxLength(20)
                .setRequired(true)
                .build()
            val position = TextInput.create("position", TextInputStyle.SHORT)
                .setPlaceholder("Cutter")
                .setMinLength(5)
                .setMaxLength(30)
                .setRequired(true)
                .build()
            val hobbies = TextInput.create("hobbies", TextInputStyle.PARAGRAPH)
                .setMinLength(40)
                .setRequired(true)
                .build()
            val strongWeek = TextInput.create("strongWeek", TextInputStyle.PARAGRAPH)
                .setMinLength(40)
                .setRequired(true)
                .build()
            val experiences = TextInput.create("experience", TextInputStyle.PARAGRAPH)
                .setMinLength(50)
                .setRequired(true)
                .build()
            return Modal.create("apply", "Bewerbungsformular")
                .addComponents(
                    Label.of("Name und Alter", nameAge),
                    Label.of("Stelle", position),
                    Label.of("Hobbys", hobbies),
                    Label.of("Stärken und Schwächen", strongWeek),
                    Label.of("Erfahrung", experiences))
                .build()
        }
    }
}
