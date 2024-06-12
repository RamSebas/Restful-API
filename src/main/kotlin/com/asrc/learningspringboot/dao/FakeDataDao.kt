package com.asrc.learningspringboot.dao
import com.asrc.learningspringboot.model.Gender
import com.asrc.learningspringboot.model.User
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class FakeDataDao: UserDao{

    private var database: MutableMap<UUID, User> = HashMap()

    init {
        val joeUserUid = UUID.randomUUID()
        database[joeUserUid] = User(joeUserUid, "Joe", "Jones", Gender.MALE, 22, "joejones@gmail.com")
    }

    override fun selectAllUsers(): MutableCollection<User> {
        return database.values
    }

    override fun selectUserByUserUid(userUid: UUID): User? {
        return database[userUid]
    }

    override fun updateUser(user: User): Int {
        val userUid = user.userUid
        if (userUid != null) {
            database[userUid] = user
            return 1
        }
        return 0 // Indicar que no se pudo actualizar debido a la falta de userUid
    }

    override fun deleteUserByUserUid(userUid: UUID): Int {
        database.remove(userUid)
        return 1
    }

    override fun insertUser(userUid: UUID, user: User): Int {
        database[userUid] = user
        return 1
    }

}