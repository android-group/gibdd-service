package ru.android_studio.olga.gibdd_servis;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/*
* Класс отвечающий за загрузку изображений
* Использую 2 библиотеки
 * Picasso - для загрузки изображений с сервера
 * OkHttpClient - кэш
 * Если не нашли изображения в кэше, тогда скачиваем их с сервера
* */
public class ImageLoader {
    public static void loadByUrlToImageView(final Context context, final String url, final ImageView imageView) {

        Picasso.with(context)
                .load(url)
                .error(R.drawable.logo)
                .placeholder(R.drawable.logo)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(url)
                                .error(R.drawable.logo)
                                .placeholder(R.drawable.logo)
                                .into(imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        Log.d("Load img from url:", url);
                                    }

                                    @Override
                                    public void onError() {
                                        Log.v("Picasso", "Could not fetch image");
                                    }
                                });
                    }
                });
    }
}
