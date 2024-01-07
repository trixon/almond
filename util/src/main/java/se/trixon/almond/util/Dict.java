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
    ACTIVE_ALT,
    ADD,
    ADD_BOOKMARK,
    ADD_ONS,
    ADD_REMOVE,
    ADMIN,
    ADMINISTRATION,
    ADMINISTRATOR,
    ADVANCED,
    AFTER,
    AGE,
    ALL,
    ALL_FILES,
    ALTITUDE,
    ALWAYS_ON_TOP,
    ANALYZE,
    APPEARANCE,
    APPEND,
    APPLICATION,
    APPLICATION_NEWS,
    APPLICATION_TOOLS,
    APPLY,
    ARCHIVE,
    AUTHORS,
    AUTOMATIC,
    AUTOMATIC_UPDATING,
    AVAILABLE,
    BACKGROUND,
    BACKGROUND_COLOR,
    BACKGROUND_IMAGE,
    BASENAME,
    BASIC,
    BATCH,
    BEARING,
    BEFORE,
    BOOKMARK,
    BOOKMARKS,
    BOOKMARKS_SEARCH,
    BORDER,
    BORDER_SIZE,
    BROWSE,
    CACHE,
    CACHING,
    CALENDAR_LANGUAGE,
    CANCEL,
    CANCELED,
    CATEGORY,
    CHANGE,
    CHANGE_TO,
    CHART,
    CLEAR,
    CLEAR_COMMENTS,
    CLEAR_SELECTION,
    CLIENT,
    CLONE,
    CLOSE,
    CLOSE_ALL,
    CLOSE_WINDOW,
    COLLAPSE,
    COLOR,
    COMMAND,
    COMMAND_NOT_FOUND,
    COMMAND_NOT_FOUND_S,
    COMMANDS,
    COMMENT,
    COMPARE,
    COMPONENT,
    CONFIGURATION,
    CONNECT,
    CONNECTION,
    CONNECTION_LOST,
    CONNECTION_LOST_SERVER_SHUTDOWN,
    CONNECT_TO_HOST,
    CONNECT_TO_SERVER,
    CONTAINS,
    COORDINATE,
    COORDINATE_FILE,
    COORDINATE_FILES,
    COORDINATE_LIST,
    COPY,
    COPY_ALL,
    COPY_NAMES,
    COPY_SELECTION,
    COPYRIGHT,
    CREDIT,
    CUSTOMIZE,
    CUSTOMIZED,
    CUSTOM_TEXT,
    CUSTOM_TIME_ZONE,
    DARK,
    DATABASE,
    DATA_SOURCES,
    DATE,
    DATE_PATTERN,
    DATE_SOURCE,
    DEACTIVATE,
    DECODE,
    DEFAULT,
    DEFAULT_VALUE,
    DELETIONS,
    DESCRIPTION,
    DESTINATION,
    DETAILS,
    DIRECTORIES,
    DIRECTORY,
    DIRECTORY_MAINTENANCE,
    DIRECTORY_NOT_FOUND,
    DIRECTORY_NOT_FOUND_DEST,
    DIRECTORY_NOT_FOUND_SOURCE,
    DISABLE,
    DISABLED,
    DISCONNECT,
    DOCUMENT,
    DOCUMENTATION,
    DONE,
    DOWNLOAD,
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
    EDITOR,
    EDITORS,
    ELEVATION,
    EMPTY,
    ENABLE,
    ENABLED,
    ENCODE,
    ENDS_WITH,
    ENVIRONMENT,
    EVENT,
    EVENTS,
    EVERY,
    EXCLUDE,
    EXPAND,
    EXPORT,
    EXTENSION,
    EXTENSION_FILTER_LOG,
    EXTERNAL_FILE,
    EXTERNAL,
    EXTERNAL_ALT,
    FAILED,
    FILE,
    FILELIST_EMPTY,
    FILENAME,
    FILES,
    FILE_MENU,
    FILE_OPENER,
    FILE_PATTERN,
    FILE_REFERENCE,
    FILTER,
    FIRST,
    FOLDER,
    FOLDERS,
    FOLLOW_LINKS,
    FONT,
    FOREGROUND,
    FROM,
    FULL_SCREEN,
    GENERAL,
    GENERATING_FILELIST,
    GLOBAL,
    GLOBAL_OPTIONS,
    GRAPHICS,
    GROUP,
    HELP,
    HIDE,
    HIDE_ALL,
    HISTORY,
    HITS,
    HOME,
    HOST,
    ICON,
    ICONS,
    IMAGE,
    IMAGE_DIRECTORY,
    IMAGE_SIZE,
    IMPORT,
    INCLUDE,
    INDICATOR,
    INDICATORS,
    INFORMATION,
    INTERNAL,
    INTERNAL_ALT,
    INTERVAL,
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
    ITEM,
    ITEMS,
    JOB,
    JOBS,
    JOB_ALREADY_RUNNING,
    JOB_CANCELLED_S,
    TASK_CANCELLED_S,
    JOB_FAILED,
    JOB_FINISHED,
    JOB_INTERRUPTED,
    JOB_NOT_FOUND,
    JOB_NOT_RUNNING,
    JOB_PROPERTIES,
    KEY,
    KILOMETERS,
    LABEL,
    LAST,
    LATEST,
    LATITUDE,
    LAYER,
    LAYERS,
    LAYER_SEARCH,
    LIBRARIES,
    LICENSE,
    LIGHT,
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
    LOWER,
    MADE_BY,
    MADE_IN,
    MADE_WITH,
    MAINTENANCE,
    MANUAL,
    MAP,
    MAP_TYPE_HYBRID,
    MAP_TYPE_ROADMAP,
    MAP_TYPE_SATELLITE,
    MAP_TYPE_TERRAIN,
    MASK,
    MATERIAL_BLACK,
    MATERIAL_WHITE,
    MAX_HEIGHT,
    MAX_WIDTH,
    MEASURE,
    MENU,
    MESSAGE,
    MESSAGES,
    META,
    METADATA,
    METADATA_FILE,
    METADATA_FILES,
    METERS,
    MISCELLANEOUS,
    MODE,
    MODULE,
    MODULES,
    MOVE,
    MOVEMENT,
    MOVE_BOTTOM,
    MOVE_DOWN,
    MOVE_TOP,
    MOVE_UP,
    NAME,
    NEED,
    NEW,
    NEWS,
    NEXT,
    NIGHT_MODE,
    NO,
    NONE,
    NOTE,
    NOTES,
    NO_BOOKMARKS,
    NUM_OF_S,
    OBJECT,
    OBJECT_PROPERTIES,
    OK,
    OPACITY,
    OPEN,
    OPENING_S,
    OPEN_DIRECTORY,
    OPEN_IN_WEB_BROWSER,
    OPERATION,
    OPERATION_COMPLETED,
    OPERATION_INTERRUPTED,
    OPTIONS,
    ORGANIZATION,
    ORIGINAL,
    OUTPUT,
    OUT_OF_BOUNDS,
    OWNERSHIP,
    PALETTE,
    PASSWORD,
    PASTE,
    PATH,
    PATIENCE_IS_A_VIRTUE,
    PATTERN,
    PATTERNS,
    PHOTO,
    PHOTOS,
    PIN,
    PLACEMARK,
    PLACE_NAME,
    PLACE_NAMES,
    PLATFORM,
    PLAY,
    PLEASE_WAIT,
    PLOT,
    PLUGINS,
    POLYGON,
    PORT,
    PREFERENCES,
    PRESETS,
    PREVIEW,
    PREVIOUS,
    PROCESSING,
    PROFILE,
    PROFILES,
    PROJECT,
    PROPERTIES,
    PROPERTY,
    QUALIFIER,
    QUIT,
    RANDOM,
    READY,
    READ_ONLY,
    RECURSIVE,
    REDO,
    REFERENCE,
    REFRESH,
    REGENERATE,
    RELATIVE,
    REMOVE,
    REMOVE_ALL,
    RENAME,
    RENDER,
    RENDERING,
    REPLACE,
    REPORT,
    REPORTS,
    RESET,
    RESET_WINDOWS,
    RESTART,
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
    SAVE_ALL,
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
    SELECTION,
    SELECT_ALL,
    SELECT_COLOR,
    SEPARATOR,
    SERVER,
    SERVER_SHUTDOWN,
    SERVER_START,
    SHOW,
    SHOW_ALL,
    SHOW_THIS_AGAIN,
    SHUTDOWN,
    SHUTDOWN_AND_QUIT,
    SHUTDOWN_SERVER_AND_QUIT,
    SHUTDOWN_SERVER_AND_WINDOW,
    SHUTDOWN_SERVICE,
    SIZE,
    SOURCE,
    SOURCES,
    SOURCE_AND_DEST,
    SPLIT_BY,
    START,
    STARTED,
    STARTS_WITH,
    STATIC,
    STATUS,
    STOP,
    STOPPED,
    STOP_ON_ERROR,
    STYLE,
    SUBDIRECTORIES,
    SYMBOL,
    SYMBOLS,
    SYSTEM,
    SYSTEM_ADMINISTRATION,
    SYSTEM_CODE,
    SYSTEM_INFORMATION,
    SYSTEM_TIME_ZONE,
    TAB_CLOSE,
    TAB_CLOSE_ALL,
    TAB_CLOSE_OTHERS,
    TAB_CLOSE_TO_LEFT,
    TAB_CLOSE_TO_RIGHT,
    TAB_NEW,
    TAG,
    TAGS,
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
    TEMPORARY,
    TEMPORARY_CONTENT,
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
    TOGGLE_FULL_SCREEN,
    TOOLBARS,
    TOOLBOX,
    TOOLS,
    TOOLS_SEARCH,
    TO_MANY_CLIENTS,
    TRACK,
    TRACKS,
    TRANSLATION,
    TYPE,
    UNCATEGORISED,
    UNDO,
    UNHANDLED_ERROR,
    UNHANDLED_EXCEPTION,
    UNIQUE,
    UNTITLED,
    UP,
    UPDATE,
    UPDATED_S,
    UPDATER,
    UPDATE_MANAGER,
    UPLOAD,
    UPPER,
    USER,
    USERNAME,
    VALID,
    VALUE,
    VERIFY,
    VERSION,
    VIEW,
    WARMING_UP,
    WARNING,
    WINDOW,
    WINDOW_ALREADY_OPEN,
    YES,
    ZOOM,
    ZOOM_EXTENTS;

    private final ResourceBundle mResourceBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(Dict.class) + "Dict", Locale.getDefault());

    private static String getString(ResourceBundle bundle, String key) {
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        } else {
            return "Key not found: " + key;
        }
    }

    public String formatted(Object... args) {
        return toString().formatted(args);
    }

    public String toLower() {
        return toString().toLowerCase(Locale.ROOT);
    }

    @Override
    public String toString() {
        return getString(mResourceBundle, name().toLowerCase(Locale.ROOT));
    }

    public String toUpper() {
        return toString().toUpperCase(Locale.ROOT);
    }

    public enum Dialog {
        ACTION_CANT_BE_UNDONE,
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
        MESSAGE_BOOKMARK_REMOVE_ALL,
        MESSAGE_DELETE_OBJECT,
        MESSAGE_DELETE_OBJECTS,
        MESSAGE_FILE_CLOSE,
        MESSAGE_FILE_CLOSE_ALL,
        MESSAGE_FILE_EXISTS,
        MESSAGE_FILE_NOT_FOUND,
        MESSAGE_HELP_NOT_FOUND,
        MESSAGE_JOB_RUNNING,
        MESSAGE_NO_PROFILES_FOUND,
        MESSAGE_OBJECT_REMOVE_ALL,
        MESSAGE_PROFILE_REMOVE,
        MESSAGE_PROFILE_REMOVE_ALL,
        MESSAGE_REMOVE_DIR,
        MESSAGE_REMOVE_DIRS,
        MESSAGE_REMOVE_FILE,
        MESSAGE_REMOVE_FILES,
        MESSAGE_RESTART_REQUIRED,
        MESSAGE_ROWS_NOT_SELECTED,
        MESSAGE_TASK_RUNNING,
        MESSAGE_WRONG_PASSWORD,
        TITLE_ADD_IMAGE,
        TITLE_ADD_IMAGES,
        TITLE_BOOKMARK_REMOVE,
        TITLE_BOOKMARK_REMOVE_ALL,
        TITLE_CLOSE_S,
        TITLE_CONFIRM_DELETE,
        TITLE_DELETE_OBJECT,
        TITLE_DELETE_OBJECTS,
        TITLE_EDIT_PROPERTIES,
        TITLE_FILE_CLOSE,
        TITLE_FILE_CLOSE_ALL,
        TITLE_FILE_EXISTS,
        TITLE_FILE_NOT_FOUND,
        TITLE_HELP_NOT_FOUND,
        TITLE_IO_ERROR,
        TITLE_JOB_RUNNING,
        TITLE_OBJECT_REMOVE_ALL,
        TITLE_PROFILE_ADD,
        TITLE_PROFILE_CLONE,
        TITLE_PROFILE_REMOVE,
        TITLE_PROFILE_REMOVE_ALL,
        TITLE_PROFILE_RENAME,
        TITLE_PROFILE_RUN,
        TITLE_REMOVE_ALL_S,
        TITLE_REMOVE_DIR,
        TITLE_REMOVE_DIRS,
        TITLE_REMOVE_FILE,
        TITLE_REMOVE_FILES,
        TITLE_REMOVE_S,
        TITLE_RESTART_REQUIRED,
        TITLE_ROWS_NOT_SELECTED,
        TITLE_SELECT_COLOR,
        TITLE_TASK_RUNNING,
        TITLE_TASK_RUN_S,
        YOU_ARE_ABOUT_TO_S;
        private final ResourceBundle mResourceBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(Dict.class) + "DictDialog", Locale.getDefault());

        public String toLower() {
            return toString().toLowerCase(Locale.ROOT);
        }

        @Override
        public String toString() {
            return getString(mResourceBundle, name().toLowerCase(Locale.ROOT));
        }

        public String toUpper() {
            return toString().toUpperCase(Locale.ROOT);
        }
    }

    public enum Geometry {
        ANGLE,
        AREA,
        BEARING,
        CENTER,
        CIRCLE,
        DIAMETER,
        DIRECTION_C,
        DIRECTION_N,
        DIRECTION_NE,
        DIRECTION_E,
        DIRECTION_SE,
        DIRECTION_S,
        DIRECTION_SW,
        DIRECTION_W,
        DIRECTION_NW,
        ELLIPSE,
        GEOMETRIES,
        GEOMETRY,
        HEIGHT,
        LENGTH,
        LINE,
        PATH,
        PATHS,
        PATH_GAP,
        PLANE,
        PERIMETER,
        POINT,
        POLYGON,
        RADIUS,
        RECTANGLE,
        SHAPE,
        SQUARE,
        SURFACE,
        WIDTH;
        private final ResourceBundle mResourceBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(Dict.class) + "DictGeometry", Locale.getDefault());

        public String toLower() {
            return toString().toLowerCase(Locale.ROOT);
        }

        @Override
        public String toString() {
            return getString(mResourceBundle, name().toLowerCase(Locale.ROOT));
        }

        public String toUpper() {
            return toString().toUpperCase(Locale.ROOT);
        }
    }

    public enum Time {
        CURRENT,
        DATE,
        DAY,
        DAYS,
        HOUR,
        HOURS,
        MAX_AGE,
        MINUTE,
        MINUTES,
        MONTH,
        MONTHS,
        SECOND,
        SECONDS,
        TIME,
        TODAY,
        WEEK,
        WEEKS,
        YEAR,
        YEARS;
        private final ResourceBundle mResourceBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(Dict.class) + "DictTime", Locale.getDefault());

        public String toLower() {
            return toString().toLowerCase(Locale.ROOT);
        }

        @Override
        public String toString() {
            return getString(mResourceBundle, name().toLowerCase(Locale.ROOT));
        }

        public String toUpper() {
            return toString().toUpperCase(Locale.ROOT);
        }
    }

    public enum Game {
        ARCADE,
        BOARD,
        CARD,
        DICE,
        GAME,
        GAME_OVER,
        GAME_SELECTOR,
        GAME_TYPE,
        GOAL,
        GO_HOME,
        HOLD,
        INSTALLED_GAMES,
        INSTALL_GAMES,
        LEVEL,
        NEW_ROUND,
        NO_INSTALLED_GAMES,
        NUMBER_OF_PLAYERS,
        PLAYER,
        PLAYERS,
        PLAY_SOUND,
        RESULT,
        SCORECARD,
        SELECT_PLAYER,
        SHUFFLE,
        STRATEGY,
        VARIANT;
        private final ResourceBundle mResourceBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(Dict.class) + "DictGame", Locale.getDefault());

        public String toLower() {
            return toString().toLowerCase(Locale.ROOT);
        }

        @Override
        public String toString() {
            return getString(mResourceBundle, name().toLowerCase(Locale.ROOT));
        }

        public String toUpper() {
            return toString().toUpperCase(Locale.ROOT);
        }
    }
}
