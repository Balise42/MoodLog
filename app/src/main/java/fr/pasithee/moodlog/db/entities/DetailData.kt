package fr.pasithee.moodlog.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "moodDetail",
        foreignKeys = [(ForeignKey(entity = MoodEntryData::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("id"),
                onDelete = ForeignKey.CASCADE))])
data class DetailData(@PrimaryKey var id: Long?,
                      @PrimaryKey var detail: String
)
