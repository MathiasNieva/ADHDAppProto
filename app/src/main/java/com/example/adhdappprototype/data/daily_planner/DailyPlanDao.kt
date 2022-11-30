package com.example.adhdappprototype.data.daily_planner

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyPlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlan(todo: DailyPlan)

    @Delete
    suspend fun deletePlan(todo: DailyPlan)

    @Query("SELECT * FROM DailyPlan WHERE id = :id")
    suspend fun getPlanById(id: Int): DailyPlan?

    @Query("SELECT * FROM DailyPlan")
    fun getPlans(): Flow<List<DailyPlan>>
}