package com.example.my_personal_trainer

class NativeLib {
    companion object {
        init {
            System.loadLibrary("native-lib")
        }

        @JvmStatic external fun calculateDiffX(e2x: Float, itx: Float): Float
    }
}