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

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.RecyclerViewHolder>{

    private List<Crypto> cryptoList;

    CryptoAdapter(List<Crypto> cryptoList) {
        this.cryptoList = cryptoList;
    }

    @NonNull
    @Override
    public CryptoAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.crypto_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoAdapter.RecyclerViewHolder viewHolder, int i) {
        viewHolder.nameCrypto.setText(cryptoList.get(i).getMarketName());
        Picasso picasso = Picasso.get();
        picasso.setLoggingEnabled(true);
        picasso.setIndicatorsEnabled(true);
        Context context = viewHolder.nameCrypto.getContext();
        int sizeInPx = Utils.dpToPx((int) context.getResources().getDimension(R.dimen.image_size));
        picasso.load(cryptoList.get(i).getLogoUrl())
                .noFade()
                .resize(sizeInPx, sizeInPx)
                .into(viewHolder.logoCrypto, new Callback() {
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
        return cryptoList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_crypto)
        TextView nameCrypto;
        @BindView(R.id.logo_crypto)
        ImageView logoCrypto;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
