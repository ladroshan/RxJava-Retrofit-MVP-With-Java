package mvp.rxjavas.dnyaneshwar.rxjavawithmvp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.beans.ResponseModel;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dnyaneshwar Dalvi on 30/01/18.
 */
public class ApiClient {


    private static final String BASE_URL = "https://reqres.in/";

    private static ApiClient apiClient;
    private GetDataServices myAppService;

    private ApiClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myAppService = retrofit.create(GetDataServices.class);
    }

    /**
     * Gets my app client.
     *
     * @return the my app client
     */
    public static ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }

        return apiClient;
    }

    /**
     * Gets list of user.
     *
     * @return the list of user
     */
    public Observable<retrofit2.Response<ResponseModel>> getListOfUser(String pageNumber) {
        return myAppService.getList(pageNumber);
    }

}
