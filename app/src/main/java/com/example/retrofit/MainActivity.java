package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView topText;
    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final static String HIMA_URL = "http://localhost:45600/authentication-ws/";
    // added same comment line 21
    //comment branch gaurav

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topText = (TextView) findViewById(R.id.topTextView);
        topText.setText("");

        // Step 1 : create Retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        // Step 2 : convert json data to model class object  ||  json data will be converted to MpAPi.class type
        MpApi mpApi = retrofit.create(MpApi.class);


        // Step 3: create a call of model and enqueue for processing
        Call<List<Model>> call = mpApi.getData();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                Log.e("here",BASE_URL);
                // Step 4: Receive response data in simple model type list
                List<Model> data = response.body();
                if(data == null)
                    return;

                for(Model m : data){
                    topText.append("user ID: " + m.getId() + " \n Title: " + m.getTitle());


                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }
}