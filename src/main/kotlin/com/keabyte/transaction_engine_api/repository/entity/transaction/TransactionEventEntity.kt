package com.keabyte.transaction_engine_api.repository.entity.transaction

import com.keabyte.transaction_engine_api.repository.enum.TransactionType
import com.keabyte.transaction_engine_api.web.model.transaction.TransactionEvent
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SourceType
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "transaction_event")
data class TransactionEventEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val transactionReference: String = UUID.randomUUID().toString(),
    @CreationTimestamp(source = SourceType.DB)
    val dateCreated: OffsetDateTime? = null,
    @Enumerated(EnumType.STRING)
    val transactionType: TransactionType,
    @OneToMany(mappedBy = "transactionEvent")
    var accountTransactions: MutableList<AccountTransactionEntity> = ArrayList()
) {

    fun toModel() = TransactionEvent(
        transactionReference = transactionReference,
        dateCreated = dateCreated!!,
        transactionType = transactionType,
        accountTransactions = accountTransactions.map { it.toModel() }
    )
}