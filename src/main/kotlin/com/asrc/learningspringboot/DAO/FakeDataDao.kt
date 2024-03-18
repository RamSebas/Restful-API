package com.asrc.learningspringboot.DAO
import com.asrc.learningspringboot.Model.User
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


@Repository
class FakeDataDao: UserDao{

    private var database: MutableMap<UUID, User> = HashMap()

    init {
        val joeUserUid = UUID.randomUUID()
        database[joeUserUid] = User(joeUserUid, "Joe", "Jones", User.Gender.MALE, 22, "joejones@gmail.com")
    }

    override fun selectAllUsers(): ArrayList<MutableCollection<User>> {
        return arrayListOf(database.values)
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