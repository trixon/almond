/*
 * Copyright 2014 Patrik Karlsson.
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
    APPEND,
    BASIC,
    BEFORE,
    CANCEL,
    CATEGORY,
    CLEAR,
    CLEAR_SELECTION,
    CLOSE,
    CONTAINS,
    COMPARE,
    COPY,
    COPYRIGHT,
    DATE,
    DEACTIVATE,
    DESCRIPTION,
    DESTINATION,
    DETAILS,
    DIRECTORY,
    EDIT,
    ENDS_WITH,
    ENVIRONMENT,
    ERROR,
    EXCLUDE,
    EXPORT,
    FILE,
    FILE_ERROR_READ_MESSAGE,
    FILE_ERROR_WRITE_MESSAGE,
    FILE_EXISTS_MESSAGE,
    FILE_EXISTS_TITLE,
    FILE_NOT_FOUND_MESSAGE,
    FILE_NOT_FOUND_TITLE,
    FILTER,
    INCLUDE,
    INFORMATION,
    INVALID_INPUT,
    IO_ERROR_TITLE,
    JOB,
    JOB_PROPERTIES,
    LICENSE,
    LOG,
    MOVE_BOTTOM,
    MOVE_DOWN,
    MOVE_TOP,
    MOVE_UP,
    NAME,
    OK,
    OPEN,
    OPERATION_COMPLETED,
    OPTIONS,
    PATH,
    PATTERN,
    PROJECT,
    REMOVE,
    REMOVE_ALL,
    REMOVE_FILE_MESSAGE,
    REMOVE_FILES_MESSAGE,
    REMOVE_FILE_TITLE,
    REMOVE_FILES_TITLE,
    RESET,
    ROWS_NOT_SELECTED_MESSAGE,
    ROWS_NOT_SELECTED_TITLE,
    RUN,
    RUN_AFTER,
    RUN_BEFORE,
    SAVE,
    SAVE_AS,
    SEARCH,
    SEARCHING_IN,
    SELECT,
    SELECT_ALL,
    SIZE,
    SOURCE,
    START,
    STARTS_WITH,
    STOP,
    TASK,
    TASK_ABORTED,
    TASK_COMPLETED,
    TASKS,
    TASKS_ACTIVE,
    TASKS_AVAILABLE,
    TYPE,
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
