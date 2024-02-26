package io.lostpacket.breadit.app.config

import io.lostpacket.breadit.BuildConfig

object Config {

    private const val DURATION = "temporary" // or permanent
    private const val SCOPE = "read" // or identity, edit, flair, history, modconfig, modflair, modlog, modposts, modwiki, mysubreddits, privatemessages, read, report, save, submit, subscribe, vote, wikiedit, wikiread.
    private const val TYPE = "code" // uh, nfi
    private const val STATE = BuildConfig.STATE

    fun redirectUri(): String = BuildConfig.REDIRECT_URI
    fun clientId(): String = BuildConfig.OAUTH_CLIENT_ID

    fun state() = BuildConfig.STATE

    fun loginUri() : String {
        return "https://www.reddit.com/api/v1/authorize?client_id=${clientId()}&response_type=$TYPE&state=${state()}&redirect_uri=${redirectUri()}&duration=$DURATION&scope=$SCOPE"
    }
}