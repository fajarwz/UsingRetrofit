package com.example.usingretrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    //@Query digunakan untuk manipulasi url dengan misal ?userId=1
    //hati2 menuliskan url (contoh posts ini), jika ditambahkan / dibelakang misal
    // /posts maka akan menghapus satu slash dibelakang dari misal url seperti:
    // https://jsonplaceholder.typicode.com/v3 menjadi https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId, //pakai Integer daripada int agar nullable
            @Query("_sort") String sort,    //untuk tambahan query, cth: ?userId=1&_sort=id
            @Query("_order") String order
    );

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);

    //URL manipulation, gunakan @Path untuk manipulasi bagian url mana
    //yang mau dimanipulasi. ini hasilnya berupa contoh: ../posts/3/comments
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET
    Call<List<Comment>> getComments(@Url String url);

    //5
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    //6
    //diencode sama seperti url diencode
    //dalam url, formurlencoded berarti memberikan query seperti
    //userId=23&title=New%20Title&body=New%Text
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    //7
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);

    //8
    //put akan mereplace seluruh data yg ada dengan data yg kita kirimkan
    //put harus mengirimkan data full
    //@PUT("posts/{id}")
    //Call<Post> putPost(@Path("id") int id, @Body Post post);

    //9
    //patch hanya akan mengganti data sebagian
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);

    //10
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

    //11
    //passing header
    @Headers({"Static-Header1: 123", "Static-Header2: 456"})
    @PUT("posts/{id}")
    Call<Post> putPost(
            @Header ("Dynamic-Header") String header,
            @Path("id") int id,
            @Body Post post
    );

    //12
    //passing header
    @Headers({"Static-Header1: 123", "Static-Header2: 456"})
    @PATCH("posts/{id}")
    Call<Post> patchPost(
            @HeaderMap Map<String, String> headers,
            @Path("id") int id,
            @Body Post post
    );
}
