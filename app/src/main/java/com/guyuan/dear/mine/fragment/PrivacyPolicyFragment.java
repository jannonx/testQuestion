package com.guyuan.dear.mine.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentPrivacyPolicyBinding;
import com.guyuan.dear.mine.bean.PrivacyPolicyDataBean;
import com.guyuan.dear.mine.data.MineViewModel;

/**
 * @description: 我的--隐私政策
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class PrivacyPolicyFragment extends BaseDataBindingFragment<FragmentPrivacyPolicyBinding, MineViewModel> {

    public static final String TAG = "FocusAssessOverviewFrag";
    WebSettings mWebSettings;

    public static PrivacyPolicyFragment newInstance() {
        Bundle args = new Bundle();
        PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_privacy_policy;
    }

    @Override
    protected void initialization() {

        viewModel.getPrivacyPolicyData();

        viewModel.getPrivacyDataEvent().observe(this, new Observer<PrivacyPolicyDataBean>() {
            @Override
            public void onChanged(PrivacyPolicyDataBean dataBean) {

                binding.webView.loadDataWithBaseURL(null, getHtmlData(dataBean.getDescHtml()),
                        "text/html", "utf-8", null);
            }
        });


    }

    private void initWebView() {
        mWebSettings = binding.webView.getSettings();

        binding.webView.loadUrl("https://jannonx.github.io");


        //设置不用系统浏览器打开,直接显示在当前Webview
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //设置WebChromeClient类
        binding.webView.setWebChromeClient(new WebChromeClient() {


            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("数据加载中...");
//                mtitle.setText(title);
            }


            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress < 100) {
//                    String progress = newProgress + "%";
//                    binding.tvLoading.setText(progress);
//                } else if (newProgress == 100) {
//                    String progress = newProgress + "%";
//                    binding.tvLoading.setText(progress);
//                }
            }
        });


        //设置WebViewClient类
        binding.webView.setWebViewClient(new WebViewClient() {
            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                binding.llTip.setVisibility(View.VISIBLE);

            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
//                binding.llTip.setVisibility(View.GONE);

            }
        });
    }

    @Override
    protected int getVariableId() {
        return 0;
    }


    //销毁Webview
    @Override
    public void onDestroy() {
        if (binding.webView != null) {
            binding.webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            binding.webView.clearHistory();

            ((ViewGroup) binding.webView.getParent()).removeView(binding.webView);
            binding.webView.destroy();
//            binding.webView = null;
        }
        super.onDestroy();
    }

    /**
     * 富文本适配
     */
    private String getHtmlData(String bodyHTML) {
        String head = "<head>"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> "
                + "<style>img{max-width: 100%; width:auto; height:auto;}</style>"
                + "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
}
