package xproject.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import xproject.mvp.model.AndroidVersion;
import xproject.mvplogin.R;

public class RecycleViewDataAdapter extends RecyclerView.Adapter<RecycleViewDataAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.profile_image);
            textView = (TextView) view.findViewById(R.id.android_version);
        }
    }

    public static int dpTopx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int pxTodp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public Context context;
    public ArrayList<AndroidVersion> androidVersions;
    private int size;
    private Object tag;

    public RecycleViewDataAdapter(Context context, ArrayList<AndroidVersion> androidVersionsList) {
        this.context = context;
        this.androidVersions = androidVersionsList;
        this.size = dpTopx(context, 96);
        this.tag = new Object();
    }

    public Object getTag() {
        return tag;
    }

    @Override
    public RecycleViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picasso_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleViewDataAdapter.ViewHolder holder, int position) {
        Picasso.with(context).load(androidVersions.get(position).imageUrl).into(holder.imageView);
//        Picasso.with(context).load(androidVersions.get(position).imageUrl)
//                .resize(size, size).centerCrop().config(Bitmap.Config.RGB_565).tag(tag).into(holder.imageView);
        holder.textView.setText(androidVersions.get(position).name);
    }

    @Override
    public int getItemCount() {
        return androidVersions.size();
    }

}