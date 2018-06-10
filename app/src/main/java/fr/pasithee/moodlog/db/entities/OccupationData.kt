package fr.pasithee.moodlog.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "occupation",
        foreignKeys = [(ForeignKey(entity = MoodEntryData::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("id"),
                onDelete = ForeignKey.CASCADE))],
        primaryKeys = ["id", "occupation"])
data class OccupationData(
        var id: Long = 0,
        var occupation: String = ""
) {
    constructor(occupation: String) : this(0, occupation)
}
