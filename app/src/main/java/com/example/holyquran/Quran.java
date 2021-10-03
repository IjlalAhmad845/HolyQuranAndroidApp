package com.example.holyquran;

public class Quran {
    String Arabic;
    String Translation;

    public Quran(String arabic, String translation) {
        Arabic = arabic;
        Translation = translation;
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
