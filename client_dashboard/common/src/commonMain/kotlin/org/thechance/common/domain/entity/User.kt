package org.thechance.common.domain.entity


data class User(
    val id: String,
    val fullName: String,
    val username:String,
    val email:String,
    val country:String,
    val permission:List<Permission>,
    val imageUrl: String,
){
    enum class Permission {
        RESTAURANT,
        DRIVER,
        END_USER,
        SUPPORT,
        DELIVERY,
        ADMIN,
    }
}
