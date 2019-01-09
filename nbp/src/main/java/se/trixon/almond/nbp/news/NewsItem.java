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
package se.trixon.almond.nbp.news;

/**
 *
 * @author Patrik Karlström
 */
public class NewsItem {

    private String mName;
    private String mNews;

    public NewsItem(String name, String news) {
        mName = name;
        mNews = news;
    }

    public String getName() {
        return mName;
    }

    public String getNews() {
        return mNews;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setNews(String news) {
        mNews = news;
    }
}
