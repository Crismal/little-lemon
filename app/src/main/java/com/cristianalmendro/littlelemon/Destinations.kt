package com.cristianalmendro.littlelemon

interface Destinations {
    val navigationRoute: String
}

object Onboarding : Destinations {
    override val navigationRoute: String
        get() = "Onboarding"
}

object Home : Destinations {
    override val navigationRoute: String
        get() = "Home"
}

object Profile : Destinations {
    override val navigationRoute: String
        get() = "Profile"
}