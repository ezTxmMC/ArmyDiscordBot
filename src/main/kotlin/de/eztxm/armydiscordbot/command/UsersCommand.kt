package de.eztxm.armydiscordbot.command

import de.eztxm.armydiscordbot.ArmyDiscordBot
import de.eztxm.armydiscordbot.database.model.UserManagement
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class UsersCommand {
    companion object {

        fun execute(event: SlashCommandInteractionEvent) {
            val management: UserManagement = ArmyDiscordBot.database.userManagement
            when (event.subcommandName) {
                "add" -> {
                    for (member in event.guild!!.members) {
                        val id = member.id
                        if (management.userExist(id)) {
                            continue
                        }
                        management.addUser(id)
                    }
                    event.reply("Alle Benutzer wurden hinzugefügt.").setEphemeral(true).queue()
                }
                "remove" -> {
                    for (member in event.guild!!.members) {
                        val id = member.id
                        if (!management.userExist(id)) {
                            continue
                        }
                        management.removeUser(id)
                    }
                    event.reply("Alle Benutzer wurden entfernt.").setEphemeral(true).queue()
                }
                else -> event.reply("Unbekannter Unterbefehl des User Befehls.").setEphemeral(true).queue()
            }
        }
    }
}
