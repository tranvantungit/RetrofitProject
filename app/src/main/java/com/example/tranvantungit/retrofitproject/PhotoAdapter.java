package com.example.tranvantungit.retrofitproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoAdapterViewHolder>{
    List<Photo> photos;

    public PhotoAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotoAdapter.PhotoAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.photo_item, parent, false);
        return new PhotoAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.PhotoAdapterViewHolder holder, int position) {
        holder.imageTitle.setText(photos.get(position).getTitle());
        Picasso picasso = Picasso.get();
        picasso.setLoggingEnabled(true);
        picasso.setIndicatorsEnabled(true);
        Context context = holder.imageTitle.getContext();
        int sizeInPx = Utils.dpToPx((int) context.getResources().getDimension(R.dimen.image_size));
        picasso.load(photos.get(position).getUrl())
                .noFade()
                .resize(sizeInPx, sizeInPx)
                .into(holder.thumbnail, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class PhotoAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.image_title)
        TextView imageTitle;

        PhotoAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
