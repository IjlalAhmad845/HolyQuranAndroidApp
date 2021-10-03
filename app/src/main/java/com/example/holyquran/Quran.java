package com.example.holyquran;

public class Quran {
    String Arabic;
    String Translation;
    String Chapter;

    public Quran(String arabic, String translation) {
        Arabic = arabic;
        Translation = translation;
    }

    public Quran(String chapters) {
        Chapter = chapters;
    }

    public String getChapter() {
        return Chapter;
    }

    public void setChapter(String chapter) {
        Chapter = chapter;
    }

    public String getArabic() {
        return Arabic;
    }

    public void setArabic(String arabic) {
        Arabic = arabic;
    }

    public String getTranslation() {
        return Translation;
    }

    public void setTranslation(String translation) {
        Translation = translation;
    }
}
