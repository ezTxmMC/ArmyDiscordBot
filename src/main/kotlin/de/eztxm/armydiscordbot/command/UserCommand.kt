package de.eztxm.armydiscordbot.command

import de.eztxm.armydiscordbot.ArmyDiscordBot
import de.eztxm.armydiscordbot.database.model.UserManagement
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class UserCommand {
    companion object {

        fun execute(event: SlashCommandInteractionEvent) {
            val management: UserManagement = ArmyDiscordBot.database.userManagement
            when (event.subcommandName) {
                "add" -> {
                    val user = event.getOption("user")?.asUser
                    if (management.userExist(user?.id)) {
                        event.reply("Benutzer ${user?.asMention} existiert bereits.").setEphemeral(true).queue()
                        return
                    }
                    management.addUser(user?.id)
                    event.reply("Benutzer ${user?.asMention} wurde hinzugefügt.").setEphemeral(true).queue()
                }
                "remove" -> {
                    val user = event.getOption("user")?.asUser
                    if (!management.userExist(user?.id)) {
                        event.reply("Benutzer ${user?.asMention} existiert nicht.").setEphemeral(true).queue()
                        return
                    }
                    management.removeUser(user?.id)
                    event.reply("Benutzer ${user?.asMention} wurde entfernt.").setEphemeral(true).queue()
                }
                else -> event.reply("Unbekannter Unterbefehl des User Befehls.").setEphemeral(true).queue()
            }
        }
    }
}