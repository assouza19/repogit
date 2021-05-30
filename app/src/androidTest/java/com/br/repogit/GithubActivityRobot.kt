package com.br.repogit

import android.content.Intent
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After

private const val serverPort = 8080

fun onActivity(func: GithubActivityRobot.() -> Unit) = GithubActivityRobot().apply { func() }

class GithubActivityRobot : GithubActivityAssertionRobot() {

    private val server = MockWebServer()

    fun setupMocks() = runBlocking {
        server.start(serverPort)

        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "search/repositories" -> successResponse
                    else -> errorResponse
                }
            }
        }
    }

    fun launch() {
        activityRule.launchActivity(Intent())
    }

    @After
    fun tearDown() {
        server.shutdown()
    }


    companion object {
        private val successResponse by lazy {
            val body =
                "{\n" +
                        "\t\"total_count\": 550369,\n" +
                        "\t\"incomplete_results\": false,\n" +
                        "\t\"items\": [{\n" +
                        "\t\t\"id\": 51148780,\n" +
                        "\t\t\"node_id\": \"MDEwOlJlcG9zaXRvcnk1MTE0ODc4MA==\",\n" +
                        "\t\t\"name\": \"architecture-samples\",\n" +
                        "\t\t\"full_name\": \"android/architecture-samples\",\n" +
                        "\t\t\"private\": false,\n" +
                        "\t\t\"owner\": {\n" +
                        "\t\t\t\"login\": \"android\",\n" +
                        "\t\t\t\"id\": 32689599,\n" +
                        "\t\t\t\"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjMyNjg5NTk5\",\n" +
                        "\t\t\t\"avatar_url\": \"https://avatars.githubusercontent.com/u/32689599?v=4\",\n" +
                        "\t\t\t\"gravatar_id\": \"\",\n" +
                        "\t\t\t\"url\": \"https://api.github.com/users/android\",\n" +
                        "\t\t\t\"html_url\": \"https://github.com/android\",\n" +
                        "\t\t\t\"followers_url\": \"https://api.github.com/users/android/followers\",\n" +
                        "\t\t\t\"following_url\": \"https://api.github.com/users/android/following{/other_user}\",\n" +
                        "\t\t\t\"gists_url\": \"https://api.github.com/users/android/gists{/gist_id}\",\n" +
                        "\t\t\t\"starred_url\": \"https://api.github.com/users/android/starred{/owner}{/repo}\",\n" +
                        "\t\t\t\"subscriptions_url\": \"https://api.github.com/users/android/subscriptions\",\n" +
                        "\t\t\t\"organizations_url\": \"https://api.github.com/users/android/orgs\",\n" +
                        "\t\t\t\"repos_url\": \"https://api.github.com/users/android/repos\",\n" +
                        "\t\t\t\"events_url\": \"https://api.github.com/users/android/events{/privacy}\",\n" +
                        "\t\t\t\"received_events_url\": \"https://api.github.com/users/android/received_events\",\n" +
                        "\t\t\t\"type\": \"Organization\",\n" +
                        "\t\t\t\"site_admin\": false\n" +
                        "\t\t},\n" +
                        "\t\t\"html_url\": \"https://github.com/android/architecture-samples\",\n" +
                        "\t\t\"description\": \"A collection of samples to discuss and showcase different architectural tools and patterns for Android apps.\",\n" +
                        "\t\t\"fork\": false,\n" +
                        "\t\t\"url\": \"https://api.github.com/repos/android/architecture-samples\",\n" +
                        "\t\t\"forks_url\": \"https://api.github.com/repos/android/architecture-samples/forks\",\n" +
                        "\t\t\"keys_url\": \"https://api.github.com/repos/android/architecture-samples/keys{/key_id}\",\n" +
                        "\t\t\"collaborators_url\": \"https://api.github.com/repos/android/architecture-samples/collaborators{/collaborator}\",\n" +
                        "\t\t\"teams_url\": \"https://api.github.com/repos/android/architecture-samples/teams\",\n" +
                        "\t\t\"hooks_url\": \"https://api.github.com/repos/android/architecture-samples/hooks\",\n" +
                        "\t\t\"issue_events_url\": \"https://api.github.com/repos/android/architecture-samples/issues/events{/number}\",\n" +
                        "\t\t\"events_url\": \"https://api.github.com/repos/android/architecture-samples/events\",\n" +
                        "\t\t\"assignees_url\": \"https://api.github.com/repos/android/architecture-samples/assignees{/user}\",\n" +
                        "\t\t\"branches_url\": \"https://api.github.com/repos/android/architecture-samples/branches{/branch}\",\n" +
                        "\t\t\"tags_url\": \"https://api.github.com/repos/android/architecture-samples/tags\",\n" +
                        "\t\t\"blobs_url\": \"https://api.github.com/repos/android/architecture-samples/git/blobs{/sha}\",\n" +
                        "\t\t\"git_tags_url\": \"https://api.github.com/repos/android/architecture-samples/git/tags{/sha}\",\n" +
                        "\t\t\"git_refs_url\": \"https://api.github.com/repos/android/architecture-samples/git/refs{/sha}\",\n" +
                        "\t\t\"trees_url\": \"https://api.github.com/repos/android/architecture-samples/git/trees{/sha}\",\n" +
                        "\t\t\"statuses_url\": \"https://api.github.com/repos/android/architecture-samples/statuses/{sha}\",\n" +
                        "\t\t\"languages_url\": \"https://api.github.com/repos/android/architecture-samples/languages\",\n" +
                        "\t\t\"stargazers_url\": \"https://api.github.com/repos/android/architecture-samples/stargazers\",\n" +
                        "\t\t\"contributors_url\": \"https://api.github.com/repos/android/architecture-samples/contributors\",\n" +
                        "\t\t\"subscribers_url\": \"https://api.github.com/repos/android/architecture-samples/subscribers\",\n" +
                        "\t\t\"subscription_url\": \"https://api.github.com/repos/android/architecture-samples/subscription\",\n" +
                        "\t\t\"commits_url\": \"https://api.github.com/repos/android/architecture-samples/commits{/sha}\",\n" +
                        "\t\t\"git_commits_url\": \"https://api.github.com/repos/android/architecture-samples/git/commits{/sha}\",\n" +
                        "\t\t\"comments_url\": \"https://api.github.com/repos/android/architecture-samples/comments{/number}\",\n" +
                        "\t\t\"issue_comment_url\": \"https://api.github.com/repos/android/architecture-samples/issues/comments{/number}\",\n" +
                        "\t\t\"contents_url\": \"https://api.github.com/repos/android/architecture-samples/contents/{+path}\",\n" +
                        "\t\t\"compare_url\": \"https://api.github.com/repos/android/architecture-samples/compare/{base}...{head}\",\n" +
                        "\t\t\"merges_url\": \"https://api.github.com/repos/android/architecture-samples/merges\",\n" +
                        "\t\t\"archive_url\": \"https://api.github.com/repos/android/architecture-samples/{archive_format}{/ref}\",\n" +
                        "\t\t\"downloads_url\": \"https://api.github.com/repos/android/architecture-samples/downloads\",\n" +
                        "\t\t\"issues_url\": \"https://api.github.com/repos/android/architecture-samples/issues{/number}\",\n" +
                        "\t\t\"pulls_url\": \"https://api.github.com/repos/android/architecture-samples/pulls{/number}\",\n" +
                        "\t\t\"milestones_url\": \"https://api.github.com/repos/android/architecture-samples/milestones{/number}\",\n" +
                        "\t\t\"notifications_url\": \"https://api.github.com/repos/android/architecture-samples/notifications{?since,all,participating}\",\n" +
                        "\t\t\"labels_url\": \"https://api.github.com/repos/android/architecture-samples/labels{/name}\",\n" +
                        "\t\t\"releases_url\": \"https://api.github.com/repos/android/architecture-samples/releases{/id}\",\n" +
                        "\t\t\"deployments_url\": \"https://api.github.com/repos/android/architecture-samples/deployments\",\n" +
                        "\t\t\"created_at\": \"2016-02-05T13:42:07Z\",\n" +
                        "\t\t\"updated_at\": \"2021-05-29T16:41:10Z\",\n" +
                        "\t\t\"pushed_at\": \"2021-04-25T14:00:45Z\",\n" +
                        "\t\t\"git_url\": \"git://github.com/android/architecture-samples.git\",\n" +
                        "\t\t\"ssh_url\": \"git@github.com:android/architecture-samples.git\",\n" +
                        "\t\t\"clone_url\": \"https://github.com/android/architecture-samples.git\",\n" +
                        "\t\t\"svn_url\": \"https://github.com/android/architecture-samples\",\n" +
                        "\t\t\"homepage\": \"\",\n" +
                        "\t\t\"size\": 12253,\n" +
                        "\t\t\"stargazers_count\": 38806,\n" +
                        "\t\t\"watchers_count\": 38806,\n" +
                        "\t\t\"language\": \"Kotlin\",\n" +
                        "\t\t\"has_issues\": true,\n" +
                        "\t\t\"has_projects\": true,\n" +
                        "\t\t\"has_downloads\": true,\n" +
                        "\t\t\"has_wiki\": true,\n" +
                        "\t\t\"has_pages\": false,\n" +
                        "\t\t\"forks_count\": 10683,\n" +
                        "\t\t\"mirror_url\": null,\n" +
                        "\t\t\"archived\": false,\n" +
                        "\t\t\"disabled\": false,\n" +
                        "\t\t\"open_issues_count\": 183,\n" +
                        "\t\t\"license\": {\n" +
                        "\t\t\t\"key\": \"apache-2.0\",\n" +
                        "\t\t\t\"name\": \"Apache License 2.0\",\n" +
                        "\t\t\t\"spdx_id\": \"Apache-2.0\",\n" +
                        "\t\t\t\"url\": \"https://api.github.com/licenses/apache-2.0\",\n" +
                        "\t\t\t\"node_id\": \"MDc6TGljZW5zZTI=\"\n" +
                        "\t\t},\n" +
                        "\t\t\"forks\": 10683,\n" +
                        "\t\t\"open_issues\": 183,\n" +
                        "\t\t\"watchers\": 38806,\n" +
                        "\t\t\"default_branch\": \"main\",\n" +
                        "\t\t\"score\": 1.0\n" +
                        "\t}]\n" +
                        "}"

            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }
}