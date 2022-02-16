package com.example.holyquran;

public class Quran {
    String Arabic;
    String Translation;
    String ByChapter;
    String ByPages;
    int VerseCount;

    public Quran(String arabic, String translation, String byChapter, String byPages,int verseCount) {
        Arabic = arabic;
        Translation = translation;
        ByChapter = byChapter;
        ByPages = byPages;
        VerseCount=verseCount;
    }

    public int getVerseCount() {
        return VerseCount;
    }

    public void setVerseCount(int verseCount) {
        VerseCount = verseCount;
    }
    public String getByPages() {
        return ByPages;
    }

    public void setByPages(String byPages) {
        ByPages = byPages;
    }

    public String getByChapter() {
        return ByChapter;
    }

    public void setByChapter(String byChapter) {
        ByChapter = byChapter;
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
