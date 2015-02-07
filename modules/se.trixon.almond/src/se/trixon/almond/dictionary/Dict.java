/*
 * Copyright 2015 Patrik Karlsson.
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
package se.trixon.almond.dictionary;

import java.util.Locale;
import java.util.ResourceBundle;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum Dict {

    ACTIVATE,
    ADD,
    ADVANCED,
    AFTER,
    ALTITUDE,
    ANALYZE,
    APPEND,
    BASIC,
    BEARING,
    BEFORE,
    BOOKMARKS,
    CANCEL,
    CATEGORY,
    CHANGE,
    CHANGE_TO,
    CLEAR,
    CLEAR_SELECTION,
    CLOSE,
    COMPARE,
    CONTAINS,
    COORDINATE,
    COORDINATE_FILE,
    COORDINATE_FILES,
    COPY,
    COPYRIGHT,
    CUSTOM_TEXT,
    DATE,
    DATE_PATTERN,
    DEACTIVATE,
    DEFAULT,
    DEFAULT_VALUE,
    DESCRIPTION,
    DESTINATION,
    DETAILS,
    DIRECTORY,
    DRY_RUN,
    EDIT,
    ENDS_WITH,
    ENVIRONMENT,
    ERROR,
    ERROR_PATH_SEPARATOR,
    EXCLUDE,
    EXPORT,
    FILE,
    FILE_ERROR_READ_MESSAGE,
    FILE_ERROR_WRITE_MESSAGE,
    FILE_EXISTS_MESSAGE,
    FILE_EXISTS_TITLE,
    FILENAME,
    FILE_NOT_FOUND_MESSAGE,
    FILE_NOT_FOUND_TITLE,
    FILE_PATTERN,
    FILELIST_EMPTY,
    FILTER,
    FOLDER,
    FOLDERS,
    FOLLOW_LINKS,
    FROM,
    GENERATING_FILELIST,
    HELP,
    HELP_NOT_FOUND_MESSAGE,
    HELP_NOT_FOUND_TITLE,
    HISTORY,
    INCLUDE,
    INFORMATION,
    INVALID_DATE_PATTERN,
    INVALID_DESTINATION,
    INVALID_INPUT,
    INVALID_PATH,
    INVALID_SETTING,
    INVALID_SETTINGS,
    INVALID_SOURCE,
    IO_ERROR_TITLE,
    JOB,
    JOB_PROPERTIES,
    LATITUDE,
    LICENSE,
    LINE_SEPARATOR,
    LONGITUDE,
    LOG,
    LOG_FILES,
    METADATA_FILE,
    METADATA_FILES,
    MOVE_BOTTOM,
    MOVE_DOWN,
    MOVE_TOP,
    MOVE_UP,
    NAME,
    NEWS,
    OK,
    OPEN,
    OPERATION_COMPLETED,
    OPTIONS,
    ORIGINAL,
    PATH,
    PATTERN,
    PLACEMARK,
    PHOTO,
    PROJECT,
    PROCESSING,
    QUALIFIER,
    RECURSIVE,
    REFRESH,
    REMOVE,
    REMOVE_ALL,
    REMOVE_FILE_MESSAGE,
    REMOVE_FILES_MESSAGE,
    REMOVE_FILE_TITLE,
    REMOVE_FILES_TITLE,
    RESET,
    ROOT,
    ROWS_NOT_SELECTED_MESSAGE,
    ROWS_NOT_SELECTED_TITLE,
    RUN,
    RUN_AFTER,
    RUN_BEFORE,
    SAVE,
    SAVE_AS,
    SAVING,
    SEARCH,
    SEARCHING_IN,
    SELECT,
    SELECT_ALL,
    SEPARATOR,
    SIZE,
    SOURCE,
    START,
    STARTS_WITH,
    STOP,
    SUBDIRECTORIES,
    TAB_NEW,
    TAB_CLOSE,
    TAB_CLOSE_ALL,
    TAB_CLOSE_TO_LEFT,
    TAB_CLOSE_TO_RIGHT,
    TAB_CLOSE_OTHERS,
    TASK,
    TASK_ABORTED,
    TASK_COMPLETED,
    TASKS,
    TASKS_ACTIVE,
    TASKS_AVAILABLE,
    TIME_MIN,
    TIME_SEC,
    TO,
    TYPE,
    UP,
    VERIFY,
    VERSION,
    WARNING;

    private final ResourceBundle mDefaultResourceBundle = ResourceBundle.getBundle("se/trixon/almond/dictionary/Bundle", Locale.getDefault());
    private final ResourceBundle mResourceBundle = NbBundle.getBundle(Dict.class);

    public String getString() {
        return mResourceBundle.getString(name().toLowerCase());
    }

    public String getStringDefault() {
        return mDefaultResourceBundle.getString(name().toLowerCase());
    }
}
