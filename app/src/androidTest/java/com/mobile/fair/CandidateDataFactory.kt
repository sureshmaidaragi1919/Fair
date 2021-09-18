package com.mobile.fair

import com.mobile.fair.data.entities.Candidate
import com.mobile.fair.data.entities.Location

object CandidateDataFactory {

    val candidate = Candidate(
        id = "",
        firstName = "",
        lastName = "",
        dateOfBirth = "",
        email = "",
        gender = "",
        location = Location(
            street = "",
            city = "",
            state = "",
            country = "",
            timezone = ""
        ),
        phone = "",
        picture = "",
        title = ""
    )

    val candidateList = listOf(
        Candidate(
            id = "",
            firstName = "",
            lastName = "",
            dateOfBirth = "",
            email = "",
            gender = "",
            location = Location(
                street = "",
                city = "",
                state = "",
                country = "",
                timezone = ""
            ),
            phone = "",
            picture = "",
            title = ""
        )
    )
}