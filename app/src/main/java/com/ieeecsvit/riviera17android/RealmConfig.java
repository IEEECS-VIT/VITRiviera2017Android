package com.ieeecsvit.riviera17android;

import io.realm.RealmConfiguration;

/**
 * Created by Karishnu Poddar on 12/01/2017.
 */

public class RealmConfig {

    private static RealmConfiguration instance = null;
    private RealmConfig() {
        // Exists only to defeat instantiation.
    }

    public static RealmConfiguration getInstance() {
        if(instance == null) {
            instance = new RealmConfiguration.Builder()
                    .schemaVersion(1)
                    .migration(new Migration())
                    .build();
        }
        return instance;
    }
}