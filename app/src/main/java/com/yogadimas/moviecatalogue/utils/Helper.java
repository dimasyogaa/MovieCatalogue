package com.yogadimas.moviecatalogue.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Helper {

    public static double formatDouble(double number) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        DecimalFormat df = new DecimalFormat("#.#", symbols);
        String hasil = df.format(number);
        return Double.parseDouble(hasil.replace(',', '.'));
    }

}
