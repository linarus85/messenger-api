package com.example.messengerapi.services

import com.example.messengerapi.models.Conversions
import com.example.messengerapi.models.User

interface ConversationService {
    fun createConversation(userA: User, userB: User): Conversions
    fun coversationExists(userA: User, userB: User): Boolean
    fun getConversation(userA: User, userB: User): Conversions?
    fun retriveThread(conversationId: Long): Conversions
    fun listUserConversarion(userId: Long): List<Conversions>
    fun nameSecondaryParty(conversation: Conversions, userId: Long): String
}
