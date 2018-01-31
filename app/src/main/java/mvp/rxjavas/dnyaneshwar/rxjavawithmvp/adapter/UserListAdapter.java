package mvp.rxjavas.dnyaneshwar.rxjavawithmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.R;
import mvp.rxjavas.dnyaneshwar.rxjavawithmvp.beans.ResponseModel;


/**
 * The type User list adapter.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private final ArrayList<ResponseModel.Datum> arraylist;
    private final Context appContext;

    /**
     * Instantiates a new User list adapter.
     *
     * @param appContext the app context
     * @param arraylist  the arraylist
     */
    public UserListAdapter(Context appContext, ArrayList<ResponseModel.Datum> arraylist) {
        this.arraylist = arraylist;
        this.appContext = appContext;
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_user_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserListAdapter.ViewHolder holder, final int position) {
        ResponseModel.Datum list = arraylist.get(position);
        if (list != null) {
            holder.tvFirstName.setText("First Name: " + list.getFirst_name());
            holder.lastName.setText("Last Name: " + list.getLast_name());
            holder.Id.setText("ID: " + list.getId().toString());
            Glide.with(appContext).load(list.getAvatar()).into(holder.avatar);
        }
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    /**
     * The type View holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * The Tv first name.
         */
        TextView tvFirstName, /**
         * The Last name.
         */
        lastName, /**
         * The Id.
         */
        Id;
        /**
         * The Avatar.
         */
        ImageView avatar;

        /**
         * Instantiates a new View holder.
         *
         * @param itemView the item view
         */
        public ViewHolder(View itemView) {
            super(itemView);
            tvFirstName = (TextView) itemView.findViewById(R.id.tvFirstName);
            lastName = (TextView) itemView.findViewById(R.id.tvLastName);
            Id = (TextView) itemView.findViewById(R.id.tvId);
            avatar = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }
}
