package com.example.adhdappprototype.data.daily_planner

import kotlinx.coroutines.flow.Flow

class DailyPlanRepositoryImpl (
    private val daily_plan_dao: DailyPlanDao
): DailyPlanRepository {

    override suspend fun insertPlan(todo: DailyPlan) {
        daily_plan_dao.insertPlan(todo)
    }

    override suspend fun deletePlan(todo: DailyPlan) {
        daily_plan_dao.deletePlan(todo)
    }

    override suspend fun getPlanById(id: Int): DailyPlan? {
        return daily_plan_dao.getPlanById(id)
    }

    override fun getPlans(): Flow<List<DailyPlan>> {
        return daily_plan_dao.getPlans()
    }
}