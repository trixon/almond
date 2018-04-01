/* 
 * Copyright 2018 Patrik Karlström.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.openide.util.Lookup;

/**
 *
 * @author Patrik Karlström
 */
public class NewsBuilder {

    private final Map<String, LinkedList<NewsItem>> mDateCollections = new HashMap<>();

    public NewsBuilder() {
    }

    public StringBuilder getNews() {
        Collection<? extends NewsProvider> newsProviders = Lookup.getDefault().lookupAll(NewsProvider.class);

        newsProviders.stream().forEach((provider) -> {
            ResourceBundle bundle = provider.getNewsBundle();
            if (bundle != null) {
                String name = provider.getName();
                bundle.keySet().stream().forEach((key) -> {
                    getDateCollection(key).add(new NewsItem(name, bundle.getString(key)));
                });
            }
        });

        StringBuilder builder = new StringBuilder();

        List keys = new ArrayList(mDateCollections.keySet());
        Collections.sort(keys);
        Collections.reverse(keys);

        keys.stream().forEach((key) -> {
            builder.append("<h2>").append(key).append("</h2>");
            LinkedList<NewsItem> newsItems = mDateCollections.get((String) key);
            newsItems.sort((NewsItem o1, NewsItem o2) -> o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));

            newsItems.stream().forEach((newsItem) -> {
                builder.append("<p><strong>").append(newsItem.getName()).append(":</strong> ").append(newsItem.getNews()).append("</p>");
            });
        });

        return builder;
    }

    private LinkedList<NewsItem> getDateCollection(String key) {
        if (!mDateCollections.containsKey(key)) {
            mDateCollections.put(key, new LinkedList<>());
        }

        return mDateCollections.get(key);
    }
}
