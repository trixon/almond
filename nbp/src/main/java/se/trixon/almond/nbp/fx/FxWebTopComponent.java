/* 
 * Copyright 2023 Patrik Karlström.
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
package se.trixon.almond.nbp.fx;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Patrik Karlström
 */
public class FxWebTopComponent extends FxTopComponent {

    private transient WebView mWebView;

    public FxWebTopComponent() {
        setName("WEB");
    }

    public WebEngine getWebEngine() {
        return mWebView.getEngine();
    }

    public WebView getWebView() {
        return mWebView;
    }

    public void load(String url) {
        mWebView.getEngine().load(url);
    }

    @Override
    protected void initFX() {
        mWebView = new WebView();
        mWebView.getEngine().setJavaScriptEnabled(true);
        mWebView.getEngine().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");

        setScene(new Scene(mWebView));
    }

}
