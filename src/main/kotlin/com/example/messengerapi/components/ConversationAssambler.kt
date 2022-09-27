package com.example.messengerapi.components

import com.example.messengerapi.helpers.ConversationListVO
import com.example.messengerapi.helpers.ConversationVO
import com.example.messengerapi.helpers.MessageVO
import com.example.messengerapi.models.Conversions
import com.example.messengerapi.services.ConversationServiceImpl
import org.springframework.stereotype.Component

@Component
class ConversationAssambler(
    val conversationService: ConversationServiceImpl,
    val messageAssambler: MessageAssambler
) {
    fun toConversationVO(conversions: Conversions, userId: Long): ConversationVO {
        val conversationMessage: ArrayList<MessageVO> = ArrayList()
        conversions.messages?.mapTo(conversationMessage) {
            messageAssambler.toMessageVo(it)
        }
        return ConversationVO(
            conversions.id,
            conversationService.nameSecondaryParty(conversions, userId),
            conversationMessage
        )
    }
    fun toConversationListVO(conversions: ArrayList<Conversions>,
    userId: Long):ConversationListVO{
        val conversationListVO = conversions.map {
            toConversationVO(it,userId)
        }
        return  ConversationListVO(conversationListVO)
    }
}
