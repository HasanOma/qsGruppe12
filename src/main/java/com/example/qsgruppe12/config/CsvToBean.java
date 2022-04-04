package com.example.qsgruppe12.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public final class CsvToBean {
    @NotNull
    public static final CsvToBean.Companion Companion = new CsvToBean.Companion((DefaultConstructorMarker)null);

    @JvmStatic
    @NotNull
    public static final com.opencsv.bean.CsvToBean createCSVToBean(@Nullable BufferedReader fileReader, @NotNull Class beanClass) {
        return Companion.createCSVToBean(fileReader, beanClass);
    }

    @JvmStatic
    public static final void closeFileReader(@Nullable BufferedReader fileReader) throws Throwable {
        Companion.closeFileReader(fileReader);
    }

    @JvmStatic
    public static final void throwIfFileEmpty(@NotNull MultipartFile file) throws Throwable {
        Companion.throwIfFileEmpty(file);
    }

    public static final class Companion {
        @JvmStatic
        @NotNull
        public final com.opencsv.bean.CsvToBean createCSVToBean(@Nullable BufferedReader fileReader, @NotNull Class beanClass) {
            Intrinsics.checkNotNullParameter(beanClass, "beanClass");
            com.opencsv.bean.CsvToBean var10000 = (new CsvToBeanBuilder((Reader)fileReader)).withType(beanClass).withIgnoreLeadingWhiteSpace(true).build();
            Intrinsics.checkNotNullExpressionValue(var10000, "CsvToBeanBuilder<T>(file…\n                .build()");
            return var10000;
        }

        @JvmStatic
        public final void closeFileReader(@Nullable BufferedReader fileReader) throws Throwable {
            try {
                Intrinsics.checkNotNull(fileReader);
                fileReader.close();
            } catch (IOException var3) {
                throw (Throwable)(new RuntimeException("Error during csv import"));
            }
        }

        @JvmStatic
        public final void throwIfFileEmpty(@NotNull MultipartFile file) throws Throwable {
            Intrinsics.checkNotNullParameter(file, "file");
            if (file.isEmpty()) {
                throw (Throwable)(new RuntimeException("Empty file"));
            }
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
