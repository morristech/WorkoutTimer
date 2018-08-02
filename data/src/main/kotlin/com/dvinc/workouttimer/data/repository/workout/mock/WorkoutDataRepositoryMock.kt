/*
 * Copyright (c) 2018 by Denis Verentsov (decsent@yandex.ru)
 * All rights reserved.
 */

package com.dvinc.workouttimer.data.repository.workout.mock

import com.dvinc.workouttimer.domain.model.workout.Workout
import com.dvinc.workouttimer.domain.repository.workout.WorkoutRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject

class WorkoutDataRepositoryMock @Inject constructor() : WorkoutRepository {

    override fun obtainWorkouts(): Flowable<List<Workout>> {
        return Flowable.fromArray(generateMocks())
    }

    override fun obtainActiveWorkout(): Maybe<Workout> {
        return Maybe.empty()
    }

    private fun generateMocks(): List<Workout> {
        val list = ArrayList<Workout>()

        (1..7).forEach {
            list.add(Workout(it, "Workout$it", "Hilarious workout with girls, beer, and thousands exercises", 10, 100000, false))
        }

        return list
    }
}
