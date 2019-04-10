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

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Patrik Karlström
 */
public enum Dict {

    ABANDONED,
    ABORTING,
    ABOUT,
    ABOUT_S,
    ABSOLUTE,
    ACCESS_DENIED,
    ACTIVATE,
    ACTIVE,
    ADD,
    ADD_BOOKMARK,
    ADD_REMOVE,
    ADMIN,
    ADMINISTRATOR,
    ADVANCED,
    AFTER,
    ALL,
    ALL_FILES,
    ALTITUDE,
    ALWAYS_ON_TOP,
    ANALYZE,
    APPEARANCE,
    APPEND,
    APPLICATION,
    APPLY,
    ARCHIVE,
    AUTHORS,
    AUTOMATIC_UPDATING,
    AVAILABLE,
    BACKGROUND,
    BACKGROUND_COLOR,
    BASENAME,
    BASIC,
    BEARING,
    BEFORE,
    BOOKMARK,
    BOOKMARKS,
    BOOKMARKS_SEARCH,
    BORDER,
    BORDER_SIZE,
    BROWSE,
    CALENDAR_LANGUAGE,
    CANCEL,
    CANCELED,
    CATEGORY,
    CHANGE,
    CHANGE_TO,
    @Deprecated
    CIRCLE,
    CLEAR,
    CLEAR_COMMENTS,
    CLEAR_SELECTION,
    CLIENT,
    CLONE,
    CLOSE,
    CLOSE_WINDOW,
    COLOR,
    COMMAND,
    COMMENT,
    COMPARE,
    CONNECT,
    CONNECTION_LOST,
    CONNECTION_LOST_SERVER_SHUTDOWN,
    CONNECT_TO_HOST,
    CONNECT_TO_SERVER,
    CONTAINS,
    COORDINATE,
    COORDINATE_FILE,
    COORDINATE_FILES,
    COPY,
    COPYRIGHT,
    CREDIT,
    CUSTOMIZE,
    CUSTOMIZED,
    CUSTOM_TEXT,
    CUSTOM_TIME_ZONE,
    DARK,
    DATA_SOURCES,
    DATE,
    DATE_PATTERN,
    DATE_SOURCE,
    DEACTIVATE,
    DEFAULT,
    DEFAULT_VALUE,
    DELETIONS,
    DESCRIPTION,
    DESTINATION,
    DETAILS,
    DIRECTORY,
    DIRECTORY_NOT_FOUND,
    DIRECTORY_NOT_FOUND_DEST,
    DIRECTORY_NOT_FOUND_SOURCE,
    DISABLE,
    DISABLED,
    DISCONNECT,
    DOCUMENT,
    DOCUMENTATION,
    DONE,
    DOWNLOADS_SCHEDULE,
    DOWNLOAD_COMPLETED,
    DOWNLOAD_FAILED,
    DOWNLOAD_LATER,
    DOWNLOAD_NOW,
    DOWNLOAD_SCHEDULE,
    DOWNLOAD_SCHEDULED,
    DO_NOT_SPLIT,
    DRY_RUN,
    DYNAMIC,
    DYNAMIC_WORD_WRAP,
    EDIT,
    ELEVATION,
    ENABLE,
    ENABLED,
    ENDS_WITH,
    ENVIRONMENT,
    EVENT,
    EVENTS,
    EVERY,
    EXCLUDE,
    EXPORT,
    EXTENSION,
    EXTENSION_FILTER_LOG,
    EXTERNAL_FILE,
    FAILED,
    FILE,
    FILELIST_EMPTY,
    FILENAME,
    FILE_MENU,
    FILE_PATTERN,
    FILE_REFERENCE,
    FILTER,
    FOLDER,
    FOLDERS,
    FOLLOW_LINKS,
    FONT,
    FOREGROUND,
    FROM,
    FULL_SCREEN,
    GENERATING_FILELIST,
    GLOBAL,
    GLOBAL_OPTIONS,
    GROUP,
    @Deprecated
    HEIGHT,
    HELP,
    HISTORY,
    HITS,
    HOME,
    HOST,
    ICONS,
    IMAGE,
    IMAGE_DIRECTORY,
    IMAGE_SIZE,
    IMPORT,
    INCLUDE,
    INFORMATION,
    INVALID_ARGUMENT,
    INVALID_DATE_PATTERN,
    INVALID_DESTINATION,
    INVALID_DIRECTORY,
    INVALID_INPUT,
    INVALID_PATH,
    INVALID_PORT,
    INVALID_SETTING,
    INVALID_SETTINGS,
    INVALID_SOURCE,
    JOB,
    JOBS,
    JOB_ALREADY_RUNNING,
    JOB_FAILED,
    JOB_FINISHED,
    JOB_INTERRUPTED,
    JOB_NOT_FOUND,
    JOB_NOT_RUNNING,
    JOB_PROPERTIES,
    KEY,
    KILOMETERS,
    LAST,
    LATITUDE,
    LAYER,
    LAYERS,
    LIBRARIES,
    LICENSE,
    LIGHT,
    @Deprecated
    LINE,
    LINE_SEPARATOR,
    LIST,
    LOCAL,
    LOG,
    LOGGING,
    LOG_DIRECTORY,
    LOG_ERRORS,
    LOG_FILES,
    LOG_OUTPUT,
    LOG_PROGRAM,
    LOG_SEPARATE_ERRORS,
    LOG_SYSTEM,
    LONGITUDE,
    LOOK_AND_FEEL,
    MADE_BY,
    MADE_IN,
    MADE_WITH,
    MANUAL,
    MAP,
    MAP_TYPE_HYBRID,
    MAP_TYPE_ROADMAP,
    MAP_TYPE_SATELLITE,
    MAP_TYPE_TERRAIN,
    MATERIAL_BLACK,
    MATERIAL_WHITE,
    MAX_HEIGHT,
    MAX_WIDTH,
    MEASURE,
    MENU,
    METADATA_FILE,
    METADATA_FILES,
    METERS,
    MISCELLANEOUS,
    MODE,
    MOVE,
    MOVE_BOTTOM,
    MOVE_DOWN,
    MOVE_TOP,
    MOVE_UP,
    NAME,
    NEW,
    NEWS,
    NEXT,
    NO,
    NONE,
    NOTE,
    NOTES,
    NO_BOOKMARKS,
    OK,
    OPACITY,
    OPEN,
    OPEN_DIRECTORY,
    OPERATION,
    OPERATION_COMPLETED,
    OPERATION_INTERRUPTED,
    OPTIONS,
    ORGANIZATION,
    ORIGINAL,
    OUTPUT,
    OUT_OF_BOUNDS,
    OWNERSHIP,
    PASSWORD,
    PASTE,
    PATH,
    PATH_GAP_GFX,
    @Deprecated
    PATH_GFX,
    PATTERN,
    PHOTO,
    PIN,
    PLACEMARK,
    PLACE_NAME,
    PLACE_NAMES,
    PLAY,
    PLEASE_WAIT,
    PLOT,
    PLUGINS,
    POLYGON,
    PORT,
    PREFERENCES,
    PRESETS,
    PREVIOUS,
    PROCESSING,
    PROFILE,
    PROFILES,
    PROJECT,
    PROPERTIES,
    QUALIFIER,
    QUIT,
    RANDOM,
    READY,
    READ_ONLY,
    RECURSIVE,
    REDO,
    REFRESH,
    REGENERATE,
    RELATIVE,
    REMOVE,
    REMOVE_ALL,
    RENAME,
    RENDER,
    REPLACE,
    REPORT,
    REPORTS,
    RESET,
    RESET_WINDOWS,
    RESTORE,
    RESTORE_DEFAULTS,
    REVERT,
    REVIEW,
    ROOT,
    RULER,
    RUN,
    RUN_AFTER,
    RUN_BEFORE,
    SAVE,
    SAVE_AS,
    SAVE_LOG,
    SAVING,
    SCALE,
    SCHEDULE,
    SCHEDULER,
    SEARCH,
    SEARCHING_IN,
    SELECT,
    SELECTED,
    SELECT_ALL,
    SELECT_COLOR,
    SEPARATOR,
    SERVER,
    SERVER_SHUTDOWN,
    SERVER_START,
    SHOW,
    SHUTDOWN,
    SHUTDOWN_AND_QUIT,
    SHUTDOWN_SERVER_AND_QUIT,
    SHUTDOWN_SERVER_AND_WINDOW,
    SHUTDOWN_SERVICE,
    SIZE,
    SOURCE,
    SOURCE_AND_DEST,
    SPLIT_BY,
    START,
    STARTED,
    STARTS_WITH,
    STATIC,
    STOP,
    STOPPED,
    STOP_ON_ERROR,
    STYLE,
    SUBDIRECTORIES,
    SYMBOL,
    SYSTEM,
    SYSTEM_CODE,
    SYSTEM_TIME_ZONE,
    TAB_CLOSE,
    TAB_CLOSE_ALL,
    TAB_CLOSE_OTHERS,
    TAB_CLOSE_TO_LEFT,
    TAB_CLOSE_TO_RIGHT,
    TAB_NEW,
    TASK,
    TASKS,
    TASKS_ACTIVE,
    TASKS_AVAILABLE,
    TASKS_FAILED,
    TASKS_SELECTED,
    TASK_ABORTED,
    TASK_COMPLETED,
    TEMPLATE,
    TEMPLATES,
    TEXT,
    TEXT_COLOR,
    THANKS_TO,
    THEME,
    THICKNESS,
    THUMBNAIL,
    TIME_MIN,
    TIME_MINUTES,
    TIME_SEC,
    TIME_SECONDS,
    TO,
    TOOLBARS,
    TOOLBOX,
    TOOLS,
    TOOLS_SEARCH,
    TO_MANY_CLIENTS,
    TRANSLATION,
    TYPE,
    UNDO,
    UNHANDLED_ERROR,
    UNHANDLED_EXCEPTION,
    UNIQUE,
    UNTITLED,
    UP,
    USER,
    USERNAME,
    VALUE,
    VERIFY,
    VERSION,
    VIEW,
    WARNING,
    @Deprecated
    WIDTH,
    WINDOW,
    WINDOW_ALREADY_OPEN,
    YES,
    ZOOM_EXTENTS,
    ZOOM;

    private final ResourceBundle mResourceBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(Dict.class) + "Dict", Locale.getDefault());

    private static String getString(ResourceBundle bundle, String key) {
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        } else {
            return "Key not found: " + key;
        }
    }

    @Override
    public String toString() {
        return getString(mResourceBundle, name().toLowerCase());
    }

    public enum Dialog {
        ERROR,
        ERRORS,
        ERROR_CANT_OPEN_DIRECTORY,
        ERROR_CANT_OPEN_FILE,
        ERROR_DEST_CANT_WRITE,
        ERROR_DEST_DIR_IS_FILE,
        ERROR_DEST_FILE_EXISTS,
        ERROR_EXIF_NOT_FOUND,
        ERROR_FILE_FORMAT_NOT_SUPPORTED,
        ERROR_FILE_READ,
        ERROR_FILE_WRITE,
        ERROR_NO_GUI_IN_HEADLESS,
        ERROR_PATH_SEPARATOR,
        ERROR_PROFILE_EXIST,
        ERROR_PROFILE_NOT_FOUND,
        ERROR_VALIDATION,
        MESSAGE_ARE_YOU_SURE,
        MESSAGE_DELETE_OBJECT,
        MESSAGE_DELETE_OBJECTS,
        MESSAGE_FILE_EXISTS,
        MESSAGE_FILE_NOT_FOUND,
        MESSAGE_HELP_NOT_FOUND,
        MESSAGE_NO_PROFILES_FOUND,
        MESSAGE_PROFILE_REMOVE,
        MESSAGE_PROFILE_REMOVE_ALL,
        MESSAGE_BOOKMARK_REMOVE_ALL,
        MESSAGE_REMOVE_FILE,
        MESSAGE_REMOVE_FILES,
        MESSAGE_RESTART_REQUIRED,
        MESSAGE_ROWS_NOT_SELECTED,
        MESSAGE_WRONG_PASSWORD,
        TITLE_ADD_IMAGE,
        TITLE_ADD_IMAGES,
        TITLE_BOOKMARK_REMOVE,
        TITLE_BOOKMARK_REMOVE_ALL,
        TITLE_CONFIRM_DELETE,
        TITLE_DELETE_OBJECT,
        TITLE_DELETE_OBJECTS,
        TITLE_EDIT_PROPERTIES,
        TITLE_FILE_EXISTS,
        TITLE_FILE_NOT_FOUND,
        TITLE_HELP_NOT_FOUND,
        TITLE_IO_ERROR,
        TITLE_PROFILE_ADD,
        TITLE_PROFILE_CLONE,
        TITLE_PROFILE_REMOVE,
        TITLE_PROFILE_REMOVE_ALL,
        TITLE_PROFILE_RENAME,
        TITLE_PROFILE_RUN,
        TITLE_REMOVE_FILE,
        TITLE_REMOVE_FILES,
        TITLE_REMOVE_S,
        TITLE_RESTART_REQUIRED,
        TITLE_ROWS_NOT_SELECTED,
        TITLE_SELECT_COLOR,
        ZZZ;
        private final ResourceBundle mResourceBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(Dict.class) + "DictDialog", Locale.getDefault());

        @Override
        public String toString() {
            return getString(mResourceBundle, name().toLowerCase());
        }
    }

    public enum Geometry {
        ANGLE,
        AREA,
        BEARING,
        CENTER,
        CIRCLE,
        DIAMETER,
        ELLIPSE,
        GEOMETRIES,
        GEOMETRY,
        HEIGHT,
        LENGTH,
        LINE,
        PATH,
        PERIMETER,
        POINT,
        POLYGON,
        RADIUS,
        RECTANGLE,
        SQUARE,
        WIDTH,
        ZZZ;
        private final ResourceBundle mResourceBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(Dict.class) + "DictGeometry", Locale.getDefault());

        @Override
        public String toString() {
            return getString(mResourceBundle, name().toLowerCase());
        }
    }

    public enum Time {
        DATE,
        DAY,
        HOUR,
        MINUTE,
        MONTH,
        SECOND,
        TIME,
        WEEK,
        YEAR,
        ZZZ;
        private final ResourceBundle mResourceBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(Dict.class) + "DictTime", Locale.getDefault());

        @Override
        public String toString() {
            return getString(mResourceBundle, name().toLowerCase());
        }
    }

    public enum Game {
        DICE,
        GAME,
        GAME_OVER,
        GAME_SELECTOR,
        GAME_TYPE,
        GO_HOME,
        GOAL,
        HOLD,
        INSTALL_GAMES,
        INSTALLED_GAMES,
        LEVEL,
        NEW_ROUND,
        NUMBER_OF_PLAYERS,
        NO_INSTALLED_GAMES,
        PLAYER,
        PLAYERS,
        SELECT_PLAYER,
        SCORECARD,
        SHUFFLE,
        VARIANT,
        ZZZ;
        private final ResourceBundle mResourceBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(Dict.class) + "DictGame", Locale.getDefault());

        @Override
        public String toString() {
            return getString(mResourceBundle, name().toLowerCase());
        }
    }
}
