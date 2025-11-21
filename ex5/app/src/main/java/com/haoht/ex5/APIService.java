package com.haoht.ex5;
import java.util.List;
import retrofit2.Call;
import  retrofit2.http.GET;
import com.haoht.ex5.Model.Category;

public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getCategoryAll();
}
