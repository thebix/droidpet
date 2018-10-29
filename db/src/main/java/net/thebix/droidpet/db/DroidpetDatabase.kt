package net.thebix.droidpet.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Database
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Insert
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Query
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Update
import android.content.Context
import io.reactivex.Flowable


@Database(
    version = 1,
    entities = [GithubRepoEntity::class]
)
// INFO: @TypeConverters({Converters.class})
abstract class DroidpetDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: DroidpetDatabase? = null
        fun getInstance(context: Context): DroidpetDatabase? {
            if (INSTANCE == null) {
                synchronized(DroidpetDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, DroidpetDatabase::class.java, "droidpet-db")
                        // INFO: .addMigrations()
                        .build()
                }
            }
            return INSTANCE
        }

        fun removeInstance() {
            INSTANCE = null
        }
    }

    abstract fun getGithubDao(): GithubDao

}

// TODO: move to separate file
@Entity
// INFO: @Entity (primaryKeys = {"complex", "key"})
// INFO: @Entity(tableName = "if_custom_name_needed")
// INFO: @Entity(indices = {@Index("id"),
//          @Index(value = {"user_id", "title"})},)
// INFO: @Entity(indices = {@Index(value = {"user_id", "title"},
//          unique = true)})
// INFO: @Entity(foreignKeys = @ForeignKey(entity = User.class,
//          parentColumns = "id",
//          childColumns = "user_id"))
class GithubRepoEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,

    // INFO: @ColumnInfo(name = "different_title")
        var title: String = ""

    //INFO: @Ignore
    //      val someValue : Any

    // INFO: @Embedded
    //       public Address address;

)

@Dao
interface GithubDao {

    @Insert
    // INFO: @Insert(onConflict = OnConflictStrategy.REPLACE), default = ABORT
    fun insertRepo(repo: GithubRepoEntity): Long // rowId, List for collections

    // INFO: many objects
    //       @Insert
    //       fun insertReposAndUser(repos: List<GithubRepooEntity>, user: User) : Unit

    @Update
    fun updateRepo(vararg repo: GithubRepoEntity) // : Int - number of updated rows

    @Delete
    fun deleteUsers(vararg repo: GithubRepoEntity)

    @Query("SELECT * FROM GithubRepoEntity")
    fun getRepos(): Flowable<List<GithubRepoEntity>>

    @Query("DELETE FROM GithubRepoEntity")
    fun deleteAllRepos()

    // INFO: passing parameter
    //      @Query("SELECT * FROM user WHERE age > :minAge")
    //      fun loadAllUsersOlderThan(minAge: Int): Array<User>
    // INFO: passing array as parameter
    //      @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
    //      fun loadUsersFromRegions(regions: List<String>): List<NameTuple>
}
