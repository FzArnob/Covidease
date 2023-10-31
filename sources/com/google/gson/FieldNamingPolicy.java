package com.google.gson;

public enum FieldNamingPolicy implements FieldNamingStrategy {
    ;

    static String separateCamelCase(String str, String str2) {
        StringBuilder sb;
        String name = str;
        String separator = str2;
        new StringBuilder();
        StringBuilder translation = sb;
        int length = name.length();
        for (int i = 0; i < length; i++) {
            char character = name.charAt(i);
            if (Character.isUpperCase(character) && translation.length() != 0) {
                StringBuilder append = translation.append(separator);
            }
            StringBuilder append2 = translation.append(character);
        }
        return translation.toString();
    }

    static String upperCaseFirstLetter(String str) {
        StringBuilder sb;
        String name = str;
        new StringBuilder();
        StringBuilder fieldNameBuilder = sb;
        int index = 0;
        char firstCharacter = name.charAt(0);
        int length = name.length();
        while (index < length - 1 && !Character.isLetter(firstCharacter)) {
            StringBuilder append = fieldNameBuilder.append(firstCharacter);
            index++;
            firstCharacter = name.charAt(index);
        }
        if (Character.isUpperCase(firstCharacter)) {
            return name;
        }
        return fieldNameBuilder.append(modifyString(Character.toUpperCase(firstCharacter), name, index + 1)).toString();
    }

    private static String modifyString(char c, String str, int i) {
        String valueOf;
        StringBuilder sb;
        char firstCharacter = c;
        String srcString = str;
        int indexOfSubstring = i;
        if (indexOfSubstring < srcString.length()) {
            new StringBuilder();
            valueOf = sb.append(firstCharacter).append(srcString.substring(indexOfSubstring)).toString();
        } else {
            valueOf = String.valueOf(firstCharacter);
        }
        return valueOf;
    }
}
