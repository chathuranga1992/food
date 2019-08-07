package com.uwu.ans.foodsafty.util;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Patterns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rukshan Nawarathna : coolrukshan@gmail.com on 8/24/2016.
 */
public final class StringUtils {

    public static final String EMPTY_STR = "";
    private static final float costForMIN = 2.0f;
    private static double mWaitingCost = 0.0;

    private StringUtils() {
        throw new AssertionError();
    }

    public static boolean isEmpty(CharSequence sequence) {
        final String str = sequence == null ? null : sequence.toString();
        return (str == null) || (str.isEmpty()) || (str.trim().isEmpty());
    }

    /**
     * <p>Checks if a String is not empty ("") nor null nor blank space ("   ").</p>
     *
     * @param sequence the CharSequence to check, may be null
     * @return <code>true</code> if the String is not empty
     */
    public static boolean isNotEmpty(CharSequence sequence) {
        return !isEmpty(sequence);
    }

    public static boolean equals(String lh, String rh) {
        return lh == null ? rh == null : lh.equals(rh);
    }

    public static boolean equalsIgnoreCase(String lh, String rh) {
        return lh == null ? rh == null : lh.equalsIgnoreCase(rh);
    }

    public static boolean containsIgnoreCase(String value, String pattern) {
        return !((value == null) || (pattern == null)) && value.toLowerCase().contains(pattern.toLowerCase());
    }

    public static boolean startWithIgnoreCase(String value, String pattern) {
        return !((value == null) || (pattern == null)) && value.toLowerCase().startsWith(pattern);
    }

    public static float stringToFloat(String input) {
        return isEmpty(input) ? 0.0f : Float.parseFloat(input);
    }

    public static int stringToInt(String input) {
        return Integer.parseInt(input);
    }

    public static Double stringToDouble(String input) {
        return Double.parseDouble(input);
    }

    public static String formatDouble(double val) {
        return String.format("%.2f", val);
    }

    public static String formatRating(double val) {
        return String.format("%.1f", val);
    }

    public static String toTitleCase(String str) {
        if (str == null) {
            return null;
        }
        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public final static boolean isValidPhone(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.PHONE.matcher(target).matches();
        }
    }

    public static String getCurrentDate() {
        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return parseFormat.format(date);
    }

    public static String getCurrentDateTime() {
        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        return parseFormat.format(date);
    }

    public static String getCurrentDateTime(Date date) {
        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return parseFormat.format(date);
    }

    public static int compareDate(String date) {
        String currentDate = getCurrentDate();
        return currentDate.compareTo(date);
    }

    public static String getDDateFromString(String dateF) {
        dateF = dateF.replace("T", " ");
        // SimpleDateFormat parseFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat parseFormat = new SimpleDateFormat("d MMM");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = null;
        try {
            date = dateFormat.parse(dateF);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseFormat.format(date);
        //return dateFormat.format(date);
    }

    public static String getTimeFromDate(String dateF) {
        dateF = dateF.replace("T", " ");
        // SimpleDateFormat parseFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat parseFormat = new SimpleDateFormat("HH:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //parseFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(dateF);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseFormat.format(date);
        //return dateFormat.format(date);
    }

    public static String getTimeFromDateWithTZ(String dateF) {
        //dateF = dateF.replace("T", " ");
        // SimpleDateFormat parseFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat parseFormat = new SimpleDateFormat("HH:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        parseFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = dateFormat.parse(dateF);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseFormat.format(date);
        //return dateFormat.format(date);
    }

    public static String getTimeFromString(String dateF) {
        dateF = dateF.replace("T", " ");
        //SimpleDateFormat parseFormat = new SimpleDateFormat("dd-MM-yy HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = dateFormat.parse(dateF);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // return parseFormat.format(date);
        return dateFormat.format(date);
    }

    public static Date getDateFromString(String dateF) {
        if (dateF.contains("T")) {
            dateF = dateF.replace("T", " ");
        }
        //SimpleDateFormat parseFormat = new SimpleDateFormat("MMMM dd,yyyy   hh:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        Date date = null;
        try {
            date = dateFormat.parse(dateF);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static SpannableString formatString(String input, int start, int end, float size, int color) {
        SpannableString ss = new SpannableString(input);
        ss.setSpan(new RelativeSizeSpan(size), start, end, 0); // set size
        ss.setSpan(new ForegroundColorSpan(color), start, end, 0);// set color
        return ss;
    }


    public static String textToTitleCase(String input) {
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        if (words[0].length() > 0) {
            sb.append(Character.toUpperCase(words[0].charAt(0))).append(words[0].subSequence(1, words[0].length()).toString().toLowerCase());
            for (int i = 1; i < words.length; i++) {
                sb.append(" ");
                sb.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].subSequence(1, words[i].length()).toString().toLowerCase());
            }
        }
        return sb.toString();
    }

    public static String getFormatPrice(String price) {
        return "Rs " + price;
    }

    public static String getDropOffText() {
        String text = "<font color=#969696>Enter </font> <font color=#e53e2c>Drop of</font><font color=#969696> Location</font>";
        return text;
    }

    public static String getTimeZone() {

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        L.d("Time zone", "=" + tz.getID());

        return tz.getID();
    }


    public static String getDummyImage() {
        return "http://www2.pictures.zimbio.com/mp/3bDNl40zuqyl.jpg";
    }

    public static long getTimeDifference(String start, String end) {
        String time = "";

        L.d("-----dif---->>>", start + " --- " + end);

        try {
            Date dateStart = getDateFromString(start);
            Date dateEnd = getDateFromString(end);

            long different = dateEnd.getTime() - dateStart.getTime();

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

           /* long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;
            if (elapsedHours != 0) {
                time = elapsedHours + " hours ";
            }*/

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;
            if (elapsedMinutes != 0) {
                time += elapsedMinutes + " minutes ";
            }

            long elapsedSeconds = different / secondsInMilli;
            if (elapsedSeconds != 0) {
                time += elapsedSeconds + " seconds ";
            }

            return elapsedMinutes;
        } catch (NullPointerException e) {

        }


        return 0;
    }

    public static String extractYTId(String ytUrl) {
        String vId = null;
        Pattern pattern = Pattern.compile(
                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()) {
            vId = matcher.group(1);
        }
        return vId;
    }


}

