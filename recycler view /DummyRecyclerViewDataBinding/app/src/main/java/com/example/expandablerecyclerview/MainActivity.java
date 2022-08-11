package com.example.expandablerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.expandablerecyclerview.model.Post;
import com.example.expandablerecyclerview.model.SkuReports;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "UserListActivity";

    private JsonPlaceHolder jsonPlaceHolder;
    private static volatile OkHttpClient.Builder httpClient = null;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        RecyclerView userRecyclerView = findViewById(R.id.recyclerview_user_list);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserListAdapter adapter = new UserListAdapter();
        userRecyclerView.setAdapter(adapter);

        OkHttpClient client = getOkHttpClient(this).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://qa.ninjacart.in:9220")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);



        Call<Post> call = jsonPlaceHolder.createPost(new Dummy());
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {

                    result += response.code() +" "+ response.message();

                    Log.d("result", "onResponse: 1 " + result);

                    return;
                }

                Post post = response.body();


                String content = "" + "\n";
                content += "ID: " + post.getId() + "\n";
                content += "Status: " + post.getStatus() + "\n";
                content += "Facility: " + post.getFacilityId() + "\n";
                content += "Facility: " + post.getReport().getFacilityDepartmentId()+ "\n";
                content += "getSkuSubCategoryId: " + post.getReport().getSkuReports().get(0).getSkuSubCategoryId()+ "\n";
                content += "skuId: " + post.getReport().getSkuReports().get(0).getSkuId()+ "\n";
                content += "getExpectedQuantity: " + post.getReport().getSkuReports().get(0).getExpectedQuantity()+ "\n";
                List<SkuReports> skuReports = post.getReport().getSkuReports();
                result += content;



                HashMap<Integer,List<SkuReports>> hashMap = new HashMap<>();
               for( SkuReports data :skuReports){
                  if(hashMap.containsKey(data.getSkuSubCategoryId())){
                      hashMap.get(data.getSkuSubCategoryId()).add(data);
                  }else{
                      List<SkuReports> list = new ArrayList<>();
                      list.add(data);
                      hashMap.put(data.getSkuSubCategoryId(),list);
                  }
                }
                List<SkuReports> skuRecyclerData = new ArrayList<>();

                Log.d("adap", "onResponse: 2 " +" "+skuRecyclerData.size());
                for (Map.Entry<Integer,List<SkuReports>> entry : hashMap.entrySet()){
                    SkuReports head = new SkuReports();
                    head.setHeading(true);
                    head.setChildCount(entry.getValue().size());
                    skuRecyclerData.add(head);
                    skuRecyclerData.addAll(entry.getValue());

                }


                adapter.setSkuReportsData( skuRecyclerData);


//                for( SkuReports d :skuReports){
//                    Log.d("result", "onResponse:  " +" "+ d.getSkuId());
//                }
//                Log.d("result", "onResponse: 2 " + result+" "+skuReports.size());

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                result += t.getMessage();
                Log.d("result", "onResponse: 3 " + result);

            }


        });






    }



    private void createPosts() {




        //  Post post = new Post(45,"NEW TITLE","NEW TEXT");
//        Post post = new Post(23,"New Title","New Text");

//        Call<Post> call = jsonPlaceHolder.createPost(post);

        Call<Post> call = jsonPlaceHolder.createPost(new Dummy());
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {

                    result += response.code() +" "+ response.message();

                    Log.d("result", "onResponse: 1 " + result);

                    return;
                }

                    Post post = response.body();


                    String content = "" + "\n";
                    content += "ID: " + post.getId() + "\n";
                    content += "Status: " + post.getStatus() + "\n";
                    content += "Facility: " + post.getFacilityId() + "\n";
                    content += "Facility: " + post.getReport().getFacilityDepartmentId()+ "\n";
                    content += "Facility: " + post.getReport().getSkuReports().get(0).getWeight()+ "\n";

                    result += content;



                Log.d("result", "onResponse: 2 " + result);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                result += t.getMessage();
                Log.d("result", "onResponse: 3 " + result);

            }


        });
    }



    private static OkHttpClient.Builder getOkHttpClient(final Context context) {

            if (httpClient == null) {

                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                httpClient = new OkHttpClient.Builder();
                httpClient.retryOnConnectionFailure(true);
                httpClient.addInterceptor(interceptor);
                httpClient.addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = getHeaderParams(original, context);

                    okhttp3.Response response = chain.proceed(request);
                    return response;
                });
                httpClient.connectTimeout(5, TimeUnit.MINUTES)
                        .readTimeout(5, TimeUnit.MINUTES)
                        .writeTimeout(5, TimeUnit.MINUTES).build();
            }

        return httpClient;
    }

    public static Request getHeaderParams(Request original, Context context) {
        return original.newBuilder()
                .header("Authorization", "Basic YXBwb3QzOjEyMzQ1Ng==")
                .header("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build();
    }

//
//    private void getPost() {
//
//
//        // Call<List<Post>> call = jsonPlaceHolder.getPosts(); // everything will be fetched check the interface
////        Call<List<Post>> call = jsonPlaceHolder.getPosts(4); // get only specific id
//
//
//        // Call<List<Post>> call = jsonPlaceHolder.getPosts(4,"null","null"); // even this works
//        //Call<List<Post>> call = jsonPlaceHolder.getPosts(4,"id","des");
////        Call<List<Post>> call = jsonPlaceHolder.getPosts(new Integer[]{1,5,6},"id","des");// fixed parameters
//
////        Map<String,String> parameters = new HashMap<>();
////        parameters.put("userId","1");
////        parameters.put("_sort","id");
////        parameters.put("_order","desc");
////
////        Call<List<Post>> call = jsonPlaceHolder.getPosts(parameters);
//
//        Call<List<Post>> call = jsonPlaceHolder.getPosts(); // everything will be fetched check the interface
//
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//
//                if (!response.isSuccessful()) {
//
//                    result += response.code();
//
//                    Log.d("result", "onResponse: " + result);
//
//                    return;
//                }
//
//                List<Post> posts = response.body();
//
//                for (Post post : posts) {
//                    String content = "";
//                    content += "ID: " + post.getId() + "\n";
//                    content += "Status: " + post.getStatus() + "\n";
//                    content += "Facility: " + post.getFacilityId() + "\n";
//
//                    result += content;
//
//                }
//
//                Log.d("result", "onResponse: " + result);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//
//                result += t.getMessage();
//                Log.d("result", "onResponse: " + result);
//
//
//            }
//        });
//
//
//    }

}