package mvp.rxjavas.dnyaneshwar.rxjavawithmvp.view_presenter;

import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.beans.ResponseModel;
import retrofit2.Response;

/**
 * The interface View presenter.
 */
public interface ViewPresenter {

     interface MainView {
        void mainValidateError();
        void showProgressBar();
        void hideProgressBar();
        void mainSuccess(Response<ResponseModel> responseModel);
        void mainError(Throwable throwable);
        boolean checkInternet();
    }

     interface MainPresenter {
        void loadData(int pageNumber);
        void onStop();
    }
}
