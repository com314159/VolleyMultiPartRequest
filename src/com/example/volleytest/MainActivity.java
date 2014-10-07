package com.example.volleytest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

	Button bt;
	private static RequestQueue mSingleQueue;
	private static String TAG = "test";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSingleQueue = Volley.newRequestQueue(this, new MultiPartStack());
		// mSingleQueue = Volley.newRequestQueue(this);

		bt = (Button) findViewById(R.id.button1);
		bt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Map<String, File> files = new HashMap<String, File>();
				files.put("avatar", new File(
						"/storage/emulated/0/myotee/myotee1403008731525.png"));

				Map<String, String> params = new HashMap<String, String>();
				params.put("token", "DJrlPbpJQs21rv1lP41yiA==");

				// JSONObject json = new JSONObject();
				// try {
				// json.put("token", "Rrg+wuOtS3GIN+CIaQsXSA==");
				// } catch (JSONException e) {
				// e.printStackTrace();
				// }

				addPutUploadFileRequest(
						"your_url",
						files, params, mResonseListenerString, mErrorListener, null);
			}
		});
	}

	Listener<JSONObject> mResonseListener = new Listener<JSONObject>() {

		@Override
		public void onResponse(JSONObject response) {
			Log.i(TAG, " on response json" + response.toString());
		}
	};

	Listener<String> mResonseListenerString = new Listener<String>() {

		@Override
		public void onResponse(String response) {
			Log.i(TAG, " on response String" + response.toString());
		}
	};

	ErrorListener mErrorListener = new ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			if (error != null) {
				if (error.networkResponse != null)
					Log.i(TAG, " error " + new String(error.networkResponse.data));
			}
		}
	};

	// 添加网络请求
	public static void addGetRequest(final String url, final JSONObject parameters,
			final Listener<JSONObject> responseListener, final ErrorListener errorListener,
			final Object tag) {

		if (null == url || null == responseListener) {
			return;
		}

		JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, url,
				parameters, responseListener, errorListener);

		// jsonObjRequest.setRetryPolicy(MainApplication.getDefaultRetryPolicy());
		if (null != tag) {
			jsonObjRequest.setTag(tag);
		}

		Log.i(TAG, "volley GET: " + url);
		mSingleQueue.add(jsonObjRequest);
	}

	public static void addPutUploadFileRequest(final String url,
			final Map<String, File> files, final Map<String, String> params,
			final Listener<String> responseListener, final ErrorListener errorListener,
			final Object tag) {
		if (null == url || null == responseListener) {
			return;
		}

		MultiPartStringRequest multiPartRequest = new MultiPartStringRequest(
				Request.Method.PUT, url, responseListener, errorListener) {

			@Override
			public Map<String, File> getFileUploads() {
				return files;
			}

			@Override
			public Map<String, String> getStringUploads() {
				return params;
			}
			
		};

		Log.i(TAG, " volley put : uploadFile " + url);

		mSingleQueue.add(multiPartRequest);
	}

	// 添加网络请求
	public static void addDeleteRequest(final String url,
			final Map<String, String> mapParameters, final Listener<String> responseListener,
			final ErrorListener errorListener, final Object tag) {

		if (null == url || null == responseListener) {
			return;
		}

		StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
				responseListener, errorListener) {

			@Override
			public Map<String, String> getParams() throws AuthFailureError {
				return mapParameters;
			}

		};

		// stringRequest.setRetryPolicy(MainApplication.getDefaultRetryPolicy());
		if (null != tag) {
			stringRequest.setTag(tag);
		}

		Log.i(TAG, "volley DELETE: " + url);
		mSingleQueue.add(stringRequest);
	}

}
