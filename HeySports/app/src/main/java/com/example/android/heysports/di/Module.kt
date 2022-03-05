package com.example.android.heysports.di

import com.example.android.heysports.di.part.RepositoryPart
import com.example.android.heysports.di.part.RetrofitPart
import com.example.android.heysports.di.part.ServicePart

/**
 * Created by Jihye Noh
 * Date: 2022-02-16
 */
val modules = listOf(
    RetrofitPart,
    ServicePart,
    RepositoryPart
)