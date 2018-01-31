package mvp.rxjavas.dnyaneshwar.rxjavawithmvp.ui;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.R;
import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.adapter.UserListAdapter;
import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.beans.ResponseModel;
import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.model.PresenterImplementation;
import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.utility.CheckInternet;
import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.utility.EndlessRecyclerViewScrollListener;
import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.view_presenter.ViewPresenter;
import retrofit2.Response;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements ViewPresenter.MainView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.llNoConnection)
    LinearLayout llNoConnection;
    int pagelimit = 4;

    PresenterImplementation presenterImplementation;
    LinearLayoutManager linearLayoutManager;
    private UserListAdapter userListAdapter;
    private ArrayList<ResponseModel.Datum> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenterImplementation = new PresenterImplementation(MainActivity.this);
        initUI();

        presenterImplementation.loadData(1);

        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (page <= pagelimit) {
                    presenterImplementation.loadData(page);
                }
            }
        });

        userListAdapter = new UserListAdapter(this, arrayList);
        mRecyclerView.setAdapter(userListAdapter);
    }

    @OnClick(R.id.btnTry)
    public void btnTry(View view) {
        recreate();
    }

    private void initUI() {
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setFitsSystemWindows(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterImplementation.onStop();
    }

    @Override
    public void mainValidateError() {
        llNoConnection.setVisibility(View.VISIBLE);
        Snackbar.make(llNoConnection, "Please check your internet connection", Snackbar.LENGTH_SHORT)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Take Action
                    }
                }).show();
    }

    @Override
    public void mainSuccess(Response<ResponseModel> responseModel) {
        Log.i("RxJava2: Response ", responseModel.toString());
        llNoConnection.setVisibility(View.GONE);
        if (responseModel.body().getData() != null) {
            List<ResponseModel.Datum> listOfItem = responseModel.body().getData();
            arrayList.addAll(listOfItem);
        }
    }

    @Override
    public void mainError(Throwable throwable) {
        Log.i("RxJava2: Response ", throwable.toString());
        llNoConnection.setVisibility(View.VISIBLE);
        Toast.makeText(MainActivity.this, "Please check your Internet Connection and try again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean checkInternet() {
        return CheckInternet.isNetwork(getApplicationContext());
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
