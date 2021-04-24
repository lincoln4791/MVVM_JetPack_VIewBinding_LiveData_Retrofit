package com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.R;
import com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.common.EndPoints;
import com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.model.DogsDataApiService;
import com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.model.Model_MainActivity;
import com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.view.MainActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class VM_MainActivity extends AndroidViewModel {
    public List<Model_MainActivity> list = new ArrayList<>();
    public MutableLiveData<List<Model_MainActivity>> mutableList = new MutableLiveData<List<Model_MainActivity>>();
    public DisplayDataApiCallback displayDataApiCallback;

    public void setDisplayDataApiCallback(DisplayDataApiCallback displayDataApiCallback) {
        this.displayDataApiCallback = displayDataApiCallback;
    }

    public VM_MainActivity(@NonNull Application application) {
        super(application);
    }







    public void fetchData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DogsDataApiService dogApi = retrofit.create(DogsDataApiService.class);
        Call call = dogApi.getData();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.code()!=200){
                    Toast.makeText(getApplication().getApplicationContext(), getApplication().getApplicationContext()
                            .getString(R.string.internetProblem), Toast.LENGTH_SHORT).show();

                }

                else{
                    List<Model_MainActivity> listt = (List<Model_MainActivity>) response.body();
                        for(Model_MainActivity tempList : listt){
                            tempList.setCounter("0");
                        }
                        list.addAll (listt) ;
                    Toast.makeText(getApplication().getApplicationContext(), "pre gg", Toast.LENGTH_SHORT).show();
                        displayDataApiCallback.displayData(list);


                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), getApplication().getApplicationContext()
                        .getString(R.string.internetProblem)+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mutableList.setValue(list);
    }













    public interface DisplayDataApiCallback {
        void displayData(List<Model_MainActivity> listReturned);
    }


}
