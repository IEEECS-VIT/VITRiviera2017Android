package com.ieeecsvit.riviera17android;

/**
 * Created by Karishnu Poddar on 12/01/2017.
 */

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Example of migrating a Realm file from version 0 (initial version) to its last version (version 3).
 */
public class Migration implements RealmMigration {

    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            RealmObjectSchema eventSchema = schema.get("Event");
            eventSchema.removeField("eventRating");
            oldVersion++;
        }
    }

    @Override
    public int hashCode() {
        return 37;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Migration);
    }
}