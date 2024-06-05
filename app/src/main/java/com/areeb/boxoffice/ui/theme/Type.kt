package com.areeb.boxoffice.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


// Set of Material typography styles to start with
val Typography = Typography(

    titleLarge = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        fontSize = 18.sp,
    ),

    titleMedium = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        fontSize = 16.sp,
    ),

    titleSmall = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 14.sp,
    ),


    labelLarge = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 16.sp,
    ),

    labelMedium = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.SemiBold,
        color = AmericanOrange,
        fontSize = 12.sp,
    ),


    labelSmall = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 12.sp,
    ),


    displaySmall = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 14.sp,
    ),


    headlineLarge = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Medium,
        color = Color.White,
        fontSize = 12.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 12.sp,
    ),



    )