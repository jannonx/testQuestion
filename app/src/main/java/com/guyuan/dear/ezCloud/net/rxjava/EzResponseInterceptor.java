package com.guyuan.dear.ezCloud.net.rxjava;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 廖华凯
 * @since 2020/2/28 11:52
 **/
public class EzResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        Request interceptedRequest = request.newBuilder().build();
        Response response = chain.proceed(request);
//        Response interceptedResponse = response.newBuilder().build();
//        String method = interceptedRequest.method();
//
//        if(method.equalsIgnoreCase("POST")){
//            HttpUrl url = interceptedRequest.url();
//            StringBuilder sb = new StringBuilder();
//            sb.append("-------->Request:\n");
//            sb.append(url.host());
//            List<String> pathSegments = url.pathSegments();
//            for (String s : pathSegments) {
//                sb.append(s);
//            }
//            sb.append("\n").append(interceptedRequest.body().toString()).append("\n");
//            sb.append("-------->Response:\n");
//            Map<String, List<String>> map = interceptedResponse.headers().toMultimap();
//            Iterator<Map.Entry<String, List<String>>> iterator = map.entrySet().iterator();
//            while (iterator.hasNext()){
//                Map.Entry<String, List<String>> next = iterator.next();
//                sb.append(next.getKey()).append(":").append(next.getValue()).append("\n");
//            }
//            sb.append(interceptedResponse.body().string()).append("\n");
//            LogUtils.showLog(sb.toString());
//        }
        return response;
    }
}
