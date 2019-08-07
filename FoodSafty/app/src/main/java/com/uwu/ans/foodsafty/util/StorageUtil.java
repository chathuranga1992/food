package com.uwu.ans.foodsafty.util;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.uwu.ans.foodsafty.FoodApplication.sAppContext;


/**
 * Utility class responsible for accessing/saving application's
 * data
 * <p>
 * Created by Adriano Braga Alencar (adrianobragaalencar@gmail.com) on 27/08/15.
 */
public final class StorageUtil {

    public static final String PHOTO_NAME_TEMP = "IMG" + File.separator + "IMG_TEMP.jpg";
    public static final String PHOTO_FILE_PATH = File.separator + "IMG" + File.separator;
    private static final DateFormat FORMAT = new SimpleDateFormat("yyyyMMdd HHmmssSSSZ");
    private static final String PHOTO_NAME_FORMAT = "%s.jpg";
    private static final String AUDIO_NAME_FORMAT = "AUD" + File.separator + "ADU_%s.mp3";
    private static final String VIDEO_NAME_FORMAT = "VID_%s.mp4";
    private static final String TRIM_VIDEO_NAME_FORMAT = "VID" + File.separator + "VID_%s.mp4";
    private static final File DCIM_PATH = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    private static final File PICTURE_PATH = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

    private StorageUtil() {
        throw new AssertionError();
    }

    public static void deleteFile(String path) {
        final File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    public static String renameFile(String path, String filename) {
        final int extensionIndex = path.lastIndexOf('.');
        final String extension = extensionIndex == -1 ?
                StringUtils.EMPTY_STR :
                path.substring(extensionIndex);
        final File from = new File(path);
        String newFilePath = "";
        if (from.exists()) {
            filename += extension;
            from.setWritable(true);
            final File parent = from.getParentFile();
            final File to = parent == null ?
                    new File(filename) :
                    new File(parent, filename);
            newFilePath = to.getAbsolutePath();
            if (to.exists()) {

            } else {
                from.renameTo(to);
            }
        }
        return newFilePath;
    }

    public static void createUserDir(String userId) {
        final File appDir = sAppContext.getExternalCacheDir();
        if (appDir == null) {
            L.d("-----", "external cache dir not found");
        } else {
            final File file = new File(appDir, userId);
            createDirs(file);
            createDirs(new File(file, "IMG"));

        }
    }

    public static String getVideoPath(String userId) {
        File file = new File(DCIM_PATH, "/" + "Camera/" + getFormattedName(VIDEO_NAME_FORMAT));
        return file.getAbsolutePath();
    }

    public static String getVideoSavePath(String userId) {
        return getMediaPath(userId, VIDEO_NAME_FORMAT);
    }

    public static String getTrimVideoPath(String userId) {
        return getMediaPath(userId, TRIM_VIDEO_NAME_FORMAT);
    }

    public static String getAudioPath(String userId) {
        return getMediaPath(userId, AUDIO_NAME_FORMAT);
    }

    public static File getImagePath() {
        File file = new File(DCIM_PATH, "/" + "Camera/" + getFormattedName(PHOTO_NAME_FORMAT));
        return file;
    }

    public static String getPhotoNameFormat() {
        return getFormattedName(PHOTO_NAME_FORMAT);
    }

    private static String getPicturePath(String id) {
        File file = getUserDir(id);
        L.d("----getPicturePath--->>>", file.getPath());
        return file.getPath() + File.separator + "IMG";
    }

    public static File savePicture(String userId, byte data[], String sid) {
        final File userDir = getUserDir(userId);
        File file = null;
        if (userDir == null) {
            L.d("-----", "external cache dir not found");
        } else {
            final String filename = sid + ".jpg";
            L.d("-------->>>", filename + " --- " + sid);
            file = new File(userDir + PHOTO_FILE_PATH, filename);
            try {
                IOUtils.write(data, new FileOutputStream(file));
                return file;
            } catch (IOException e) {
                L.d("-----", "Save picture failed:" + e.getMessage());
            }
        }
        return file;
    }

    public static void savePicture(Uri path, byte data[]) {
        File file = new File(path.getPath());
        try {
            IOUtils.write(data, new FileOutputStream(file));
        } catch (IOException e) {
            L.d("-----", "Save picture failed:" + e.getMessage());
        }
    }

//    public static File savePictureToGallery(byte data[], int facing) {
//        File path = getImagePath();
//        Uri uri = null;
//        try {
//
//            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//            bitmap = flipIMage(bitmap, facing);
//
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
//            IOUtils.write(stream.toByteArray(), new FileOutputStream(path));
//
//            // File curFile = new File(path); // ... This is an image file from my device.
//        /*    Bitmap rotatedBitmap;
//
//            try {
//                ExifInterface exif = new ExifInterface(path.getPath());
//                int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//                int rotationInDegrees = exifToDegrees(rotation);
//                Matrix matrix = new Matrix();
//                if (rotation != 0f) {matrix.preRotate(rotationInDegrees);}
//                rotatedBitmap = Bitmap.createBitmap(bitmap,0,0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
//                IOUtils.write(outputStream.toByteArray(), new FileOutputStream(path));
//
//            }catch(IOException ex){
//                L.e("Td", ex.toString());
//            }*/
//
//            mediaScanner(path);
//            bitmap.recycle();
//
//        } catch (IOException e) {
//            L.d("-----", "Save picture failed:" + e.getMessage());
//        }
//        return path;
//    }

    private static int exifToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private static int fixOrientation(Bitmap bitmap) {
        if (bitmap.getWidth() > bitmap.getHeight()) {
            return 90;
        }
        return 0;
    }

//    public static Bitmap flipIMage(Bitmap bitmap, int facing) {
//        //Moustafa: fix issue of image reflection due to front camera settings
//        Matrix matrix = new Matrix();
//        int rotation = fixOrientation(bitmap);
//        if (facing == CameraView.FACING_FRONT) {
//            rotation = 270;
//        }
//
//        matrix.postRotate(rotation);
//        //matrix.preScale(-1, 1);
//        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//    }

    public static void mediaScanner(File path) {
        MediaScannerConnection.scanFile(sAppContext, new String[]{path.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String path, Uri uri) {

            }
        });
    }

    private static String getFormattedName(String format) {
        String path = String.format(format, FORMAT.format(new Date())).replace(" ", "_");
        return path.trim();
    }

    private static boolean createDirs(File file) {
        return file.exists() || file.mkdirs();
    }

    private static File getUserDir(String userId) {
        // Whenever user has tried to do any IO operation
        // we've must guarantee that whole user folders
        // structure exists
        createUserDir(userId);
        final File appDir = sAppContext.getExternalCacheDir();
        File userDir = null;
        if (appDir == null) {
            L.d("-----", "external cache dir not found");
        } else {
            userDir = new File(appDir, userId);
        }
        return userDir;
    }

    private static String getMediaPath(String userId, String format) {
        String filename = null;
        final File userDir = getUserDir(userId);
        if (userDir == null) {
            L.d("-----", "external cache dir not found");
        } else {
            final File file = new File(userDir, getFormattedName(format));
            filename = file.getAbsolutePath();
        }
        return filename;
    }

    public static File[] getAllFiles(String id) {
        String path = getPicturePath(id);
        File file = new File(path);
        return file.listFiles();
    }

    public static String getRealPathFromURIPath(Uri contentURI) {
        Cursor cursor = sAppContext.getContentResolver().query(contentURI, null,
                null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }


    public static Bitmap modifyOrientation(Bitmap bitmap, String image_absolute_path) throws IOException {
        ExifInterface ei = new ExifInterface(image_absolute_path);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotate(bitmap, 90);

            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotate(bitmap, 180);

            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotate(bitmap, 270);

            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                return flip(bitmap, true, false);

            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                return flip(bitmap, false, true);

            default:
                return bitmap;
        }
    }

    public static Bitmap rotate(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Matrix matrix = new Matrix();
        matrix.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
