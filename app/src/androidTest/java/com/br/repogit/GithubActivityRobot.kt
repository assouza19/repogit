package com.br.repogit

import android.content.Intent
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

private const val serverPort = 8080

fun onActivity(func: GithubActivityRobot.() -> Unit) = GithubActivityRobot().apply { func() }

class GithubActivityRobot : GithubActivityAssertionRobot() {

    private val server = MockWebServer()

    fun setUp() {
        server.start(serverPort)

        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {

                    "/search/repositories?q=language%3Akotlin&sort=stars&page=1" -> {
                        successResponse
                    }
                    else -> {
                        errorResponse
                    }
                }
            }
        }
    }

    fun launch() {
        activityRule.launchActivity(Intent())
    }

    companion object {
        private val successResponse by lazy {
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    "{\n" +
                            "\"total_count\": 550369,\n" +
                            "\"incomplete_results\": false,\n" +
                            "\"items\": [{\n" +
                            "\"id\": 51148780,\n" +
                            "\"node_id\": \"MDEwOlJlcG9zaXRvcnk1MTE0ODc4MA==\",\n" +
                            "\"name\": \"architecture-samples\",\n" +
                            "\"full_name\": \"android/architecture-samples\",\n" +
                            "\"private\": false,\n" +
                            "\"owner\": {\n" +
                            "\"login\": \"android\",\n" +
                            "\"id\": 32689599,\n" +
                            "\"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjMyNjg5NTk5\",\n" +
                            "\"avatar_url\": \"https://avatars.githubusercontent.com/u/32689599?v=4\",\n" +
                            "\"gravatar_id\": \"\",\n" +
                            "\"url\": \"https://api.github.com/users/android\",\n" +
                            "\"html_url\": \"https://github.com/android\",\n" +
                            "\"followers_url\": \"https://api.github.com/users/android/followers\",\n" +
                            "\"following_url\": \"https://api.github.com/users/android/following{/other_user}\",\n" +
                            "\"gists_url\": \"https://api.github.com/users/android/gists{/gist_id}\",\n" +
                            "\"starred_url\": \"https://api.github.com/users/android/starred{/owner}{/repo}\",\n" +
                            "\"subscriptions_url\": \"https://api.github.com/users/android/subscriptions\",\n" +
                            "\"organizations_url\": \"https://api.github.com/users/android/orgs\",\n" +
                            "\"repos_url\": \"https://api.github.com/users/android/repos\",\n" +
                            "\"events_url\": \"https://api.github.com/users/android/events{/privacy}\",\n" +
                            "\"received_events_url\": \"https://api.github.com/users/android/received_events\",\n" +
                            "\"type\": \"Organization\",\n" +
                            "\"site_admin\": false\n" +
                            "},\n" +
                            "\"html_url\": \"https://github.com/android/architecture-samples\",\n" +
                            "\"description\": \"A collection of samples to discuss and showcase different architectural tools and patterns for Android apps.\",\n" +
                            "\"fork\": false,\n" +
                            "\"url\": \"https://api.github.com/repos/android/architecture-samples\",\n" +
                            "\"forks_url\": \"https://api.github.com/repos/android/architecture-samples/forks\",\n" +
                            "\"keys_url\": \"https://api.github.com/repos/android/architecture-samples/keys{/key_id}\",\n" +
                            "\"collaborators_url\": \"https://api.github.com/repos/android/architecture-samples/collaborators{/collaborator}\",\n" +
                            "\"teams_url\": \"https://api.github.com/repos/android/architecture-samples/teams\",\n" +
                            "\"hooks_url\": \"https://api.github.com/repos/android/architecture-samples/hooks\",\n" +
                            "\"issue_events_url\": \"https://api.github.com/repos/android/architecture-samples/issues/events{/number}\",\n" +
                            "\"events_url\": \"https://api.github.com/repos/android/architecture-samples/events\",\n" +
                            "\"assignees_url\": \"https://api.github.com/repos/android/architecture-samples/assignees{/user}\",\n" +
                            "\"branches_url\": \"https://api.github.com/repos/android/architecture-samples/branches{/branch}\",\n" +
                            "\"tags_url\": \"https://api.github.com/repos/android/architecture-samples/tags\",\n" +
                            "\"blobs_url\": \"https://api.github.com/repos/android/architecture-samples/git/blobs{/sha}\",\n" +
                            "\"git_tags_url\": \"https://api.github.com/repos/android/architecture-samples/git/tags{/sha}\",\n" +
                            "\"git_refs_url\": \"https://api.github.com/repos/android/architecture-samples/git/refs{/sha}\",\n" +
                            "\"trees_url\": \"https://api.github.com/repos/android/architecture-samples/git/trees{/sha}\",\n" +
                            "\"statuses_url\": \"https://api.github.com/repos/android/architecture-samples/statuses/{sha}\",\n" +
                            "\"languages_url\": \"https://api.github.com/repos/android/architecture-samples/languages\",\n" +
                            "\"stargazers_url\": \"https://api.github.com/repos/android/architecture-samples/stargazers\",\n" +
                            "\"contributors_url\": \"https://api.github.com/repos/android/architecture-samples/contributors\",\n" +
                            "\"subscribers_url\": \"https://api.github.com/repos/android/architecture-samples/subscribers\",\n" +
                            "\"subscription_url\": \"https://api.github.com/repos/android/architecture-samples/subscription\",\n" +
                            "\"commits_url\": \"https://api.github.com/repos/android/architecture-samples/commits{/sha}\",\n" +
                            "\"git_commits_url\": \"https://api.github.com/repos/android/architecture-samples/git/commits{/sha}\",\n" +
                            "\"comments_url\": \"https://api.github.com/repos/android/architecture-samples/comments{/number}\",\n" +
                            "\"issue_comment_url\": \"https://api.github.com/repos/android/architecture-samples/issues/comments{/number}\",\n" +
                            "\"contents_url\": \"https://api.github.com/repos/android/architecture-samples/contents/{+path}\",\n" +
                            "\"compare_url\": \"https://api.github.com/repos/android/architecture-samples/compare/{base}...{head}\",\n" +
                            "\"merges_url\": \"https://api.github.com/repos/android/architecture-samples/merges\",\n" +
                            "\"archive_url\": \"https://api.github.com/repos/android/architecture-samples/{archive_format}{/ref}\",\n" +
                            "\"downloads_url\": \"https://api.github.com/repos/android/architecture-samples/downloads\",\n" +
                            "\"issues_url\": \"https://api.github.com/repos/android/architecture-samples/issues{/number}\",\n" +
                            "\"pulls_url\": \"https://api.github.com/repos/android/architecture-samples/pulls{/number}\",\n" +
                            "\"milestones_url\": \"https://api.github.com/repos/android/architecture-samples/milestones{/number}\",\n" +
                            "\"notifications_url\": \"https://api.github.com/repos/android/architecture-samples/notifications{?since,all,participating}\",\n" +
                            "\"labels_url\": \"https://api.github.com/repos/android/architecture-samples/labels{/name}\",\n" +
                            "\"releases_url\": \"https://api.github.com/repos/android/architecture-samples/releases{/id}\",\n" +
                            "\"deployments_url\": \"https://api.github.com/repos/android/architecture-samples/deployments\",\n" +
                            "\"created_at\": \"2016-02-05T13:42:07Z\",\n" +
                            "\"updated_at\": \"2021-05-29T16:41:10Z\",\n" +
                            "\"pushed_at\": \"2021-04-25T14:00:45Z\",\n" +
                            "\"git_url\": \"git://github.com/android/architecture-samples.git\",\n" +
                            "\"ssh_url\": \"git@github.com:android/architecture-samples.git\",\n" +
                            "\"clone_url\": \"https://github.com/android/architecture-samples.git\",\n" +
                            "\"svn_url\": \"https://github.com/android/architecture-samples\",\n" +
                            "\"homepage\": \"\",\n" +
                            "\"size\": 12253,\n" +
                            "\"stargazers_count\": 38806,\n" +
                            "\"watchers_count\": 38806,\n" +
                            "\"language\": \"Kotlin\",\n" +
                            "\"has_issues\": true,\n" +
                            "\"has_projects\": true,\n" +
                            "\"has_downloads\": true,\n" +
                            "\"has_wiki\": true,\n" +
                            "\"has_pages\": false,\n" +
                            "\"forks_count\": 10683,\n" +
                            "\"mirror_url\": null,\n" +
                            "\"archived\": false,\n" +
                            "\"disabled\": false,\n" +
                            "\"open_issues_count\": 183,\n" +
                            "\"license\": {\n" +
                            "\"key\": \"apache-2.0\",\n" +
                            "\"name\": \"Apache License 2.0\",\n" +
                            "\"spdx_id\": \"Apache-2.0\",\n" +
                            "\"url\": \"https://api.github.com/licenses/apache-2.0\",\n" +
                            "\"node_id\": \"MDc6TGljZW5zZTI=\"\n" +
                            "},\n" +
                            "\"forks\": 10683,\n" +
                            "\"open_issues\": 183,\n" +
                            "\"watchers\": 38806,\n" +
                            "\"default_branch\": \"main\",\n" +
                            "\"score\": 1.0\n" +
                            "}]\n" +
                            "}"
                )
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(503) }
    }
}