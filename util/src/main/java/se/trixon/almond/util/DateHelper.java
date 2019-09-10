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

import java.sql.Timestamp;

/**
 *
 * @author Patrik Karlström
 */
public class DateHelper {

    /**
     *
     * returns max of t1 and t2, if equal; t2
     */
    public static java.util.Date getMax(java.util.Date t1, java.util.Date t2) {
        return t1.after(t2) ? t1 : t2;
    }

    /**
     *
     * returns max of t1 and t2, if equal; t2
     */
    public static java.sql.Date getMax(java.sql.Date t1, java.sql.Date t2) {
        return t1.after(t2) ? t1 : t2;
    }

    /**
     *
     * returns max of t1 and t2, if equal; t2
     */
    public static Timestamp getMax(Timestamp t1, Timestamp t2) {
        return t1.after(t2) ? t1 : t2;
    }

    /**
     *
     * returns min of t1 and t2, if equal; t2
     */
    public static java.util.Date getMin(java.util.Date t1, java.util.Date t2) {
        return t1.before(t2) ? t1 : t2;
    }

    /**
     *
     * returns min of t1 and t2, if equal; t2
     */
    public static java.sql.Date getMin(java.sql.Date t1, java.sql.Date t2) {
        return t1.before(t2) ? t1 : t2;
    }

    /**
     *
     * returns min of t1 and t2, if equal; t2
     */
    public static Timestamp getMin(Timestamp t1, Timestamp t2) {
        return t1.before(t2) ? t1 : t2;
    }
}
