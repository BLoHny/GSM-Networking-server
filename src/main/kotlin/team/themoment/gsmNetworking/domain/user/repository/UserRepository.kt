package team.themoment.gsmNetworking.domain.user.repository

import team.themoment.gsmNetworking.domain.user.domain.User
import org.springframework.data.repository.CrudRepository
import java.util.*

/**
 * User Entity를 위한 Repository 인터페이스 입니다.
 */
interface UserRepository : CrudRepository<User, Long> {

    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun existsByEmail(email: String): Boolean
    fun findByAuthenticationId(authenticationId: Long): User?
    fun existsByAuthenticationId(authenticationId: Long): Boolean
    fun findByEmail(email: String): User?
}
