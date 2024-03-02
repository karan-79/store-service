package com.rotikhao.storemanagement.utils;

import java.util.UUID;

public class UUIDUtils {
    public static UUID uuid(String uuidString){
        return UUID.fromString(uuidString);
    }
}
