/*
 * Copyright (c) 2018 by Denis Verentsov (decsent@yandex.ru)
 * All rights reserved.
 */

package com.dvinc.workouttimer.domain.repository.exercise

import com.dvinc.workouttimer.domain.model.exercise.Exercise
import io.reactivex.Flowable
import io.reactivex.Single

interface ExerciseRepository {

    fun obtainExercises(): Flowable<List<Exercise>>

    fun addDefaultExercises(workoutId: Int): Single<Pair<Int, Long>>
}
