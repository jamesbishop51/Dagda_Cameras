package edu.jbishop.dagda_cameras


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.management.ManagementException
import com.auth0.android.management.UsersAPIClient
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import edu.jbishop.dagda_cameras.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var account: Auth0
    private lateinit var binding: ActivityLoginBinding
    private var cachedCredentials: Credentials? = null
    private var cachedUserProfile: UserProfile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //with kotlin it is easier to use view binding to assign the xml to a variable.
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the account object with the Auth0 application details
        account = Auth0(
                getString(R.string.com_auth0_client_id),
                getString(R.string.com_auth0_domain)
        )
        binding.buttonLogin.setOnClickListener { loginWithBrowser() }
    }
    //this updates and passes the user information to the rest of the app.
    private fun update() {
        val name = cachedUserProfile?.name
        val email = cachedUserProfile?.email
        val verified = cachedUserProfile?.isEmailVerified.toString()
        val imgurl = cachedUserProfile?.pictureURL


        val intent = Intent (this@LoginActivity, MainActivity::class.java )
        intent.putExtra("Email",email)
        intent.putExtra("ImgURL",imgurl)
        intent.putExtra("Name",name)
        intent.putExtra("Verified",verified)
        startActivity(intent)

    }
    private fun loginWithBrowser() {
        // Setup the WebAuthProvider, using the custom scheme and scope.
        WebAuthProvider.login(account)
                .withScheme("demo")
                .withScope("openid profile email read:current_user update:current_user_metadata")
                .withAudience("https://${getString(R.string.com_auth0_domain)}/api/v2/")

                // Launch the authentication passing the callback where the results will be received
                .start(this, object : Callback<Credentials, AuthenticationException> {
                    override fun onFailure(exception: AuthenticationException) {
                        showSnackBar("Failure: ${exception.getCode()}")
                    }

                    override fun onSuccess(credentials: Credentials) {
                        cachedCredentials = credentials
                        showSnackBar("Success: ${credentials.accessToken}")
                        /*val intent = Intent (this@LoginActivitykt, MainActivity::class.java )
                        startActivity(intent)*/
                        update()
                        showUserProfile()
                    }
                })
    }
    private fun showUserProfile() {
        val client = AuthenticationAPIClient(account)

        // Use the access token to call userInfo endpoint.
        // In this sample, we can assume cachedCredentials has been initialized by this point.
        client.userInfo(cachedCredentials!!.accessToken!!)
                .start(object : Callback<UserProfile, AuthenticationException> {
                    override fun onFailure(exception: AuthenticationException) {
                        showSnackBar("Failure: ${exception.getCode()}")
                    }

                    override fun onSuccess(profile: UserProfile) {
                        cachedUserProfile = profile;
                        update()
                    }
                })
    }
    private fun showSnackBar(text: String) {
        Snackbar.make(
                binding.root,
                text,
                Snackbar.LENGTH_LONG
        ).show()
    }
}