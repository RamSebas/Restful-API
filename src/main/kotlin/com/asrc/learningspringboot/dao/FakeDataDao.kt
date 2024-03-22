package com.asrc.learningspringboot.dao
import com.asrc.learningspringboot.model.User
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class FakeDataDao: UserDao{

    private var database: MutableMap<UUID, User> = HashMap()

    init {
        val joeUserUid = UUID.randomUUID()
        database[joeUserUid] = User(joeUserUid, "Joe", "Jones", User.Gender.MALE, 22, "joejones@gmail.com")
    }

    override fun selectAllUsers(): MutableCollection<User> {
        return database.values
    }

    override fun selectUserByUserUid(userUid: UUID): User? {
        return database[userUid]
    }

    override fun updateUser(user: User): Int {
        database[user.getUserUid()] = user
        return 1
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