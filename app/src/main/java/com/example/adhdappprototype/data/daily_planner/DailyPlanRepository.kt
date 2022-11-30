package com.example.adhdappprototype.data.daily_planner

import kotlinx.coroutines.flow.Flow

interface DailyPlanRepository {

    suspend fun insertPlan(todo: DailyPlan)

    suspend fun deletePlan(todo: DailyPlan)

    suspend fun getPlanById(id: Int): DailyPlan?

    fun getPlans(): Flow<List<DailyPlan>>
}