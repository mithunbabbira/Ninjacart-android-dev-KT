package com.example.expandablerecyclerview;

import com.example.expandablerecyclerview.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolder {

    @GET("posts/{id}/comments")
    Call<List<Post>> getComments(@Path("id")int postId);




    @POST("/facilityDepartmentInventoryAudit/startManualAudit")
    Call<Post> createPost(@Body Dummy dummy);


    @PUT("post/{id}")
    Call<Post> putPost(@Path("id") int id ,@Body Post post);


    @PATCH("post/{id}")
    Call<Post> patchPost(@Path("id") int id ,@Body Post post);


}
