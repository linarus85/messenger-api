package com.example.messengerapi.repositories

import com.example.messengerapi.models.Conversions
import org.springframework.data.repository.CrudRepository

interface ConversationRepository : CrudRepository<Conversions, Long> {
    fun findBySenderId(id: Long): List<Conversions>
    fun findByRecepientId(id: Long): List<Conversions>
    fun findBySenderIdAndRecepientId(senderId: Long, resepientId: Long): Conversions?
}