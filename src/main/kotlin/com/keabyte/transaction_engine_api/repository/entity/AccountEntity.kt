package com.keabyte.transaction_engine_api.repository.entity

import com.keabyte.transaction_engine_api.web.model.account.Account
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SourceType
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "account")
data class AccountEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val accountNumber: String = UUID.randomUUID().toString(),
    @CreationTimestamp(source = SourceType.DB)
    val dateCreated: OffsetDateTime? = null,
    @ManyToOne
    @JoinColumn(name = "client_id")
    val client: ClientEntity
) {

    fun toModel(): Account = Account(
        clientNumber = client.clientNumber,
        dateCreated = dateCreated!!
    )
}