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
package se.trixon.almond.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import org.apache.commons.lang3.ObjectUtils;

/**
 *
 * @author Patrik Karlström
 */
public class DateHelper {

    public static Date convertToDate(LocalDateTime ldt) {
        var zonedDateTime = ZonedDateTime.of(ldt, ZoneId.systemDefault());

        return new Date(zonedDateTime.toInstant().toEpochMilli());
    }

    public static Date convertToDate(LocalDate ld) {
        return convertToDate(ld.atStartOfDay());
    }

    /**
     *
     * returns max of t1 and t2, if equal; t2
     */
    public static LocalDate getMax(LocalDate t1, LocalDate t2) {
        return t1.isAfter(t2) ? t1 : t2;
    }

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
    public static LocalDate getMin(LocalDate t1, LocalDate t2) {
        return t1.isBefore(t2) ? t1 : t2;
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

    public static boolean isAfterOrEqual(LocalDate value, LocalDate reference) {
        if (ObjectUtils.anyNull(value, reference)) {
            return true;
        } else {
            return value.isEqual(reference) || value.isAfter(reference);
        }
    }

    public static boolean isBeforeOrEqual(LocalDate value, LocalDate reference) {
        if (ObjectUtils.anyNull(value, reference)) {
            return true;
        } else {
            return value.isEqual(reference) || value.isBefore(reference);
        }
    }

    public static boolean isBetween(LocalDate ld1, LocalDate ld2, LocalDate value) {
        if (ObjectUtils.allNull(ld1, ld2)) {
            return true;
        }

        if (ObjectUtils.allNotNull(ld1, ld2)) {
            if (value == null) {
                return true;
            } else {
                return isAfterOrEqual(value, ld1) && isAfterOrEqual(value, ld2);
            }
        }

        if (ld1 != null) {
            return isAfterOrEqual(value, ld1);
        }

        if (ld2 != null) {
            return isBeforeOrEqual(value, ld2);
        }

        throw new UnsupportedOperationException();
    }

    public static String toDateString(LocalDateTime localDateTime) {
        return localDateTime == null ? null : toDateString(localDateTime.toLocalDate());
    }

    public static String toDateString(LocalDate localDate) {
        return localDate == null ? null : localDate.toString();
    }
}
