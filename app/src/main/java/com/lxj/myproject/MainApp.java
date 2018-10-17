package com.lxj.myproject;

import android.app.Application;
import android.os.Handler;
import android.widget.Toast;

import com.lxj.myproject.util.SSL;
import com.zhy.http.okhttp.OkHttpUtils;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

import static android.widget.Toast.makeText;


/**
 * Created by li on 2018/9/11.
 */

public class MainApp extends Application {
    private static MainApp mainApp;
    private Handler delayHandler = new Handler();
    private Toast mToast;

    @Override
    public void onCreate() {
        super.onCreate();
        mainApp = this;
        initOkHttpClient();
    }

    public static MainApp getInstance() {
        return mainApp;
    }

    /**
     * 初始化okhttp
     */
    public void initOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(new SSL(trustAllCert), trustAllCert)
                // 连接超时时间
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                // 加载超时时间
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    //定义一个信任所有证书的TrustManager
    final X509TrustManager trustAllCert = new X509TrustManager() {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }
    };

    /**
     * Toast的工具方法
     *
     * @param msg
     */
    public void showMsg(final String msg) {
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (null == mToast) {
                    mToast = makeText(mainApp, msg, Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(msg);
                }
                mToast.show();
            }
        }, 100);
    }
}
