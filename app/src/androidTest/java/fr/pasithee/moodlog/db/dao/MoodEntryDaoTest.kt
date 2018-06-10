package fr.pasithee.moodlog.db.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import fr.pasithee.moodlog.db.MoodLogDb
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.MoodEntryData
import fr.pasithee.moodlog.db.entities.OccupationData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class MoodEntryDaoTest {
    private var dao: MoodEntryDao? = null
    private var db: MoodLogDb? = null

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, MoodLogDb::class.java).build()
        dao = db!!.MoodEntryDao()
    }

    @Test
    fun shouldInsertMood() {
        //given
        val d = Date()
        val mood = MoodEntryData(0, d, 4, emptyList(), emptyList())
        //when
        val id = dao?.insertMood(mood)!!
        //then
        val moodTest = dao?.getMoodEntryData(id)!!
        assertEquals(d, moodTest.date)
        assertEquals(4, moodTest.level)
    }

    @Test
    fun shouldInsertMoodAll() {
        //given
        val d = Date()
        val details = arrayListOf<DetailData>(DetailData("det1"), DetailData("det2"))
        val occupations = arrayListOf<OccupationData>(OccupationData("occ1"), OccupationData("occ2"))
        val mood = MoodEntryData(0, d, 5, details, occupations)
        //when
        dao?.insertMoodAllData(mood)
        //then
        val moodTest = dao?.getMoodAllData(mood.id)!!
        assertEquals(d, moodTest.date)
        assertEquals(5, moodTest.level)
        assertEquals(details, moodTest.details)
        assertEquals(occupations, mood.occupations)
    }

}