package com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.R;
import com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.databinding.ActivityMainBinding;
import com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.model.Model_MainActivity;
import com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.viewmodel.VM_MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements VM_MainActivity.DisplayDataApiCallback {
    private VM_MainActivity vm_mainActivity;
    private AdapterMainActivity adapterMainActivity;


    @BindView(R.id.recycleView)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(activityMainBinding.getRoot());
        activityMainBinding.setWelcomeText(getString(R.string.welcome));
        ButterKnife.bind(this);

        //all variables
        vm_mainActivity = ViewModelProviders.of(this).get(VM_MainActivity.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterMainActivity = new AdapterMainActivity(vm_mainActivity.list,MainActivity.this);


        //all methods
        vm_mainActivity.fetchData();
        vm_mainActivity.setDisplayDataApiCallback(this);


        //setters
        recyclerView.setAdapter(adapterMainActivity);
        adapterMainActivity.notifyDataSetChanged();

        observeData();

    }

    private void observeData() {
        vm_mainActivity.mutableList.observe(this, new Observer<List<Model_MainActivity>>() {
            @Override
            public void onChanged(List<Model_MainActivity> model_mainActivities) {
                vm_mainActivity.list.addAll(model_mainActivities);
            }
        });

    }


    public void editCounter(int position,String newValue){

        vm_mainActivity.list.get(position).setCounter(newValue);
        vm_mainActivity.mutableList.setValue(vm_mainActivity.list);
    }


    @Override
    public void displayData(List<Model_MainActivity> listReturned) {
        adapterMainActivity.notifyDataSetChanged();
    }
}