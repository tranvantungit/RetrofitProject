package com.example.tranvantungit.retrofitproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class RxActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.just_button)
    Button justButton;

    @BindView(R.id.from_button)
    Button fromButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        ButterKnife.bind(this);

        justButton.setOnClickListener(this);
        fromButton.setOnClickListener(this);
    }

    void onFromButtonClick() {
//        Observable.fromArray(1, 2, 3)
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.e("TUNG:From:onNext", String.valueOf(integer));
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        List<String> list = new ArrayList<>();
        list.add("Tung");
        list.add("Dep");
        list.add("Trai");
        list.add("Va");
        list.add("Thong");
        list.add("Minh");

//        Observable.fromIterable(list)
//                .filter(new Predicate<String>() {
//                    @Override
//                    public boolean test(String s) {
//                        return s.toLowerCase().contains("n");
//                    }
//                })
//                .map(new Function<String, String>() {
//                    @Override
//                    public String apply(String s) throws Exception {
//                        return s.toLowerCase();
//                    }
//                })
//
//                .subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e("TUNG:From:onNext", s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e("TUNG:From:onNext", ":error:", e);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        Observable.fromIterable(list)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.toLowerCase().contains("n");
                    }
                })
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(String s) {
                        return Observable.just("Tung + " + s);
                    }
                }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                Log.e("TUNG:From:onNext", String.valueOf(o));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    void onJustButtonClick() {
        Observable.just(1, 2, 3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("TUNG:Just:onNext", String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.just_button:
                onJustButtonClick();
                break;
            case R.id.from_button:
                onFromButtonClick();
                break;
        }
    }
}
