package com.wparja.veterinaryreports.utils;

import android.os.Environment;

import com.wparja.veterinaryreports.persistence.entities.Report;

import java.io.File;

public class FileHelper {

    public static final String PHOTOS = "Photos/";
    public static final String FILES = "Files/";
    public static final String LOGS = "Logs/";
    public static final String ROOT_FOLDER = "/Veterinary Reports/";
    public static final String TEMP_FOLDER = "Temp/";
    public static final String TEMP_PREFIX_PHOTO_NAME = "temp_";
    public static final String PHOTO_EXTENSION = ".jpg";

    public static File getRootFolder() throws Exception {
        return createFolder(Environment.getExternalStorageDirectory(), ROOT_FOLDER);
    }

    public static File getTempFolder()throws Exception{
        return createFolder(getRootFolder(), TEMP_FOLDER);
    }

    public static File gePhotoFolder(String folderName) throws Exception {
        File mainFolder = createFolder(getRootFolder(), folderName);
        return createFolder(mainFolder, PHOTOS);
    }

    public static File geLogsFolder(String folderName) throws Exception {
        File mainFolder = createFolder(getRootFolder(), folderName);
        return createFolder(mainFolder, LOGS);
    }

    public static File getFilesFolder(String folderName) throws Exception {
        File mainFolder = createFolder(getRootFolder(), folderName);
        return createFolder(mainFolder, FILES);
    }

    private static File createFolder(File root, String folderName) throws Exception {
        File folder = new File(root, folderName);
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }

        if (!success) {
            throw new Exception();
        }

        return folder;
    }
}
