VolleyMultiPartRequest
======================

android volley upload file

This is a demo that use volley to upload file by use mutipartrequest

Some source code is from 
https://github.com/smanikandan14/Volley-demo

Usage :

You can see the demo code in MainActivity, I have tested it can upload file. 

usage is almost the same as volley native request.

Demo code:
    		
    		
    		
    		RequestQueue mSingleQueue = Volley.newRequestQueue(this, new MultiPartStack());
    

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
		
		mSingeQueue.add(multiPartRequest)
		
	  
