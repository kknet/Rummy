//package com.gamegards.allinonev3;
//
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.net.http.SslError;
//import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
//import android.view.View;
//import android.webkit.SslErrorHandler;
//import android.webkit.WebResourceError;
//import android.webkit.WebResourceRequest;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.Toast;
//
//import com.gamegards.allinonev3.Comman.PayUMoneyActivity;
//import com.gamegards.allinonev3.Interface.ApiRequest;
//import com.gamegards.allinonev3.Interface.Callback;
//import com.gamegards.allinonev3.SampleClasses.Const;
//import com.gamegards.allinonev3.Utils.Funtions;
//import com.gamegards.allinonev3.Utils.SharePref;
//
//
//import org.json.JSONObject;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//public class PayuMoney extends AppCompatActivity {
//
//    PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
//    //declare paymentParam object
//    PayUmoneySdkInitializer.PaymentParam paymentParam = null;
//
//    String TAG = "mainActivity", txnid = "txt12346", amount = "100.0", phone = SharePref.getInstance().getString("mobile"),
//            prodname = "BlueApp Course", firstname = SharePref.getInstance().getString("name"), email = "support@gmail.com",
//
//
//    merchantId = "5008458", merchantkey = "gtKFFx";  //   first test key only
//    String mSalt = "wia56q6O";
//
//    Context activity = this;
//    private String mAction = ""; // For Final URL
//    private String mBaseURL = "https://test.payu.in";
////    private String mBaseURL = "https://secure.payu.in";
//
//
//    private String mSuccessUrl = "https://www.payumoney.com/mobileapp/payumoney/success.php";
//    private String mFailedUrl = "https://www.payumoney.com/mobileapp/payumoney/failure.php";
//
//
//    private String mServiceProvider = "payu_paisa";
//
//    Handler mHandler = new Handler();
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_payu_money);
//
//        Intent intent = getIntent();
//        phone = intent.getExtras().getString("phone");
//        amount = intent.getExtras().getString("amount");
//
//        startpay();
////        getHashkey();
//
//
//        mAction = mBaseURL.concat("/_payment");
//
//    }
//
//    public void startpay() {
//
//        builder.setAmount(amount)                          // Payment amount
//                .setTxnId(txnid)                     // Transaction ID
//                .setPhone(phone)                   // User Phone number
//                .setProductName(prodname)                   // Product Name or description
//                .setFirstName(firstname)                              // User First name
//                .setEmail(email)              // User Email ID
//                .setsUrl(mSuccessUrl)     // Success URL (surl)
//                .setfUrl(mFailedUrl)     //Failure URL (furl)
//                .setUdf1("")
//                .setUdf2("")
//                .setUdf3("")
//                .setUdf4("")
//                .setUdf5("")
//                .setUdf6("")
//                .setUdf7("")
//                .setUdf8("")
//                .setUdf9("")
//                .setUdf10("")
//                .setIsDebug(true)                              // Integration environment - true (Debug)/ false(Production)
//                .setKey(merchantkey)                        // Merchant key
//                .setMerchantId(merchantId);
//
//
//        try {
//            paymentParam = builder.build();
//
//            // Invoke the following function to open the checkout page.
//
////            paymentParam.setMerchantHash(mHash);
//
//            paymentParam = calculateServerSideHashAndInitiatePayment1(paymentParam);
//
//            PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, PayuMoney.this, R.style.AppTheme_default, false);
//
//
//        } catch (Exception e) {
//            Log.e(TAG, " error s " + e.toString());
//        }
//
//    }
//
//    String mHash = "";
//
//    public void getHashkey() {
//
//        HashMap params = new HashMap();
//        params.put("user_id", SharePref.getInstance().getString("user_id", ""));
//        params.put("token", SharePref.getInstance().getString("token", ""));
//        params.put("plan_id", "" + 21);
//
//        ApiRequest.Call_Api(PayuMoney.this, Const.payu_token, params, new Callback() {
//            @Override
//            public void Responce(String resp, String type, Bundle bundle) {
//
//                try {
//
//                    JSONObject jsonObject = new JSONObject(resp);
//                    String code = jsonObject.getString("code");
//                    String message = jsonObject.getString("message");
//
//                    if (code.equals("200")) {
//
//                        mHash = jsonObject.getString("payumoney_token");
//
//
////                        payumoney_string = jsonObject.getString("payumoney_string");
//
//                        JSONObject paymentbody = jsonObject.getJSONObject("payumoney_body");
//                        prodname = paymentbody.getString("plan_id");
//                        txnid = paymentbody.getString("orderId");
//                        firstname = paymentbody.getString("name");
//                        phone = paymentbody.getString("mobile");
//                        email = paymentbody.getString("email");
//                        amount = paymentbody.getString("amount");
//
//
//                        startpay();
//
//
//                    } else if (code.equals("404")) {
//                        Toast.makeText(PayuMoney.this, "" + message, Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        });
//
//
//    }
//
//    private PayUmoneySdkInitializer.PaymentParam calculateServerSideHashAndInitiatePayment1(final PayUmoneySdkInitializer.PaymentParam paymentParam) {
//
//        StringBuilder stringBuilder = new StringBuilder();
//        HashMap<String, String> params = paymentParam.getParams();
//        stringBuilder.append(params.get(PayUmoneyConstants.KEY) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.TXNID) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.AMOUNT) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.PRODUCT_INFO) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.FIRSTNAME) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.EMAIL) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF1) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF2) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF3) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF4) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF5) + "||||||");
//
//        stringBuilder.append(mSalt);
//
////        String hash = hashCal(stringBuilder.toString());
//        Funtions.LOGE("calculateServerSideHashAndInitiatePayment1", "" + stringBuilder.toString());
//        Funtions.LOGE("calculateServerSideHashAndInitiatePayment1", "" + hash);
////        paymentParam.setMerchantHash(hash);
//
//        return paymentParam;
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//// PayUMoneySdk: Success -- payuResponse{"id":225642,"mode":"CC","status":"success","unmappedstatus":"captured","key":"9yrcMzso","txnid":"223013","transaction_fee":"20.00","amount":"20.00","cardCategory":"domestic","discount":"0.00","addedon":"2018-12-31 09:09:43","productinfo":"a2z shop","firstname":"kamal","email":"kamal.bunkar07@gmail.com","phone":"9144040888","hash":"b22172fcc0ab6dbc0a52925ebbd0297cca6793328a8dd1e61ef510b9545d9c851600fdbdc985960f803412c49e4faa56968b3e70c67fe62eaed7cecacdfdb5b3","field1":"562178","field2":"823386","field3":"2061","field4":"MC","field5":"167227964249","field6":"00","field7":"0","field8":"3DS","field9":" Verification of Secure Hash Failed: E700 -- Approved -- Transaction Successful -- Unable to be determined--E000","payment_source":"payu","PG_TYPE":"AXISPG","bank_ref_no":"562178","ibibo_code":"VISA","error_code":"E000","Error_Message":"No Error","name_on_card":"payu","card_no":"401200XXXXXX1112","is_seamless":1,"surl":"https://www.payumoney.com/sandbox/payment/postBackParam.do","furl":"https://www.payumoney.com/sandbox/payment/postBackParam.do"}
////PayUMoneySdk: Success -- merchantResponse438104
//// on successfull txn
//        //  request code 10000 resultcode -1
//        //tran {"status":0,"message":"payment status for :438104","result":{"postBackParamId":292490,"mihpayid":"225642","paymentId":438104,"mode":"CC","status":"success","unmappedstatus":"captured","key":"9yrcMzso","txnid":"txt12345","amount":"20.00","additionalCharges":"","addedon":"2018-12-31 09:09:43","createdOn":1546227592000,"productinfo":"a2z shop","firstname":"kamal","lastname":"","address1":"","address2":"","city":"","state":"","country":"","zipcode":"","email":"kamal.bunkar07@gmail.com","phone":"9144040888","udf1":"","udf2":"","udf3":"","udf4":"","udf5":"","udf6":"","udf7":"","udf8":"","udf9":"","udf10":"","hash":"0e285d3a1166a1c51b72670ecfc8569645b133611988ad0b9c03df4bf73e6adcca799a3844cd279e934fed7325abc6c7b45b9c57bb15047eb9607fff41b5960e","field1":"562178","field2":"823386","field3":"2061","field4":"MC","field5":"167227964249","field6":"00","field7":"0","field8":"3DS","field9":" Verification of Secure Hash Failed: E700 -- Approved -- Transaction Successful -- Unable to be determined--E000","bank_ref_num":"562178","bankcode":"VISA","error":"E000","error_Message":"No Error","cardToken":"","offer_key":"","offer_type":"","offer_availed":"","pg_ref_no":"","offer_failure_reason":"","name_on_card":"payu","cardnum":"401200XXXXXX1112","cardhash":"This field is no longer supported in postback params.","card_type":"","card_merchant_param":null,"version":"","postUrl":"https:\/\/www.payumoney.com\/mobileapp\/payumoney\/success.php","calledStatus":false,"additional_param":"","amount_split":"{\"PAYU\":\"20.0\"}","discount":"0.00","net_amount_debit":"20","fetchAPI":null,"paisa_mecode":"","meCode":"{\"vpc_Merchant\":\"TESTIBIBOWEB\"}","payuMoneyId":"438104","encryptedPaymentId":null,"id":null,"surl":null,"furl":null,"baseUrl":null,"retryCount":0,"merchantid":null,"payment_source":null,"pg_TYPE":"AXISPG"},"errorCode":null,"responseCode":null}---438104
//
//        // Result Code is -1 send from Payumoney activity
//        Log.e("StartPaymentActivity", "request code " + requestCode + " resultcode " + resultCode);
//        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
//            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE);
//
//            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
//
//                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {
//                    //Success Transaction
//                } else {
//                    //Failure Transaction
//                }
//
//                // Response from Payumoney
//                String payuResponse = transactionResponse.getPayuResponse();
//
//                // Response from SURl and FURL
//                String merchantResponse = transactionResponse.getTransactionDetails();
//                Log.e(TAG, "tran " + payuResponse + "---" + merchantResponse);
//            } /* else if (resultModel != null && resultModel.getError() != null) {
//                Log.d(TAG, "Error response : " + resultModel.getError().getTransactionResponse());
//            } else {
//                Log.d(TAG, "Both objects are null!");
//            }*/
//        }
//    }
//
//    @SuppressLint({"WrongConstant", "JavascriptInterface"})
//    private void PayuWebview() {
//
//        WebView webView = findViewById(R.id.webView);
//
//        /**
//         * WebView Client
//         */
//        webView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//                Toast.makeText(activity, "Oh no! " + error, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onReceivedSslError(WebView view,
//                                           SslErrorHandler handler, SslError error) {
//                Toast.makeText(activity, "SSL Error! " + error, Toast.LENGTH_SHORT).show();
//                handler.proceed();
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//
//                if (url.equals(mSuccessUrl)) {
////                        Intent intent = new Intent(PayUMoneyActivity.this, PaymentStatusActivity.class);
////                        intent.putExtra("status", true);
////                        intent.putExtra("transaction_id", mTXNId);
////                        intent.putExtra("id", mId);
////                        intent.putExtra("isFromOrder", isFromOrder);
////                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                        startActivity(intent);
//                } else if (url.equals(mFailedUrl)) {
////                        Intent intent = new Intent(PayUMoneyActivity.this, PaymentStatusActivity.class);
////                        intent.putExtra("status", false);
////                        intent.putExtra("transaction_id", mTXNId);
////                        intent.putExtra("id", mId);
////                        intent.putExtra("isFromOrder", isFromOrder);
////                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                        startActivity(intent);
//                }
//                super.onPageFinished(view, url);
//            }
//        });
//
//        webView.setVisibility(View.VISIBLE);
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setCacheMode(2);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.clearHistory();
//        webView.clearCache(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setUseWideViewPort(false);
//        webView.getSettings().setLoadWithOverviewMode(false);
//        webView.addJavascriptInterface(new PayUJavaScriptInterface(PayuMoney.this), "PayUMoney");
//
//        /**
//         * Mapping Compulsory Key Value Pairs
//         */
//        Map<String, String> mapParams = new HashMap<>();
//
//        mapParams.put("key", merchantkey);
//        mapParams.put("txnid", txnid);
//        mapParams.put("amount", String.valueOf(amount));
//        mapParams.put("productinfo", prodname);
//        mapParams.put("firstname", firstname);
//        mapParams.put("email", email);
//        mapParams.put("phone", phone);
//        mapParams.put("surl", mSuccessUrl);
//        mapParams.put("furl", mFailedUrl);
//        mapParams.put("hash", mHash);
//        mapParams.put("service_provider", mServiceProvider);
//
//        webViewClientPost(webView, mAction, mapParams.entrySet());
//    }
//
//
//    public void webViewClientPost(WebView webView, String url,
//                                  Collection<Map.Entry<String, String>> postData) {
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("<html><head></head>");
//        sb.append("<body onload='form1.submit()'>");
//        sb.append(String.format("<form id='form1' action='%s' method='%s'>", url, "post"));
//
//        for (Map.Entry<String, String> item : postData) {
//            sb.append(String.format("<input name='%s' type='hidden' value='%s' />", item.getKey(), item.getValue()));
//        }
//        sb.append("</form></body></html>");
//
//        Log.d("TAG", "webViewClientPost called: " + sb.toString());
//        webView.loadData(sb.toString(), "text/html", "utf-8");
//    }
//
//    public class PayUJavaScriptInterface {
//        Context mContext;
//
//        /**
//         * Instantiate the interface and set the context
//         */
//        PayUJavaScriptInterface(Context c) {
//            mContext = c;
//        }
//
//        public void success(long id, final String paymentId) {
//            mHandler.post(new Runnable() {
//
//                public void run() {
//                    mHandler = null;
//                    Toast.makeText(activity, "Payment Successfully.", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//
//}