/*
 * Copyright (c) 2018 by Denis Verentsov (decsent@Yandex.ru)
 * All rights reserved.
 */

package com.dvinc.circlestimer.ui.trainings;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dvinc.circlestimer.R;
import com.dvinc.circlestimer.data.db.entities.Training;

import java.util.ArrayList;
import java.util.List;

public class TrainingsAdapter extends RecyclerView.Adapter<TrainingsAdapter.MyViewHolder> {

    private List<Training> trainingsList = new ArrayList<>();
    @Nullable
    private TrainingClickListener clickListener;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView trainingNameTextView;
        private TextView trainingCurrentText;

        MyViewHolder(View view) {
            super(view);
            trainingNameTextView = view.findViewById(R.id.tv_item_training_name);
            trainingCurrentText = view.findViewById(R.id.tv_item_training_current);
        }
    }

    void setList(List<Training> list) {
        this.trainingsList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrainingsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_training, parent, false);
        return new TrainingsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingsAdapter.MyViewHolder holder, final int position) {
        final Training training = trainingsList.get(position);
        holder.trainingNameTextView.setText(training.getTrainingName());
        holder.trainingCurrentText.setVisibility(training.isCurrentTraining() ? View.VISIBLE : View.INVISIBLE);
        holder.itemView.setOnClickListener(view -> {
            if (clickListener != null) {
                clickListener.onItemClick(training);
            }
        });
        holder.itemView.setOnLongClickListener(v -> {
            if (clickListener != null) {
                clickListener.onLongItemClick(training);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return trainingsList.size();
    }

    public void setOnClickTrainingListener(@NonNull TrainingClickListener listener) {
        clickListener = listener;
    }

    @Nullable
    Training getItem(int itemPosition) {
        if (trainingsList.size() > 0) {
            return trainingsList.get(itemPosition);
        } else {
            return null;
        }
    }

    interface TrainingClickListener {
        void onItemClick(@NonNull Training item);
        void onLongItemClick(@NonNull Training item);
    }
}
