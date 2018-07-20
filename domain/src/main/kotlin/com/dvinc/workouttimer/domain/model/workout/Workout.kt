/*
 * Copyright (c) 2018 by Denis Verentsov (decsent@yandex.ru)
 * All rights reserved.
 */

package com.dvinc.workouttimer.domain.model.workout

data class Workout(
        val id: Int,
        val name: String,
        val description: String,
        val exerciseCount: Int,
        val exerciseTotalTime: Long,
        val isActive: Boolean
)