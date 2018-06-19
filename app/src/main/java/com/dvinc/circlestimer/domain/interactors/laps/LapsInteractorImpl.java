/*
 * Copyright (c) 2018 by Denis Verentsov
 * Date: 4/17/2018
 * Email: decsent@yandex.ru
 * All rights reserved.
 */

package com.dvinc.circlestimer.domain.interactors.laps;

import android.support.annotation.NonNull;

import com.dvinc.circlestimer.data.db.entities.Lap;
import com.dvinc.circlestimer.data.db.entities.Training;
import com.dvinc.circlestimer.data.repositories.laps.LapsRepository;
import com.dvinc.circlestimer.data.repositories.training.TrainingsRepository;
import com.dvinc.circlestimer.di.qualifiers.IoScheduler;
import com.dvinc.circlestimer.di.qualifiers.UiScheduler;
import com.dvinc.circlestimer.domain.entities.LapItem;
import com.dvinc.circlestimer.domain.mappers.LapsMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;

public class LapsInteractorImpl implements LapsInteractor {

    @NonNull
    private TrainingsRepository trainingsRepository;

    @NonNull
    private LapsRepository lapsRepository;

    @NonNull
    private final Scheduler schedulerIo;

    @NonNull
    private final Scheduler schedulerUi;

    @NonNull
    private final LapsMapper lapsMapper;

    @Inject
    public LapsInteractorImpl(@NonNull TrainingsRepository trainingsRepository,
                              @NonNull LapsRepository lapsRepository,
                              @NonNull @IoScheduler Scheduler schedulerIo,
                              @NonNull @UiScheduler Scheduler schedulerUi,
                              @NonNull LapsMapper lapsMapper) {
        this.trainingsRepository = trainingsRepository;
        this.lapsRepository = lapsRepository;
        this.schedulerIo = schedulerIo;
        this.schedulerUi = schedulerUi;
        this.lapsMapper = lapsMapper;
    }

    @Override
    public Flowable<List<LapItem>> getLaps() {
        return Flowable.fromCallable(() -> trainingsRepository.getCurrentTraining())
                .filter(training -> training != null)
                .map(Training::getUid)
                .map(trainingId -> lapsRepository.getLapsByTrainingId(trainingId))
                .map(lapsMapper::map)
                .subscribeOn(schedulerIo)
                .observeOn(schedulerUi);
    }
}