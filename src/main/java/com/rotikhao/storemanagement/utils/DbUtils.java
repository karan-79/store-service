package com.rotikhao.storemanagement.utils;

import org.apache.logging.log4j.util.Supplier;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class DbUtils {

    public static <T> void updateIfChanged(Supplier<T> supplier,Supplier<T> newSup, Consumer<T> consumer) {
        if(supplier.get() != newSup.get()){
            consumer.accept(newSup.get());
        }
    }

}
