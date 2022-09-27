package com.example.messengerapi.services

import com.example.messengerapi.exeptions.ConversionsInvalinExeptions
import com.example.messengerapi.models.Conversions
import com.example.messengerapi.models.User
import com.example.messengerapi.repositories.ConversationRepository
import org.springframework.stereotype.Service

@Service
class ConversationServiceImpl(val repository: ConversationRepository) : ConversationService {
    override fun createConversation(userA: User, userB: User): Conversions {
        val conversation = Conversions(userA, userB)
        repository.save(conversation)
        return conversation
    }

    override fun coversationExists(userA: User, userB: User): Boolean {
        return if (repository.findBySenderIdAndRecepientId(userA.id, userB.id) != null) {
            true
        } else repository.findBySenderIdAndRecepientId(userA.id, userB.id) != null
    }

    override fun getConversation(userA: User, userB: User): Conversions? {
        return when {
            repository.findBySenderIdAndRecepientId(userA.id, userB.id) != null ->
                repository.findBySenderIdAndRecepientId(userA.id, userB.id)

            repository.findBySenderIdAndRecepientId(userB.id, userA.id) != null ->
                repository.findBySenderIdAndRecepientId(userB.id, userA.id)

            else -> null
        }
    }

    override fun retriveThread(conversationId: Long): Conversions {
        val conversation = repository.findById(conversationId)
        if (conversation.isPresent) {
            return conversation.get()
        }
        throw ConversionsInvalinExeptions("Invalid '$conversationId'")
    }

    override fun listUserConversarion(userId: Long): ArrayList<Conversions> {
        val conversationList: ArrayList<Conversions> = ArrayList()
        conversationList.addAll(repository.findBySenderId(userId))
        conversationList.addAll(repository.findByRecepientId(userId))
        return conversationList
    }

    override fun nameSecondaryParty(conversation: Conversions, userId: Long): String {
        return if (conversation.sender?.id == userId) {
            conversation.recipient?.username as String
        } else {
            conversation.sender?.username as String
        }
    }

}