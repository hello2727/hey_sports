package com.example.android.heysports.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Jihye Noh
 * Date: 2022-03-10
 */
@Entity
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int
)