/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.volley.mock;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.message.BasicHttpResponse;
import cz.msebera.android.httpclient.message.BasicStatusLine;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;


public class MockHttpClient implements HttpClient {
    private int mStatusCode = HttpStatus.SC_OK;
    private HttpEntity mResponseEntity = null;

    public void setResponseData(HttpEntity entity) {
        mStatusCode = HttpStatus.SC_OK;
        mResponseEntity = entity;
    }

    public void setErrorCode(int statusCode) {
        if (statusCode == HttpStatus.SC_OK) {
            throw new IllegalArgumentException("statusCode cannot be 200 for an error");
        }
        mStatusCode = statusCode;
    }

    public HttpUriRequest requestExecuted = null;

    // This is the only one we actually use.
    @Override
    public HttpResponse execute(HttpUriRequest request, HttpContext context) {
        requestExecuted = request;
        StatusLine statusLine = new BasicStatusLine(
                new ProtocolVersion("HTTP", 1, 1), mStatusCode, "");
        HttpResponse response = new BasicHttpResponse(statusLine);
        response.setEntity(mResponseEntity);

        return response;
    }


    // Unimplemented methods ahoy

    @Override
    public HttpResponse execute(HttpUriRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HttpResponse execute(HttpHost target, HttpRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T execute(HttpUriRequest arg0, ResponseHandler<? extends T> arg1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T execute(HttpUriRequest arg0, ResponseHandler<? extends T> arg1, HttpContext arg2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T execute(HttpHost arg0, HttpRequest arg1, ResponseHandler<? extends T> arg2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T execute(HttpHost arg0, HttpRequest arg1, ResponseHandler<? extends T> arg2,
            HttpContext arg3) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ClientConnectionManager getConnectionManager() {
        throw new UnsupportedOperationException();
    }

    @Override
    public HttpParams getParams() {
        throw new UnsupportedOperationException();
    }
}
