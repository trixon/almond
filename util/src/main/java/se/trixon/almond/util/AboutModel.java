/*
 * Copyright 2019 Patrik Karlström.
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
package se.trixon.almond.util;

import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javax.swing.ImageIcon;

/**
 *
 * @author Patrik Karlström
 */
public class AboutModel {

    private String mAppCopyright;
    private String mAppDate;
    private String mAppDescription;
    private String mAppLicenseUrl;
    private String mAppLicenseUrlTitle;
    private String mAppName;
    private String mAppUrl;
    private String mAppUrlTitle;
    private String mAppVersion;
    private String mAuthors;
    private ResourceBundle mBundle;
    private ImageIcon mImageIcon = null;
    private ImageView mImageView = null;
    private String mLibraries;
    private String mThanksTo;
    private String mTranslation;

    public AboutModel() {
    }

    public AboutModel(ResourceBundle resourceBundle) {
        setBundle(resourceBundle);
    }

    public AboutModel(ResourceBundle resourceBundle, ImageIcon imageIcon) {
        setBundle(resourceBundle);
        setImageIcon(imageIcon);
    }

    public AboutModel(ResourceBundle resourceBundle, ImageView imageView) {
        setBundle(resourceBundle);
        setImageView(imageView);
    }

    public String getAppCopyright() {
        return mAppCopyright;
    }

    public String getAppDate() {
        return mAppDate;
    }

    public String getAppDescription() {
        return mAppDescription;
    }

    public String getAppLicenseUrl() {
        return mAppLicenseUrl;
    }

    public String getAppLicenseUrlTitle() {
        return mAppLicenseUrlTitle;
    }

    public String getAppName() {
        return mAppName;
    }

    public String getAppUrl() {
        return mAppUrl;
    }

    public String getAppUrlTitle() {
        return mAppUrlTitle;
    }

    public String getAppVersion() {
        return mAppVersion;
    }

    public String getAuthors() {
        return mAuthors;
    }

    public ResourceBundle getBundle() {
        return mBundle;
    }

    public ImageIcon getImageIcon() {
        return mImageIcon;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public String getLibraries() {
        return mLibraries;
    }

    public String getThanksTo() {
        return mThanksTo;
    }

    public String getTranslation() {
        return mTranslation;
    }

    public void setAppCopyright(String appCopyright) {
        mAppCopyright = appCopyright;
    }

    public void setAppDate(String appDate) {
        mAppDate = appDate;
    }

    public void setAppDescription(String appDescription) {
        mAppDescription = appDescription;
    }

    public void setAppLicenseUrl(String appLicenseUrl) {
        mAppLicenseUrl = appLicenseUrl;
    }

    public void setAppLicenseUrlTitle(String appLicenseUrlTitle) {
        mAppLicenseUrlTitle = appLicenseUrlTitle;
    }

    public void setAppName(String appName) {
        mAppName = appName;
    }

    public void setAppUrl(String appUrl) {
        mAppUrl = appUrl;
    }

    public void setAppUrlTitle(String appUrlTitle) {
        mAppUrlTitle = appUrlTitle;
    }

    public void setAppVersion(String appVersion) {
        mAppVersion = appVersion;
    }

    public void setAuthors(String authors) {
        mAuthors = authors;
    }

    public void setBundle(ResourceBundle bundle) {
        mBundle = bundle;

        setAppName(getBundleValue("app.name"));
        setAppVersion(getBundleValue("app.version"));
        setAppCopyright(getBundleValue("app.copyright"));
        setAppDate(getBundleValue("app.date"));
        setAppDescription(getBundleValue("app.description"));
        setAppUrl(getBundleValue("app.uri"));
        setAppUrlTitle(getBundleValue("app.uriTitle"));
        setAppLicenseUrl(getBundleValue("app.licenseUri"));
        setAppLicenseUrlTitle(getBundleValue("app.licenseUriTitle"));
        setLibraries(getBundleValue("libraries"));
        setAuthors(getBundleValue("authors"));
        setTranslation(getBundleValue("translation"));
        setThanksTo(getBundleValue("thanksTo"));
    }

    public void setImageIcon(ImageIcon imageIcon) {
        mImageIcon = imageIcon;
    }

    public void setImageView(ImageView imageView) {
        mImageView = imageView;
        mImageView.setPreserveRatio(true);
        mImageView.setFitHeight(100);
        mImageView.setFitWidth(100);
    }

    public void setLibraries(String libraries) {
        mLibraries = libraries;
    }

    public void setThanksTo(String thanksTo) {
        mThanksTo = thanksTo;
    }

    public void setTranslation(String translation) {
        mTranslation = translation;
    }

    private String getBundleValue(String key) {
        if (mBundle.containsKey(key)) {
            return mBundle.getString(key);
        } else {
            return null;
        }
    }
}
