package fr.pasithee.moodlog.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "occupation",
        foreignKeys = [(ForeignKey(entity = MoodEntryData::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("id"),
                onDelete = ForeignKey.CASCADE))])
data class OccupationData(@PrimaryKey var id: Long?,
                          @PrimaryKey var occupation: String
)
