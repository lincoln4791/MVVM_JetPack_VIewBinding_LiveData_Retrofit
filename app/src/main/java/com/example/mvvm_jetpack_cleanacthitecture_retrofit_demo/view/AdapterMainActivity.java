package com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.databinding.SampleMainactivityBinding;
import com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.model.Model_MainActivity;

import java.util.List;

public class AdapterMainActivity extends RecyclerView.Adapter<AdapterMainActivity.MyViewHolder> {
    private List<Model_MainActivity> newList;
    private Context context;

    public AdapterMainActivity(List<Model_MainActivity> newList, Context context) {
        this.newList = newList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SampleMainactivityBinding sampleMainactivityBinding = SampleMainactivityBinding.inflate(inflater,parent,false);
        return new MyViewHolder(sampleMainactivityBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model_MainActivity object = newList.get(position);
            holder.sampleMainactivityBinding.setDogsModel(object);



            holder.sampleMainactivityBinding.ivProfilePictureSampleMainActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int newCounter = Integer.parseInt(newList.get(position).getCounter());
                    newCounter = newCounter+1;
                    String newCounterInString = String.valueOf(newCounter);
                    holder.sampleMainactivityBinding.tvCounterSampleMainActivity.setText(newCounterInString);
                    ((MainActivity)context).editCounter(position,newCounterInString);

                }
            });

    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SampleMainactivityBinding sampleMainactivityBinding;
        public MyViewHolder(@NonNull SampleMainactivityBinding itemView) {
            super(itemView.getRoot());
            sampleMainactivityBinding = itemView;

        }
    }
}
