/*
 * Copyright 2021 Patrik Karlström.
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

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrik Karlström
 */
public class MailHelper {

    public static URI generateMailToUri(String mailTo, String subject, String cc, String body) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        add(map, "mailto:", mailTo);
        add(map, "subject", subject);
        add(map, "cc", cc);
        add(map, "body", body);

        StringBuilder sb = new StringBuilder();
        for (var entry : map.entrySet()) {
            sb.append(entry.getKey()).append(entry.getValue());
        }

        try {
            return new URI(sb.toString());
        } catch (URISyntaxException ex) {
            return null;
        }

    }

    public static void mail(URI mailToURI) {
        try {
            Desktop.getDesktop().mail(mailToURI);
        } catch (IOException ex) {
            Logger.getLogger(MailHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void add(LinkedHashMap<String, String> map, String key, String value) {
        if (value != null) {
            int i = map.size();
            String prefix = "";
            if (i == 1) {
                prefix = "?";
            } else if (i > 1) {
                prefix = "&";
            }
            String postfix = i > 0 ? "=" : "";
            map.put(prefix + key + postfix, URLEncoder.encode(value, StandardCharsets.UTF_8));
        }
    }

}
