package com.luiz.mobile.poctonegenerator

class Tone(var toneType: Int, var toneName: String, var toneDesc: String) {
    override fun toString(): String {
        return toneName
    }
}