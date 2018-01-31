package mvp.rxjavas.dnyaneshwar.rxjavawithmvp.model;


import android.support.annotation.NonNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.network.ApiClient;
import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.view_presenter.ViewPresenter;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by Dnyaneshwar Dalvi on 30/01/18.
 */
public class PresenterImplementation implements ViewPresenter.MainPresenter {


    /**
     * The Main view.
     */
    ViewPresenter.MainView mainView;

    @NonNull
    private Disposable disposable;

    /**
     * Instantiates a new Presenter implementation.
     *
     * @param mainView the main view
     */
    public PresenterImplementation(ViewPresenter.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void loadData(int pageNumber) {
        mainView.showProgressBar();

        /* Without Lambda function */

        /*disposable = ApiClient.getInstance()
                .getListOfUser(String.valueOf(pageNumber))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseModel>() {
                    @Override
                    public void accept(ResponseModel responseModel) throws Exception {
                        mainView.hideProgressBar();
                        Log.i("RxJava2: Response ", responseModel.toString());
                        mainView.mainSeccess(responseModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mainView.hideProgressBar();
                        Log.i("RxJava2: Response ", throwable.toString());
                        mainView.mainError(throwable);

                    }
                });*/

        /* With Lambda function */

        if (mainView.checkInternet()) {

            disposable = ApiClient.getInstance()
                    .getListOfUser(String.valueOf(pageNumber))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(userResponse -> {
                        mainView.hideProgressBar();
                        int responseCode = userResponse.code();
                        switch (responseCode) {
                            case 200:
                            case 201:
                            case 202:
                                mainView.mainSuccess(userResponse);
                                break;
                            case 401:
                                break;
                            case 402:
                                break;
                            case 500:
                                break;
                            case 501:
                                break;
                        }
                    }, error -> {
                        mainView.hideProgressBar();
                        if (error instanceof HttpException) {

                            Response response = ((HttpException) error).response();
                            switch (response.code()) {
                                // Handle respnse code
                            }
                        } else {
                            // Handel other errors
                        }
                        mainView.mainError(error);
                    });
        } else {
            mainView.hideProgressBar();
            mainView.mainValidateError();
        }
    }

    @Override
    public void onStop() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
