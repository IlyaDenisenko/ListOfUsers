package com.task.listofusers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersModel(val info: Info, val results: MutableList<Result>): Parcelable

@Parcelize
data class Info (
    val seed: String,
    val results: Long,
    val page: Long,
    val version: String
): Parcelable

@Parcelize
data class Result (
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: Dob,
    val registered: Dob,
    val phone: String,
    val cell: String,
    val id: ID,
    val picture: Picture,
    val nat: String
): Parcelable

@Parcelize
data class Dob (
    val date: String,
    val age: Long
): Parcelable

@Parcelize
data class ID (
    val name: String,
    val value: String?
): Parcelable

@Parcelize
data class Location (
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: Coordinates,
    val timezone: Timezone
): Parcelable

@Parcelize
data class Coordinates (
    val latitude: String,
    val longitude: String
): Parcelable

@Parcelize
data class Street (
    val number: Long,
    val name: String
): Parcelable

@Parcelize
data class Timezone (
    val offset: String,
    val description: String
): Parcelable

@Parcelize
data class Login (
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
): Parcelable

@Parcelize
data class Name (
    val title: String,
    val first: String,
    val last: String
): Parcelable

@Parcelize
data class Picture (
    val large: String,
    val medium: String,
    val thumbnail: String
): Parcelable
