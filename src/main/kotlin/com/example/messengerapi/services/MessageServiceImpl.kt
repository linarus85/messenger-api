package com.example.messengerapi.services

import com.example.messengerapi.exeptions.MassageEmptyExeptions
import com.example.messengerapi.exeptions.MessageResipentInvalidExeptions
import com.example.messengerapi.models.Conversions
import com.example.messengerapi.models.Message
import com.example.messengerapi.models.User
import com.example.messengerapi.repositories.ConversationRepository
import com.example.messengerapi.repositories.MessageRepository
import com.example.messengerapi.repositories.UserRepository
import kotlin.jvm.Throws

class MessageServiceImpl(
    val repository: MessageRepository,
    val conversationRepository: ConversationRepository,
    val conversationServise: ConversationService,
    val userRepository: UserRepository
) : MessageService {
    @Throws(MassageEmptyExeptions::class, MessageResipentInvalidExeptions::class)
    override fun sendMessage(sender: User, recipentId: Long, messageText: String): Message {
        val optional = userRepository.findById(recipentId)
        if (optional.isPresent) {
            val recepient = optional.get()
            if (messageText.isNotEmpty()) {
                val conversation: Conversions =
                    if (conversationServise.coversationExists(sender, recepient)) {
                        conversationServise.getConversation(sender, recepient) as Conversions
                    } else {
                        conversationServise.createConversation(sender, recepient)
                    }
                conversationRepository.save(conversation)
                val message = Message(sender, recepient, messageText, conversation)
                repository.save(message)
                return message
            }
        }else{
            throw  MessageResipentInvalidExeptions("'$recipentId' is invalid")
        }
        throw MassageEmptyExeptions()
    }
}