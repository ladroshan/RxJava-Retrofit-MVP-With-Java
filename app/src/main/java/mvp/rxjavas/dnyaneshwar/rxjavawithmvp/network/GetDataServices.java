package mvp.rxjavas.dnyaneshwar.rxjavawithmvp.network;

import io.reactivex.Observable;
import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.beans.ResponseModel;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Dnyaneshwar Dalvi on 30/01/18.
 */
public interface GetDataServices {


    /**
     * Gets list.
     *
     * @param pages the pages
     * @return the list
     */
    @GET("api/users?")
    Observable<Response<ResponseModel>> getList(@Query("page") String pages);
}
