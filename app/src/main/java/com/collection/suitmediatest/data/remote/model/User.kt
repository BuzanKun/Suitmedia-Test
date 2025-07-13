package com.collection.suitmediatest.data.remote.model

import com.collection.suitmediatest.data.remote.response.DataItem

data class User(
    val id: Int?,
    val fullName: String,
    val email: String?,
    val avatarUrl: String?
)

fun DataItem.toUser(): User {
    return User(
        id = this.id,
        fullName = "${this.firstName} ${this.lastName}",
        email = this.email,
        avatarUrl = this.avatar
    )
}