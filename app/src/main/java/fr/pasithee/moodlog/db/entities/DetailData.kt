package fr.pasithee.moodlog.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey


@Entity(tableName = "moodDetail",
        foreignKeys = [(ForeignKey(entity = MoodEntryData::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("id"),
                onDelete = ForeignKey.CASCADE))],
        primaryKeys = ["id", "detail"])
data class DetailData(
        var id: Long = 0,
        var detail: String = ""
) {
    constructor(detail : String) : this(0, detail)
}
