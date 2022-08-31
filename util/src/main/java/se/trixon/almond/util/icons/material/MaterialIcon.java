/*
 * Copyright 2022 Patrik Karlström.
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
package se.trixon.almond.util.icons.material;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import se.trixon.almond.util.GraphicsHelper;
import se.trixon.almond.util.fx.FxHelper;

public class MaterialIcon {

    private static final Class cls = se.trixon.almond.util.icons.material.MaterialIcon.class;
    private static javafx.scene.paint.Color sDefaultColor = javafx.scene.paint.Color.BLACK;

    public static javafx.scene.paint.Color getDefaultColor() {
        return sDefaultColor;
    }

    public static void setDefaultColor(javafx.scene.paint.Color color) {
        sDefaultColor = color;
    }

    private static BufferedImage getBufferedImage(String dir, String baseName, int size, java.awt.Color color) {
        BufferedImage bi = null;

        try {
            bi = ImageIO.read(cls.getResource("%s/%s_white.png".formatted(dir, baseName.toLowerCase())));
            bi = GraphicsHelper.toBufferedImage(bi.getScaledInstance(size, size, Image.SCALE_SMOOTH));
            bi = GraphicsHelper.colorize(bi, color);
        } catch (IOException ex) {
            Logger.getLogger(MaterialIcon.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bi;
    }

    private static ImageIcon getImageIcon(String dir, String baseName, int size, java.awt.Color color) {
        BufferedImage bufferedImage = getBufferedImage(dir, baseName, size, color);
        return new ImageIcon(bufferedImage);
    }

    private static ImageView getImageView(String dir, String baseName, int size, javafx.scene.paint.Color color) {
        BufferedImage bufferedImage = getBufferedImage(dir, baseName, size, FxHelper.colorToColor(color));

        return new ImageView(SwingFXUtils.toFXImage(bufferedImage, null));
    }

    public enum _Action implements IconGetter {
        _3D_ROTATION,
        ACCESSIBILITY,
        ACCESSIBLE,
        ACCOUNT_BALANCE,
        ACCOUNT_BALANCE_WALLET,
        ACCOUNT_BOX,
        ACCOUNT_CIRCLE,
        ADD_SHOPPING_CART,
        ALARM,
        ALARM_ADD,
        ALARM_OFF,
        ALARM_ON,
        ALL_OUT,
        ANDROID,
        ANNOUNCEMENT,
        ASPECT_RATIO,
        ASSESSMENT,
        ASSIGNMENT,
        ASSIGNMENT_IND,
        ASSIGNMENT_LATE,
        ASSIGNMENT_RETURN,
        ASSIGNMENT_RETURNED,
        ASSIGNMENT_TURNED_IN,
        AUTORENEW,
        BACKUP,
        BOOK,
        BOOKMARK,
        BOOKMARK_BORDER,
        BUG_REPORT,
        BUILD,
        CACHED,
        CAMERA_ENHANCE,
        CARD_GIFTCARD,
        CARD_MEMBERSHIP,
        CARD_TRAVEL,
        CHANGE_HISTORY,
        CHECK_CIRCLE,
        CHROME_READER_MODE,
        CLASS,
        CODE,
        COMPARE_ARROWS,
        COPYRIGHT,
        CREDIT_CARD,
        DASHBOARD,
        DATE_RANGE,
        DELETE,
        DELETE_FOREVER,
        DESCRIPTION,
        DNS,
        DONE,
        DONE_ALL,
        DONUT_LARGE,
        DONUT_SMALL,
        EJECT,
        EURO_SYMBOL,
        EVENT,
        EVENT_SEAT,
        EXIT_TO_APP,
        EXPLORE,
        EXTENSION,
        FACE,
        FAVORITE,
        FAVORITE_BORDER,
        FEEDBACK,
        FIND_IN_PAGE,
        FIND_REPLACE,
        FINGERPRINT,
        FLIGHT_LAND,
        FLIGHT_TAKEOFF,
        FLIP_TO_BACK,
        FLIP_TO_FRONT,
        G_TRANSLATE,
        GAVEL,
        GET_APP,
        GIF,
        GRADE,
        GROUP_WORK,
        HELP,
        HELP_OUTLINE,
        HIGHLIGHT_OFF,
        HISTORY,
        HOME,
        HOURGLASS_EMPTY,
        HOURGLASS_FULL,
        HTTP,
        HTTPS,
        IMPORTANT_DEVICES,
        INFO,
        INFO_OUTLINE,
        INPUT,
        INVERT_COLORS,
        LABEL,
        LABEL_OUTLINE,
        LANGUAGE,
        LAUNCH,
        LIGHTBULB_OUTLINE,
        LINE_STYLE,
        LINE_WEIGHT,
        LIST,
        LOCK,
        LOCK_OPEN,
        LOCK_OUTLINE,
        LOYALTY,
        MARKUNREAD_MAILBOX,
        MOTORCYCLE,
        NOTE_ADD,
        OFFLINE_PIN,
        OPACITY,
        OPEN_IN_BROWSER,
        OPEN_IN_NEW,
        OPEN_WITH,
        PAGEVIEW,
        PAN_TOOL,
        PAYMENT,
        PERM_CAMERA,
        PERM_CONTACT_CALENDAR,
        PERM_DATA_SETTING,
        PERM_DEVICE_INFORMATION,
        PERM_IDENTITY,
        PERM_MEDIA,
        PERM_PHONE_MSG,
        PERM_SCAN_WIFI,
        PETS,
        PICTURE_IN_PICTURE,
        PICTURE_IN_PICTURE_ALT,
        PLAY_FOR_WORK,
        POLYMER,
        POWER_SETTINGS_NEW,
        PREGNANT_WOMAN,
        PRINT,
        QUERY_BUILDER,
        QUESTION_ANSWER,
        RECEIPT,
        RECORD_VOICE_OVER,
        REDEEM,
        REMOVE_SHOPPING_CART,
        REORDER,
        REPORT_PROBLEM,
        RESTORE,
        RESTORE_PAGE,
        ROOM,
        ROUNDED_CORNER,
        ROWING,
        SCHEDULE,
        SEARCH,
        SETTINGS,
        SETTINGS_APPLICATIONS,
        SETTINGS_BACKUP_RESTORE,
        SETTINGS_BLUETOOTH,
        SETTINGS_BRIGHTNESS,
        SETTINGS_CELL,
        SETTINGS_ETHERNET,
        SETTINGS_INPUT_ANTENNA,
        SETTINGS_INPUT_COMPONENT,
        SETTINGS_INPUT_COMPOSITE,
        SETTINGS_INPUT_HDMI,
        SETTINGS_INPUT_SVIDEO,
        SETTINGS_OVERSCAN,
        SETTINGS_PHONE,
        SETTINGS_POWER,
        SETTINGS_REMOTE,
        SETTINGS_VOICE,
        SHOP,
        SHOP_TWO,
        SHOPPING_BASKET,
        SHOPPING_CART,
        SPEAKER_NOTES,
        SPEAKER_NOTES_OFF,
        SPELLCHECK,
        STARS,
        STORE,
        SUBJECT,
        SUPERVISOR_ACCOUNT,
        SWAP_HORIZ,
        SWAP_VERT,
        SWAP_VERTICAL_CIRCLE,
        SYSTEM_UPDATE_ALT,
        TAB,
        TAB_UNSELECTED,
        THEATERS,
        THUMB_DOWN,
        THUMB_UP,
        THUMBS_UP_DOWN,
        TIMELINE,
        TOC,
        TODAY,
        TOLL,
        TOUCH_APP,
        TRACK_CHANGES,
        TRANSLATE,
        TRENDING_DOWN,
        TRENDING_FLAT,
        TRENDING_UP,
        TURNED_IN,
        TURNED_IN_NOT,
        UPDATE,
        VERIFIED_USER,
        VIEW_AGENDA,
        VIEW_ARRAY,
        VIEW_CAROUSEL,
        VIEW_COLUMN,
        VIEW_DAY,
        VIEW_HEADLINE,
        VIEW_LIST,
        VIEW_MODULE,
        VIEW_QUILT,
        VIEW_STREAM,
        VIEW_WEEK,
        VISIBILITY,
        VISIBILITY_OFF,
        WATCH_LATER,
        WORK,
        YOUTUBE_SEARCHED_FOR,
        ZOOM_IN,
        ZOOM_OUT;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Alert implements IconGetter {
        ADD_ALERT,
        ERROR,
        ERROR_OUTLINE,
        WARNING;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Av implements IconGetter {
        ADD_TO_QUEUE,
        AIRPLAY,
        ALBUM,
        ART_TRACK,
        AV_TIMER,
        BRANDING_WATERMARK,
        CALL_TO_ACTION,
        CLOSED_CAPTION,
        EQUALIZER,
        EXPLICIT,
        FAST_FORWARD,
        FAST_REWIND,
        FEATURED_PLAY_LIST,
        FEATURED_VIDEO,
        FIBER_DVR,
        FIBER_MANUAL_RECORD,
        FIBER_NEW,
        FIBER_PIN,
        FIBER_SMART_RECORD,
        FORWARD_10,
        FORWARD_30,
        FORWARD_5,
        GAMES,
        HD,
        HEARING,
        HIGH_QUALITY,
        LIBRARY_ADD,
        LIBRARY_BOOKS,
        LIBRARY_MUSIC,
        LOOP,
        MIC,
        MIC_NONE,
        MIC_OFF,
        MOVIE,
        MUSVIDEO,
        NEW_RELEASES,
        NOT_INTERESTED,
        NOTE,
        PAUSE,
        PAUSE_CIRCLE_FILLED,
        PAUSE_CIRCLE_OUTLINE,
        PLAY_ARROW,
        PLAY_CIRCLE_FILLED,
        PLAY_CIRCLE_OUTLINE,
        PLAYLIST_ADD,
        PLAYLIST_ADD_CHECK,
        PLAYLIST_PLAY,
        QUEUE,
        QUEUE_MUSIC,
        QUEUE_PLAY_NEXT,
        RADIO,
        RECENT_ACTORS,
        REMOVE_FROM_QUEUE,
        REPEAT,
        REPEAT_ONE,
        REPLAY,
        REPLAY_10,
        REPLAY_30,
        REPLAY_5,
        SHUFFLE,
        SKIP_NEXT,
        SKIP_PREVIOUS,
        SLOW_MOTION_VIDEO,
        SNOOZE,
        SORT_BY_ALPHA,
        STOP,
        SUBSCRIPTIONS,
        SUBTITLES,
        SURROUND_SOUND,
        VIDEO_CALL,
        VIDEO_LABEL,
        VIDEO_LIBRARY,
        VIDEOCAM,
        VIDEOCAM_OFF,
        VOLUME_DOWN,
        VOLUME_MUTE,
        VOLUME_OFF,
        VOLUME_UP,
        WEB,
        WEB_ASSET;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Communication implements IconGetter {
        BUSINESS,
        CALL,
        CALL_END,
        CALL_MADE,
        CALL_MERGE,
        CALL_MISSED,
        CALL_MISSED_OUTGOING,
        CALL_RECEIVED,
        CALL_SPLIT,
        CHAT,
        CHAT_BUBBLE,
        CHAT_BUBBLE_OUTLINE,
        CLEAR_ALL,
        COMMENT,
        CONTACT_MAIL,
        CONTACT_PHONE,
        CONTACTS,
        DIALER_SIP,
        DIALPAD,
        EMAIL,
        FORUM,
        IMPORT_CONTACTS,
        IMPORT_EXPORT,
        INVERT_COLORS_OFF,
        LIVE_HELP,
        LOCATION_OFF,
        LOCATION_ON,
        MAIL_OUTLINE,
        MESSAGE,
        NO_SIM,
        PHONE,
        PHONELINK_ERASE,
        PHONELINK_LOCK,
        PHONELINK_RING,
        PHONELINK_SETUP,
        PORTABLE_WIFI_OFF,
        PRESENT_TO_ALL,
        RING_VOLUME,
        RSS_FEED,
        SCREEN_SHARE,
        SPEAKER_PHONE,
        STAY_CURRENT_LANDSCAPE,
        STAY_CURRENT_PORTRAIT,
        STAY_PRIMARY_LANDSCAPE,
        STAY_PRIMARY_PORTRAIT,
        STOP_SCREEN_SHARE,
        SWAP_CALLS,
        TEXTSMS,
        VOICEMAIL,
        VPN_KEY;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Content implements IconGetter {
        ADD,
        ADD_BOX,
        ADD_CIRCLE,
        ADD_CIRCLE_OUTLINE,
        ARCHIVE,
        BACKSPACE,
        BLOCK,
        CLEAR,
        CONTENT_COPY,
        CONTENT_CUT,
        CONTENT_PASTE,
        CREATE,
        DELETE_SWEEP,
        DRAFTS,
        FILTER_LIST,
        FLAG,
        FONT_DOWNLOAD,
        FORWARD,
        GESTURE,
        INBOX,
        LINK,
        LOW_PRIORITY,
        MAIL,
        MARKUNREAD,
        MOVE_TO_INBOX,
        NEXT_WEEK,
        REDO,
        REMOVE,
        REMOVE_CIRCLE,
        REMOVE_CIRCLE_OUTLINE,
        REPLY,
        REPLY_ALL,
        REPORT,
        SAVE,
        SELECT_ALL,
        SEND,
        SORT,
        TEXT_FORMAT,
        UNARCHIVE,
        UNDO,
        WEEKEND;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Device implements IconGetter {
        ACCESS_ALARM,
        ACCESS_ALARMS,
        ACCESS_TIME,
        ADD_ALARM,
        AIRPLANEMODE_ACTIVE,
        AIRPLANEMODE_INACTIVE,
        BATTERY_20,
        BATTERY_30,
        BATTERY_50,
        BATTERY_60,
        BATTERY_80,
        BATTERY_90,
        BATTERY_ALERT,
        BATTERY_CHARGING_20,
        BATTERY_CHARGING_30,
        BATTERY_CHARGING_50,
        BATTERY_CHARGING_60,
        BATTERY_CHARGING_80,
        BATTERY_CHARGING_90,
        BATTERY_CHARGING_FULL,
        BATTERY_FULL,
        BATTERY_STD,
        BATTERY_UNKNOWN,
        BLUETOOTH,
        BLUETOOTH_CONNECTED,
        BLUETOOTH_DISABLED,
        BLUETOOTH_SEARCHING,
        BRIGHTNESS_AUTO,
        BRIGHTNESS_HIGH,
        BRIGHTNESS_LOW,
        BRIGHTNESS_MEDIUM,
        DATA_USAGE,
        DEVELOPER_MODE,
        DEVICES,
        DVR,
        GPS_FIXED,
        GPS_NOT_FIXED,
        GPS_OFF,
        GRAPHEQ,
        LOCATION_DISABLED,
        LOCATION_SEARCHING,
        NETWORK_CELL,
        NETWORK_WIFI,
        NFC,
        SCREEN_LOCK_LANDSCAPE,
        SCREEN_LOCK_PORTRAIT,
        SCREEN_LOCK_ROTATION,
        SCREEN_ROTATION,
        SD_STORAGE,
        SETTINGS_SYSTEM_DAYDREAM,
        SIGNAL_CELLULAR_0_BAR,
        SIGNAL_CELLULAR_1_BAR,
        SIGNAL_CELLULAR_2_BAR,
        SIGNAL_CELLULAR_3_BAR,
        SIGNAL_CELLULAR_4_BAR,
        SIGNAL_CELLULAR_CONNECTED_NO_INTERNET_0_BAR,
        SIGNAL_CELLULAR_CONNECTED_NO_INTERNET_1_BAR,
        SIGNAL_CELLULAR_CONNECTED_NO_INTERNET_2_BAR,
        SIGNAL_CELLULAR_CONNECTED_NO_INTERNET_3_BAR,
        SIGNAL_CELLULAR_CONNECTED_NO_INTERNET_4_BAR,
        SIGNAL_CELLULAR_NO_SIM,
        SIGNAL_CELLULAR_NULL,
        SIGNAL_CELLULAR_OFF,
        SIGNAL_WIFI_0_BAR,
        SIGNAL_WIFI_1_BAR,
        SIGNAL_WIFI_1_BAR_LOCK,
        SIGNAL_WIFI_2_BAR,
        SIGNAL_WIFI_2_BAR_LOCK,
        SIGNAL_WIFI_3_BAR,
        SIGNAL_WIFI_3_BAR_LOCK,
        SIGNAL_WIFI_4_BAR,
        SIGNAL_WIFI_4_BAR_LOCK,
        SIGNAL_WIFI_OFF,
        STORAGE,
        USB,
        WALLPAPER,
        WIDGETS,
        WIFI_LOCK,
        WIFI_TETHERING;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Editor implements IconGetter {
        ATTACH_FILE,
        ATTACH_MONEY,
        BORDER_ALL,
        BORDER_BOTTOM,
        BORDER_CLEAR,
        BORDER_COLOR,
        BORDER_HORIZONTAL,
        BORDER_INNER,
        BORDER_LEFT,
        BORDER_OUTER,
        BORDER_RIGHT,
        BORDER_STYLE,
        BORDER_TOP,
        BORDER_VERTICAL,
        BUBBLE_CHART,
        DRAG_HANDLE,
        FORMAT_ALIGN_CENTER,
        FORMAT_ALIGN_JUSTIFY,
        FORMAT_ALIGN_LEFT,
        FORMAT_ALIGN_RIGHT,
        FORMAT_BOLD,
        FORMAT_CLEAR,
        FORMAT_COLOR_FILL,
        FORMAT_COLOR_RESET,
        FORMAT_COLOR_TEXT,
        FORMAT_INDENT_DECREASE,
        FORMAT_INDENT_INCREASE,
        FORMAT_ITALIC,
        FORMAT_LINE_SPACING,
        FORMAT_LIST_BULLETED,
        FORMAT_LIST_NUMBERED,
        FORMAT_PAINT,
        FORMAT_QUOTE,
        FORMAT_SHAPES,
        FORMAT_SIZE,
        FORMAT_STRIKETHROUGH,
        FORMAT_TEXTDIRECTION_L_TO_R,
        FORMAT_TEXTDIRECTION_R_TO_L,
        FORMAT_UNDERLINED,
        FUNCTIONS,
        HIGHLIGHT,
        INSERT_CHART,
        INSERT_COMMENT,
        INSERT_DRIVE_FILE,
        INSERT_EMOTICON,
        INSERT_INVITATION,
        INSERT_LINK,
        INSERT_PHOTO,
        LINEAR_SCALE,
        MERGE_TYPE,
        MODE_COMMENT,
        MODE_EDIT,
        MONETIZATION_ON,
        MONEY_OFF,
        MULTILINE_CHART,
        PIE_CHART,
        PIE_CHART_OUTLINED,
        PUBLISH,
        SHORT_TEXT,
        SHOW_CHART,
        SPACE_BAR,
        STRIKETHROUGH_S,
        TEXT_FIELDS,
        TITLE,
        VERTICAL_ALIGN_BOTTOM,
        VERTICAL_ALIGN_CENTER,
        VERTICAL_ALIGN_TOP,
        WRAP_TEXT;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _File implements IconGetter {
        ATTACHMENT,
        CLOUD,
        CLOUD_CIRCLE,
        CLOUD_DONE,
        CLOUD_DOWNLOAD,
        CLOUD_OFF,
        CLOUD_QUEUE,
        CLOUD_UPLOAD,
        CREATE_NEW_FOLDER,
        FILE_DOWNLOAD,
        FILE_UPLOAD,
        FOLDER,
        FOLDER_OPEN,
        FOLDER_SHARED;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Hardware implements IconGetter {
        CAST,
        CAST_CONNECTED,
        COMPUTER,
        DESKTOP_MAC,
        DESKTOP_WINDOWS,
        DEVELOPER_BOARD,
        DEVICE_HUB,
        DEVICES_OTHER,
        DOCK,
        GAMEPAD,
        HEADSET,
        HEADSET_MIC,
        KEYBOARD,
        KEYBOARD_ARROW_DOWN,
        KEYBOARD_ARROW_LEFT,
        KEYBOARD_ARROW_RIGHT,
        KEYBOARD_ARROW_UP,
        KEYBOARD_BACKSPACE,
        KEYBOARD_CAPSLOCK,
        KEYBOARD_HIDE,
        KEYBOARD_RETURN,
        KEYBOARD_TAB,
        KEYBOARD_VOICE,
        LAPTOP,
        LAPTOP_CHROMEBOOK,
        LAPTOP_MAC,
        LAPTOP_WINDOWS,
        MEMORY,
        MOUSE,
        PHONE_ANDROID,
        PHONE_IPHONE,
        PHONELINK,
        PHONELINK_OFF,
        POWER_INPUT,
        ROUTER,
        SCANNER,
        SECURITY,
        SIM_CARD,
        SMARTPHONE,
        SPEAKER,
        SPEAKER_GROUP,
        TABLET,
        TABLET_ANDROID,
        TABLET_MAC,
        TOYS,
        TV,
        VIDEOGAME_ASSET,
        WATCH;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Image implements IconGetter {
        ADD_A_PHOTO,
        ADD_TO_PHOTOS,
        ADJUST,
        ASSISTANT,
        ASSISTANT_PHOTO,
        AUDIOTRACK,
        BLUR_CIRCULAR,
        BLUR_LINEAR,
        BLUR_OFF,
        BLUR_ON,
        BRIGHTNESS_1,
        BRIGHTNESS_2,
        BRIGHTNESS_3,
        BRIGHTNESS_4,
        BRIGHTNESS_5,
        BRIGHTNESS_6,
        BRIGHTNESS_7,
        BROKEN_IMAGE,
        BRUSH,
        BURST_MODE,
        CAMERA,
        CAMERA_ALT,
        CAMERA_FRONT,
        CAMERA_REAR,
        CAMERA_ROLL,
        CENTER_FOCUS_STRONG,
        CENTER_FOCUS_WEAK,
        COLLECTIONS,
        COLLECTIONS_BOOKMARK,
        COLOR_LENS,
        COLORIZE,
        COMPARE,
        CONTROL_POINT,
        CONTROL_POINT_DUPLICATE,
        CROP,
        CROP_16_9,
        CROP_3_2,
        CROP_5_4,
        CROP_7_5,
        CROP_DIN,
        CROP_FREE,
        CROP_LANDSCAPE,
        CROP_ORIGINAL,
        CROP_PORTRAIT,
        CROP_ROTATE,
        CROP_SQUARE,
        DEHAZE,
        DETAILS,
        EDIT,
        EXPOSURE,
        EXPOSURE_NEG_1,
        EXPOSURE_NEG_2,
        EXPOSURE_PLUS_1,
        EXPOSURE_PLUS_2,
        EXPOSURE_ZERO,
        FILTER,
        FILTER_1,
        FILTER_2,
        FILTER_3,
        FILTER_4,
        FILTER_5,
        FILTER_6,
        FILTER_7,
        FILTER_8,
        FILTER_9,
        FILTER_9_PLUS,
        FILTER_B_AND_W,
        FILTER_CENTER_FOCUS,
        FILTER_DRAMA,
        FILTER_FRAMES,
        FILTER_HDR,
        FILTER_NONE,
        FILTER_TILT_SHIFT,
        FILTER_VINTAGE,
        FLARE,
        FLASH_AUTO,
        FLASH_OFF,
        FLASH_ON,
        FLIP,
        GRADIENT,
        GRAIN,
        GRID_OFF,
        GRID_ON,
        HDR_OFF,
        HDR_ON,
        HDR_STRONG,
        HDR_WEAK,
        HEALING,
        IMAGE,
        IMAGE_ASPECT_RATIO,
        ISO,
        LANDSCAPE,
        LEAK_ADD,
        LEAK_REMOVE,
        LENS,
        LINKED_CAMERA,
        LOOKS,
        LOOKS_3,
        LOOKS_4,
        LOOKS_5,
        LOOKS_6,
        LOOKS_ONE,
        LOOKS_TWO,
        LOUPE,
        MONOCHROME_PHOTOS,
        MOVIE_CREATION,
        MOVIE_FILTER,
        MUSNOTE,
        NATURE,
        NATURE_PEOPLE,
        NAVIGATE_BEFORE,
        NAVIGATE_NEXT,
        PALETTE,
        PANORAMA,
        PANORAMA_FISH_EYE,
        PANORAMA_HORIZONTAL,
        PANORAMA_VERTICAL,
        PANORAMA_WIDE_ANGLE,
        PHOTO,
        PHOTO_ALBUM,
        PHOTO_CAMERA,
        PHOTO_FILTER,
        PHOTO_LIBRARY,
        PHOTO_SIZE_SELECT_ACTUAL,
        PHOTO_SIZE_SELECT_LARGE,
        PHOTO_SIZE_SELECT_SMALL,
        PICTURE_AS_PDF,
        PORTRAIT,
        REMOVE_RED_EYE,
        ROTATE_90_DEGREES_CCW,
        ROTATE_LEFT,
        ROTATE_RIGHT,
        SLIDESHOW,
        STRAIGHTEN,
        STYLE,
        SWITCH_CAMERA,
        SWITCH_VIDEO,
        TAG_FACES,
        TEXTURE,
        TIMELAPSE,
        TIMER,
        TIMER_10,
        TIMER_3,
        TIMER_OFF,
        TONALITY,
        TRANSFORM,
        TUNE,
        VIEW_COMFY,
        VIEW_COMPACT,
        VIGNETTE,
        WB_AUTO,
        WB_CLOUDY,
        WB_INCANDESCENT,
        WB_IRIDESCENT,
        WB_SUNNY;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Maps implements IconGetter {
        ADD_LOCATION,
        BEENHERE,
        DIRECTIONS,
        DIRECTIONS_BIKE,
        DIRECTIONS_BOAT,
        DIRECTIONS_BUS,
        DIRECTIONS_CAR,
        DIRECTIONS_RAILWAY,
        DIRECTIONS_RUN,
        DIRECTIONS_SUBWAY,
        DIRECTIONS_TRANSIT,
        DIRECTIONS_WALK,
        EDIT_LOCATION,
        EV_STATION,
        FLIGHT,
        HOTEL,
        LAYERS,
        LAYERS_CLEAR,
        LOCAL_ACTIVITY,
        LOCAL_AIRPORT,
        LOCAL_ATM,
        LOCAL_BAR,
        LOCAL_CAFE,
        LOCAL_CAR_WASH,
        LOCAL_CONVENIENCE_STORE,
        LOCAL_DINING,
        LOCAL_DRINK,
        LOCAL_FLORIST,
        LOCAL_GAS_STATION,
        LOCAL_GROCERY_STORE,
        LOCAL_HOSPITAL,
        LOCAL_HOTEL,
        LOCAL_LAUNDRY_SERVICE,
        LOCAL_LIBRARY,
        LOCAL_MALL,
        LOCAL_MOVIES,
        LOCAL_OFFER,
        LOCAL_PARKING,
        LOCAL_PHARMACY,
        LOCAL_PHONE,
        LOCAL_PIZZA,
        LOCAL_PLAY,
        LOCAL_POST_OFFICE,
        LOCAL_PRINTSHOP,
        LOCAL_SEE,
        LOCAL_SHIPPING,
        LOCAL_TAXI,
        MAP,
        MY_LOCATION,
        NAVIGATION,
        NEAR_ME,
        PERSON_PIN,
        PERSON_PIN_CIRCLE,
        PIN_DROP,
        PLACE,
        RATE_REVIEW,
        RESTAURANT,
        RESTAURANT_MENU,
        SATELLITE,
        STORE_MALL_DIRECTORY,
        STREETVIEW,
        SUBWAY,
        TERRAIN,
        TRAFFIC,
        TRAIN,
        TRAM,
        TRANSFER_WITHIN_A_STATION,
        ZOOM_OUT_MAP;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Navigation implements IconGetter {
        APPS,
        ARROW_BACK,
        ARROW_DOWNWARD,
        ARROW_DROP_DOWN,
        ARROW_DROP_DOWN_CIRCLE,
        ARROW_DROP_UP,
        ARROW_FORWARD,
        ARROW_UPWARD,
        CANCEL,
        CHECK,
        CHEVRON_LEFT,
        CHEVRON_RIGHT,
        CLOSE,
        EXPAND_LESS,
        EXPAND_MORE,
        FIRST_PAGE,
        FULLSCREEN,
        FULLSCREEN_EXIT,
        LAST_PAGE,
        MENU,
        MORE_HORIZ,
        MORE_VERT,
        REFRESH,
        SUBDIRECTORY_ARROW_LEFT,
        SUBDIRECTORY_ARROW_RIGHT,
        UNFOLD_LESS,
        UNFOLD_MORE;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Notification implements IconGetter {
        ADB,
        AIRLINE_SEAT_FLAT,
        AIRLINE_SEAT_FLAT_ANGLED,
        AIRLINE_SEAT_INDIVIDUAL_SUITE,
        AIRLINE_SEAT_LEGROOM_EXTRA,
        AIRLINE_SEAT_LEGROOM_NORMAL,
        AIRLINE_SEAT_LEGROOM_REDUCED,
        AIRLINE_SEAT_RECLINE_EXTRA,
        AIRLINE_SEAT_RECLINE_NORMAL,
        BLUETOOTH_AUDIO,
        CONFIRMATION_NUMBER,
        DISC_FULL,
        DO_NOT_DISTURB,
        DO_NOT_DISTURB_ALT,
        DO_NOT_DISTURB_OFF,
        DO_NOT_DISTURB_ON,
        DRIVE_ETA,
        ENHANCED_ENCRYPTION,
        EVENT_AVAILABLE,
        EVENT_BUSY,
        EVENT_NOTE,
        FOLDER_SPECIAL,
        LIVE_TV,
        MMS,
        MORE,
        NETWORK_CHECK,
        NETWORK_LOCKED,
        NO_ENCRYPTION,
        ONDEMAND_VIDEO,
        PERSONAL_VIDEO,
        PHONE_BLUETOOTH_SPEAKER,
        PHONE_FORWARDED,
        PHONE_IN_TALK,
        PHONE_LOCKED,
        PHONE_MISSED,
        PHONE_PAUSED,
        POWER,
        PRIORITY_HIGH,
        RV_HOOKUP,
        SD_CARD,
        SIM_CARD_ALERT,
        SMS,
        SMS_FAILED,
        SYNC,
        SYNC_DISABLED,
        SYNC_PROBLEM,
        SYSTEM_UPDATE,
        TAP_AND_PLAY,
        TIME_TO_LEAVE,
        VIBRATION,
        VOICE_CHAT,
        VPN_LOCK,
        WC,
        WIFI;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Places implements IconGetter {
        AC_UNIT,
        AIRPORT_SHUTTLE,
        ALL_INCLUSIVE,
        BEACH_ACCESS,
        BUSINESS_CENTER,
        CASINO,
        CHILD_CARE,
        CHILD_FRIENDLY,
        FITNESS_CENTER,
        FREE_BREAKFAST,
        GOLF_COURSE,
        HOT_TUB,
        KITCHEN,
        POOL,
        ROOM_SERVICE,
        RV_HOOKUP,
        SMOKE_FREE,
        SMOKING_ROOMS,
        SPA;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Social implements IconGetter {
        CAKE,
        DOMAIN,
        GROUP,
        GROUP_ADD,
        LOCATION_CITY,
        MOOD,
        MOOD_BAD,
        NOTIFICATIONS,
        NOTIFICATIONS_ACTIVE,
        NOTIFICATIONS_NONE,
        NOTIFICATIONS_OFF,
        NOTIFICATIONS_PAUSED,
        PAGES,
        PARTY_MODE,
        PEOPLE,
        PEOPLE_OUTLINE,
        PERSON,
        PERSON_ADD,
        PERSON_OUTLINE,
        PLUS_ONE,
        POLL,
        PUBL,
        SCHOOL,
        SENTIMENT_DISSATISFIED,
        SENTIMENT_NEUTRAL,
        SENTIMENT_SATISFIED,
        SENTIMENT_VERY_DISSATISFIED,
        SENTIMENT_VERY_SATISFIED,
        SHARE,
        WHATSHOT;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public enum _Toggle implements IconGetter {
        STAR,
        STAR_BORDER,
        STAR_HALF;

        @Override
        public ImageIcon getImageIcon(int size, java.awt.Color color) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageIcon getImageIcon(int size) {
            return MaterialIcon.getImageIcon(getClass().getSimpleName().toLowerCase(), name(), size, FxHelper.colorToColor(getDefaultColor()));
        }

        @Override
        public ImageView getImageView(int size, javafx.scene.paint.Color color) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, color);
        }

        @Override
        public ImageView getImageView(int size) {
            return MaterialIcon.getImageView(getClass().getSimpleName().toLowerCase(), name(), size, getDefaultColor());
        }
    }

    public interface IconGetter {

        public ImageIcon getImageIcon(int size, java.awt.Color color);

        public ImageIcon getImageIcon(int size);

        public ImageView getImageView(int size, javafx.scene.paint.Color color);

        public ImageView getImageView(int size);
    }

}
