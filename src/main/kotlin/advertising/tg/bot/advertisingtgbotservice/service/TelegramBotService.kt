package advertising.tg.bot.advertisingtgbotservice.service

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TelegramBotService(
    @Value("\${telegram.bot.token}")
    private val botToken: String
) {

    private val bot = Bot.Builder().build {
        token = botToken

        dispatch {
            command("start") {
                handleStart(message.chat.id)
            }
            command("help") {
                handleHelp(message.chat.id)
            }
        }
    }

    fun startBot() {
        bot.startPolling()
    }

    @PostConstruct
    fun init() {
        startBot()
    }

    private fun handleStart(chatId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            bot.sendMessage(
                chatId = ChatId.fromId(chatId),
                text = "Привет! Я рекламный бот!"
            )
        }
    }

    private fun handleHelp(chatId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            bot.sendMessage(
                chatId = ChatId.fromId(chatId),
                text = "/start - начать\n/help - помощь"
            )
        }
    }
}
