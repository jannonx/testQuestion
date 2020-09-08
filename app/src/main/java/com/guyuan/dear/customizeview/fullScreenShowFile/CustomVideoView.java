package com.guyuan.dear.customizeview.fullScreenShowFile;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.VideoView;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * created by tl
 * created at 2020/6/17
 * 支持播放https视频
 */
public class CustomVideoView extends VideoView {


    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setVideoURI(Uri uri) {
        super.setVideoURI(uri);
        try {
            SSlUtiles sSlUtiles = new SSlUtiles();
            HttpsURLConnection.setDefaultSSLSocketFactory(sSlUtiles.createSSLSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(sSlUtiles.getTrustAllHostVerifier());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class SSlUtiles {
        /**
         * 默认信任所有的证书
         * <p>
         * xts
         */
        public SSLSocketFactory createSSLSocketFactory() {

            SSLSocketFactory sSLSocketFactory = null;

            try {
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, new TrustManager[]{(TrustManager) new TrustAllManager()}, new SecureRandom());
                sSLSocketFactory = sc.getSocketFactory();
            } catch (Exception e) {
            }

            return sSLSocketFactory;
        }

        public class TrustAllManager implements X509TrustManager {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)

                throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }

        public class TrustAllHostnameVerifier implements HostnameVerifier {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        }

        public TrustAllHostnameVerifier getTrustAllHostVerifier() {
            return new TrustAllHostnameVerifier();
        }
    }
}
