package com.example.homemade.utils

class AppSharedFunctions {
    companion object {
//        fun checkEditTextIsEmpty(editText: String): Boolean {
//            if (editText.isNullOrEmpty()) {
//                 editText =  "Field is required"
//            }
//            return TextUtils.isEmpty(editText)
//        }

        fun checkPasswordIsMatch(editText: String, editTextSecond: String): Boolean {
            return editText == editTextSecond
        }
    }
}
